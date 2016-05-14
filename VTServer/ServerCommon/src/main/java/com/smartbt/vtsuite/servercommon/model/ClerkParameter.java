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
@XmlRootElement
public class ClerkParameter implements Serializable {
	public ClerkParameter() {
	}
	
	private int id;
	@JsonIgnore
	private com.smartbt.vtsuite.servercommon.model.ParameterGroup parameterGroup;
	
	private com.smartbt.vtsuite.servercommon.model.DataType dataType;
	
	private String parameter;
	
	private String description;
	@JsonProperty("value")
	private String defaultValue;
	@JsonIgnore
	private java.util.Collection<com.smartbt.vtsuite.servercommon.model.ClerkParameterValue> clerkParameterValue = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.ClerkParameterValue>();
	
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
	
	public void setParameterGroup(com.smartbt.vtsuite.servercommon.model.ParameterGroup value) {
		this.parameterGroup = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.ParameterGroup getParameterGroup() {
		return parameterGroup;
	}
	
	public void setDataType(com.smartbt.vtsuite.servercommon.model.DataType value) {
		this.dataType = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.DataType getDataType() {
		return dataType;
	}
	
	public void setClerkParameterValue(java.util.Collection<com.smartbt.vtsuite.servercommon.model.ClerkParameterValue> value) {
		this.clerkParameterValue = value;
	}
	
	public java.util.Collection<com.smartbt.vtsuite.servercommon.model.ClerkParameterValue> getClerkParameterValue() {
		return clerkParameterValue;
	}
	
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
