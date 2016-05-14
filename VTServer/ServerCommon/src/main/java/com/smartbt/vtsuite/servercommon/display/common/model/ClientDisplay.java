/*
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
import java.util.List;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Chris, Ariel Saavedra
 */
@XmlRootElement
public class ClientDisplay implements Serializable {

    private Integer id;

    @NotBlank(message = VTSuiteMessages.VALUE_IS_EMPTY + " (firstName)")
    @Length(max = Constants.STANDARD_TEXT_MAX_LENGTH, message = VTSuiteMessages.ERROR_LENGTH + " (firstName)")
    @Pattern(regexp = RegExp.VALID_TEXT_III_REG_EXP, message = VTSuiteMessages.ERROR_INVALID_CHARACTERS + " (firstName)")
    private String firstName;

    @NotBlank(message = VTSuiteMessages.VALUE_IS_EMPTY + " (lastName)")
    @Length(max = Constants.STANDARD_TEXT_MAX_LENGTH, message = VTSuiteMessages.ERROR_LENGTH + " (lastName)")
    @Pattern(regexp = RegExp.VALID_TEXT_III_REG_EXP, message = VTSuiteMessages.ERROR_INVALID_CHARACTERS + " (lastName)")
    private String lastName;

    @Length(max = Constants.MEDIUM_TEXT_MAX_LENGTH, message = VTSuiteMessages.ERROR_LENGTH + " (company)")
    @Pattern(regexp = RegExp.VALID_TEXT_VI_REG_EXP, message = VTSuiteMessages.ERROR_INVALID_CHARACTERS + " (company)")
    private String company;

    private List<AddressDisplay> clientAddressList;
    
    private List<TelephoneDisplay> clientTelephoneList;

    @Email(message = VTSuiteMessages.ERROR_INVALID_CHARACTERS + " (email)")
    private String email;

    private Boolean active;
    private MerchantDisplay merchant;
    private List<AccountDisplay> accounts;

    /**
     *
     * @return
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @return
     */
    public String getCompany() {
        return company;
    }

    /**
     *
     * @return
     */
    public List<AddressDisplay> getClientAddressList() {
        return clientAddressList;
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @return
     */
    public List<TelephoneDisplay> getClientTelephoneList() {
        return clientTelephoneList;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @param clientTelephoneList
     */
    public void setClientTelephoneList(List<TelephoneDisplay> clientTelephoneList) {
        this.clientTelephoneList = clientTelephoneList;
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
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *
     * @param company
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     *
     * @param clientAddressList
     */
    public void setClientAddressList(List<AddressDisplay> clientAddressList) {
        this.clientAddressList = clientAddressList;
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
    public MerchantDisplay getMerchant() {
        return merchant;
    }

    /**
     *
     * @param merchant
     */
    public void setMerchant(MerchantDisplay merchant) {
        this.merchant = merchant;
    }

    /**
     *
     * @param accounts
     */
    public void setAccounts(List<AccountDisplay> accounts) {
        this.accounts = accounts;
    }

    /**
     *
     * @return
     */
    public List<AccountDisplay> getAccounts() {
        return accounts;
    }
}
