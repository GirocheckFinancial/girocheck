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
 * Stores all devices.
 */
@XmlRootElement
public class Device implements Serializable {
	public Device() {
	}
	
	private int id;
	
	private com.smartbt.vtsuite.servercommon.model.Terminal terminal;
	
	private com.smartbt.vtsuite.servercommon.model.ProductType productType;
	
	private String serialNumber;
	
	private String description;
	
	private Boolean active;
	
	private Integer prodType;
	
	private java.util.Collection<com.smartbt.vtsuite.servercommon.model.DeviceParameterValue> deviceParameterValue = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.DeviceParameterValue>();
	
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
	 * Device's number.
	 */
	public void setSerialNumber(String value) {
		this.serialNumber = value;
	}
	
	/**
	 * Device's number.
	 */
	public String getSerialNumber() {
		return serialNumber;
	}
	
	/**
	 * Device's description.
	 */
	public void setDescription(String value) {
		this.description = value;
	}
	
	/**
	 * Device's description.
	 */
	public String getDescription() {
		return description;
	}
	@JsonIgnore
	public void setActive(boolean value) {
		setActive(new Boolean(value));
	}
	
	public void setActive(Boolean value) {
		this.active = value;
	}
	
	public Boolean getActive() {
		return active;
	}
	@JsonIgnore
	public void setProdType(int value) {
		setProdType(new Integer(value));
	}
	
	public void setProdType(Integer value) {
		this.prodType = value;
	}
	
	public Integer getProdType() {
		return prodType;
	}
	
	public void setProductType(com.smartbt.vtsuite.servercommon.model.ProductType value) {
		this.productType = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.ProductType getProductType() {
		return productType;
	}
	
	public void setTerminal(com.smartbt.vtsuite.servercommon.model.Terminal value) {
		this.terminal = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.Terminal getTerminal() {
		return terminal;
	}
	
	public void setDeviceParameterValue(java.util.Collection<com.smartbt.vtsuite.servercommon.model.DeviceParameterValue> value) {
		this.deviceParameterValue = value;
	}
	
	public java.util.Collection<com.smartbt.vtsuite.servercommon.model.DeviceParameterValue> getDeviceParameterValue() {
		return deviceParameterValue;
	}
	
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
