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

import com.smartbt.girocheck.common.AbstractBusinessLogicModule;
import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.log.LogUtil;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
import com.smartbt.girocheck.servercommon.messageFormat.IdType;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import com.smartbt.girocheck.servercommon.utils.IMap;
import com.smartbt.vtsuite.boundary.client.BalanceInquiryResponse;
import com.smartbt.vtsuite.boundary.client.CardActivationResponse;
import com.smartbt.vtsuite.boundary.client.CardCreationResponse;
import com.smartbt.vtsuite.boundary.client.CardHolderValidationResponse;
import com.smartbt.vtsuite.boundary.client.CardLoadResponse;
import com.smartbt.vtsuite.boundary.client.CardToBankResponse;
import com.smartbt.vtsuite.boundary.client.CardValidationResponse;
import com.smartbt.vtsuite.boundary.client.CashToCardResponse;
import com.smartbt.vtsuite.boundary.client.EchoResponse;
import com.smartbt.vtsuite.boundary.client.LastTransactionsResponse;
import com.smartbt.vtsuite.boundary.client.SessionTag;
import com.smartbt.vtsuite.boundary.util.MapUtil;
import com.smartbt.vtsuite.util.TecnicardConstantValues;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Mpowa Business Logic Class
 */
public class MockTecnicardBusinessLogic extends AbstractBusinessLogicModule {

    /**
     * Constructor
     */
    public MockTecnicardBusinessLogic() {
        //  service = new IStreamSrvHostWS();
        //  port = service.getIStreamSrvHostWSSoap();
    }

    @Override
    public void preprocess(DirexTransactionRequest tr) throws Exception {

    }

    @Override
    public DirexTransactionResponse process(DirexTransactionRequest request) throws Exception {
        Map transactionData = request.getTransactionData();

        TransactionType transactionType = request.getTransactionType();

        IMap response = null;
        IMap responseBalance = null;

        switch (transactionType) {
            case TECNICARD_CARD_ACTIVATION:
                response = wmCardActivation(transactionData);
                break;
            case TECNICARD_CARD_PERSONALIZATION:
                response = wmCardPersonalization(transactionData);
                break;
            case TECNICARD_CARD_LOAD:
                System.out.println("**MockTecnicardBusinessLogic:: TECNICARD_CARD_LOAD");
                response = wmCardLoad(transactionData);
                break;
            case TECNICARD_BALANCE_INQUIRY:
                response = wmBalanceInquiry(transactionData);
                break;
            case TECNICARD_CARD_TO_BANK:
                response = wmCardToBank(transactionData);
                break;

            case TECNICARD_CARD_VALIDATION:
                response = wmCardValidation(transactionData);
                break;
            case TECNICARD_CARD_HOLDER_VALIDATION:
                response = wmCardHolderValidation(transactionData);
                break;
            case TECNICARD_ECHO:
                response = wmEcho(transactionData);
                break;
            case TECNICARD_LAST_TRANSACTIONS:
                response = wmLastTransactions(transactionData);
                break;
            case TECNICARD_CASH_TO_CARD:
                response = wmCashToCard(transactionData);
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

    /**
     * Performs postprocess operations
     *
     * transactionRequest The transaction request transactionResponse The
     * transaction response
     *
     * @param transactionRequest
     * @param transactionResponse
     * @throws Exception
     */
    @Override
    public void postprocess(DirexTransactionRequest transactionRequest, DirexTransactionResponse transactionResponse) throws Exception {
    }

    private IMap wmCardActivation(Map map) throws Exception {

        String pCardNumber = MapUtil.getStringValueFromMap(map, ParameterName.CARD_NUMBER, false);
        //String pTerminalCode = MapUtil.getStringValueFromMap(map, ParameterName.ENTITY_ID, false);
        String pTerminalCode = TecnicardConstantValues.TERMINAL_CODE;   //sacar de la BD
        String pRequestID = MapUtil.getStringValueFromMap(map, ParameterName.REQUEST_ID, false);

        CardActivationResponse response = new CardActivationResponse();

        SessionTag sessionTag = buildSessionTag("CardActivation", pRequestID, "000000");

        response.setSessionTag(sessionTag);
        return response;
    }

    private IMap wmCardPersonalization(Map map) throws Exception {

        String pId = MapUtil.getStringValueFromMap(map, ParameterName.SSN, true);

        String pState = MapUtil.getStringValueFromMap(map, ParameterName.STATE, false);
        String pCountry = MapUtil.getStringValueFromMap(map, ParameterName.COUNTRY, false);
        String pIdState = MapUtil.getStringValueFromMap(map, ParameterName.IDSTATE, false);
        String pIdCountry = MapUtil.getStringValueFromMap(map, ParameterName.IDCOUNTRY, false);

        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        Date date = MapUtil.getDateValueFromMap(map, ParameterName.BORNDATE_AS_DATE, false);
        String pDateOfBirth = (date != null) ? df.format(date) : "";

        String pAddress = MapUtil.getStringValueFromMap(map, ParameterName.ADDRESS, false);
        String pCity = MapUtil.getStringValueFromMap(map, ParameterName.CITY, false);

//        String pTelephoneAreaCode = "";// MapUtil.getStringValueFromMap( map, ParameterName.TELEPHONE_AREA_CODE, false );
        String pTelephoneAreaCode = MapUtil.getStringValueFromMap(map, ParameterName.TELEPHONE_AREA_CODE, false);
        String pCellphone = MapUtil.getStringValueFromMap(map, ParameterName.CELL_PHONE, false);
        String pZipCode = MapUtil.getStringValueFromMap(map, ParameterName.ZIPCODE, false);
        String pMiddleName = MapUtil.getStringValueFromMap(map, ParameterName.MIDDLE_NAME, false);
//        String pTelephone = "";//MapUtil.getStringValueFromMap( map, ParameterName.TELEPHONE, false );
        String pTelephone = MapUtil.getStringValueFromMap(map, ParameterName.TELEPHONE, false);
        String pFaxAreaCode = MapUtil.getStringValueFromMap(map, ParameterName.FAX_AREA_CODE, false);
        String pLastName = MapUtil.getStringValueFromMap(map, ParameterName.LAST_NAME, false);

        String pRequestID = MapUtil.getStringValueFromMap(map, ParameterName.REQUEST_ID, true);
        String pWorkphoneAreaCode = MapUtil.getStringValueFromMap(map, ParameterName.WORKPHONE_AREA_CODE, false);
        String pCellphoneAreaCode = MapUtil.getStringValueFromMap(map, ParameterName.CELL_PHONE_AREA, false);
        String pPersonTitle = MapUtil.getStringValueFromMap(map, ParameterName.PERSON_TITLE, false);
        String pEmail = MapUtil.getStringValueFromMap(map, ParameterName.EMAIL, false);

        String pCurrentAddress = MapUtil.getStringValueFromMap(map, ParameterName.ADDRESS_CORRECT, false);
        String pRBService = "0";  // OJO
        String pFirstName = MapUtil.getStringValueFromMap(map, ParameterName.FIRST_NAME, false);

        String pMaidenName = MapUtil.getStringValueFromMap(map, ParameterName.MAIDEN_NAME, false);
        String pWorkphone = MapUtil.getStringValueFromMap(map, ParameterName.WORK_PHONE, false);

        String pFaxphone = MapUtil.getStringValueFromMap(map, ParameterName.FAX_PHONE, false);
        String pCard = MapUtil.getStringValueFromMap(map, ParameterName.CARD_NUMBER, false);

        Date datee = MapUtil.getDateValueFromMap(map, ParameterName.EXPIRATION_DATE_AS_DATE, false);

        String pIdExpiration = (datee != null) ? df.format(datee) : "";

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[TecnicardBusinessLogic] -> port.wmCardPersonalization( pRequestID "
                + pRequestID + ",  pCard ************" + pCard.substring(pCard.length() - 4) + ",  pId " + pId + ",  pIdType = 1,  pIdExpiration,  pIdCountry " + pIdCountry + ",  pIdState " + pIdState + ",  pPersonTitle " + pPersonTitle + ",  pFirstName " + pFirstName + ", pMiddleName " + pMiddleName + ", pLastName " + pLastName + ", pMaidenName " + pMaidenName + ", pDateOfBirth " + pDateOfBirth + ", pCountry " + pCountry + ", pState "
                + pState + ", pCity " + pCity + ", pAddress  " + pAddress + ", pZipCode " + pZipCode + ", pEmail " + pEmail + ", pTelephoneAreaCode " + pTelephoneAreaCode + ", pTelephone " + pTelephone + ", pCellphoneAreaCode " + pCellphoneAreaCode + ", pCellphone " + pCellphone + ", pWorkphoneAreaCode " + pWorkphoneAreaCode + ", pWorkphone " + pWorkphone + ", pFaxAreaCode " + pFaxAreaCode + ", pFaxphone " + pFaxphone + ", pRBService " + pRBService + ", pCurrentAddress " + pCurrentAddress + ")", null);

        CardCreationResponse cardCreationResponse = new CardCreationResponse();
        cardCreationResponse.setCardNumber("CPersonalization: CardNumber");
        cardCreationResponse.setActualCardNumber("CPersonalization: ActualCardNumber");
        cardCreationResponse.setEmbossedName("CPersonalization: EmbossedName");
        cardCreationResponse.setExpirationDate("CPersonalization: ExpirationDate");
        cardCreationResponse.setVerificationCode("CPersonalization: VerificationCode");

        SessionTag sessionTag = buildSessionTag("CPersonalization", pRequestID, "000000");

        cardCreationResponse.setSessionTag(sessionTag);

        return cardCreationResponse;

    }

    public IMap wmCardLoad(Map map) throws Exception {
        String pTransAmount = MapUtil.getStringValueFromMap(map, ParameterName.PAYOUT_AMMOUNT, false);
        String pCheckFee = MapUtil.getStringValueFromMap(map, ParameterName.FEE_AMMOUNT, false);
        String pCardNumber = MapUtil.getStringValueFromMap(map, ParameterName.CARD_NUMBER, false);
        String pTerminalCode = MapUtil.getStringValueFromMap(map, ParameterName.TERMINAL_ID_TECNICARD, false);
        String pRequestID = MapUtil.getStringValueFromMap(map, ParameterName.REQUEST_ID, false);

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[TecnicardBusinessLogic] port.wmCardLoad(" + pRequestID + "," + pTerminalCode + ", ************" + pCardNumber.substring(pCardNumber.length() - 4) + ", " + pTransAmount + ", " + pCheckFee, null);

        CardLoadResponse response = new CardLoadResponse();
        response.setTransactionNumber("CardLoad: transactionNumber");
        response.setDate(getDate());
        response.setAmount(pTransAmount);

        SessionTag sessionTag = buildSessionTag("CardLoad", pRequestID, "0");

        response.setSessionTag(sessionTag);
        return response;
    }

    public IMap wmBalanceInquiry(Map map) throws Exception {
        String pCardNumber = MapUtil.getStringValueFromMap(map, ParameterName.CARD_NUMBER, false);
        String pRequestID = MapUtil.getStringValueFromMap(map, ParameterName.REQUEST_ID, false);

        BalanceInquiryResponse response = new BalanceInquiryResponse();
        response.setBalance("120.00");
        response.setInTransitFunds("80.00");

        String validationResponse = "0";
        if (map.containsKey(ParameterName.TECNICARD_VALIDATION_RESPONSE)) {
            validationResponse = MapUtil.getStringValueFromMap(map, ParameterName.TECNICARD_VALIDATION_RESPONSE, false);
        }

        response.setSessionTag(buildSessionTag("BalanceInquiry", pRequestID, validationResponse));

        return response;
    }

    public IMap wmCardToBank(Map map) throws Exception {
        String pAmount = MapUtil.getStringValueFromMap(map, ParameterName.AMMOUNT, false);
        String pRoutingBankNumber = MapUtil.getStringValueFromMap(map, ParameterName.ROUTING_BANK_NUMBER, false);
        String pCardNumber = MapUtil.getStringValueFromMap(map, ParameterName.CARD_NUMBER, false);
        String pAccountNumber = MapUtil.getStringValueFromMap(map, ParameterName.ACCOUNT_NUMBER, false);
        String pRequestID = MapUtil.getStringValueFromMap(map, ParameterName.REQUEST_ID, false);

        CardToBankResponse response = new CardToBankResponse();
        response.setTransactionNumber("Card2Bank: transNumber");
        response.setAmount("3.00");
        response.setDate(getDate());

        response.setSessionTag(buildSessionTag("Card2Bank", pRequestID, "0"));

        return response;
    }

    public IMap wmCardValidation(Map map) throws Exception {
        System.out.println("MockTecnicardBusinessLogic -> wmCardValidation ");

        String pAmount = MapUtil.getStringValueFromMap(map, ParameterName.AMMOUNT, false);
        String pCardNumber = MapUtil.getStringValueFromMap(map, ParameterName.CARD_NUMBER, false);
        String pRequestID = MapUtil.getStringValueFromMap(map, ParameterName.REQUEST_ID, false);

        String validationResponse = "100011";
//        String validationResponse = "100017"; //Negative Case

        if (map.containsKey(ParameterName.TECNICARD_VALIDATION_RESPONSE)) {
            validationResponse = MapUtil.getStringValueFromMap(map, ParameterName.TECNICARD_VALIDATION_RESPONSE, false);
        }

        System.out.println("MockTecnicardBusinessLogic -> validationResponse = " + validationResponse);
        CardValidationResponse response = new CardValidationResponse();
        SessionTag sessionTag = buildSessionTag("CActivation", pRequestID, validationResponse);
        //Negative Case
//        sessionTag.setResultMessage("Card was already replaced");
//        sessionTag.setSucessfullProcessing(false);

        response.setSessionTag(sessionTag);

        return response;
    }

    public IMap wmCardHolderValidation(Map map) throws Exception {
        String pId = MapUtil.getStringValueFromMap(map, ParameterName.ID, false);
        String pCardNumber = MapUtil.getStringValueFromMap(map, ParameterName.CARD_NUMBER, false);
        String pRequestID = MapUtil.getStringValueFromMap(map, ParameterName.REQUEST_ID, false);
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[MockTecnicardBusinessLogic] port.wmCardHolderValidation(" + pRequestID + ",   ************" + pCardNumber.substring(pCardNumber.length() - 4) + ",  " + pId + ",  " + "1" + ")", null);
        CardHolderValidationResponse response = new CardHolderValidationResponse();
        response.setSessionTag(buildSessionTag("CardHolderValidation", pRequestID, "100013"));
        return response;
    }

    public IMap wmEcho(Map map) throws Exception {
        String pRequestID = MapUtil.getStringValueFromMap(map, ParameterName.REQUEST_ID, false);

        EchoResponse response = new EchoResponse();
        response.setSessionTag(buildSessionTag("Echo", pRequestID, "0"));

        return response;
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

    public IMap wmCashToCard(Map map) throws Exception {
        String pTransAmount = MapUtil.getStringValueFromMap(map, ParameterName.PAYOUT_AMMOUNT, false);
        String pCardNumber = MapUtil.getStringValueFromMap(map, ParameterName.CARD_NUMBER, false);
        String pTerminalCode = MapUtil.getStringValueFromMap(map, ParameterName.TERMINAL_ID_TECNICARD, false);
        String pRequestID = MapUtil.getStringValueFromMap(map, ParameterName.REQUEST_ID, false);
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[TecnicardBusinessLogic] port.wmCashToCard(" + pRequestID + ",  " + pTerminalCode + ",   ************" + pCardNumber.substring(pCardNumber.length() - 4) + ", " + pTransAmount, null);

        CashToCardResponse response = new CashToCardResponse();
        response.setTransactionNumber("Cash2Card: transaction Number");
        response.setDate(getDate());
        response.setAmount("2.00");

        response.setSessionTag(buildSessionTag("Cash2Card", pRequestID, "0"));
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

    private String getDate() {
        DateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
        Date d = new Date();
        return df.format(d);

    }
}
