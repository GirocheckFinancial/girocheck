/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.vtsuite.manager;

import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.enums.ResultCode;
import com.smartbt.girocheck.servercommon.enums.ResultMessage;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.jms.JMSManager;
import com.smartbt.girocheck.servercommon.manager.FeeBucketsManager;
import com.smartbt.girocheck.servercommon.manager.TransactionManager;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
import com.smartbt.girocheck.servercommon.model.SubTransaction;
import com.smartbt.girocheck.servercommon.model.Transaction;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import com.smartbt.vtsuite.util.CoreTransactionUtil;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomHost;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;

/**
 *
 * @author Roberto Rodriguez :: <roberto.rodriguez@smartbt.com>
 */
public class CoreLocalTransactionBusinessLogic extends CoreAbstractTransactionBusinessLogic {

//    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(CoreLocalTransactionBusinessLogic.class);
    private static final long CARD_VALIDATION_WAIT_TIME = 30000;

    TransactionManager transactionManager = new TransactionManager();
    private static final long ISTREAM_HOST_WAIT_TIME = 30000;
    private JMSManager jmsManager = JMSManager.get();
    // private String finalFee;

    public CoreLocalTransactionBusinessLogic() {
        super();
    }

    @Override
    public void process(DirexTransactionRequest direxTransactionRequest, Transaction transaction) throws Exception {
        transaction.setSingle(Boolean.TRUE);

        TransactionType transactionType = (TransactionType) direxTransactionRequest.getTransactionData().get(TransactionType.TRANSACTION_TYPE);
        DirexTransactionResponse response = new DirexTransactionResponse();

        switch (transactionType) {
            case ISTREAM_CHECK_AUTH_LOCATION_CONFIG:
                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreLocalTransactionBusinessLogic] processing ISTREAM_CHECK_AUTH_LOCATION_CONFIG", null);

                boolean isNewLocationConfig = isNewLocationConfig(direxTransactionRequest);

                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreLocalTransactionBusinessLogic] isNewLocationConfig = " + isNewLocationConfig, null);

                if (isNewLocationConfig) {
                    response = checkAuthLocationConfig(direxTransactionRequest, transaction);
                } else {
                    response = oldCheckAuthLocationConfig(direxTransactionRequest, transaction);
                }

                break;

        }

        transaction.setResultCode(response.getResultCode().getCode());
        transaction.setResultMessage(response.getResultMessage());
        transaction.setTransactionFinished(true);

//        response.setResultCode( ResultCode.SUCCESS );
//        response.setResultMessage( ResultMessage.SUCCESS.getMessage() );
        JMSManager.get().send(response, JMSManager.get().getCoreOutQueue(), direxTransactionRequest.getCorrelation());

        System.out.println("[CoreLocalTransactionBusinessLogic] persistTransaction:: " + transaction.getTransactionType());
        if (transaction.getTransactionType() == TransactionType.ISTREAM_CHECK_AUTH_LOCATION_CONFIG.getCode()) {
            System.out.println("[CoreLocalTransactionBusinessLogic] ISTREAM_CHECK_AUTH_LOCATION_CONFIG:: Not persist");
            System.out.println("[CoreLocalTransactionBusinessLogic] ISTREAM_CHECK_AUTH_LOCATION_CONFIG::transaction.getResultCode() = " + transaction.getResultCode());
            if (transaction.getResultCode() == ResultCode.SUCCESS.getCode()) {
                return;
            }
        }

        HibernateUtil.beginTransaction();
        transactionManager.saveOrUpdate(transaction);
        HibernateUtil.commitTransaction();
    }

    private boolean isNewLocationConfig(DirexTransactionRequest direxTransactionRequest) {
        Map data = direxTransactionRequest.getTransactionData();

        return data.containsKey(ParameterName.AMMOUNT)
                && data.containsKey(ParameterName.CARD_NUMBER)
                && data.containsKey(ParameterName.OPERATION);
    }

    public DirexTransactionResponse oldCheckAuthLocationConfig(DirexTransactionRequest direxTransactionRequest, Transaction transaction) throws Exception {
//        coreLogger.logAndStore( "CoreLocalTransactionBusinessLogic", "checkAuthLocationConfig");
//        log.info( "[CoreLocalTransactionBusinessLogic] checkAuthLocationConfig(...)");
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CoreLocalTransactionBusinessLogic] oldCheckAuthLocationConfig(...)", null);
        // Map requestMap = direxTransactionRequest.getTransactionData();
        Map responseMap;
        DirexTransactionResponse response;
//        Map applicationParameterMap = new HashMap();

        //Go to make checkAuthLocationConfig to Istream and update fees.
        direxTransactionRequest.setTransactionType(TransactionType.ISTREAM_CHECK_AUTH_LOCATION_CONFIG);
        response = sendMessageToHost(direxTransactionRequest, NomHost.ISTREAM.toString(), ISTREAM_HOST_WAIT_TIME, transaction);
        if (!response.wasApproved()) {
            response.setTransactionType(direxTransactionRequest.getTransactionType());
            CoreTransactionUtil.subTransactionFailed(transaction, response, jmsManager.getCoreOutQueue(), direxTransactionRequest.getCorrelation());
            return response.forException(ResultCode.ISTREAM_LOGIN_FAILED, "iStream Host Failed.");
        } else {
            transaction.addSubTransactionList(response.getTransaction().getSub_Transaction());
        }

        responseMap = response.getTransactionData();

        List list = (List) responseMap.get(ParameterName.CONFIG_LIST);

        responseMap = new HashMap();

        for (Iterator iterator = list.iterator(); iterator.hasNext();) {
            Map next = (Map) iterator.next();

            String parameter = (String) next.get(ParameterName.LABEL);
//            coreLogger.logAndStore( "CoreLocalTransactionBusinessLogic","ISTREAM checkAuthLocationConfig() printing incoming label: "+parameter);
//            log.debug("[CoreLocalTransactionBusinessLogic] ISTREAM checkAuthLocationConfig() printing incoming label: "+parameter);
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreLocalTransactionBusinessLogic] oldCheckAuthLocationConfig() label: " + parameter, null);
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreLocalTransactionBusinessLogic] oldCheckAuthLocationConfig() value = " + next.get(ParameterName.VALUE), null);
            switch (parameter) {
                case "AUTHFEEM":

                    responseMap.put(ParameterName.AUTH_FEEM, next.get(ParameterName.VALUE));
                    break;
                case "AUTHFEEP":
                    responseMap.put(ParameterName.AUTH_FEEP, next.get(ParameterName.VALUE));
                    break;
                case "CRDLDF":
//                        responseMap.put(ParameterName.CRDLDF, next.get(ParameterName.VALUE));        
                    responseMap.put(ParameterName.CRDLDF, "3.95");
                    break;

            }

        }
        //por ahora ponerlo fijo
        responseMap.put(ParameterName.CRDLDF, "3.95");

//        try {
//            HibernateUtil.beginTransaction();
//            ApplicationParameterManager applicationParameterManager = new ApplicationParameterManager();
//
//             applicationParameterList = applicationParameterManager.listParameterValue();
//             
//             
//             
////             coreLogger.logAndStore( "CoreLocalTransactionBusinessLogic", "applicationParameterMap.keySet().size() :: " + applicationParameterMap.keySet().size());
//             coreLogger.logAndStore( "CoreLocalTransactionBusinessLogic", "applicationParameterList.size() :: " + applicationParameterList.size());
//        
//            HibernateUtil.commitTransaction();
//        } catch ( Exception e ) {
//            e.printStackTrace();
//            HibernateUtil.rollbackTransaction();
//        }
//        for ( Object key : applicationParameterMap.keySet() ) {
//           coreLogger.logAndStore( "CoreLocalTransactionBusinessLogic", (String) key + ":: " + (String)applicationParameterMap.get( key ));
//        }
//        if ( applicationParameterMap.containsKey( EnumApplicationParameter.AUTH_FEEM.toString() ) ) {
//            coreLogger.logAndStore( "CoreLocalTransactionBusinessLogic", "AUTH_FEEM :: " + (String) applicationParameterMap.get( EnumApplicationParameter.AUTH_FEEM.toString() ));
//            responseMap.put( ParameterName.AUTH_FEEM, (String) applicationParameterMap.get( EnumApplicationParameter.AUTH_FEEM.toString() ) );
//        }
//        if ( direxTransactionRequest.getTransactionData().containsKey( ParameterName.AUTH_FEEP ) ) {
//             coreLogger.logAndStore( "CoreLocalTransactionBusinessLogic", "AUTH_FEEP :: " + (Double) direxTransactionRequest.getTransactionData().get(ParameterName.AUTH_FEEP));
//            responseMap.put( ParameterName.AUTH_FEEP, (Double) direxTransactionRequest.getTransactionData().get(ParameterName.AUTH_FEEP));
//        }
//        if ( applicationParameterMap.containsKey( EnumApplicationParameter.CRDLDF.toString() ) ) {
//            coreLogger.logAndStore( "CoreLocalTransactionBusinessLogic", "CRDLDF :: " + (String) applicationParameterMap.get( EnumApplicationParameter.CRDLDF.toString() ));
//            responseMap.put( ParameterName.CRDLDF, (String) applicationParameterMap.get( EnumApplicationParameter.CRDLDF.toString() ) );
//        }   
        response.setResultCode(ResultCode.SUCCESS);
        response.setResultMessage(ResultMessage.SUCCESS.getMessage());

        if (!responseMap.containsKey(ParameterName.AUTH_FEEM) || responseMap.get(ParameterName.AUTH_FEEM) == null
                || !responseMap.containsKey(ParameterName.AUTH_FEEP) || responseMap.get(ParameterName.AUTH_FEEP) == null) {
            response.setResultCode(ResultCode.ISTREAM_HOST_ERROR);
            response.setResultMessage(ResultMessage.ISTREAM_FAILED.getMessage() + " Error: Missing field.");
        }

        response.setTransactionData(responseMap);

        return response;//ajustar el transaction data con los campos q se le van a enviar a la terminal.
    }

    public DirexTransactionResponse checkAuthLocationConfig(DirexTransactionRequest direxTransactionRequest, Transaction transaction) throws Exception {

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CoreLocalTransactionBusinessLogic] checkAuthLocationConfig(...)", null);

        DirexTransactionResponse response;
        DirexTransactionResponse auxResponse;

        response = calculateFees(direxTransactionRequest, transaction);

        if (!response.wasApproved()) {
            return response;
        }

        direxTransactionRequest.getTransactionData().putAll(response.getTransactionData());

        auxResponse = tCValidation(direxTransactionRequest, transaction);

        if (!auxResponse.wasApproved()) {
            return auxResponse;
        }

        if (auxResponse.getTransactionData().containsKey(ParameterName.ACTIVATION_FEE)) {
            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CoreLocalTransactionBusinessLogic] ACTIVATION_FEE = " + auxResponse.getTransactionData().get(ParameterName.ACTIVATION_FEE), null);
            response.getTransactionData().put(ParameterName.ACTIVATION_FEE, auxResponse.getTransactionData().get(ParameterName.ACTIVATION_FEE));
        }

        response.setResultCode(auxResponse.getResultCode());
        response.setResultMessage(auxResponse.getResultMessage());
        response.setTerminalResultMessage(auxResponse.getTerminalResultMessage());

        return response;//ajustar el transaction data con los campos q se le van a enviar a la terminal.
    }

    private DirexTransactionResponse sendMessageToHost(DirexTransactionRequest request, String hostName, long waitTime, Transaction transaction) throws JMSException, BreakException, Exception {
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CoreLocalTransactionBusinessLogic] Send message to host " + hostName, null);

        Properties props = new Properties();
        props.setProperty("hostName", hostName);

        jmsManager.sendWithProps(request, jmsManager.getHostInQueue(), request.getCorrelation(), props);

        return receiveMessageFromHost(request, request.getTransactionType(), hostName, waitTime, transaction);
    }

    private DirexTransactionResponse receiveMessageFromHost(DirexTransactionRequest request, TransactionType transactionType, String hostName, long waitTime, Transaction transaction) throws BreakException, Exception {
//        coreLogger.logAndStore("CoreLocalBL", "             Receiving message from host " + hostName);
//        log.info("[CoreLocalTransactionBusinessLogic] Receiving message from host " + hostName);
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CoreLocalTransactionBusinessLogic] Receiving message from host " + hostName, null);

        Message message = null;
        DirexTransactionResponse response;
        try {
            message = jmsManager.receive(jmsManager.getHostOutQueue(), request.getCorrelation(), waitTime);
        } catch (IOException | JMSException e) {

            response = DirexTransactionResponse.forException(transactionType, ResultCode.RESPONSE_TIME_EXCEEDED, ResultMessage.RESPONSE_TIME_EXCEEDED, " " + hostName);
            response.setTransactionType(transactionType);
            CoreTransactionUtil.subTransactionFailed(transaction, response, jmsManager.getCoreOutQueue(), request.getCorrelation());
            throw new BreakException();
        }

        if (message == null || !(message instanceof ObjectMessage)) {

            response = DirexTransactionResponse.forException(transactionType, ResultCode.CORE_RECEIVED_NULL_FROM_HOST, ResultMessage.CORE_RECEIVED_NULL_FROM_HOST, " " + hostName);
            response.setTransactionType(transactionType);
            CoreTransactionUtil.subTransactionFailed(transaction, response, jmsManager.getCoreOutQueue(), request.getCorrelation());
            throw new BreakException();
        }

        ObjectMessage objectMessage = (ObjectMessage) message;
        Serializable s = objectMessage.getObject();
        response = (DirexTransactionResponse) s;

//        coreLogger.logAndStore("CoreLocalBL", "           Message received!");
//        log.info("[CoreLocalTransactionBusinessLogic] Message received");
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CoreLocalTransactionBusinessLogic] Message received", null);
        return response;
    }

    private DirexTransactionResponse tCValidation(DirexTransactionRequest direxTransactionRequest, Transaction transaction) throws Exception {

        DirexTransactionResponse response = new DirexTransactionResponse();

        payOutAmountCalculator(direxTransactionRequest, transaction);

        try {
            response = sendMessageToHost(direxTransactionRequest, NomHost.TECNICARD.name(), CARD_VALIDATION_WAIT_TIME, transaction);
        } catch (Exception e) {
            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CoreLocalTransactionBL] Exception calling Tecnicard.", null);
            response.setApproved(false);

        }

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CoreLocalTransactionBL] Recived message from " + NomHost.TECNICARD.name() + " Validation", null);

        transaction.addSubTransactionList(response.getTransaction().getSub_Transaction());

        if (!response.wasApproved()) {

            response = DirexTransactionResponse.forException(direxTransactionRequest.getTransactionType(), ResultCode.CORE_ERROR, ResultMessage.TECNICARD_FAILED, "TECNICARD ERROR VALIDATING CARD.");
            response.setTransactionType(direxTransactionRequest.getTransactionType());
            CoreTransactionUtil.subTransactionFailed(transaction, response, jmsManager.getCoreOutQueue(), direxTransactionRequest.getCorrelation());

        } else {

//            String code = (String) response.getTransactionData().get(ParameterName.RESULT_CODE);
//
//            switch (code) {
//                case "000000":
//                    response.setResultCode(ResultCode.SUCCESS);
//                    response.setResultMessage(ResultMessage.TC_000000.getMessage());
//                    response.setTerminalResultMessage(ResultMessage.TC_000000.getTerminalMessage());
//                    break;
//                case "000031":
//                    response.setResultCode(ResultCode.FAILED);
//                    response.setResultMessage(ResultMessage.TC_000031.getMessage());
//                    response.setTerminalResultMessage(ResultMessage.TC_000031.getTerminalMessage());
//                    break;
//                case "000122":
//                    response.setResultCode(ResultCode.FAILED);
//                    response.setResultMessage(ResultMessage.TC_000122.getMessage());
//                    response.setTerminalResultMessage(ResultMessage.TC_000122.getTerminalMessage());
//                    break;
//                case "000199":
//                    response.setResultCode(ResultCode.FAILED);
//                    response.setResultMessage(ResultMessage.TC_000199.getMessage());
//                    response.setTerminalResultMessage(ResultMessage.TC_000199.getTerminalMessage());
//                    break;
//                case "100011":
//                    response.setResultCode(ResultCode.SUCCESS);
//                    response.setResultMessage(ResultMessage.TC_100011.getMessage());
//                    response.setTerminalResultMessage(ResultMessage.TC_100011.getTerminalMessage());
//                    break;
//                case "100012":
//                    response.setResultCode(ResultCode.SUCCESS);
//                    response.setResultMessage(ResultMessage.TC_100012.getMessage());
//                    response.setTerminalResultMessage(ResultMessage.TC_100012.getTerminalMessage());
//                    break;
//                case "100013":
//                    response.setResultCode(ResultCode.SUCCESS);
//                    response.setResultMessage(ResultMessage.TC_100013.getMessage());
//                    response.setTerminalResultMessage(ResultMessage.TC_100013.getTerminalMessage());
//                    break;
//                case "100014":
//                    response.setResultCode(ResultCode.FAILED);
//                    response.setResultMessage(ResultMessage.TC_100014.getMessage());
//                    response.setTerminalResultMessage(ResultMessage.TC_100014.getTerminalMessage());
//                    break;
//                case "100015":
//                    response.setResultCode(ResultCode.SUCCESS);
//                    response.setResultMessage(ResultMessage.TC_100015.getMessage());
//                    response.setTerminalResultMessage(ResultMessage.TC_100015.getTerminalMessage());
//                    break;
//                case "100016":
//                    response.setResultCode(ResultCode.FAILED);
//                    response.setResultMessage(ResultMessage.TC_100016.getMessage());
//                    response.setTerminalResultMessage(ResultMessage.TC_100016.getTerminalMessage());
//                    break;
//                case "100017":
//                    response.setResultCode(ResultCode.FAILED);
//                    response.setResultMessage(ResultMessage.TC_100017.getMessage());
//                    response.setTerminalResultMessage(ResultMessage.TC_100017.getTerminalMessage());
//                    break;
//                default:
//                    response = manageUnexpectedAnswer(response.getTransactionData(), TransactionType.TECNICARD_CARD_VALIDATION, transaction);
//                    break;
//            }
        }
        return response;
    }

    private DirexTransactionResponse calculateFees(DirexTransactionRequest direxTransactionRequest, Transaction transaction) throws Exception {

        DirexTransactionResponse response = new DirexTransactionResponse();

        Map responseMap = new HashMap();
        try {
            HibernateUtil.beginTransaction();

            FeeBucketsManager feeBucketsManager = new FeeBucketsManager();
            responseMap = (Map) feeBucketsManager.getFees((direxTransactionRequest.getTransactionData().get(ParameterName.IDMERCHANT)) + "",
                    (direxTransactionRequest.getTransactionData().get(ParameterName.OPERATION)) + "",
                    (direxTransactionRequest.getTransactionData().get(ParameterName.AMMOUNT)) + "");

            response.setTransactionData(responseMap);
            HibernateUtil.commitTransaction();
        } catch (Exception e) {
            e.printStackTrace();
            HibernateUtil.rollbackTransaction();

            response = DirexTransactionResponse.forException(direxTransactionRequest.getTransactionType(), ResultCode.CORE_ERROR, ResultMessage.FAILED, "");
            response.setTransactionType(direxTransactionRequest.getTransactionType());
            CoreTransactionUtil.subTransactionFailed(transaction, response, jmsManager.getCoreOutQueue(), direxTransactionRequest.getCorrelation());
            return response;
        }

        return response;
    }

    private DirexTransactionResponse manageUnexpectedAnswer(Map sessionTagMap, TransactionType transactionType, Transaction transaction) {
        String resultCode = (String) sessionTagMap.get(ParameterName.RESULT_CODE);
        String resultMessage = (String) sessionTagMap.get(ParameterName.RESULT_MESSAGE);

        return manageUnexpectedAnswer(resultCode, resultMessage, transactionType, transaction);
    }

    private DirexTransactionResponse manageUnexpectedAnswer(String resultCode, String resultMessage, TransactionType transactionType, Transaction transaction) {

        String message = "Tecnicard [" + transactionType.toString() + "] received unexpected answer. Code: " + resultCode + ". Description: " + resultMessage + ".";

        addSubTransaction(transactionType, ResultCode.TECNICARD_HOST_UNEXPECTED_RESULT_CODE, message, resultCode, transaction);
        DirexTransactionResponse exRsp;
        switch (resultCode) {
            case "1360320":
                exRsp = DirexTransactionResponse.forException(ResultCode.TECNICARD_HOST_UNEXPECTED_RESULT_CODE, ResultMessage.TECNICARD_PERSONALIZATION_FAILED, message, resultCode);
                break;
            case "136053":
                exRsp = DirexTransactionResponse.forException(ResultCode.TECNICARD_HOST_UNEXPECTED_RESULT_CODE, ResultMessage.TECNICARD_PERSONALIZATION_INFO_FAILED, message, resultCode);
                break;
            default:
                exRsp = DirexTransactionResponse.forException(ResultCode.TECNICARD_HOST_UNEXPECTED_RESULT_CODE, ResultMessage.TECNICARD_FAILED, message, resultCode);
        }

        exRsp.getTransaction().addSubTransactionList(transaction.getSub_Transaction());
        return exRsp;
    }

    private void addSubTransaction(TransactionType transactionType, ResultCode resultCode, String resultMessage, String errorCode, Transaction transaction) {
        SubTransaction subTransaction = new SubTransaction();
        subTransaction.setType(transactionType.getCode());
        subTransaction.setResultCode(resultCode.getCode());
        subTransaction.setResultMessage(resultMessage);
        subTransaction.setErrorCode(errorCode);
        subTransaction.setHost(NomHost.TECNICARD.getId());
        transaction.addSubTransaction(subTransaction);
    }

    private void payOutAmountCalculator(DirexTransactionRequest request, Transaction transaction) {

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreLocalTransactionBusinessLogic] feeCalculator() start ... amount = " + request.getTransactionData().get(ParameterName.AMMOUNT), null);
        Double amount = (Double) request.getTransactionData().get(ParameterName.AMMOUNT);

        Float feeAmount = (Float) request.getTransactionData().get(ParameterName.CRDLDF);

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreLocalTransactionBusinessLogic] amount =" + amount + " ,FEE_AMOUNT applied: " + feeAmount, null);
        Double payOut = amount - feeAmount;

        request.getTransactionData().put(ParameterName.PAYOUT_AMMOUNT, payOut);
        request.getTransactionData().put(ParameterName.FEE_AMMOUNT, feeAmount);

        transaction.setPayoutAmmount(payOut);
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreLocalTransactionBusinessLogic] feeCalculator() done with PAYOUT_AMOUNT: " + payOut, null);
    }

    class BreakException extends Exception {
    }
}
