/*
 ** File: IStreamMDB.java
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
//com.smartbt.vtsuite.jms.manager.RequestMessage
//import com.smartbt.vtsuite.manager.IStreamHostManager;
import com.smartbt.girocheck.common.ServerJNDI;
import com.smartbt.vtsuite.manager.OrderExpressHostManager;
import com.smartbt.girocheck.servercommon.jms.JMSManager;
import com.smartbt.girocheck.servercommon.log.LogUtil;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
import com.smartbt.girocheck.servercommon.enums.ResultCode;
import com.smartbt.girocheck.servercommon.enums.ResultMessage;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;

import java.io.Serializable;
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
    @ActivationConfigProperty( propertyName = "messageSelector", propertyValue = "hostName = 'ORDER_EXPRESS'" )
} )
@TransactionManagement( value = TransactionManagementType.BEAN )
public class OrderExpressMDB implements MessageListener {
    
//    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(OrderExpressMDB.class);
    

      private static JMSManager jmsManager = JMSManager.get();
      private OrderExpressHostManager hostManager = new OrderExpressHostManager();
  
      
    boolean logMessages=true;
        
    /**
     * Constructor
     */
    public OrderExpressMDB() {
    }

    /**
     * Host Bean.
     *
     * @param message The JMS message
     */
    @Override
    @SuppressWarnings( "null" )
    public void onMessage( Message message ) {
//       log.info( "[OrderExpressMDB] Recived message. ");
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[OrderExpressMDB] Recived message.",null);
//        System.out.println("[OrderExpressMDB] Recived message. ");

        DirexTransactionRequest direxTransactionRequest = null;
        DirexTransactionResponse direxTransactionResponse = null;

        
        try {
            if (message instanceof ObjectMessage) {
                ObjectMessage obj = (ObjectMessage) message;

                Serializable s = obj.getObject();
                direxTransactionRequest = (DirexTransactionRequest) s;

             
               direxTransactionResponse = hostManager.processTransaction(direxTransactionRequest, 6);  //6 atempts, if OP_CODE2 = 025, resubmit again.

            } else {
                   direxTransactionResponse = DirexTransactionResponse.forException(ResultCode.ORDER_EXPRESS_HOST_RECEIVED_NULL, ResultMessage.HOST_RECEIVED_NULL);
            }

//             log.debug( "[OrderExpressMDB] Sent message to Core. correlation :: "+ direxTransactionRequest.getCorrelation());
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[OrderExpressMDB] Sent message to Core. correlation :: "+ direxTransactionRequest.getCorrelation(),null);
//             System.out.println("[OrderExpressMDB] Sent message to Core. correlation :: "+ direxTransactionRequest.getCorrelation());
            jmsManager.send(direxTransactionResponse, jmsManager.getHostOutQueue(), direxTransactionRequest.getCorrelation());

        } catch (Exception e) {
            try {
                e.printStackTrace();
                
              direxTransactionResponse = DirexTransactionResponse.forException(ResultCode.ORDER_EXPRESS_HOST_ERROR, e);
              
              jmsManager.send(direxTransactionResponse, jmsManager.getHostOutQueue(), direxTransactionRequest.getCorrelation());
            } catch (Exception ex) {
//               LogUtil.log( "OrderExp MDB", "             Throw Exception "+ e.getMessage());
//               log.debug( "[OrderExpressMDB] Throw Exception "+ e.getMessage(),ex);
                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[OrderExpressMDB] Error",ex.getMessage());
//                System.out.println("[OrderExpressMDB] Throw Exception "+ e.getMessage());
            }
        }
 
    }
    
}
