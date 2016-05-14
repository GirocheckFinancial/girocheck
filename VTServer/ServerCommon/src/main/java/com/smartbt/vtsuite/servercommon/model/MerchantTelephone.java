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
public class MerchantTelephone extends com.smartbt.vtsuite.servercommon.model.Telephone implements Serializable {
	public MerchantTelephone() {
	}
	
	private com.smartbt.vtsuite.servercommon.model.Merchant merchant;
	
	public void setMerchant(com.smartbt.vtsuite.servercommon.model.Merchant value) {
		this.merchant = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.Merchant getMerchant() {
		return merchant;
	}
	
	public String toString() {
		return super.toString();
	}
	
}
