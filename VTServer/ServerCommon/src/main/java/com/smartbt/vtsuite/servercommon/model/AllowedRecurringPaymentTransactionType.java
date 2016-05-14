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
 * Stores all recurrent payment types that will be available. Eg: "credit sale".
 */
@XmlRootElement
public class AllowedRecurringPaymentTransactionType implements Serializable {
	public AllowedRecurringPaymentTransactionType() {
	}
	
	private int id;
	
	private com.smartbt.vtsuite.servercommon.model.Mode mode;
	
	private com.smartbt.vtsuite.servercommon.model.Operation operation;
	
	private String name;
	
	private String description;
	
	private java.util.Collection<com.smartbt.vtsuite.servercommon.model.RecurringPayment> recurringPayment = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.RecurringPayment>();
	
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
	 * Name for the recurrent payment type. Eg: "credit sale"
	 */
	public void setName(String value) {
		this.name = value;
	}
	
	/**
	 * Name for the recurrent payment type. Eg: "credit sale"
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Description for the recurrent payment type.
	 */
	public void setDescription(String value) {
		this.description = value;
	}
	
	/**
	 * Description for the recurrent payment type.
	 */
	public String getDescription() {
		return description;
	}
	
	public void setOperation(com.smartbt.vtsuite.servercommon.model.Operation value) {
		this.operation = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.Operation getOperation() {
		return operation;
	}
	
	public void setMode(com.smartbt.vtsuite.servercommon.model.Mode value) {
		this.mode = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.Mode getMode() {
		return mode;
	}
	
	public void setRecurringPayment(java.util.Collection<com.smartbt.vtsuite.servercommon.model.RecurringPayment> value) {
		this.recurringPayment = value;
	}
	
	public java.util.Collection<com.smartbt.vtsuite.servercommon.model.RecurringPayment> getRecurringPayment() {
		return recurringPayment;
	}
	
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
