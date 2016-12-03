package com.smartbt.vtsuite.manager;

import com.smartbt.girocheck.servercommon.email.EmailUtils;
import com.smartbt.girocheck.servercommon.email.ImagePart;
import com.smartbt.vtsuite.util.CoreTransactionUtil;
import com.smartbt.girocheck.servercommon.enums.EnumCountry;
import com.smartbt.girocheck.servercommon.enums.EnumState;
import com.smartbt.girocheck.servercommon.jms.JMSManager;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.enums.ResultCode;
import com.smartbt.girocheck.servercommon.enums.ResultMessage;
import com.smartbt.girocheck.servercommon.manager.CountryManager;
import com.smartbt.girocheck.servercommon.manager.FeeBucketsManager;
import com.smartbt.girocheck.servercommon.manager.PersonalIdentificationManager;
import com.smartbt.girocheck.servercommon.manager.StateManager;
import com.smartbt.girocheck.servercommon.manager.TransactionManager;
import com.smartbt.girocheck.servercommon.messageFormat.IdType;
import com.smartbt.girocheck.servercommon.model.Address;
import com.smartbt.girocheck.servercommon.model.Check;
import com.smartbt.girocheck.servercommon.model.Country;
import com.smartbt.girocheck.servercommon.model.PersonalIdentification;
import com.smartbt.girocheck.servercommon.model.State;
import com.smartbt.girocheck.servercommon.model.SubTransaction;
import com.smartbt.girocheck.servercommon.model.Transaction;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import com.smartbt.girocheck.servercommon.utils.IDScanner;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomHost;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomState;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.sql.rowset.serial.SerialBlob;

@TransactionManagement(value = TransactionManagementType.BEAN)
public class CoreComplexCashTransactionBusinessLogic extends CoreAbstractTransactionBusinessLogic {

    private JMSManager jmsManager = JMSManager.get();
    private int state = 0;
    private String checkId;
    private Transaction transaction;
    private String correlationId;

    // WAITING TIMES
    private static final long TECNICARD_CONFIRMATION_WAIT_TIME = 180000;
    private static final long ORDER_EXPRESS_WAIT_TIME = 30000;
    private static final long ORDER_EXPRESS_LOG_WAIT_TIME = 240000;
    private static final long GENERIC_VALIDATION_WAIT_TIME = 60000;
    private static final long GENERIC_CARD_LOAD_WAIT_TIME = 30000;

    private static CountryManager countryManager = new CountryManager();
    private static TransactionManager transactionManager = new TransactionManager();
    private static StateManager stateManager = new StateManager();
    private PersonalIdentificationManager personalIdentificationManager = new PersonalIdentificationManager();

    public CoreComplexCashTransactionBusinessLogic() {
        super();

    }

    @Override
    public void process(DirexTransactionRequest request, Transaction transaction) throws Exception {

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CoreComplexCashBL] Send answer to TERMINAL", null);

        if (request.getTransactionData().containsKey(ParameterName.DLDATASCAN) || request.getTransactionData().containsKey(ParameterName.DLDATASWIPE)) {
            if (request.getTransactionData().get(ParameterName.DLDATASCAN) != null || request.getTransactionData().get(ParameterName.DLDATASWIPE) != null) {
                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreComplexCashBL] data ParameterName.DLDATASCAN received value: " + request.getTransactionData().get(ParameterName.DLDATASCAN), null);
                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreComplexCashBL] data ParameterName.DLDATASWIPE received value: " + request.getTransactionData().get(ParameterName.DLDATASWIPE), null);
            } else {
                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreComplexCashBL] data from DLDATASCAN or DLDATASWIPE is null", null);
            }
        }

        state = 1;
        DirexTransactionResponse response;
        Map responseMap;
        TransactionType originalTransaction = request.getTransactionType();

        this.transaction = transaction;
        this.correlationId = request.getCorrelation();
        transaction.setSingle(Boolean.FALSE);

        if (request.getTransactionData().containsKey(ParameterName.OPERATION)) {
            transaction.setOperation((String) request.getTransactionData().get(ParameterName.OPERATION));
        }

        try {

            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CoreComplexCashBL] Calculating fee for cash from amount", null);

            try {
                feeCalculator(request, transaction);
            } catch (Exception e) {
                e.printStackTrace();
                CoreTransactionUtil.subTransactionFailed(transaction, DirexTransactionResponse.forException(ResultCode.CORE_FEE_CALCULATION_ERROR, e), jmsManager.getCoreOutQueue(), correlationId);
                return;
            }

            Map personalInfoRequestMap;
            if (!originalTransaction.equals(TransactionType.CARD_RELOAD_WITH_DATA)) {

                CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CoreComplexCashBL] Getting personal info from PersonalInfoFromIDReader()", null);
                DirexTransactionResponse personalInfoResponse = getPersonalInfoFromIDReader(request);

                if (!personalInfoResponse.wasApproved()) {
                    personalInfoResponse.setTransactionType(TransactionType.PERSONAL_INFO);
                    CoreTransactionUtil.subTransactionFailed(transaction, personalInfoResponse, jmsManager.getCoreOutQueue(), correlationId);
                    return;
                }

                personalInfoRequestMap = personalInfoResponse.getTransactionData();

            } else {
                personalInfoRequestMap = request.getTransactionData();
                personalInfoRequestMap.put(ParameterName.CHECK_ID, request.getTransactionData().get(ParameterName.ID));
            }

            //-----  WAIT PERSONAL INFO MESSAGE -------------- 
            checkId = (String) personalInfoRequestMap.get(ParameterName.CHECK_ID);

            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CoreComplexCashBL] Personal info from PersonalInfoFromIDReader() done", null);
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreComplexCashBL] Personal info from PersonalInfoFromIDReader() done with check id: " + checkId, null);

            if (personalInfoRequestMap.containsKey(ParameterName.FEE_AMMOUNT)) {
                try {
                    String feeAmmountString = personalInfoRequestMap.get(ParameterName.FEE_AMMOUNT) + "";
                    double feeAmmount = Double.parseDouble(feeAmmountString);
                    transaction.setFeeAmmount(feeAmmount);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                transaction.setPayoutAmmount((Double) personalInfoRequestMap.get(ParameterName.PAYOUT_AMMOUNT));

            }

            request.getTransactionData().putAll(personalInfoRequestMap);

            //------ CREATE PERSONAL INFO SUBTRANSACTION ------
            SubTransaction personalInfoSubTransaction = new SubTransaction();
            personalInfoSubTransaction.setType(TransactionType.PERSONAL_INFO.getCode());
            personalInfoSubTransaction.setResultCode(ResultCode.SUCCESS.getCode());
            personalInfoSubTransaction.setResultMessage(ResultMessage.SUCCESS.getMessage());
            transaction.addSubTransaction(personalInfoSubTransaction);

            //---------  ASK IF CANCELATED ------------------ (set cancelable as TRUE)
            if (isCancelated(true)) {
                response = DirexTransactionResponse.forException(ResultCode.TERMINAL_CANCELATED_TRANSACTION, ResultMessage.TERMINAL_CANCELATED_TRANSACTION);
                response.setTransactionType(TransactionType.ISTREAM_CASH_AUTH_SUBMIT);
                CoreTransactionUtil.subTransactionFailed(transaction, response, jmsManager.getCoreOutQueue(), correlationId);
                return;
            }

            //-------------------------
            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CoreComplexCashBL] Persist personal Info", null);
            //PERSIST PERSONAL INFO

            fillOutClient(request.getTransactionData());
            fillOutClientAddress(request.getTransactionData());
            PersonalIdentification identification = fillOutPersonalIdentification(request.getTransactionData());
            fillOutCheck(request.getTransactionData());

            if (personalInfoRequestMap.containsKey(ParameterName.IDCOUNTRY) || personalInfoRequestMap.containsKey(ParameterName.IDSTATE)
                    || personalInfoRequestMap.containsKey(ParameterName.COUNTRY) || personalInfoRequestMap.containsKey(ParameterName.STATE)) {
                try {
                    HibernateUtil.beginTransaction();

                    if (identification.getIdType() != null) {
                        personalIdentificationManager.removeByClientAndType(transaction.getClient().getId(), identification.getIdType());
                    }

                    if (personalInfoRequestMap.containsKey(ParameterName.IDCOUNTRY)) {
                        String idCountryAbbreviation = (String) personalInfoRequestMap.get(ParameterName.IDCOUNTRY);
                        Country country = countryManager.getByAbbreviation(idCountryAbbreviation);

                        if (country != null) {
                            request.getTransactionData().put(ParameterName.IDCOUNTRY, country.getCode() + "");
                        } else {
                            request.getTransactionData().put(ParameterName.IDCOUNTRY, EnumCountry.EUA.getCode() + "");
                        }
                    }

                    if (personalInfoRequestMap.containsKey(ParameterName.IDSTATE)) {
                        String idStateAbbreviation = (String) personalInfoRequestMap.get(ParameterName.IDSTATE);
                        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreComplexCashBL] For IDSTATE StateAbbreviation >>> = " + idStateAbbreviation, null);
                        State statee = stateManager.getByAbbreviation(idStateAbbreviation);

                        if (statee != null) {
                            request.getTransactionData().put(ParameterName.IDSTATE, statee.getCode() + "");
                            identification.setState(statee);
                        } else {
                            request.getTransactionData().put(ParameterName.IDSTATE, EnumState.FL.getId() + "");
                        }
                        request.getTransactionData().put(ParameterName.OEIDSTATE, NomState.valueOf(idStateAbbreviation).getId() + "");
                    }
                    if (personalInfoRequestMap.containsKey(ParameterName.COUNTRY)) {
                        String countryAbbreviation = (String) personalInfoRequestMap.get(ParameterName.COUNTRY);
                        Country country = countryManager.getByAbbreviation(countryAbbreviation);

                        if (country != null) {
                            request.getTransactionData().put(ParameterName.COUNTRY, country.getCode() + "");
                            transaction.getClient().getAddress().setCountry(country);
                        } else {
                            request.getTransactionData().put(ParameterName.COUNTRY, EnumCountry.EUA.getCode() + "");
                        }
                    }

                    if (personalInfoRequestMap.containsKey(ParameterName.STATE)) {
                        String stateAbbreviation = (String) personalInfoRequestMap.get(ParameterName.STATE);
                        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreComplexCashBL] For IDSTATE StateAbbreviation >>> = " + stateAbbreviation, null);
                        State statee = stateManager.getByAbbreviation(stateAbbreviation);

                        if (statee != null) {
                            request.getTransactionData().put(ParameterName.STATE, statee.getCode() + "");
                            transaction.getClient().getAddress().setState(statee);
                        } else {
                            request.getTransactionData().put(ParameterName.STATE, EnumState.FL.getId() + "");
                        }
                        request.getTransactionData().put(ParameterName.OEIDSTATE, NomState.valueOf(stateAbbreviation).getId() + "");
                    }

                    HibernateUtil.commitTransaction();
                } catch (Exception e) {
                    CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreComplexCashBL] Error", e.getMessage());
                    HibernateUtil.rollbackTransaction();
                }
            }

            
            
//            if (personalInfoRequestMap.containsKey(ParameterName.BORNDATE) || personalInfoRequestMap.containsKey(ParameterName.EXPIRATION_DATE)) {
//
//                if (personalInfoRequestMap.get(ParameterName.BORNDATE) != null) {
//                    String dob = (String) personalInfoRequestMap.get(ParameterName.BORNDATE);
//                    CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreComplexCashBL] BORNDATE value after convert it to a DATE: " + dob, null);
//                    Date dobb = new SimpleDateFormat("MM-dd-yyyy").parse(dob);
//                    request.getTransactionData().put(ParameterName.BORNDATE, dobb);
//
//                    CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreComplexCashBL] BORNDATE value : " + dobb, null);
//                }
//
//                if (personalInfoRequestMap.get(ParameterName.EXPIRATION_DATE) != null) {
//
//                    String expDate = (String) personalInfoRequestMap.get(ParameterName.EXPIRATION_DATE);
//                    Date expiDate = new SimpleDateFormat("yyyyMMdd").parse(expDate);
//                    request.getTransactionData().put(ParameterName.EXPIRATION_DATE, expiDate);
//                    CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreComplexCashBL] EXPIRATIONDATE value: " + expiDate, null);
//
//                }
//
//            }

            identification.setClient(transaction.getClient());
            Set set = new HashSet();
            set.add(identification);
            transaction.getClient().setData_SD(set);

            //-------SENT TO  ORDER_EXPRESS ------
            DirexTransactionRequest reqOE = request;
            reqOE.setTransactionType(TransactionType.ORDER_EXPRESS_CONTRATACIONES);

            try {
                response = sendMessageToHost(reqOE, NomHost.ORDER_EXPRESS.toString(), ORDER_EXPRESS_WAIT_TIME);
            } catch (Exception e) {
                //sendEmail
                StringBuffer buffer2 = new StringBuffer();
                buffer2.append("<html><head><style type=\"text/css\">body{border:0px none;text-align:center;}</style></head><body>");

                buffer2.append("There were a problem receiving OE Contrataciones ************").append(" at ").append((new Date()).toString());
                buffer2.append("</body></html>");

                generateNotificationEmail(buffer2, null);
                return;
            }

            if (!response.wasApproved()) {
                response.setTransactionType(request.getTransactionType());
                CoreTransactionUtil.subTransactionFailed(transaction, response, jmsManager.getCoreOutQueue(), correlationId);
                return;
            }
            //---- if ORDER_EXPRESS Success --------    opCode:: 01
            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CoreComplexCashBL] ORDER_EXPRESS Success", null);

            transaction.addSubTransactionList(response.getTransaction().getSub_Transaction());

            responseMap = response.getTransactionData();

            if (responseMap.containsKey(ParameterName.AUTHO_NUMBER)) {
                transaction.setOrderExpressId((String) responseMap.get(ParameterName.AUTHO_NUMBER));
            }

            if (responseMap.containsKey(ParameterName.IDBENEFICIARY)) {
                transaction.getClient().setIdBeneficiary((String) responseMap.get(ParameterName.IDBENEFICIARY));
            }

            //if order express success, I ask if terminal cancelated transaction.
            //(set cancelable as false)
            if (isCancelated(false)) {
                response = DirexTransactionResponse.forException(ResultCode.TERMINAL_CANCELATED_TRANSACTION, ResultMessage.TERMINAL_CANCELATED_TRANSACTION);
                response.setTransactionType(originalTransaction);
                CoreTransactionUtil.subTransactionFailed(transaction, response, jmsManager.getCoreOutQueue(), correlationId);
                return;
            }

            if (request.getTransactionData().containsKey(ParameterName.PHONE)) {
                String cell_area_code = (String) request.getTransactionData().get(ParameterName.PHONE);
                request.getTransactionData().put(ParameterName.CELL_PHONE_AREA, cell_area_code.substring(0, 3));

                String cell_phone = (String) request.getTransactionData().get(ParameterName.PHONE);
                request.getTransactionData().put(ParameterName.CELL_PHONE, cell_phone.substring(3));

                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreComplexCashBL] CELL AREA CODE: " + cell_phone.substring(0, 3) + " CELL NUMBER " + cell_phone.substring(3), null);
            }
            state = 2;
            /**
             * *********************** OE LOG METHOD commented for tests
             * only***************
             */

            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreComplexCashBL]  Sending from core to OE host call LOGS: : ", null);
            DirexTransactionRequest req002 = request;
            req002.setTransactionType(TransactionType.ORDER_EXPRESS_LOGS);
            req002.getTransactionData().put(ParameterName.AUTHO_NUMBER, responseMap.get(ParameterName.AUTHO_NUMBER));
            DirexTransactionResponse responseeeee2;

            try {
                responseeeee2 = sendMessageToHost(req002, NomHost.ORDER_EXPRESS.toString(), ORDER_EXPRESS_LOG_WAIT_TIME);
            } catch (Exception e) {
                //sendEmail
                OEDevolucion(request);
                StringBuffer buffer2 = new StringBuffer();
                buffer2.append("<html><head><style type=\"text/css\">body{border:0px none;text-align:center;}</style></head><body>");

                buffer2.append("There were a problem receiving OE log response ************").append(" at ").append((new Date()).toString());
                buffer2.append("</body></html>");

                generateNotificationEmail(buffer2, null);
                return;
            }

            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreComplexCashBL] Response from method log OE OP_CODE : " + responseeeee2.getTransactionData().get(ParameterName.OP_CODE), null);
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreComplexCashBL] Response from method log OE OESTATUS : " + responseeeee2.getTransactionData().get(ParameterName.OESTATUS), null);
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreComplexCashBL] Response from method log OE IDTELLER : " + responseeeee2.getTransactionData().get(ParameterName.IDTELLER), null);
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreComplexCashBL] Response from method log OE OEDATE_TIME : " + responseeeee2.getTransactionData().get(ParameterName.OEDATE_TIME), null);
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreComplexCashBL] Response from method log OE OENOTES : " + responseeeee2.getTransactionData().get(ParameterName.OENOTES), null);
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreComplexCashBL] Response from method log OE AUTHO_NUMBER : " + responseeeee2.getTransactionData().get(ParameterName.AUTHO_NUMBER), null);

            if (!responseeeee2.wasApproved()) {
                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreComplexCashBL] Response from method log OE wasn't approved ", null);
                response.setTransactionType(req002.getTransactionType());
                CoreTransactionUtil.subTransactionFailed(transaction, responseeeee2, jmsManager.getCoreOutQueue(), correlationId);
                return;
            }

            /**
             * *******************************LOG OE method
             * ************************
             */
            //----------  TECNICARD VALIDATON ------------------
            //ask for host and if is Fuze put in the request the needed fuze data.
            String hostName = (String) request.getTransactionData().get(ParameterName.HOSTNAME);

            request.setTransactionType(TransactionType.GENERIC_HOST_VALIDATION);
            request.getTransactionData().put(TransactionType.TRANSACTION_TYPE, originalTransaction);

            try {
                response = sendMessageToHost(request, hostName, GENERIC_VALIDATION_WAIT_TIME);
            } catch (Exception e) {
                //sendEmail
                OEDevolucion(request);
                StringBuffer buffer2 = new StringBuffer();
                buffer2.append("<html><head><style type=\"text/css\">body{border:0px none;text-align:center;}</style></head><body>");

                buffer2.append("There were a problem receiving generic host validation response ************").append(" at ").append((new Date()).toString());
                buffer2.append("</body></html>");

                generateNotificationEmail(buffer2, null);
                return;
            }

            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CoreComplexCashBL] Recived message from " + hostName + " Validation", null);

            transaction.addSubTransactionList(response.getTransaction().getSub_Transaction());

            if (!response.wasApproved()) {
                response.setTransactionType(request.getTransactionType());
                CoreTransactionUtil.subTransactionFailed(transaction, response, jmsManager.getCoreOutQueue(), correlationId);
                OEDevolucion(request);
                return;
            }

            String estimatedPostingTime = response.getResultMessage();

            //Send response to the terminal of reload or new cardload
            sendAnswerToTerminal(originalTransaction, response.getResultCode(), estimatedPostingTime, hostName);

            state = 3;

            //-------------- WAIT CONFIRMATION FROM TERMINAL -------------
            DirexTransactionRequest confirmationRequest;
            try {
                confirmationRequest = receiveMessageFromFront(TransactionType.TECNICARD_CONFIRMATION);
            } catch (Exception e) {
                //sendEmail
                OEDevolucion(request);
                StringBuffer buffer = new StringBuffer();
                buffer.append("<html><head><style type=\"text/css\">body{border:0px none;text-align:center;}</style></head><body>");

                buffer.append("There were a problem receiving the confirmation request ************").append(((String) request.getTransactionData().get(ParameterName.CARD_NUMBER)).substring(12)).append(" by the client with ID = ").append((String) request.getTransactionData().get(ParameterName.ID)).append(" at ").append((new Date()).toString());
                buffer.append("</body></html>");

                generateNotificationEmail(buffer, null);
                return;
            }

            //------ CREATE TECNICARD_CONFIRMATION SUBTRANSACTION ------
            SubTransaction tecnicardConfirmationSubTransaction = new SubTransaction();
            tecnicardConfirmationSubTransaction.setType(TransactionType.TECNICARD_CONFIRMATION.getCode());
            tecnicardConfirmationSubTransaction.setResultCode(ResultCode.SUCCESS.getCode());
            tecnicardConfirmationSubTransaction.setResultMessage(ResultMessage.SUCCESS.getMessage());
            transaction.addSubTransaction(tecnicardConfirmationSubTransaction);
            //--------------------------------------------------

            //-----  SEND TO GENERIC HOST -----------    GENERIC_HOST_CARD_LOAD
            request.setTransactionType(TransactionType.GENERIC_HOST_CARD_LOAD);

            try {

                if (hostName.equals(NomHost.FUZE.toString())) {
                    request.getTransactionData().put(ParameterName.BILLER_ID, response.getTransactionData().get(ParameterName.BILLER_ID));
                    request.getTransactionData().put(ParameterName.TRANSACTION_ID, transaction.getId());
                }

                try {
                    response = sendMessageToHost(request, hostName, GENERIC_CARD_LOAD_WAIT_TIME);
                } catch (Exception e) {
                    //sendEmail
                    OEDevolucion(request);
                    StringBuffer buffer2 = new StringBuffer();
                    buffer2.append("<html><head><style type=\"text/css\">body{border:0px none;text-align:center;}</style></head><body>");

                    buffer2.append("There were a problem receiving generic host card load response ************").append(" at ").append((new Date()).toString());
                    buffer2.append("</body></html>");

                    generateNotificationEmail(buffer2, null);
                    sendAnswerToTerminal(TransactionType.TECNICARD_CONFIRMATION, ResultCode.CORE_ERROR, ResultMessage.FAILED.getTerminalMessage(), hostName);
                    return;
                }

                if (!response.wasApproved()) {

                    response.setErrorCode("FAILED LOADING CARD");
                    OEDevolucion(request);
                    StringBuffer buffer = new StringBuffer();
                    buffer.append("<html><head><style type=\"text/css\">body{border:0px none;text-align:center;}</style></head><body>");

                    buffer.append("There were a problem trying to load a card ************").append(((String) request.getTransactionData().get(ParameterName.CARD_NUMBER)).substring(12)).append(" by the client with ID = ").append((String) request.getTransactionData().get(ParameterName.ID)).append(" at ").append((new Date()).toString());

                    if (hostName.equals(NomHost.TECNICARD.toString())) {
                        response.setResultCode(ResultCode.FAILED);
                        response.setResultMessage(ResultMessage.TECNICARD_FAILED.getMessage());

                        transaction.addSubTransactionList(response.getTransaction().getSub_Transaction());

                        CoreTransactionUtil.subTransactionFailed(transaction, response, jmsManager.getCoreOutQueue(), correlationId);
                        sendAnswerToTerminal(TransactionType.TECNICARD_CONFIRMATION, response.getResultCode(), ResultMessage.TECNICARD_FAILED.getTerminalMessage(), hostName);
                    } else if (hostName.equals(NomHost.FUZE.toString())) {

                        transaction.addSubTransactionList(response.getTransaction().getSub_Transaction());

                        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CoreComplexCashBL] Sending FUZE_LOOKUP_TRANSACTION ", null);
                        request.setTransactionType(TransactionType.FUZE_LOOKUP_TRANSACTION);
                        DirexTransactionResponse response2;

                        try {
                            response2 = sendMessageToHost(request, hostName, GENERIC_CARD_LOAD_WAIT_TIME);
                        } catch (Exception e) {
                            //sendEmail
//                            OEDevolucion(request);
                            StringBuffer buffer2 = new StringBuffer();
                            buffer2.append("<html><head><style type=\"text/css\">body{border:0px none;text-align:center;}</style></head><body>");

                            buffer2.append("There were a problem receiving fuze lookup transaction response ************").append(" at ").append((new Date()).toString());
                            buffer2.append("</body></html>");

                            generateNotificationEmail(buffer2, null);

                            CoreTransactionUtil.subTransactionFailed(transaction, response, jmsManager.getCoreOutQueue(), correlationId);
                            sendAnswerToTerminal(TransactionType.TECNICARD_CONFIRMATION, ResultCode.CORE_ERROR, ResultMessage.FAILED.getTerminalMessage(), hostName);
                            return;
                        }

                        response2.setResultMessage(ResultMessage.FUZE_HOST_FAILED.getMessage() + " Transaction Status from Fuze: " + response2.getTransactionData().get(ParameterName.FUZE_TRANSACTION_STATUS));
                        response2.setErrorCode("FAILED CARD LOADING" + " Transaction Status from Fuze: " + response2.getTransactionData().get(ParameterName.FUZE_TRANSACTION_STATUS));

                        transaction.addSubTransactionList(response2.getTransaction().getSub_Transaction());
                        CoreTransactionUtil.persistTransaction(transaction);
                        buffer.append(" The transaction status from Fuze is: ").append(response2.getTransactionData().get(ParameterName.FUZE_TRANSACTION_STATUS));
                        sendAnswerToTerminal(TransactionType.TECNICARD_CONFIRMATION, response.getResultCode(), ResultMessage.FUZE_HOST_FAILED.getTerminalMessage(), hostName);
                    }

                    buffer.append("</body></html>");
                    generateNotificationEmail(buffer, null);
                    return;
                } else {
                    sendAnswerToTerminal(TransactionType.TECNICARD_CONFIRMATION, response.getResultCode(), estimatedPostingTime, hostName);

                    CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreComplexCashBL] Before consuming OEPagowith OE AUTHO_NUMBER : " + responseMap.get(ParameterName.AUTHO_NUMBER), null);
                    OEPago(request);
                    CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreComplexCashBL] Reporta Pago sent. Transaction done. Success.", null);
                }
            } catch (Exception be) {

                OEDevolucion(request);

                StringBuffer buffer = new StringBuffer();
                buffer.append("<html><head><style type=\"text/css\">body{border:0px none;text-align:center;}</style></head><body>");

                buffer.append("There were a problem trying to load a card ************").append(((String) request.getTransactionData().get(ParameterName.CARD_NUMBER)).substring(12)).append(" by the client with ID = ").append((String) request.getTransactionData().get(ParameterName.ID)).append(" at ").append((new Date()).toString());

                if (hostName.equals(NomHost.FUZE.toString())) {
                    transaction.addSubTransactionList(response.getTransaction().getSub_Transaction());

                    CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CoreComplexCashBL] Sending FUZE_LOOKUP_TRANSACTION ", null);
                    request.setTransactionType(TransactionType.FUZE_LOOKUP_TRANSACTION);
                    DirexTransactionResponse response2;

                    try {
                        response2 = sendMessageToHost(request, hostName, GENERIC_CARD_LOAD_WAIT_TIME);
                    } catch (Exception e) {
                        //sendEmail
                        StringBuffer buffer2 = new StringBuffer();
                        buffer2.append("<html><head><style type=\"text/css\">body{border:0px none;text-align:center;}</style></head><body>");

                        buffer2.append("There were a problem receiving fuze lookup transaction response ************").append(" at ").append((new Date()).toString());
                        buffer2.append("</body></html>");

                        generateNotificationEmail(buffer2, null);
                        sendAnswerToTerminal(TransactionType.TECNICARD_CONFIRMATION, ResultCode.FUZE_HOST_ERROR, ResultMessage.FUZE_HOST_FAILED.getTerminalMessage(), hostName);
                        return;
                    }

                    response2.setResultMessage(ResultMessage.FUZE_HOST_FAILED.getMessage() + " Transaction Status from Fuze: " + response2.getTransactionData().get(ParameterName.FUZE_TRANSACTION_STATUS));
                    response2.setErrorCode("FAILED CARD LOADING" + " Transaction Status from Fuze: " + response2.getTransactionData().get(ParameterName.FUZE_TRANSACTION_STATUS));

                    transaction.addSubTransactionList(response2.getTransaction().getSub_Transaction());
                    sendAnswerToTerminal(TransactionType.TECNICARD_CONFIRMATION, response2.getResultCode(), estimatedPostingTime, hostName);
                    CoreTransactionUtil.persistTransaction(transaction);
                    buffer.append(" The transaction status from Fuze is: ").append(response2.getTransactionData().get(ParameterName.FUZE_TRANSACTION_STATUS));
                }

                buffer.append("</body></html>");

                generateNotificationEmail(buffer, null);

                throw be;
            }

            transaction.addSubTransactionList(response.getTransaction().getSub_Transaction());

            transaction.setResultCode(ResultCode.SUCCESS.getCode());
            transaction.setResultMessage(ResultMessage.SUCCESS.getMessage());

            CoreTransactionUtil.persistTransaction(transaction);

            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CoreComplexCashBL] Transaction finished successfully. ", null);

        } catch (Exception e) {
            e.printStackTrace();
            response = DirexTransactionResponse.forException(ResultCode.CORE_ERROR, e);

            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[NewCoreComplexTransactionBusinessLogic] Exception in state :: " + state, null);
            // SI OCURRE UNA EXCEPCION, SE LE NOTIFICA A LOS FRONTS QUE ESTEN ESPERANDO MSG.

            switch (state) {
                case 2:
                    OEDevolucion(request);
                case 1:
                    CoreTransactionUtil.subTransactionFailed(transaction, response, jmsManager.getCoreOutQueue(), correlationId);
                default:
                    transaction.setResultCode(ResultCode.CORE_ERROR.getCode());
                    String msg = e.getMessage();
                    if (msg != null) {
                        transaction.setResultMessage((msg.length() > 254) ? msg.substring(0, 254) : msg);
                    } else {
                        transaction.setResultMessage("Message Error was printed, Please check the server logs. ");
                    }

                    CoreTransactionUtil.persistTransaction(transaction);
            }

        }
    }

    private void sendAnswerToTerminal(TransactionType transactionType, ResultCode resultCode, String estimated_posting_time, String host) throws JMSException {

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CoreComplexCashBL] Send answer to TERMINAL. ", null);

        DirexTransactionResponse provissionalResponse = new DirexTransactionResponse();
        provissionalResponse.setResultCode(resultCode);
        provissionalResponse.setResultMessage(estimated_posting_time);

        Queue queue;

        if (transactionType == TransactionType.TECNICARD_CONFIRMATION) {
            if (host.equals("FUZE")) {
                provissionalResponse.getTransactionData().put(ParameterName.PRINTLOGO, "02");
            } else {
                provissionalResponse.getTransactionData().put(ParameterName.PRINTLOGO, "01");
            }
            queue = jmsManager.getCore2OutQueue();
        } else {
            queue = jmsManager.getCoreOutQueue();
        }

        provissionalResponse.getTransactionData().put("host", host);
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CoreComplexCashBL] Sending message to TERMINAL. ", null);
        JMSManager.get().send(provissionalResponse, queue, correlationId);
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CoreComplexCashBL] Sent message to TERMINAL. ", null);
    }

    private DirexTransactionResponse sendMessageToHost(DirexTransactionRequest request, String hostName, long waitTime) throws Exception, Exception {
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CoreComplexCashBL] Send message to host " + hostName, null);

        Properties props = new Properties();
        props.setProperty("hostName", hostName);
        DirexTransactionResponse direxTransactionResponse = null;

        jmsManager.sendWithProps(request, jmsManager.getHostInQueue(), correlationId, props);

        if (request.getTransactionType() != TransactionType.ISTREAM_CHECK_AUTH_SUBMIT) {
            direxTransactionResponse = receiveMessageFromHost(request.getTransactionType(), hostName, waitTime);
        }

        return direxTransactionResponse;
    }

    private DirexTransactionResponse receiveMessageFromHost(TransactionType transactionType, String hostName, long waitTime) throws Exception {

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CoreComplexCashBL] Receiving message from host " + hostName, null);

        Message message = null;
        DirexTransactionResponse response;
        try {
            message = jmsManager.receive(jmsManager.getHostOutQueue(), correlationId, waitTime);
        } catch (IOException | JMSException e) {

            response = DirexTransactionResponse.forException(transactionType, ResultCode.RESPONSE_TIME_EXCEEDED, ResultMessage.RESPONSE_TIME_EXCEEDED, " " + hostName);
            response.setTransactionType(transactionType);
            CoreTransactionUtil.subTransactionFailed(transaction, response, jmsManager.getCoreOutQueue(), correlationId);
            throw new Exception();
        }

        if (message == null || !(message instanceof ObjectMessage)) {

            response = DirexTransactionResponse.forException(transactionType, ResultCode.CORE_RECEIVED_NULL_FROM_HOST, ResultMessage.CORE_RECEIVED_NULL_FROM_HOST, " " + hostName);
            response.setTransactionType(transactionType);
            CoreTransactionUtil.subTransactionFailed(transaction, response, jmsManager.getCoreOutQueue(), correlationId);
            throw new Exception();
        }

        ObjectMessage objectMessage = (ObjectMessage) message;
        Serializable s = objectMessage.getObject();
        response = (DirexTransactionResponse) s;

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CoreComplexCashBL] Message received. ", null);
        return response;
    }

    private DirexTransactionRequest receiveMessageFromFront(TransactionType transactionType) throws Exception {

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CoreComplexCashBL] Waiting " + transactionType + " from FRONT. ", null);

        DirexTransactionResponse response;
        Message message = null;

        // in error case
        ResultCode errorCode = null;
        String correlation = null;
        Long waitTime = null;
        Queue queue = null;

        switch (transactionType) {

            case TECNICARD_CONFIRMATION:
                errorCode = ResultCode.TERMINAL_CONFIRMATION_TIME_EXCEED;
                correlation = correlationId;
                waitTime = TECNICARD_CONFIRMATION_WAIT_TIME;
                queue = jmsManager.getCore2InQueue();
                break;
        }

        try {
            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CoreComplexCashBL] Receiving... ", null);
            message = jmsManager.receive(queue, correlation, waitTime);

            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CoreComplexCashBL] Received " + transactionType, null);
        } catch (Exception e) {
            response = DirexTransactionResponse.forException(errorCode, ResultMessage.FAILED, transactionType + " not received. ", "");
            response.setTransactionType(transactionType);
            CoreTransactionUtil.subTransactionFailed(transaction, response, jmsManager.getCoreOutQueue(), correlationId);
            e.printStackTrace();
            throw new Exception();
        }

        if (message == null || !(message instanceof ObjectMessage)) {
            response = DirexTransactionResponse.forException(errorCode, ResultMessage.FAILED, transactionType + " not received. ", "");
            response.setTransactionType(transactionType);
            CoreTransactionUtil.subTransactionFailed(transaction, response, jmsManager.getCoreOutQueue(), correlationId);
            throw new Exception();
        }

        ObjectMessage objectMessage = (ObjectMessage) message;
        Serializable s = objectMessage.getObject();
        DirexTransactionRequest request = (DirexTransactionRequest) s;

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CoreComplexCashBL] Message received. ", null);
        return request;
    }

    public void postprocess(DirexTransactionRequest direxTransactionRequest, DirexTransactionResponse direxTransactionResponse) throws Exception {
    }

    private String getFromMap(Map map, ParameterName parameterName) {
        if (map.containsKey(parameterName)) {
            return (String) map.get(parameterName);
        } else {
            return null;
        }
    }

    private void fillOutClient(Map transactionMap) throws SQLException, Exception {
        if (transactionMap.containsKey(ParameterName.FIRST_NAME)) {
            transaction.getClient().setFirstName((String) transactionMap.get(ParameterName.FIRST_NAME));
        }
        if (transactionMap.containsKey(ParameterName.LAST_NAME)) {
            transaction.getClient().setLastName((String) transactionMap.get(ParameterName.LAST_NAME));
        }
        if (transactionMap.containsKey(ParameterName.TELEPHONE)) {
            transaction.getClient().setTelephone((String) transactionMap.get(ParameterName.TELEPHONE));
        }
        if (transactionMap.containsKey(ParameterName.EMAIL)) {
            transaction.getClient().setEmail((String) transactionMap.get(ParameterName.EMAIL));
        }
         if (transactionMap.containsKey(ParameterName.BORNDATE_AS_DATE)) {
                transaction.getClient().setBornDate((Date) transactionMap.get(ParameterName.BORNDATE_AS_DATE));
         }

        try {
            if (transactionMap.containsKey(ParameterName.ADDRESS_CORRECT)) {

                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreComplexCashBL] Transaction contains address correct", null);

                if (transactionMap.get(ParameterName.ADDRESS_CORRECT) != null) {

                    CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreComplexCashBL] transactionMap.get(ParameterName.ADDRESS_CORRECT) != null)", null);

                    CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreComplexCashBL] transactionMap.get(ParameterName.ADDRESS_CORRECT) = " + transactionMap.get(ParameterName.ADDRESS_CORRECT), null);

                    CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreComplexCashBL] " + ((((String) transactionMap.get(ParameterName.ADDRESS_CORRECT)).contains("n")) || ((String) transactionMap.get(ParameterName.ADDRESS_CORRECT)).contains("N")), null);

                    if (transactionMap.containsKey(ParameterName.ADDRESS_FORM) && transactionMap.get(ParameterName.ADDRESS_FORM) != null) {
                        byte[] addressForm = (byte[]) transactionMap.get(ParameterName.ADDRESS_FORM);
                        if (addressForm != null) {

                            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreComplexCashBL] addressForm != null", null);
                            if (addressForm.length > 0) {
                                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreComplexCashBL] addressForm.length > 0", null);
                                java.sql.Blob addressFormBlob = new SerialBlob(addressForm);
                                transaction.getClient().setAddressForm(addressFormBlob);
                            } else {
                                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreComplexCashBL] addressForm.length = 0", null);
                            }

                        } else {
                            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreComplexCashBL] addressForm is null", null);
                        }
                    }
                } else {
                    CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreComplexCashBL] Address correct param is null)", null);
                }
            } else {
                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreComplexCashBL] Address correct param not content", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreComplexCashBL] Exception in ADDRESS_FORM", e.getMessage());
            e.printStackTrace();
            throw new Exception();
        }

    }

    private void fillOutClientAddress(Map transactionMap) {

        if (transaction.getClient().getAddress() == null) {
            transaction.getClient().setAddress(new Address());
            transaction.getClient().getAddress().setClient(transaction.getClient());
        }

        if (transactionMap.containsKey(ParameterName.ADDRESS)) {
            transaction.getClient().getAddress().setAddress((String) transactionMap.get(ParameterName.ADDRESS));
        }
        if (transactionMap.containsKey(ParameterName.CITY)) {
            transaction.getClient().getAddress().setCity((String) transactionMap.get(ParameterName.CITY));
        }
        if (transactionMap.containsKey(ParameterName.ZIPCODE)) {
            transaction.getClient().getAddress().setZipcode((String) transactionMap.get(ParameterName.ZIPCODE));
        }
    }

    private PersonalIdentification fillOutPersonalIdentification(Map transactionMap) throws SQLException {
        PersonalIdentification identidication = new PersonalIdentification();
        identidication.setClient(transaction.getClient());

        if (transactionMap.containsKey(ParameterName.ID)) {
            identidication.setIdentification((String) transactionMap.get(ParameterName.ID));
        }
        if (transactionMap.containsKey(ParameterName.IDTYPE)) {
            identidication.setIdType(((IdType) transactionMap.get(ParameterName.IDTYPE)).getId());
        }

        if (transactionMap.containsKey(ParameterName.EXPIRATION_DATE)) {

            Date date = new Date();
            try {
                date = new SimpleDateFormat("MMddyyyy", Locale.ENGLISH).parse((String) transactionMap.get(ParameterName.EXPIRATION_DATE));
            } catch (ParseException ex) {
                Logger.getLogger(CoreComplexCashTransactionBusinessLogic.class.getName()).log(Level.SEVERE, null, ex);
            }
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreComplexCashBL] fillOutPersonalIdentification() with EXPIRATION_DATE value: " + date, null);

            identidication.setExpirationDate(date);
        }
        if (transactionMap.containsKey(ParameterName.IDFRONT) &&  transactionMap.get(ParameterName.IDFRONT) != null) {
            byte[] idFront = (byte[]) transactionMap.get(ParameterName.IDFRONT);
            java.sql.Blob idFrontBlob = new SerialBlob(idFront);
            identidication.setIdFront(idFrontBlob);
        }
        if (transactionMap.containsKey(ParameterName.IDBACK) && transactionMap.get(ParameterName.IDBACK) != null) {
            byte[] idBack = (byte[]) transactionMap.get(ParameterName.IDBACK);
            java.sql.Blob idBackBlob = new SerialBlob(idBack);
            identidication.setIdFront(idBackBlob);
        }

        return identidication;
    }

    private void fillOutCheck(Map transactionMap) throws SQLException {
        Check check = new Check();

        if (transactionMap.containsKey(ParameterName.MICR)) {
            check.setMicr((String) transactionMap.get(ParameterName.MICR));
        }
        if (transactionMap.containsKey(ParameterName.CRC)) {
            check.setCrc((String) transactionMap.get(ParameterName.CRC));
        }
        if (transactionMap.containsKey(ParameterName.MAKER_NAME)) {
            check.setMakerName((String) transactionMap.get(ParameterName.MAKER_NAME));
        }
        if (transactionMap.containsKey(ParameterName.MAKER_ADDRESS)) {
            check.setMakerAddress((String) transactionMap.get(ParameterName.MAKER_ADDRESS));
        }
        if (transactionMap.containsKey(ParameterName.MAKER_CITY)) {
            check.setMakerCity((String) transactionMap.get(ParameterName.MAKER_CITY));
        }
        if (transactionMap.containsKey(ParameterName.MAKER_STATE)) {
            check.setMakerState((String) transactionMap.get(ParameterName.MAKER_STATE));
        }
        if (transactionMap.containsKey(ParameterName.MAKER_ZIP)) {
            check.setMakerZip((String) transactionMap.get(ParameterName.MAKER_ZIP));
        }
        if (transactionMap.containsKey(ParameterName.MAKER_PHONE)) {
            check.setMakerPhone((String) transactionMap.get(ParameterName.MAKER_PHONE));
        }
        if (transactionMap.containsKey(ParameterName.LOCATION_ID)) {
            check.setLocationId((String) transactionMap.get(ParameterName.LOCATION_ID));
        }

        if (transactionMap.containsKey(ParameterName.CHECK_BACK) && transactionMap.get(ParameterName.CHECK_BACK) != null) {
            byte[] checkBack = (byte[]) transactionMap.get(ParameterName.CHECK_BACK);
            java.sql.Blob checkBackBlob = new SerialBlob(checkBack);
            check.setCheckBack(checkBackBlob);
        }

        if (transactionMap.containsKey(ParameterName.CHECK_FRONT) && transactionMap.get(ParameterName.CHECK_FRONT) != null) {
            byte[] checkFront = (byte[]) transactionMap.get(ParameterName.CHECK_FRONT);
            java.sql.Blob checkFrontBlob = new SerialBlob(checkFront);
            check.setCheckFront(checkFrontBlob);
        }

        check.setTransaction(transaction);
        check.setClient1(transaction.getClient());
        transaction.setCheck(check);
    }

    public DirexTransactionResponse getPersonalInfoFromIDReader(DirexTransactionRequest request) {

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[NewCoreComplexTransactionBusinessLogic] Into the method getPersonalInfoFromIDReader(...)", null);

        DirexTransactionResponse dtr = new DirexTransactionResponse();
        dtr.getTransactionData().putAll(request.getTransactionData());
        try {
            if (request.getTransactionData().containsKey(ParameterName.DLDATASCAN) || request.getTransactionData().containsKey(ParameterName.DLDATASWIPE)) {
                CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[NewCoreComplexTransactionBusinessLogic] getPersonalInfoFromIDReader() contains datascan or dataswipe", null);

                Map personalInfoMap = null;
                String dlData = "";

                if (request.getTransactionData().get(ParameterName.DLDATASCAN) != null && !request.getTransactionData().get(ParameterName.DLDATASCAN).equals("")) {

                    dlData = (String) request.getTransactionData().get(ParameterName.DLDATASCAN);
                    CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[NewCoreComplexTransactionBusinessLogic] getPersonalInfoFromIDReader(...) with xmlStringfrom DLDATASCAN: [" + dlData + "]", null);

                } else {

                    dlData = (String) request.getTransactionData().get(ParameterName.DLDATASWIPE);
                    CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[NewCoreComplexTransactionBusinessLogic] getPersonalInfoFromIDReader(...) with xmlString from DLDATASWIPE: [" + dlData + "]", null);

                }

               if (dlData != null && !dlData.isEmpty()) {
                    try {
                        personalInfoMap = IDScanner.parseID(CoreTransactionManager.ID_SCAN_AUTH_KEY, dlData);
                    } catch (Exception e) {
                        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[NewCoreComplexTransactionBusinessLogic] Null personInfo from DLicense WS.", null);
                        return DirexTransactionResponse.forException(ResultCode.CORE_ERROR, ResultMessage.FAILED, " Null personInfo from DLicense WS ", "");
                    }

                }

                if (personalInfoMap != null) {

                    CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[NewCoreComplexTransactionBusinessLogic] ----------------- Printing PersonInfo map  -----------------  ", null);
                    String ssn = (String)request.getTransactionData().get(ParameterName.SSN);
                    dtr.getTransactionData().put(ParameterName.IDTYPE, CoreTransactionUtil.getIdTypeFromId(ssn));
                    
                    dtr.getTransactionData().put(ParameterName.ID, personalInfoMap.get(ParameterName.ID));
                    NewCoreComplexTransactionBusinessLogic.fixPersonInfoName(personalInfoMap);
                    dtr.getTransactionData().putAll(personalInfoMap);
                    dtr.setResultCode(ResultCode.SUCCESS);
                    dtr.setResultMessage(ResultMessage.SUCCESS.getMessage());
                    dtr.setTerminalResultMessage(ResultMessage.SUCCESS.getMessage());
                } else {
                    CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[NewCoreComplexTransactionBusinessLogic] Null personInfo from DLicense WS.", null);
                    dtr = DirexTransactionResponse.forException(ResultCode.CORE_ERROR, ResultMessage.FAILED, " Null personInfo from DLicense WS ", "");
                }

            } else {
                dtr = DirexTransactionResponse.forException(ResultCode.CORE_ERROR, ResultMessage.FAILED, " The request doesn't contain DLDATASCAN or DLDATASWIPE ", "");
            }
        } catch (Exception e) {
            dtr = null;
            e.printStackTrace();
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[NewCoreComplexTransactionBusinessLogic] Error", e.getMessage());
        }

        return dtr;
    }

    public boolean isCancelated(boolean cancelable) {
        boolean isCancelated;
        try {
            HibernateUtil.beginTransaction();

            isCancelated = transactionManager.isCanceled(transaction.getRequestId(), cancelable);

            HibernateUtil.commitTransaction();

        } catch (Exception e) {
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreComplexCashBL] Error", e.getMessage());
            isCancelated = false;
        }
        return isCancelated;
    }

    public static void generateNotificationEmail(StringBuffer buffer, Map<ParameterName, ImagePart> images) throws Exception {

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CoreComplexCashBL] Sending email(...)", null);
        String receiptTitle = "SBT Middleware Warning Message";

        List<String> emailList = new ArrayList<>();

        emailList.add(System.getProperty("SEND_MAIL_GCH"));
        emailList.add(System.getProperty("SEND_MAIL_GCH2"));

        String server_address = "smtp.cbeyond.com";
        String server_port = "587";
        String server_username = "direx@smartbt.com";
        String server_password = "MiamiRocks12";
        String server_from_address = "direx@smartbt.com";

        boolean email_debug = false;

        String[] recipients = new String[emailList.size()];
        emailList.toArray(recipients);

        EmailUtils email;

        if (!server_username.isEmpty()) {
            email = new EmailUtils(server_address, server_port, server_username, server_password);
        } else {
            email = new EmailUtils(server_address, server_port);
        }

        if (images != null) {
            for (Object key : images.keySet()) {
                ImagePart img = (ImagePart) images.get(key);
                email.addImage(img);
            }
        }

        email.setMessage(buffer.toString(), "text/html");
//        email.sendEmail(recipients, server_from_address, receiptTitle, email_debug);
    }
//
//    private void feeCalculator(DirexTransactionRequest request, Transaction transaction) {
//
//        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreComplexCashBL] feeCalculator() start ...",null);
//        Double amount = (Double) request.getTransactionData().get(ParameterName.AMMOUNT);
//        Double payOut = amount;
//
//        if (request.getTransactionData().containsKey(ParameterName.HOSTNAME)) {
//
//            if (request.getTransactionData().get(ParameterName.HOSTNAME).equals("FUZE")) {
//                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreComplexCashBL] The fee applied was 4.95 for a Fuze transaction",null);
//                payOut = amount - 4.95;
//                request.getTransactionData().put(ParameterName.FEE_AMMOUNT, "4.95");
//            } else {
//                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreComplexCashBL] There's no fee applied for the Tecnicard transaction",null);
//                payOut = amount;//No se le resta ese fee a peticion de carlos aparicio dic/04/2014
//                request.getTransactionData().put(ParameterName.FEE_AMMOUNT, "3.95");
//            }
//
//        }
//
//        request.getTransactionData().put(ParameterName.PAYOUT_AMMOUNT, payOut);
//
//        transaction.setPayoutAmmount(payOut);
//        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreComplexCashBL] feeCalculator() done with payout: "+payOut,null);
//    }

    private void feeCalculator(DirexTransactionRequest request, Transaction transaction) throws Exception {

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreComplexCashBL] feeCalculator() start ...", null);
        Double amount = (Double) request.getTransactionData().get(ParameterName.AMMOUNT);
        Double payOut;

        Map map = new HashMap();

        try {
            HibernateUtil.beginTransaction();

            FeeBucketsManager feeBucketsManager = new FeeBucketsManager();
            map = (Map) feeBucketsManager.getFees(request.getTransactionData().get(ParameterName.IDMERCHANT) + "",
                    request.getTransactionData().get(ParameterName.OPERATION) + "",
                    request.getTransactionData().get(ParameterName.AMMOUNT) + "");

            HibernateUtil.commitTransaction();

            String finalFee = map.get(ParameterName.CRDLDF) + "";
            Double feeAmount = Double.parseDouble(finalFee);

//        if (request.getTransactionData().containsKey(ParameterName.HOSTNAME)) {
//
//            if (request.getTransactionData().get(ParameterName.HOSTNAME).equals("FUZE")) {
//                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreComplexCashBL] The fee applied was 4.95 for a Fuze transaction",null);
//                payOut = amount - 4.95;
//                feeAmount = "4.95";
//            } else {
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreComplexCashBL] FEE_AMOUNT applied: " + feeAmount, null);
            payOut = amount - feeAmount;//No se le resta ese fee a peticion de carlos aparicio dic/04/2014
//            }
//
//        }

            request.getTransactionData().put(ParameterName.PAYOUT_AMMOUNT, payOut);
            request.getTransactionData().put(ParameterName.FEE_AMMOUNT, feeAmount);

            transaction.setPayoutAmmount(payOut);

        } catch (Exception e) {
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreComplexCashBL] FEE Calculation error.", null);
            e.printStackTrace();
            HibernateUtil.rollbackTransaction();
            throw new Exception("Fee calculation error.");
        }
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreComplexCashBL] feeCalculator() done with PAYOUT_AMOUNT: " + payOut, null);
    }

    /**
     * *************************** commented for tests
     * only*****************************
     */
    private void OEPago(DirexTransactionRequest request) throws Exception {

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreComplexCashBL] Sending from core to OE host call REPORTAPAGO: ", null);
        DirexTransactionRequest req00 = request;
        req00.setTransactionType(TransactionType.ORDER_EXPRESS_REPORTAPAGO);
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreComplexCashBL] Sending from core to OE host call REPORTAPAGO with authonumber: " + transaction.getOrderExpressId(), null);
        req00.getTransactionData().put(ParameterName.AUTHO_NUMBER, transaction.getOrderExpressId());
        DirexTransactionResponse responseeeee;

        try {
            responseeeee = sendMessageToHost(req00, NomHost.ORDER_EXPRESS.toString(), ORDER_EXPRESS_WAIT_TIME);
        } catch (Exception e) {
            //sendEmail
            StringBuffer buffer2 = new StringBuffer();
            buffer2.append("<html><head><style type=\"text/css\">body{border:0px none;text-align:center;}</style></head><body>");

            buffer2.append("There were a problem receiving OE Pago ************").append(" at ").append((new Date()).toString());
            buffer2.append("</body></html>");

            generateNotificationEmail(buffer2, null);
            return;
        }

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreComplexCashBL] Response from method reporta pago OE OP_CODE : " + responseeeee.getTransactionData().get(ParameterName.OP_CODE), null);

    }

    private void OEDevolucion(DirexTransactionRequest request) throws Exception {

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreComplexCashBL]  Sending from core to OE host call DEVOLUCION: : ", null);
        DirexTransactionRequest req001 = request;
        req001.setTransactionType(TransactionType.ORDER_EXPRESS_DEVOLUCION);
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreComplexCashBL]  Sending from core to OE host call DEVOLUCION with authonumber: " + transaction.getOrderExpressId(), null);
        req001.getTransactionData().put(ParameterName.AUTHO_NUMBER, transaction.getOrderExpressId());
        DirexTransactionResponse responseeeee1;
        try {
            responseeeee1 = sendMessageToHost(req001, NomHost.ORDER_EXPRESS.toString(), ORDER_EXPRESS_WAIT_TIME);
        } catch (Exception e) {
            //sendEmail
            StringBuffer buffer2 = new StringBuffer();
            buffer2.append("<html><head><style type=\"text/css\">body{border:0px none;text-align:center;}</style></head><body>");

            buffer2.append("There were a problem receiving OE Devolución ************").append(" at ").append((new Date()).toString());
            buffer2.append("</body></html>");

            generateNotificationEmail(buffer2, null);
            return;
        }

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreComplexCashBL] Response from method devolucion OE OP_CODE : " + responseeeee1.getTransactionData().get(ParameterName.OP_CODE), null);
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreComplexCashBL] Response from method devolucion, TRANSACTION FINISHED. ", null);

    }

}
