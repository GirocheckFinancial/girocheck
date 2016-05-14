/*
 ** File: MerchantDisplay.java
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
 * The Settings Display Class - containing all sets/gets
 */
@XmlRootElement
public class SettingDisplay implements Serializable {

    private Map merchantParameters;
    private Map terminalParameters;
    private Map clerkPrivileges;
    private int merchantId;
    private String merchant;
    private String merchantName;
    private String merchantLogo;
    private int terminalId;
    private String terminal;
    private int customerId;
    private String customer;
    private String customerName;
    private String token;
    private String entityType;
    private Boolean firstTime;

    /**
     * THe default constructor
     */
    public SettingDisplay() {
    }

    /**
     *
     * @return
     */
    public Map getMerchantParameters() {
        return merchantParameters;
    }

    /**
     *
     * @param merchantParameters
     */
    public void setMerchantParameters(Map merchantParameters) {
        this.merchantParameters = merchantParameters;
    }

    /**
     *
     * @return
     */
    public Map getTerminalParameters() {
        return terminalParameters;
    }

    /**
     *
     * @param terminalParameters
     */
    public void setTerminalParameters(Map terminalParameters) {
        this.terminalParameters = terminalParameters;
    }

    /**
     *
     * @return
     */
    public Map getClerkPrivileges() {
        return clerkPrivileges;
    }

    /**
     *
     * @param clerkPrivileges
     */
    public void setClerkPrivileges(Map clerkPrivileges) {
        this.clerkPrivileges = clerkPrivileges;
    }

    /**
     *
     * @return
     */
    public String getMerchant() {
        return merchant;
    }

    /**
     *
     * @param merchant
     */
    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    /**
     *
     * @return
     */
    public int getMerchantId() {
        return merchantId;
    }

    /**
     *
     * @param merchantId
     */
    public void setMerchantId(int merchantId) {
        this.merchantId = merchantId;
    }

    /**
     *
     * @return
     */
    public String getTerminal() {
        return terminal;
    }

    /**
     *
     * @param terminal
     */
    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    /**
     *
     * @return
     */
    public int getTerminalId() {
        return terminalId;
    }

    /**
     *
     * @param terminalId
     */
    public void setTerminalId(int terminalId) {
        this.terminalId = terminalId;
    }

    /**
     *
     * @return
     */
    public String getCustomer() {
        return customer;
    }

    /**
     *
     * @param customer
     */
    public void setCustomer(String customer) {
        this.customer = customer;
    }

    /**
     *
     * @return
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     *
     * @param customerId
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     *
     * @return
     */
    public String getToken() {
        return token;
    }

    /**
     *
     * @param token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     *
     * @return
     */
    public String getEntityType() {
        return entityType;
    }

    /**
     *
     * @param entityType
     */
    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    /**
     *
     * @return
     */
    public String getMerchantLogo() {
        return merchantLogo;
    }

    /**
     *
     * @param merchantLogo
     */
    public void setMerchantLogo(String merchantLogo) {
        this.merchantLogo = merchantLogo;
    }

    /**
     *
     * @return
     */
    public Boolean isFirstTime() {
        return firstTime;
    }

    /**
     *
     * @param firstTime
     */
    public void setFirstTime(Boolean firstTime) {
        this.firstTime = firstTime;
    }

    /**
     *
     * @return
     */
    public String getMerchantName() {
        return merchantName;
    }

    /**
     *
     * @param merchantName
     */
    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    /**
     *
     * @return
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     *
     * @param customerName
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
