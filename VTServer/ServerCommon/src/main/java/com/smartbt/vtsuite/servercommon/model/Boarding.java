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
@XmlRootElement
public class Boarding implements Serializable {
	public Boarding() {
	}
	
	private int id;
	
	private String name;
	
	private String sicCode;
	
	private String terminalID;
	
	private String merchantNumber;
	
	private String customerNumber;
	
	private String description;
	
	private java.util.Date activationDate;
	
	private Boolean active;
	
	private String boarded;
	
	public void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return id;
	}
	
	public void setName(String value) {
		this.name = value;
	}
	
	public String getName() {
		return name;
	}
	
	public void setSicCode(String value) {
		this.sicCode = value;
	}
	
	public String getSicCode() {
		return sicCode;
	}
	
	public void setTerminalID(String value) {
		this.terminalID = value;
	}
	
	public String getTerminalID() {
		return terminalID;
	}
	
	public void setMerchantNumber(String value) {
		this.merchantNumber = value;
	}
	
	public String getMerchantNumber() {
		return merchantNumber;
	}
	
	public void setCustomerNumber(String value) {
		this.customerNumber = value;
	}
	
	public String getCustomerNumber() {
		return customerNumber;
	}
	
	public void setDescription(String value) {
		this.description = value;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setActivationDate(java.util.Date value) {
		this.activationDate = value;
	}
	
	public java.util.Date getActivationDate() {
		return activationDate;
	}
	@JsonIgnore
	public void setActive(boolean value) {
		setActive(new Boolean(value));
	}
	
	public void setActive(Boolean value) {
		this.active = value;
	}
	
	public Boolean getActive() {
		return active;
	}
	
	public void setBoarded(String value) {
		this.boarded = value;
	}
	
	public String getBoarded() {
		return boarded;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
