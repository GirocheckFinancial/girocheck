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
public class MerchantHostParameterValue implements Serializable {
	public MerchantHostParameterValue() {
	}
	
	private int id;
	
	private com.smartbt.vtsuite.servercommon.model.MerchantHost merchantHost;
	
	private com.smartbt.vtsuite.servercommon.model.MerchantHostParameter merchantHostParameters;
	
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
	
	public void setMerchantHost(com.smartbt.vtsuite.servercommon.model.MerchantHost value) {
		this.merchantHost = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.MerchantHost getMerchantHost() {
		return merchantHost;
	}
	
	public void setMerchantHostParameters(com.smartbt.vtsuite.servercommon.model.MerchantHostParameter value) {
		this.merchantHostParameters = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.MerchantHostParameter getMerchantHostParameters() {
		return merchantHostParameters;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
