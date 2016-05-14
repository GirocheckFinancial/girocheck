/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartbt.vtsuite.manager;

import com.smartbt.girocheck.servercommon.jms.JMSManager;
import com.smartbt.girocheck.servercommon.jms.UtilsJMS;
import com.smartbt.girocheck.servercommon.log.LogUtil;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
import com.smartbt.girocheck.servercommon.enums.ResultCode;
import com.smartbt.girocheck.servercommon.enums.ResultMessage;
import java.io.Serializable;
import java.util.Map;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import org.apache.log4j.Logger;

/**
 *
 * @author Roberto
 */
public class FrontBusinessLogic {
    private static final Logger log = Logger.getLogger(FrontBusinessLogic.class);   
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

        DirexTransactionResponse direxTransactionResponse = null;
        try {
            Map transactionData = direxTransactionRequest.getTransactionData();
            TransactionType transactionType = (TransactionType)transactionData.get( TransactionType.TRANSACTION_TYPE);
            
            LogUtil.logAndStore("Front", "Processing " + transactionType);
            
            
            LogUtil.log("Front", "Sent to core " + direxTransactionRequest.getCorrelation());
           
      
            long wait_time = 50000;
            jmsManager.send(direxTransactionRequest, JMSManager.get().getCoreInQueue(), direxTransactionRequest.getCorrelation());
       
            ObjectMessage tmsg = null;
            
           Message message = jmsManager.receive(jmsManager.getCoreOutQueue(), direxTransactionRequest.getCorrelation(), wait_time);

            LogUtil.log("Front", "Front recived message from Core");

            if (message != null) {
                tmsg = (ObjectMessage) message;
                Serializable s = tmsg.getObject();
                direxTransactionResponse = (DirexTransactionResponse) s;

            } else {
                direxTransactionResponse = DirexTransactionResponse.forException(ResultCode.GIROCHECK_FRONT_ERROR, ResultMessage.FRONT_RECEIVED_NULL);
              
                LogUtil.logAndStore("Front", "Front recived message null from Core");
            }
        } catch (Exception e) {
            direxTransactionResponse = DirexTransactionResponse.forException(ResultCode.GIROCHECK_FRONT_ERROR, e);
            
            e.printStackTrace();
        }
        return direxTransactionResponse;
    }

    /**
     * Perform postprocess operations.
     *
     * @param direxTransactionRequest The transaction request object
     * @param direxTransactionResponse The transaction response object
     *
     * @throws Exception
     */
    private void postprocess(DirexTransactionRequest direxTransactionRequest, DirexTransactionResponse direxTransactionResponse) throws Exception {
//        if (direxTransactionResponse.getApprovalNumber().compareTo("XXXXXX") != 0 && !(direxTransactionRequest.getMode().equalsIgnoreCase(NomMode.MOCK.toString()))) {
//        }
    }
}
