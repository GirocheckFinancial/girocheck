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
public class HostModeMerchant implements Serializable {
	public HostModeMerchant() {
	}
	
	private int id;
	
	private com.smartbt.vtsuite.servercommon.model.HostMode hostMode;
	
	private com.smartbt.vtsuite.servercommon.model.Merchant merchant;
	
	public void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return id;
	}		
	
	public void setMerchant(com.smartbt.vtsuite.servercommon.model.Merchant value) {
		this.merchant = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.Merchant getMerchant() {
		return merchant;
	}
	
	public void setHostMode(com.smartbt.vtsuite.servercommon.model.HostMode value) {
		this.hostMode = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.HostMode getHostMode() {
		return hostMode;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
