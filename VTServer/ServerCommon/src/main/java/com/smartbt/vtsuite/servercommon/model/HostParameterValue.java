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
@XmlRootElement
public class HostParameterValue implements Serializable {
	public HostParameterValue() {
	}
	
	private int id;
	
	private com.smartbt.vtsuite.servercommon.model.Host host;
	
	private com.smartbt.vtsuite.servercommon.model.HostParameter hostParameter;
	
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
	
	public void setHost(com.smartbt.vtsuite.servercommon.model.Host value) {
		this.host = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.Host getHost() {
		return host;
	}
	
	public void setHostParameter(com.smartbt.vtsuite.servercommon.model.HostParameter value) {
		this.hostParameter = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.HostParameter getHostParameter() {
		return hostParameter;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
