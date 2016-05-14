/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.vtsuite.manager;

import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
import com.smartbt.girocheck.servercommon.enums.ResultCode;
import com.smartbt.girocheck.servercommon.enums.ResultMessage;
import com.smartbt.girocheck.servercommon.utils.IMap;
import java.util.HashMap;
import java.util.Map;
import javax.jms.JMSException;

/**
 *
 * @author Roberto
 */
public class FrontManager {

      public static FrontBusinessLogic businessLogic = new FrontBusinessLogic();
   // public static TestBusinessLogic businessLogic = new TestBusinessLogic();

    public FrontManager() {
    }

    public static Map processTransaction(IMap request) throws Exception  {
       
        DirexTransactionRequest direxTransactionRequest = new DirexTransactionRequest();
        direxTransactionRequest.setTransactionData(request.toMap());

        DirexTransactionResponse response = businessLogic.handle(direxTransactionRequest);

        Map transactionData = response.getTransactionData();
        
        if (response.wasApproved()){
            transactionData.put(ParameterName.RESULT_CODE, String.valueOf(ResultCode.SUCCESS.getCode()));
            transactionData.put(ParameterName.RESULT_MESSAGE, ResultMessage.SUCCESS.getMessage());
        }else{
            transactionData.put(ParameterName.RESULT_CODE,String.valueOf(response.getResultCode().getCode()));
            transactionData.put(ParameterName.RESULT_MESSAGE, response.getResultMessage());
        } 

        return transactionData;
    }
    
     
}
