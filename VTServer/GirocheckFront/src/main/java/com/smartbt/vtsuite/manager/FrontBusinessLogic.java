/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.vtsuite.manager;

import com.smartbt.girocheck.scan.Transaction;
import com.smartbt.girocheck.scan.Transactions;
import com.smartbt.girocheck.servercommon.display.ActivityReportTransactionDisplay;
import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.jms.JMSManager;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
import com.smartbt.girocheck.servercommon.enums.ResultCode;
import com.smartbt.girocheck.servercommon.enums.ResultMessage;
import com.smartbt.girocheck.servercommon.manager.TransactionManager;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import com.smartbt.vtsuite.util.OperationType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;

/**
 *
 * @author Roberto
 */
public class FrontBusinessLogic {

//    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(FrontBusinessLogic.class);
    private JMSManager jmsManager = JMSManager.get();
    private TransactionManager transactionManager = TransactionManager.get();

    public DirexTransactionResponse handle(DirexTransactionRequest request) throws Exception {
        DirexTransactionRequest direxTransactionRequest = preprocess(request);
        DirexTransactionResponse response = process(direxTransactionRequest);
        postprocess(request, response);
        return response;
    }

    private DirexTransactionRequest preprocess(DirexTransactionRequest direxTransactionRequest) throws Exception {

        return direxTransactionRequest;
    }

    private DirexTransactionResponse process(DirexTransactionRequest direxTransactionRequest) throws Exception {

        DirexTransactionResponse direxTransactionResponse = null;
        try {
            Map transactionData = direxTransactionRequest.getTransactionData();
            TransactionType transactionType = (TransactionType) transactionData.get(TransactionType.TRANSACTION_TYPE);
            direxTransactionRequest.setTransactionType(transactionType);
//            System.out.println("FrontBusinessLogic.process() with this transactionType: "+transactionType);
            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[FrontBusinessLogic] Processing ... " + transactionType, null);

            Queue queueIn, queueOut;

            if (!transactionData.containsKey(ParameterName.REQUEST_ID) || ((String) transactionData.get(ParameterName.REQUEST_ID)).isEmpty()) {
                return DirexTransactionResponse.forException(ResultCode.REQUEST_ID_REQUIRED, ResultMessage.REQUEST_ID_REQUIRED);
            }
            if (!transactionData.containsKey(ParameterName.TERMINAL_ID) || ((String) transactionData.get(ParameterName.TERMINAL_ID)).isEmpty()) {
                return DirexTransactionResponse.forException(ResultCode.TERMINAL_ID_REQUIRED, ResultMessage.TERMINAL_ID_REQUIRED);
            }
            if (!transactionData.containsKey(ParameterName.USER) || ((String) transactionData.get(ParameterName.USER)).isEmpty()) {
                return DirexTransactionResponse.forException(ResultCode.USER_REQUIRED, ResultMessage.USER_REQUIRED);
            }
            if (!transactionData.containsKey(ParameterName.PASSWORD) || ((String) transactionData.get(ParameterName.PASSWORD)).isEmpty()) {
                return DirexTransactionResponse.forException(ResultCode.PASSWORD_REQUIRED, ResultMessage.PASSWORD_REQUIRED);
            }

//            System.out.println( "transactionType = " + transactionType );
            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[FrontBusinessLogic]  transactionType = " + transactionType, null);
            if ((transactionType == TransactionType.NEW_CARD_LOAD || transactionType == TransactionType.CARD_RELOAD)
                    && (((String) transactionData.get(ParameterName.SSN)) == null || ((String) transactionData.get(ParameterName.SSN)).isEmpty())) {
                return DirexTransactionResponse.forException(ResultCode.SSN_REQUIRED, ResultMessage.SSN_REQUIRED);
            }
            if (transactionData.containsKey(ParameterName.AMMOUNT)) {
                String ammountString = (String) transactionData.get(ParameterName.AMMOUNT);
                ammountString = ammountString.replace("$", "");
                try {
                    double ammount = Double.parseDouble(ammountString);
                    transactionData.put(ParameterName.AMMOUNT, ammount);
                } catch (NumberFormatException e) {
                    return DirexTransactionResponse.forException(ResultCode.TERMINAL_WRONG_AMMOUNT_FORMAT, ResultMessage.TERMINAL_WRONG_AMMOUNT_FORMAT);
                }

            }

            direxTransactionRequest.setCorrelation((String) transactionData.get(ParameterName.REQUEST_ID));
            direxTransactionRequest.setRequestId((String) transactionData.get(ParameterName.REQUEST_ID));

            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[FrontBusinessLogic]  Sending RequestId :: " + ((String) transactionData.get(ParameterName.REQUEST_ID)), null);

            if (transactionType == TransactionType.TECNICARD_CONFIRMATION || transactionType == TransactionType.TECNICARD_CARD_TO_BANK_CONFIRMATION) {
                queueIn = jmsManager.getCore2InQueue();
                queueOut = jmsManager.getCore2OutQueue();

                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[FrontBusinessLogic]  Sending queueIn = jmsManager.getCore2InQueue();", null);

            } else {
                queueIn = jmsManager.getCoreInQueue();
                queueOut = jmsManager.getCoreOutQueue();

                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[FrontBusinessLogic]  Sending queueIn = jmsManager.getCoreInQueue();", null);
            }

            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[FrontBusinessLogic]  Sent to core " + direxTransactionRequest.getCorrelation(), null);

//            long wait_time = 1200000;//20 mins
            long wait_time = 1800000;//30 mins
            jmsManager.send(direxTransactionRequest, queueIn, direxTransactionRequest.getCorrelation());

            ObjectMessage tmsg = null;

            Message message = null;

            try {
                message = jmsManager.receive(queueOut, direxTransactionRequest.getCorrelation(), wait_time);
            } catch (JMSException e) {
                return DirexTransactionResponse.forException(ResultCode.RESPONSE_TIME_EXCEEDED, ResultMessage.RESPONSE_TIME_EXCEEDED);
            }

            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[FrontBusinessLogic]  Front recived message from Core", null);

            if (message != null) {
                tmsg = (ObjectMessage) message;
                Serializable s = tmsg.getObject();
                direxTransactionResponse = (DirexTransactionResponse) s;
                Set set = direxTransactionResponse.getTransactionData().keySet();

            } else {
                direxTransactionResponse = DirexTransactionResponse.forException(ResultCode.RESPONSE_TIME_EXCEEDED, ResultMessage.RESPONSE_TIME_EXCEEDED);
                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[FrontBusinessLogic]  Front recived message null from Core", null);
            }
        } catch (Exception e) {
            direxTransactionResponse = DirexTransactionResponse.forException(ResultCode.GIROCHECK_FRONT_ERROR, e);

            e.printStackTrace();
        }

//        if(direxTransactionResponse.getTransactionData().containsKey("host")){
//            if(direxTransactionResponse.getResultCode().equals(ResultCode.SUCCESS)){
//                if(direxTransactionResponse.getTransactionData().get("host").equals("FUZE")){}
//            }else{
//                direxTransactionResponse.setResultMessage(direxTransactionResponse.getTerminalResultMessage());
//            }
//        }else{
        direxTransactionResponse.setResultMessage(direxTransactionResponse.getTerminalResultMessage());
//        }            

        return direxTransactionResponse;
    }

    /**
     * Perform postprocess operations.
     *
     * @param direxTransactionRequest The transaction request object
     * @param direxTransactionResponse The transaction response object
     *
     * @throws Exception
     */
    private void postprocess(DirexTransactionRequest direxTransactionRequest, DirexTransactionResponse direxTransactionResponse) throws Exception {
//        if (direxTransactionResponse.getApprovalNumber().compareTo("XXXXXX") != 0 && !(direxTransactionRequest.getMode().equalsIgnoreCase(NomMode.MOCK.toString()))) {
//        }
    }

    public Map activityReport(Map input) {
        Map output = transactionManager.activityReport(input);
  
        output.put(ParameterName.CHECK2CARD_TRANSACTIONS, displayToTransactionList( (List<ActivityReportTransactionDisplay>) output.get(ParameterName.CHECK2CARD_TRANSACTIONS), OperationType.CHECK2CARD));
        output.put(ParameterName.CASH2CARD_TRANSACTIONS, displayToTransactionList((List<ActivityReportTransactionDisplay>) output.get(ParameterName.CASH2CARD_TRANSACTIONS), OperationType.CASH2CARD));
        output.put(ParameterName.CARD2MERCHANT_TRANSACTIONS, displayToTransactionList((List<ActivityReportTransactionDisplay>) output.get(ParameterName.CARD2MERCHANT_TRANSACTIONS), OperationType.CARD2MERCHANT));

        return output;
    }

    public Transactions displayToTransactionList(List<ActivityReportTransactionDisplay> displayList, OperationType operationType) {
        List<Transaction> transactionList = new ArrayList<>();

        if (displayList != null) {
            for (ActivityReportTransactionDisplay display : displayList) {
                transactionList.add(new Transaction(display, operationType));
            }
        }

        return new Transactions(transactionList);
    }

}
