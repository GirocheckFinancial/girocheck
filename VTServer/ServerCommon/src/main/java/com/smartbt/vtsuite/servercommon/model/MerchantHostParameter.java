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
public class MerchantHostParameter implements Serializable {
	public MerchantHostParameter() {
	}
	
	private int id;
	
	private com.smartbt.vtsuite.servercommon.model.Host host;
	
	private com.smartbt.vtsuite.servercommon.model.DataType dataType;
	
	private String parameter;
	
	private String description;
	
	private String defaultValue;
	
	private java.util.Collection<com.smartbt.vtsuite.servercommon.model.MerchantHostParameterValue> merchantHostParameterValue = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.MerchantHostParameterValue>();
	
	public void setId(int value) {
		this.id = value;
	}
	
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
	
	public void setMerchantHostParameterValue(java.util.Collection<com.smartbt.vtsuite.servercommon.model.MerchantHostParameterValue> value) {
		this.merchantHostParameterValue = value;
	}
	
	public java.util.Collection<com.smartbt.vtsuite.servercommon.model.MerchantHostParameterValue> getMerchantHostParameterValue() {
		return merchantHostParameterValue;
	}
	
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
