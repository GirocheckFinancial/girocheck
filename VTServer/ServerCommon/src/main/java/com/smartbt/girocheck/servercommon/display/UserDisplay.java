/*
 ** File: UserDisplay.java
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
package com.smartbt.girocheck.servercommon.display;

import com.smartbt.girocheck.common.VTSuiteMessages;
import com.smartbt.vtsuite.vtcommon.validator.Constants;
import com.smartbt.vtsuite.vtcommon.validator.RegExp;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * The User Display Class - containing all sets/gets
 */
@XmlRootElement
public class UserDisplay implements Serializable {

    private Integer id;
    @NotNull
    private RoleDisplay role;
    
    private String username;
    
    @NotBlank(message = VTSuiteMessages.VALUE_IS_EMPTY + " (lastName)")
    @Length(max = Constants.STANDARD_TEXT_MAX_LENGTH, message = VTSuiteMessages.ERROR_LENGTH + " (lastName)")
    @Pattern(regexp = RegExp.VALID_TEXT_III_REG_EXP, message = VTSuiteMessages.ERROR_INVALID_CHARACTERS + " (lastName)")
    private String lastName;
    
    @NotBlank(message = VTSuiteMessages.VALUE_IS_EMPTY + " (firstName)")
    @Length(max = Constants.STANDARD_TEXT_MAX_LENGTH, message = VTSuiteMessages.ERROR_LENGTH + " (firstName)")
    @Pattern(regexp = RegExp.VALID_TEXT_III_REG_EXP, message = VTSuiteMessages.ERROR_INVALID_CHARACTERS + " (firstName)")
    private String firstName;
    
    private boolean active;
    
    private String password;
    
//    private CustomerDisplay customer;
//    private MerchantDisplay merchant;
//    private TerminalDisplay terminal;
    private String email; 

    /**
     * The default constructor
     */
    public UserDisplay() {
    }

//    public CustomerDisplay getCustomer() {
//        return customer;
//    }

//    public void setCustomer(CustomerDisplay customer) {
//        this.customer = customer;
//    }

//    public MerchantDisplay getMerchant() {
//        return merchant;
//    }
//
//    public void setMerchant(MerchantDisplay merchant) {
//        this.merchant = merchant;
//    }
//
//    public TerminalDisplay getTerminal() {
//        return terminal;
//    }
//
//    public void setTerminal(TerminalDisplay terminal) {
//        this.terminal = terminal;
//    }

    /**
     *
     * @return
     */
    public RoleDisplay getRole() {
        return role;
    }

    /**
     *
     * @param role
     */
    public void setRole(RoleDisplay role) {
        this.role = role;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
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
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
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
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
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
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * @return
     */
    public boolean getActive() {
        return active;
    }

    /**
     *
     * @param active
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
