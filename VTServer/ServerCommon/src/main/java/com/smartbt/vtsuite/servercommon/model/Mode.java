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
@XmlRootElement
public class Mode implements Serializable {
	public Mode() {
	}
	
	private int id;
	
	private String name;
	
	private Integer instrument;
	
	private java.util.Collection<com.smartbt.vtsuite.servercommon.model.Transaction> transaction = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.Transaction>();
	
	private java.util.Collection<com.smartbt.vtsuite.servercommon.model.AllowedRecurringPaymentTransactionType> allowedRecurringPaymentTransactionType = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.AllowedRecurringPaymentTransactionType>();
	
	private java.util.Collection<com.smartbt.vtsuite.servercommon.model.HostMode> hostMode = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.HostMode>();
	
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
	
	public void setInstrument(int value) {
		setInstrument(new Integer(value));
	}
	
	public void setInstrument(Integer value) {
		this.instrument = value;
	}
	@JsonIgnore
	public Integer getInstrument() {
		return instrument;
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
	
	
	public void setHostMode(java.util.Collection<com.smartbt.vtsuite.servercommon.model.HostMode> value) {
		this.hostMode = value;
	}
	
	public java.util.Collection<com.smartbt.vtsuite.servercommon.model.HostMode> getHostMode() {
		return hostMode;
	}
	
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
