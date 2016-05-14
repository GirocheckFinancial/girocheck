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
 * Stores all rules that will be available for recurrent payments.
 */
@XmlRootElement
public class Rule implements Serializable {
	public Rule() {
	}
	
	private int id;
	
	private String description;
	
	private String name;
	
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
	 * Rule's description.
	 */
	public void setDescription(String value) {
		this.description = value;
	}
	
	/**
	 * Rule's description.
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Rule's name.
	 */
	public void setName(String value) {
		this.name = value;
	}
	
	/**
	 * Rule's name.
	 */
	public String getName() {
		return name;
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
