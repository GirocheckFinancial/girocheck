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
 * Stores all intervals that will be available for recurrent payments.
 */
@XmlRootElement
public class Intervals implements Serializable {
	public Intervals() {
	}
	
	private int id;
	
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
	 * Interval's name. Eg: "Monthly".
	 */
	public void setName(String value) {
		this.name = value;
	}
	
	/**
	 * Interval's name. Eg: "Monthly".
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Interval's description.
	 */
	public void setDescription(String value) {
		this.description = value;
	}
	
	/**
	 * Interval's description.
	 */
	public String getDescription() {
		return description;
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
