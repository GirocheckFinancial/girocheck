/*
 ** File: AuthManager.java
 **
 ** Date Created: August 2014
 **
 ** Copyright @ 2004-2014 Smart Business Technology, Inc.
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

import com.smartbt.girocheck.servercommon.dao.MobileClientDao;
import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
import com.smartbt.girocheck.servercommon.model.MobileClient;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

/**
 *
 * @author Roberto Rodriguez
 */
@Service
public class TransactionManager {

    public String balanceInquiry(String cardNumber, String token) {

        DirexTransactionRequest direxTransactionRequest = new DirexTransactionRequest();
        String balance = "-1";
        try {
            Map map = new HashMap();

            map.put(TransactionType.TRANSACTION_TYPE, TransactionType.TECNICARD_BALANCE_INQUIRY);
            map.put(ParameterName.CARD_NUMBER, cardNumber);
            //This has to be unique
            map.put(ParameterName.REQUEST_ID, token);
            direxTransactionRequest.setTransactionData(map);
            direxTransactionRequest.setCorrelation(token);
            direxTransactionRequest.setTransactionType(TransactionType.TECNICARD_BALANCE_INQUIRY);
            System.out.println("[TransactionManager:balanceEnquiry] -  card: ************" + cardNumber.substring(12));

            //DirexTransactionResponse technicardResponse = sendMessageToHost(direxTransactionRequest);
            System.out.println("Calling Tecnicard without JMS...");
            DirexTransactionResponse technicardResponse = TecnicardHostManager.get().processTransaction(direxTransactionRequest);

            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "technicardResponse.wasApproved()" + technicardResponse.wasApproved(), null);

            if (technicardResponse != null && technicardResponse.wasApproved()) {
                if (technicardResponse.getTransactionData() != null && technicardResponse.getTransactionData().containsKey(ParameterName.BALANCE)) {
                    balance = (String) technicardResponse.getTransactionData().get(ParameterName.BALANCE);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return balance;
    }

    public Map transactionHistory(Integer clientId, Integer page, Integer start, Integer limit, String startDateStr, String endDateStr, String token) {
        DirexTransactionResponse technicardResponse;
        Map transactionHistory = new HashMap();

        DirexTransactionRequest direxTransactionRequest = new DirexTransactionRequest();

        try {
            Map map = new HashMap();
            String card = MobileClientDao.get().getCardNumberByMobileClientId(clientId);
            
            System.out.println("TransactionManager.transactionHistory -> card = " + card);
            
            if (card != null) {
                System.out.println("[TransactionManager:transactionHistory] - CARD_MASK_NUMBER:- **********" + card.substring(12));

                map.put(TransactionType.TRANSACTION_TYPE, TransactionType.TECNICARD_LAST_TRANSACTIONS);
                map.put(ParameterName.CARD_NUMBER, card);
                map.put(ParameterName.START_DATE, startDateStr);
                map.put(ParameterName.END_DATE, endDateStr);
                map.put(ParameterName.STATUS_CODE, "G");

                map.put(ParameterName.START, start);
                map.put(ParameterName.MAX, limit);

                direxTransactionRequest.setTransactionData(map);

                //This has to be unique
                direxTransactionRequest.setCorrelation(token);
                direxTransactionRequest.setTransactionType(TransactionType.TECNICARD_LAST_TRANSACTIONS);

                System.out.println("Sending Transaction to Tecnicard HOST...");
                technicardResponse = TecnicardHostManager.get().processTransaction(direxTransactionRequest);

                if (technicardResponse != null) {
                    CustomeLogger.Output(CustomeLogger.OutputStates.Info, "technicardResponse.wasApproved() = " + technicardResponse.wasApproved(), null);
                    if (technicardResponse.wasApproved() && technicardResponse.getTransactionData() != null) {
                        transactionHistory = (Map) technicardResponse.getTransactionData();
                     }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return transactionHistory;
    }
 

    //KEEP this in case injecting Tecnicard.ejb not work in prod (need to test in real scenario)
//    private DirexTransactionResponse sendMessageToHost(DirexTransactionRequest request) throws JMSException, Exception {
//
//        Properties props = new Properties();
//        props.setProperty("hostName", NomHost.TECNICARD.toString());
//        JMSManager jmsManager = JMSManager.get();
//
//        jmsManager.sendWithProps(request, jmsManager.getHostInQueue(), request.getCorrelation(), props);
//
//        return receiveMessageFromHost(request.getTransactionType(), request.getCorrelation());
//
//    }
//
//    private DirexTransactionResponse receiveMessageFromHost(TransactionType transactionType, String correlationId) throws Exception {
//        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[TransactionAMSManager] Receiving message from host TECNICARD", null);
//
//        Message message = null;
//        DirexTransactionResponse response;
//        JMSManager jmsManager = JMSManager.get();
//        try {
//            message = jmsManager.receive(jmsManager.getHostOutQueue(), correlationId, 30000);
//        } catch (Exception e) {
//            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[TransactionAMSManager] receiving message on receiveMessageFromHost() throws an exeption.", null);
//            response = DirexTransactionResponse.forException(transactionType, ResultCode.RESPONSE_TIME_EXCEEDED, ResultMessage.RESPONSE_TIME_EXCEEDED, NomHost.TECNICARD.toString());
//            response.setTransactionType(transactionType);
//
//            throw new Exception();
//        }
//
//        ObjectMessage objectMessage = (ObjectMessage) message;
//        Serializable s = objectMessage.getObject();
//        response = (DirexTransactionResponse) s;
//
//        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[TransactionAMSManager] Message received", null);
//
//        return response;
//    }
}
