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
package com.smartbt.girocheck.servercommon.display;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * The Terminal Display Class - containing all sets/gets
 */
@XmlRootElement
public class TerminalDisplay implements Serializable {
    
//    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(TerminalDisplay.class);

    private Integer id;
    private String serialNumber;
    private String terminalUser;
    private String terminalPassword;
    private String description;
    private String idPOSOrderExp;
    
    private Integer idMerchant;
    private Boolean hasTransaction;
    private Boolean validate = true;
    
    public void print() {
//        log.debug( "Printing terminal..." );
//        log.debug( ReflectionToStringBuilder.reflectionToString( this ) );
    }
    
    public TerminalDisplay(Integer id, String serialNumber) {
        this.id = id;
        this.serialNumber = serialNumber;
    }

    public void setHasTransaction( Boolean hasTransaction ) {
        this.hasTransaction = hasTransaction;
    }

    public void setIdMerchant( Integer idMerchant ) {
        this.idMerchant = idMerchant;
    }

    public void setTerminalPassword( String terminalPassword ) {
        this.terminalPassword = terminalPassword;
    }

    public void setTerminalUser( String terminalUser ) {
        this.terminalUser = terminalUser;
    }

    public Boolean isHasTransaction() {
        return hasTransaction;
    }

    public Integer getIdMerchant() {
        return idMerchant;
    }

    public String getTerminalPassword() {
        return terminalPassword;
    }

    public String getTerminalUser() {
        return terminalUser;
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
     * @return the idPOSOrderExp
     */
    public String getIdPOSOrderExp() {
        return idPOSOrderExp;
    }

    /**
     * @param idPOSOrderExp the idPOSOrderExp to set
     */
    public void setIdPOSOrderExp(String idPOSOrderExp) {
        this.idPOSOrderExp = idPOSOrderExp;
    }

    /**
     * @return the validate
     */
    public Boolean getValidate() {
        return validate;
    }

    /**
     * @param validate the validate to set
     */
    public void setValidate(Boolean validate) {
        this.validate = validate;
    }


   
}
