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
 * Stores the recurrent payment 's history.
 */
@XmlRootElement
public class RecurringPaymentLog implements Serializable {
	public RecurringPaymentLog() {
	}
	
	private int id;
	
	private com.smartbt.vtsuite.servercommon.model.RecurringPayment recurringPayment;
	
	private com.smartbt.vtsuite.servercommon.model.Status status;
	
	private java.util.Date date;
	
	private String description;
	
	public void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return id;
	}		
	
	/**
	 * Date in which occurred.
	 */
	public void setDate(java.util.Date value) {
		this.date = value;
	}
	
	/**
	 * Date in which occurred.
	 */
	public java.util.Date getDate() {
		return date;
	}
	
	/**
	 * Rules' description applied to the recurrent payment.
	 */
	public void setDescription(String value) {
		this.description = value;
	}
	
	/**
	 * Rules' description applied to the recurrent payment.
	 */
	public String getDescription() {
		return description;
	}
	
	public void setRecurringPayment(com.smartbt.vtsuite.servercommon.model.RecurringPayment value) {
		this.recurringPayment = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.RecurringPayment getRecurringPayment() {
		return recurringPayment;
	}
	
	public void setStatus(com.smartbt.vtsuite.servercommon.model.Status value) {
		this.status = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.Status getStatus() {
		return status;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
