/*
 ** File: TransactionDisplay.java
 **
 ** Date Created: October 2013
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
package com.smartbt.vtsuite.servercommon.display.common.model;

import com.smartbt.vtsuite.servercommon.utils.CurrencySerializer;
import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * The Transaction Display Class - containing all sets/gets
 */
@XmlRootElement
public class TransactionDisplay implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    /**
     * REQUEST parameters
     */
    private String mode;
    private String operation;
    private String cardBrand;    
    private Double subTotalAmount;
    private Double tipAmount;
    private Double taxAmount;
    private Double totalAmount;
    private String cvv;
    private ClientDisplay client;
    private UserDisplay clerk;
    private String cardHolderEmail;
    private String geoLocation;
    private String signature;
    private AccountDisplay account;
    private boolean allowPartialAuth;
    /**
     * REQUEST Reader Parameters
     */
    private String formatID;
    private String ksn;
    private String encTrack1AndTrack2;
    private Integer track1Length;
    private Integer track2Length;
    private String maskedPAN;
    private Boolean swipe;
    private String pin;
    private String authNumber;
    /**
     * RESPONSE Parameters
     */
    private String disposition;
    private Integer sequence;
    private String approvalNumber;
    private String approvalCode;
    private String accountSuffix;
    private Boolean voided;
    private String poNumber;
    private String invoiceNumber;
    private Date createdAt;
    private Boolean finalized;
    private Boolean hostCapture;
    private String retrievalData;
    private Integer batchNumber;
    private String cvvResult;
    private Integer entryMethod;
    private TerminalDisplay terminal;
    private MerchantDisplay merchant;
    private String debugMessage;
    private Double amountAuthorized;
    /**
     * SERVER Purposes
     */
    // private Integer idTransactionReversal;
    private String originalSequenceNumber;
    private String originalApprovalNumber;
    private String originalRetrievalData;
    private String originalCVVResult;
    private Long clientStartTime;
    private Long frontStartTime;
    private Boolean debug;
    private Integer idOriginalTransaction;
    /**
     * APPLICATION Purposes
     */
    private Boolean saveClient;
    private Boolean saveAccount;
    private Boolean saveAsRecurringPayment;
    private Boolean emailReceipt;
    /**
     * AMS Purposes
     */
    /**
     * OTHER Purposes
     */
    private String host;

    /**
     * The default constructor
     */
    public TransactionDisplay() {
    }

    /**
     *
     * @return
     */
    public String getCardBrand() {
        return cardBrand;
    }

    /**
     *
     * @param cardBrand
     */
    public void setCardBrand(String cardBrand) {
        this.cardBrand = cardBrand;
    }

    /**
     *
     * @return
     */
    public String getSignature() {
        return signature;
    }

    /**
     *
     * @param signature
     */
    public void setSignature(String signature) {
        this.signature = signature;
    }

//    public String getMicr() {
//        return micr;
//    }
//
//    public void setMicr(String micr) {
//        this.micr = micr;
//    }
    /**
     *
     * @return
     */
    public String getMode() {
        return mode;
    }

    /**
     *
     * @param mode
     */
    public void setMode(String mode) {
        this.mode = mode;
    }

    /**
     *
     * @return
     */
    @JsonSerialize(using = CurrencySerializer.class)
    public Double getSubTotalAmount() {
        return subTotalAmount;
    }

    /**
     *
     * @param subTotalAmount
     */
    public void setSubTotalAmount(Double subTotalAmount) {
        this.subTotalAmount = subTotalAmount;
    }

    /**
     *
     * @return
     */
    @JsonSerialize(using = CurrencySerializer.class)
    public Double getTipAmount() {
        return tipAmount;
    }

    /**
     *
     * @param tipAmount
     */
    public void setTipAmount(Double tipAmount) {
        this.tipAmount = tipAmount;
    }

    /**
     *
     * @return
     */
    @JsonSerialize(using = CurrencySerializer.class)
    public Double getTaxAmount() {
        return taxAmount;
    }

    /**
     *
     * @param taxAmount
     */
    public void setTaxAmount(Double taxAmount) {
        this.taxAmount = taxAmount;
    }

    /**
     *
     * @return
     */
    @JsonSerialize(using = CurrencySerializer.class)
    public Double getTotalAmount() {
        return totalAmount;
    }

    /**
     *
     * @param totalAmount
     */
    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     *
     * @return
     */
    public String getDisposition() {
        return disposition;
    }

    /**
     *
     * @param disposition
     */
    public void setDisposition(String disposition) {
        this.disposition = disposition;
    }

    /**
     *
     * @return
     */
    public String getOperation() {
        return operation;
    }

    /**
     *
     * @return
     */
    public Integer getSequence() {
        return sequence;
    }

    /**
     *
     * @param sequence
     */
    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    /**
     *
     * @return
     */
    public String getApprovalNumber() {
        return approvalNumber;
    }

    /**
     *
     * @param approvalNumber
     */
    public void setApprovalNumber(String approvalNumber) {
        this.approvalNumber = approvalNumber;
    }

    /**
     *
     * @return
     */
    public String getApprovalCode() {
        return approvalCode;
    }

    /**
     *
     * @param approvalCode
     */
    public void setApprovalCode(String approvalCode) {
        this.approvalCode = approvalCode;
    }

    /**
     *
     * @return
     */
    public String getAccountSuffix() {
        return accountSuffix;
    }

    /**
     *
     * @param accountSuffix
     */
    public void setAccountSuffix(String accountSuffix) {
        this.accountSuffix = accountSuffix;
    }

    /**
     *
     * @return
     */
    public String getPoNumber() {
        return poNumber;
    }

    /**
     *
     * @param poNumber
     */
    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    /**
     *
     * @return
     */
    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    /**
     *
     * @param invoiceNumber
     */
    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    /**
     *
     * @return
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     *
     * @param createdAt
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     *
     * @return
     */
    public String getGeoLocation() {
        return geoLocation;
    }

    /**
     *
     * @param geoLocation
     */
    public void setGeoLocation(String geoLocation) {
        this.geoLocation = geoLocation;
    }

    /**
     *
     * @return
     */
    public Boolean getFinalized() {
        return finalized;
    }

    /**
     *
     * @param finalized
     */
    public void setFinalized(Boolean finalized) {
        this.finalized = finalized;
    }

    /**
     *
     * @return
     */
    public Boolean getHostCapture() {
        return hostCapture;
    }

    /**
     *
     * @param hostCapture
     */
    public void setHostCapture(Boolean hostCapture) {
        this.hostCapture = hostCapture;
    }

    /**
     *
     * @return
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public Boolean getVoided() {
        return voided;
    }

    /**
     *
     * @param voided
     */
    public void setVoided(Boolean voided) {
        this.voided = voided;
    }

    /**
     *
     * @return
     */
    public String getCvvResult() {
        return cvvResult;
    }

    /**
     *
     * @param cvvResult
     */
    public void setCvvResult(String cvvResult) {
        this.cvvResult = cvvResult;
    }

    /**
     *
     * @return
     */
    public String getCvv() {
        return cvv;
    }

    /**
     *
     * @param cvv
     */
    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    /**
     *
     * @return
     */
    public ClientDisplay getClient() {
        return client;
    }

    /**
     *
     * @param client
     */
    public void setClient(ClientDisplay client) {
        this.client = client;
    }

    /**
     *
     * @return
     */
    public UserDisplay getClerk() {
        return clerk;
    }

    /**
     *
     * @param clerk
     */
    public void setClerk(UserDisplay clerk) {
        this.clerk = clerk;
    }

    /**
     *
     * @return
     */
    public TerminalDisplay getTerminal() {
        return terminal;
    }

    /**
     *
     * @param terminal
     */
    public void setTerminal(TerminalDisplay terminal) {
        this.terminal = terminal;
    }

    /**
     *
     * @return
     */
    public String getCardHolderEmail() {
        return cardHolderEmail;
    }

    /**
     *
     * @param cardHolderEmail
     */
    public void setCardHolderEmail(String cardHolderEmail) {
        this.cardHolderEmail = cardHolderEmail;
    }

    /**
     *
     * @return
     */
    public String getFormatID() {
        return formatID;
    }

    /**
     *
     * @param formatID
     */
    public void setFormatID(String formatID) {
        this.formatID = formatID;
    }

    /**
     *
     * @return
     */
    public String getKsn() {
        return ksn;
    }

    /**
     *
     * @param ksn
     */
    public void setKsn(String ksn) {
        this.ksn = ksn;
    }

    /**
     *
     * @return
     */
    public String getEncTrack1AndTrack2() {
        return encTrack1AndTrack2;
    }

    /**
     *
     * @param encTrack1AndTrack2
     */
    public void setEncTrack1AndTrack2(String encTrack1AndTrack2) {
        this.encTrack1AndTrack2 = encTrack1AndTrack2;
    }

    /**
     *
     * @return
     */
    public Integer getTrack1Length() {
        return track1Length;
    }

    /**
     *
     * @param track1Length
     */
    public void setTrack1Length(Integer track1Length) {
        this.track1Length = track1Length;
    }

    /**
     *
     * @return
     */
    public Integer getTrack2Length() {
        return track2Length;
    }

    /**
     *
     * @param track2Length
     */
    public void setTrack2Length(Integer track2Length) {
        this.track2Length = track2Length;
    }

    /**
     *
     * @return
     */
    public String getMaskedPAN() {
        return maskedPAN;
    }

    /**
     *
     * @param maskedPAN
     */
    public void setMaskedPAN(String maskedPAN) {
        this.maskedPAN = maskedPAN;
    }

    /**
     *
     * @return
     */
    public Boolean getSwipe() {
        return swipe;
    }

    /**
     *
     * @param swipe
     */
    public void setSwipe(Boolean swipe) {
        this.swipe = swipe;
    }

    /**
     *
     * @return
     */
    public String getRetrievalData() {
        return retrievalData;
    }

    /**
     *
     * @param retrievalData
     */
    public void setRetrievalData(String retrievalData) {
        this.retrievalData = retrievalData;
    }

    /**
     *
     * @return
     */
    public Integer getBatchNumber() {
        return batchNumber;
    }

    /**
     *
     * @param batchNumber
     */
    public void setBatchNumber(Integer batchNumber) {
        this.batchNumber = batchNumber;
    }

    /**
     *
     * @return
     */
    public Integer getEntryMethod() {
        return entryMethod;
    }

    /**
     *
     * @param entryMethod
     */
    public void setEntryMethod(Integer entryMethod) {
        this.entryMethod = entryMethod;
    }

    /**
     *
     * @return
     */
    public String getDebugMessage() {
        return debugMessage;
    }

    /**
     *
     * @param debugMessage
     */
    public void setDebugMessage(String debugMessage) {
        this.debugMessage = debugMessage;
    }

//    public Integer getIdTransactionReversal() {
//        return idTransactionReversal;
//    }
//
//    public void setIdTransactionReversal(Integer idTransactionReversal) {
//        this.idTransactionReversal = idTransactionReversal;
//    }
    /**
     *
     * @return
     */
    public String getOriginalSequenceNumber() {
        return originalSequenceNumber;
    }

    /**
     *
     * @param originalSequenceNumber
     */
    public void setOriginalSequenceNumber(String originalSequenceNumber) {
        this.originalSequenceNumber = originalSequenceNumber;
    }

    /**
     *
     * @return
     */
    public String getOriginalApprovalNumber() {
        return originalApprovalNumber;
    }

    /**
     *
     * @param originalApprovalNumber
     */
    public void setOriginalApprovalNumber(String originalApprovalNumber) {
        this.originalApprovalNumber = originalApprovalNumber;
    }

    /**
     *
     * @return
     */
    public String getOriginalRetrievalData() {
        return originalRetrievalData;
    }

    /**
     *
     * @param originalRetrievalData
     */
    public void setOriginalRetrievalData(String originalRetrievalData) {
        this.originalRetrievalData = originalRetrievalData;
    }

    /**
     *
     * @return
     */
    public String getOriginalCVVResult() {
        return originalCVVResult;
    }

    /**
     *
     * @param originalCVVResult
     */
    public void setOriginalCVVResult(String originalCVVResult) {
        this.originalCVVResult = originalCVVResult;
    }

    /**
     *
     * @return
     */
    public Long getClientStartTime() {
        return clientStartTime;
    }

    /**
     *
     * @param clientStartTime
     */
    public void setClientStartTime(Long clientStartTime) {
        this.clientStartTime = clientStartTime;
    }

    /**
     *
     * @return
     */
    public Long getFrontStartTime() {
        return frontStartTime;
    }

    /**
     *
     */
    public void setFrontStartTime() {
        this.frontStartTime = System.currentTimeMillis();
    }

    /**
     *
     * @return
     */
    public Boolean getDebug() {
        return debug;
    }

    /**
     *
     * @param debug
     */
    public void setDebug(Boolean debug) {
        this.debug = debug;
    }

    /**
     *
     * @return
     */
    public MerchantDisplay getMerchant() {
        return merchant;
    }

    /**
     *
     * @param merchant
     */
    public void setMerchant(MerchantDisplay merchant) {
        this.merchant = merchant;
    }

    /**
     *
     * @return
     */
    public String getHost() {
        return host;
    }

    /**
     *
     * @param host
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     *
     * @return
     */
    public Boolean getSaveClient() {
        return saveClient;
    }

    /**
     *
     * @param saveClient
     */
    public void setSaveClient(Boolean saveClient) {
        this.saveClient = saveClient;
    }

    /**
     *
     * @return
     */
    public Boolean getSaveAccount() {
        return saveAccount;
    }

    /**
     *
     * @param saveAccount
     */
    public void setSaveAccount(Boolean saveAccount) {
        this.saveAccount = saveAccount;
    }

    /**
     *
     * @return
     */
    public Boolean getSaveAsRecurringPayment() {
        return saveAsRecurringPayment;
    }

    /**
     *
     * @param saveAsRecurringPayment
     */
    public void setSaveAsRecurringPayment(Boolean saveAsRecurringPayment) {
        this.saveAsRecurringPayment = saveAsRecurringPayment;
    }

    /**
     *
     * @return
     */
    public AccountDisplay getAccount() {
        return account;
    }

    /**
     *
     * @param account
     */
    public void setAccount(AccountDisplay account) {
        this.account = account;
    }

    /**
     *
     * @return
     */
    public Boolean getEmailReceipt() {
        return emailReceipt;
    }

    /**
     *
     * @param emailReceipt
     */
    public void setEmailReceipt(Boolean emailReceipt) {
        this.emailReceipt = emailReceipt;
    }

    /**
     *
     * @return
     */
    public Integer getIdOriginalTransaction() {
        return idOriginalTransaction;
    }

    /**
     *
     * @param idOriginalTransaction
     */
    public void setIdOriginalTransaction(Integer idOriginalTransaction) {
        this.idOriginalTransaction = idOriginalTransaction;
    }

    /**
     *
     * @return
     */
    public boolean isAllowPartialAuth() {
        return allowPartialAuth;
    }

    /**
     *
     * @param allowPartialAuth
     */
    public void setAllowPartialAuth(boolean allowPartialAuth) {
        this.allowPartialAuth = allowPartialAuth;
    }

    /**
     *
     * @return
     */
    public String getPin() {
        return pin;
    }

    /**
     *
     * @param pin
     */
    public void setPin(String pin) {
        this.pin = pin;
    }

    /**
     *
     * @return
     */
    public String getAuthNumber() {
        return authNumber;
    }

    /**
     *
     * @param authNumber
     */
    public void setAuthNumber(String authNumber) {
        this.authNumber = authNumber;
    }

    /**
     *
     * @return
     */
    @JsonSerialize(using = CurrencySerializer.class)
    public Double getAmountAuthorized() {
        return amountAuthorized;
    }

    /**
     *
     * @param amountAuthorized
     */
    public void setAmountAuthorized(Double amountAuthorized) {
        this.amountAuthorized = amountAuthorized;
    }

    /**
     * Extra or modified methods
     */
    /**
     * isApproved
     *
     * @return if is Approved
     */
    public boolean isApproved() {
        return disposition != null && disposition.toUpperCase().contains("APPROVED");
    }

    /**
     * Extra or modified methods
     */
    /**
     * setOperation
     *
     * @param operation
     */
    public void setOperation(String operation) {
        if (operation != null && operation.equals("Reversal")) {
            this.operation = "Void";
        } else {
            this.operation = operation;
        }
    }

    /**
     * Extra or modified methods
     */
    /**
     * setClientStartTime
     */
    public void setClientStartTime() {
        this.clientStartTime = System.currentTimeMillis();
    }
}
