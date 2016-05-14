/*
 ** File: BoardingDisplay.java
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
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The Boarding Display Class - containing all sets/gets
 */
@XmlRootElement
public class BoardingDisplay implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private int id;
    private Date activationDate;
    private Boolean active;
    private String boarded;
    private String terminalID;
    private String merchantNumber;
    private String customerNumber;
    private String sicCode;
    private String name;
    private String description;

    /**
     * The default constructor
     * @return 
     */
    public String getCustomerNumber() {
        return customerNumber;
    }

    /**
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @return
     */
    public String getMerchantNumber() {
        return merchantNumber;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return
     */
    public String getSicCode() {
        return sicCode;
    }

    /**
     *
     * @return
     */
    public String getTerminalID() {
        return terminalID;
    }

    /**
     *
     * @param customerNumber
     */
    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @param merchantNumber
     */
    public void setMerchantNumber(String merchantNumber) {
        this.merchantNumber = merchantNumber;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @param sicCode
     */
    public void setSicCode(String sicCode) {
        this.sicCode = sicCode;
    }

    /**
     *
     * @param terminalID
     */
    public void setTerminalID(String terminalID) {
        this.terminalID = terminalID;
    }

    /**
     *
     */
    public BoardingDisplay() {
    }

    private void setId(int value) {
        this.id = value;
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
     * @return
     */
    public Date getActivationDate() {
        return activationDate;
    }

    /**
     *
     * @param activationDate
     */
    public void setActivationDate(Date activationDate) {
        this.activationDate = activationDate;
    }

    /**
     *
     * @param value
     */
    public void setActive(Boolean value) {
        this.active = value;
    }

    /**
     *
     * @return
     */
    public Boolean getActive() {
        return active;
    }

    /**
     *
     * @param value
     */
    public void setBoarded(String value) {
        this.boarded = value;
    }

    /**
     *
     * @return
     */
    public String getBoarded() {
        return boarded;
    }
    
    @Override
    public String toString(){
        return ToStringBuilder.reflectionToString( this);
    }
    
}
