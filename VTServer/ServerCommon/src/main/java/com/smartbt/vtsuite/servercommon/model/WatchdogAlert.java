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
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class WatchdogAlert implements Serializable {

    public WatchdogAlert() {
    }

    private int id;

    private com.smartbt.vtsuite.servercommon.model.Watchdog watchdog;

    private String value;

    private java.util.Date createdAt;

    private Clerk clerkOrigination;

    private User userOrigination;

    private java.util.Collection<com.smartbt.vtsuite.servercommon.model.WatchdogAlertClerk> watchdogAlertClerk = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.WatchdogAlertClerk>();

    public void setId(int value) {
        this.id = value;
    }

    public int getId() {
        return id;
    }

    public void setCreatedAt(java.util.Date value) {
        this.createdAt = value;
    }

    public java.util.Date getCreatedAt() {
        return createdAt;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String toString() {
        return String.valueOf(getId());
    }

    public Clerk getClerkOrigination() {
        return clerkOrigination;
    }

    public void setClerkOrigination(Clerk clerkOrigination) {
        this.clerkOrigination = clerkOrigination;
    }

    public Collection<WatchdogAlertClerk> getWatchdogAlertClerk() {
        return watchdogAlertClerk;
    }

    public void setWatchdogAlertClerk(Collection<WatchdogAlertClerk> watchdogAlertClerk) {
        this.watchdogAlertClerk = watchdogAlertClerk;
    }

    public Watchdog getWatchdog() {
        return watchdog;
    }

    public void setWatchdog(Watchdog watchdog) {
        this.watchdog = watchdog;
    }

    public User getUserOrigination() {
        return userOrigination;
    }

    public void setUserOrigination(User userOrigination) {
        this.userOrigination = userOrigination;
    }

}
