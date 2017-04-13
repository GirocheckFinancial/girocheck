/*
 ** File: TecnicardHostManager.java
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
package com.smartbt.vtsuite.manager;

//import com.smartbt.vtsuite.servercommon.manager.AuditManager;
import com.smartbt.girocheck.common.AbstractBusinessLogicModule;
import com.smartbt.girocheck.servercommon.display.mobile.MobileTransaction;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.enums.ResultCode;
import com.smartbt.girocheck.servercommon.enums.ResultMessage;
import com.smartbt.girocheck.servercommon.model.SubTransaction;
import com.smartbt.girocheck.servercommon.model.Transaction;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import com.smartbt.vtsuite.mock.MockTecnicardBusinessLogic;
import com.smartbt.vtsuite.util.FixUtil;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomHost;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Host Manager class
 */
public class TecnicardHostManager {

    public static TecnicardHostManager INSTANCE;

    public static TecnicardHostManager get() {
        if (INSTANCE == null) {
            INSTANCE = new TecnicardHostManager();
        }
        return INSTANCE;
    }

    AbstractBusinessLogicModule bizLogic;
    public static Map TRANSACTION_SEQUENCE;
    private static String CODE_000000, CODE_100011, CODE_100012, CODE_100015;
    private Transaction transaction;

    static {
        CODE_000000 = "000000";
        CODE_100011 = "100011";
        CODE_100012 = "100012"; //RELOAD
        CODE_100015 = "100015";

        TRANSACTION_SEQUENCE = new HashMap();

        TransactionType[] resp_000000 = new TransactionType[]{TransactionType.TECNICARD_CARD_HOLDER_VALIDATION};
        TransactionType[] resp_100011 = new TransactionType[]{TransactionType.TECNICARD_CARD_ACTIVATION, TransactionType.TECNICARD_CARD_PERSONALIZATION, TransactionType.TECNICARD_CARD_HOLDER_VALIDATION};
        TransactionType[] resp_100012 = new TransactionType[]{TransactionType.TECNICARD_CARD_ACTIVATION, TransactionType.TECNICARD_CARD_HOLDER_VALIDATION};
        TransactionType[] resp_100015 = new TransactionType[]{TransactionType.TECNICARD_CARD_PERSONALIZATION, TransactionType.TECNICARD_CARD_HOLDER_VALIDATION};

        TRANSACTION_SEQUENCE.put(CODE_000000, resp_000000);
        TRANSACTION_SEQUENCE.put(CODE_100011, resp_100011);
        TRANSACTION_SEQUENCE.put(CODE_100012, resp_100012); //reload
        TRANSACTION_SEQUENCE.put(CODE_100015, resp_100015);
    }

    /**
     * Process Direx Transaction Request.
     *
     * @param request
     * @return DirexTransactionResponse The transaction response object
     * @throws Exception
     */
    public DirexTransactionResponse processTransaction(DirexTransactionRequest request) throws Exception {

        transaction = new Transaction();
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[TecnicardHostManager] Processing req transaction :: " + request.getTransactionType(), null);

        String prodProperty = System.getProperty("PROD");
        System.out.println("TecnicardHostManager() -> prodProperty = " + prodProperty);
        Boolean isProd = prodProperty != null && prodProperty.equalsIgnoreCase("true");
        System.out.println("TecnicardHostManager() -> isProd = " + isProd);

        if (isProd) {
            bizLogic = new TecnicardBusinessLogic();
        } else {
            bizLogic = new MockTecnicardBusinessLogic();
        }

        /**
         * Selecting the correct sequence of transaction to do.
         * sequence(Integer.parseInt(request.getTransactionData().get(ParameterName.CARDLOADTYPE)));
         *
         */
        DirexTransactionResponse response;

        if (request.getTransactionData().containsKey(ParameterName.AMMOUNT)) {
            request.getTransactionData().put(ParameterName.AMMOUNT, FixUtil.fixAmmount(request.getTransactionData().get(ParameterName.AMMOUNT).toString()));
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[TecnicardHostManager] Parsing  amount value :: " + request.getTransactionData().get(ParameterName.AMMOUNT), null);
        }

        if (request.getTransactionData().containsKey(ParameterName.PAYOUT_AMMOUNT)) {
            request.getTransactionData().put(ParameterName.PAYOUT_AMMOUNT, FixUtil.fixAmmount(request.getTransactionData().get(ParameterName.PAYOUT_AMMOUNT).toString()));
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[TecnicardHostManager] Parsing  payOut_amount value :: " + request.getTransactionData().get(ParameterName.PAYOUT_AMMOUNT), null);
        }
        if (request.getTransactionData().containsKey(ParameterName.FEE_AMMOUNT)) {
            request.getTransactionData().put(ParameterName.FEE_AMMOUNT, FixUtil.fixAmmount(request.getTransactionData().get(ParameterName.FEE_AMMOUNT).toString()));
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[TecnicardHostManager] Parsing  fee_amount value :: " + request.getTransactionData().get(ParameterName.FEE_AMMOUNT), null);
        }
       
        
        TransactionType originalTransactionType = (TransactionType) request.getTransactionData().get(TransactionType.TRANSACTION_TYPE);

        Map collectorMap = new HashMap();

        //*************  PROVISIONAL
        // request.getTransactionData().put( ParameterName.REQUEST_ID, 1 );  //PROVISIONAL
        //************** THIS FIELD SHOULD BE AN AUTHOINCREMENTAL IN THE DB
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[TecnicardHostManager] Processing orig: " + originalTransactionType, null);

        TransactionType transactionType = request.getTransactionType();

        String resultCode = "";

        switch (transactionType) {
            case TECNICARD_LAST_TRANSACTIONS:
                try {
                    response = (DirexTransactionResponse) bizLogic.handle(request);
                    Map responseData = response.getTransactionData();

                    responseData = buildMobileTransactionList(request.getTransactionData(), responseData);
                    response.setTransactionData(responseData);
                    return response;
                } catch (Exception e) {
                    e.printStackTrace();
                    return DirexTransactionResponse.forException(ResultCode.TECNICARD_HOST_ERROR, e);
                }

            case GENERIC_HOST_VALIDATION:

                CustomeLogger.Output(CustomeLogger.OutputStates.Info, "--[TecnicardHostManager] Processing: TECNICARD_CARD_VALIDATION", null);
                request.setTransactionType(TransactionType.TECNICARD_CARD_VALIDATION);

                try {
                    response = (DirexTransactionResponse) bizLogic.handle(request);

                } catch (Exception e) {
                    e.printStackTrace();
                    return manageExceptionAnswer(TransactionType.TECNICARD_CARD_VALIDATION, e);
                }

                Map sessionTagMap = (Map) response.getTransactionData().get(ParameterName.SESSION_TAG_MAP);

                resultCode = (String) sessionTagMap.get(ParameterName.RESULT_CODE);

                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[TecnicardHostManager] TECNICARD_CARD_VALIDATION resultCode = " + resultCode, null);

                if (TRANSACTION_SEQUENCE.containsKey(resultCode)) {

//                    if ( originalTransactionType == TransactionType.CARD_RELOAD && ( !resultCode.equals( CODE_100012 ) ) ) {
//                        String message = "Tecnicard Exception: Card Validation result code: " + resultCode + " unacceptable for RELOAD";
//                        return manageUnexpectedAnswer( CODE_100012, message, TransactionType.TECNICARD_CARD_VALIDATION );
//                    } else {
                    addSubTransaction(TransactionType.TECNICARD_CARD_VALIDATION, ResultCode.SUCCESS, ResultMessage.SUCCESS.getMessage(), resultCode);
//                    }

                    TransactionType[] transactionSequence = (TransactionType[]) TRANSACTION_SEQUENCE.get(resultCode);

                    for (TransactionType transactionTypeOfSecuence : transactionSequence) {
                        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[TecnicardHostManager] Processing: " + transactionTypeOfSecuence, null);
                        request.setTransactionType(transactionTypeOfSecuence);

                        try {
                            response = (DirexTransactionResponse) bizLogic.handle(request);
                        } catch (Exception e) {
                            e.printStackTrace();
                            return manageExceptionAnswer(transactionTypeOfSecuence, e);
                        }

                        sessionTagMap = (Map) response.getTransactionData().get(ParameterName.SESSION_TAG_MAP);

                        if ((Boolean) sessionTagMap.get(ParameterName.SESSION_TAG_IS_EXPECTED_ANSWER)) {
                            collectorMap.putAll(response.getTransactionData());
                            addSubTransaction(transactionTypeOfSecuence, ResultCode.SUCCESS, ResultMessage.SUCCESS.getMessage(), (String) sessionTagMap.get(ParameterName.RESULT_CODE));
                        } else {
                            return manageUnexpectedAnswer(sessionTagMap, transactionTypeOfSecuence);
                        }
                    }

                    response.setTransactionData(collectorMap);
                    response.getTransaction().addSubTransactionList(transaction.getSub_Transaction());

                    response.setResultCode(ResultCode.SUCCESS);
                    response.setResultMessage(ResultMessage.SUCCESS.getMessage());
                    return response;
                } else {
                    return manageUnexpectedAnswer(sessionTagMap, TransactionType.TECNICARD_CARD_VALIDATION);
                }
            case ISTREAM_CHECK_AUTH_LOCATION_CONFIG:

                CustomeLogger.Output(CustomeLogger.OutputStates.Info, "--[TecnicardHostManager] Processing: ISTREAM_CHECK_AUTH_LOCATION_CONFIG", null);
                request.setTransactionType(TransactionType.TECNICARD_CARD_VALIDATION);

                try {
                    response = (DirexTransactionResponse) bizLogic.handle(request);
                    // int a = 9/0;
                } catch (Exception e) {
                    return manageExceptionAnswer(TransactionType.TECNICARD_CARD_VALIDATION, e);
                }

                Map sessionTagMapp = (Map) response.getTransactionData().get(ParameterName.SESSION_TAG_MAP);

                resultCode = (String) sessionTagMapp.get(ParameterName.RESULT_CODE);

                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[TecnicardHostManager] ISTREAM_CHECK_AUTH_LOCATION_CONFIG resultCode = " + resultCode, null);

                if (TRANSACTION_SEQUENCE.containsKey(resultCode)) {

                    addSubTransaction(TransactionType.TECNICARD_CARD_VALIDATION, ResultCode.SUCCESS, ResultMessage.SUCCESS.getMessage(), resultCode);

                    response.setTransactionData(sessionTagMapp);

                    if (CODE_100011.equals(resultCode)
                            || CODE_100012.equals(resultCode)
                            || CODE_100015.equals(resultCode)) {
                        Double activationFeeConfig = (Double) request.getTransactionData().get(ParameterName.ACTIVATION_FEE_CONFIG);

                        response.getTransactionData().put(ParameterName.ACTIVATION_FEE, activationFeeConfig);
                        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[TecnicardHostManager] ACTIVATION_FEE = " + activationFeeConfig, null);
                    }

                    response.getTransaction().addSubTransactionList(transaction.getSub_Transaction());

                    response.setResultCode(ResultCode.SUCCESS);
                    response.setResultMessage(ResultMessage.SUCCESS.getMessage());
                    return response;
                } else {
                    return manageUnexpectedAnswer(sessionTagMapp, TransactionType.TECNICARD_CARD_VALIDATION);
                }

            case GENERIC_HOST_CARD_LOAD:
                CustomeLogger.Output(CustomeLogger.OutputStates.Info, "--[TecnicardHostManager] Processing: GENERIC_HOST_CARD_LOAD", null);
                if (!request.getTransactionData().containsKey(ParameterName.OPERATION)) {
                    return manageExceptionAnswer(TransactionType.TECNICARD_CARD_LOAD_OR_CASH_TO_CARD, ResultMessage.TECNICARD_WRONG_ID_OPERATION.getMessage());
                }

                String idOperation = (String) request.getTransactionData().get(ParameterName.OPERATION);

                switch (idOperation) {
                    case "01": {  //check
                        transactionType = TransactionType.TECNICARD_CARD_LOAD;
                        break;
                    }
                    case "02": {  //cash
                        transactionType = TransactionType.TECNICARD_CASH_TO_CARD;
                        break;
                    }
                    default:
                        return manageExceptionAnswer(TransactionType.TECNICARD_CARD_LOAD_OR_CASH_TO_CARD, ResultMessage.TECNICARD_WRONG_ID_OPERATION.getMessage());
                }
                request.setTransactionType(transactionType);
            default:
                CustomeLogger.Output(CustomeLogger.OutputStates.Info, "--[TecnicardHostManager] Processing: default", null);
                try {
                    response = (DirexTransactionResponse) bizLogic.handle(request);

                    Map tagMap = ((Map) response.getTransactionData().get(ParameterName.SESSION_TAG_MAP));

                    String tecnicardResultCode = (String) tagMap.get(ParameterName.RESULT_CODE);

                    System.out.println("TecnicardHostManager -> tecnicardResultCode = " + tecnicardResultCode);

                    boolean success = (Boolean) tagMap.get(ParameterName.SUCESSFULL_PROCESSING);

                    CustomeLogger.Output(CustomeLogger.OutputStates.Info, "--[TecnicardHostManager] success = " + success, null);

                    if (success) {
                        request.setTransactionType(TransactionType.TECNICARD_BALANCE_INQUIRY);
                        DirexTransactionResponse inquiryResponse = (DirexTransactionResponse) bizLogic.handle(request);

                        if (inquiryResponse.getTransactionData() != null) {
                            response.getTransactionData().putAll(inquiryResponse.getTransactionData());
                        }

                        response.setResultCode(ResultCode.SUCCESS);
                        response.setResultMessage(ResultMessage.SUCCESS.getMessage());
                    } else {
                        response = DirexTransactionResponse.forException(ResultCode.TECNICARD_HOST_RETURN_PROCESSING_FALSE, ResultMessage.TECNICARD_FAILED, "Tecnicard processing failed. Description: " + (String) tagMap.get(ParameterName.RESULT_MESSAGE), (String) tagMap.get(ParameterName.RESULT_CODE));
                    }

                    addSubTransaction(transactionType, response.getResultCode(), response.getResultMessage(), (String) tagMap.get(ParameterName.RESULT_CODE));
                    transaction.setResultCode(response.getResultCode().getCode());
                    transaction.setResultMessage(response.getResultMessage());
                    response.setTransaction(transaction);

                    return response;
                } catch (Exception e) {
                    e.printStackTrace();
                    return DirexTransactionResponse.forException(ResultCode.TECNICARD_HOST_ERROR, e);
                }
        }

    }

    private DirexTransactionResponse manageUnexpectedAnswer(Map sessionTagMap, TransactionType transactionType) {
        String resultCode = (String) sessionTagMap.get(ParameterName.RESULT_CODE);
        String resultMessage = (String) sessionTagMap.get(ParameterName.RESULT_MESSAGE);

        System.out.println("TecnicardHostManager -> manageUnexpectedAnswer:: resultMessage = " + resultMessage);
        return manageUnexpectedAnswer(resultCode, resultMessage, transactionType);
    }

    private DirexTransactionResponse manageUnexpectedAnswer(String resultCode, String resultMessage, TransactionType transactionType) {

        String message = "Tecnicard [" + transactionType.toString() + "] received unexpected answer. Code: " + resultCode + ". Description: " + resultMessage + ".";

        addSubTransaction(transactionType, ResultCode.TECNICARD_HOST_UNEXPECTED_RESULT_CODE, message, resultCode);

        DirexTransactionResponse exRsp;
        switch (resultCode) {
            case "1360320":
                exRsp = DirexTransactionResponse.forException(ResultCode.TECNICARD_HOST_UNEXPECTED_RESULT_CODE, ResultMessage.TECNICARD_PERSONALIZATION_FAILED, message, resultCode);
                break;
            case "136053":
                exRsp = DirexTransactionResponse.forException(ResultCode.TECNICARD_HOST_UNEXPECTED_RESULT_CODE, ResultMessage.TECNICARD_PERSONALIZATION_INFO_FAILED, message, resultCode);
                break;
            default:
                exRsp = DirexTransactionResponse.forException(ResultCode.TECNICARD_HOST_UNEXPECTED_RESULT_CODE, resultMessage);
        }

        exRsp.getTransaction().addSubTransactionList(transaction.getSub_Transaction());
        return exRsp;
    }

    private DirexTransactionResponse manageExceptionAnswer(TransactionType transactionType, Exception e) {
        return manageExceptionAnswer(transactionType, e.getMessage());
    }

    private DirexTransactionResponse manageExceptionAnswer(TransactionType transactionType, String exceptionMessage) {

        String message = ResultMessage.TECNICARD_FAILED.getMessage() + ". Error description: " + exceptionMessage;

        addSubTransaction(transactionType, ResultCode.TECNICARD_HOST_ERROR, message, "");
        DirexTransactionResponse exRsp = DirexTransactionResponse.forException(ResultCode.TECNICARD_HOST_ERROR, ResultMessage.TECNICARD_FAILED, message, "");

        exRsp.getTransaction().addSubTransactionList(transaction.getSub_Transaction());
        return exRsp;
    }

    private void addSubTransaction(TransactionType transactionType, ResultCode resultCode, String resultMessage, String errorCode) {
        SubTransaction subTransaction = new SubTransaction();
        subTransaction.setType(transactionType.getCode());
        subTransaction.setResultCode(resultCode.getCode());
        subTransaction.setResultMessage(resultMessage);
        subTransaction.setErrorCode(errorCode);
        subTransaction.setHost(NomHost.TECNICARD.getId());
        transaction.addSubTransaction(subTransaction);
    }

    private Map buildMobileTransactionList(Map requestMap, Map responseMap) {
        Map transactionHistory = new HashMap();
        List<MobileTransaction> result = new ArrayList<>();

        Integer start = (Integer) requestMap.get(ParameterName.START);//this you receive as param ( the value 0 is just to put something there now)
        Integer max = (Integer) requestMap.get(ParameterName.MAX);
        int successfulCount = 0;

        List<com.smartbt.vtsuite.boundary.client.Transaction> originalList = (List<com.smartbt.vtsuite.boundary.client.Transaction>) responseMap.get(ParameterName.TRANSACTIONS_LIST);


        if (originalList != null) {
                    System.out.println("TecnicardHostManager.buildMobileTransactionList start = " + start + ", max = " + max);
                    System.out.println("TecnicardHostManager.buildMobileTransactionList originalList.size() = " + originalList.size());

            for (int i = 0; i < originalList.size(); i++) {
                originalList.get(i).print();

                if (originalList.get(i).wasSuccess()) {
                    if (successfulCount >= start && result.size() < max) {
                       MobileTransaction mt =originalList.get(i).toMobileTransaction();
                       
                       result.add(mt);
                       
                       if(mt.getType() != null && mt.getType().equalsIgnoreCase("CASH TO CARD")){ 
                           MobileTransaction feeTransaction = new MobileTransaction("CASH LOAD FEE", mt.getDate(), mt.getFee(), "0.00", "D","G", "Cash Load Fee");
                           result.add(feeTransaction);
                           successfulCount++;
                       }
                    }
                    successfulCount++;
                }
            }
        }else{
            System.out.println("originalList is null");
        }

        transactionHistory.put("items", result);
        transactionHistory.put("total", successfulCount);

        return transactionHistory;
    }
 
}
