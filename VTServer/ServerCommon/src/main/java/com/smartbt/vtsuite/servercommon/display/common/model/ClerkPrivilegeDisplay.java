/*
 ** File: MerchantParameterMobile.java
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
import java.util.Map;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Merchant Parameter Mobile class - containing all sets/gets
 */
@XmlRootElement
public class ClerkPrivilegeDisplay implements Serializable{

    private boolean allowCardPayment;
    private boolean allowCardPaymentManual;
    private boolean allowMyTransactions;
    private boolean allowMyCustomers;
    private boolean allowRefund;
    private boolean allowRefundManual;
    private boolean allowDashboard;
    private boolean allowVoid;
    private boolean allowChangeSettings;
    private boolean allowCreditSaleSwipe;
    private boolean allowDebitSaleSwipe;
    private boolean allowCreditSaleManual;
    private boolean allowCreditRefundSwipe;
    private boolean allowDebitRefundSwipe;
    private boolean allowCreditSaleVoid;
    private boolean allowDebitSaleVoid;
    private boolean allowCreditBalanceInq;
    private boolean allowDebitBalanceInq;
    private boolean allowCreditForce;
    private boolean allowCheckPayment;
    private boolean allowRecurringPayment;
    private boolean allowMyDashboard;
    private boolean allowReports;
    private boolean allowWatchDogs;
    private boolean allowSettings;

    /**
     * The default constructor
     */
    public ClerkPrivilegeDisplay() {
    }

    /**
     *
     * @return
     */
    public boolean isAllowCardPayment() {
        return allowCardPayment;
    }

    /**
     *
     * @return
     */
    public boolean isAllowCardPaymentManual() {
        return allowCardPaymentManual;
    }

    /**
     *
     * @return
     */
    public boolean isAllowChangeSettings() {
        return allowChangeSettings;
    }

    /**
     *
     * @return
     */
    public boolean isAllowDashboard() {
        return allowDashboard;
    }

    /**
     *
     * @return
     */
    public boolean isAllowMyCustomers() {
        return allowMyCustomers;
    }

    /**
     *
     * @return
     */
    public boolean isAllowMyTransactions() {
        return allowMyTransactions;
    }

    /**
     *
     * @return
     */
    public boolean isAllowRefund() {
        return allowRefund;
    }

    /**
     *
     * @return
     */
    public boolean isAllowRefundManual() {
        return allowRefundManual;
    }

    /**
     *
     * @return
     */
    public boolean isAllowVoid() {
        return allowVoid;
    }

    /**
     *
     * @param allowCardPayment
     */
    public void setAllowCardPayment(boolean allowCardPayment) {
        this.allowCardPayment = allowCardPayment;
    }

    /**
     *
     * @param allowCardPaymentManual
     */
    public void setAllowCardPaymentManual(boolean allowCardPaymentManual) {
        this.allowCardPaymentManual = allowCardPaymentManual;
    }

    /**
     *
     * @param allowChangeSettings
     */
    public void setAllowChangeSettings(boolean allowChangeSettings) {
        this.allowChangeSettings = allowChangeSettings;
    }

    /**
     *
     * @param allowDashboard
     */
    public void setAllowDashboard(boolean allowDashboard) {
        this.allowDashboard = allowDashboard;
    }

    /**
     *
     * @param allowMyCustomers
     */
    public void setAllowMyCustomers(boolean allowMyCustomers) {
        this.allowMyCustomers = allowMyCustomers;
    }

    /**
     *
     * @param allowMyTransactions
     */
    public void setAllowMyTransactions(boolean allowMyTransactions) {
        this.allowMyTransactions = allowMyTransactions;
    }

    /**
     *
     * @param allowRefund
     */
    public void setAllowRefund(boolean allowRefund) {
        this.allowRefund = allowRefund;
    }

    /**
     *
     * @param allowRefundManual
     */
    public void setAllowRefundManual(boolean allowRefundManual) {
        this.allowRefundManual = allowRefundManual;
    }

    /**
     *
     * @param allowVoid
     */
    public void setAllowVoid(boolean allowVoid) {
        this.allowVoid = allowVoid;
    }

    /**
     *
     * @return
     */
    public boolean isAllowCreditSaleSwipe() {
        return allowCreditSaleSwipe;
    }

    /**
     *
     * @return
     */
    public boolean isAllowDebitSaleSwipe() {
        return allowDebitSaleSwipe;
    }

    /**
     *
     * @return
     */
    public boolean isAllowCreditSaleManual() {
        return allowCreditSaleManual;
    }

    /**
     *
     * @return
     */
    public boolean isAllowCreditRefundSwipe() {
        return allowCreditRefundSwipe;
    }

    /**
     *
     * @return
     */
    public boolean isAllowDebitRefundSwipe() {
        return allowDebitRefundSwipe;
    }

    /**
     *
     * @return
     */
    public boolean isAllowCreditSaleVoid() {
        return allowCreditSaleVoid;
    }

    /**
     *
     * @return
     */
    public boolean isAllowDebitSaleVoid() {
        return allowDebitSaleVoid;
    }

    /**
     *
     * @return
     */
    public boolean isAllowCreditBalanceInq() {
        return allowCreditBalanceInq;
    }

    /**
     *
     * @return
     */
    public boolean isAllowDebitBalanceInq() {
        return allowDebitBalanceInq;
    }

    /**
     *
     * @return
     */
    public boolean isAllowCreditForce() {
        return allowCreditForce;
    }

    /**
     *
     * @param allowCreditSaleSwipe
     */
    public void setAllowCreditSaleSwipe(boolean allowCreditSaleSwipe) {
        this.allowCreditSaleSwipe = allowCreditSaleSwipe;
    }

    /**
     *
     * @param allowDebitSaleSwipe
     */
    public void setAllowDebitSaleSwipe(boolean allowDebitSaleSwipe) {
        this.allowDebitSaleSwipe = allowDebitSaleSwipe;
    }

    /**
     *
     * @param allowCreditSaleManual
     */
    public void setAllowCreditSaleManual(boolean allowCreditSaleManual) {
        this.allowCreditSaleManual = allowCreditSaleManual;
    }

    /**
     *
     * @param allowCreditRefundSwipe
     */
    public void setAllowCreditRefundSwipe(boolean allowCreditRefundSwipe) {
        this.allowCreditRefundSwipe = allowCreditRefundSwipe;
    }

    /**
     *
     * @param allowDebitRefundSwipe
     */
    public void setAllowDebitRefundSwipe(boolean allowDebitRefundSwipe) {
        this.allowDebitRefundSwipe = allowDebitRefundSwipe;
    }

    /**
     *
     * @param allowCreditSaleVoid
     */
    public void setAllowCreditSaleVoid(boolean allowCreditSaleVoid) {
        this.allowCreditSaleVoid = allowCreditSaleVoid;
    }

    /**
     *
     * @param allowDebitSaleVoid
     */
    public void setAllowDebitSaleVoid(boolean allowDebitSaleVoid) {
        this.allowDebitSaleVoid = allowDebitSaleVoid;
    }

    /**
     *
     * @param allowCreditBalanceInq
     */
    public void setAllowCreditBalanceInq(boolean allowCreditBalanceInq) {
        this.allowCreditBalanceInq = allowCreditBalanceInq;
    }

    /**
     *
     * @param allowDebitBalanceInq
     */
    public void setAllowDebitBalanceInq(boolean allowDebitBalanceInq) {
        this.allowDebitBalanceInq = allowDebitBalanceInq;
    }

    /**
     *
     * @param allowCreditForce
     */
    public void setAllowCreditForce(boolean allowCreditForce) {
        this.allowCreditForce = allowCreditForce;
    }

    /**
     *
     * @return
     */
    public boolean isAllowCheckPayment() {
        return allowCheckPayment;
    }

    /**
     *
     * @return
     */
    public boolean isAllowRecurringPayment() {
        return allowRecurringPayment;
    }

    /**
     *
     * @return
     */
    public boolean isAllowMyDashboard() {
        return allowMyDashboard;
    }

    /**
     *
     * @return
     */
    public boolean isAllowReports() {
        return allowReports;
    }

    /**
     *
     * @return
     */
    public boolean isAllowWatchDogs() {
        return allowWatchDogs;
    }

    /**
     *
     * @return
     */
    public boolean isAllowSettings() {
        return allowSettings;
    }

    /**
     *
     * @param allowCheckPayment
     */
    public void setAllowCheckPayment(boolean allowCheckPayment) {
        this.allowCheckPayment = allowCheckPayment;
    }

    /**
     *
     * @param allowRecurringPayment
     */
    public void setAllowRecurringPayment(boolean allowRecurringPayment) {
        this.allowRecurringPayment = allowRecurringPayment;
    }

    /**
     *
     * @param allowMyDashboard
     */
    public void setAllowMyDashboard(boolean allowMyDashboard) {
        this.allowMyDashboard = allowMyDashboard;
    }

    /**
     *
     * @param allowReports
     */
    public void setAllowReports(boolean allowReports) {
        this.allowReports = allowReports;
    }

    /**
     *
     * @param allowWatchDogs
     */
    public void setAllowWatchDogs(boolean allowWatchDogs) {
        this.allowWatchDogs = allowWatchDogs;
    }

    /**
     *
     * @param allowSettings
     */
    public void setAllowSettings(boolean allowSettings) {
        this.allowSettings = allowSettings;
    }
    
    /**
     * Initializes class from map
     *
     * @param map The data
     */
    public void initFromMap(Map<String, Object> map) {
        if (map != null) {
            allowCardPayment = Boolean.parseBoolean(map.get("allowCardPayment").toString());
            allowCardPaymentManual = Boolean.parseBoolean(map.get("allowCardPaymentManual").toString());
            allowMyTransactions = Boolean.parseBoolean(map.get("allowMyTransactions").toString());
            allowMyCustomers = Boolean.parseBoolean(map.get("allowMyCustomers").toString());
            allowRefund = Boolean.parseBoolean(map.get("allowRefund").toString());
            allowRefundManual = Boolean.parseBoolean(map.get("allowRefundManual").toString());
            allowDashboard = Boolean.parseBoolean(map.get("allowDashboard").toString());
            allowVoid = Boolean.parseBoolean(map.get("allowVoid").toString());
            allowChangeSettings = Boolean.parseBoolean(map.get("allowChangeSettings").toString());
            allowCreditSaleSwipe = Boolean.parseBoolean(map.get("allowCreditSaleSwipe").toString());
            allowDebitSaleSwipe = Boolean.parseBoolean(map.get("allowDebitSaleSwipe").toString());
            allowCreditSaleManual = Boolean.parseBoolean(map.get("allowCreditSaleManual").toString());
            allowCreditRefundSwipe = Boolean.parseBoolean(map.get("allowCreditRefundSwipe").toString());
            allowDebitRefundSwipe = Boolean.parseBoolean(map.get("allowDebitRefundSwipe").toString());
            allowCreditSaleVoid = Boolean.parseBoolean(map.get("allowCreditSaleVoid").toString());
            allowDebitSaleVoid = Boolean.parseBoolean(map.get("allowDebitSaleVoid").toString());
            allowCreditBalanceInq = Boolean.parseBoolean(map.get("allowCreditBalanceInq").toString());
            allowDebitBalanceInq = Boolean.parseBoolean(map.get("allowDebitBalanceInq").toString());
            allowCreditForce = Boolean.parseBoolean(map.get("allowCreditForce").toString());
            allowCheckPayment = Boolean.parseBoolean(map.get("allowCheckPayment").toString());
            allowRecurringPayment = Boolean.parseBoolean(map.get("allowRecurringPayment").toString());
            allowMyDashboard = Boolean.parseBoolean(map.get("allowMyDashboard").toString());
            allowReports = Boolean.parseBoolean(map.get("allowReports").toString());
            allowWatchDogs = Boolean.parseBoolean(map.get("allowWatchDogs").toString());
            allowSettings = Boolean.parseBoolean(map.get("allowSettings").toString());

        }
    }
}
