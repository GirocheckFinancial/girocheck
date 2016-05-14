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
 * Stores all client's driver license.
 */
@XmlRootElement
public class DriverLicense extends com.smartbt.vtsuite.servercommon.model.SensitiveData implements Serializable {
	public DriverLicense() {
	}
	
	private com.smartbt.vtsuite.servercommon.model.State state;
	
	private java.util.Date expDate;
	
	private String hash;
	
	private com.smartbt.vtsuite.servercommon.model.Client client;
	
	/**
	 * Driver license expiration date.
	 */
	public void setExpDate(java.util.Date value) {
		this.expDate = value;
	}
	
	/**
	 * Driver license expiration date.
	 */
	public java.util.Date getExpDate() {
		return expDate;
	}
	
	public void setHash(String value) {
		this.hash = value;
	}
	
	public String getHash() {
		return hash;
	}
	
	public void setState(com.smartbt.vtsuite.servercommon.model.State value) {
		this.state = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.State getState() {
		return state;
	}
	
	public void setClient(com.smartbt.vtsuite.servercommon.model.Client value) {
		this.client = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.Client getClient() {
		return client;
	}
	
	public String toString() {
		return super.toString();
	}
	
}
