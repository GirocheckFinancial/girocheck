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
public class TerminalHostParameter implements Serializable {
	public TerminalHostParameter() {
	}
	
	private int id;
	
	private com.smartbt.vtsuite.servercommon.model.Host host;
	
	private com.smartbt.vtsuite.servercommon.model.DataType dataType;
	
	private String parameter;
	
	private String description;
	
	private String defaultValue;
	
	private java.util.Collection<com.smartbt.vtsuite.servercommon.model.TerminalHostParameterValue> terminalHostParameterValue = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.TerminalHostParameterValue>();
	
	public void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return id;
	}	
	
	public void setParameter(String value) {
		this.parameter = value;
	}
	
	public String getParameter() {
		return parameter;
	}
	
	public void setDescription(String value) {
		this.description = value;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDefaultValue(String value) {
		this.defaultValue = value;
	}
	
	public String getDefaultValue() {
		return defaultValue;
	}
	
	public void setHost(com.smartbt.vtsuite.servercommon.model.Host value) {
		this.host = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.Host getHost() {
		return host;
	}
	
	public void setDataType(com.smartbt.vtsuite.servercommon.model.DataType value) {
		this.dataType = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.DataType getDataType() {
		return dataType;
	}
	
	public void setTerminalHostParameterValue(java.util.Collection<com.smartbt.vtsuite.servercommon.model.TerminalHostParameterValue> value) {
		this.terminalHostParameterValue = value;
	}
	
	public java.util.Collection<com.smartbt.vtsuite.servercommon.model.TerminalHostParameterValue> getTerminalHostParameterValue() {
		return terminalHostParameterValue;
	}
	
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
