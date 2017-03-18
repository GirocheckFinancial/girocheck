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
package com.smartbt.vtsuite.manager;

import com.smartbt.girocheck.servercommon.utils.IMap;
import com.smartbt.vtsuite.boundary.client.IStreamSrvHostWS;
import com.smartbt.vtsuite.boundary.client.IStreamSrvHostWSSoap;
import com.smartbt.vtsuite.boundary.util.MapUtil;
import com.smartbt.girocheck.common.AbstractBusinessLogicModule;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import com.smartbt.vtsuite.boundary.client.ArrayOfTransaction;
import com.smartbt.vtsuite.boundary.client.LastTransactionsResponse;
import com.smartbt.vtsuite.boundary.client.Transaction;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.ws.BindingProvider;

/**
 * Mpowa Business Logic Class
 */
public class TecnicardBusinessLogic extends AbstractBusinessLogicModule {

    private IStreamSrvHostWS service;
    private IStreamSrvHostWSSoap port;

//    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger( TecnicardBusinessLogic.class );
    /**
     * Constructor
     */
    public TecnicardBusinessLogic() {
        service = new IStreamSrvHostWS();
        port = service.getIStreamSrvHostWSSoap();

        String url = "";
        try {

            url = System.getProperty("WS_TECNICARD_PRODUCTION_URL");

            if (url == null) {
                url = "https://bizsrv.tcmsystem.net/IStreamWS/iStreamSrvHost.asmx?WSDL";
            }

            BindingProvider bindingProvider = (BindingProvider) port;
            bindingProvider.getRequestContext().put(
                    BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
                    url);

        } catch (Exception ex) {
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[TecnicardBusinessLogic] Error", ex.getMessage());
        }

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[TecnicardBusinessLogic] URL value: " + url, null);

    }

    @Override
    public void preprocess(DirexTransactionRequest tr) throws Exception {
    }

    @Override
    public DirexTransactionResponse process(DirexTransactionRequest request) throws Exception {
        Map transactionData = request.getTransactionData();

        TransactionType transactionType = request.getTransactionType();
        DirexTransactionResponse direxTransactionResponse = new DirexTransactionResponse();
        IMap response = null;
        IMap responseBalance = null;

        Map map;

//         LogUtil.logAndStore( "TecnicardBL", "                        proccessing:: " + transactionType );
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[TecnicardBusinessLogic] proccessing:: " + transactionType, null);

        switch (transactionType) {
            case TECNICARD_CARD_ACTIVATION:
                response = wmCardActivation(transactionData);
                break;
            case TECNICARD_CARD_PERSONALIZATION:
                response = wmCardPersonalization(transactionData);
                break;
            case TECNICARD_CARD_LOAD:
                response = wmCardLoad(transactionData);
                break;
            case TECNICARD_BALANCE_INQUIRY:
                response = wmBalanceInquiry(transactionData);

                map = response.toMap();
                map = (Map) map.get(ParameterName.SESSION_TAG_MAP);
                if (map.containsKey(ParameterName.SUCESSFULL_PROCESSING)) {
                    boolean success = (boolean) map.get(ParameterName.SUCESSFULL_PROCESSING);
                    if (!success) {
                        String resultMessage = (String) map.get(ParameterName.RESULT_MESSAGE);
                        throw new Exception(resultMessage);
                    }
                }

                break;
            case TECNICARD_CARD_TO_BANK:
                response = wmCardToBank(transactionData);

                map = response.toMap();
                map = (Map) map.get(ParameterName.SESSION_TAG_MAP);
                if (map.containsKey(ParameterName.SUCESSFULL_PROCESSING)) {
                    boolean success = (boolean) map.get(ParameterName.SUCESSFULL_PROCESSING);
                    if (!success) {
                        String resultMessage = (String) map.get(ParameterName.RESULT_MESSAGE);
                        throw new Exception(resultMessage);
                    }
                }
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
                map = response.toMap();
                direxTransactionResponse.setTransactionData(map);
                return direxTransactionResponse;
            case TECNICARD_CASH_TO_CARD:
                response = wmCashToCard(transactionData);
                break;
        }

//        LogUtil.log( "TecnicardManager", "                        Finish " + transactionType );
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[TecnicardBusinessLogic] Finish " + transactionType, null);

        if (response != null) {
            map = response.toMap();
            if (!map.containsKey(ParameterName.SESSION_TAG_MAP)) {
                throw new Exception("Tecnicard Host Unexpected answer (Answer should contain the SESSION_TAG)");
            } else {
                if (!((Map) map.get(ParameterName.SESSION_TAG_MAP)).containsKey(ParameterName.RESULT_CODE)) {
                    throw new Exception("Tecnicard Host Unexpected answer (Answer should contain the RESULT_CODE)");
                }
            }
            direxTransactionResponse.setTransactionData(map);
        } else {
            direxTransactionResponse.setTransactionData(new HashMap());
        }

        if (responseBalance != null) {
            direxTransactionResponse.getTransactionData().putAll(responseBalance.toMap());
        }

        return direxTransactionResponse;
    }

    /**
     * Performs postprocess operations
     *
     * transactionRequest The transaction request transactionResponse The
     * transaction response
     *
     * @throws Exception
     */
    @Override
    public void postprocess(DirexTransactionRequest transactionRequest, DirexTransactionResponse transactionResponse) throws Exception {
    }

    private IMap wmCardActivation(Map map) throws Exception {

        String pCardNumber = MapUtil.getStringValueFromMap(map, ParameterName.CARD_NUMBER, true);
        String pTerminalCode = MapUtil.getStringValueFromMap(map, ParameterName.TERMINAL_ID_TECNICARD, true);
        String pRequestID = MapUtil.getStringValueFromMap(map, ParameterName.REQUEST_ID, true);

//        LogUtil.logAndStore(" port.wmCardActivation(" + pRequestID+ ", "+ pTerminalCode+ ", " + pCardNumber );
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[TecnicardBusinessLogic] port.wmCardActivation(" + pRequestID + ", " + pTerminalCode + ",  ************" + pCardNumber.substring(pCardNumber.length() - 4), null);
        return port.wmCardActivation(pRequestID, pTerminalCode, pCardNumber);
    }

    private IMap wmCardPersonalization(Map map) throws Exception {

        Integer idType = 1;

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

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[TecnicardBusinessLogic] -> port.wmCardPersonalization( pRequestID "
                + pRequestID + ",  pCard ************" + pCard.substring(pCard.length() - 4) + ",  pId " + pId + ",  pIdType " + idType + ",  pIdExpiration,  pIdCountry " + pIdCountry + ",  pIdState " + pIdState + ",  pPersonTitle " + pPersonTitle + ",  pFirstName " + pFirstName + ", pMiddleName " + pMiddleName + ", pLastName " + pLastName + ", pMaidenName " + pMaidenName + ", pDateOfBirth " + pDateOfBirth + ", pCountry " + pCountry + ", pState "
                + pState + ", pCity " + pCity + ", pAddress  " + pAddress + ", pZipCode " + pZipCode + ", pEmail " + pEmail + ", pTelephoneAreaCode " + pTelephoneAreaCode + ", pTelephone " + pTelephone + ", pCellphoneAreaCode " + pCellphoneAreaCode + ", pCellphone " + pCellphone + ", pWorkphoneAreaCode " + pWorkphoneAreaCode + ", pWorkphone " + pWorkphone + ", pFaxAreaCode " + pFaxAreaCode + ", pFaxphone " + pFaxphone + ", pRBService " + pRBService + ", pCurrentAddress " + pCurrentAddress + ")", null);

        return port.wmCardPersonalization(
                pRequestID, pCard, pId, "" + idType, "", pIdCountry, pIdState, pPersonTitle, pFirstName, pMiddleName, pLastName, pMaidenName, pDateOfBirth, pCountry,
                pState, pCity, pAddress, pZipCode, pEmail, pTelephoneAreaCode, pTelephone, pCellphoneAreaCode, pCellphone, pWorkphoneAreaCode, pWorkphone, pFaxAreaCode, pFaxphone, pRBService, pCurrentAddress);
    }

    public IMap wmCardLoad(Map map) throws Exception {
        String pTransAmount = MapUtil.getStringValueFromMap(map, ParameterName.AMMOUNT, false);
        String pCheckFee = MapUtil.getStringValueFromMap(map, ParameterName.FEE_AMMOUNT, false);
        String pCardNumber = MapUtil.getStringValueFromMap(map, ParameterName.CARD_NUMBER, false);
        String pTerminalCode = MapUtil.getStringValueFromMap(map, ParameterName.TERMINAL_ID_TECNICARD, false);
        String pRequestID = MapUtil.getStringValueFromMap(map, ParameterName.REQUEST_ID, false);
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[TecnicardBusinessLogic] port.wmCardLoad(" + pRequestID + "," + pTerminalCode + ", ************" + pCardNumber.substring(pCardNumber.length() - 4) + ", " + pTransAmount + ", " + pCheckFee, null);
        return port.wmCardLoad(pRequestID, pTerminalCode, pCardNumber, pTransAmount, pCheckFee);
    }

    public IMap wmBalanceInquiry(Map map) throws Exception {
        String pCardNumber = MapUtil.getStringValueFromMap(map, ParameterName.CARD_NUMBER, false);
        String pRequestID = MapUtil.getStringValueFromMap(map, ParameterName.REQUEST_ID, false);
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[TecnicardBusinessLogic] port.wmBalanceInquiry(" + pRequestID + "  ************" + pCardNumber.substring(pCardNumber.length() - 4) + ")", null);
        return port.wmBalanceInquiry(pRequestID, pCardNumber);
    }
//modificar campos

    public IMap wmCardToBank(Map map) throws Exception {
        String pAmount = MapUtil.getStringValueFromMap(map, ParameterName.AMMOUNT, false);
        String pRoutingBankNumber = MapUtil.getStringValueFromMap(map, ParameterName.ROUTING_BANK_NUMBER, false);
        String pCardNumber = MapUtil.getStringValueFromMap(map, ParameterName.CARD_NUMBER, false);
        String pAccountNumber = MapUtil.getStringValueFromMap(map, ParameterName.ACCOUNT_NUMBER, false);
        String pRequestID = MapUtil.getStringValueFromMap(map, ParameterName.REQUEST_ID, false);
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[TecnicardBusinessLogic] port.wmCardToBank( " + pRequestID + ",   ************" + pCardNumber.substring(pCardNumber.length() - 4) + ",  " + pAccountNumber + ",  " + pRoutingBankNumber + ", " + pAmount, null);
        return port.wmCardToBank(pRequestID, pCardNumber, pAccountNumber, pRoutingBankNumber, pAmount);
    }

    public IMap wmCardValidation(Map map) throws Exception {
        String pAmount = MapUtil.getStringValueFromMap(map, ParameterName.PAYOUT_AMMOUNT, true);
        String pCardNumber = MapUtil.getStringValueFromMap(map, ParameterName.CARD_NUMBER, true);
        String pRequestID = MapUtil.getStringValueFromMap(map, ParameterName.REQUEST_ID, true);
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[TecnicardBusinessLogic] port.wmCardValidation( " + pRequestID + ",   ************" + pCardNumber.substring(pCardNumber.length() - 4) + ", " + pAmount, null);
        return port.wmCardValidation(pRequestID, pCardNumber, pAmount);
    }

    public IMap wmCardHolderValidation(Map map) throws Exception {

        String pId = MapUtil.getStringValueFromMap(map, ParameterName.SSN, true);
        String pCardNumber = MapUtil.getStringValueFromMap(map, ParameterName.CARD_NUMBER, false);
        String pRequestID = MapUtil.getStringValueFromMap(map, ParameterName.REQUEST_ID, false);
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[TecnicardBusinessLogic] port.wmCardHolderValidation(" + pRequestID + ",   ************" + pCardNumber.substring(pCardNumber.length() - 4) + ",  " + pId + ",  " + "1" + ")", null);
        return port.wmCardHolderValidation(pRequestID, pCardNumber, pId, "1");
    }

    public IMap wmEcho(Map map) throws Exception {
        String pRequestID = MapUtil.getStringValueFromMap(map, ParameterName.REQUEST_ID, false);
        return port.wmEcho(pRequestID);
    }

    public IMap wmLastTransactions(Map map) throws Exception {
        String pStartDate = MapUtil.getStringValueFromMap(map, ParameterName.START_DATE, false);
        String pCardNumber = MapUtil.getStringValueFromMap(map, ParameterName.CARD_NUMBER, false);
        String pEndDate = MapUtil.getStringValueFromMap(map, ParameterName.END_DATE, false);
        String pRequestID = MapUtil.getStringValueFromMap(map, ParameterName.REQUEST_ID, false);

        System.out.println("TecnicardBusinessLogin -> wmLastTransactions(" + pRequestID + ", " + pCardNumber + ", " + pStartDate + ", " + pEndDate + ", \"\")");

        LastTransactionsResponse response = port.wmLastTransactions(pRequestID, pCardNumber, pStartDate, pEndDate, "");

        System.out.println("response is " + (response == null ? "NULL" : "NOT NULL"));

        if (response != null) {
            System.out.println("response.getTransactionsList() is " + (response.getTransactionsList() == null ? "NULL" : "NOT NULL"));
        }

        if (response.getTransactionsList() != null) {
            System.out.println("response.getTransactionsList().size = " + response.getTransactionsList());

            List<Transaction> arrayOfTransaction = response.getTransactionsList().getTransaction();

            System.out.println("arrayOfTransaction = " + (arrayOfTransaction == null ? "NULL" : "NOT NULL"));

            if (arrayOfTransaction != null) {
                System.out.println("arrayOfTransaction.size() = " + arrayOfTransaction.size());

                for (Transaction arrayOfTransaction1 : arrayOfTransaction) {
                    arrayOfTransaction1.print();
                }
            }
        }

        return response;
    }

    //TODO
    //Make the format of the output map similar to what I did in MockTecnicardBusinesLogic
    //Include the logic for pagination (look at my suggestion in [FrontMobile] TransactionManager.getSubList)
    public IMap wmCashToCard(Map map) throws Exception {
        String pTransAmount = MapUtil.getStringValueFromMap(map, ParameterName.AMMOUNT, false);
        String pCardNumber = MapUtil.getStringValueFromMap(map, ParameterName.CARD_NUMBER, false);
        String pTerminalCode = MapUtil.getStringValueFromMap(map, ParameterName.TERMINAL_ID_TECNICARD, false);
        String pRequestID = MapUtil.getStringValueFromMap(map, ParameterName.REQUEST_ID, false);
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[TecnicardBusinessLogic] port.wmCashToCard(" + pRequestID + ",  " + pTerminalCode + ",   ************" + pCardNumber.substring(pCardNumber.length() - 4) + ", " + pTransAmount, null);
        return port.wmCashToCard(pRequestID, pTerminalCode, pCardNumber, pTransAmount);
    }
}
