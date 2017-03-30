/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.vtsuite.manager;

import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.enums.ResultCode;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
import com.smartbt.girocheck.servercommon.utils.IMap;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Roberto
 */
public class FrontManager {

      public static FrontBusinessLogic businessLogic = new FrontBusinessLogic(); 

    public FrontManager() {
    }

    public static Map processTransaction(IMap request) throws Exception {

        DirexTransactionRequest direxTransactionRequest = new DirexTransactionRequest();

        Map map = request.toMap();

        Map transactionData;

        if (map.containsKey(ParameterName.VALIDATION_ERROR)) {
            transactionData = new HashMap();

            transactionData.put(ParameterName.RESULT_CODE, ResultCode.ISTREAM_FRONT_VALIDATION_ERROR);
            transactionData.put(ParameterName.RESULT_MESSAGE, "VALIDATION ERROR: " + map.get(ParameterName.VALIDATION_ERROR));
            return transactionData;
        }

        direxTransactionRequest.setTransactionData(map);

        try {
            DirexTransactionResponse response = businessLogic.handle(direxTransactionRequest);

            transactionData = response.getTransactionData();

            transactionData.put(ParameterName.RESULT_CODE, String.valueOf(response.getResultCode().getCode()));
            transactionData.put(ParameterName.RESULT_MESSAGE, response.getResultMessage());

        } catch (Exception e) {
            transactionData = new HashMap();
            transactionData.put(ParameterName.RESULT_CODE, String.valueOf(ResultCode.FAILED.getCode()));
            transactionData.put(ParameterName.RESULT_MESSAGE, e.getMessage());
        }
        return transactionData;
    }
    
     
}
