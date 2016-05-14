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

import com.smartbt.vtsuite.servercommon.display.common.model.EntityObject;
import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Stores the alerts or messages that will be showed.
 */
@XmlRootElement
public class WatchdogEntity implements Serializable {

    public WatchdogEntity() {
    }

    private static final long serialVersionUID = 1L;
    
    private int id;

    private com.smartbt.vtsuite.servercommon.model.Customer customer;

    private com.smartbt.vtsuite.servercommon.model.Merchant merchant;

    private com.smartbt.vtsuite.servercommon.model.Terminal terminal;

    private com.smartbt.vtsuite.servercommon.model.Watchdog watchdog;

    private String value;

    private java.util.Date createdAt;

    private java.util.Collection<com.smartbt.vtsuite.servercommon.model.WatchdogEntityClerk> watchdogEntityClerk = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.WatchdogEntityClerk>();

    //Not linked with DB
    private List<EntityObject> sendAlertTo;

    private com.smartbt.vtsuite.servercommon.model.Clerk clerkCreator;

    private com.smartbt.vtsuite.servercommon.model.User userCreator;

    public List<EntityObject> getSendAlertTo() {
        return sendAlertTo;
    }

    public void setSendAlertTo(List<EntityObject> sendAlertTo) {
        this.sendAlertTo = sendAlertTo;
    }

    public Clerk getClerkCreator() {
        return clerkCreator;
    }

    public void setClerkCreator(Clerk clerkCreator) {
        this.clerkCreator = clerkCreator;
    }

    public User getUserCreator() {
        return userCreator;
    }

    public void setUserCreator(User userCreator) {
        this.userCreator = userCreator;
    }

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

    /**
     * The message to be sent.
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * The message to be sent.
     */
    public String getValue() {
        return value;
    }

    public void setCreatedAt(java.util.Date value) {
        this.createdAt = value;
    }

    public java.util.Date getCreatedAt() {
        return createdAt;
    }

    public void setMerchant(com.smartbt.vtsuite.servercommon.model.Merchant value) {
        this.merchant = value;
    }

    public com.smartbt.vtsuite.servercommon.model.Merchant getMerchant() {
        return merchant;
    }

    public void setTerminal(com.smartbt.vtsuite.servercommon.model.Terminal value) {
        this.terminal = value;
    }

    public com.smartbt.vtsuite.servercommon.model.Terminal getTerminal() {
        return terminal;
    }

    public void setCustomer(com.smartbt.vtsuite.servercommon.model.Customer value) {
        this.customer = value;
    }

    public com.smartbt.vtsuite.servercommon.model.Customer getCustomer() {
        return customer;
    }

    public void setWatchdog(com.smartbt.vtsuite.servercommon.model.Watchdog value) {
        this.watchdog = value;
    }

    public com.smartbt.vtsuite.servercommon.model.Watchdog getWatchdog() {
        return watchdog;
    }

    public void setWatchdogEntityClerk(java.util.Collection<com.smartbt.vtsuite.servercommon.model.WatchdogEntityClerk> value) {
        this.watchdogEntityClerk = value;
    }

    public java.util.Collection<com.smartbt.vtsuite.servercommon.model.WatchdogEntityClerk> getWatchdogEntityClerk() {
        return watchdogEntityClerk;
    }

    public String toString() {
        return String.valueOf(getId());
    }

}
