/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.vtsuite.manager;

import com.smartbt.girocheck.common.VTSuiteMessages;
import com.smartbt.girocheck.servercommon.display.message.ResponseData;
import com.smartbt.girocheck.servercommon.display.mobile.MobileTransaction;
import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
import com.smartbt.girocheck.servercommon.model.MobileClient;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import com.smartbt.vtsuite.dao.MobileClientDao;
import com.smartbt.vtsuite.mock.MockFrontMobileBusinessLogic;
import com.smartbt.vtsuite.vtcommon.Constants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

                //TODO
                //put the limit just when page = 1
                //We need to ask all transactions in this range of dates
                //to Technicard and make the pagination manually in our side
                //This is because:
                //1- Tecnicard doesn't support pagination
                //2- We need to return how many transactions are in that range of dates.
                //(Need to find out what to pass as 'limit' in order to 
                // get all transactions in the range of dates..  (may be -1 ??)    

                //map.put(ParameterName.TRANSACTION_QUANTITY, limit);

                direxTransactionRequest.setTransactionData(map);
                direxTransactionRequest.setCorrelation(mobileClient.getCard().getCardNumber());
                direxTransactionRequest.setTransactionType(TransactionType.TECNICARD_LAST_TRANSACTIONS);
                System.out.println("[TransactionManager:transactionHistory] - CLIENT_ID:- " + clientId);

                technicardResponse = FrontMobileBusinessLogic.get().process(direxTransactionRequest);

                if (technicardResponse != null && technicardResponse.wasApproved()) {
                    CustomeLogger.Output(CustomeLogger.OutputStates.Info, "technicardResponse.wasApproved()" + technicardResponse.wasApproved(), null);

                    if (technicardResponse.getTransactionData() != null) {
                        Map transactionData = (Map) technicardResponse.getTransactionData().get(ParameterName.TRANSACTIONS_LIST);
                        if (transactionData != null) {
                            List mobileTransactions = buildTransactionList(transactionData);//Mobile application display purpose                       
                            //pagination 
                            if (page > 0) {
                                transactionHistory.put("items", getSubList(limit, page, mobileTransactions));
                                transactionHistory.put("total", mobileTransactions.size());
                            } else {
                                transactionHistory.put("items", mobileTransactions);
                                transactionHistory.put("total", mobileTransactions.size());
                            }
                        }
                    }
                    CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[TransactionManager:transactionHistory] processTransaction finished" + transactionId, null);
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return transactionHistory;
    }

    public ResponseData balanceEnquiry(Integer clientId) {

        DirexTransactionResponse technicardResponse;
        ResponseData response = new ResponseData();

        if ((clientId == 1 || clientId == 0)) {
            technicardResponse = MockFrontMobileBusinessLogic.get().processBalanceEnquiry();
            response.setStatus(Constants.CODE_SUCCESS);
            response.setStatusMessage(VTSuiteMessages.SUCCESS);
            response.setData(technicardResponse.getTransactionData());
            return response;

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
                        response.setStatus(Constants.CODE_SUCCESS);
                        response.setStatusMessage(VTSuiteMessages.SUCCESS);
                        Map balanceInquiry = new HashMap();
                        balanceInquiry.put(ParameterName.BALANCE, technicardResponse.getTransactionData().get(ParameterName.BALANCE));                       
                        response.setData(balanceInquiry);
                    } 
                }
            }
        } catch (Exception e) {
            response.setStatus(Constants.CODE_ERROR_GENERAL);
            response.setStatusMessage(VTSuiteMessages.ERROR_GENERAL);           
            e.printStackTrace();
        }
        return response;
    }

    public static List<?> getSubList(int max, int pageNumber, List<?> list) {
        if (null == list || list.isEmpty()) {
            return list;
        }
        int fromIndex = 0;
        int toIndex = max;
        if (max < list.size()) {
            fromIndex = (pageNumber * max) - max;
            toIndex = fromIndex + max;
        }
        toIndex = (toIndex > list.size()) ? list.size() : toIndex;
        return list.subList(fromIndex, toIndex);
    }

    public List buildTransactionList(Map transactionData) {
        List list = new ArrayList();
        for (Object key : transactionData.keySet()) {
            Map transaction = (Map) transactionData.get(key);
            list.add(new MobileTransaction((String) transaction.get(ParameterName.DATE), (String) transaction.get(ParameterName.AMMOUNT), (String) transaction.get(ParameterName.DESCRIPTION)));

        }
        return list;
    }
}
