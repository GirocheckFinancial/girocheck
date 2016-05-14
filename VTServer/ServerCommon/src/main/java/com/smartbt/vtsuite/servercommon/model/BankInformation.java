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
 * Stores bank information.
 */
@XmlRootElement
public class BankInformation implements Serializable {
	public BankInformation() {
	}
	
	private int id;
	
	private int routingNumber;
	
	private String name;
	
	private java.util.Collection<com.smartbt.vtsuite.servercommon.model.BankAccount> bankAccount = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.BankAccount>();
	
	private java.util.Collection<com.smartbt.vtsuite.servercommon.model.BankAddress> bankAddress = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.BankAddress>();
	
	private java.util.Collection<com.smartbt.vtsuite.servercommon.model.Check> dataSck = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.Check>();
	
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
	 * Bank's routing number.
	 */
	public void setRoutingNumber(int value) {
		this.routingNumber = value;
	}
	
	/**
	 * Bank's routing number.
	 */
	public int getRoutingNumber() {
		return routingNumber;
	}
	
	/**
	 * Bank's name.
	 */
	public void setName(String value) {
		this.name = value;
	}
	
	/**
	 * Bank's name.
	 */
	public String getName() {
		return name;
	}
	
	public void setBankAccount(java.util.Collection<com.smartbt.vtsuite.servercommon.model.BankAccount> value) {
		this.bankAccount = value;
	}
	
	public java.util.Collection<com.smartbt.vtsuite.servercommon.model.BankAccount> getBankAccount() {
		return bankAccount;
	}
	
	
	public void setBankAddress(java.util.Collection<com.smartbt.vtsuite.servercommon.model.BankAddress> value) {
		this.bankAddress = value;
	}
	
	public java.util.Collection<com.smartbt.vtsuite.servercommon.model.BankAddress> getBankAddress() {
		return bankAddress;
	}
	
	
	public void setDataSck(java.util.Collection<com.smartbt.vtsuite.servercommon.model.Check> value) {
		this.dataSck = value;
	}
	
	public java.util.Collection<com.smartbt.vtsuite.servercommon.model.Check> getDataSck() {
		return dataSck;
	}
	
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
