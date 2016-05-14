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
 * Stores all operations that will be available.
 */
@XmlRootElement
public class Operation implements Serializable {
	public Operation() {
	}
	
	private int id;
	
	private String name;
	
	private Boolean active;
	
	private java.util.Collection<com.smartbt.vtsuite.servercommon.model.Transaction> transaction = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.Transaction>();
	
	private java.util.Collection<com.smartbt.vtsuite.servercommon.model.AllowedRecurringPaymentTransactionType> allowedRecurringPaymentTransactionType = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.AllowedRecurringPaymentTransactionType>();
	
	private java.util.Collection<com.smartbt.vtsuite.servercommon.model.HostModeOperation> hostModeOperation = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.HostModeOperation>();
	
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
	 * Operation name. Eg: "credit", "debit", "obt", "ach".
	 */
	public void setName(String value) {
		this.name = value;
	}
	
	/**
	 * Operation name. Eg: "credit", "debit", "obt", "ach".
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Flag for activate/deactivate the operation.
	 */
        @JsonIgnore
	public void setActive(boolean value) {
		setActive(new Boolean(value));
	}
	
	/**
	 * Flag for activate/deactivate the operation.
	 */
	public void setActive(Boolean value) {
		this.active = value;
	}
	
	/**
	 * Flag for activate/deactivate the operation.
	 */
	public Boolean getActive() {
		return active;
	}
	
	public void setTransaction(java.util.Collection<com.smartbt.vtsuite.servercommon.model.Transaction> value) {
		this.transaction = value;
	}
	
	public java.util.Collection<com.smartbt.vtsuite.servercommon.model.Transaction> getTransaction() {
		return transaction;
	}
	
	
	public void setAllowedRecurringPaymentTransactionType(java.util.Collection<com.smartbt.vtsuite.servercommon.model.AllowedRecurringPaymentTransactionType> value) {
		this.allowedRecurringPaymentTransactionType = value;
	}
	
	public java.util.Collection<com.smartbt.vtsuite.servercommon.model.AllowedRecurringPaymentTransactionType> getAllowedRecurringPaymentTransactionType() {
		return allowedRecurringPaymentTransactionType;
	}
	
	
	public void setHostModeOperation(java.util.Collection<com.smartbt.vtsuite.servercommon.model.HostModeOperation> value) {
		this.hostModeOperation = value;
	}
	
	public java.util.Collection<com.smartbt.vtsuite.servercommon.model.HostModeOperation> getHostModeOperation() {
		return hostModeOperation;
	}
	
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
