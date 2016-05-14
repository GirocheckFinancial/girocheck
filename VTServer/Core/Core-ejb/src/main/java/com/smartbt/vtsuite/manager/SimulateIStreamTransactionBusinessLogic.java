///*
// ** File: CoreTransactionBusinessLogic.java
// **
// ** Date Created: April 2013
// **
// ** Copyright @ 2004-2013 Smart Business Technology, Inc.
// **
// ** All rights reserved. No part of this software may be 
// ** reproduced, transmitted, transcribed, stored in a retrieval 
// ** system, or translated into any language or computer language, 
// ** in any form or by any means, electronic, mechanical, magnetic, 
// ** optical, chemical, manual or otherwise, without the prior 
// ** written permission of Smart Business Technology, Inc.
// **
// */
//package com.smartbt.vtsuite.manager;
//
//import com.smartbt.girocheck.servercommon.email.EmailUtils;
//import com.smartbt.girocheck.servercommon.email.ImagePart;
//import com.smartbt.vtsuite.util.CoreTransactionUtil;
//import com.smartbt.girocheck.servercommon.enums.EnumCountry;
//import com.smartbt.girocheck.servercommon.enums.EnumState;
//import com.smartbt.girocheck.servercommon.jms.JMSManager;
//import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
//import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
//import com.smartbt.girocheck.servercommon.enums.ParameterName;
//import com.smartbt.girocheck.servercommon.enums.TransactionType;
//import com.smartbt.girocheck.servercommon.enums.ResultCode;
//import com.smartbt.girocheck.servercommon.enums.ResultMessage;
//import com.smartbt.girocheck.servercommon.log.LogUtil;
//import com.smartbt.girocheck.servercommon.manager.CountryManager;
//import com.smartbt.girocheck.servercommon.manager.HostManager;
//import com.smartbt.girocheck.servercommon.manager.PersonalIdentificationManager;
//import com.smartbt.girocheck.servercommon.manager.StateManager;
//import com.smartbt.girocheck.servercommon.manager.TransactionManager;
//import com.smartbt.girocheck.servercommon.messageFormat.IdType;
//import com.smartbt.girocheck.servercommon.model.Address;
//import com.smartbt.girocheck.servercommon.model.Check;
//import com.smartbt.girocheck.servercommon.model.Country;
//import com.smartbt.girocheck.servercommon.model.Host;
//import com.smartbt.girocheck.servercommon.model.PersonalIdentification;
//import com.smartbt.girocheck.servercommon.model.State;
//import com.smartbt.girocheck.servercommon.model.SubTransaction;
//import com.smartbt.girocheck.servercommon.model.Transaction;
//import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
//
//import static com.smartbt.vtsuite.manager.NewCoreComplexTransactionBusinessLogic.generateNotificationEmail;
//import com.smartbt.vtsuite.util.CoreLogger;
//import com.smartbt.vtsuite.vtcommon.nomenclators.NomHost;
//import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
//import java.io.IOException;
//import java.io.Serializable;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.sql.SQLException;
//import java.text.DateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import java.util.Properties;
//import java.util.Set;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.ejb.TransactionManagement;
//import javax.ejb.TransactionManagementType;
//import javax.jms.JMSException;
//import javax.jms.Message;
//import javax.jms.ObjectMessage;
//import javax.jms.Queue;
//import javax.sql.rowset.serial.SerialBlob;
//import javax.xml.namespace.QName;
//import org.tempuri.VerifyBarcodeService;
//import org.tempuri.VerifyBarcodeServiceSoap;
//
//@TransactionManagement(value = TransactionManagementType.BEAN)
//public class SimulateIStreamTransactionBusinessLogic extends CoreAbstractTransactionBusinessLogic {
//
//    private JMSManager jmsManager = JMSManager.get();
//    private int state = 0;
//    private String checkId;
//    private Transaction transaction;
//    private String correlationId;
////    private Host cardHost;
//    // WAITING TIMES
//    private static final long TECNICARD_CONFIRMATION_WAIT_TIME = 180000;
//    private static final long ISTREAM_HOST_WAIT_TIME = 30000;
//    private static final long PERSONAL_INFO_WAIT_TIME = 180000;
//    private static final long ORDER_EXPRESS_WAIT_TIME = 30000;
//    private static final long GENERIC_VALIDATION_WAIT_TIME = 60000;
//    private static final long GENERIC_CARD_LOAD_WAIT_TIME = 30000;
//    private static final long CERTEGY_INFO_WAIT_TIME = 240000;
//
//    private static CountryManager countryManager = new CountryManager();
//    private static TransactionManager transactionManager = new TransactionManager();
//    private static StateManager stateManager = new StateManager();
//    private PersonalIdentificationManager personalIdentificationManager = new PersonalIdentificationManager();
//
//    public SimulateIStreamTransactionBusinessLogic(CoreLogger LogUtil) {
//        super(LogUtil);
//        try {
//            String url = System.getProperty("WS_VERIFYBARCODE_URL") + "/VerifyBarcode/VerifyBarcodeService.asmx?wsdl";
//            System.out.println("URL: " +url);
//            service = new VerifyBarcodeService(new URL(url), new QName("http://tempuri.org/", "VerifyBarcodeService"));
////            service = new VerifyBarcodeService();
//        
//        } catch (MalformedURLException ex) {
//            Logger.getLogger(NewCoreComplexTransactionBusinessLogic.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        port = service.getVerifyBarcodeServiceSoap();
//        
//    }
//
//    @Override
//    public void process(DirexTransactionRequest request, Transaction transaction) throws Exception {
//        LogUtil.logAndStore("SimulateBL", "             Send answer to TERMINAL");
//
//        state = 1;
//        DirexTransactionResponse response;
//        Map responseMap;
//        TransactionType originalTransaction = request.getTransactionType();
//
//        this.transaction = transaction;
//        this.correlationId = request.getCorrelation();
//        transaction.setSingle(Boolean.FALSE);
////        cardHost = findingHost(request);
//
//        if (request.getTransactionData().containsKey(ParameterName.OPERATION)) {
//            transaction.setOperation((String) request.getTransactionData().get(ParameterName.OPERATION));
//        }
//
//        try {
//
//            //-------  go to HOST ISTREAM (checkAuth) -------
//            request.setTransactionType(TransactionType.ISTREAM_CHECK_AUTH);
//            response = sendMessageToHost(request, NomHost.ISTREAM.toString(), ISTREAM_HOST_WAIT_TIME, false);
//
//            if (!response.wasApproved()) {
//                response.setTransactionType(request.getTransactionType());
//                CoreTransactionUtil.subTransactionFailed(transaction, response, jmsManager.getCoreOutQueue(), correlationId);
//                return;
//            } else {
//                System.out.println("lista de IStream trajo " + response.getTransaction().getSub_Transaction().size());
//                transaction.addSubTransactionList(response.getTransaction().getSub_Transaction());
//            }
//
//            responseMap = response.getTransactionData();
//
//            //-----  WAIT PERSONAL INFO MESSAGE -------------- 
//            checkId = (String) responseMap.get(ParameterName.CHECK_ID);
//
//            //  DirexTransactionRequest personalInfoRequest = receiveMessageFromFront( TransactionType.PERSONAL_INFO );
//            //    Map personalInfoRequestMap = personalInfoRequest.getTransactionData();
//            double amount = (double) request.getTransactionData().get(ParameterName.AMMOUNT);
//
//            Map personalInfoRequestMap = simulatePersonalInfo(amount);
//
//            if (personalInfoRequestMap.containsKey(ParameterName.FEE_AMMOUNT)) {
//                try {
//                    String feeAmmountString = (String) personalInfoRequestMap.get(ParameterName.FEE_AMMOUNT);
//                    double feeAmmount = Double.parseDouble(feeAmmountString);
//                    transaction.setFeeAmmount(feeAmmount);
//                } catch (NumberFormatException e) {
//                }
//                try {
//                    String payoutAmmountString = (String) personalInfoRequestMap.get(ParameterName.PAYOUT_AMMOUNT);
//                    double payoutAmmount = Double.parseDouble(payoutAmmountString);
//                    transaction.setPayoutAmmount(payoutAmmount);
//                } catch (NumberFormatException e) {
//                }
//            }
//
//            request.getTransactionData().putAll(personalInfoRequestMap);
//
//            //------ CREATE PERSONAL INFO SUBTRANSACTION ------
//            SubTransaction personalInfoSubTransaction = new SubTransaction();
//            personalInfoSubTransaction.setType(TransactionType.PERSONAL_INFO.getCode());
//            personalInfoSubTransaction.setResultCode(ResultCode.SUCCESS.getCode());
//            personalInfoSubTransaction.setResultMessage(ResultMessage.SUCCESS.getMessage());
//            transaction.addSubTransaction(personalInfoSubTransaction);
//
//            //---------  ASK IF CANCELATED ------------------ (set cancelable as TRUE)
//            if (isCancelated(true)) {
//                sendResponseToIStreamFront(false, checkId);
//                response = DirexTransactionResponse.forException(ResultCode.TERMINAL_CANCELATED_TRANSACTION, ResultMessage.TERMINAL_CANCELATED_TRANSACTION);
//                response.setTransactionType(TransactionType.ISTREAM_CASH_AUTH_SUBMIT);
//                CoreTransactionUtil.subTransactionFailed(transaction, response, jmsManager.getCoreOutQueue(), correlationId);
//                return;
//            }
//
//            //--------------------------------------------------
//            state = 2;
//            //-------------------------
//            LogUtil.logAndStore("SimulateBL", "             Persist personal Info.");
//            //PERSIST PERSONAL INFO
//
//            fillOutClient(request.getTransactionData());
//            fillOutClientAddress(request.getTransactionData());
//            PersonalIdentification identification = fillOutPersonalIdentification(request.getTransactionData());
//            fillOutCheck(request.getTransactionData());
//
//            if (personalInfoRequestMap.containsKey(ParameterName.IDCOUNTRY) || personalInfoRequestMap.containsKey(ParameterName.IDSTATE)
//                    || personalInfoRequestMap.containsKey(ParameterName.COUNTRY) || personalInfoRequestMap.containsKey(ParameterName.STATE)) {
//                try {
//                    HibernateUtil.beginTransaction();
//
//                    if (identification.getIdType() != null) {
//                        personalIdentificationManager.removeByClientAndType(transaction.getClient().getId(), identification.getIdType());
//                    }
//
//                    if (personalInfoRequestMap.containsKey(ParameterName.IDCOUNTRY)) {
//                        String idCountryAbbreviation = (String) personalInfoRequestMap.get(ParameterName.IDCOUNTRY);
//                        Country country = countryManager.getByAbbreviation(idCountryAbbreviation);
//
//                        if (country != null) {
//                            request.getTransactionData().put(ParameterName.IDCOUNTRY, country.getCode() + "");
//                        } else {
//                            request.getTransactionData().put(ParameterName.IDCOUNTRY, EnumCountry.EUA.getId() + "");
//                        }
//                    }
//
//                    if (personalInfoRequestMap.containsKey(ParameterName.IDSTATE)) {
//                        String idStateAbbreviation = (String) personalInfoRequestMap.get(ParameterName.IDSTATE);
//                        State state = stateManager.getByAbbreviation(idStateAbbreviation);
//
//                        if (state != null) {
//                            request.getTransactionData().put(ParameterName.IDSTATE, state.getCode() + "");
//                            identification.setState(state);
//                        } else {
//                            request.getTransactionData().put(ParameterName.IDSTATE, EnumState.FL.getId() + "");
//                        }
//                    }
//                    if (personalInfoRequestMap.containsKey(ParameterName.COUNTRY)) {
//                        String countryAbbreviation = (String) personalInfoRequestMap.get(ParameterName.COUNTRY);
//                        Country country = countryManager.getByAbbreviation(countryAbbreviation);
//
//                        if (country != null) {
//                            request.getTransactionData().put(ParameterName.COUNTRY, country.getCode() + "");
//                            transaction.getClient().getAddress().setCountry(country);
//                        } else {
//                            request.getTransactionData().put(ParameterName.COUNTRY, EnumCountry.EUA.getId() + "");
//                        }
//                    }
//
//                    if (personalInfoRequestMap.containsKey(ParameterName.STATE)) {
//                        String stateAbbreviation = (String) personalInfoRequestMap.get(ParameterName.STATE);
//                        State state = stateManager.getByAbbreviation(stateAbbreviation);
//
//                        if (state != null) {
//                            request.getTransactionData().put(ParameterName.STATE, state.getCode() + "");
//                            transaction.getClient().getAddress().setState(state);
//                        } else {
//                            request.getTransactionData().put(ParameterName.STATE, EnumState.FL.getId() + "");
//                        }
//                    }
//
//                    HibernateUtil.commitTransaction();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    HibernateUtil.rollbackTransaction();
//                }
//            }
//
//            identification.setClient(transaction.getClient());
//            Set set = new HashSet();
//            set.add(identification);
//            transaction.getClient().setData_SD(set);
//
//            //-------SENT TO  ORDER_EXPRESS ------
//            response = sendMessageToHost(request, NomHost.ORDER_EXPRESS.toString(), ORDER_EXPRESS_WAIT_TIME, true);
//
//            if (!response.wasApproved()) {
//                sendResponseToIStreamFront(false, checkId);
//                response.setTransactionType(request.getTransactionType());
//                CoreTransactionUtil.subTransactionFailed(transaction, response, jmsManager.getCoreOutQueue(), correlationId);
//                return;
//            }
//            //---- if ORDER_EXPRESS Success --------    opCode:: 01
//            // notify to IStream
//            LogUtil.logAndStore("SimulateBL", "             ORDER_EXPRESS Success ");
//
//            transaction.addSubTransactionList(response.getTransaction().getSub_Transaction());
//
//            responseMap = response.getTransactionData();
//
//            if (responseMap.containsKey(ParameterName.AUTHO_NUMBER)) {
//                transaction.setOrderExpressId((String) responseMap.get(ParameterName.AUTHO_NUMBER));
//            }
//
//            if (responseMap.containsKey(ParameterName.IDBENEFICIARY)) {
//                transaction.getClient().setIdBeneficiary((String) responseMap.get(ParameterName.IDBENEFICIARY));
//            }
//
//            //if order express success, I ask if terminal cancelated transaction.
//            //(set cancelable as false)
//            if (isCancelated(false)) {
//                sendResponseToIStreamFront(false, checkId);
//                response = DirexTransactionResponse.forException(ResultCode.TERMINAL_CANCELATED_TRANSACTION, ResultMessage.TERMINAL_CANCELATED_TRANSACTION);
//                response.setTransactionType(originalTransaction);
//                CoreTransactionUtil.subTransactionFailed(transaction, response, jmsManager.getCoreOutQueue(), correlationId);
//                return;
//            }
//
//            //----------  TECNICARD VALIDATON ------------------
//            String hostName = (String) request.getTransactionData().get(ParameterName.HOSTNAME);
//
//            request.setTransactionType(TransactionType.GENERIC_HOST_VALIDATION);
//            request.getTransactionData().put(TransactionType.TRANSACTION_TYPE, originalTransaction);
//            response = sendMessageToHost(request, hostName, GENERIC_VALIDATION_WAIT_TIME, true);
//
//            //SI LLEGA AQUI ES QUE SIIII  SE RECIBIO EL MSG DE TECNICARD NORMALMENTE
//            LogUtil.logAndStore("SimulateBL", "             Recived message from " + hostName + " Validation");
//            LogUtil.logAndStore("SimulateBL", "             Recived message from " + hostName + " Validation with billerID: " + response.getTransactionData().get(ParameterName.BILLER_ID));
//            LogUtil.logAndStore("SimulateBL", "             Transaction type: " + response.getTransactionType());
//            for (SubTransaction object : response.getTransaction().getSub_Transaction()) {
//                LogUtil.logAndStore("SimulateBL", "             subTransactions types: " + object.getType());
//                LogUtil.logAndStore("SimulateBL", "             subTransactions ResultCode: " + object.getResultCode());
//                LogUtil.logAndStore("SimulateBL", "             subTransactions ResultMessage: " + object.getResultMessage());
//            }
//
//            transaction.addSubTransactionList(response.getTransaction().getSub_Transaction());
//
//            if (!response.wasApproved()) {
//                sendResponseToIStreamFront(false, checkId);
//                CoreTransactionUtil.subTransactionFailed(transaction, response, jmsManager.getCoreOutQueue(), correlationId);
//                return;
//            }
//
//            String estimatedPostingTime = response.getResultMessage();
//
//            sendResponseToIStreamFront(true, checkId);
//
////            DirexTransactionRequest certegyRequest = receiveMessageFromFront( TransactionType.CERTEGY_INFO );
////
////            Map certegyInfoRequestMap = certegyRequest.getTransactionData();
////
////            request.getTransactionData().putAll( certegyInfoRequestMap );
//            boolean certegySuccess = true;
////            boolean certegySuccess = false;
////            String certegyCode = "EMPTY";
////            //----
////            //Verify if Certegy success here  ---------
////            if ( certegyInfoRequestMap.containsKey( ParameterName.CERTEGY_CODE ) ) {
////                certegyCode = (String) certegyInfoRequestMap.get( ParameterName.CERTEGY_CODE );
////                if ( certegyCode.equals( "00" ) ) {
////                    certegySuccess = true;
////                }
////            }
//            //-----------------------------------------
//
//            if (certegySuccess) {
//                sendAnswerToTerminal(originalTransaction, estimatedPostingTime);
//
//                state = 3;
//
//                //------ CREATE CERTEGY INFO SUBTRANSACRTION ------
//                SubTransaction certegyInfoSubTransaction = new SubTransaction();
//                certegyInfoSubTransaction.setType(TransactionType.CERTEGY_INFO.getCode());
//                certegyInfoSubTransaction.setResultCode(ResultCode.SUCCESS.getCode());
//                certegyInfoSubTransaction.setErrorCode("certegyCode");
////                certegyInfoSubTransaction.setErrorCode( certegyCode );
//                certegyInfoSubTransaction.setResultMessage(ResultMessage.SUCCESS.getMessage());
//                transaction.addSubTransaction(certegyInfoSubTransaction);
//                //--------------------------------------------------
//
////                if ( certegyInfoRequestMap.containsKey( ParameterName.DEPOSIT_ID ) ) {
////                    String depositId = (String) certegyInfoRequestMap.get( ParameterName.DEPOSIT_ID );
////                    transaction.setIstream_id( depositId );
////                }
//            } else {
//                response.setResultCode(ResultCode.CERTEGY_DENY);
//                response.setResultMessage(ResultMessage.CERTEGY_DENY.getMessage());
//                response.setTransactionType(TransactionType.CERTEGY_INFO);
//                response.setErrorCode("certegyCode");
//                CoreTransactionUtil.subTransactionFailed(transaction, response, jmsManager.getCoreOutQueue(), correlationId);
//                return;
//            }
//
//            //-------------- WAIT CONFIRMATION FROM TERMINAL -------------
//            DirexTransactionRequest confirmationRequest;
//            try {
//             confirmationRequest = receiveMessageFromFront(TransactionType.TECNICARD_CONFIRMATION);
//            } catch (BreakException e) {
//                //sendEmail
//                
//                StringBuffer buffer = new StringBuffer();
//                buffer.append("<html><head><style type=\"text/css\">body{border:0px none;text-align:center;}</style></head><body>");
//
//                buffer.append("There were a problem trying to load a card ************" + ((String) request.getTransactionData().get(ParameterName.CARD_NUMBER)).substring(12) + " by the client with ID = " + (String) request.getTransactionData().get(ParameterName.ID) + " at " + (new Date()).toString());
//                buffer.append("</body></html>");
//                
//                generateNotificationEmail(buffer,null/*(String) request.getTransactionData().get(ParameterName.CARD_NUMBER), (String) request.getTransactionData().get(ParameterName.ID)*/);
//                throw e;
//            }
//            
//            //-------------Validate whether the check was truncated successfully.
//            validateCheckTruncation(confirmationRequest);
//
//            sendAnswerToTerminal(TransactionType.TECNICARD_CONFIRMATION, estimatedPostingTime);
//
//            extractTecnicardConfirmationInformation(confirmationRequest);
//
//            //------ CREATE TECNICARD_CONFIRMATION SUBTRANSACTION ------
//            SubTransaction tecnicardConfirmationSubTransaction = new SubTransaction();
//            tecnicardConfirmationSubTransaction.setType(TransactionType.TECNICARD_CONFIRMATION.getCode());
//            tecnicardConfirmationSubTransaction.setResultCode(ResultCode.SUCCESS.getCode());
//            tecnicardConfirmationSubTransaction.setResultMessage(ResultMessage.SUCCESS.getMessage());
//            transaction.addSubTransaction(tecnicardConfirmationSubTransaction);
//            //--------------------------------------------------
//
//            //-----  SEND TO GENERIC HOST -----------    GENERIC_HOST_CARD_LOAD
//            request.setTransactionType(TransactionType.GENERIC_HOST_CARD_LOAD);
//
//            try {
//
//                if (hostName.equals(NomHost.FUZE.toString())) {
//                    request.getTransactionData().put(ParameterName.BILLER_ID, response.getTransactionData().get(ParameterName.BILLER_ID));                    
//                    request.getTransactionData().put(ParameterName.TRANSACTION_ID, transaction.getId());
//                }
//                response = sendMessageToHost(request, hostName, GENERIC_CARD_LOAD_WAIT_TIME, false);
// 
//                LogUtil.logAndStore("SimulateBL", "             --------------------------------------------------------------------------------------" );
//                LogUtil.logAndStore("SimulateBL", "             Transaction from "+ hostName +" resultCode" + response.getTransaction().getResultCode());
//                LogUtil.logAndStore("SimulateBL", "             Transaction from "+ hostName +" ResultMessage" + response.getTransaction().getResultMessage());
//
//                if (!response.wasApproved()) {
//                    
//                StringBuffer buffer = new StringBuffer();
//                buffer.append("<html><head><style type=\"text/css\">body{border:0px none;text-align:center;}</style></head><body>");
//
//                buffer.append("There were a problem trying to load a card ************" + ((String) request.getTransactionData().get(ParameterName.CARD_NUMBER)).substring(12) + " by the client with ID = " + (String) request.getTransactionData().get(ParameterName.ID) + " at " + (new Date()).toString());
//                buffer.append("</body></html>");
//                    
//                    generateNotificationEmail(buffer,null/*(String) request.getTransactionData().get(ParameterName.CARD_NUMBER), (String) request.getTransactionData().get(ParameterName.ID)*/);
//                }
//
//            } catch (BreakException be) {
//                
//                StringBuffer buffer = new StringBuffer();
//                buffer.append("<html><head><style type=\"text/css\">body{border:0px none;text-align:center;}</style></head><body>");
//
//                buffer.append("There were a problem trying to load a card ************" + ((String) request.getTransactionData().get(ParameterName.CARD_NUMBER)).substring(12) + " by the client with ID = " + (String) request.getTransactionData().get(ParameterName.ID) + " at " + (new Date()).toString());
//                buffer.append("</body></html>");
//                
//                generateNotificationEmail(buffer,null/*(String) request.getTransactionData().get(ParameterName.CARD_NUMBER), (String) request.getTransactionData().get(ParameterName.ID)*/);
//                throw be;
//            }
//
//            LogUtil.logAndStore("SimulateBL", "             Recived message from " + hostName + " GENERIC_CARD_LOAD");
//            LogUtil.logAndStore("SimulateBL", "             Recived message from " + hostName + " GENERIC_CARD_LOAD with PENDING_BALANCE: " + response.getTransactionData().get(ParameterName.PENDING_BALANCE));
//            LogUtil.logAndStore("SimulateBL", "             Transaction type: " + response.getTransactionType());
//            for (SubTransaction object : response.getTransaction().getSub_Transaction()) {
//                LogUtil.logAndStore("SimulateBL", "             subTransactions types: " + object.getType());
//                LogUtil.logAndStore("SimulateBL", "             subTransactions ResultCode: " + object.getResultCode());
//                LogUtil.logAndStore("SimulateBL", "             subTransactions ResultMessage: " + object.getResultMessage());
//            }
//
//            transaction.addSubTransactionList(response.getTransaction().getSub_Transaction());
//
//            transaction.setResultCode(response.getTransaction().getResultCode());
//            LogUtil.logAndStore("SimulateBL", "             Transaction from "+ hostName +" resultCode" + response.getTransaction().getResultCode());
//            transaction.setResultMessage(response.getTransaction().getResultMessage());
//            LogUtil.logAndStore("SimulateBL", "             Transaction from "+ hostName +" ResultMessage" + response.getTransaction().getResultMessage());
//
//            CoreTransactionUtil.persistTransaction(transaction);
//
//            LogUtil.logAndStore("SimulateBL", "             Transaction finished successfuly.");
//            return;
//
//        } catch (BreakException breakException) {
//            //Esta Excepcion solo es usada para interrumpir el flujo, por lo que no se hace nada.
//            return;
//        } catch (Exception e) {
//            response = DirexTransactionResponse.forException(ResultCode.CORE_ERROR, e);
//            System.out.println("Final Exception :: ");
//            e.printStackTrace();
//            //persist end of the transaction here.
//
//            LogUtil.logAndStore("SimulateBL", "             Exception in state :: " + state);
//            // SI OCURRE UNA EXCEPCION, SE LE NOTIFICA A LOS FRONTS QUE ESTEN ESPERANDO MSG.
//
//            switch (state) {
//                case 2:
//                    sendResponseToIStreamFront(false, checkId);
//                case 1:
//                    CoreTransactionUtil.subTransactionFailed(transaction, response, jmsManager.getCoreOutQueue(), correlationId);
//                default:
//                    transaction.setResultCode(ResultCode.CORE_ERROR.getCode());
//                    String msg = e.getMessage();
//                    transaction.setResultMessage((msg.length() > 254) ? msg.substring(0, 254) : msg);
//
//                    CoreTransactionUtil.persistTransaction(transaction);
//            }
//
//        }
//    }
//
//    private void sendAnswerToTerminal(TransactionType transactionType, String estimated_posting_time) throws JMSException {
//        LogUtil.logAndStore("SimulateBL", "             Send answer to TERMINAL");
//
//        DirexTransactionResponse provissionalResponse = new DirexTransactionResponse();
//        provissionalResponse.setResultCode(ResultCode.SUCCESS);
//        provissionalResponse.setResultMessage(estimated_posting_time);
//
//        Queue queue = null;
//
//        if (transactionType == TransactionType.TECNICARD_CONFIRMATION) {
//            queue = jmsManager.getCore2OutQueue();
//        } else {
//            queue = jmsManager.getCoreOutQueue();
//        }
//
//        LogUtil.logAndStore("SimulateBL", "             Send certegy success message to TERMINAL *******");
//        JMSManager.get().send(provissionalResponse, queue, correlationId);
//    }
//
//    private DirexTransactionResponse sendMessageToHost(DirexTransactionRequest request, String hostName, long waitTime, boolean notifyToIstream) throws JMSException, BreakException, Exception {
//        LogUtil.logAndStore("SimulateBL", "             Send message to host" + hostName);
//
//        Properties props = new Properties();
//        props.setProperty("hostName", hostName);
//
//        jmsManager.sendWithProps(request, jmsManager.getHostInQueue(), correlationId, props);
//
//        return receiveMessageFromHost(request.getTransactionType(), hostName, waitTime, notifyToIstream);
//    }
//
//    private DirexTransactionResponse receiveMessageFromHost(TransactionType transactionType, String hostName, long waitTime, boolean notifyToIstream) throws BreakException, Exception {
//        LogUtil.logAndStore("SimulateBL", "             Receiving message from host" + hostName);
//        LogUtil.logAndStore("SimulateBL", "             Receiving message from host" + hostName + " with transaction type: " + transactionType.toString());
//
//        Message message = null;
//        DirexTransactionResponse response;
//        try {
//            message = jmsManager.receive(jmsManager.getHostOutQueue(), correlationId, waitTime);
//        } catch (Exception e) {
//            if (notifyToIstream) {
//                sendResponseToIStreamFront(false, checkId);
//            }
//
//            response = DirexTransactionResponse.forException(transactionType, ResultCode.RESPONSE_TIME_EXCEEDED, ResultMessage.RESPONSE_TIME_EXCEEDED, " "+hostName);
//            response.setTransactionType(transactionType);
//            CoreTransactionUtil.subTransactionFailed(transaction, response, jmsManager.getCoreOutQueue(), correlationId);
//            throw new BreakException();
//        }
//
//        if (message == null || !(message instanceof ObjectMessage)) {
//            if (notifyToIstream) {
//                sendResponseToIStreamFront(false, checkId);
//            }
//
//            response = DirexTransactionResponse.forException(transactionType, ResultCode.CORE_RECEIVED_NULL_FROM_HOST, ResultMessage.CORE_RECEIVED_NULL_FROM_HOST, " "+hostName);
//            response.setTransactionType(transactionType);
//            CoreTransactionUtil.subTransactionFailed(transaction, response, jmsManager.getCoreOutQueue(), correlationId);
//            throw new BreakException();
//        }
//
//        ObjectMessage objectMessage = (ObjectMessage) message;
//        Serializable s = objectMessage.getObject();
//        response = (DirexTransactionResponse) s;
//
//        LogUtil.logAndStore("SimulateBL", "           Message received!");
//        LogUtil.logAndStore("SimulateBL", "           Message received! with transaction type: " + response.getTransactionType());
//        LogUtil.logAndStore("SimulateBL", "           Message received! with resultCode: " + response.getResultCode());
//        return response;
//    }
//
//    private DirexTransactionRequest receiveMessageFromFront(TransactionType transactionType) throws BreakException, Exception {
//        LogUtil.logAndStore("SimulateBL", "             ___Waiting " + transactionType + "from IStream FRONT");
//
//        DirexTransactionResponse response;
//        Message message;
//
//        // in error case
//        ResultCode errorCode = null;
//        String correlation = null;
//        Long waitTime = null;
//        Queue queue = null;
//
//        switch (transactionType) {
//            case PERSONAL_INFO:
//                errorCode = ResultCode.ISTREAM_FRONT_PERSONAL_INFO_NOT_RECEIVED;
//                correlation = checkId;
//                waitTime = PERSONAL_INFO_WAIT_TIME;
//                queue = jmsManager.getFrontIStreamOutQueue();
//                break;
//            case CERTEGY_INFO:
//                errorCode = ResultCode.ISTREAM_FRONT_CERTEGY_INFO_NOT_RECEIVED;
//                correlation = checkId;
//                waitTime = CERTEGY_INFO_WAIT_TIME;
//                queue = jmsManager.getFrontIStreamOutQueue();
//                break;
//            case TECNICARD_CONFIRMATION:
//                errorCode = ResultCode.TERMINAL_CONFIRMATION_TIME_EXCEED;
//                correlation = correlationId;
//                waitTime = TECNICARD_CONFIRMATION_WAIT_TIME;
//                queue = jmsManager.getCore2InQueue();
//                break;
//        }
//
//        try {
//            message = jmsManager.receive(queue, correlation, waitTime);
//            LogUtil.logAndStore("SimulateBL", "             Recived " + transactionType);
//        } catch (JMSException e) {
//            response = DirexTransactionResponse.forException(errorCode, ResultMessage.FAILED, transactionType + " not received.","");
//            response.setTransactionType(transactionType);
//            CoreTransactionUtil.subTransactionFailed(transaction, response, jmsManager.getCoreOutQueue(), correlationId);
//            throw new BreakException();
//        }
//
//        //  DirexTransactionRequest personalInfoRequest;
//        if (message == null || !(message instanceof ObjectMessage)) {
//            response = DirexTransactionResponse.forException(errorCode, ResultMessage.FAILED, transactionType + " not received.","");
//            response.setTransactionType(transactionType);
//            CoreTransactionUtil.subTransactionFailed(transaction, response, jmsManager.getCoreOutQueue(), correlationId);
//            throw new BreakException();
//        }
//
//        ObjectMessage objectMessage = (ObjectMessage) message;
//        Serializable s = objectMessage.getObject();
//        DirexTransactionRequest request = (DirexTransactionRequest) s;
//
//        LogUtil.logAndStore("SimulateBL", "           Message received!");
//        return request;
//    }
//
//    private void sendResponseToIStreamFront(boolean success, String checkId) throws JMSException {
//        LogUtil.logAndStore("SimulateBL", "Send response back to IStreamFront :: " + (success ? "SUCCESS" : "FAILED"));
//
//        DirexTransactionResponse iStreamResponse = new DirexTransactionResponse();
//        iStreamResponse.getTransactionData().put(ParameterName.CHECK_ID, checkId);
//
//        iStreamResponse.setResultCode(success ? ResultCode.SUCCESS : ResultCode.FAILED);
//        iStreamResponse.setResultMessage(success ? ResultMessage.SUCCESS.getMessage() : ResultMessage.FAILED.getMessage());
//        iStreamResponse.setApproved(success);
//
//        //  jmsManager.send( iStreamResponse, jmsManager.getFrontIStreamInQueue(), checkId );
//    }
//
//    public void postprocess(DirexTransactionRequest direxTransactionRequest, DirexTransactionResponse direxTransactionResponse) throws Exception {
//
//    }
//
//    private String getFromMap(Map map, ParameterName parameterName) {
//        if (map.containsKey(parameterName)) {
//            return (String) map.get(parameterName);
//        } else {
//            return null;
//        }
//    }
//
//    private void fillOutClient(Map transactionMap) throws SQLException {
//        if (transactionMap.containsKey(ParameterName.FIRST_NAME)) {
//            transaction.getClient().setFirstName((String) transactionMap.get(ParameterName.FIRST_NAME));
//        }
//        if (transactionMap.containsKey(ParameterName.LAST_NAME)) {
//            transaction.getClient().setLastName((String) transactionMap.get(ParameterName.LAST_NAME));
//        }
//        if (transactionMap.containsKey(ParameterName.TELEPHONE)) {
//            transaction.getClient().setTelephone((String) transactionMap.get(ParameterName.TELEPHONE));
//        }
//        if (transactionMap.containsKey(ParameterName.EMAIL)) {
//            transaction.getClient().setEmail((String) transactionMap.get(ParameterName.EMAIL));
//        }
//        if (transactionMap.containsKey(ParameterName.BORNDATE)) {
//            transaction.getClient().setBornDate((Date) transactionMap.get(ParameterName.BORNDATE));
//        }
//
//        try {
//            if (transactionMap.containsKey(ParameterName.ADDRESS_CORRECT) && transactionMap.get(ParameterName.ADDRESS_CORRECT) != null && (((String) transactionMap.get(ParameterName.ADDRESS_CORRECT)).contains("n")) || ((String) transactionMap.get(ParameterName.ADDRESS_CORRECT)).contains("N")) {
//                LogUtil.logAndStore("fillOutClient", "ADDRESS_CORRECT = " + transactionMap.get(ParameterName.ADDRESS_CORRECT));
//                if (transactionMap.containsKey(ParameterName.ADDRESS_FORM) && transactionMap.get(ParameterName.ADDRESS_FORM) != null) {
//                    byte[] addressForm = (byte[]) transactionMap.get(ParameterName.ADDRESS_FORM);
//                    if (addressForm != null) {
//                        LogUtil.logAndStore("fillOutClient", "addressForm != null");
//                        if (addressForm.length > 0) {
//                            LogUtil.logAndStore("fillOutClient", " addressForm.length > 0");
//                            java.sql.Blob addressFormBlob = new SerialBlob(addressForm);
//                            transaction.getClient().setAddressForm(addressFormBlob);
//                        } else {
//                            LogUtil.logAndStore("fillOutClient", " addressForm.length = 0");
//                        }
//
//                    } else {
//                        LogUtil.logAndStore("fillOutClient", "addressForm is null");
//                    }
//                }
//            }
//        } catch (Exception e) {
//            LogUtil.logAndStore("fillOutClient", "Exception in ADDRESS_FORM------------------------------------");
//        }
//
//    }
//
//    private void fillOutClientAddress(Map transactionMap) {
//
//        if (transaction.getClient().getAddress() == null) {
//            transaction.getClient().setAddress(new Address());
//            transaction.getClient().getAddress().setClient(transaction.getClient());
//        }
//
//        if (transactionMap.containsKey(ParameterName.ADDRESS)) {
//            transaction.getClient().getAddress().setAddress((String) transactionMap.get(ParameterName.ADDRESS));
//        }
//        if (transactionMap.containsKey(ParameterName.CITY)) {
//            transaction.getClient().getAddress().setCity((String) transactionMap.get(ParameterName.CITY));
//        }
//        if (transactionMap.containsKey(ParameterName.ZIPCODE)) {
//            transaction.getClient().getAddress().setZipcode((String) transactionMap.get(ParameterName.ZIPCODE));
//        }
//    }
//
//    private PersonalIdentification fillOutPersonalIdentification(Map transactionMap) throws SQLException {
//        PersonalIdentification identidication = new PersonalIdentification();
//        identidication.setClient(transaction.getClient());
//
//        if (transactionMap.containsKey(ParameterName.ID)) {
//            identidication.setIdentification((String) transactionMap.get(ParameterName.ID));
//        }
//        if (transactionMap.containsKey(ParameterName.IDTYPE)) {
//            identidication.setIdType(((IdType) transactionMap.get(ParameterName.IDTYPE)).getId());
//        }
//
//        if (transactionMap.containsKey(ParameterName.EXPIRATION_DATE)) {
//            identidication.setExpirationDate(((Date) transactionMap.get(ParameterName.EXPIRATION_DATE)));
//        }
//        if (transactionMap.containsKey(ParameterName.IDFRONT)) {
//            byte[] idFront = (byte[]) transactionMap.get(ParameterName.IDFRONT);
//            java.sql.Blob idFrontBlob = new SerialBlob(idFront);
//            identidication.setIdFront(idFrontBlob);
//        }
//        if (transactionMap.containsKey(ParameterName.IDBACK)) {
//            byte[] idBack = (byte[]) transactionMap.get(ParameterName.IDBACK);
//            java.sql.Blob idBackBlob = new SerialBlob(idBack);
//            identidication.setIdFront(idBackBlob);
//        }
//
//        return identidication;
//    }
//
//    private void fillOutCheck(Map transactionMap) throws SQLException {
//        Check check = new Check();
//
//        if (transactionMap.containsKey(ParameterName.MICR)) {
//            check.setMicr((String) transactionMap.get(ParameterName.MICR));
//        }
//        if (transactionMap.containsKey(ParameterName.CRC)) {
//            check.setCrc((String) transactionMap.get(ParameterName.CRC));
//        }
//        if (transactionMap.containsKey(ParameterName.MAKER_NAME)) {
//            check.setMakerName((String) transactionMap.get(ParameterName.MAKER_NAME));
//        }
//        if (transactionMap.containsKey(ParameterName.MAKER_ADDRESS)) {
//            check.setMakerAddress((String) transactionMap.get(ParameterName.MAKER_ADDRESS));
//        }
//        if (transactionMap.containsKey(ParameterName.MAKER_CITY)) {
//            check.setMakerCity((String) transactionMap.get(ParameterName.MAKER_CITY));
//        }
//        if (transactionMap.containsKey(ParameterName.MAKER_STATE)) {
//            check.setMakerState((String) transactionMap.get(ParameterName.MAKER_STATE));
//        }
//        if (transactionMap.containsKey(ParameterName.MAKER_ZIP)) {
//            check.setMakerZip((String) transactionMap.get(ParameterName.MAKER_ZIP));
//        }
//        if (transactionMap.containsKey(ParameterName.MAKER_PHONE)) {
//            check.setMakerPhone((String) transactionMap.get(ParameterName.MAKER_PHONE));
//        }
//        if (transactionMap.containsKey(ParameterName.LOCATION_ID)) {
//            check.setLocationId((String) transactionMap.get(ParameterName.LOCATION_ID));
//        }
//
//        if (transactionMap.containsKey(ParameterName.CHECK_BACK) && transactionMap.get(ParameterName.CHECK_BACK) != null) {
//            byte[] checkBack = (byte[]) transactionMap.get(ParameterName.CHECK_BACK);
//            java.sql.Blob checkBackBlob = new SerialBlob(checkBack);
//            check.setCheckBack(checkBackBlob);
//        }
//
//        if (transactionMap.containsKey(ParameterName.CHECK_FRONT) && transactionMap.get(ParameterName.CHECK_FRONT) != null) {
//            byte[] checkFront = (byte[]) transactionMap.get(ParameterName.CHECK_FRONT);
//            java.sql.Blob checkFrontBlob = new SerialBlob(checkFront);
//            check.setCheckFront(checkFrontBlob);
//        }
//
//        check.setTransaction(transaction);
//        check.setClient1(transaction.getClient());
//        transaction.setCheck(check);
//    }
//
////    private boolean validateCreditCard( TransactionType transactionType, String cardnumber, String ssn ) throws DirexException {
////        if ( transactionType == TransactionType.NEW_CARD_LOAD ) {
////            return creditCardManager.validateNewCard( cardnumber );
////        } else {
////            if ( transactionType == TransactionType.CARD_RELOAD ) {
////                return creditCardManager.validateExistentCard( cardnumber, ssn );
////            } else {
////                return true;
////            }
////        }
////    }
//    private void extractTecnicardConfirmationInformation(DirexTransactionRequest request) throws JMSException, SQLException {
//        Map transactionData = request.getTransactionData();
//
//        if (transactionData.containsKey(ParameterName.TRUNCATED_CHECK) && transactionData.get(ParameterName.TRUNCATED_CHECK) != null) {
//            byte[] truncatedCheck = (byte[]) transactionData.get(ParameterName.TRUNCATED_CHECK);
//            if (truncatedCheck != null) {
//                LogUtil.logAndStore("extractTecnicardConfirmationInformation", "truncatedCheck != null");
//                java.sql.Blob truncatedCheckBlob = new SerialBlob(truncatedCheck);
//                transaction.setTruncatedCheck(truncatedCheckBlob);
//            } else {
//                LogUtil.logAndStore("extractTecnicardConfirmationInformation", "truncatedCheck IS null");
//            }
//        } else {
//            LogUtil.logAndStore("extractTecnicardConfirmationInformation", "truncatedCheck IS null");
//        }
//    }
//    
//    private VerifyBarcodeService service;
//    private VerifyBarcodeServiceSoap port;
//    
//    private void validateCheckTruncation(DirexTransactionRequest request){
//
//        Map transactionData = request.getTransactionData();
//        
//        if (transactionData.containsKey(ParameterName.TRUNCATED_CHECK) && transactionData.get(ParameterName.TRUNCATED_CHECK) != null) {
//            System.out.println("validateCheckTruncation() into the if()");
//            byte[] truncatedCheck = (byte[]) transactionData.get(ParameterName.TRUNCATED_CHECK);
//            String imageAsBase64="";
//            Map<ParameterName, ImagePart> images = new HashMap<ParameterName, ImagePart>();
//        try{
//            imageAsBase64 = Base64.encode( truncatedCheck );
//        }catch(Exception e){
//            imageAsBase64 = (String) transactionData.get(ParameterName.TRUNCATED_CHECK);
//        }
//
//            ImagePart truncatedCheckk = new ImagePart( imageAsBase64, ParameterName.TRUNCATED_CHECK.toString(), "truncatedCheck" );
//            images.put( ParameterName.TRUNCATED_CHECK, truncatedCheckk );
//
//            //call the WS to validate the truncatedCheck.
//            String barCodeResult="";
//            try{
//                barCodeResult = port.validate(imageAsBase64, "21");
//                System.out.println(" validateCheckTruncation value : "+ barCodeResult);
//                if(!Boolean.getBoolean(barCodeResult)){
//
//                    StringBuffer buffer = new StringBuffer();
//                    buffer.append("<html><head><style type=\"text/css\">body{border:0px none;text-align:center;}</style></head><body>");
//
//                    buffer.append("The barcode from the truncated check would not been read at " + (new Date()).toString());
//                    buffer.append("</body></html>");
//
//                    try {
//                        generateNotificationEmail(buffer,images);
//                    } catch (Exception ex) {
//                        Logger.getLogger(NewCoreComplexTransactionBusinessLogic.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
//            }catch(Exception e){
//                System.out.println(" validateCheckTruncation ERROR at " + (new Date()).toString());
//
//                    StringBuffer buffer = new StringBuffer();
//                    buffer.append("<html><head><style type=\"text/css\">body{border:0px none;text-align:center;}</style></head><body>");
//
//                    buffer.append("The barcode from the truncated check would not been read at " + (new Date()).toString());
//                    buffer.append("</body></html>");
//
//                    try {
//                        generateNotificationEmail(buffer,images);
//                    } catch (Exception ex) {
//                        Logger.getLogger(NewCoreComplexTransactionBusinessLogic.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//
//            }
//            
//        } else {
//            coreLogger.logAndStore("extractTecnicardConfirmationInformation", "truncatedCheck IS null");
//        }
//
//    }
//
//    public boolean isCancelated(boolean cancelable) {
//        boolean isCancelated;
//        try {
//            HibernateUtil.beginTransaction();
//
//            isCancelated = transactionManager.isCanceled(transaction.getRequestId(), cancelable);
//
//            HibernateUtil.commitTransaction();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            isCancelated = false;
//        }
//        return isCancelated;
//    }
//
//    public Map simulatePersonalInfo(double ammount) {
//        Map map = new HashMap();
//
//        map.put(TransactionType.TRANSACTION_TYPE, TransactionType.PERSONAL_INFO);
//
//        map.put(ParameterName.USER, "sbt");
//        map.put(ParameterName.PASSWORD, "sbt");
//
//        map.put(ParameterName.CHECK_ID, "123456789");
//        map.put(ParameterName.ID, "112223333");
//        map.put(ParameterName.IDTYPE, IdType.SSN);
//        map.put(ParameterName.TELEPHONE, "7864540209");
//        map.put(ParameterName.EMAIL, "girocheck@cardmarte.com");
//        map.put(ParameterName.ADDRESS, "222333 PEACHTREE PLACE");
//        map.put(ParameterName.CITY, "Atlanta");
//        map.put(ParameterName.STATE, "11");
//        map.put(ParameterName.IDSTATE, "");
//        map.put(ParameterName.ZIPCODE, "30318");
//        map.put(ParameterName.COUNTRY, "USA");
//        map.put(ParameterName.IDCOUNTRY, "840");
//        map.put(ParameterName.FIRST_NAME, "John");
//        map.put(ParameterName.LAST_NAME, "Smith");
//
//        Date date = new Date(1988, 01, 02);
//        Date d = new Date();
//        map.put(ParameterName.BORNDATE, date);
//        map.put(ParameterName.EXPIRATION_DATE, d);
//
//        map.put(ParameterName.MICR, "");
//        map.put(ParameterName.MAKER_NAME, "");
//        map.put(ParameterName.MAKER_CITY, "");
//        map.put(ParameterName.MAKER_STATE, "");
//        map.put(ParameterName.MAKER_ZIP, "");
//        map.put(ParameterName.MAKER_PHONE, "");
//        map.put(ParameterName.MAKER_ADDRESS, "");
//
//        map.put(ParameterName.FEE_AMMOUNT, calcFeeAmmount(ammount) + "");
//        map.put(ParameterName.PAYOUT_AMMOUNT, (ammount - calcFeeAmmount(ammount)) + "");
//
//        map.put(ParameterName.LOCATION_ID, "");
//        map.put(ParameterName.PAYMENTCHECK, "");
//        return map;
//    }
//
//    public double calcFeeAmmount(double ammount) {
//        return (ammount * 1.5) / 100;
//    }
//
//    public static void generateNotificationEmail(/*String cardNumber, String ID*/StringBuffer buffer, Map<ParameterName, ImagePart> images) throws Exception {
//        System.out.println("Sending email***********");
//        String receiptTitle = "SBT Middleware Warning Message";
//
//        List<String> emailList = new ArrayList<String>();
//
//        emailList.add("caparicio@girocheck.com");
//        emailList.add("alejandro.leandro@smartbt.com");
//        emailList.add("girochecktest2@gmail.com");
//
//        //    TransactionValidator.sendTheEmail(imageAsBase64, emailList);
//        String server_address = "smtp.cbeyond.com";
//        String server_port = "587";
//        String server_username = "direx@smartbt.com";
//        String server_password = "MiamiRocks12";
//        String server_from_address = "direx@smartbt.com";
//
//        boolean email_debug = false;
//
//        String[] recipients = new String[emailList.size()];
//        emailList.toArray(recipients);
//
//        EmailUtils email;
//
//        if (server_username != null && !server_username.isEmpty()) {
//            email = new EmailUtils(server_address, server_port, server_username, server_password);
//        } else {
//            email = new EmailUtils(server_address, server_port);
//        }
//
////        StringBuffer buffer = new StringBuffer();
////        buffer.append("<html><head><style type=\"text/css\">body{border:0px none;text-align:center;}</style></head><body>");
////
////        buffer.append("There were a problem trying to load a card ************" + cardNumber.substring(12) + " by the client with ID = " + ID + " at " + (new Date()).toString());
////        buffer.append("</body></html>");
//        
//        if ( images != null ) {
//            for ( Object key : images.keySet() ) {
//                ImagePart img = (ImagePart) images.get( key );
//                email.addImage( img );
//            }
//        }
//
//        email.setMessage(buffer.toString(), "text/html");
//        email.sendEmail(recipients, server_from_address, receiptTitle, email_debug);
//    }
//
////    public Host findingHost(DirexTransactionRequest request) {
////        
////        Host host = new Host();
////
////        LogUtil.logAndStore("NewCoreComplexTransactionBusinessLogic", "findingHost(DirexTransactionRequest request");
////
////        Map transactionData = request.getTransactionData();
////
////        if (transactionData.containsKey(ParameterName.CARD_NUMBER)) {
////
////            String cardNumber = (String) transactionData.get(ParameterName.CARD_NUMBER);
////
////            String binNumber = cardNumber.substring(0, 6);
////
////            try {
////                HibernateUtil.beginTransaction();
////
////                host = hostManager.getHostByBinNumber(binNumber);
////
////                HibernateUtil.commitTransaction();
////
////            } catch (Exception e) {
////                e.printStackTrace();
////            }
////
////        }
////
////        LogUtil.logAndStore("NewCoreComplexTransactionBusinessLogic", "Finding Host result: " + host.getHostName());
////        return host;
////
////    }
//}
//
////class TecnicardNotRespondException extends Exception {
////}
////class BreakException extends Exception {
////}
