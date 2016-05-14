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
 * Stores all parameters that will be available for the merchant.
 */
@XmlRootElement
public class MerchantParameter implements Serializable {
	public MerchantParameter() {
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
	private java.util.Collection<com.smartbt.vtsuite.servercommon.model.MerchantParameterValue> merchantParameterValue = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.MerchantParameterValue>();
	
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
	 * Parameter's name.
	 */
	public void setParameter(String value) {
		this.parameter = value;
	}
	
	/**
	 * Parameter's name.
	 */
	public String getParameter() {
		return parameter;
	}
	
	/**
	 * Parameter's description.
	 */
	public void setDescription(String value) {
		this.description = value;
	}
	
	/**
	 * Parameter's description.
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Parameter's default value.
	 */
	public void setDefaultValue(String value) {
		this.defaultValue = value;
	}
	
	/**
	 * Parameter's default value.
	 */
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
	
	public void setMerchantParameterValue(java.util.Collection<com.smartbt.vtsuite.servercommon.model.MerchantParameterValue> value) {
		this.merchantParameterValue = value;
	}
	
	public java.util.Collection<com.smartbt.vtsuite.servercommon.model.MerchantParameterValue> getMerchantParameterValue() {
		return merchantParameterValue;
	}
	
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
