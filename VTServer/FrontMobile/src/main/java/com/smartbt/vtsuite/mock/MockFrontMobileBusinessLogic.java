/*
 ** File: IStreamBusinessLogic.java
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
package com.smartbt.vtsuite.mock;

import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.utils.IMap;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.log.LogUtil;
import com.smartbt.vtsuite.boundary.util.MapUtil;
import java.util.HashMap;
import java.util.Map;
import com.smartbt.vtsuite.boundary.client.LastTransactionsResponse;
import com.smartbt.vtsuite.boundary.client.SessionTag;
import java.util.GregorianCalendar;

/**
 * Mpowa Business Logic Class
 */
public class MockFrontMobileBusinessLogic {

    private static MockFrontMobileBusinessLogic INSTANCE;

    public static synchronized MockFrontMobileBusinessLogic get() {
        if (INSTANCE == null) {
            INSTANCE = new MockFrontMobileBusinessLogic();
        }
        return INSTANCE;
    }

    public DirexTransactionResponse process(DirexTransactionRequest request) throws Exception {
         Map transactionData = request.getTransactionData();

        TransactionType transactionType = request.getTransactionType();

        IMap response = null;
        IMap responseBalance = null;

        switch (transactionType) {            
            case TECNICARD_LAST_TRANSACTIONS:
                response = wmLastTransactions(transactionData);
                break;
           
        }

        LogUtil.log("TecnicardManager", "                        Finish " + transactionType);

        DirexTransactionResponse direxTransactionResponse = new DirexTransactionResponse();

        if (response != null) {
            direxTransactionResponse.setTransactionData(response.toMap());

            if (responseBalance != null) {
                direxTransactionResponse.getTransactionData().putAll(responseBalance.toMap());
            }
        } else {
            direxTransactionResponse.setTransactionData(new HashMap());
        }

        return direxTransactionResponse;
    }
    
    public IMap wmLastTransactions(Map map) throws Exception {
        String pTransactionQuantity = MapUtil.getStringValueFromMap(map, ParameterName.TRANSACTION_QUANTITY, false);
        String pStartDate = MapUtil.getStringValueFromMap(map, ParameterName.START_DATE, false);
        String pCardNumber = MapUtil.getStringValueFromMap(map, ParameterName.CARD_NUMBER, false);
        String pEndDate = MapUtil.getStringValueFromMap(map, ParameterName.END_DATE, false);
        String pRequestID = MapUtil.getStringValueFromMap(map, ParameterName.REQUEST_ID, false);

        LastTransactionsResponse response = new LastTransactionsResponse();
        response.setCurrencySymbol("LastTrans: currencySymbol");
        response.setCurrencyName("LastTrans: currencyName");
        response.setInitialBalance("LastTrans: initialBalance");
        response.setFinalBalance("LastTrans: finalBalance");
        response.setFinalBalance("LastTrans: finalBalance");
        response.setTransactionsList(null);

        response.setSessionTag(buildSessionTag("LastTransactions", pRequestID, "0"));

        return response;
    }
    
    private SessionTag buildSessionTag(String methodName, String requestId, String resultCode) {
        SessionTag sessionTag = new SessionTag();
        sessionTag.setSucessfullProcessing(true);
        sessionTag.setRequestID(requestId);
        sessionTag.setSystemName(methodName + ": SystemName");
        sessionTag.setOperationName(methodName + ": OperationName");

        sessionTag.setTime(requestId);

        String timeZone = GregorianCalendar.getInstance().getTimeZone().toString();
        sessionTag.setGMTTimeZone(timeZone);

        sessionTag.setOperationID(methodName + ": OperationId");
        sessionTag.setResultCode(resultCode);
        sessionTag.setResultMessage(methodName + ": resultMessage");

        return sessionTag;
    }

}
