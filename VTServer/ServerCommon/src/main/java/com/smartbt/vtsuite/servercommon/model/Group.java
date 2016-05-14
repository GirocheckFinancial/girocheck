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
 * Stores all groups.
 */
@XmlRootElement
public class Group implements Serializable {
	public Group() {
	}
	
	private int id;
	
	private String name;
	
	private String description;
	
	private Boolean active;
	
	private java.util.Collection<com.smartbt.vtsuite.servercommon.model.Customer> customer = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.Customer>();
	
	private java.util.Collection<com.smartbt.vtsuite.servercommon.model.GroupParameterValue> groupParameterValue = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.GroupParameterValue>();
	
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
	 * Group's name.
	 */
	public void setName(String value) {
		this.name = value;
	}
	
	/**
	 * Group's name.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Group's description.
	 */
	public void setDescription(String value) {
		this.description = value;
	}
	
	/**
	 * Group's description.
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Group's flag for active/deactivate.
	 */
        @JsonIgnore
	public void setActive(boolean value) {
		setActive(new Boolean(value));
	}
	
	/**
	 * Group's flag for active/deactivate.
	 */
	public void setActive(Boolean value) {
		this.active = value;
	}
	
	/**
	 * Group's flag for active/deactivate.
	 */
	public Boolean getActive() {
		return active;
	}
	
	public void setCustomer(java.util.Collection<com.smartbt.vtsuite.servercommon.model.Customer> value) {
		this.customer = value;
	}
	
	public java.util.Collection<com.smartbt.vtsuite.servercommon.model.Customer> getCustomer() {
		return customer;
	}
	
	
	public void setGroupParameterValue(java.util.Collection<com.smartbt.vtsuite.servercommon.model.GroupParameterValue> value) {
		this.groupParameterValue = value;
	}
	
	public java.util.Collection<com.smartbt.vtsuite.servercommon.model.GroupParameterValue> getGroupParameterValue() {
		return groupParameterValue;
	}
	
	
	public void addCustomer(com.smartbt.vtsuite.servercommon.model.Customer c) {
		
		        getCustomer().add(c);
		        c.setGroup(this);
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
