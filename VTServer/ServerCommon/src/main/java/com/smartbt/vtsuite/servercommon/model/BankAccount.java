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
 * Stores extra transaction's information when it involves checks.
 */
@XmlRootElement
public class BankAccount extends com.smartbt.vtsuite.servercommon.model.Account implements Serializable {
	public BankAccount() {
	}
	
	private com.smartbt.vtsuite.servercommon.model.BankInformation bankInformation;
	
	public void setBankInformation(com.smartbt.vtsuite.servercommon.model.BankInformation value) {
		this.bankInformation = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.BankInformation getBankInformation() {
		return bankInformation;
	}
	
	public String toString() {
		return super.toString();
	}
	
}
