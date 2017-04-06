
/*

 @Author Roberto Rodriguez
 robertoSoftwareEngineer@gmail.com

 */
package com.smartbt.vtsuite.manager;

import com.smartbt.vtsuite.util.CoreTransactionUtil;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.enums.ResultCode;
import com.smartbt.girocheck.servercommon.enums.ResultMessage;
import com.smartbt.girocheck.servercommon.model.Transaction;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import com.smartbt.vtsuite.util.TransactionalException;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomHost;
import java.io.Serializable;
import java.util.Map;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import static com.smartbt.vtsuite.util.CoreTransactionUtil.*;

@TransactionManagement(value = TransactionManagementType.BEAN)
public class CashBusinessLogic extends AbstractCommonBusinessLogic {

    @Override
    public void process(DirexTransactionRequest request, Transaction transaction) throws Exception {
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CashBusinessLogic] Send answer to TERMINAL", null);

        if (request.getTransactionData().containsKey(ParameterName.DLDATASCAN) || request.getTransactionData().containsKey(ParameterName.DLDATASWIPE)) {
            if (request.getTransactionData().get(ParameterName.DLDATASCAN) != null || request.getTransactionData().get(ParameterName.DLDATASWIPE) != null) {
                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CashBusinessLogic] data ParameterName.DLDATASCAN received value: " + request.getTransactionData().get(ParameterName.DLDATASCAN), null);
                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CashBusinessLogic] data ParameterName.DLDATASWIPE received value: " + request.getTransactionData().get(ParameterName.DLDATASWIPE), null);
            } else {
                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CashBusinessLogic] data from DLDATASCAN or DLDATASWIPE is null", null);
            }
        }

        DirexTransactionResponse response;
        TransactionType originalTransaction = request.getTransactionType();

        String correlationId = request.getCorrelation();
        Queue currentQueue = jmsManager.getCoreOutQueue();

        transaction.setSingle(Boolean.FALSE);

        if (request.getTransactionData().containsKey(ParameterName.OPERATION)) {
            transaction.setOperation((String) request.getTransactionData().get(ParameterName.OPERATION));
        }

        try {

            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CashBusinessLogic] Calculating fee for cash from amount", null);

            feeCalculator(request, transaction);

            Map personalInfoRequestMap;
            if (!originalTransaction.equals(TransactionType.CARD_RELOAD_WITH_DATA)) {

                CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CashBusinessLogic] Getting personal info from PersonalInfoFromIDReader()", null);
                DirexTransactionResponse personalInfoResponse = getPersonalInfoFromIDReader(request);

                personalInfoRequestMap = personalInfoResponse.getTransactionData();

            } else {
                personalInfoRequestMap = request.getTransactionData();
            }

            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CashBusinessLogic] Personal info from PersonalInfoFromIDReader() done", null);

            request.getTransactionData().putAll(personalInfoRequestMap);

            //------ PROCESS PERSONAL INFO  ------
            processPersonalInfo(transaction, request, personalInfoRequestMap);

            //----------  TECNICARD VALIDATON ------------------
            String hostName = (String) request.getTransactionData().get(ParameterName.HOSTNAME);

            request.setTransactionType(TransactionType.GENERIC_HOST_VALIDATION);
            request.getTransactionData().put(TransactionType.TRANSACTION_TYPE, originalTransaction);

            response = sendMessageToHost(request, NomHost.valueOf(hostName), GENERIC_VALIDATION_WAIT_TIME, transaction);

            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CashBusinessLogic] Recived message from " + hostName + " Validation", null);

            String estimatedPostingTime = response.getResultMessage();

            //Send response to the terminal of reload or new cardload 
            sendAnswerToTerminal(originalTransaction, response.getResultCode(), estimatedPostingTime, correlationId);

            //-------------- WAIT CONFIRMATION FROM TERMINAL -------------
            DirexTransactionRequest confirmationRequest = null;

            confirmationRequest = receiveMessageFromFront(TransactionType.TECNICARD_CONFIRMATION, correlationId, transaction);

            correlationId = confirmationRequest.getCorrelation();
            request.setCorrelation(correlationId);
            currentQueue = jmsManager.getCore2OutQueue();

            //------ CREATE TECNICARD_CONFIRMATION SUBTRANSACTION ------
            addSuccessfulSubTransaction(transaction, TransactionType.TECNICARD_CONFIRMATION);

            //-----  SEND TO GENERIC HOST -----------    GENERIC_HOST_CARD_LOAD
            request.setTransactionType(TransactionType.GENERIC_HOST_CARD_LOAD);

            response = sendMessageToHost(request, NomHost.valueOf(hostName), GENERIC_CARD_LOAD_WAIT_TIME, transaction);

            sendAnswerToTerminal(TransactionType.TECNICARD_CONFIRMATION, response.getResultCode(), estimatedPostingTime, correlationId);

            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CashBusinessLogic] Before consuming choiceNotifyPayment " + transaction.getSub_Transaction().size(), null);
            transaction.setResultCode(ResultCode.SUCCESS.getCode());
            transaction.setResultMessage(ResultMessage.SUCCESS.getMessage());

            CoreTransactionUtil.persistTransaction(transaction);

            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CashBusinessLogic] Transaction finished successfully. ", null);
            //
            //     EXCEPTIONAL CASES
            //
        } catch (TransactionalException transactionalException) {
            System.out.println("*********** TransactionalException ************");

            if (transactionalException.getResponse() != null) {
                // this is when  !response.approved()
                System.out.println("Sending subTransactionFailed 1");
                CoreTransactionUtil.subTransactionFailed(transaction, transactionalException.getResponse(), currentQueue, correlationId);
            } else {
                //this is when caughting Exception
                System.out.println("Sending subTransactionFailed 2 -> transactionalException.getTransactionType()" + transactionalException.getTransactionType());
                CoreTransactionUtil.subTransactionFailed(transaction, currentQueue, correlationId, transactionalException.getTransactionType(), transactionalException.getMessage());
            }

        } catch (Exception e) {
            System.out.println("!!!!!!!!!!!!!!!!  UNEXPECTED  EXCEPTION  !!!!!!!!!!!!!!!!!!!");
            e.printStackTrace();
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[NewCoreComplexTransactionBusinessLogic] UNEXPECTED Exception ", null);

            String msg = e.getMessage();
            if (msg != null) {
                transaction.setResultMessage((msg.length() > 254) ? msg.substring(0, 254) : msg);
            } else {
                transaction.setResultMessage("Message Error was printed, Please check the server logs. ");
            }

            CoreTransactionUtil.subTransactionFailed(transaction, currentQueue, correlationId, request.getTransactionType(), e.getMessage());
        }
    }

    private DirexTransactionRequest receiveMessageFromFront(TransactionType transactionType, String correlationId, Transaction transaction) throws Exception {

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "", null);
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "", null);
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CashBusinessLogic] Waiting " + transactionType + " from FRONT. ", null);

        DirexTransactionResponse response;
        Message message = null;

        // in error case
        ResultCode errorCode = null;
        Long waitTime = null;
        Queue queue = null;

        switch (transactionType) {

            case TECNICARD_CONFIRMATION:
                errorCode = ResultCode.TERMINAL_CONFIRMATION_TIME_EXCEED;
                waitTime = TECNICARD_CONFIRMATION_WAIT_TIME;
                queue = jmsManager.getCore2InQueue();
                break;
        }

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CashBusinessLogic] Receiving... ", null);
        message = jmsManager.receive(queue, correlationId, waitTime);

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CashBusinessLogic] Received " + transactionType, null);

        if (message == null || !(message instanceof ObjectMessage)) {
            // throw new TransactionalException(ResultCode.getFromHost(host), transactionType, e);
            response = DirexTransactionResponse.forException(errorCode, ResultMessage.FAILED, transactionType + " not received. ", "");
            response.setTransactionType(transactionType);
            CoreTransactionUtil.subTransactionFailed(transaction, response, jmsManager.getCoreOutQueue(), correlationId);
            throw new Exception();
        }

        ObjectMessage objectMessage = (ObjectMessage) message;
        Serializable s = objectMessage.getObject();
        DirexTransactionRequest request = (DirexTransactionRequest) s;

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CashBusinessLogic] Message received. ", null);
        return request;
    }

}
