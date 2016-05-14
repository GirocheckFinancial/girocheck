/*
 ** File: CustomerDisplay.java
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
 * The Customer Display Class - containing all sets/gets
 */
@XmlRootElement
public class CustomerDisplay implements Serializable {

    private Integer id;
    private String name;
    private String description;
    private String number;
    private Boolean active;
    private String activationDate;
    private String deactivationDate;
    private List<AddressDisplay> address;
    private List<TelephoneDisplay> telephones;

    public CustomerDisplay(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    
    
    /**
     * The default constructor
     */
    public CustomerDisplay() {
    }

    /**
     *
     * @return
     */
    public String getNumber() {
        return number;
    }

    /**
     *
     * @param number
     */
    public void setNumber(String number) {
        this.number = number;
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
    public String getName() {
        return name;
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
    public List<AddressDisplay> getAddress() {
        return address;
    }

    /**
     *
     * @param address
     */
    public void setAddress(List<AddressDisplay> address) {
        this.address = address;
    }

    /**
     *
     * @return
     */
    public List<TelephoneDisplay> getTelephones() {
        return telephones;
    }

    /**
     *
     * @param telephones
     */
    public void setTelephones(List<TelephoneDisplay> telephones) {
        this.telephones = telephones;
    }
}
