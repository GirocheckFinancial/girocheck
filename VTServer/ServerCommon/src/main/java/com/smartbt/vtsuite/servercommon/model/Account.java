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
/**
 * Stores bank account information.
 */
@XmlRootElement
public class Account extends com.smartbt.vtsuite.servercommon.model.SensitiveData implements Serializable {
	public Account() {
	}
	
	private com.smartbt.vtsuite.servercommon.model.Client client;
	
	private java.util.Collection<com.smartbt.vtsuite.servercommon.model.RecurringPayment> recurringPayment = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.RecurringPayment>();
	
	private java.util.Collection<com.smartbt.vtsuite.servercommon.model.Transaction> transaction = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.Transaction>();
	
	public void setClient(com.smartbt.vtsuite.servercommon.model.Client value) {
		this.client = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.Client getClient() {
		return client;
	}
	
	public void setRecurringPayment(java.util.Collection<com.smartbt.vtsuite.servercommon.model.RecurringPayment> value) {
		this.recurringPayment = value;
	}
	
	public java.util.Collection<com.smartbt.vtsuite.servercommon.model.RecurringPayment> getRecurringPayment() {
		return recurringPayment;
	}
	
	
	public void setTransaction(java.util.Collection<com.smartbt.vtsuite.servercommon.model.Transaction> value) {
		this.transaction = value;
	}
	
	public java.util.Collection<com.smartbt.vtsuite.servercommon.model.Transaction> getTransaction() {
		return transaction;
	}
	
	
	public String toString() {
		return super.toString();
	}
	
}
