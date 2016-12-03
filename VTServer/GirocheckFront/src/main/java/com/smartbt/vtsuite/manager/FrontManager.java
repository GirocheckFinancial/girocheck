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
import com.smartbt.girocheck.servercommon.utils.IMap;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Roberto
 */
public class FrontManager {

    public static FrontBusinessLogic businessLogic = new FrontBusinessLogic();
    public static CancelTransactionBusinessLogic cancelBusinessLogic = new CancelTransactionBusinessLogic();

    public FrontManager() {
    }

    public static Map processTransaction(IMap request) throws Exception {
        DirexTransactionRequest direxTransactionRequest = new DirexTransactionRequest();

        Map map = request.toMap();

        Map transactionData;

        if (map.containsKey(ParameterName.VALIDATION_ERROR)) {
            transactionData = new HashMap();

            transactionData.put(ParameterName.RESULT_CODE, ResultCode.MISSING_FIELDS);
            transactionData.put(ParameterName.RESULT_MESSAGE, "VALIDATION ERROR: " + map.get(ParameterName.VALIDATION_ERROR));
            return transactionData;
        }

        direxTransactionRequest.setTransactionData(map);

//        TransactionType transactionType = (TransactionType)transactionData.get( TransactionType.TRANSACTION_TYPE);
        try {
            DirexTransactionResponse response;

//            if(transactionType == TransactionType.ISTREAM_CHECK_AUTH_SUBMIT){
//                response = cancelBusinessLogic.handle( direxTransactionRequest );
//            }else{
            response = businessLogic.handle(direxTransactionRequest);
//            }

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

    public static Map activityReport(IMap request) throws Exception {
        Map input = request.toMap();
        System.out.println("From terminal:: Start Date = " + input.get(ParameterName.START_DATE));
        System.out.println("From terminal:: End Date = " + input.get(ParameterName.END_DATE));

        Map output = null;

        try {
            HibernateUtil.beginTransaction();

            output = businessLogic.activityReport(input);

            HibernateUtil.commitTransaction();

        } catch (Exception e) {
            e.printStackTrace();
            HibernateUtil.rollbackTransaction();
        }

        return output;
    }

}
