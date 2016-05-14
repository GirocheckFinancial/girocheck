/*
 ** File: BadgeDisplay.java
 **
 ** Date Created: February 2013
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

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Boarding Display Class - containing all sets/gets
 */
@XmlRootElement
public class BadgeDisplay implements Serializable {

    private String amountOfCustomers;
    private String cardPaymentByMerchant;
    private String refundByMerchant;
    private String taxCollectedByMerchant;
    private String tipsCollectedByMerchant;
    private String voids;
    private String countCardpaymentsByMerchant;
    private String countRefundByMerchant;
    private String countVoidsByMerchant;
    private String countTransactions;
    private String totalDayTransactionAmount;

    /**
     *
     * @return
     */
    public String getTotalDayTransactionAmount() {
        return totalDayTransactionAmount;
    }

    /**
     *
     * @return
     */
    public String getAmountOfCustomers() {
        return amountOfCustomers;
    }

    /**
     *
     * @return
     */
    public String getCardPaymentByMerchant() {
        return cardPaymentByMerchant;
    }

    /**
     *
     * @return
     */
    public String getCountCardpaymentsByMerchant() {
        return countCardpaymentsByMerchant;
    }

    /**
     *
     * @return
     */
    public String getCountRefundByMerchant() {
        return countRefundByMerchant;
    }

    /**
     *
     * @return
     */
    public String getCountTransactions() {
        return countTransactions;
    }

    /**
     *
     * @return
     */
    public String getCountVoidsByMerchant() {
        return countVoidsByMerchant;
    }

    /**
     *
     * @return
     */
    public String getRefundByMerchant() {
        return refundByMerchant;
    }

    /**
     *
     * @return
     */
    public String getTaxCollectedByMerchant() {
        return taxCollectedByMerchant;
    }

    /**
     *
     * @return
     */
    public String getTipsCollectedByMerchant() {
        return tipsCollectedByMerchant;
    }

    /**
     *
     * @return
     */
    public String getVoids() {
        return voids;
    }

    /**
     *
     * @param totalDayTransactionAmount
     */
    public void setTotalDayTransactionAmount(String totalDayTransactionAmount) {
        this.totalDayTransactionAmount = totalDayTransactionAmount;
    }

    /**
     *
     * @param amountOfCustomers
     */
    public void setAmountOfCustomers(String amountOfCustomers) {
        this.amountOfCustomers = amountOfCustomers;
    }

    /**
     *
     * @param cardPaymentByMerchant
     */
    public void setCardPaymentByMerchant(String cardPaymentByMerchant) {
        this.cardPaymentByMerchant = cardPaymentByMerchant;
    }

    /**
     *
     * @param countCardpaymentsByMerchant
     */
    public void setCountCardpaymentsByMerchant(String countCardpaymentsByMerchant) {
        this.countCardpaymentsByMerchant = countCardpaymentsByMerchant;
    }

    /**
     *
     * @param countRefundByMerchant
     */
    public void setCountRefundByMerchant(String countRefundByMerchant) {
        this.countRefundByMerchant = countRefundByMerchant;
    }

    /**
     *
     * @param countTransactions
     */
    public void setCountTransactions(String countTransactions) {
        this.countTransactions = countTransactions;
    }

    /**
     *
     * @param countVoidsByMerchant
     */
    public void setCountVoidsByMerchant(String countVoidsByMerchant) {
        this.countVoidsByMerchant = countVoidsByMerchant;
    }

    /**
     *
     * @param refundByMerchant
     */
    public void setRefundByMerchant(String refundByMerchant) {
        this.refundByMerchant = refundByMerchant;
    }

    /**
     *
     * @param taxCollectedByMerchant
     */
    public void setTaxCollectedByMerchant(String taxCollectedByMerchant) {
        this.taxCollectedByMerchant = taxCollectedByMerchant;
    }

    /**
     *
     * @param tipsCollectedByMerchant
     */
    public void setTipsCollectedByMerchant(String tipsCollectedByMerchant) {
        this.tipsCollectedByMerchant = tipsCollectedByMerchant;
    }

    /**
     *
     * @param voids
     */
    public void setVoids(String voids) {
        this.voids = voids;
    }
}
