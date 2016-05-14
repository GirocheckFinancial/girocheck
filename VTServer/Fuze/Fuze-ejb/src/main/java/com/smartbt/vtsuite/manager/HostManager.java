/*
 ** File: HostTransactionManager.java
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

import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.enums.ResultCode;
import com.smartbt.girocheck.servercommon.enums.ResultMessage;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.log.LogUtil;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
import com.smartbt.girocheck.servercommon.model.SubTransaction;
import com.smartbt.girocheck.servercommon.model.Transaction;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import com.smartbt.vtsuite.manager.processor.FuzeBillerSearchManager;
import com.smartbt.vtsuite.manager.processor.FuzeLookupTransactionManager;
import com.smartbt.vtsuite.manager.processor.FuzeProcessPaymentManager;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomHost;
import java.util.Map;

/**
 * The Host Manager class
 */
public class HostManager {

    private static HostManager manager;
    private Transaction transaction;

    public HostManager() {
    }

    public static HostManager get() {
        if (manager == null) {
            manager = new HostManager();
        }
        return manager;
    }


    public DirexTransactionResponse process(DirexTransactionRequest request) throws Exception {
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[FuzeHostManager] Processing ... "+request.getTransactionType(),null);

        DirexTransactionResponse response = new DirexTransactionResponse();

        transaction = request.getTransaction();
        Map fuzeResponse;

        TransactionType transactionType = request.getTransactionType();

        switch (transactionType) {
            case GENERIC_HOST_VALIDATION:

                CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[FuzeHostManager] Processing FUZE_BILLER_SEARCH",null);
                request.setTransactionType(TransactionType.FUZE_BILLER_SEARCH);
                try {
                    fuzeResponse = FuzeBillerSearchManager.get().process(request);
//                    fuzeResponse = FuzeBillerSearchManager.get().mock();

                    if (fuzeResponse != null) {
                        if (fuzeResponse.get(ParameterName.STATUS).equals("100")) {
                            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[FuzeHostManager] FUZE_BILLER_SEARCH Result: Status: 100",null);
                            fuzeResponse.put(ParameterName.S2P, request.getTransactionData().get(ParameterName.S2P));
                            fuzeResponse.put(ParameterName.CARD_NUMBER, request.getTransactionData().get(ParameterName.CARD_NUMBER));
                            fuzeResponse.put(ParameterName.AMMOUNT, request.getTransactionData().get(ParameterName.AMMOUNT));
                            fuzeResponse.put(ParameterName.MERCHANT_NAME, request.getTransactionData().get(ParameterName.MERCHANT_NAME));

                            addSubTransaction(TransactionType.FUZE_BILLER_SEARCH, ResultCode.SUCCESS, ResultMessage.SUCCESS.getMessage(), "");
                            response = new DirexTransactionResponse();
                            response.setTransactionType(TransactionType.FUZE_BILLER_SEARCH);
                            response.getTransaction().addSubTransactionList(transaction.getSub_Transaction());
                            response.getTransaction().setResultCode(ResultCode.SUCCESS.getCode());
                            response.getTransaction().setResultMessage(ResultMessage.SUCCESS.getMessage());
                            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[FuzeHostManager] Getting response.getTransaction().getSub_Transaction().size(): "+response.getTransaction().getSub_Transaction().size(),null);
                            response.setTransactionData(fuzeResponse);
                            response.setResultCode(ResultCode.SUCCESS);
                            response.setResultMessage((String) fuzeResponse.get(ParameterName.TIME) +" A $1 Fee May Apply.");
                            break;
                        } else {
                            response = manageExceptionAnswer(TransactionType.FUZE_BILLER_SEARCH, (String) fuzeResponse.get(ParameterName.STATUS), (String) fuzeResponse.get(ParameterName.MESSAGE), "");
                            break;
                        }
                    } else {
                        response = manageExceptionAnswer(TransactionType.FUZE_BILLER_SEARCH, "", "", "Billers list is empty");
                        break;
                    }

                } catch (Exception e) {
                    CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[FuzeHostManager] FUZE_BILLER_SEARCH Exception ",e.getMessage());
                    response = manageExceptionAnswer(TransactionType.FUZE_BILLER_SEARCH, "HostError", e.getMessage(), e.getLocalizedMessage());
                    break;
                }
            case GENERIC_HOST_CARD_LOAD:
                CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[FuzeHostManager] Processing: FUZE_PROCESS_PAYMENT ",null);
                request.setTransactionType(TransactionType.FUZE_PROCESS_PAYMENT);
                try {
                    fuzeResponse = FuzeProcessPaymentManager.get().process(request);
//                    fuzeResponse = FuzeProcessPaymentManager.get().mock();

                    if (fuzeResponse.get(ParameterName.STATUS).equals("100")) {

                        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[FuzeHostManager] FUZE_PROCESS_PAYMENT Result: Status: 100",null);
                        addSubTransaction(TransactionType.FUZE_PROCESS_PAYMENT, ResultCode.SUCCESS, ResultMessage.SUCCESS.getMessage(), "");
                        response = new DirexTransactionResponse();
                        response.setTransactionType(TransactionType.FUZE_PROCESS_PAYMENT);
                        response.getTransaction().addSubTransactionList(transaction.getSub_Transaction());
                        response.getTransaction().setResultCode(ResultCode.SUCCESS.getCode());
                        response.getTransaction().setResultMessage(ResultMessage.SUCCESS.getMessage());
                        response.setTransactionData(fuzeResponse);
                        response.setResultCode(ResultCode.SUCCESS);
                        response.setResultMessage(ResultMessage.SUCCESS.getMessage());
                        break;
                    } else {
                        response = manageExceptionAnswer(TransactionType.FUZE_PROCESS_PAYMENT, (String) fuzeResponse.get(ParameterName.STATUS), (String) fuzeResponse.get(ParameterName.MESSAGE), "");
                        break;
                    }

                } catch (Exception e) {
                    CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[FuzeHostManager] FUZE_PROCESS_PAYMENT Exception ",e.getMessage());
                    response = manageExceptionAnswer(TransactionType.FUZE_PROCESS_PAYMENT, "HostError", e.getMessage(), e.getLocalizedMessage());
                    break;
                }
            case FUZE_LOOKUP_TRANSACTION:
                CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[FuzeHostManager] Processing: FUZE_LOOKUP_TRANSACTION ",null);
                request.setTransactionType(TransactionType.FUZE_LOOKUP_TRANSACTION);
                try {
                    fuzeResponse = FuzeLookupTransactionManager.get().process(request);
//                    fuzeResponse = FuzeLookupTransactionManager.get().mock();

                    if (fuzeResponse.get(ParameterName.STATUS).equals("100")) {
                        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[FuzeHostManager] FUZE_LOOKUP_TRANSACTION Result: Status: 100",null);
                        addSubTransaction(TransactionType.FUZE_LOOKUP_TRANSACTION, ResultCode.SUCCESS, ResultMessage.SUCCESS.getMessage(), "");
                        response = new DirexTransactionResponse();
                        response.setTransactionType(TransactionType.FUZE_LOOKUP_TRANSACTION);
                        response.getTransaction().addSubTransactionList(transaction.getSub_Transaction());
                        response.getTransaction().setResultCode(ResultCode.SUCCESS.getCode());
                        response.getTransaction().setResultMessage(ResultMessage.SUCCESS.getMessage());
                        response.setTransactionData(fuzeResponse);
                        response.setResultCode(ResultCode.SUCCESS);
                        response.setResultMessage(ResultMessage.SUCCESS.getMessage());
                        break;
                    } else {
                        response = manageExceptionAnswer(TransactionType.FUZE_LOOKUP_TRANSACTION, (String) fuzeResponse.get(ParameterName.STATUS), (String) fuzeResponse.get(ParameterName.MESSAGE), "");
                        break;
                    }

                } catch (Exception e) {
                    CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[FuzeHostManager] FUZE_LOOKUP_TRANSACTION Exception",e.getMessage());
                    response = manageExceptionAnswer(TransactionType.FUZE_LOOKUP_TRANSACTION, "HostError", e.getMessage(), e.getLocalizedMessage());
                    break;
                }
        }

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[FuzeHostManager] Sending ResultCode from Fuze Host to core with resultCode: "+ response.getResultCode(),null);
        return response;
    }
    
    private DirexTransactionResponse manageExceptionAnswer( TransactionType transactionType, String hostExceptionCode, String  hostExceptionMessage, String errorCode) {

        String transactionMessage = "Error ResultMessage "+ResultMessage.FUZE_HOST_FAILED.getMessage() + ". Fuze error description: " + "Status: "+hostExceptionCode+" Message: "+ hostExceptionMessage;

        addSubTransaction( transactionType, ResultCode.FUZE_HOST_ERROR, transactionMessage, errorCode );
        DirexTransactionResponse exRsp = DirexTransactionResponse.forException( ResultCode.FUZE_HOST_ERROR, ResultMessage.FUZE_HOST_FAILED);

        exRsp.getTransaction().addSubTransactionList( transaction.getSub_Transaction() );
        exRsp.getTransaction().setResultCode(ResultCode.FUZE_HOST_ERROR.getCode());
        exRsp.getTransaction().setResultMessage(transactionMessage);
        return exRsp;
    }
    
    private void addSubTransaction( TransactionType transactionType, ResultCode resultCode, String resultMessage, String errorCode ) {
        SubTransaction subTransaction = new SubTransaction();
            subTransaction.setType( transactionType.getCode() );
            LogUtil.logAndStore("FuzeManager", "                        Processing: addSubTransaction transaction type "+ transactionType+" code "+transactionType.getCode()+" ResultCode "+resultCode.getCode()+" ResultMesage "+resultMessage);
            subTransaction.setResultCode( resultCode.getCode());
            subTransaction.setResultMessage(resultMessage);
            subTransaction.setErrorCode(errorCode);
            subTransaction.setHost( NomHost.FUZE.getId());
        transaction.addSubTransaction( subTransaction );
    }
}
