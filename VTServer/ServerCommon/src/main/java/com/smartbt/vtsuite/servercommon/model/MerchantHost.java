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
 * Establish the relationship (m:m) between merchants and processors.
 */
@XmlRootElement
public class MerchantHost implements Serializable {
	public MerchantHost() {
	}
	
	private int id;
	
	private com.smartbt.vtsuite.servercommon.model.Merchant merchant;
	
	private com.smartbt.vtsuite.servercommon.model.Host host;
	
	private Boolean hostCapture;
	
	private java.util.Collection<com.smartbt.vtsuite.servercommon.model.MerchantHostParameterValue> merchantHostParameterValue = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.MerchantHostParameterValue>();
	
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
	@JsonIgnore
	public void setHostCapture(boolean value) {
		setHostCapture(new Boolean(value));
	}
	
	public void setHostCapture(Boolean value) {
		this.hostCapture = value;
	}
	
	public Boolean getHostCapture() {
		return hostCapture;
	}
	
	public void setMerchant(com.smartbt.vtsuite.servercommon.model.Merchant value) {
		this.merchant = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.Merchant getMerchant() {
		return merchant;
	}
	
	public void setHost(com.smartbt.vtsuite.servercommon.model.Host value) {
		this.host = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.Host getHost() {
		return host;
	}
	
	public void setMerchantHostParameterValue(java.util.Collection<com.smartbt.vtsuite.servercommon.model.MerchantHostParameterValue> value) {
		this.merchantHostParameterValue = value;
	}
	
	public java.util.Collection<com.smartbt.vtsuite.servercommon.model.MerchantHostParameterValue> getMerchantHostParameterValue() {
		return merchantHostParameterValue;
	}
	
	
	public void addMerchantHostParameterValue(com.smartbt.vtsuite.servercommon.model.MerchantHostParameterValue mhpv) {
		
		        getMerchantHostParameterValue().add(mhpv);
		        mhpv.setMerchantHost(this);
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
