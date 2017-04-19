/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.vtsuite.manager;

import com.smartbt.girocheck.servercommon.jms.JMSManager;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.enums.ResultCode;
import com.smartbt.girocheck.servercommon.enums.ResultMessage;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;

/**
 *
 * @author Roberto
 */
public class FrontBusinessLogic {

    private JMSManager jmsManager = JMSManager.get();

    public DirexTransactionResponse handle( DirexTransactionRequest request ) throws Exception {
        DirexTransactionRequest direxTransactionRequest = preprocess( request );
        DirexTransactionResponse response = process( direxTransactionRequest );
        postprocess( request, response );
        return response;
    }

    private DirexTransactionRequest preprocess( DirexTransactionRequest direxTransactionRequest ) throws Exception {

        return direxTransactionRequest;
    }

    private DirexTransactionResponse process( DirexTransactionRequest direxTransactionRequest ) throws Exception {

        DirexTransactionResponse direxTransactionResponse = null;
        try {
            Map transactionData = direxTransactionRequest.getTransactionData();

            if ( !transactionData.containsKey( ParameterName.CHECK_ID ) ) {

                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[IStreamFront BusinessLogic] Check ID required",null);
                throw new Exception( "Check ID required" );
            }
            String checkId = (String) transactionData.get( ParameterName.CHECK_ID );
            direxTransactionRequest.setCorrelation( checkId );

            TransactionType transactionType = (TransactionType) transactionData.get( TransactionType.TRANSACTION_TYPE );

            System.out.println("transactionType = " + transactionType);
            if(transactionType == TransactionType.CHECK_INFO){
               
                String WESTECH2GIROCHECK_USERNAME = System.getProperty("WESTECH2GIROCHECK_USERNAME");
                String WESTECH2GIROCHECK_PASSWORD = System.getProperty("WESTECH2GIROCHECK_PASSWORD");
                
                System.out.println("WESTECH2GIROCHECK_USERNAME = " + WESTECH2GIROCHECK_USERNAME);
                System.out.println("WESTECH2GIROCHECK_PASSWORD = " + WESTECH2GIROCHECK_PASSWORD);
                
                String user = (String)transactionData.get(ParameterName.USER);
                String password = (String)transactionData.get(ParameterName.USER);
                
                System.out.println("user = " + user);
                System.out.println("password = " + password);
                
                System.out.println("if = " + (!(user.equals(WESTECH2GIROCHECK_USERNAME) && password.equals(WESTECH2GIROCHECK_PASSWORD))));
                
                if(!(user.equals(WESTECH2GIROCHECK_USERNAME) && password.equals(WESTECH2GIROCHECK_PASSWORD))){
                    return DirexTransactionResponse.forException( ResultCode.LOGIN_FAILED, ResultMessage.LOGIN_FAILED );
                }
            }
            
            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[IStreamFront BusinessLogic] Processing "+ transactionType ,null);
            direxTransactionRequest.setTransactionType( transactionType );

            long wait_time = 600000;//10mins

            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[IStreamFront BusinessLogic] Sent message to FrontIStreamOutQueue, correlationId:: " + checkId,null);
            jmsManager.send( direxTransactionRequest, JMSManager.get().getFrontIStreamOutQueue(), direxTransactionRequest.getCorrelation() );

            if(transactionType == TransactionType.CHECK_INFO){
                return DirexTransactionResponse.forSuccess();
            }
            
            ObjectMessage tmsg = null;

                Message message = jmsManager.receive( jmsManager.getFrontIStreamInQueue(), direxTransactionRequest.getCorrelation(), wait_time );

                CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[IStreamFront BusinessLogic] Front recived message from Core.",null);

                if ( message != null ) {
                    tmsg = (ObjectMessage) message;
                    Serializable s = tmsg.getObject();
                    direxTransactionResponse = (DirexTransactionResponse) s;
                    CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[IStreamFront BusinessLogic] transaction approved = " + direxTransactionResponse.wasApproved(),null);
                    System.out.println("[IStreamFront BusinessLogic] after being approved");
                } else {
                    direxTransactionResponse = DirexTransactionResponse.forException( ResultCode.RESPONSE_TIME_EXCEEDED, ResultMessage.RESPONSE_TIME_EXCEEDED );
                    CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[IStreamFront BusinessLogic] Front recived message null from Core.",null);
                }
           

        } catch ( JMSException | IOException e ) {
            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[IStreamFront BusinessLogic] Error.",null);
            throw new Exception( e.getMessage(), e.getCause() );
        }
         System.out.println("[IStreamFront BusinessLogic] before return");
        return direxTransactionResponse;
    }

    private void postprocess( DirexTransactionRequest direxTransactionRequest, DirexTransactionResponse direxTransactionResponse ) throws Exception {
    }
}
