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
public class BankAddress extends com.smartbt.vtsuite.servercommon.model.Address implements Serializable {
	public BankAddress() {
	}
	
	private com.smartbt.vtsuite.servercommon.model.BankInformation bank;
	
	public void setBank(com.smartbt.vtsuite.servercommon.model.BankInformation value) {
		this.bank = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.BankInformation getBank() {
		return bank;
	}
	
	public String toString() {
		return super.toString();
	}
	
}
