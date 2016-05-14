/**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 * 
 * This is an automatic generated file. It will be regenerated every time 
 * you generate persistence class.
 * 
 * Modifying its content may cause the program not work, or your work may lost.
 */

/**
 * Licensee: SMART BUSINESS TECHNOLOGY
 * License Type: Purchased
 */
package com.smartbt.vtsuite.servercommon.model;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
/**
 * Stores all merchants.
 */
@XmlRootElement
public class Merchant implements Serializable {
	public Merchant() {
	}
	
	private int id;
	
	private com.smartbt.vtsuite.servercommon.model.Customer customer;
	
	private String number;
	
	private String name;
	
	private String description;
	
	private Boolean active;
	
	private Boolean monitor;
	
	private java.util.Date activationDate;
	
	private java.util.Date deactivationDate;
	
	private String sic;
	
	private Boolean industryType;
	
	private Integer aliveSessionTime;
	
	private java.sql.Blob logo;
	
	private java.util.Collection<com.smartbt.vtsuite.servercommon.model.Terminal> terminal = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.Terminal>();
	
	private java.util.Collection<com.smartbt.vtsuite.servercommon.model.Clerk> clerk = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.Clerk>();
	
	private java.util.Collection<com.smartbt.vtsuite.servercommon.model.Client> client = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.Client>();
	
	private java.util.Collection<com.smartbt.vtsuite.servercommon.model.HostModeMerchant> hostModeMerchant = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.HostModeMerchant>();
	
	private java.util.Collection<com.smartbt.vtsuite.servercommon.model.MerchantHost> merchantHost = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.MerchantHost>();

	private java.util.Collection<com.smartbt.vtsuite.servercommon.model.MerchantParameterValue> merchantParameterValue = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.MerchantParameterValue>();
	
	private java.util.Collection<com.smartbt.vtsuite.servercommon.model.MerchantTelephone> merchantTelephone = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.MerchantTelephone>();
	
	private java.util.Collection<com.smartbt.vtsuite.servercommon.model.MerchantAddress> merchantAddress = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.MerchantAddress>();
	
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
	
	/**
	 * Merchant's number.
	 */
	public void setNumber(String value) {
		this.number = value;
	}
	
	/**
	 * Merchant's number.
	 */
	public String getNumber() {
		return number;
	}
	
	public void setName(String value) {
		this.name = value;
	}
	
	public String getName() {
		return name;
	}
	
	/**
	 * Merchant's description.
	 */
	public void setDescription(String value) {
		this.description = value;
	}
	
	/**
	 * Merchant's description.
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Merchant's flag for activate/deactivate.
	 */
        @JsonIgnore
	public void setActive(boolean value) {
		setActive(new Boolean(value));
	}
	
	/**
	 * Merchant's flag for activate/deactivate.
	 */
	public void setActive(Boolean value) {
		this.active = value;
	}
	
	/**
	 * Merchant's flag for activate/deactivate.
	 */
	public Boolean getActive() {
		return active;
	}
	
	/**
	 * Merchant flag for activate/deactivate the monitoring activity.
	 */
        @JsonIgnore
	public void setMonitor(boolean value) {
		setMonitor(new Boolean(value));
	}
	
	/**
	 * Merchant flag for activate/deactivate the monitoring activity.
	 */
	public void setMonitor(Boolean value) {
		this.monitor = value;
	}
	
	/**
	 * Merchant flag for activate/deactivate the monitoring activity.
	 */
	public Boolean getMonitor() {
		return monitor;
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
	
	public void setSic(String value) {
		this.sic = value;
	}
	
	public String getSic() {
		return sic;
	}
	@JsonIgnore
	public void setIndustryType(boolean value) {
		setIndustryType(new Boolean(value));
	}
	
	public void setIndustryType(Boolean value) {
		this.industryType = value;
	}
	
	public Boolean getIndustryType() {
		return industryType;
	}
	@JsonIgnore
	public void setAliveSessionTime(int value) {
		setAliveSessionTime(new Integer(value));
	}
	
	public void setAliveSessionTime(Integer value) {
		this.aliveSessionTime = value;
	}
	
	public Integer getAliveSessionTime() {
		return aliveSessionTime;
	}
	
	public void setLogo(java.sql.Blob value) {
		this.logo = value;
	}
	
	public java.sql.Blob getLogo() {
		return logo;
	}
	
	public void setCustomer(com.smartbt.vtsuite.servercommon.model.Customer value) {
		this.customer = value;
	}
	
        @JsonIgnore
	public com.smartbt.vtsuite.servercommon.model.Customer getCustomer() {
		return customer;
	}
	@JsonProperty
	public void setTerminal(java.util.Collection<com.smartbt.vtsuite.servercommon.model.Terminal> value) {
		this.terminal = value;
	}
	
        @JsonIgnore
	public java.util.Collection<com.smartbt.vtsuite.servercommon.model.Terminal> getTerminal() {
		return terminal;
	}
	
	@JsonProperty
	public void setClerk(java.util.Collection<com.smartbt.vtsuite.servercommon.model.Clerk> value) {
		this.clerk = value;
	}
        
	@JsonIgnore
	public java.util.Collection<com.smartbt.vtsuite.servercommon.model.Clerk> getClerk() {
		return clerk;
	}
	
	
	public void setClient(java.util.Collection<com.smartbt.vtsuite.servercommon.model.Client> value) {
		this.client = value;
	}
	@JsonIgnore
	public java.util.Collection<com.smartbt.vtsuite.servercommon.model.Client> getClient() {
		return client;
	}
	
	
	public void setHostModeMerchant(java.util.Collection<com.smartbt.vtsuite.servercommon.model.HostModeMerchant> value) {
		this.hostModeMerchant = value;
	}
	
        @JsonIgnore
	public java.util.Collection<com.smartbt.vtsuite.servercommon.model.HostModeMerchant> getHostModeMerchant() {
		return hostModeMerchant;
	}
	
	
	public void setMerchantHost(java.util.Collection<com.smartbt.vtsuite.servercommon.model.MerchantHost> value) {
		this.merchantHost = value;
	}
	
        @JsonIgnore
	public java.util.Collection<com.smartbt.vtsuite.servercommon.model.MerchantHost> getMerchantHost() {
		return merchantHost;
	}
	
	
	public void setMerchantParameterValue(java.util.Collection<com.smartbt.vtsuite.servercommon.model.MerchantParameterValue> value) {
		this.merchantParameterValue = value;
	}
	
        @JsonIgnore
	public java.util.Collection<com.smartbt.vtsuite.servercommon.model.MerchantParameterValue> getMerchantParameterValue() {
		return merchantParameterValue;
	}
	
	@JsonProperty
	public void setMerchantTelephone(java.util.Collection<com.smartbt.vtsuite.servercommon.model.MerchantTelephone> value) {
		this.merchantTelephone = value;
	}
	@JsonIgnore
	public java.util.Collection<com.smartbt.vtsuite.servercommon.model.MerchantTelephone> getMerchantTelephone() {
		return merchantTelephone;
	}
	
	@JsonProperty
	public void setMerchantAddress(java.util.Collection<com.smartbt.vtsuite.servercommon.model.MerchantAddress> value) {
		this.merchantAddress = value;
	}
	@JsonIgnore
	public java.util.Collection<com.smartbt.vtsuite.servercommon.model.MerchantAddress> getMerchantAddress() {
		return merchantAddress;
	}
	
	
	public void setWatchdogEntity(java.util.Collection<com.smartbt.vtsuite.servercommon.model.WatchdogEntity> value) {
		this.watchdogEntity = value;
	}
	@JsonIgnore
	public java.util.Collection<com.smartbt.vtsuite.servercommon.model.WatchdogEntity> getWatchdogEntity() {
		return watchdogEntity;
	}
	
	
	public void addTerminal(com.smartbt.vtsuite.servercommon.model.Terminal t) {
		
		        getTerminal().add(t);
		        t.setMerchant(this);
	}
	
	public void addMerchantAddress(com.smartbt.vtsuite.servercommon.model.MerchantAddress ma) {
		
		        getMerchantAddress().add(ma);
		        ma.setMerchant(this);
	}
	
	public void addMerchantTelephone(com.smartbt.vtsuite.servercommon.model.MerchantTelephone mt) {
		
		        getMerchantTelephone().add(mt);
		        mt.setMerchant(this);
	}
	
	public void addClerk(com.smartbt.vtsuite.servercommon.model.Clerk c) {
		
		        getClerk().add(c);
		        c.setMerchant(this);
	}
	
	public void addMerchantHost(com.smartbt.vtsuite.servercommon.model.MerchantHost host) {
		
		        getMerchantHost().add(host);
		        host.setMerchant(this);
	}
	
	public void addMerchantParameterValue(com.smartbt.vtsuite.servercommon.model.MerchantParameterValue mpv) {
		
		        getMerchantParameterValue().add(mpv);
		        mpv.setMerchant(this);
	}
	
	public void addMerchantHostMode(com.smartbt.vtsuite.servercommon.model.HostModeMerchant hmm) {
		
		        getHostModeMerchant().add(hmm);
		        hmm.setMerchant(this);
	}
	
	public void addClient(com.smartbt.vtsuite.servercommon.model.Client c) {
		  getClient().add(c);
		        c.setMerchant(this);
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
