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
import org.codehaus.jackson.annotate.JsonIgnore;

@XmlRootElement
public class DataType implements Serializable {

    public DataType() {
    }

    private int id;
    private String name;

    @JsonIgnore
    private java.util.Collection<com.smartbt.vtsuite.servercommon.model.GroupParameter> groupParameter = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.GroupParameter>();
    @JsonIgnore
    private java.util.Collection<com.smartbt.vtsuite.servercommon.model.CustomerParameter> customerParameter = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.CustomerParameter>();
    @JsonIgnore
    private java.util.Collection<com.smartbt.vtsuite.servercommon.model.ClerkParameter> clerkParameter = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.ClerkParameter>();
    @JsonIgnore
    private java.util.Collection<com.smartbt.vtsuite.servercommon.model.DeviceParameter> deviceParameter = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.DeviceParameter>();
    @JsonIgnore
    private java.util.Collection<com.smartbt.vtsuite.servercommon.model.TerminalHostParameter> terminalHostParameter = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.TerminalHostParameter>();
    @JsonIgnore
    private java.util.Collection<com.smartbt.vtsuite.servercommon.model.HostParameter> hostParameter = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.HostParameter>();
    @JsonIgnore
    private java.util.Collection<com.smartbt.vtsuite.servercommon.model.MerchantParameter> merchantParameter = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.MerchantParameter>();
    @JsonIgnore
    private java.util.Collection<com.smartbt.vtsuite.servercommon.model.MerchantHostParameter> merchantHostParameter = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.MerchantHostParameter>();
    @JsonIgnore
    private java.util.Collection<com.smartbt.vtsuite.servercommon.model.Watchdog> watchdog = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.Watchdog>();
    @JsonIgnore
    private java.util.Collection<com.smartbt.vtsuite.servercommon.model.ApplicationParameter> applicationParameter = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.ApplicationParameter>();

    public void setId(int value) {
        this.id = value;
    }

    public int getId() {
        return id;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getName() {
        return name;
    }

    public void setGroupParameter(java.util.Collection<com.smartbt.vtsuite.servercommon.model.GroupParameter> value) {
        this.groupParameter = value;
    }

    public java.util.Collection<com.smartbt.vtsuite.servercommon.model.GroupParameter> getGroupParameter() {
        return groupParameter;
    }

    public void setCustomerParameter(java.util.Collection<com.smartbt.vtsuite.servercommon.model.CustomerParameter> value) {
        this.customerParameter = value;
    }

    public java.util.Collection<com.smartbt.vtsuite.servercommon.model.CustomerParameter> getCustomerParameter() {
        return customerParameter;
    }

    public void setClerkParameter(java.util.Collection<com.smartbt.vtsuite.servercommon.model.ClerkParameter> value) {
        this.clerkParameter = value;
    }

    public java.util.Collection<com.smartbt.vtsuite.servercommon.model.ClerkParameter> getClerkParameter() {
        return clerkParameter;
    }

    public void setDeviceParameter(java.util.Collection<com.smartbt.vtsuite.servercommon.model.DeviceParameter> value) {
        this.deviceParameter = value;
    }

    public java.util.Collection<com.smartbt.vtsuite.servercommon.model.DeviceParameter> getDeviceParameter() {
        return deviceParameter;
    }

    public void setTerminalHostParameter(java.util.Collection<com.smartbt.vtsuite.servercommon.model.TerminalHostParameter> value) {
        this.terminalHostParameter = value;
    }

    public java.util.Collection<com.smartbt.vtsuite.servercommon.model.TerminalHostParameter> getTerminalHostParameter() {
        return terminalHostParameter;
    }

    public void setHostParameter(java.util.Collection<com.smartbt.vtsuite.servercommon.model.HostParameter> value) {
        this.hostParameter = value;
    }

    public java.util.Collection<com.smartbt.vtsuite.servercommon.model.HostParameter> getHostParameter() {
        return hostParameter;
    }

    public void setMerchantParameter(java.util.Collection<com.smartbt.vtsuite.servercommon.model.MerchantParameter> value) {
        this.merchantParameter = value;
    }

    public java.util.Collection<com.smartbt.vtsuite.servercommon.model.MerchantParameter> getMerchantParameter() {
        return merchantParameter;
    }

    public void setMerchantHostParameter(java.util.Collection<com.smartbt.vtsuite.servercommon.model.MerchantHostParameter> value) {
        this.merchantHostParameter = value;
    }

    public java.util.Collection<com.smartbt.vtsuite.servercommon.model.MerchantHostParameter> getMerchantHostParameter() {
        return merchantHostParameter;
    }

    public void setWatchdog(java.util.Collection<com.smartbt.vtsuite.servercommon.model.Watchdog> value) {
        this.watchdog = value;
    }

    public java.util.Collection<com.smartbt.vtsuite.servercommon.model.Watchdog> getWatchdog() {
        return watchdog;
    }

    public Collection<ApplicationParameter> getApplicationParameter() {
        return applicationParameter;
    }

    public void setApplicationParameter(Collection<ApplicationParameter> applicationParameter) {
        this.applicationParameter = applicationParameter;
    }

    public String toString() {
        return String.valueOf(getId());
    }

}
