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
import com.smartbt.girocheck.servercommon.log.LogUtil;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.messageFormat.IdType;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.HashMap;
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

        IMap response = null;
        IMap responseBalance = null;
        Map responseParameterMap = new HashMap();

        Map map;
        Map balanceInqMap;

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
                break;
            case TECNICARD_CASH_TO_CARD:
                response = wmCashToCard(transactionData);
                break;
        }

//        LogUtil.log( "TecnicardManager", "                        Finish " + transactionType );
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[TecnicardBusinessLogic] Finish " + transactionType, null);

        DirexTransactionResponse direxTransactionResponse = new DirexTransactionResponse();

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
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[TecnicardBusinessLogic] port.wmCardActivation(" + pRequestID + ", " + pTerminalCode + ", " + pCardNumber, null);
        return port.wmCardActivation(pRequestID, pTerminalCode, pCardNumber);
    }

    private IMap wmCardPersonalization(Map map) throws Exception {

        Integer idType = 0;

        if (map.containsKey(ParameterName.IDTYPE)) {
            IdType type = (IdType) map.get(ParameterName.IDTYPE);
            idType = type.getId();
        }

        String pId = MapUtil.getStringValueFromMap(map, ParameterName.SSN, true);
//        String pId = MapUtil.getStringValueFromMap( map, ParameterName.ID, true );

        String pState = MapUtil.getStringValueFromMap(map, ParameterName.STATE, false);
        String pCountry = MapUtil.getStringValueFromMap(map, ParameterName.COUNTRY, false);
        String pIdState = pState;
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

        if (pLastName != null && pLastName.contains(" ")) {
            pLastName = pLastName.split(" ")[0];
        }

        String pRequestID = MapUtil.getStringValueFromMap(map, ParameterName.REQUEST_ID, true);
        String pWorkphoneAreaCode = MapUtil.getStringValueFromMap(map, ParameterName.WORKPHONE_AREA_CODE, false);

//        String pCellphoneAreaCode = "786"; // MapUtil.getStringValueFromMap( map, ParameterName.CELL_PHONE_AREA, false );
        String pCellphoneAreaCode = MapUtil.getStringValueFromMap(map, ParameterName.CELL_PHONE_AREA, false);
        String pPersonTitle = MapUtil.getStringValueFromMap(map, ParameterName.PERSON_TITLE, false);
        String pEmail = MapUtil.getStringValueFromMap(map, ParameterName.EMAIL, false);
//        String pCurrentAddress = "Y";//MapUtil.getStringValueFromMap( map, ParameterName.ADDRESS, false );
        String pCurrentAddress = MapUtil.getStringValueFromMap(map, ParameterName.ADDRESS_CORRECT, false);
        String pRBService = "0";  // OJO
        String pFirstName = MapUtil.getStringValueFromMap(map, ParameterName.FIRST_NAME, false);

        String pMaidenName = MapUtil.getStringValueFromMap(map, ParameterName.MAIDEN_NAME, false);
        String pWorkphone = MapUtil.getStringValueFromMap(map, ParameterName.WORK_PHONE, false);

        String pFaxphone = MapUtil.getStringValueFromMap(map, ParameterName.FAX_PHONE, false);
        String pCard = MapUtil.getStringValueFromMap(map, ParameterName.CARD_NUMBER, false);

//        Date datee = MapUtil.getDateValueFromMap( map, ParameterName.EXPIRATION_DATE, false );
//        String pIdExpiration = "";// = (date != null) ? df.format( date ) : "";
//        String pIdExpiration = (datee != null) ? df.format( datee ) : "";
//        String fields = " pRequestID, pCard, pId, pIdType, pIdExpiration, pIdCountry, pIdState, pPersonTitle, pFirstName, pMiddleName, pLastName, pMaidenName, pDateOfBirth, pCountry, pState, pCity, pAddress, pZipCode, pEmail, pTelephoneAreaCode, pTelephone, pCellphoneAreaCode, pCellphone, pWorkphoneAreaCode, pWorkphone, pFaxAreaCode, pFaxphone, pRBService, pCurrentAddress";
//        String[] names = fields.split( ",");
//        printCardPersonalization(names, pRequestID, pCard, "112223333", "1", "", "840", "", "", "John", "", "Smith", "", "19750228", "840",
//                "11", "Atlanta", "222333 PEACHTREE PLACE", "30318", "girocheck@cardmarte.com", "", "", "786", "4540209", "", "", "", "", "0", "Y"  );
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[TecnicardBusinessLogic] -> port.wmCardPersonalization( pRequestID "
                + pRequestID + ",  pCard ********" + pCard.substring(pCard.length() - 4) + ",  pId " + pId + ",  pIdType " + idType + ",  pIdExpiration,  pIdCountry " + pIdCountry + ",  pIdState " + pIdState + ",  pPersonTitle " + pPersonTitle + ",  pFirstName " + pFirstName + ", pMiddleName " + pMiddleName + ", pLastName " + pLastName + ", pMaidenName " + pMaidenName + ", pDateOfBirth " + pDateOfBirth + ", pCountry " + pCountry + ", pState "
                + pState + ", pCity " + pCity + ", pAddress  " + pAddress + ", pZipCode " + pZipCode + ", pEmail " + pEmail + ", pTelephoneAreaCode " + pTelephoneAreaCode + ", pTelephone " + pTelephone + ", pCellphoneAreaCode " + pCellphoneAreaCode + ", pCellphone " + pCellphone + ", pWorkphoneAreaCode " + pWorkphoneAreaCode + ", pWorkphone " + pWorkphone + ", pFaxAreaCode " + pFaxAreaCode + ", pFaxphone " + pFaxphone + ", pRBService " + pRBService + ", pCurrentAddress " + pCurrentAddress + ")", null);

        return port.wmCardPersonalization(
                pRequestID, pCard, pId, "" + idType, "", pIdCountry, pIdState, pPersonTitle, pFirstName, pMiddleName, pLastName, pMaidenName, pDateOfBirth, pCountry,
                pState, pCity, pAddress, pZipCode, pEmail, pTelephoneAreaCode, pTelephone, pCellphoneAreaCode, pCellphone, pWorkphoneAreaCode, pWorkphone, pFaxAreaCode, pFaxphone, pRBService, pCurrentAddress);
    }

    private void printCardPersonalization(String[] names, String... ent) {
        StringBuffer b = new StringBuffer();
        b.append('\n');
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[TecnicardBusinessLogic] ", null);
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "<wmCardPersonalization>", null);
        int i = 0;
        for (String string : ent) {
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "<" + names[i] + "> " + string + " </" + names[i] + ">", null);
            b.append("<").append(names[i]).append("> ").append(string).append(" </").append(names[i]).append(">" + '\n');
            i++;

        }
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "</wmCardPersonalization>", null);

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "*********************************************    <wmCardPersonalization>    *****************************************************", null);
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, b.toString(), null);
    }

    public IMap wmCardLoad(Map map) throws Exception {
        String pTransAmount = MapUtil.getStringValueFromMap(map, ParameterName.AMMOUNT, false);
        String pCheckFee = MapUtil.getStringValueFromMap(map, ParameterName.FEE_AMMOUNT, false);
        String pCardNumber = MapUtil.getStringValueFromMap(map, ParameterName.CARD_NUMBER, false);
        String pTerminalCode = MapUtil.getStringValueFromMap(map, ParameterName.TERMINAL_ID_TECNICARD, false);
        String pRequestID = MapUtil.getStringValueFromMap(map, ParameterName.REQUEST_ID, false);
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[TecnicardBusinessLogic] port.wmCardLoad(" + pRequestID + "," + pTerminalCode + "," + pCardNumber + ", " + pTransAmount + ", " + pCheckFee, null);
        return port.wmCardLoad(pRequestID, pTerminalCode, pCardNumber, pTransAmount, pCheckFee);
    }

    public IMap wmBalanceInquiry(Map map) throws Exception {
        String pCardNumber = MapUtil.getStringValueFromMap(map, ParameterName.CARD_NUMBER, false);
        String pRequestID = MapUtil.getStringValueFromMap(map, ParameterName.REQUEST_ID, false);
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[TecnicardBusinessLogic] port.wmBalanceInquiry(" + pRequestID + " " + pCardNumber + ")", null);
        return port.wmBalanceInquiry(pRequestID, pCardNumber);
    }
//modificar campos

    public IMap wmCardToBank(Map map) throws Exception {
        String pAmount = MapUtil.getStringValueFromMap(map, ParameterName.AMMOUNT, false);
        String pRoutingBankNumber = MapUtil.getStringValueFromMap(map, ParameterName.ROUTING_BANK_NUMBER, false);
        String pCardNumber = MapUtil.getStringValueFromMap(map, ParameterName.CARD_NUMBER, false);
        String pAccountNumber = MapUtil.getStringValueFromMap(map, ParameterName.ACCOUNT_NUMBER, false);
        String pRequestID = MapUtil.getStringValueFromMap(map, ParameterName.REQUEST_ID, false);
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[TecnicardBusinessLogic] port.wmCardToBank( " + pRequestID + ",  " + pCardNumber + ",  " + pAccountNumber + ",  " + pRoutingBankNumber + ", " + pAmount, null);
        return port.wmCardToBank(pRequestID, pCardNumber, pAccountNumber, pRoutingBankNumber, pAmount);
    }

    public IMap wmCardValidation(Map map) throws Exception {
        String pAmount = MapUtil.getStringValueFromMap(map, ParameterName.PAYOUT_AMMOUNT, true);
        String pCardNumber = MapUtil.getStringValueFromMap(map, ParameterName.CARD_NUMBER, true);
        String pRequestID = MapUtil.getStringValueFromMap(map, ParameterName.REQUEST_ID, true);
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[TecnicardBusinessLogic] port.wmCardValidation( " + pRequestID + ",  " + pCardNumber + ", " + pAmount, null);
        return port.wmCardValidation(pRequestID, pCardNumber, pAmount);
    }

    public IMap wmCardHolderValidation(Map map) throws Exception {
//        String pIdType = IdType.SSN.getId() + "";
//        String pIdType = MapUtil.getStringValueFromMap( map, ParameterName.SENSITIVEIDTYPE, true );
        IdType idType = (IdType) map.get(ParameterName.IDTYPE);

        // String pIdType = MapUtil.getStringValueFromMap( map, ParameterName.SENSITIVEIDTYPE, true );
        String pId = MapUtil.getStringValueFromMap(map, ParameterName.SSN, true);

        String pCardNumber = MapUtil.getStringValueFromMap(map, ParameterName.CARD_NUMBER, false);
        String pRequestID = MapUtil.getStringValueFromMap(map, ParameterName.REQUEST_ID, false);
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[TecnicardBusinessLogic] port.wmCardHolderValidation(" + pRequestID + ",  " + pCardNumber + ",  " + pId + ",  " + idType.getId() + ")", null);
        return port.wmCardHolderValidation(pRequestID, pCardNumber, pId, idType.getId() + "");
    }

    public IMap wmEcho(Map map) throws Exception {
        String pRequestID = MapUtil.getStringValueFromMap(map, ParameterName.REQUEST_ID, false);
        return port.wmEcho(pRequestID);
    }

    public IMap wmLastTransactions(Map map) throws Exception {
        String pTransactionQuantity = MapUtil.getStringValueFromMap(map, ParameterName.TRANSACTION_QUANTITY, false);
        String pStartDate = MapUtil.getStringValueFromMap(map, ParameterName.START_DATE, false);
        String pCardNumber = MapUtil.getStringValueFromMap(map, ParameterName.CARD_NUMBER, false);
        String pEndDate = MapUtil.getStringValueFromMap(map, ParameterName.END_DATE, false);
        String pRequestID = MapUtil.getStringValueFromMap(map, ParameterName.REQUEST_ID, false);
        return port.wmLastTransactions(pRequestID, pCardNumber, pStartDate, pEndDate, pTransactionQuantity);
    }

    public IMap wmCashToCard(Map map) throws Exception {
        String pTransAmount = MapUtil.getStringValueFromMap(map, ParameterName.AMMOUNT, false);
        String pCardNumber = MapUtil.getStringValueFromMap(map, ParameterName.CARD_NUMBER, false);
        String pTerminalCode = MapUtil.getStringValueFromMap(map, ParameterName.TERMINAL_ID_TECNICARD, false);
        String pRequestID = MapUtil.getStringValueFromMap(map, ParameterName.REQUEST_ID, false);
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[TecnicardBusinessLogic] port.wmCashToCard(" + pRequestID + ",  " + pTerminalCode + ",  " + pCardNumber + ", " + pTransAmount, null);
        return port.wmCashToCard(pRequestID, pTerminalCode, pCardNumber, pTransAmount);
    }
}
