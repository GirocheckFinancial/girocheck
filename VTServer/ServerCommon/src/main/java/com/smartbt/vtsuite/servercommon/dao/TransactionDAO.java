/*
 ** File: TransactionDAO.java
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
package com.smartbt.vtsuite.servercommon.dao;

import com.smartbt.girocheck.servercommon.dao.BaseDAO;
import com.smartbt.vtsuite.common.VTSuiteMessages;
import com.smartbt.vtsuite.servercommon.display.common.model.AccountDisplay;
import com.smartbt.vtsuite.servercommon.display.common.model.MerchantDisplay;
import com.smartbt.vtsuite.servercommon.display.common.model.ModeDisplay;
import com.smartbt.vtsuite.servercommon.display.common.model.TerminalDisplay;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import com.smartbt.vtsuite.servercommon.model.Transaction;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import static com.smartbt.vtsuite.vtcommon.enums.EntityType.CUSTOMER;
import static com.smartbt.vtsuite.vtcommon.enums.EntityType.MERCHANT;
import static com.smartbt.vtsuite.vtcommon.enums.EntityType.TERMINAL;
import com.smartbt.vtsuite.servercommon.display.common.model.TransactionDisplay;
import com.smartbt.vtsuite.servercommon.display.common.model.UserDisplay;
import com.smartbt.vtsuite.servercommon.dao.UtilsDAO.TransactionUtilsDAO;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
import com.smartbt.vtsuite.servercommon.model.Card;
import com.smartbt.vtsuite.servercommon.model.CardBrand;
import com.smartbt.vtsuite.servercommon.model.Check;
import com.smartbt.vtsuite.servercommon.model.Clerk;
import com.smartbt.vtsuite.servercommon.model.Client;
import com.smartbt.vtsuite.servercommon.model.Host;
import com.smartbt.vtsuite.servercommon.model.Mode;
import com.smartbt.vtsuite.servercommon.model.Operation;
import com.smartbt.vtsuite.servercommon.model.Signature;
import com.smartbt.vtsuite.servercommon.model.Terminal;
import com.smartbt.vtsuite.servercommon.utils.DateUtils;
import com.smartbt.girocheck.servercommon.utils.bd.TransformerComplexBeans;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomApplication;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomCardBrand;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomEntryMethod;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomOperation;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomTransactionMode;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.sql.rowset.serial.SerialBlob;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.transform.Transformers;

/**
 *
 * @author Ariel Saavedra
 */
public class TransactionDAO extends BaseDAO<Transaction> {

    /**
     *
     */
    protected static TransactionDAO dao;
    ObjectMapper objectMapper = new ObjectMapper();

    /**
     *
     */
    public TransactionDAO() {
        // super(Transaction.class);
    }

    /**
     *
     * @return
     */
    public static TransactionDAO get() {
        if (dao == null) {
            dao = new TransactionDAO();
        }
        return dao;
    }

    /**
     * Get Transaction modes.
     *
     * @return List modes
     */
    public List<ModeDisplay> getTransactionModes() {
        Criteria criteria = HibernateUtil.getSession().createCriteria(Mode.class);

        ProjectionList projectionList = Projections.projectionList()
                .add(Projections.property("id").as("id"))
                .add(Projections.property("name").as("name"));

        criteria.setProjection(projectionList);
        criteria.setResultTransformer(Transformers.aliasToBean(ModeDisplay.class));
        return criteria.list();
    }

    /**
     *
     * @param idEntity
     * @param searchFilter
     * @param entityType
     * @param startRangeDate
     * @param endRangeDate
     * @param firstResult
     * @param maxResult
     * @param application
     * @return
     */
    public List<TransactionDisplay> searchTransactions(int idEntity, String searchFilter, EntityType entityType, Date startRangeDate, Date endRangeDate, int firstResult, int maxResult, NomApplication application) {

        Criteria cri = HibernateUtil.getSession().createCriteria(Transaction.class)
                .createAlias("clerk", "clerk")
                .createAlias("terminal", "terminal")
                .createAlias("terminal.merchant", "merchant")
                .createAlias("merchant.customer", "customer")
                .createAlias("mode", "mode")
                .createAlias("operation", "operation")
                .createAlias("cardBrand", "cardBrand")
                //we won't store account info
                //.createAlias("account", "account")
                .add(Restrictions.eq("voided", false))
                .addOrder(Order.desc("createdAt"));

        if (firstResult >= 0) {
            cri.setFirstResult(firstResult);
            cri.setMaxResults(maxResult);
        }

        if (startRangeDate != null) {
            cri.add(Restrictions.ge("createdAt", startRangeDate));
        }
        if (endRangeDate != null) {
            endRangeDate.setHours(24);
            cri.add(Restrictions.le("createdAt", endRangeDate));
        }
        switch (entityType) {
            case CUSTOMER:
                cri.add(Restrictions.eq("customer.id", idEntity));
                break;
            case MERCHANT:
                cri.add(Restrictions.eq("merchant.id", idEntity));
                break;
            case TERMINAL:
                cri.add(Restrictions.eq("terminal.id", idEntity));
                break;
        }

        switch (application) {
            case VT_MOBILE:
                cri.add(Restrictions.ilike("disposition", "%APPROVED%"));
                if (searchFilter != null && !searchFilter.isEmpty()) {
                    Disjunction disjunction = (Disjunction) Restrictions.disjunction()
                            .add(Restrictions.like("cardBrand.name", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                            .add(Restrictions.like("operation.name", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                            .add(Restrictions.like("mode.name", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                            .add(Restrictions.like("accountSuffix", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                            .add(Restrictions.sqlRestriction("to_char(total_amount,'999999990.99') LIKE ?", "%" + searchFilter + "%", StandardBasicTypes.STRING));
                    cri.add(disjunction);
                    cri.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
                }
                break;
            default:
                if (searchFilter != null && !searchFilter.isEmpty()) {
                    Disjunction disjunction = (Disjunction) Restrictions.disjunction()
                            .add(Restrictions.like("geoLocation", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                            .add(Restrictions.like("invoiceNumber", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                            .add(Restrictions.like("poNumber", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                            .add(Restrictions.like("disposition", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                            .add(Restrictions.like("approvalCode", searchFilter, MatchMode.ANYWHERE))
                            .add(Restrictions.like("approvalNumber", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                            .add(Restrictions.like("cardBrand.name", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                            .add(Restrictions.like("operation.name", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                            .add(Restrictions.like("mode.name", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                            .add(Restrictions.like("accountSuffix", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                            .add(Restrictions.like("terminal.terminalId", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                            .add(Restrictions.like("clerk.username", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                            .add(Restrictions.like("merchant.name", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                            .add(Restrictions.sqlRestriction("sequence LIKE ?", "%" + searchFilter + "%", StandardBasicTypes.STRING))
                            .add(Restrictions.sqlRestriction("to_char(total_amount,'999999990.99') LIKE ?", "%" + searchFilter + "%", StandardBasicTypes.STRING))
                            .add(Restrictions.sqlRestriction("to_char(tax_amount,'999999990.99') LIKE ?", "%" + searchFilter + "%", StandardBasicTypes.STRING))
                            .add(Restrictions.sqlRestriction("to_char(sub_total_amount,'999999990.99') LIKE ?", "%" + searchFilter + "%", StandardBasicTypes.STRING))
                            .add(Restrictions.sqlRestriction("to_char(tip_amount,'999999990.99') LIKE ?", "%" + searchFilter + "%", StandardBasicTypes.STRING));

                    Criterion dateRestriction = DateUtils.getRestrictionForDateFilter(searchFilter, "createdAt");
                    if (dateRestriction != null) {
                        disjunction.add(dateRestriction);
                    }
                    cri.add(disjunction);
                }
                break;
        }

        ProjectionList projectionList = Projections.projectionList()
                .add(Projections.property("id").as("id"))
                .add(Projections.property("mode.name").as("mode"))
                .add(Projections.property("operation.name").as("operation"))
                .add(Projections.property("cardBrand.name").as("cardBrand"))
                .add(Projections.property("subTotalAmount").as("subTotalAmount"))
                .add(Projections.property("totalAmount").as("totalAmount"))
                .add(Projections.property("tipAmount").as("tipAmount"))
                .add(Projections.property("taxAmount").as("taxAmount"))
                //.add(Projections.property("client.id").as("client.id"))
                .add(Projections.property("clerk.id").as("clerk.id"))
                .add(Projections.property("clerk.username").as("clerk.username"))
                .add(Projections.property("terminal.id").as("terminal.id"))
                .add(Projections.property("terminal.terminalId").as("terminal.terminalId"))
                .add(Projections.property("cardHolderEmail").as("cardHolderEmail"))
                .add(Projections.property("geoLocation").as("geoLocation"))
                .add(Projections.property("disposition").as("disposition"))
                .add(Projections.property("sequence").as("sequence"))
                .add(Projections.property("approvalNumber").as("approvalNumber"))
                .add(Projections.property("approvalCode").as("approvalCode"))
                .add(Projections.property("accountSuffix").as("accountSuffix"))
                .add(Projections.property("voided").as("voided"))
                .add(Projections.property("poNumber").as("poNumber"))
                .add(Projections.property("invoiceNumber").as("invoiceNumber"))
                .add(Projections.property("createdAt").as("createdAt"))
                .add(Projections.property("finalized").as("finalized"))
                .add(Projections.property("hostCapture").as("hostCapture"))
                .add(Projections.property("retrievalData").as("retrievalData"))
                .add(Projections.property("batchNumber").as("batchNumber"))
                .add(Projections.property("cvvResult").as("cvvResult"))
                .add(Projections.property("entryMethod").as("entryMethod"))
                .add(Projections.property("idOriginalTransaction").as("idOriginalTransaction"))
                .add(Projections.property("merchant.name").as("merchant.name"))
                .add(Projections.property("merchant.number").as("merchant.number"))
                .add(Projections.property("merchant.id").as("merchant.id"));
                //we won't store account info
                // .add(Projections.property("account.cardHolderName").as("account.cardHolderName"));
        cri.setProjection(projectionList);
        cri.setResultTransformer(new TransformerComplexBeans(TransactionDisplay.class));

        return cri.list();
    }

    /**
     * Get a Transaction by a given id
     *
     * @param id
     * @param operation
     * @return TransactionDisplay
     * @throws java.lang.Exception
     *
     */
    public TransactionDisplay getTransaction(int id, NomOperation operation) throws Exception {
        TransactionDisplay transactionDisplay = new TransactionDisplay();

        Transaction transaction = (Transaction) HibernateUtil.getSession().get(Transaction.class, id);
        transactionDisplay.setClientStartTime(); /*This is temporary should be replace for a value take from the Mobile's TransactionController*/

        if ((operation != null) && (operation.equals(NomOperation.VOID) && (transaction.getFinalized() || transaction.getVoided() ))) {
            throw new Exception(VTSuiteMessages.UNABLE_FINALIZE);
        }

        transactionDisplay.setId(transaction.getId());
        transactionDisplay.setMode(transaction.getMode().getName());
        transactionDisplay.setOperation(transaction.getOperation().getName());

        transactionDisplay.setTotalAmount(transaction.getTotalAmount());
        transactionDisplay.setSubTotalAmount(transaction.getSubTotalAmount());
        transactionDisplay.setTipAmount(transaction.getTipAmount());
        transactionDisplay.setTaxAmount(transaction.getTaxAmount());
        transactionDisplay.setCardHolderEmail(transaction.getCardHolderEmail());
        transactionDisplay.setAccountSuffix(transaction.getAccountSuffix());
        transactionDisplay.setCvv("");

        transactionDisplay.setCardBrand((transaction.getCardBrand().getName()));
        transactionDisplay.setSwipe((transaction.getEntryMethod() != 0));
        transactionDisplay.setApprovalCode(transaction.getApprovalNumber());
        transactionDisplay.setDisposition(transaction.getDisposition());
        transactionDisplay.setBatchNumber(transaction.getBatchNumber());
        transactionDisplay.setCvvResult(transaction.getCvvResult());
        transactionDisplay.setSequence(transaction.getSequence());
        transactionDisplay.setEntryMethod(transaction.getEntryMethod());

        transactionDisplay.setGeoLocation(transaction.getGeoLocation());

        MerchantDisplay merchantDisplay = new MerchantDisplay();
        merchantDisplay.setId(transaction.getTerminal().getMerchant().getId());
        merchantDisplay.setName(transaction.getTerminal().getMerchant().getName());
        merchantDisplay.setNumber(TransactionUtilsDAO.get().getMerchantHostIdFromTransactionId(id));

        TerminalDisplay terminalDisplay = new TerminalDisplay();
        terminalDisplay.setId(transaction.getTerminal().getId());
        terminalDisplay.setTerminalId(TransactionUtilsDAO.get().getTerminalHostIdFromTransactionId(id));
        terminalDisplay.setApplication(String.valueOf(transaction.getTerminal().getApplication().getId()));

        UserDisplay clerkDisplay = new UserDisplay();
        clerkDisplay.setId(transaction.getClerk().getId());

        transactionDisplay.setMerchant(merchantDisplay);
        transactionDisplay.setTerminal(terminalDisplay);
        transactionDisplay.setClerk(clerkDisplay);
        transactionDisplay.setCreatedAt(transaction.getCreatedAt());
        transactionDisplay.setApprovalNumber(transaction.getApprovalNumber());

        //if operation = reversal we should specify the original voided operation
        if (transaction.getOperation().getName().equalsIgnoreCase(NomOperation.VOID.toString())) {
            Transaction originalTransaction = (Transaction) HibernateUtil.getSession().get(Transaction.class, transaction.getIdOriginalTransaction());
            transactionDisplay.setOperation(originalTransaction.getOperation().getName() + " " + transaction.getOperation().getName());
        }
        if (transaction.getAccount() != null) {
            boolean loadSensitiveDataForVoid = (operation != null) && (operation.equals(NomOperation.VOID) || operation.equals(NomOperation.REFUND));

            AccountDisplay accountDisplay = new AccountDisplay();
            accountDisplay.setId(transaction.getAccount().getId());
            accountDisplay.setPan(loadSensitiveDataForVoid ? transaction.getAccount().getEncryptedData() : transaction.getAccountSuffix());
            if (transaction.getMode().getName().equalsIgnoreCase(NomTransactionMode.CREDIT.toString())
                    || transaction.getMode().getName().equalsIgnoreCase(NomTransactionMode.DEBIT.toString())) {
                Card card = (Card) transaction.getAccount();
                accountDisplay.setExpDate(loadSensitiveDataForVoid ? card.getExpDate() : "");
                //accountDisplay.setBillingZipCode(card.getBillingZipCode());
                accountDisplay.setCardHolderName(card.getCardHolderName());
            }
            if (transaction.getMode().getName().equalsIgnoreCase(NomTransactionMode.CHECK.toString())) {
                Check check = (Check) transaction.getAccount();
                accountDisplay.setRoutingNumber(loadSensitiveDataForVoid ? check.getBankInformation().getRoutingNumber() : 0);
                accountDisplay.setDate(check.getDate());
            }

            transactionDisplay.setAccount(accountDisplay);
            if (loadSensitiveDataForVoid) {
                transactionDisplay.setOriginalSequenceNumber(String.valueOf(transaction.getSequence()));
                transactionDisplay.setOriginalApprovalNumber(transaction.getApprovalNumber());
                transactionDisplay.setOriginalRetrievalData(transaction.getRetrievalData());
                transactionDisplay.setOriginalCVVResult(transaction.getCvvResult());

                transactionDisplay.setTotalAmount(operation.equals(NomOperation.VOID) ? (transaction.getTotalAmount()) : 0);
                transactionDisplay.setSubTotalAmount(operation.equals(NomOperation.VOID) ? (transaction.getSubTotalAmount()) : 0);
                transactionDisplay.setTipAmount(operation.equals(NomOperation.VOID) ? (transaction.getTipAmount()) : 0);
                transactionDisplay.setTaxAmount(operation.equals(NomOperation.VOID) ? (transaction.getTaxAmount()) : 0);
            }
        }
        if (transaction.getSignature() != null) {
            byte[] bdata = transaction.getSignature().getSignature().getBytes(1, (int) transaction.getSignature().getSignature().length());
            transactionDisplay.setSignature(new String(bdata));
        }

        return transactionDisplay;
    }

    //------------------------------------------------------------------------------------------------- mobile transaction
    /**
     * Insert transaction's signature.
     *
     * @param idTransaction The transaction id
     * @param signatureStr The signature
     * @return
     * @throws java.sql.SQLException
     *
     */
    public boolean addSignature(int idTransaction, String signatureStr) throws SQLException {
        java.sql.Blob signatureBlob = new SerialBlob(signatureStr.getBytes());

        Transaction transaction = (Transaction) HibernateUtil.getSession().get(Transaction.class, idTransaction);

        Signature signature = new Signature();
        signature.setSignature(signatureBlob);

        transaction.setSignature(signature);

        HibernateUtil.getSession().save(transaction);

        return true;
    }

    /**
     * Log transaction into database.
     *
     * @param direxTransactionRequest The transaction request object.
     * @param direxTransactionResponse The transaction response object.
     * @throws Exception
     */
    public void logTransaction(DirexTransactionRequest direxTransactionRequest, DirexTransactionResponse direxTransactionResponse) throws Exception {
//        Session session = null;
//        //  int transactionId;
//        try {
//            Client client = null;
//            Terminal terminal = TerminalDAO.get().findById(direxTransactionRequest.getTerminalId());
//            Clerk clerk = ClerkDAO.get().findById(direxTransactionRequest.getClerkId());
//            Host host = HostDAO.get().getHostByMerchantMode(direxTransactionRequest.getTerminalId(), direxTransactionRequest.getMode());
//            Operation operation = OperationDAO.get().getOperationByName(direxTransactionRequest.getOperation());
//            Mode mode = ModeDAO.get().getModeByName(direxTransactionRequest.getMode());
//            if (direxTransactionRequest.getClientId() != null && direxTransactionRequest.getClientId() != 0) {
//                client = ClientDAO.get().findById(direxTransactionRequest.getClientId());
//            }
//            session = HibernateUtil.getSession();

            // We won't store card info
            // Accont info
//            Account account = null;
//            List<Account> accountList;
//
//            if (direxTransactionRequest.getMode().equalsIgnoreCase(NomTransactionMode.CREDIT.toString())
//                    || direxTransactionRequest.getMode().equalsIgnoreCase(NomTransactionMode.DEBIT.toString())) {
//
//                if (direxTransactionRequest.getClientId() != null && direxTransactionRequest.getClientId() != 0) {
//                    if (direxTransactionRequest.getIdAccount() != null && direxTransactionRequest.getIdAccount() != 0) {
//                        account = AccountDAO.get().findById(direxTransactionRequest.getIdAccount());
//                    } else { // Not accountId, we have account information
//                        accountList = AccountDAO.get().findByPAN(direxTransactionRequest.getPrimaryAccountNumber());
//                        if (!accountList.isEmpty()) {
//                            //Look if client has the account
//                            for (Account clientAccount : client.getAccount()) {
//                                if (clientAccount.getEncryptedData().equalsIgnoreCase(direxTransactionRequest.getPrimaryAccountNumber())) {
//                                    account = clientAccount;
//                                    break;
//                                }
//                            }
//                        }
//                    }
//                }
//                if (account == null) {
//                    account = new Card();
//                    account.setEncryptedData(direxTransactionRequest.getPrimaryAccountNumber());
//                    ((Card) account).setCardHolderName(direxTransactionRequest.getCardHolderName());
//                    ((Card) account).setExpDate(direxTransactionRequest.getExpirationDate());
//                    ((Card) account).setBillingZipCode(direxTransactionRequest.getBillingZipCode());
//                }
//            }
//            if (direxTransactionRequest.getMode().equalsIgnoreCase(NomTransactionMode.CHECK.toString())) {
//                account = new Check();
//                account.setEncryptedData(direxTransactionRequest.getPrimaryAccountNumber());
//                ((Check) account).setCheckNumber(Integer.valueOf(direxTransactionRequest.getMicr()
//                        .substring(direxTransactionRequest.getMicr().indexOf("C") + 1)));
//                ((Check) account).setDate(new Date());
//                String routingNumber = direxTransactionRequest.getMicr()
//                        .substring(1, direxTransactionRequest.getMicr().indexOf("A"));
//                //TODO load BankInfo by routingNumber and set to the acconunt              
//            }
//            NomCardBrand nomCardBrand = NomCardBrand.infer(direxTransactionRequest.getPrimaryAccountNumber());
//
//            java.util.Date date = new java.util.Date();
//            Timestamp createdAt = new Timestamp(date.getTime());
//
//            Criteria criteria = session.createCriteria(CardBrand.class).
//                    add(Restrictions.eq("id", nomCardBrand.getId()));
//            CardBrand cardBrand = (CardBrand) criteria.uniqueResult();
//
//            Transaction transaction = new Transaction();
//            transaction.setClerk(clerk);
//            transaction.setTerminal(terminal);
//            transaction.setClient(client);
//            transaction.setHost(host);
//            transaction.setOperation(operation);
//            transaction.setMode(mode);
//
//            Integer availableAmount = direxTransactionResponse.getAuthorizedAmount();
//
//            //Logging SubTotalAmount 
//            if (direxTransactionRequest.getSubTotalAmount() > 0) {
//                if (availableAmount >= direxTransactionRequest.getSubTotalAmount()) {
//                    transaction.setSubTotalAmount(((direxTransactionRequest.getSubTotalAmount() / (double) 100)));
//                    availableAmount = availableAmount - direxTransactionRequest.getSubTotalAmount();
//                    direxTransactionResponse.setAuthorizedSubTotalAmount(direxTransactionRequest.getSubTotalAmount());
//
//                } else {
//                    transaction.setSubTotalAmount(((availableAmount / (double) 100)));
//                    direxTransactionResponse.setAuthorizedSubTotalAmount(availableAmount);
//                    availableAmount = 0;
//                }
//            }
//
//            //Logging TaxAmount 
//            if (direxTransactionRequest.getTaxAmount() > 0) {
//                if (availableAmount >= direxTransactionRequest.getTaxAmount()) {
//                    transaction.setTaxAmount(((direxTransactionRequest.getTaxAmount() / (double) 100)));
//                    availableAmount = availableAmount - direxTransactionRequest.getTaxAmount();
//                    direxTransactionResponse.setAuthorizedTaxAmount(direxTransactionRequest.getTaxAmount());
//
//                } else {
//                    transaction.setTaxAmount(((availableAmount / (double) 100)));
//                    direxTransactionResponse.setAuthorizedTaxAmount(availableAmount);
//                    availableAmount = 0;
//                }
//            }
//
//            //Logging TipAmount 
//            if (direxTransactionRequest.getTipAmount() > 0) {
//                if (availableAmount >= direxTransactionRequest.getTipAmount()) {
//                    transaction.setTipAmount(((direxTransactionRequest.getTipAmount() / (double) 100)));
//                    availableAmount = availableAmount - direxTransactionRequest.getTipAmount();
//                    direxTransactionResponse.setAuthorizedTipAmount(direxTransactionRequest.getTipAmount());
//
//                } else {
//                    transaction.setTipAmount(((availableAmount / (double) 100)));
//                    direxTransactionResponse.setAuthorizedTipAmount(availableAmount);
//                    availableAmount = 0;
//                }
//            }
//
//            //Logging TotalAmount 
//            transaction.setTotalAmount(direxTransactionRequest.getTotalAmount() / (double) 100);
//
//            transaction.setDisposition(direxTransactionResponse.getLiteralResponse());
//            transaction.setSequence(direxTransactionResponse.getSequenceNumber());
//            transaction.setApprovalNumber(direxTransactionResponse.getApprovalNumber());
//            transaction.setApprovalCode(direxTransactionResponse.getApprovalCode().toString());
//            //transaction.setAccount(account); we won't store card info
//            transaction.setRetrievalData(direxTransactionResponse.getRetrievalBillableEventData());
//            transaction.setAccountSuffix(direxTransactionRequest.getPrimaryAccountNumber().substring(direxTransactionRequest.getPrimaryAccountNumber().length() - 4));
//            transaction.setCardBrand(cardBrand);
//            transaction.setCreatedAt(createdAt);
//            transaction.setUpdatedAt(createdAt);
//            transaction.setBatchNumber(direxTransactionResponse.getBatchNumber());
//            transaction.setCvvResult(direxTransactionResponse.getCvvResponseCode());
//            transaction.setEntryMethod(direxTransactionResponse.getEntryMethod());
//            transaction.setHostCapture(true);
//            transaction.setCardHolderEmail(direxTransactionRequest.getEmailReceipt());
//            transaction.setGeoLocation(direxTransactionRequest.getGeoLocation());
//            transaction.setVoided(Boolean.FALSE);
//            transaction.setFinalized(Boolean.FALSE);
//
//            if (direxTransactionRequest.getOperation().equalsIgnoreCase(NomOperation.REVERSAL.toString())) {
//                if (direxTransactionResponse.wasApproved()) {
//                    Transaction transactionReversal = (Transaction) session.get(Transaction.class, direxTransactionRequest.getIdTransactionReversal());
//                    transactionReversal.setVoided(Boolean.TRUE);
//                    session.saveOrUpdate(transactionReversal);
//                }
//                transaction.setIdOriginalTransaction(direxTransactionRequest.getIdTransactionReversal());
//                transaction.setCvvResult(direxTransactionRequest.getOriginalCVVResult());
//            }
//
//            //transaction.setPoNumber(null);
//            //transaction.setInvoiceNumber(null);
//            session.saveOrUpdate(transaction);
//            direxTransactionResponse.setTransactionId(transaction.getId());
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println(e.getCause());
//            throw new Exception(VTSuiteMessages.FAIL_LOG_TRANSACTION + e.getCause(), e.getCause());
//        }

    }

    //Watchdog verifications --------------------------------------------------------------------------
    /**
     * Get how transaction were performed by a clerk with a specific type of
     * operation
     *
     * @param clerk
     * @param operation
     * @return
     */
    public long getQuantityOfOperationsByClerkLast24H(Clerk clerk, NomOperation operation) {
        Criteria criteria = HibernateUtil.getSession().createCriteria(Transaction.class)
                .createAlias("clerk", "clerk")
                .createAlias("operation", "operation")
                .add(Restrictions.eq("clerk.id", clerk.getId()))
                .add(Restrictions.ge("createdAt", DateUtils.lessDays(new GregorianCalendar(), 1)))
                .add(Restrictions.like("operation.name", operation.toString(), MatchMode.ANYWHERE).ignoreCase())
                .setProjection(Projections.rowCount());

        return (Long) criteria.uniqueResult();
    }

    /**
     * Get how many swipes were performed by a clerk in the past 24 hours
     *
     * @param clerk
     * @return
     */
    public long getQuantityOfSwipesByClerkLast24H(Clerk clerk) {
        Criteria criteria = HibernateUtil.getSession().createCriteria(Transaction.class)
                .createAlias("clerk", "clerk")
                .add(Restrictions.eq("clerk.id", clerk.getId()))
                .add(Restrictions.ge("createdAt", DateUtils.lessDays(new GregorianCalendar(), 1)))
                .add(Restrictions.eq("entryMethod", NomEntryMethod.SWIPE.getId()))
                .setProjection(Projections.rowCount());

        return (Long) criteria.uniqueResult();
    }

    /**
     * Get last transaction performed by a Clerk
     *
     * @param clerk
     * @param idCurrentTran
     * @return
     */
    public Transaction getLastTransactionByClerkDiffToCurrent(Clerk clerk, int idCurrentTran) {
        Criteria criteria = HibernateUtil.getSession().createCriteria(Transaction.class)
                .createAlias("clerk", "clerk")
                .add(Restrictions.eq("clerk.id", clerk.getId()))
                .add(Restrictions.not(Restrictions.eq("id", idCurrentTran)))
                .addOrder(Order.desc("createdAt"))
                .setMaxResults(1);

        return (Transaction) criteria.uniqueResult();
    }

}
