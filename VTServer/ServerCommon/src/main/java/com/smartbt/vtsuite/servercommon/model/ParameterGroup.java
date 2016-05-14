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
 * Specialization for parameters of type receipt.
 */
@XmlRootElement
public class ParameterGroup implements Serializable {
	public ParameterGroup() {
	}
	
	private int id;
	
	private String name;
	
	private String description;
	
	private java.util.Collection<com.smartbt.vtsuite.servercommon.model.MerchantParameter> merchantParameter = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.MerchantParameter>();
	
	private java.util.Collection<com.smartbt.vtsuite.servercommon.model.ClerkParameter> clerkParameter = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.ClerkParameter>();
	
	private java.util.Collection<com.smartbt.vtsuite.servercommon.model.CustomerParameter> customerParameter = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.CustomerParameter>();
	
	private java.util.Collection<com.smartbt.vtsuite.servercommon.model.GroupParameter> groupParameter = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.GroupParameter>();
	
	private java.util.Collection<com.smartbt.vtsuite.servercommon.model.HostParameter> hostParameter = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.HostParameter>();
	
	private java.util.Collection<com.smartbt.vtsuite.servercommon.model.DeviceParameter> deviceParameter = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.DeviceParameter>();
	
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
	 * Parameter's entity name.
	 */
	public void setName(String value) {
		this.name = value;
	}
	
	/**
	 * Parameter's entity name.
	 */
	public String getName() {
		return name;
	}
	
	public void setDescription(String value) {
		this.description = value;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setMerchantParameter(java.util.Collection<com.smartbt.vtsuite.servercommon.model.MerchantParameter> value) {
		this.merchantParameter = value;
	}
	
	public java.util.Collection<com.smartbt.vtsuite.servercommon.model.MerchantParameter> getMerchantParameter() {
		return merchantParameter;
	}
	
	
	public void setClerkParameter(java.util.Collection<com.smartbt.vtsuite.servercommon.model.ClerkParameter> value) {
		this.clerkParameter = value;
	}
	
	public java.util.Collection<com.smartbt.vtsuite.servercommon.model.ClerkParameter> getClerkParameter() {
		return clerkParameter;
	}
	
	
	public void setCustomerParameter(java.util.Collection<com.smartbt.vtsuite.servercommon.model.CustomerParameter> value) {
		this.customerParameter = value;
	}
	
	public java.util.Collection<com.smartbt.vtsuite.servercommon.model.CustomerParameter> getCustomerParameter() {
		return customerParameter;
	}
	
	
	public void setGroupParameter(java.util.Collection<com.smartbt.vtsuite.servercommon.model.GroupParameter> value) {
		this.groupParameter = value;
	}
	
	public java.util.Collection<com.smartbt.vtsuite.servercommon.model.GroupParameter> getGroupParameter() {
		return groupParameter;
	}
	
	
	public void setHostParameter(java.util.Collection<com.smartbt.vtsuite.servercommon.model.HostParameter> value) {
		this.hostParameter = value;
	}
	
	public java.util.Collection<com.smartbt.vtsuite.servercommon.model.HostParameter> getHostParameter() {
		return hostParameter;
	}
	
	
	public void setDeviceParameter(java.util.Collection<com.smartbt.vtsuite.servercommon.model.DeviceParameter> value) {
		this.deviceParameter = value;
	}
	
	public java.util.Collection<com.smartbt.vtsuite.servercommon.model.DeviceParameter> getDeviceParameter() {
		return deviceParameter;
	}
	
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
