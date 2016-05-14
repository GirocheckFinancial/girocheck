/**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 *
 * This is an automatic generated file. It will be regenerated every time you
 * generate persistence class.
 *
 * Modifying its content may cause the program not work, or your work may lost.
 */
/**
 * Licensee: SMART BUSINESS TECHNOLOGY License Type: Purchased
 */
package com.smartbt.vtsuite.servercommon.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Stores all customers.
 */
@XmlRootElement
public class Customer implements Serializable {

    public Customer() {
    }

    private int id;
    
    private com.smartbt.vtsuite.servercommon.model.Group group;

    private String number;

    private String name;

    private String description;

    private Boolean active;

    private java.util.Date activationDate;

    private java.util.Date deactivationDate;

    private java.util.Date dateEstablished;
    
    private java.util.Collection<com.smartbt.vtsuite.servercommon.model.Merchant> merchant = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.Merchant>();
    
    private java.util.Collection<com.smartbt.vtsuite.servercommon.model.Clerk> clerk = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.Clerk>();
    
    private java.util.Collection<com.smartbt.vtsuite.servercommon.model.CustomerParameterValue> customerParameterValue = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.CustomerParameterValue>();
    
    private java.util.Collection<com.smartbt.vtsuite.servercommon.model.CustomerAddress> customerAddress = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.CustomerAddress>();
    
    private java.util.Collection<com.smartbt.vtsuite.servercommon.model.CustomerTelephone> customerTelephone = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.CustomerTelephone>();
    
    private java.util.Collection<com.smartbt.vtsuite.servercommon.model.WatchdogEntity> watchdogEntity = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.WatchdogEntity>();

    /**
     * Table record identification.
     */
    public void setId(int value) {
        this.id = value;
    }

    /**
     * Table record identification.
     */
    public int getId() {
        return id;
    }

    public void setNumber(String value) {
        this.number = value;
    }

    public String getNumber() {
        return number;
    }

    /**
     * Customer's name.
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Customer's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Customer's description.
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Customer's description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Customer's flag for activate/deactivate.
     */
    @JsonIgnore
    public void setActive(boolean value) {
        setActive(new Boolean(value));
    }

    /**
     * Customer's flag for activate/deactivate.
     */
    public void setActive(Boolean value) {
        this.active = value;
    }

    /**
     * Customer's flag for activate/deactivate.
     */
    public Boolean getActive() {
        return active;
    }

    public void setActivationDate(java.util.Date value) {
        this.activationDate = value;
    }

    public java.util.Date getActivationDate() {
        return activationDate;
    }

    public void setDeactivationDate(java.util.Date value) {
        this.deactivationDate = value;
    }

    public java.util.Date getDeactivationDate() {
        return deactivationDate;
    }

    public void setDateEstablished(java.util.Date value) {
        this.dateEstablished = value;
    }

    public java.util.Date getDateEstablished() {
        return dateEstablished;
    }

    public void setGroup(com.smartbt.vtsuite.servercommon.model.Group value) {
        this.group = value;
    }

    @JsonIgnore
    public com.smartbt.vtsuite.servercommon.model.Group getGroup() {
        return group;
    }
    @JsonProperty("merchant")
    public void setMerchant(java.util.Collection<com.smartbt.vtsuite.servercommon.model.Merchant> value) {
        this.merchant = value;
    }

    @JsonIgnore
    public java.util.Collection<com.smartbt.vtsuite.servercommon.model.Merchant> getMerchant() {
        return merchant;
    }

    public void setClerk(java.util.Collection<com.smartbt.vtsuite.servercommon.model.Clerk> value) {
        this.clerk = value;
    }

    @JsonIgnore
    public java.util.Collection<com.smartbt.vtsuite.servercommon.model.Clerk> getClerk() {
        return clerk;
    }

    public void setCustomerParameterValue(java.util.Collection<com.smartbt.vtsuite.servercommon.model.CustomerParameterValue> value) {
        this.customerParameterValue = value;
    }

    @JsonIgnore
    public java.util.Collection<com.smartbt.vtsuite.servercommon.model.CustomerParameterValue> getCustomerParameterValue() {
        return customerParameterValue;
    }

    public void setCustomerAddress(java.util.Collection<com.smartbt.vtsuite.servercommon.model.CustomerAddress> value) {
        this.customerAddress = value;
    }

    @JsonIgnore
    public java.util.Collection<com.smartbt.vtsuite.servercommon.model.CustomerAddress> getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerTelephone(java.util.Collection<com.smartbt.vtsuite.servercommon.model.CustomerTelephone> value) {
        this.customerTelephone = value;
    }
    @JsonIgnore
    public java.util.Collection<com.smartbt.vtsuite.servercommon.model.CustomerTelephone> getCustomerTelephone() {
        return customerTelephone;
    }

    public void setWatchdogEntity(java.util.Collection<com.smartbt.vtsuite.servercommon.model.WatchdogEntity> value) {
        this.watchdogEntity = value;
    }
    @JsonIgnore
    public java.util.Collection<com.smartbt.vtsuite.servercommon.model.WatchdogEntity> getWatchdogEntity() {
        return watchdogEntity;
    }

    public void addMerchant(com.smartbt.vtsuite.servercommon.model.Merchant m) {

        getMerchant().add(m);
        m.setCustomer(this);
    }

    public void addCustomerAddress(com.smartbt.vtsuite.servercommon.model.CustomerAddress ca) {

        getCustomerAddress().add(ca);
        ca.setCustomer(this);
    }

    public void addCustomerTelephone(com.smartbt.vtsuite.servercommon.model.CustomerTelephone ct) {

        getCustomerTelephone().add(ct);
        ct.setCustomer(this);
    }

    public void addClerk(com.smartbt.vtsuite.servercommon.model.Clerk c) {

        getClerk().add(c);
        c.setCustomer(this);
    }

    public String toString() {
        return String.valueOf(getId());
    }

}
