/*
 ** File: CoreMDB.java
 **
 ** Date Created: April 2013
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

import com.smartbt.vtsuite.manager.CoreTransactionManager;
import com.smartbt.girocheck.servercommon.jms.JMSManager;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
import com.smartbt.girocheck.common.ServerJNDI;
import com.smartbt.girocheck.servercommon.log.LogUtil;
import com.smartbt.girocheck.servercommon.enums.ResultCode;
import com.smartbt.girocheck.servercommon.enums.ResultMessage;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import com.smartbt.girocheck.servercommon.utils.DirexException;
import java.io.Serializable;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/**
 * The Core Message Driven Bean class
 */
@MessageDriven(mappedName = ServerJNDI.CORE_IN_QUEUE_JNDI, activationConfig = {
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
@TransactionManagement(value = TransactionManagementType.BEAN)
public class CoreMDB implements MessageListener {

//    private static final Logger log = Logger.getLogger(CoreMDB.class);
//    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(CoreMDB.class);

    /**
     * Constructor
     */
    public CoreMDB() {
    }

    /**
     * Core Bean.
     *
     * @param message
     */
    @Override
    public void onMessage(Message message) {
        
        LogUtil.clearBuffer();
        
//        LogUtil.logAndStore( "Core MDB", "Message recived in CoreMDB");
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CoreMDB] Message recived in CoreMDB", null);
        CoreTransactionManager coreManager = new CoreTransactionManager();
        JMSManager jmsManager = JMSManager.get();

        DirexTransactionRequest direxTransactionRequest = null;
        DirexTransactionResponse direxTransactionResponse = null;

        try {

            if (message instanceof ObjectMessage) {
                ObjectMessage obj = (ObjectMessage) message;
                Serializable s = obj.getObject();

                direxTransactionRequest = (DirexTransactionRequest) s;

                coreManager.processTransaction(direxTransactionRequest);
                
              } else {
                direxTransactionResponse = DirexTransactionResponse.forException(ResultCode.CORE_RECEIVED_NULL, ResultMessage.CORE_RECEIVED_NULL);
            }
                
   
        } catch (Exception e) {
            try {
                if (e instanceof DirexException) {
                    if (((DirexException) e).getResultCode().equals(ResultCode.LOGIN_FAILED)) {
                        direxTransactionResponse = DirexTransactionResponse.forException(ResultCode.CORE_ERROR, ResultMessage.LOGIN_FAILED, e.getMessage(),"");
                        jmsManager.send(direxTransactionResponse, jmsManager.getCoreOutQueue(), direxTransactionRequest.getCorrelation());
                    } else if (((DirexException) e).getResultCode().equals(ResultCode.CREDIT_CARD_NOT_EXIST)) {
                        direxTransactionResponse = DirexTransactionResponse.forException(ResultCode.CORE_ERROR, ResultMessage.CREDIT_CARD_NOT_EXIST, e.getMessage(),"");
                        jmsManager.send(direxTransactionResponse, jmsManager.getCoreOutQueue(), direxTransactionRequest.getCorrelation());
                    } else if (((DirexException) e).getResultCode().equals(ResultCode.TERMINAL_ID_NOT_EXIST)) {
                        direxTransactionResponse = DirexTransactionResponse.forException(ResultCode.CORE_ERROR, ResultMessage.TERMINAL_ID_NOT_EXIST, e.getMessage(),"");
                        jmsManager.send(direxTransactionResponse, jmsManager.getCoreOutQueue(), direxTransactionRequest.getCorrelation());
                    }

                } else {
                    direxTransactionResponse = DirexTransactionResponse.forException(ResultCode.CORE_ERROR, e);
                    jmsManager.send(direxTransactionResponse, jmsManager.getCoreOutQueue(), direxTransactionRequest.getCorrelation());
                }
            } catch (Exception ex) {
                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreMDB] Core Bean an exception has occurred: "+ e.getMessage(),ex.getMessage());
            }
        }

    }

}
