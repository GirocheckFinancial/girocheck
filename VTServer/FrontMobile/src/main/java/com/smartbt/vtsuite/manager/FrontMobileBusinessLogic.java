/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.vtsuite.manager;

import com.smartbt.girocheck.common.AbstractBusinessLogicModule;
import com.smartbt.girocheck.servercommon.enums.ResultCode;
import com.smartbt.girocheck.servercommon.enums.ResultMessage;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.jms.JMSManager;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomHost;
import java.io.Serializable;
import java.util.Properties;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;

/**
 *
 * @author Sreekanth
 */
public class FrontMobileBusinessLogic extends AbstractBusinessLogicModule{
    
    private JMSManager jmsManager = JMSManager.get();
    String correlationId;
    
    private static FrontMobileBusinessLogic INSTANCE;
    
    public static synchronized FrontMobileBusinessLogic get() {
        if (INSTANCE == null) {
            INSTANCE = new FrontMobileBusinessLogic();
        }
        return INSTANCE;
    }

    @Override
    protected void preprocess(DirexTransactionRequest request) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected DirexTransactionResponse process(DirexTransactionRequest request) throws Exception {
           
            correlationId = request.getCorrelation();

            long wait_time = 1800000;//30 mins

            DirexTransactionResponse technicardResponse = sendMessageToHost(request, NomHost.TECNICARD.toString(), wait_time, false);
            
           
            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "Technicard Resposonse " +technicardResponse, null);        
            
            return technicardResponse;
    }

    @Override
    protected void postprocess(DirexTransactionRequest request, DirexTransactionResponse response) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    private DirexTransactionResponse sendMessageToHost(DirexTransactionRequest request, String hostName, long waitTime, boolean notifyToIstream) throws JMSException, Exception {
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[TransactionAMSManager] Send message to host " + hostName, null);

        Properties props = new Properties();
        props.setProperty("hostName", hostName);

        jmsManager.sendWithProps(request, jmsManager.getHostInQueue(), correlationId, props);

        if (request.getTransactionType() == TransactionType.TECNICARD_LAST_TRANSACTIONS) {
            return receiveMessageFromHost(request.getTransactionType(), hostName, waitTime, notifyToIstream);
        } else {
            return null;
        }
    }
    

    private DirexTransactionResponse receiveMessageFromHost(TransactionType transactionType, String hostName, long waitTime, boolean notifyToIstream) throws Exception {
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[TransactionAMSManager] Receiving message from host " + hostName, null);

        Message message = null;
        DirexTransactionResponse response;
        try {
            message = jmsManager.receive(jmsManager.getHostOutQueue(), correlationId, waitTime);
        } catch (Exception e) {
            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[TransactionAMSManager] receiving message on receiveMessageFromHost() throws an exeption.", null);
            response = DirexTransactionResponse.forException(transactionType, ResultCode.RESPONSE_TIME_EXCEEDED, ResultMessage.RESPONSE_TIME_EXCEEDED, " " + hostName);
            response.setTransactionType(transactionType);

            throw new Exception();
        }


        ObjectMessage objectMessage = (ObjectMessage) message;
        Serializable s = objectMessage.getObject();
        response = (DirexTransactionResponse) s;

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[TransactionAMSManager] Message received", null);

        return response;
    }
    
}
