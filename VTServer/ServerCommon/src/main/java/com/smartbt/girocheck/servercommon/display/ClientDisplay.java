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
package com.smartbt.girocheck.servercommon.display;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Alejo
 */
@XmlRootElement
public class ClientDisplay implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    private Integer id;
//    private com.smartbt.girocheck.servercommon.model.Address address;
    private String address;
    private String firstName;
    private String lastName;
    private String telephone;
    private String email;
    private String maskSS;
    private String zipcode;
    private String state;
    private String city;
    private Boolean blackList;
    private Boolean optOut;

//    private Boolean active;
//    private java.util.Date createdAt;
//    private java.sql.Blob addressForm;
//    private java.sql.Blob achForm;
//    private java.util.Date bornDate;
//    private String ssn;
//    private String idBeneficiary;
//    private java.util.Set<com.smartbt.girocheck.servercommon.model.CreditCard> data_SC = new java.util.HashSet<com.smartbt.girocheck.servercommon.model.CreditCard>();
//
//    private java.util.Set<com.smartbt.girocheck.servercommon.model.PersonalIdentification> data_SD = new java.util.HashSet<com.smartbt.girocheck.servercommon.model.PersonalIdentification>();
//
//    private java.util.Set<com.smartbt.girocheck.servercommon.model.Transaction> transaction = new java.util.HashSet<com.smartbt.girocheck.servercommon.model.Transaction>();
//
//    private java.util.Set<com.smartbt.girocheck.servercommon.model.Check> check = new java.util.HashSet<com.smartbt.girocheck.servercommon.model.Check>();
    public ClientDisplay() {
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * @param telephone the telephone to set
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
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

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the zipcode
     */
    public String getZipcode() {
        return zipcode;
    }

    /**
     * @param zipcode the zipcode to set
     */
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the maskSS
     */
    public String getMaskSS() {
        return "*****" + maskSS;
    }

    /**
     * @param maskSS the maskSS to set
     */
    public void setMaskSS(String maskSS) {
        this.maskSS = maskSS;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the blackList
     */
    public Boolean getBlackList() {
        return blackList;
    }

    /**
     * @param blackList the blackList to set
     */
    public void setBlackList(Boolean blackList) {
        this.blackList = blackList;
    }

    /**
     * @return the excludeSms
     */
    public Boolean getOptOut() {
        return optOut;
    }

    /**
     * @param excludeSms the excludeSms to set
     */
    public void setOptOut(Boolean optOut) {
        this.optOut = optOut;
    }
    /**
     * @return the address
     */
//    public com.smartbt.girocheck.servercommon.model.Address getAddress() {
//        return address;
//    }
//
//    /**
//     * @param address the address to set
//     */
//    public void setAddress(com.smartbt.girocheck.servercommon.model.Address address) {
//        this.address = address;
//    }
//    /**
//     * @return the active
//     */
//    public Boolean getActive() {
//        return active;
//    }
//
//    /**
//     * @param active the active to set
//     */
//    public void setActive(Boolean active) {
//        this.active = active;
//    }
//
//    /**
//     * @return the createdAt
//     */
//    public java.util.Date getCreatedAt() {
//        return createdAt;
//    }
//
//    /**
//     * @param createdAt the createdAt to set
//     */
//    public void setCreatedAt(java.util.Date createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    /**
//     * @return the addressForm
//     */
//    public java.sql.Blob getAddressForm() {
//        return addressForm;
//    }
//
//    /**
//     * @param addressForm the addressForm to set
//     */
//    public void setAddressForm(java.sql.Blob addressForm) {
//        this.addressForm = addressForm;
//    }
//
//    /**
//     * @return the achForm
//     */
//    public java.sql.Blob getAchForm() {
//        return achForm;
//    }
//
//    /**
//     * @param achForm the achForm to set
//     */
//    public void setAchForm(java.sql.Blob achForm) {
//        this.achForm = achForm;
//    }
//
//    /**
//     * @return the bornDate
//     */
//    public java.util.Date getBornDate() {
//        return bornDate;
//    }
//
//    /**
//     * @param bornDate the bornDate to set
//     */
//    public void setBornDate(java.util.Date bornDate) {
//        this.bornDate = bornDate;
//    }
//
//    /**
//     * @return the ssn
//     */
//    public String getSsn() {
//        return ssn;
//    }
//
//    /**
//     * @param ssn the ssn to set
//     */
//    public void setSsn(String ssn) {
//        this.ssn = ssn;
//    }
//
//    /**
//     * @return the idBeneficiary
//     */
//    public String getIdBeneficiary() {
//        return idBeneficiary;
//    }
//
//    /**
//     * @param idBeneficiary the idBeneficiary to set
//     */
//    public void setIdBeneficiary(String idBeneficiary) {
//        this.idBeneficiary = idBeneficiary;
//    }
//
//    /**
//     * @return the data_SC
//     */
//    public java.util.Set<com.smartbt.girocheck.servercommon.model.CreditCard> getData_SC() {
//        return data_SC;
//    }
//
//    /**
//     * @param data_SC the data_SC to set
//     */
//    public void setData_SC(java.util.Set<com.smartbt.girocheck.servercommon.model.CreditCard> data_SC) {
//        this.data_SC = data_SC;
//    }
//
//    /**
//     * @return the data_SD
//     */
//    public java.util.Set<com.smartbt.girocheck.servercommon.model.PersonalIdentification> getData_SD() {
//        return data_SD;
//    }
//
//    /**
//     * @param data_SD the data_SD to set
//     */
//    public void setData_SD(java.util.Set<com.smartbt.girocheck.servercommon.model.PersonalIdentification> data_SD) {
//        this.data_SD = data_SD;
//    }
//
//    /**
//     * @return the transaction
//     */
//    public java.util.Set<com.smartbt.girocheck.servercommon.model.Transaction> getTransaction() {
//        return transaction;
//    }
//
//    /**
//     * @param transaction the transaction to set
//     */
//    public void setTransaction(java.util.Set<com.smartbt.girocheck.servercommon.model.Transaction> transaction) {
//        this.transaction = transaction;
//    }
//
//    /**
//     * @return the check
//     */
//    public java.util.Set<com.smartbt.girocheck.servercommon.model.Check> getCheck() {
//        return check;
//    }
//
//    /**
//     * @param check the check to set
//     */
//    public void setCheck(java.util.Set<com.smartbt.girocheck.servercommon.model.Check> check) {
//        this.check = check;
//    }
}