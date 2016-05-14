/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartbt.vtsuite.manager;

import com.smartbt.girocheck.servercommon.jms.JMSManager;
import com.smartbt.girocheck.servercommon.log.LogUtil;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.jms.UtilsJMS;
import com.smartbt.girocheck.servercommon.enums.ResultCode;
import com.smartbt.girocheck.servercommon.enums.ResultMessage;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
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
    
    public DirexTransactionResponse handle(DirexTransactionRequest request) throws Exception {
        DirexTransactionRequest direxTransactionRequest = preprocess(request);
        DirexTransactionResponse response = process(direxTransactionRequest);
        postprocess(request, response);
        return response;
    }

    
    
    private DirexTransactionRequest preprocess(DirexTransactionRequest direxTransactionRequest) throws Exception {
           direxTransactionRequest.setCorrelation(UtilsJMS.generateGUID());   
         return direxTransactionRequest;
    }

    
    private DirexTransactionResponse process(DirexTransactionRequest direxTransactionRequest) throws Exception {
        LogUtil.log("OrderExp Front BL", "Processing transaction...");
        DirexTransactionResponse direxTransactionResponse = null;
        try {
           
            long wait_time = 50000;
            direxTransactionRequest.setTransactionType( (TransactionType)direxTransactionRequest.getTransactionData().get( TransactionType.TRANSACTION_TYPE));
           
            jmsManager.send(direxTransactionRequest, JMSManager.get().getCoreInQueue(), direxTransactionRequest.getCorrelation());
       
            ObjectMessage tmsg = null;
            
           Message message = jmsManager.receive(jmsManager.getCoreOutQueue(), direxTransactionRequest.getCorrelation(), wait_time);

            LogUtil.log("OrderExp Front BL", "Front recived message from Core");

            if (message != null) {
                tmsg = (ObjectMessage) message;
                Serializable s = tmsg.getObject();
                direxTransactionResponse = (DirexTransactionResponse) s;

            } else {
                direxTransactionResponse = DirexTransactionResponse.forException(ResultCode.ISTREAM_FRONT_ERROR, ResultMessage.FRONT_RECEIVED_NULL);
                LogUtil.logAndStore("OrderExp Front BL", "Front recived message null from Core");
            }
        } catch (Exception e) {
            LogUtil.logAndStore("OrderExp Front BL", "JMSException" + e.getMessage());
            throw new Exception(e.getMessage(), e.getCause());
        }
        return direxTransactionResponse;
    }

   
    private void postprocess(DirexTransactionRequest direxTransactionRequest, DirexTransactionResponse direxTransactionResponse) throws Exception {
 }
}
