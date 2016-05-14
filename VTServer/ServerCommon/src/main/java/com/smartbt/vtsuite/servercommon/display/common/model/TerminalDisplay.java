/*
 ** File: TerminalDisplay.java
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
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Terminal Display Class - containing all sets/gets
 */
@XmlRootElement
public class TerminalDisplay implements Serializable {

    private Integer id;
    private String serialNumber;
    private String description;
    private Boolean active;
    private String terminalId;
    private List<ParameterDisplay> parameter;
    private String activationDate;
    private String deactivationDate;
    private Boolean monitored;
    private String customerName;
    private String merchantName;
    private String productType;
    private String application;
    private int idApplication;

    public TerminalDisplay(Integer id, String serialNumber) {
        this.id = id;
        this.serialNumber = serialNumber;
    }

    
    
    /**
     * The default constructor
     */
    public TerminalDisplay() {
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
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     *
     * @param serialNumber
     */
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
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
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
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
     * @param active
     */
    public void setActive(Boolean active) {
        this.active = active;
    }

    /**
     *
     * @return
     */
    public String getTerminalId() {
        return terminalId;
    }

    /**
     *
     * @param terminalId
     */
    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    /**
     *
     * @return
     */
    public List<ParameterDisplay> getParameter() {
        return parameter;
    }

    /**
     *
     * @param parameter
     */
    public void setParameter(List<ParameterDisplay> parameter) {
        this.parameter = parameter;
    }

    /**
     *
     * @return
     */
    public String getActivationDate() {
        return activationDate;
    }

    /**
     *
     * @param activationDate
     */
    public void setActivationDate(String activationDate) {
        this.activationDate = activationDate;
    }

    /**
     *
     * @return
     */
    public String getDeactivationDate() {
        return deactivationDate;
    }

    /**
     *
     * @param deactivationDate
     */
    public void setDeactivationDate(String deactivationDate) {
        this.deactivationDate = deactivationDate;
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
    public String getProductType() {
        return productType;
    }

    /**
     *
     * @param productType
     */
    public void setProductType(String productType) {
        this.productType = productType;
    }

    /**
     *
     * @return
     */
    public String getApplication() {
        return application;
    }

    public void setIdApplication(int idApplication) {
        this.idApplication = idApplication;
    }

    public int getIdApplication() {
        return idApplication;
    }

    /**
     *
     * @param application
     */
    public void setApplication(String application) {
        this.application = application;
    }

    public Boolean isMonitored() {
        return monitored;
    }

    public void setMonitored(Boolean monitored) {
        this.monitored = monitored;
    }

}
