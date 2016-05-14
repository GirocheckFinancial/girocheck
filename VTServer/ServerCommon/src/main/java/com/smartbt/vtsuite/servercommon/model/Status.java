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
 * Status' description.
 */
@XmlRootElement
public class Status implements Serializable {
	public Status() {
	}
	
	private Boolean id;
	
	private String description;
	
	private java.util.Collection<com.smartbt.vtsuite.servercommon.model.RecurringPaymentLog> recurringPaymentLog = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.RecurringPaymentLog>();
	
	/**
	 * Table record identification.
	 */
        @JsonIgnore
	public void setId(boolean value) {
		setId(new Boolean(value));
	}
	
	/**
	 * Table record identification.
	 */
	public void setId(Boolean value) {
		this.id = value;
	}
	
	/**
	 * Table record identification.
	 */
	public Boolean getId() {
		return id;
	}		
	
	/**
	 * Status' description.
	 */
	public void setDescription(String value) {
		this.description = value;
	}
	
	/**
	 * Status' description.
	 */
	public String getDescription() {
		return description;
	}
	
	public void setRecurringPaymentLog(java.util.Collection<com.smartbt.vtsuite.servercommon.model.RecurringPaymentLog> value) {
		this.recurringPaymentLog = value;
	}
	
	public java.util.Collection<com.smartbt.vtsuite.servercommon.model.RecurringPaymentLog> getRecurringPaymentLog() {
		return recurringPaymentLog;
	}
	
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
