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
 * Establish the relationship (m:m) between rules and recurrent payments.
 */
@XmlRootElement
public class RecurringPaymentRule implements Serializable {
	public RecurringPaymentRule() {
	}
	
	private int id;
	
	private com.smartbt.vtsuite.servercommon.model.RecurringPayment recurringPayment;
	
	private com.smartbt.vtsuite.servercommon.model.Rule rule;
	
	private java.math.BigDecimal amount;
	
	private Boolean active;
	
	private int priority;
	
	private int counter;
	
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
	
	public void setAmount(java.math.BigDecimal value) {
		this.amount = value;
	}
	
	public java.math.BigDecimal getAmount() {
		return amount;
	}
	
	/**
	 * Flag for activate/deactivate the rule.
	 */
        @JsonIgnore
	public void setActive(boolean value) {
		setActive(new Boolean(value));
	}
	
	/**
	 * Flag for activate/deactivate the rule.
	 */
	public void setActive(Boolean value) {
		this.active = value;
	}
	
	/**
	 * Flag for activate/deactivate the rule.
	 */
	public Boolean getActive() {
		return active;
	}
	
	/**
	 * Rule's priority.
	 */
	public void setPriority(int value) {
		this.priority = value;
	}
	
	/**
	 * Rule's priority.
	 */
	public int getPriority() {
		return priority;
	}
	
	/**
	 * Total payment to be applied.
	 */
	public void setCounter(int value) {
		this.counter = value;
	}
	
	/**
	 * Total payment to be applied.
	 */
	public int getCounter() {
		return counter;
	}
	
	public void setRecurringPayment(com.smartbt.vtsuite.servercommon.model.RecurringPayment value) {
		this.recurringPayment = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.RecurringPayment getRecurringPayment() {
		return recurringPayment;
	}
	
	public void setRule(com.smartbt.vtsuite.servercommon.model.Rule value) {
		this.rule = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.Rule getRule() {
		return rule;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
