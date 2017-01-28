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
package com.smartbt.girocheck.servercommon.display.report;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.smartbt.vtsuite.servercommon.display.common.model.*;
import com.smartbt.vtsuite.servercommon.utils.CurrencySerializer;
import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * The Transaction Display Class - containing all sets/gets
 */
@XmlRootElement
public class TransactionDetailReportDisplay implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer transactionType;
    private String maskCardNumber;
    private String operation;
    private Double payoutAmount;
    private Double feeAmount;
    private Double amount;
    private String requestId;
    private Integer resultCode;
    private String resultMessage;
    private Date dateTime;
    private String clientFirstName;
    private String clientLastName;
    private String clientPhone;

    private String idPosOrderExp;
    private String merchantName;
    private String terminalSerial;
    private String idTellerOrderExp;
    private String oEAgentNumber;
    private String checkNumber;
    private String makerName;

    /**
     * The default constructor
     */
    public TransactionDetailReportDisplay() {
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the maskCardNumber
     */
    public String getMaskCardNumber() {
        if (maskCardNumber != null && maskCardNumber.length() >= 4) {
            return  "********" + maskCardNumber.substring(maskCardNumber.length() - 4);
        }
        return "";
    }

    /**
     * @param maskCardNumber the maskCardNumber to set
     */
    public void setMaskCardNumber(String maskCardNumber) {
        this.maskCardNumber = maskCardNumber;
    }

    /**
     * @return the operation
     */
    public String getOperation() {
        return operation;
    }

    /**
     * @param operation the operation to set
     */
    public void setOperation(String operation) {
        this.operation = operation;
    }

    /**
     * @return the payoutAmount
     */
    public Double getPayoutAmount() {
        return payoutAmount;
    }

    /**
     * @param payoutAmount the payoutAmount to set
     */
    public void setPayoutAmount(Double payoutAmount) {
        this.payoutAmount = payoutAmount;
    }

    /**
     * @return the feeAmount
     */
    public Double getFeeAmount() {
        return feeAmount;
    }

    /**
     * @param feeAmount the feeAmount to set
     */
    public void setFeeAmount(Double feeAmount) {
        this.feeAmount = feeAmount;
    }

    /**
     * @return the amount
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * @return the requestId
     */
    public String getRequestId() {
        return requestId;
    }

    /**
     * @param requestId the requestId to set
     */
    public void setRequestId(Integer requestId) {
        this.requestId = requestId + "";
    }

    /**
     * @return the resultCode
     */
    public Integer getResultCode() {
        return resultCode;
    }

    /**
     * @param resultCode the resultCode to set
     */
    public void setResultCode(Integer resultCode) {
        this.resultCode = resultCode;
    }

    /**
     * @return the resultMessage
     */
    public String getResultMessage() {
        return resultMessage;
    }

    /**
     * @param resultMessage the resultMessage to set
     */
    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    /**
     * @return the dateTime
     */
    public Date getDateTime() {
        return dateTime;
    }

    /**
     * @param dateTime the dateTime to set
     */
    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * @return the transactionType
     */
    public Integer getTransactionType() {
        return transactionType;
    }

    /**
     * @param transactionType the transactionType to set
     */
    public void setTransactionType(Integer transactionType) {
        this.transactionType = transactionType;
    }

    /**
     * @return the clientFirstName
     */
    public String getClientFirstName() {
        return clientFirstName;
    }

    /**
     * @param clientFirstName the clientFirstName to set
     */
    public void setClientFirstName(String clientFirstName) {
        this.clientFirstName = clientFirstName;
    }

    /**
     * @return the clientLastName
     */
    public String getClientLastName() {
        return clientLastName;
    }

    /**
     * @param clientLastName the clientLastName to set
     */
    public void setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
    }

    /**
     * @return the idPosOrderExp
     */
    public String getIdPosOrderExp() {
        return idPosOrderExp;
    }

    /**
     * @param idPosOrderExp the idPosOrderExp to set
     */
    public void setIdPosOrderExp(String idPosOrderExp) {
        this.idPosOrderExp = idPosOrderExp;
    }

    /**
     * @return the merchantName
     */
    public String getMerchantName() {
        return merchantName;
    }

    /**
     * @param merchantName the merchantName to set
     */
    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    /**
     * @return the terminalSerial
     */
    public String getTerminalSerial() {
        return terminalSerial;
    }

    /**
     * @param terminalSerial the terminalSerial to set
     */
    public void setTerminalSerial(String terminalSerial) {
        this.terminalSerial = terminalSerial;
    }

    /**
     * @return the idTellerOrderExp
     */
    public String getIdTellerOrderExp() {
        return idTellerOrderExp;
    }

    /**
     * @param idTellerOrderExp the idTellerOrderExp to set
     */
    public void setIdTellerOrderExp(String idTellerOrderExp) {
        this.idTellerOrderExp = idTellerOrderExp;
    }

    /**
     * @return the clientPhone
     */
    public String getClientPhone() {
        return clientPhone;
    }

    /**
     * @param clientPhone the clientPhone to set
     */
    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    /**
     * @return the oEAgentNumber
     */
    public String getoEAgentNumber() {
        return oEAgentNumber;
    }

    /**
     * @param oEAgentNumber the oEAgentNumber to set
     */
    public void setoEAgentNumber(String oEAgentNumber) {
        this.oEAgentNumber = oEAgentNumber;
    }

    /**
     * @return the checkNumber
     */
    public String getCheckNumber() {
        return checkNumber;
    }

    /**
     * @param checkNumber the checkNumber to set
     */
    public void setCheckNumber(String checkNumber) {
        this.checkNumber = checkNumber;
    }

    /**
     * @return the makerName
     */
    public String getMakerName() {
        return makerName;
    }

    /**
     * @param makerName the makerName to set
     */
    public void setMakerName(String makerName) {
        this.makerName = makerName;
    }

}
