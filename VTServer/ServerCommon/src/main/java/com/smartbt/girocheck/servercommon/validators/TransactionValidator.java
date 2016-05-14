/*
 *  File TransactionValidator
 * 
 *  Date Created: January 2014
 * 
 *  Copyright @ @ 2004-2014 Smart Business Technology, Inc.
 *
 *  All rights reserved. No part of this software may be 
 *  reproduced, transmitted, transcribed, stored in a retrieval 
 *  system, or translated into any language or computer language,
 *  in any form or by any means, electronic, mechanical, magnetic, 
 *  optical, chemical, manual or otherwise, without the prior 
 *  written permission of Smart Business Technology, Inc.
 *
 */
package com.smartbt.girocheck.servercommon.validators;

import com.smartbt.girocheck.common.SessionAMSUser;
import com.smartbt.vtsuite.common.SessionClerk;
import com.smartbt.vtsuite.common.VTSuiteMessages;
import com.smartbt.vtsuite.servercommon.dao.TransactionDAO;
import com.smartbt.vtsuite.servercommon.display.common.model.TransactionDisplay;
import com.smartbt.vtsuite.servercommon.validators.utils.UtilValidator;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomApplication;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomOperation;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomUserPrivileges;
import java.util.Date;
import java.util.List;
import javax.xml.bind.ValidationException;
import org.apache.log4j.Logger;

/**
 *
 * @author Ariel
 */
public class TransactionValidator {

    private static final Logger log = Logger.getLogger(TransactionValidator.class);

    /**
     * Get all the Transactions mode Validator
     *
     * @throws java.lang.Exception
     */
    public static void getTransactionModes() throws Exception {

    }

    /**
     * Get the Transaction by a given id Validator
     *
     * @param id
     * @param operation
     * @throws java.lang.Exception
     */
    public static void getTransaction(int id, NomOperation operation) throws Exception {
        if (TransactionDAO.get().findById(id) == null) {
            log.info("----->  getTransaction: This Transaction does not exist <-----");
            throw new ValidationException(VTSuiteMessages.TRANSACTION_DOES_NOT_EXIST);
        }
    }

    /**
     * Get a Transaction Receipt Validator
     *
     * @param id
     * @throws java.lang.Exception
     */
//    public static void getTransactionReceipt(int id) throws Exception {
//        if (SessionClerk.get() != null) {
//        } else if (SessionAMSUser.get() != null) {
//            UtilValidator.validatePrivilegesThrowEx(
//                    NomUserPrivileges.ALLOW_MERCHANT_SERVICES.getId(), NomUserPrivileges.ALLOW_MERCHANT_TRANSACTIONS.getId());
//        }
//        if (TransactionDAO.get().findById(id) == null) {
//            log.info("----->  getTransaction: This Transaction does not exist <-----");
//            throw new ValidationException(VTSuiteMessages.TRANSACTION_DOES_NOT_EXIST);
//        }
//    }

    public static void searchTransactions(String searchFilter, Date startRangeDate, Date endRangeDate, int firstResult, int maxResult, int transactionType, String operation,
            boolean filterAmmount, int ammountType, int opType, String ammountString ) throws Exception {
        if (SessionAMSUser.get() != null) {
            UtilValidator.validatePrivilegesThrowEx(
                    NomUserPrivileges.ALLOW_TRANSACTION.getId());
        }
        UtilValidator.validateSearchFilter(searchFilter);
    }


    public static void sendTheEmail(String receiptInfo, List<String> emailList) throws Exception {
    }

    /**
     * Add transaction Signature Validator
     *
     * @param transactionId
     * @param signature
     * @throws java.lang.Exception
     */
    public static void addSignature(int transactionId, String signature) throws Exception {

    }

    /**
     * Front process Client Transaction Request Validator.
     *
     * @param transactionDisplay
     * @param idClerk
     * @param idTerminal
     * @throws java.lang.Exception
     */
    public static void doTransaction(TransactionDisplay transactionDisplay, int idClerk, int idTerminal) throws Exception {
        String errors = UtilValidator.getErrorsAsString(transactionDisplay);
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }

    /**
     * Get transaction object from id Validator
     *
     * @param IdTransaction The transaction id
     * @param emailAddress
     * @throws java.lang.Exception
     */
    public static void sendEmail(int IdTransaction, String emailAddress) throws Exception {

    }

    /**
     * voidTransaction -- Do the transaction reversal Validator.
     *
     * @param display
     * @param idClerk
     * @param idTerminal
     * @throws java.lang.Exception
     */
    public static void voidTransaction(TransactionDisplay display, int idClerk, int idTerminal) throws Exception {

    }

    /**
     * refundTransaction -- Do the transaction refund Validator.
     *
     * @param IdTransaction Transaction's id to be refunded.
     * @param subTotalAmount
     * @param tipAmount
     * @param totalAmount
     * @param idClerk
     * @param idTerminal
     * @throws java.lang.Exception
     */
    public static void refundTransaction(int IdTransaction, double subTotalAmount, double tipAmount, double totalAmount, int idClerk, int idTerminal) throws Exception {
    }

    /**
     * adjustTransaction -- Do the transaction adjustment Validator.
     *
     * @param transaction Transaction's id to be adjust.
     * @param idClerk
     * @param idTerminal
     * @throws java.lang.Exception
     */
    public static void adjustTransaction(TransactionDisplay transaction, int idClerk, int idTerminal) throws Exception {

    }
}
