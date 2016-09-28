/*
 ** File: TransactionManager.java
 **
 ** Date Created: March 2013
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
package com.smartbt.vtsuite.servercommon.manager;

import com.smartbt.vtsuite.common.VTSuiteMessages;
import com.smartbt.vtsuite.servercommon.dao.MerchantDAO;
import com.smartbt.girocheck.servercommon.display.message.BaseResponse;
import com.smartbt.girocheck.servercommon.display.message.ResponseData;
import com.smartbt.girocheck.servercommon.display.message.ResponseDataList;
import com.smartbt.vtsuite.servercommon.display.common.model.MerchantParameterDisplay;
import com.smartbt.vtsuite.servercommon.display.common.model.TerminalParameterDisplay;
import com.smartbt.vtsuite.servercommon.display.common.model.TransactionDisplay;
import com.smartbt.vtsuite.servercommon.dao.ParameterValueDAO;
import com.smartbt.vtsuite.servercommon.dao.SystemPropertyDAO;
import com.smartbt.vtsuite.servercommon.dao.TransactionDAO;
import com.smartbt.vtsuite.servercommon.dao.UtilsDAO.TransactionUtilsDAO;
import com.smartbt.vtsuite.servercommon.display.common.model.MerchantDisplay;
import com.smartbt.girocheck.servercommon.email.EmailUtils;
import com.smartbt.vtsuite.servercommon.model.Merchant;
import com.smartbt.vtsuite.servercommon.model.MerchantParameterValue;
import com.smartbt.vtsuite.servercommon.model.TerminalParameterValue;
import com.smartbt.vtsuite.vtcommon.Constants;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartbt.vtsuite.servercommon.utils.receipt.ReceiptGenerator;
import com.smartbt.vtsuite.servercommon.validators.TransactionValidator;
import com.smartbt.vtsuite.vtcommon.Messages;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomApplication;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomMerchantParameter;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomOperation;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomSystemProperties;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomTerminalParameter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Ariel Saavedra
 */
public class TransactionManager {

    private static final Logger log = Logger.getLogger(TransactionManager.class);
    private TransactionDAO transactionDAO = TransactionDAO.get();
    private MerchantDAO merchantDAO = MerchantDAO.get();

    private TransactionUtilsDAO transactionUtilsDAO = new TransactionUtilsDAO();

    /**
     * Get all the Transactions mode
     *
     * @return
     * @throws java.lang.Exception
     */
    public ResponseDataList getTransactionModes() throws Exception {
        TransactionValidator.getTransactionModes();
        ResponseDataList response = new ResponseDataList();
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
        response.setData(transactionDAO.getTransactionModes());
        return response;
    }

    /**
     * Get the Transaction by a given id
     *
     * @param id
     * @param operation
     * @return
     * @throws java.lang.Exception
     */
    public ResponseData getTransaction(int id, NomOperation operation) throws Exception {
        TransactionValidator.getTransaction(id, operation);
        ResponseData response = new ResponseData();

        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
        response.setData(transactionDAO.getTransaction(id, operation));

        return response;
    }

    /**
     * Get a Transaction Receipt
     *
     * @param transaction
     * @param id
     * @return
     * @throws java.lang.Exception
     */
    public ResponseData getTransactionReceipt(TransactionDisplay transaction) throws Exception {
        TransactionValidator.getTransactionReceipt(transaction.getId());
        ResponseData response = new ResponseData();

        // Get the receipt just for APPROVED transactions
        if (transaction.isApproved()) {
            MerchantDisplay merchantDisplay = merchantDAO.getMerchant(transaction.getMerchant().getId());
            List merchantParameterValuesList = ParameterValueDAO.get().searchParametersValue(transaction.getMerchant().getId(),
                    EntityType.MERCHANT, null, -1, -1);
            MerchantParameterDisplay merchantParameterValues = getMerchantParameterValues(merchantParameterValuesList);

            List terminalParameterValuesList = ParameterValueDAO.get().searchParametersValue(transaction.getTerminal().getId(),
                    EntityType.TERMINAL, null, -1, -1);
            TerminalParameterDisplay terminalParameterValues = getTerminalParameterValues(terminalParameterValuesList);

            ReceiptGenerator receiptGenerator = new ReceiptGenerator(transaction, merchantParameterValues, terminalParameterValues, merchantDisplay);

            response.setData(receiptGenerator.getReceiptInfo());
        }
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);

        return response;
    }

    public ResponseData getTransactionReceipt(int idTransaction) throws Exception {
        TransactionDisplay transaction = transactionDAO.getTransaction(idTransaction, null);
        return getTransactionReceipt(transaction);
    }

    public ResponseDataList searchTransactions(int idEntity, String searchFilter, EntityType entityType, Date startRangeDate, Date endRangeDate,
            int pageNumber, int rowsPerPage, NomApplication application) throws Exception {
        TransactionValidator.searchTransactions(idEntity, searchFilter, entityType, startRangeDate, endRangeDate, pageNumber, rowsPerPage, application);
        ResponseDataList response = new ResponseDataList();

        response.setData(transactionDAO.searchTransactions(idEntity, searchFilter, entityType, startRangeDate, endRangeDate, pageNumber * rowsPerPage, rowsPerPage, application));

        int total = transactionDAO.searchTransactions(idEntity, searchFilter, entityType, startRangeDate, endRangeDate, -1, -1, application).size();
        response.setTotalPages((int) Math.ceil((float) total / (float) rowsPerPage));
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
        return response;
    }

    private MerchantParameterDisplay getMerchantParameterValues(List<MerchantParameterValue> mpvList) {
        MerchantParameterDisplay merchantParameterValue = new MerchantParameterDisplay();

        for (int i = 0; i < mpvList.size(); i++) {
            Integer key = mpvList.get(i).getMerchantParameter().getId();
            String value = mpvList.get(i).getValue();

            if (key == NomMerchantParameter.HEADER_LINE_1.getId()) {
                merchantParameterValue.setHeaderLine1(value);
            } else if (key == NomMerchantParameter.HEADER_LINE_2.getId()) {
                merchantParameterValue.setHeaderLine2(value);
            } else if (key == NomMerchantParameter.HEADER_LINE_3.getId()) {
                merchantParameterValue.setHeaderLine3(value);
            } else if (key == NomMerchantParameter.HEADER_LINE_4.getId()) {
                merchantParameterValue.setHeaderLine4(value);
            } else if (key == NomMerchantParameter.HEADER_LINE_5.getId()) {
                merchantParameterValue.setHeaderLine5(value);
            } else if (key == NomMerchantParameter.PROMISSORY_VERBIAGE_1.getId()) {
                merchantParameterValue.setPromissoryVerbiage1(value);
            } else if (key == NomMerchantParameter.PROMISSORY_VERBIAGE_2.getId()) {
                merchantParameterValue.setPromissoryVerbiage2(value);
            } else if (key == NomMerchantParameter.PROMISSORY_VERBIAGE_3.getId()) {
                merchantParameterValue.setPromissoryVerbiage3(value);
            }
        }

        return merchantParameterValue;
    }

    private TerminalParameterDisplay getTerminalParameterValues(List<TerminalParameterValue> tpvList) {
        TerminalParameterDisplay terminalParameterValue = new TerminalParameterDisplay();

        for (int i = 0; i < tpvList.size(); i++) {
            Integer key = tpvList.get(i).getApplicationParameter().getId();
            String value = tpvList.get(i).getValue();

            if (key == NomTerminalParameter.SESSION_TIME_OUT.getId()) {
                terminalParameterValue.setSessionTimeOut(Integer.parseInt(value));
            } else if (key == NomTerminalParameter.START_ON_CARD_PAYMENT.getId()) {
                terminalParameterValue.setStartOnCardPayment(Boolean.parseBoolean(value));
            } else if (key == NomTerminalParameter.RETURN_TO_CARD_PAYMENT.getId()) {
                terminalParameterValue.setReturnToCardPayment(Boolean.parseBoolean(value));
            } else if (key == NomTerminalParameter.TIP.getId()) {
                terminalParameterValue.setTip(Boolean.parseBoolean(value));
            } else if (key == NomTerminalParameter.TAX.getId()) {
                terminalParameterValue.setTax(Boolean.parseBoolean(value));
            } else if (key == NomTerminalParameter.TAX_PERCENTAGE.getId()) {
                terminalParameterValue.setTaxPercentage(Float.parseFloat(value));
            } else if (key == NomTerminalParameter.MERCHANT_EMAIL_COPY.getId()) {
                terminalParameterValue.setMerchantCopy(value);
            } else if (key == NomTerminalParameter.RECEIPT_MESSAGE.getId()) {
                terminalParameterValue.setReceiptMessage(value);
            }
        }

        return terminalParameterValue;
    }
//---------------------------------------------------------------------------------------- transaction mobile

    public void sendTheEmail(String receiptInfo, List<String> emailList, String receiptTitle) throws Exception {
        TransactionValidator.sendTheEmail(receiptInfo, emailList);
        String server_address = SystemPropertyDAO.get().searchSysProperty(NomSystemProperties.EMAIL_SERVER_ADDRESS.getViewValue());
        String server_port = SystemPropertyDAO.get().searchSysProperty(NomSystemProperties.EMAIL_SERVER_PORT.getViewValue());
        String server_username = SystemPropertyDAO.get().searchSysProperty(NomSystemProperties.EMAIL_SERVER_USERNAME.getViewValue());
        String server_password = SystemPropertyDAO.get().searchSysProperty(NomSystemProperties.EMAIL_SERVER_PASSWORD.getViewValue());
        String server_from_address = SystemPropertyDAO.get().searchSysProperty(NomSystemProperties.EMAIL_FROM_ADDRESS.getViewValue());
        String email_debug_setting = SystemPropertyDAO.get().searchSysProperty(NomSystemProperties.EMAIL_DEBUG.getViewValue());

        boolean email_debug = false;

        if (email_debug_setting != null && email_debug_setting.toLowerCase().compareTo("true") == 0) {
            email_debug = true;
        }

        String[] recipients = new String[emailList.size()];
        emailList.toArray(recipients);

        EmailUtils email;

        if (server_username != null && !server_username.isEmpty()) {
            email = new EmailUtils(server_address, server_port, server_username, server_password);
        } else {
            email = new EmailUtils(server_address, server_port);
        }

        email.setMessage("<html><head><style type=\"text/css\">body{border:0px none;text-align:center;}</style></head><body><img src=\"cid:image\"></body></html>", "text/html");
        email.setImage(receiptInfo.substring(22), "receipt.png", "image/png", "image");
//        email.sendEmail(recipients, server_from_address, receiptTitle, email_debug);
    }

    /**
     * Add transaction Signature
     *
     * @param transactionId
     * @param signature
     * @return BaseResponse
     * @throws java.lang.Exception
     */
    public BaseResponse addSignature(int transactionId, String signature) throws Exception {
        TransactionValidator.addSignature(transactionId, signature);
        BaseResponse baseResponse = new BaseResponse();
        transactionDAO.addSignature(transactionId, signature);
        baseResponse.setStatus(Constants.CODE_SUCCESS);

        return baseResponse;
    }

    /**
     * Front process Client Transaction Request.
     *
     * @param transactionDisplay
     * @param idClerk
     * @param idTerminal
     * @return The transaction response
     * @throws java.lang.Exception
     */
    public ResponseData doTransaction(TransactionDisplay transactionDisplay, int idClerk, int idTerminal) throws Exception {
        TransactionValidator.doTransaction(transactionDisplay, idClerk, idTerminal);
        transactionDisplay.setFrontStartTime();
        ResponseData response = new ResponseData();

//        try {
//            DTBusinessLogic bizLogic = new DTBusinessLogic();
//            response = bizLogic.handle(transactionDisplay, idClerk, idTerminal);
//            log.info("== Do Transaction response has been sent ==");
//            return response;
//        } catch (Exception ex) {
//            log.error("== Exception in Front processTransaction: " + ex.getMessage(), ex);
//            response.setStatus((Integer) getErrorCodeAndMessageFromException(ex)[0]);
//            response.setStatusMessage((String) getErrorCodeAndMessageFromException(ex)[1]);
//            return response;
//        }
        return null;
    }

    /**
     * Get transaction object from id
     *
     * @param IdTransaction The transaction id
     * @param emailAddress
     * @return Receipt
     * @throws java.lang.Exception
     */
    public BaseResponse sendEmail(int IdTransaction, String emailAddress) throws Exception {
        TransactionValidator.sendEmail(IdTransaction, emailAddress);
        BaseResponse clientResponse = new BaseResponse();
       
        List<String> emailList = new ArrayList<String>();
        TransactionDisplay transaction = TransactionDAO.get().getTransaction(IdTransaction, null);
        //Getting receipt title
        String receiptTitle = transaction.getTerminal().getApplication().equalsIgnoreCase("1") ? VTSuiteMessages.MOBILE_RECEIPT : VTSuiteMessages.APPLICATION_RECEIPT;
        ResponseData responseData = getTransactionReceipt(transaction);

        if (responseData.getStatus() == 100 && responseData.getData() != null) {
            String receiptInfo = (String) responseData.getData();

            if (emailAddress==null || emailAddress.isEmpty()) {
       
                //Getting customerEmail
                if (transaction.getCardHolderEmail() != null && !transaction.getCardHolderEmail().isEmpty()) {
                    emailList.add(transaction.getCardHolderEmail());
                }
                //Load Terminal Parameters
                List<TerminalParameterValue> terminalParameterValuesList = ParameterValueDAO.get().searchParametersValue(transaction.getTerminal().getId(),
                        EntityType.TERMINAL, null, -1, -1);

                for (int i = 0; i < terminalParameterValuesList.size(); i++) {
                    if (terminalParameterValuesList.get(i).getApplicationParameter().getId() == NomTerminalParameter.MERCHANT_EMAIL_COPY.getId()) {
                        if (terminalParameterValuesList.get(i).getValue() != null && terminalParameterValuesList.get(i).getValue().trim().compareTo("") != 0) {
                            emailList.add(terminalParameterValuesList.get(i).getValue());
                            break;
                        }
                    }
                }
            } else {
                emailList.add(emailAddress);
            }

            if (!emailList.isEmpty()) {
                sendTheEmail(receiptInfo, emailList, receiptTitle);
            }
        }

        clientResponse.setStatus(Constants.CODE_SUCCESS);
        return clientResponse;

    }

    /**
     * voidTransaction -- Do the transaction reversal.
     *
     * @param display
     * @param idClerk
     * @param idTerminal
     * @param adjustTransaction
     * @return The transaction
     * @throws java.lang.Exception
     */
    public ResponseData voidTransaction(TransactionDisplay display, int idClerk, int idTerminal, boolean adjustTransaction) throws Exception {
        TransactionValidator.voidTransaction(display, idClerk, idTerminal);
        ResponseData response;
        TransactionDisplay transactionDisplay = transactionDAO.getTransaction(display.getId(), NomOperation.VOID);
        transactionDisplay.setOperation(NomOperation.VOID.toString());
        transactionDisplay.setGeoLocation(display.getGeoLocation());
        //This is the original entry method
        transactionDisplay.setEntryMethod(transactionDisplay.getSwipe()?2:0);
        //Allow to build direxTransactionRequest with the account info retrieve from DB
        transactionDisplay.setSwipe(false);
        
        //Do transaction
        response = doTransaction(transactionDisplay, idClerk, idTerminal);
        transactionDisplay = (TransactionDisplay) response.getData();

        response.setData(transactionDisplay);
        if (response.getStatus() == Constants.CODE_SUCCESS) {
            response.setStatus(Constants.CODE_SUCCESS);
            //send email if this is not a adjust transaction
            if (!adjustTransaction)
            sendEmail(transactionDisplay.getId(), "");
        } else {
            response.setStatus(Constants.CODE_ERROR_GENERAL);
        }

        return response;
    }

    /**
     * refundTransaction -- Do the transaction refund.
     *
     * @param IdTransaction Transaction's id to be refunded.
     * @param subTotalAmount
     * @param tipAmount
     * @param totalAmount
     * @param idClerk
     * @param idTerminal
     * @return The transaction
     * @throws java.lang.Exception
     */
    public ResponseData refundTransaction(int IdTransaction, double subTotalAmount, double tipAmount, double totalAmount, int idClerk, int idTerminal) throws Exception {
        TransactionValidator.refundTransaction(IdTransaction, subTotalAmount, tipAmount, totalAmount, idClerk, idTerminal);
        ResponseData response = new ResponseData();

        TransactionDisplay transactionDisplay = transactionDAO.getTransaction(IdTransaction, NomOperation.REFUND);
        String originalOperation = transactionDisplay.getOperation();
        transactionDisplay.setOperation(NomOperation.REFUND.toString());
        transactionDisplay.setSubTotalAmount(subTotalAmount);
        transactionDisplay.setTipAmount(tipAmount);
        transactionDisplay.setTotalAmount(totalAmount);
        //This is the original entry method
        transactionDisplay.setEntryMethod(transactionDisplay.getSwipe()?2:0);
         //Allow to build direxTransactionRequest with the account info retrieve from DB
         transactionDisplay.setSwipe(false);

        //Do transaction
        response = doTransaction(transactionDisplay, idClerk, idTerminal);

        TransactionDisplay transactionDisplayReturn = (TransactionDisplay) response.getData();

        //Remove sensitive data from pan
        //  transactionDisplayReturn.setPan(transactionDisplay.getPan().substring(transactionDisplay.getPan().length() - 4));
        //Construct the response object
        transactionDisplayReturn.setOperation(originalOperation.concat(" Refund"));

        response.setData(transactionDisplayReturn);
        if (response.getStatus() == Constants.CODE_SUCCESS) {
            response.setStatus(Constants.CODE_SUCCESS);
            //send email
            sendEmail(transactionDisplayReturn.getId(),"");
        } else {
            response.setStatus(Constants.CODE_ERROR_GENERAL);
        }

        return response;
    }

    /**
     * adjustTransaction -- Do the transaction adjustment.
     *
     * @param transaction Transaction's id to be adjust.
     * @param idClerk
     * @param idTerminal
     * @return The transaction
     * @throws java.lang.Exception
     */
    public ResponseData adjustTransaction(TransactionDisplay transaction, int idClerk, int idTerminal) throws Exception {
        TransactionValidator.adjustTransaction(transaction, idClerk, idTerminal);
        ResponseData response = new ResponseData();

        Double totalAmount = new Double(0);

        if (transaction.getSubTotalAmount() != null) {
            totalAmount = transaction.getSubTotalAmount();
        }
        if (transaction.getTipAmount() != null) {
            totalAmount = totalAmount + transaction.getTipAmount();
        }
        if (transaction.getTaxAmount() != null) {
            totalAmount = totalAmount + transaction.getTaxAmount();
        }

        if (totalAmount == 0) {
            response.setStatus(Constants.CODE_ERROR_GENERAL);
            return response;
        } else {
            //We put operation = Void because we need to use the validation in getTransaction that ask for finalize
            //Later the operation is set to sale that is the one that goes to the host
            TransactionDisplay transactionDisplay = transactionDAO.getTransaction(transaction.getId(), NomOperation.VOID);
            
            String originalOperation = transactionDisplay.getOperation();
            transactionDisplay.setOperation(NomOperation.SALE.toString());
            transactionDisplay.setSubTotalAmount(transaction.getSubTotalAmount() == null ? 0 : transaction.getSubTotalAmount());
            transactionDisplay.setTipAmount(transaction.getTipAmount() == null ? 0 : transaction.getTipAmount());
            transactionDisplay.setTaxAmount(transaction.getTaxAmount() == null ? 0 : transaction.getTaxAmount());
            transactionDisplay.setTotalAmount(totalAmount);
            transactionDisplay.setSwipe(false);

            //Do transaction
            response = doTransaction(transactionDisplay, idClerk, idTerminal);

            // if adjustment was approved we void the original transaction
            TransactionDisplay responseTransactionDisplay = (TransactionDisplay) response.getData();
            if (responseTransactionDisplay.getDisposition().startsWith("APPROVED")) {
                log.info(" ============= Voiding original transaction after success adjustment=============");
                voidTransaction(transaction, idClerk, idTerminal,true);
            }

            TransactionDisplay transactionDisplayReturn = (TransactionDisplay) response.getData();
            //Construct the response object
            transactionDisplayReturn.setOperation(originalOperation.concat(" " + NomOperation.ADJUST.toString().toLowerCase()));

            response.setData(transactionDisplayReturn);
            if (response.getStatus() == Constants.CODE_SUCCESS) {
                response.setStatus(Constants.CODE_SUCCESS);
                //send email
                sendEmail(transactionDisplayReturn.getId(), "");
            } else {
                response.setStatus(Constants.CODE_ERROR_GENERAL);
            }

            return response;
        }

    }
    
    
    /**
     * adjustTransactionList -- Do the transaction adjustment.
     *
     * @param transaction Transaction's id to be adjust.
     * @param idClerk
     * @param idTerminal
     * @return The transaction
     * @throws java.lang.Exception
     */
    public ResponseData adjustTransactionList(ResponseDataList transactionList, int idClerk, int idTerminal) throws Exception {
      //  TransactionValidator.adjustTransaction(transactionList, idClerk, idTerminal);
        ResponseData response = new ResponseData();

//        Double totalAmount = new Double(0);
//
//        if (transaction.getSubTotalAmount() != null) {
//            totalAmount = transaction.getSubTotalAmount();
//        }
//        if (transaction.getTipAmount() != null) {
//            totalAmount = totalAmount + transaction.getTipAmount();
//        }
//        if (transaction.getTaxAmount() != null) {
//            totalAmount = totalAmount + transaction.getTaxAmount();
//        }
//
//        if (totalAmount == 0) {
//            response.setStatus(Constants.CODE_ERROR_GENERAL);
//            return response;
//        } else {
//
//            TransactionDisplay transactionDisplay = transactionDAO.getTransaction(transaction.getId(), NomOperation.REFUND);
//            String originalOperation = transactionDisplay.getOperation();
//            transactionDisplay.setOperation(NomOperation.SALE.toString());
//            transactionDisplay.setSubTotalAmount(transaction.getSubTotalAmount() == null ? 0 : transaction.getSubTotalAmount());
//            transactionDisplay.setTipAmount(transaction.getTipAmount() == null ? 0 : transaction.getTipAmount());
//            transactionDisplay.setTaxAmount(transaction.getTaxAmount() == null ? 0 : transaction.getTaxAmount());
//            transactionDisplay.setTotalAmount(totalAmount);
//            transactionDisplay.setSwipe(false);
//
//            //Do transaction
//            response = doTransaction(transactionDisplay, idClerk, idTerminal);
//
//            // if adjustment was approved we void the original transaction
//            TransactionDisplay responseTransactionDisplay = (TransactionDisplay) response.getData();
//            if (responseTransactionDisplay.getDisposition().startsWith("APPROVED")) {
//                log.info(" ============= Voiding original transaction after success adjustment=============");
//                voidTransaction(transaction, idClerk, idTerminal,true);
//            }
//
//            TransactionDisplay transactionDisplayReturn = (TransactionDisplay) response.getData();
//            //Construct the response object
//            transactionDisplayReturn.setOperation(originalOperation.concat(" " + NomOperation.ADJUST.toString().toLowerCase()));
//
//            response.setData(transactionDisplayReturn);
//            if (response.getStatus() == Constants.CODE_SUCCESS) {
//                response.setStatus(Constants.CODE_SUCCESS);
//                //send email
//                sendEmail(transactionDisplayReturn.getId(), "");
//            } else {
//                response.setStatus(Constants.CODE_ERROR_GENERAL);
//            }

            return response;
   //     }

    }

    /**
     * getErrorCodeAndMessageFromException -- Extract from the exception the
     * transaction code and transaction message.
     *
     * @param ex
     * @return Array object with ErrorCode in first position and errorMessage in
     * second position
     */
    private Object[] getErrorCodeAndMessageFromException(Exception ex) {

        Object[] errorDetails = new String[2];
        errorDetails[0] = Constants.CODE_ERROR_GENERAL;
        errorDetails[1] = VTSuiteMessages.ERROR_GENERAL;

        if (ex.getMessage() != null) {
            boolean found = false;
            int i = 0;
            Field[] constants = Constants.class.getDeclaredFields();
            while (found == false && i < constants.length - 1) {
                i++;
                try {
                    Field constant = constants[i];
                    constant.setAccessible(true);
                    Integer errorCode = (Integer) constant.get(null);
                    if (ex.getMessage().contains(String.valueOf(errorCode))) {
                        found = true;
                        errorDetails[0] = errorCode;
                        errorDetails[1] = ex.getMessage().replace(String.valueOf(errorCode), "");
                    }

                } catch (IllegalArgumentException ex1) {
                    java.util.logging.Logger.getLogger(TransactionManager.class.getName()).log(Level.SEVERE, null, ex1);
                } catch (IllegalAccessException ex1) {
                    java.util.logging.Logger.getLogger(TransactionManager.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        }
        return errorDetails;
    }
}
