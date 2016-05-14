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
import java.util.Collection;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Stores all clerks.
 */
@javax.xml.bind.annotation.XmlRootElement
public class Clerk implements Serializable {

    public Clerk() {
    }

    private static final long serialVersionUID = 1L;

    private int id;
    
    private com.smartbt.vtsuite.servercommon.model.Customer customer;
    
    private com.smartbt.vtsuite.servercommon.model.Merchant merchant;
    
    private com.smartbt.vtsuite.servercommon.model.ClerkRole clerkRole;
    
    private com.smartbt.vtsuite.servercommon.model.Terminal terminal;
    
    private java.util.Collection<com.smartbt.vtsuite.servercommon.model.Monitoring> monitorings = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.Monitoring>();

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private Boolean active;

    private String middleInitial;

    private java.util.Date deactivationDate;

    private boolean firstTime;
    
    private java.util.Collection<com.smartbt.vtsuite.servercommon.model.Transaction> transaction = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.Transaction>();
    
    private com.smartbt.vtsuite.servercommon.model.VTSession vTSession;
    
    private java.util.Collection<com.smartbt.vtsuite.servercommon.model.AuditLog> auditLog = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.AuditLog>();
    
    private java.util.Collection<com.smartbt.vtsuite.servercommon.model.ClerkParameterValue> clerkParameterValue = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.ClerkParameterValue>();
   
    private java.util.Collection<com.smartbt.vtsuite.servercommon.model.WatchdogEntityClerk> watchdogEntityClerk = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.WatchdogEntityClerk>();
  
    private java.util.Collection<com.smartbt.vtsuite.servercommon.model.WatchdogAlert> watchdogAlertOrigination = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.WatchdogAlert>();
    
    private java.util.Collection<com.smartbt.vtsuite.servercommon.model.WatchdogAlertClerk> watchdogAlertClerk = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.WatchdogAlertClerk>();

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

    @JsonIgnore
    public int getORMID() {
        return getId();
    }

    /**
     * Clerk's user name.
     */
    public void setUsername(String value) {
        this.username = value;
    }

    /**
     * Clerk's user name.
     */
    public String getUsername() {
        return username;
    }

    public void setPassword(String value) {
        this.password = value;
    }

    public String getPassword() {
        return password;
    }

    public void setFirstName(String value) {
        this.firstName = value;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String value) {
        this.lastName = value;
    }

    public String getLastName() {
        return lastName;
    }

    @JsonIgnore
    public void setActive(boolean value) {
        setActive(new Boolean(value));
    }

    public void setActive(Boolean value) {
        this.active = value;
    }

    public Boolean getActive() {
        return active;
    }

    public void setMiddleInitial(String value) {
        this.middleInitial = value;
    }

    public String getMiddleInitial() {
        return middleInitial;
    }

    public void setDeactivationDate(java.util.Date value) {
        this.deactivationDate = value;
    }

    public java.util.Date getDeactivationDate() {
        return deactivationDate;
    }

    public void setFirstTime(boolean value) {
        this.firstTime = value;
    }

    public boolean getFirstTime() {
        return firstTime;
    }

    public void setCustomer(com.smartbt.vtsuite.servercommon.model.Customer value) {
        this.customer = value;
    }

    @JsonIgnore
    public com.smartbt.vtsuite.servercommon.model.Customer getCustomer() {
        return customer;
    }

    public void setMerchant(com.smartbt.vtsuite.servercommon.model.Merchant value) {
        this.merchant = value;
    }

    @JsonIgnore
    public com.smartbt.vtsuite.servercommon.model.Merchant getMerchant() {
        return merchant;
    }

    @JsonProperty
    public void setClerkRole(com.smartbt.vtsuite.servercommon.model.ClerkRole value) {
        this.clerkRole = value;
    }

    @JsonIgnore
    public com.smartbt.vtsuite.servercommon.model.ClerkRole getClerkRole() {
        return clerkRole;
    }

    public void setTerminal(com.smartbt.vtsuite.servercommon.model.Terminal value) {
        this.terminal = value;
    }

    @JsonIgnore
    public com.smartbt.vtsuite.servercommon.model.Terminal getTerminal() {
        return terminal;
    }

    public void setTransaction(java.util.Collection<com.smartbt.vtsuite.servercommon.model.Transaction> value) {
        this.transaction = value;
    }

    @JsonIgnore
    public java.util.Collection<com.smartbt.vtsuite.servercommon.model.Transaction> getTransaction() {
        return transaction;
    }

    public void setvTSession(com.smartbt.vtsuite.servercommon.model.VTSession value) {
        this.vTSession = value;
    }

    @JsonIgnore
    public com.smartbt.vtsuite.servercommon.model.VTSession getvTSession() {
        return vTSession;
    }

    public void setAuditLog(java.util.Collection<com.smartbt.vtsuite.servercommon.model.AuditLog> value) {
        this.auditLog = value;
    }

    @JsonIgnore
    public java.util.Collection<com.smartbt.vtsuite.servercommon.model.AuditLog> getAuditLog() {
        return auditLog;
    }

    public void setClerkParameterValue(java.util.Collection<com.smartbt.vtsuite.servercommon.model.ClerkParameterValue> value) {
        this.clerkParameterValue = value;
    }

    @JsonIgnore
    public java.util.Collection<com.smartbt.vtsuite.servercommon.model.ClerkParameterValue> getClerkParameterValue() {
        return clerkParameterValue;
    }

    public void setWatchdogEntityClerk(java.util.Collection<com.smartbt.vtsuite.servercommon.model.WatchdogEntityClerk> value) {
        this.watchdogEntityClerk = value;
    }

    @JsonIgnore
    public java.util.Collection<com.smartbt.vtsuite.servercommon.model.WatchdogEntityClerk> getWatchdogEntityClerk() {
        return watchdogEntityClerk;
    }

    public void setWatchdogAlertOrigination(java.util.Collection<com.smartbt.vtsuite.servercommon.model.WatchdogAlert> value) {
        this.watchdogAlertOrigination = value;
    }
    
    

    @JsonIgnore
    public java.util.Collection<com.smartbt.vtsuite.servercommon.model.WatchdogAlert> getWatchdogAlertOrigination() {
        return watchdogAlertOrigination;
    }

    public void setWatchdogAlertClerk(java.util.Collection<com.smartbt.vtsuite.servercommon.model.WatchdogAlertClerk> value) {
        this.watchdogAlertClerk = value;
    }

    @JsonIgnore
    public java.util.Collection<com.smartbt.vtsuite.servercommon.model.WatchdogAlertClerk> getWatchdogAlertClerk() {
        return watchdogAlertClerk;
    }

    @JsonIgnore
    public Collection<Monitoring> getMonitorings() {
        return monitorings;
    }

    public void setMonitorings(Collection<Monitoring> monitorings) {
        this.monitorings = monitorings;
    }

    public String toString() {
        return String.valueOf(getId());
    }

}
