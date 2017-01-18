/*
 ** File: WestechMDB.java
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
import com.smartbt.vtsuite.manager.WestechHostManager;
import com.smartbt.girocheck.servercommon.jms.JMSManager;

import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
import com.smartbt.girocheck.servercommon.enums.ResultCode;
import com.smartbt.girocheck.servercommon.enums.ResultMessage;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.jms.*;

/**
 * The Host Message Driven Bean Class
 */
//glassfish configuration
@MessageDriven( mappedName = ServerJNDI.HOST_IN_QUEUE_JNDI, activationConfig = {
    @ActivationConfigProperty( propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge" ),
    @ActivationConfigProperty( propertyName = "destinationType", propertyValue = "javax.jms.Queue" ),
    @ActivationConfigProperty( propertyName = "messageSelector", propertyValue = "hostName = 'WESTECH'" )
} )
@TransactionManagement( value = TransactionManagementType.BEAN )
public class WestechMDB implements MessageListener {

//    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(WestechMDB.class);

    private static final JMSManager jmsManager = JMSManager.get();
    
     
    /**
     * Constructor
     */
    public WestechMDB() {
    }

    /**
     * Host Bean.
     *
     * @param message The JMS message
     */
    @Override
    @SuppressWarnings( "null" )
    public void onMessage( Message message ) {
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[WestechMDB] Received Message",null);

        DirexTransactionRequest direxTransactionRequest = null;
        DirexTransactionResponse direxTransactionResponse = null;

        
        
        try {
             WestechHostManager hostManager = WestechHostManager.get();
            
            if (message instanceof ObjectMessage) {
                ObjectMessage obj = (ObjectMessage) message;

                Serializable s = obj.getObject();
                direxTransactionRequest = (DirexTransactionRequest) s;

                 CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[WestechMDB] correelation :: "+ direxTransactionRequest.getCorrelation(),null);
               
                direxTransactionResponse = hostManager.processTransaction(direxTransactionRequest);
  
            } else {
                 direxTransactionResponse = DirexTransactionResponse.forException(ResultCode.CHOICE_HOST_RECEIVED_NULL, ResultMessage.HOST_RECEIVED_NULL);
            }

           CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[WestechMDB] Sent Mesage to Core. " + direxTransactionRequest.getCorrelation(),null);
          
            jmsManager.send(direxTransactionResponse, jmsManager.getHostOutQueue(), direxTransactionRequest.getCorrelation());

        } catch (Exception e) { 
            
            e.printStackTrace();
            
            try {
                direxTransactionResponse = DirexTransactionResponse.forException(ResultCode.TECNICARD_HOST_ERROR, e);
                 
                jmsManager.send(direxTransactionResponse, jmsManager.getHostOutQueue(), direxTransactionRequest.getCorrelation());
            } catch (Exception ex) {
                 CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[WestechMDB] Catch Excetion " + e.getMessage(),ex.getMessage());
            }
        }
 
    }
    
    private void printMap(Map map, String mapName) {
        if (map == null) { 
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[WestechMDB] Map = null",null);
            return;
        }

        Set set = map.keySet();

//        System.out.println("************ " + mapName + " **************");
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[WestechMDB] with map name: " + mapName ,null);
        for (Object string : set) {
            Object obj = map.get(string);
            if (obj instanceof List) {
                for (Object mapItem : (List) obj) {
                    printMap((Map) mapItem, " Map Item ");
                }

            } else {
                System.out.println(string + " - " + map.get(string));
            }
        }
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[WestechMDB] end printing",null);
    }
}
