/*
 ** File: HostMDB.java
 **
 ** Date Created: February 2013
 **
 ** Copyright @ 2004-2013 Smart Business Technology, Inc.
 **
 ** All rights reserved. No part of this software may be 
 ** reproduced, transmitted, transcribed, stored in a retrieval 
 ** system, or translated into any language or computer language, 
 ** in any form or by any means, electronic, mechanical, magnetic, 
 ** optical, chemical, manual or otherwise, without the prior 
 ** written permission of Smart Business Technology, Inc.
 **
 */
package com.smartbt.vtsuite.MDB;

import com.smartbt.girocheck.common.ServerJNDI;
import com.smartbt.vtsuite.manager.HostManager;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
import com.smartbt.girocheck.servercommon.jms.JMSManager;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import java.io.Serializable;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import org.apache.log4j.Logger;

/**
 * The Host Message Driven Bean Class
 */
//glassfish configuration
@MessageDriven(mappedName = ServerJNDI.HOST_IN_QUEUE_JNDI, activationConfig = {
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty( propertyName = "messageSelector", propertyValue = "hostName = 'FUZE'" )
})
//jboss configuration
//@MessageDriven(mappedName = ServerJNDI.HOST_IN_QUEUE, activationConfig = {
//    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
//    @ActivationConfigProperty(propertyName = "destination", propertyValue = ServerJNDI.HOST_IN_QUEUE_JNDI),
//    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
//})
@TransactionManagement(value = TransactionManagementType.BEAN)
public class HostMDB implements MessageListener {

//    private static final Logger log = Logger.getLogger(HostMDB.class);
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(HostMDB.class);

    /**
     * Constructor
     */
    public HostMDB() {
    }

    /**
     * Host Bean.
     *
     * @param message The JMS message
     */
    @Override
    @SuppressWarnings("null")
    public void onMessage(Message message) {
        log.info("[HostMDB] Entered to FuzeHostEJB");
        HibernateUtil.beginTransaction();
        DirexTransactionRequest requestData;

        try {
            if (message instanceof ObjectMessage) {
                String correlationId = message.getJMSCorrelationID();
                
                ObjectMessage obj = (ObjectMessage) message;
                Serializable s = obj.getObject();
                requestData = (DirexTransactionRequest) s;

                log.debug("[HostMDB] correlationId = " + correlationId);

                DirexTransactionResponse response = HostManager.get().process(requestData);

//                if (requestData.isResponseRequired()) {
                    JMSManager.get().send(response, JMSManager.get().getHostOutQueue(), correlationId);
//                }
                log.info("[HostMDB] Sent message to CORE.. correlationId: " + correlationId);
            } else {
                log.error("[HostMDB] Getting Message ");
            }
            HibernateUtil.commitTransaction();
        } catch (Exception e) {
            log.error("[HostMDB] Message Exception", e);
            HibernateUtil.rollbackTransaction();
        }
    }
}
