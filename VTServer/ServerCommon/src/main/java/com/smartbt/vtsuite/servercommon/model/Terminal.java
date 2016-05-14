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
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Stores all terminals.
 */
@XmlRootElement
public class Terminal implements Serializable {

    public Terminal() {
    }

    private static final long serialVersionUID = 1L;

    private int id;
    
    private com.smartbt.vtsuite.servercommon.model.Merchant merchant;
   
    private com.smartbt.vtsuite.servercommon.model.Application application;
    
    private com.smartbt.vtsuite.servercommon.model.ProductType productType;

    private String terminalId;

    private String serialNumber;

    private String description;

    private Boolean active;

    private Boolean monitored;

    private java.util.Date startedMonitorAt;

    private java.util.Date activationDate;

    private java.util.Date deactivationDate;
    
    private java.util.Collection<com.smartbt.vtsuite.servercommon.model.Transaction> transaction = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.Transaction>();
    
    private com.smartbt.vtsuite.servercommon.model.VTSession vTSession;
    
    private java.util.Collection<com.smartbt.vtsuite.servercommon.model.Device> device = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.Device>();
    
    private java.util.Collection<com.smartbt.vtsuite.servercommon.model.TerminalParameterValue> terminalParameterValues = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.TerminalParameterValue>();
    
    private java.util.Collection<com.smartbt.vtsuite.servercommon.model.TerminalHost> terminalHost = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.TerminalHost>();
    
    private java.util.Collection<com.smartbt.vtsuite.servercommon.model.Clerk> clerk = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.Clerk>();
    
    private java.util.Collection<com.smartbt.vtsuite.servercommon.model.AuditLog> auditLog = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.AuditLog>();
    
    private java.util.Collection<com.smartbt.vtsuite.servercommon.model.WatchdogEntity> watchdogEntity = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.WatchdogEntity>();
    
    private java.util.Collection<com.smartbt.vtsuite.servercommon.model.Monitoring> monitorings = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.Monitoring>();
  
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

    public void setTerminalId(String value) {
        this.terminalId = value;
    }

    public String getTerminalId() {
        return terminalId;
    }

    /**
     * Terminal 's number.
     */
    public void setSerialNumber(String value) {
        this.serialNumber = value;
    }

    /**
     * Terminal 's number.
     */
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     * Terminal's description.
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Terminal's description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Flag for activate/deactivate the terminal.
     */
    @JsonIgnore
    public void setActive(boolean value) {
        setActive(new Boolean(value));
    }

    /**
     * Flag for activate/deactivate the terminal.
     */
    public void setActive(Boolean value) {
        this.active = value;
    }

    /**
     * Flag for activate/deactivate the terminal.
     */
    public Boolean getActive() {
        return active;
    }

    public void setDeactivationDate(java.util.Date value) {
        this.deactivationDate = value;
    }

    public java.util.Date getDeactivationDate() {
        return deactivationDate;
    }

    public void setActivationDate(java.util.Date value) {
        this.activationDate = value;
    }

    public java.util.Date getActivationDate() {
        return activationDate;
    }

    public void setMerchant(com.smartbt.vtsuite.servercommon.model.Merchant value) {
        this.merchant = value;
    }
    
    @JsonIgnore
    public com.smartbt.vtsuite.servercommon.model.Merchant getMerchant() {
        return merchant;
    }

    public void setProductType(com.smartbt.vtsuite.servercommon.model.ProductType value) {
        this.productType = value;
    }
    
    @JsonIgnore
    public com.smartbt.vtsuite.servercommon.model.ProductType getProductType() {
        return productType;
    }

    @JsonProperty
    public void setApplication(com.smartbt.vtsuite.servercommon.model.Application value) {
        this.application = value;
    }
    
    @JsonIgnore
    public com.smartbt.vtsuite.servercommon.model.Application getApplication() {
        return application;
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

    public void setDevice(java.util.Collection<com.smartbt.vtsuite.servercommon.model.Device> value) {
        this.device = value;
    }

    @JsonIgnore
    public java.util.Collection<com.smartbt.vtsuite.servercommon.model.Device> getDevice() {
        return device;
    }

    public void setTerminalParameterValues(java.util.Collection<com.smartbt.vtsuite.servercommon.model.TerminalParameterValue> value) {
        this.terminalParameterValues = value;
    }

    @JsonIgnore
    public java.util.Collection<com.smartbt.vtsuite.servercommon.model.TerminalParameterValue> getTerminalParameterValues() {
        return terminalParameterValues;
    }

    public void setTerminalHost(java.util.Collection<com.smartbt.vtsuite.servercommon.model.TerminalHost> value) {
        this.terminalHost = value;
    }
    @JsonIgnore
    public java.util.Collection<com.smartbt.vtsuite.servercommon.model.TerminalHost> getTerminalHost() {
        return terminalHost;
    }

    @JsonProperty
    public void setClerk(java.util.Collection<com.smartbt.vtsuite.servercommon.model.Clerk> value) {
        this.clerk = value;
    }

    @JsonIgnore
    public java.util.Collection<com.smartbt.vtsuite.servercommon.model.Clerk> getClerk() {
        return clerk;
    }

    public void setAuditLog(java.util.Collection<com.smartbt.vtsuite.servercommon.model.AuditLog> value) {
        this.auditLog = value;
    }

    @JsonIgnore
    public java.util.Collection<com.smartbt.vtsuite.servercommon.model.AuditLog> getAuditLog() {
        return auditLog;
    }

    public void setWatchdogEntity(java.util.Collection<com.smartbt.vtsuite.servercommon.model.WatchdogEntity> value) {
        this.watchdogEntity = value;
    }

    @JsonIgnore
    public java.util.Collection<com.smartbt.vtsuite.servercommon.model.WatchdogEntity> getWatchdogEntity() {
        return watchdogEntity;
    }

    @JsonIgnore
    public Collection<Monitoring> getMonitorings() {
        return monitorings;
    }

    public void setMonitorings(Collection<Monitoring> monitorings) {
        this.monitorings = monitorings;
    }

    public Boolean isMonitored() {
        return monitored;
    }

    public void setMonitored(Boolean monitored) {
        this.monitored = monitored;
    }

    public Date getStartedMonitorAt() {
        return startedMonitorAt;
    }

    public void setStartedMonitorAt(Date startedMonitorAt) {
        this.startedMonitorAt = startedMonitorAt;
    }

    public void addDevice(com.smartbt.vtsuite.servercommon.model.Device d) {
        getDevice().add(d);
        d.setTerminal(this);
    }

    public void addTerminalParameterValues(com.smartbt.vtsuite.servercommon.model.TerminalParameterValue terminalParameterValue) {
        terminalParameterValues.add(terminalParameterValue);
        terminalParameterValue.setTerminal(this);
    }

    public void addTerminalHost(com.smartbt.vtsuite.servercommon.model.TerminalHost th) {

        getTerminalHost().add(th);
        th.setTerminal(this);
    }

    public String toString() {
        return String.valueOf(getId());
    }

}
