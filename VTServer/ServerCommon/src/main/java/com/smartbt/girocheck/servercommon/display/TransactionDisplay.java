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
package com.smartbt.girocheck.servercommon.display;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Transaction Display Class - containing all sets/gets
 */
@XmlRootElement
public class TransactionDisplay implements Serializable {

    private static long serialVersionUID = 1L;

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @param aSerialVersionUID the serialVersionUID to set
     */
    public static void setSerialVersionUID( long aSerialVersionUID ) {
        serialVersionUID = aSerialVersionUID;
    }

    private Integer id;
    /**
     * REQUEST parameters
     */
    private Integer transactionType;
    private Date createdAt;
    private String operation;
    private String accountSuffix;
    private Double ammount;
    private Double feeAmmount;
    private Double payoutAmmount;
    private Boolean single;
    private Integer resultCode;
    private String resultMessage;
    
    private String merchant;
    private String terminal;
    private String clientFirstName;
    private String clientLastName;
    private boolean transactionFinished;

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
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId( Integer id ) {
        this.id = id;
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
    public void setTransactionType( Integer transactionType ) {
        this.transactionType = transactionType;
    }

    /**
     * @return the createdAt
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt the createdAt to set
     */
    public void setCreatedAt( Date createdAt ) {
        this.createdAt = createdAt;
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
    public void setOperation( String operation ) {
        this.operation = operation;
    }

    /**
     * @return the accountSuffix
     */
    public String getAccountSuffix() {
        return accountSuffix;
    }

    /**
     * @param accountSuffix the accountSuffix to set
     */
    public void setAccountSuffix( String accountSuffix ) {
        if ( accountSuffix != null && accountSuffix.length() > 4 ) {
            this.accountSuffix = accountSuffix.substring(accountSuffix.length() - 4);
        } else {
            this.accountSuffix = accountSuffix;
        }
    }

    /**
     * @return the ammount
     */
    public Double getAmmount() {
        return ammount;
    }

    /**
     * @param ammount the ammount to set
     */
    public void setAmmount( Double ammount ) {
        this.ammount = ammount;
    }

    /**
     * @return the feeAmmount
     */
    public Double getFeeAmmount() {
        return feeAmmount;
    }

    /**
     * @param feeAmmount the feeAmmount to set
     */
    public void setFeeAmmount( Double feeAmmount ) {
        this.feeAmmount = feeAmmount;
    }

    /**
     * @return the payoutAmmount
     */
    public Double getPayoutAmmount() {
        return payoutAmmount;
    }

    /**
     * @param payoutAmmount the payoutAmmount to set
     */
    public void setPayoutAmmount( Double payoutAmmount ) {
        this.payoutAmmount = payoutAmmount;
    }

    /**
     * @return the single
     */
    public Boolean getSingle() {
        return single;
    }

    /**
     * @param single the single to set
     */
    public void setSingle( Boolean single ) {
        this.single = single;
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
    public void setResultCode( Integer resultCode ) {
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
    public void setResultMessage( String resultMessage ) {
        this.resultMessage = resultMessage;
    }

    /**
     * @return the host
     */
    public String getHost() {
        return host;
    }

    /**
     * @param host the host to set
     */
    public void setHost( String host ) {
        this.host = host;
    }

    /**
     * @return the merchant
     */
    public String getMerchant() {
        return merchant;
    }

    /**
     * @param merchant the merchant to set
     */
    public void setMerchant( String merchant ) {
        this.merchant = merchant;
    }

    /**
     * @return the terminal
     */
    public String getTerminal() {
        return terminal;
    }

    /**
     * @param terminal the terminal to set
     */
    public void setTerminal( String terminal ) {
        this.terminal = terminal;
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
    public void setClientFirstName( String clientFirstName ) {
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
    public void setClientLastName( String clientLastName ) {
        this.clientLastName = clientLastName;
    }

    /**
     * @return the transactionFinished
     */
    public boolean isTransactionFinished() {
        return transactionFinished;
    }

    /**
     * @param transactionFinished the transactionFinished to set
     */
    public void setTransactionFinished(boolean transactionFinished) {
        this.transactionFinished = transactionFinished;
    }

}
