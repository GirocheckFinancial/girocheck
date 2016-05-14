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
 * Stores all client's recurrent payment.
 */
@XmlRootElement
public class RecurringPayment implements Serializable {
	public RecurringPayment() {
	}
	
	private int id;
	
	private com.smartbt.vtsuite.servercommon.model.Client client;
	
	private com.smartbt.vtsuite.servercommon.model.Intervals interval;
	
	private com.smartbt.vtsuite.servercommon.model.AllowedRecurringPaymentTransactionType allowedRecurringPaymentTransactionType;
	
	private com.smartbt.vtsuite.servercommon.model.Account account;
	
	private String description;
	
	private java.util.Date paymentDate;
	
	private Boolean active;
	
	private java.util.Date createdAt;
	
	private float amount;
	
	private Integer failCounter;
	
	private java.util.Collection<com.smartbt.vtsuite.servercommon.model.RecurringPaymentLog> recurringPaymentLog = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.RecurringPaymentLog>();
	
	private java.util.Collection<com.smartbt.vtsuite.servercommon.model.RecurringPaymentRule> recurringPaymentRule = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.RecurringPaymentRule>();
	
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
	 * Recurrent payment description.
	 */
	public void setDescription(String value) {
		this.description = value;
	}
	
	/**
	 * Recurrent payment description.
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Recurrent payment start date.
	 */
	public void setPaymentDate(java.util.Date value) {
		this.paymentDate = value;
	}
	
	/**
	 * Recurrent payment start date.
	 */
	public java.util.Date getPaymentDate() {
		return paymentDate;
	}
	
	/**
	 * Flag for activate/deactivate the recurrent payment.
	 */
        @JsonIgnore
	public void setActive(boolean value) {
		setActive(new Boolean(value));
	}
	
	/**
	 * Flag for activate/deactivate the recurrent payment.
	 */
	public void setActive(Boolean value) {
		this.active = value;
	}
	
	/**
	 * Flag for activate/deactivate the recurrent payment.
	 */
	public Boolean getActive() {
		return active;
	}
	
	/**
	 * Date in which the recurrent payment was created.
	 */
	public void setCreatedAt(java.util.Date value) {
		this.createdAt = value;
	}
	
	/**
	 * Date in which the recurrent payment was created.
	 */
	public java.util.Date getCreatedAt() {
		return createdAt;
	}
	
	/**
	 * The amount to be charged.
	 */
	public void setAmount(float value) {
		this.amount = value;
	}
	
	/**
	 * The amount to be charged.
	 */
	public float getAmount() {
		return amount;
	}
	
	/**
	 * Counter for failed attempts.
	 */
        @JsonIgnore
	public void setFailCounter(int value) {
		setFailCounter(new Integer(value));
	}
	
	/**
	 * Counter for failed attempts.
	 */
	public void setFailCounter(Integer value) {
		this.failCounter = value;
	}
	
	/**
	 * Counter for failed attempts.
	 */
	public Integer getFailCounter() {
		return failCounter;
	}
	
	public void setAccount(com.smartbt.vtsuite.servercommon.model.Account value) {
		this.account = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.Account getAccount() {
		return account;
	}
	
	public void setClient(com.smartbt.vtsuite.servercommon.model.Client value) {
		this.client = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.Client getClient() {
		return client;
	}
	
	public void setInterval(com.smartbt.vtsuite.servercommon.model.Intervals value) {
		this.interval = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.Intervals getInterval() {
		return interval;
	}
	
	public void setAllowedRecurringPaymentTransactionType(com.smartbt.vtsuite.servercommon.model.AllowedRecurringPaymentTransactionType value) {
		this.allowedRecurringPaymentTransactionType = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.AllowedRecurringPaymentTransactionType getAllowedRecurringPaymentTransactionType() {
		return allowedRecurringPaymentTransactionType;
	}
	
	public void setRecurringPaymentLog(java.util.Collection<com.smartbt.vtsuite.servercommon.model.RecurringPaymentLog> value) {
		this.recurringPaymentLog = value;
	}
	
	public java.util.Collection<com.smartbt.vtsuite.servercommon.model.RecurringPaymentLog> getRecurringPaymentLog() {
		return recurringPaymentLog;
	}
	
	
	public void setRecurringPaymentRule(java.util.Collection<com.smartbt.vtsuite.servercommon.model.RecurringPaymentRule> value) {
		this.recurringPaymentRule = value;
	}
	
	public java.util.Collection<com.smartbt.vtsuite.servercommon.model.RecurringPaymentRule> getRecurringPaymentRule() {
		return recurringPaymentRule;
	}
	
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
