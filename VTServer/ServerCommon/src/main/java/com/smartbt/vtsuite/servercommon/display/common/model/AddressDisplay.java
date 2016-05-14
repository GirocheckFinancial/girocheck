/*
 ** File: AddressDisplay.java
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

import com.smartbt.vtsuite.common.VTSuiteMessages;
import com.smartbt.vtsuite.vtcommon.validator.Constants;
import com.smartbt.vtsuite.vtcommon.validator.RegExp;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * The Address Display Class - containing all sets/gets
 */
@XmlRootElement
public class AddressDisplay implements Serializable {

    private int id;
    private String country;

    @NotBlank(message = VTSuiteMessages.VALUE_IS_EMPTY + " (address1)")
    @Length(max = Constants.MEDIUM_TEXT_MAX_LENGTH, message = VTSuiteMessages.ERROR_LENGTH + " (address1)")
    @Pattern(regexp = RegExp.VALID_TEXT_VI_REG_EXP, message = VTSuiteMessages.ERROR_INVALID_CHARACTERS + " (address1)")
    private String address1;

    @Length(max = Constants.MEDIUM_TEXT_MAX_LENGTH, message = VTSuiteMessages.ERROR_LENGTH + " (address2)")
    @Pattern(regexp = RegExp.VALID_TEXT_VI_REG_EXP, message = VTSuiteMessages.ERROR_INVALID_CHARACTERS + " (address2)")
    private String address2;

    @NotBlank(message = VTSuiteMessages.VALUE_IS_EMPTY + " (city)")
    @Length(max = Constants.MEDIUM_TEXT_MAX_LENGTH, message = VTSuiteMessages.ERROR_LENGTH + " (city)")
    @Pattern(regexp = RegExp.VALID_TEXT_V_REG_EXP, message = VTSuiteMessages.ERROR_INVALID_CHARACTERS + " (city)")
    private String city;

    @NotNull
    private StateDisplay state;

    @NotBlank(message = VTSuiteMessages.VALUE_IS_EMPTY + " (zip)")
    @Length(min = Constants.ZIP_MIN_LENGTH, max = Constants.ZIP_MAX_LENGTH, message = VTSuiteMessages.ERROR_LENGTH + " (zip)")
    @Pattern(regexp = RegExp.VALID_TEXT_REG_EXP, message = VTSuiteMessages.ERROR_INVALID_CHARACTERS + " (zip)")
    private String zip;

    @NotNull
    private AddressTypeDisplay addressType;

    /**
     *
     * @return
     */
    public AddressTypeDisplay getAddressType() {
        return addressType;
    }

    /**
     *
     * @param addressType
     */
    public void setAddressType(AddressTypeDisplay addressType) {
        this.addressType = addressType;
    }

    /**
     *
     */
    public AddressDisplay() {
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
    public int getId() {
        return id;
    }

    /**
     *
     * @return
     */
    public StateDisplay getState() {
        return state;
    }

    /**
     *
     * @param state
     */
    public void setState(StateDisplay state) {
        this.state = state;
    }

    /**
     *
     * @return
     */
    public String getCountry() {
        return country;
    }

    /**
     *
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     *
     * @return
     */
    public String getAddress1() {
        return address1;
    }

    /**
     *
     * @param address1
     */
    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    /**
     *
     * @return
     */
    public String getAddress2() {
        return address2;
    }

    /**
     *
     * @param address2
     */
    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    /**
     *
     * @return
     */
    public String getCity() {
        return city;
    }

    /**
     *
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     *
     * @return
     */
    public String getZip() {
        return zip;
    }

    /**
     *
     * @param zip
     */
    public void setZip(String zip) {
        this.zip = zip;
    }
}
