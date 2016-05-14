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
import org.codehaus.jackson.annotate.JsonProperty;
/**
 * Establish the relationship (m:m) between customers and parameters.
 */
@XmlRootElement
public class CustomerParameterValue implements Serializable {
	public CustomerParameterValue() {
	}
	
	private int id;
	@JsonIgnore
	private com.smartbt.vtsuite.servercommon.model.Customer customer;
	@JsonProperty("parameter")
	private com.smartbt.vtsuite.servercommon.model.CustomerParameter customerParameter;
	
	private String value;
	
	public void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return id;
	}
			
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setCustomer(com.smartbt.vtsuite.servercommon.model.Customer value) {
		this.customer = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.Customer getCustomer() {
		return customer;
	}
	
	public void setCustomerParameter(com.smartbt.vtsuite.servercommon.model.CustomerParameter value) {
		this.customerParameter = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.CustomerParameter getCustomerParameter() {
		return customerParameter;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
