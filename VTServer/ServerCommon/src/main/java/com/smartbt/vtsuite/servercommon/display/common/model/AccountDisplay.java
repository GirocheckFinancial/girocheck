/*
 ** File: AccountDisplay.java
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

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ariel Saavedra
 */
@XmlRootElement
public class AccountDisplay implements Serializable {

    /**
     * COMMON parameters
     */
    private int id;
    private String pan;
    /**
     * CARD SALE parameters
     */
    private String expDate;
    private String cardHolderName;
    private String billingZipCode;
    private String cardBrand;
    /**
     * CHECK parameters
     */
    private Integer routingNumber;
    private Integer checkNumber;
    private java.util.Date date;

    /**
     *
     */
    public AccountDisplay() {
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getPan() {
        return pan;
    }

    /**
     *
     * @param pan
     */
    public void setPan(String pan) {
        this.pan = pan;
    }

    /**
     *
     * @return
     */
    public String getExpDate() {
        return expDate;
    }

    /**
     *
     * @param expDate
     */
    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    /**
     *
     * @return
     */
    public String getCardHolderName() {
        return cardHolderName;
    }

    /**
     *
     * @param cardHolderName
     */
    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    /**
     *
     * @return
     */
    public String getBillingZipCode() {
        return billingZipCode;
    }

    /**
     *
     * @param billingZipCode
     */
    public void setBillingZipCode(String billingZipCode) {
        this.billingZipCode = billingZipCode;
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
    public Integer getRoutingNumber() {
        return routingNumber;
    }

    /**
     *
     * @param routingNumber
     */
    public void setRoutingNumber(Integer routingNumber) {
        this.routingNumber = routingNumber;
    }

    /**
     *
     * @return
     */
    public Integer getCheckNumber() {
        return checkNumber;
    }

    /**
     *
     * @param checkNumber
     */
    public void setCheckNumber(Integer checkNumber) {
        this.checkNumber = checkNumber;
    }

    /**
     *
     * @return
     */
    public Date getDate() {
        return date;
    }

    /**
     *
     * @param date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Extra or modified methods
     */
    /**
     * Get Check MICR
     *
     * @return
     */
    public String getCheckMICR() {
        return "T" + routingNumber + "A" + pan + "C" + checkNumber;
    }
}
