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
 * Establish the relationship (m:m) between devices and parameters.
 */
@XmlRootElement
public class DeviceParameterValue implements Serializable {
	public DeviceParameterValue() {
	}
	
	private int id;
	
	private com.smartbt.vtsuite.servercommon.model.Device device;
	
	private com.smartbt.vtsuite.servercommon.model.DeviceParameter deviceParameter;
	
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
	
	public void setDevice(com.smartbt.vtsuite.servercommon.model.Device value) {
		this.device = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.Device getDevice() {
		return device;
	}
	
	public void setDeviceParameter(com.smartbt.vtsuite.servercommon.model.DeviceParameter value) {
		this.deviceParameter = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.DeviceParameter getDeviceParameter() {
		return deviceParameter;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
