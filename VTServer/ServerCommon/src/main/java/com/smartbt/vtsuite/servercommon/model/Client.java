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
import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import org.codehaus.jackson.annotate.JsonIgnore;
/**
 * Stores all clients.
 */
@XmlRootElement
public class Client implements Serializable {
	public Client() {
	}
	
	private int id;
	
	private com.smartbt.vtsuite.servercommon.model.Merchant merchant;
	
	private com.smartbt.vtsuite.servercommon.model.DriverLicense driverLicense;
	
	private com.smartbt.vtsuite.servercommon.model.SocialSecurity socialSecurity;
	
	private String firstName;
	
	private String lastName;
	
	private String middleInital;
	
	private String company;
	
	private String email;
	
	private Boolean emailReceipt;
	
	private Boolean active;
	
	private java.util.Date createdAt;
	
	private java.util.Collection<com.smartbt.vtsuite.servercommon.model.Transaction> transaction = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.Transaction>();
	
	private java.util.Collection<com.smartbt.vtsuite.servercommon.model.ClientPreference> clientPreference = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.ClientPreference>();
	
	private java.util.Collection<com.smartbt.vtsuite.servercommon.model.Account> account = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.Account>();
	
	private java.util.Collection<com.smartbt.vtsuite.servercommon.model.RecurringPayment> recurringPayment = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.RecurringPayment>();
	
	private java.util.Collection<com.smartbt.vtsuite.servercommon.model.ClientTelephone> clientTelephone = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.ClientTelephone>();
	
	private java.util.Collection<com.smartbt.vtsuite.servercommon.model.ClientAddress> clientAddress = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.ClientAddress>();
	
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
	 * Client's first name.
	 */
	public void setFirstName(String value) {
		this.firstName = value;
	}
	
	/**
	 * Client's first name.
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * Client's last name.
	 */
	public void setLastName(String value) {
		this.lastName = value;
	}
	
	/**
	 * Client's last name.
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * Client's initial name.
	 */
	public void setMiddleInital(String value) {
		this.middleInital = value;
	}
	
	/**
	 * Client's initial name.
	 */
	public String getMiddleInital() {
		return middleInital;
	}
	
	/**
	 * Client's company.
	 */
	public void setCompany(String value) {
		this.company = value;
	}
	
	/**
	 * Client's company.
	 */
	public String getCompany() {
		return company;
	}
	
	/**
	 * Client's email.
	 */
	public void setEmail(String value) {
		this.email = value;
	}
	
	/**
	 * Client's email.
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Flag for activate/deactivate the option to send receipt via email.
	 */
        @JsonIgnore
	public void setEmailReceipt(boolean value) {
		setEmailReceipt(new Boolean(value));
	}
	
	/**
	 * Flag for activate/deactivate the option to send receipt via email.
	 */
	public void setEmailReceipt(Boolean value) {
		this.emailReceipt = value;
	}
	
	/**
	 * Flag for activate/deactivate the option to send receipt via email.
	 */
	public Boolean getEmailReceipt() {
		return emailReceipt;
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
	
	public void setCreatedAt(java.util.Date value) {
		this.createdAt = value;
	}
	
	public java.util.Date getCreatedAt() {
		return createdAt;
	}
	
	public void setDriverLicense(com.smartbt.vtsuite.servercommon.model.DriverLicense value) {
		this.driverLicense = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.DriverLicense getDriverLicense() {
		return driverLicense;
	}
	
	public void setMerchant(com.smartbt.vtsuite.servercommon.model.Merchant value) {
		this.merchant = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.Merchant getMerchant() {
		return merchant;
	}
	
	public void setSocialSecurity(com.smartbt.vtsuite.servercommon.model.SocialSecurity value) {
		this.socialSecurity = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.SocialSecurity getSocialSecurity() {
		return socialSecurity;
	}
	
	public void setTransaction(java.util.Collection<com.smartbt.vtsuite.servercommon.model.Transaction> value) {
		this.transaction = value;
	}
	
	public java.util.Collection<com.smartbt.vtsuite.servercommon.model.Transaction> getTransaction() {
		return transaction;
	}
	
	
	public void setClientPreference(java.util.Collection<com.smartbt.vtsuite.servercommon.model.ClientPreference> value) {
		this.clientPreference = value;
	}
	
	public java.util.Collection<com.smartbt.vtsuite.servercommon.model.ClientPreference> getClientPreference() {
		return clientPreference;
	}
	
	
	public void setAccount(java.util.Collection<com.smartbt.vtsuite.servercommon.model.Account> value) {
		this.account = value;
	}
	
	public java.util.Collection<com.smartbt.vtsuite.servercommon.model.Account> getAccount() {
		return account;
	}
	
	
	public void setRecurringPayment(java.util.Collection<com.smartbt.vtsuite.servercommon.model.RecurringPayment> value) {
		this.recurringPayment = value;
	}
	
	public java.util.Collection<com.smartbt.vtsuite.servercommon.model.RecurringPayment> getRecurringPayment() {
		return recurringPayment;
	}
	
	
	public void setClientTelephone(java.util.Collection<com.smartbt.vtsuite.servercommon.model.ClientTelephone> value) {
		this.clientTelephone = value;
	}
	
	public java.util.Collection<com.smartbt.vtsuite.servercommon.model.ClientTelephone> getClientTelephone() {
		return clientTelephone;
	}
	
	
	public void setClientAddress(java.util.Collection<com.smartbt.vtsuite.servercommon.model.ClientAddress> value) {
		this.clientAddress = value;
	}
	
	public java.util.Collection<com.smartbt.vtsuite.servercommon.model.ClientAddress> getClientAddress() {
		return clientAddress;
	}
	
	
	public void addClientTelephone(com.smartbt.vtsuite.servercommon.model.ClientTelephone ct) {
		        getClientTelephone().add(ct);
		        ct.setClient(this);
	}
	
	public void addClientAddress(com.smartbt.vtsuite.servercommon.model.ClientAddress ca) {
		   getClientAddress().add(ca);
		        ca.setClient(this);
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
