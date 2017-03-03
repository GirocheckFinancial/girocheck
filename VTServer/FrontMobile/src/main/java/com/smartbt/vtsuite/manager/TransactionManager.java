/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.vtsuite.manager;

import com.smartbt.girocheck.servercommon.display.message.ResponseData;
import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
import com.smartbt.girocheck.servercommon.model.MobileClient;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import com.smartbt.vtsuite.dao.MobileClientDao;
import com.smartbt.vtsuite.mock.MockFrontMobileBusinessLogic;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author suresh
 */
public class TransactionManager {

    private MobileClientDao mobileClientDao = MobileClientDao.get();
    protected static TransactionManager _this;

    public static TransactionManager get() {
        if (_this == null) {
            _this = new TransactionManager();
        }
        return _this;
    }

    public Map transactionHistory(Integer clientId, Integer page, Integer start, Integer limit, String startDateStr, String endDateStr) {
        DirexTransactionResponse technicardResponse;
        Map transactionHistory = new HashMap();

        if ((clientId == 1 || clientId == 0)) {
            technicardResponse = MockFrontMobileBusinessLogic.get().process();
            return (Map) technicardResponse.getTransactionData().get(ParameterName.TRANSACTIONS_LIST);

        }
        String transactionId = String.valueOf(clientId);
        DirexTransactionRequest direxTransactionRequest = new DirexTransactionRequest();
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[TransactionManager] processTransaction " + transactionId, null);

        try {
            Map map = new HashMap();
            MobileClient mobileClient = mobileClientDao.getMobileClientId(clientId);
            if (mobileClient != null) {
                System.out.println("[TransactionManager:transactionHistory] - CARD_MASK_NUMBER:- " + mobileClient.getCard().getMaskCardNumber() + " CARD_NUMBER:- " + mobileClient.getCard().getCardNumber());

                map.put(TransactionType.TRANSACTION_TYPE, TransactionType.TECNICARD_LAST_TRANSACTIONS);
                map.put(ParameterName.CARD_NUMBER, mobileClient.getCard().getCardNumber());
                map.put(ParameterName.START_DATE, startDateStr);
                map.put(ParameterName.END_DATE, endDateStr);
                map.put(ParameterName.STATUS_CODE, "G");               

                //DONE 
                //We need to ask all transactions in this range of dates
                //to Technicard and make the pagination manually in our side
                //This is because:
                //1- Tecnicard doesn't support pagination
                //2- We need to return how many transactions are in that range of dates.
                //(Need to find out what to pass as 'limit' in order to 
                // get all transactions in the range of dates..  (may be -1 ??)    

                map.put(ParameterName.START, start);
                map.put(ParameterName.MAX, limit);

                direxTransactionRequest.setTransactionData(map);
                direxTransactionRequest.setCorrelation(mobileClient.getCard().getCardNumber());
                direxTransactionRequest.setTransactionType(TransactionType.TECNICARD_LAST_TRANSACTIONS);
                System.out.println("[TransactionManager:transactionHistory] - CLIENT_ID:- " + clientId);

                technicardResponse = FrontMobileBusinessLogic.get().process(direxTransactionRequest);

                if (technicardResponse != null && technicardResponse.wasApproved()) {
                    CustomeLogger.Output(CustomeLogger.OutputStates.Info, "technicardResponse.wasApproved()" + technicardResponse.wasApproved(), null);

                    if (technicardResponse.getTransactionData() != null) {
                        transactionHistory = (Map) technicardResponse.getTransactionData();                        
                    }
                    CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[TransactionManager:transactionHistory] processTransaction finished" + transactionId, null);
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return transactionHistory;
    }

    public Map balanceInquiry(Integer clientId) {

        DirexTransactionResponse technicardResponse;
        ResponseData response = new ResponseData();
        Map balanceInquiry = new HashMap();

        if ((clientId == 1 || clientId == 0)) {
            technicardResponse = MockFrontMobileBusinessLogic.get().processBalanceEnquiry();
            balanceInquiry.put(ParameterName.BALANCE, technicardResponse.getTransactionData().get(ParameterName.BALANCE));
            return balanceInquiry;
        }
        String transactionId = String.valueOf(clientId);
        DirexTransactionRequest direxTransactionRequest = new DirexTransactionRequest();
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[TransactionManager:balanceEnquiry] processTransaction " + transactionId, null);

        try {
            Map map = new HashMap();
            MobileClient mobileClient = mobileClientDao.getMobileClientId(clientId);
            if (mobileClient != null) {
                System.out.println("[TransactionManager:balanceEnquiry] - CARD_MASK_NUMBER:- " + mobileClient.getCard().getMaskCardNumber() + " CARD_NUMBER:- " + mobileClient.getCard().getCardNumber());
                map.put(TransactionType.TRANSACTION_TYPE, TransactionType.TECNICARD_BALANCE_INQUIRY);
                map.put(ParameterName.CARD_NUMBER, mobileClient.getCard().getCardNumber());
                map.put(ParameterName.REQUEST_ID, clientId);
                direxTransactionRequest.setTransactionData(map);
                direxTransactionRequest.setCorrelation(mobileClient.getCard().getCardNumber());
                direxTransactionRequest.setTransactionType(TransactionType.TECNICARD_BALANCE_INQUIRY);
                System.out.println("[TransactionManager:balanceEnquiry] - CLIENT_ID:- " + clientId);

                technicardResponse = FrontMobileBusinessLogic.get().process(direxTransactionRequest);

                if (technicardResponse != null && technicardResponse.wasApproved()) {
                    CustomeLogger.Output(CustomeLogger.OutputStates.Info, "technicardResponse.wasApproved()" + technicardResponse.wasApproved(), null);
                    if (technicardResponse.getTransactionData() != null) {
                        balanceInquiry.put(ParameterName.BALANCE, technicardResponse.getTransactionData().get(ParameterName.BALANCE));
                        response.setData(balanceInquiry);
                    } else {
                        balanceInquiry.put(ParameterName.BALANCE, "-1");
                    }
                }else {
                    balanceInquiry.put(ParameterName.BALANCE, "-1");
                }

            }
        } catch (Exception e) {
            balanceInquiry.put(ParameterName.BALANCE, "-1");
            e.printStackTrace();
        }
        return balanceInquiry;
    }
    
}
