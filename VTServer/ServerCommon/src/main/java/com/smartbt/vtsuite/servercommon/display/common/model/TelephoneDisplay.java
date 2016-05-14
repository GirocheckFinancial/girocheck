/*
 ** File: DeviceDisplay.java
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
public class TelephoneDisplay implements Serializable {

    int id;

    @NotBlank(message = VTSuiteMessages.VALUE_IS_EMPTY + " (number)")
    @Length(max = Constants.PHONE_MAX_LENGTH, message = VTSuiteMessages.ERROR_LENGTH + " (number)")
    @Pattern(regexp = RegExp.PHONE_REG_EXP, message = VTSuiteMessages.ERROR_INVALID_CHARACTERS + " (number)")
    private String number;

    @NotNull
    private PhoneTypeDisplay telephoneType;

    /**
     * The default constructor
     */
    public TelephoneDisplay() {
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
    public PhoneTypeDisplay getTelephoneType() {
        return telephoneType;
    }

    /**
     *
     * @param telephoneType
     */
    public void setTelephoneType(PhoneTypeDisplay telephoneType) {
        this.telephoneType = telephoneType;
    }
}
