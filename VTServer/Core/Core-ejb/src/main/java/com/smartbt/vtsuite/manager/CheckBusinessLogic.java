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
import com.smartbt.vtsuite.vtcommon.nomenclators.NomHost;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Map;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.sql.rowset.serial.SerialBlob;
import static com.smartbt.vtsuite.util.CoreTransactionUtil.*;
import com.smartbt.vtsuite.util.TransactionalException;

@TransactionManagement(value = TransactionManagementType.BEAN)
public class CheckBusinessLogic extends AbstractCommonBusinessLogic {

    @Override
    public void process(DirexTransactionRequest request, Transaction transaction) throws Exception {
        String checkId = "";
        String correlationId = request.getCorrelation();
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CheckBusinessLogic] Send answer to TERMINAL", null);
        boolean idScanSuccess = true;

        DirexTransactionResponse response;
        TransactionType originalTransaction = request.getTransactionType();
        Queue currentQueue = jmsManager.getCoreOutQueue();

        //This variables will control the behavior in Exceptional cases
        boolean sendChoiceCancelationRequest = false;
        boolean sendIstreamCheckAuthSubmit = false;
        boolean sendResponseToIStreamFront = false;

        try {

            if (!originalTransaction.equals(TransactionType.CARD_RELOAD_WITH_DATA)) {

                try {
                    DirexTransactionResponse personalInfoResponse = getPersonalInfoFromIDReader(request);

                    Map driverLicenseInfo = personalInfoResponse.getTransactionData();
                    request.getTransactionData().putAll(driverLicenseInfo);

                    idScanSuccess = true;
                } catch (TransactionalException e) {
                    CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CheckBusinessLogic] Driver License INELEGIBLE", null);
                    idScanSuccess = false;
                }
            }

            transaction.setSingle(Boolean.FALSE);

            if (request.getTransactionData().containsKey(ParameterName.OPERATION)) {
                transaction.setOperation((String) request.getTransactionData().get(ParameterName.OPERATION));
            }

            if (request.getTransactionData().containsKey(ParameterName.MICR)) {
                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CheckBusinessLogic] Value of MICR from POS: " + request.getTransactionData().get(ParameterName.MICR), null);
            }

            //-------  go to HOST ISTREAM (checkAuth) -------
            request.setTransactionType(TransactionType.ISTREAM_CHECK_AUTH);

            response = sendMessageToHost(request, NomHost.ISTREAM, ISTREAM_HOST_WAIT_TIME, transaction);

            //-----  WAIT PERSONAL INFO MESSAGE -------------- 
            checkId = (String) response.getTransactionData().get(ParameterName.CHECK_ID);

            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CheckBusinessLogic] ISTREAM checkId value: " + checkId, null);

            DirexTransactionRequest personalInfoRequest = receiveMessageFromFront(TransactionType.PERSONAL_INFO, transaction, checkId, correlationId);

            Map personalInfoRequestMap = personalInfoRequest.getTransactionData();

            //dont take the last name sent by iStream,
            //because when it is composed it will be trimed
            if (idScanSuccess && personalInfoRequestMap.containsKey(ParameterName.LAST_NAME)) {
                personalInfoRequestMap.remove(ParameterName.LAST_NAME);
            }

            request.getTransactionData().putAll(personalInfoRequestMap);

            feeCalculator(request, transaction);

            sendResponseToIStreamFront = true;

            if (personalInfoRequestMap.containsKey(ParameterName.ID) && personalInfoRequestMap.get(ParameterName.ID).equals("0")) {
                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CheckBusinessLogic] ParameterName.ID from personal info: " + personalInfoRequestMap.get(ParameterName.ID), null);
                throw new TransactionalException(ResultCode.ISTREAM_FRONT_PERSONAL_INFO_RECEIVED_AS_NULL, TransactionType.PERSONAL_INFO, "Personal Info sent ID = 0");
            }

            //------ PROCESS PERSONAL INFO  ------
            processPersonalInfo(transaction, request, personalInfoRequestMap);

            //-------SENT TO CHOICE ------
            DirexTransactionRequest reqCHOICE = request;
            reqCHOICE.setTransactionType(TransactionType.CHOICE_INSERT_TRANSACTION);

            response = sendMessageToHost(reqCHOICE, NomHost.CHOICE, CHOICE_WAIT_TIME, transaction);

            sendChoiceCancelationRequest = true;

            //---- if CHOICE Success ------- 
            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CheckBusinessLogic] CHOICE Success", null);

            //----------  TECNICARD VALIDATON ------------------
            String hostName = (String) request.getTransactionData().get(ParameterName.HOSTNAME);

            request.setTransactionType(TransactionType.GENERIC_HOST_VALIDATION);
            request.getTransactionData().put(TransactionType.TRANSACTION_TYPE, originalTransaction);
            response = sendMessageToHost(request, NomHost.valueOf(hostName), GENERIC_VALIDATION_WAIT_TIME, transaction);

            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CheckBusinessLogic] Recived message from " + hostName + " Validation", null);

            String estimatedPostingTime = response.getResultMessage();

            sendResponseToIStreamFront(true, checkId);

            sendResponseToIStreamFront = false;

            DirexTransactionRequest certegyRequest = receiveMessageFromFront(TransactionType.CERTEGY_INFO, transaction, checkId, correlationId);

            Map certegyInfoRequestMap = certegyRequest.getTransactionData();

            //send response to certegy
            DirexTransactionResponse certegyResponse;

            if (certegyInfoRequestMap.get(TransactionType.TRANSACTION_TYPE) == TransactionType.CERTEGY_INFO) {
                certegyResponse = DirexTransactionResponse.forSuccess();
            } else {
                certegyResponse = DirexTransactionResponse.forException(ResultCode.ISTREAM_FRONT_CERTEGY_INFO_NOT_RECEIVED, ResultMessage.ISTREAM_FRONT_CERTEGY_INFO_NOT_RECEIVED);
            }
            jmsManager.send(certegyResponse, jmsManager.getFrontIStreamInQueue(), checkId);

            request.getTransactionData().putAll(certegyInfoRequestMap);

            boolean certegySuccess = false;
            String certegyCode = "EMPTY";
            //----
            //Verify if Certegy success here  ---------
            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CheckBusinessLogic] Checking Certegy for success", null);
            if (certegyInfoRequestMap.containsKey(ParameterName.CERTEGY_CODE)) {
                certegyCode = (String) certegyInfoRequestMap.get(ParameterName.CERTEGY_CODE);
                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CheckBusinessLogic] Certegy code: [" + certegyCode + "]", null);
                if (certegyCode.trim().equalsIgnoreCase("00")) {
                    certegySuccess = true;
                }
            } 
            
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CheckBusinessLogic] Certegy value: " + certegySuccess, null);

            if (certegySuccess) {
                sendAnswerToTerminal(originalTransaction, ResultCode.SUCCESS, estimatedPostingTime, hostName, correlationId);

                //------ CREATE CERTEGY INFO SUBTRANSACRTION ------
                addSuccessfulSubTransaction(transaction, TransactionType.CERTEGY_INFO);

                if (certegyInfoRequestMap.containsKey(ParameterName.DEPOSIT_ID)) {
                    String depositId = (String) certegyInfoRequestMap.get(ParameterName.DEPOSIT_ID);
                    transaction.setIstream_id(depositId);
                }
            } else {
                throw new TransactionalException(ResultCode.CERTEGY_DENY, TransactionType.CERTEGY_INFO, ResultMessage.CERTEGY_DENY.getMessage());
            }

            sendIstreamCheckAuthSubmit = true;

            //-------------- WAIT CONFIRMATION FROM TERMINAL -------------
            DirexTransactionRequest confirmationRequest;
            confirmationRequest = receiveMessageFromFront(TransactionType.TECNICARD_CONFIRMATION, transaction, checkId, correlationId);

            extractTecnicardConfirmationInformation(confirmationRequest, transaction);

            //------ CREATE TECNICARD_CONFIRMATION SUBTRANSACTION ------
            addSuccessfulSubTransaction(transaction, TransactionType.TECNICARD_CONFIRMATION);

            correlationId = confirmationRequest.getCorrelation();
            request.setCorrelation(correlationId);
            currentQueue = jmsManager.getCore2OutQueue();

            //-----  SEND TO GENERIC HOST -----------    GENERIC_HOST_CARD_LOAD
            request.setTransactionType(TransactionType.GENERIC_HOST_CARD_LOAD);

            response = sendMessageToHost(request, NomHost.valueOf(hostName), GENERIC_CARD_LOAD_WAIT_TIME, transaction);

            if (response.wasApproved()) {
                String action = "submit";
                sendIstreamCheckAuthSubmit(request, action, transaction, checkId);
                sendAnswerToTerminal(TransactionType.TECNICARD_CONFIRMATION, ResultCode.SUCCESS, estimatedPostingTime, hostName, correlationId);
                choiceNotifyPayment(request, transaction);

            }

            transaction.setResultCode(ResultCode.SUCCESS.getCode());
            transaction.setResultMessage(ResultMessage.SUCCESS.getMessage());

            transaction.setTransactionBalanceData(response.getTransactionBalanceData());
            CoreTransactionUtil.persistTransaction(transaction);

            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CheckBusinessLogic] Transaction finished successfully", null);

            //
            //     EXCEPTIONAL CASES
            //
        } catch (TransactionalException transactionalException) {
            System.out.println("*********** TransactionalException ************");
            
            if (sendResponseToIStreamFront) {
                System.out.println("sendResponseToIStreamFront = true");
                sendResponseToIStreamFront(false, checkId);
            }
            if (sendChoiceCancelationRequest) {
                choiceCancellationRequest(request, transaction);
            }
            if (sendIstreamCheckAuthSubmit) {
                sendIstreamCheckAuthSubmit(request, "decline", transaction, checkId);
            }

            if (transactionalException.getResponse() != null) {// this is when  !response.approved()
                System.out.println("Sending subTransactionFailed 1");
                CoreTransactionUtil.subTransactionFailed(transaction, transactionalException.getResponse(), currentQueue, correlationId);
            } else {//this is when caughting Exception 
                System.out.println("Sending subTransactionFailed 2 -> transactionalException.getTransactionType()" + transactionalException.getTransactionType());
                CoreTransactionUtil.subTransactionFailed(transaction, currentQueue, correlationId, transactionalException.getTransactionType(), transactionalException.getMessage());
            }
        } catch (Exception e) {
            System.out.println("!!!!!!!!!!!!!!!!  UNEXPECTED  EXCEPTION  !!!!!!!!!!!!!!!!!!!");
            e.printStackTrace();
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CheckBusinessLogic] UNEXPECTED Exception ", null);

            String msg = e.getMessage();
            if (msg != null) {
                transaction.setResultMessage((msg.length() > 254) ? msg.substring(0, 254) : msg);
            } else {
                transaction.setResultMessage("Message Error was printed, Please check the server logs. ");
            }

            CoreTransactionUtil.subTransactionFailed(transaction, currentQueue, correlationId, request.getTransactionType(), e.getMessage());

        }
    }

    private DirexTransactionRequest receiveMessageFromFront(TransactionType transactionType, Transaction transaction, String checkId, String correlationId) throws Exception {
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CheckBusinessLogic] Waiting " + transactionType + " from FRONT", null);

        DirexTransactionResponse response;
        Message message = null;

        // in error case
        ResultCode errorCode = null;
        ResultMessage errorMessage = null;
        String correlation = null;
        Long waitTime = null;
        Queue queue = null;

        switch (transactionType) {
            case PERSONAL_INFO:
                errorCode = ResultCode.ISTREAM_FRONT_PERSONAL_INFO_NOT_RECEIVED;
                errorMessage = ResultMessage.ISTREAM_FRONT_PERSONAL_INFO_RECEIVED_AS_NULL;
                correlation = checkId;
                waitTime = PERSONAL_INFO_WAIT_TIME;
                queue = jmsManager.getFrontIStreamOutQueue();
                break;
            case CERTEGY_INFO:
                errorCode = ResultCode.ISTREAM_FRONT_CERTEGY_INFO_NOT_RECEIVED;
                errorMessage = ResultMessage.ISTREAM_FRONT_CERTEGY_INFO_NOT_RECEIVED;
                correlation = checkId;
                waitTime = CERTEGY_INFO_WAIT_TIME;
                queue = jmsManager.getFrontIStreamOutQueue();
                break;
            case TECNICARD_CONFIRMATION:
                errorCode = ResultCode.TERMINAL_CONFIRMATION_TIME_EXCEED;
                errorMessage = ResultMessage.TERMINAL_CONFIRMATION_TIME_EXCEED;
                correlation = correlationId;
                waitTime = TECNICARD_CONFIRMATION_WAIT_TIME;
                queue = jmsManager.getCore2InQueue();
                CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CheckBusinessLogic] receiveMessageFromFront(...) queue = jmsManager.getCore2InQueue(); correlationId = " + correlationId, null);
                break;
        }

        try {
            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CheckBusinessLogic] receiveMessageFromFront(...) jmsManager.receiving(...) correlation = " + correlation, null);
            message = jmsManager.receive(queue, correlation, waitTime);
            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CheckBusinessLogic] Recived " + transactionType, null);
        } catch (IOException | JMSException e) {
            throw new TransactionalException(errorCode, transactionType, e);
        }

        if (message == null || !(message instanceof ObjectMessage)) {
            throw new TransactionalException(errorCode, transactionType, "Message received is null.");
        }

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CheckBusinessLogic] Recived " + transactionType + " successfully.", null);

        ObjectMessage objectMessage = (ObjectMessage) message;
        Serializable s = objectMessage.getObject();
        DirexTransactionRequest request = (DirexTransactionRequest) s;
        DirexTransactionResponse iStreamResponse = new DirexTransactionResponse();
        if (request.getTransactionType() != transactionType) {
            iStreamResponse.getTransactionData().put(ParameterName.CHECK_ID, checkId);

            iStreamResponse.setResultCode(ResultCode.CORE_ERROR);
            iStreamResponse.setResultMessage(ResultMessage.FAILED.getMessage() + " TransactionType expected " + transactionType + " and received " + request.getTransactionType());
            iStreamResponse.setApproved(false);

            jmsManager.send(iStreamResponse, jmsManager.getFrontIStreamInQueue(), checkId);
            CoreTransactionUtil.subTransactionFailed(transaction, iStreamResponse, jmsManager.getCoreOutQueue(), correlationId);

        }

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CheckBusinessLogic] Message received", null);
        return request;
    }

    private void sendResponseToIStreamFront(boolean success, String checkId) throws JMSException {
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CheckBusinessLogic] Send response back to IStreamFront with result :: " + (success ? "SUCCESS" : "FAILED"), null);

        DirexTransactionResponse iStreamResponse = new DirexTransactionResponse();
        iStreamResponse.getTransactionData().put(ParameterName.CHECK_ID, checkId);

        iStreamResponse.setResultCode(success ? ResultCode.SUCCESS : ResultCode.FAILED);
        iStreamResponse.setResultMessage(success ? ResultMessage.SUCCESS.getMessage() : ResultMessage.FAILED.getMessage());
        iStreamResponse.setApproved(success);

        jmsManager.send(iStreamResponse, jmsManager.getFrontIStreamInQueue(), checkId);
    }

    private void extractTecnicardConfirmationInformation(DirexTransactionRequest request, Transaction transaction) throws Exception, SQLException {
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CheckBusinessLogic] extractTecnicardConfirmationInformation(...) ", null);
        Map transactionData = request.getTransactionData();
        try {
            if (transactionData.containsKey(ParameterName.TRUNCATED_CHECK) && transactionData.get(ParameterName.TRUNCATED_CHECK) != null) {
                byte[] truncatedCheck = (byte[]) transactionData.get(ParameterName.TRUNCATED_CHECK);
                if (truncatedCheck != null) {
                    CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CheckBusinessLogic] extractTecnicardConfirmationInformation(...) truncatedCheck != null", null);
                    java.sql.Blob truncatedCheckBlob = new SerialBlob(truncatedCheck);
                    transaction.setTruncatedCheck(truncatedCheckBlob);
                } else {
                    CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CheckBusinessLogic] extractTecnicardConfirmationInformation(...) truncatedCheck IS null", null);
                    throw new Exception("[CheckBusinessLogic] extractTecnicardConfirmationInformation(...) truncatedCheck IS null");
                }
            } else {
                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CheckBusinessLogic] extractTecnicardConfirmationInformation(...) truncatedCheck IS null", null);
                throw new Exception("[CheckBusinessLogic] extractTecnicardConfirmationInformation(.) truncatedCheck IS null");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception();
        }
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CheckBusinessLogic] extractTecnicardConfirmationInformation(...) DONE", null);
    }
 
    public void sendIstreamCheckAuthSubmit(DirexTransactionRequest request, String action, Transaction transaction, String checkId) throws Exception {

        DirexTransactionRequest istreamCancelRequest = new DirexTransactionRequest();

        Map istreamCancelMap = request.getTransactionData();

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CheckBusinessLogic] sendIstreamCheckAuthSubmit pass " + istreamCancelMap.get(ParameterName.TERMINAL_PASSWORD_ISTREAM), null);
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CheckBusinessLogic] sendIstreamCheckAuthSubmit user " + istreamCancelMap.get(ParameterName.TERMINAL_USER_ISTREAM), null);
        istreamCancelRequest.setTransactionType(TransactionType.ISTREAM_CHECK_AUTH_SUBMIT);
        istreamCancelRequest.getTransactionData().put(ParameterName.ACTION, action);
        istreamCancelRequest.getTransactionData().put(ParameterName.REQUEST_ID, checkId);
        istreamCancelRequest.getTransactionData().put(ParameterName.PASSWORD, istreamCancelMap.get(ParameterName.TERMINAL_PASSWORD_ISTREAM));
        istreamCancelRequest.getTransactionData().put(ParameterName.USER, istreamCancelMap.get(ParameterName.TERMINAL_USER_ISTREAM));

        //------ CREATE ISTREAM_CHECKAUTHSUBMIT SUBTRANSACTION ------
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CheckBusinessLogic] Saving IstreamCheckAutSubmitSubTransaction", null);
          
        sendMessageToHost(istreamCancelRequest, NomHost.ISTREAM, ISTREAM_HOST_WAIT_TIME, transaction);

        addSuccessfulSubTransaction(transaction, TransactionType.ISTREAM_CASH_AUTH_SUBMIT);
    }

}
