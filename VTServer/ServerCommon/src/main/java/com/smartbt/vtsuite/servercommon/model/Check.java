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
public class Check extends com.smartbt.vtsuite.servercommon.model.Account implements Serializable {
	public Check() {
	}
	
	private com.smartbt.vtsuite.servercommon.model.BankInformation bankInformation;
	
	private Integer checkNumber;
	
	private java.util.Date date;
	
	private String memo;
	@JsonIgnore
	public void setCheckNumber(int value) {
		setCheckNumber(new Integer(value));
	}
	
	public void setCheckNumber(Integer value) {
		this.checkNumber = value;
	}
	
	public Integer getCheckNumber() {
		return checkNumber;
	}
	
	public void setDate(java.util.Date value) {
		this.date = value;
	}
	
	public java.util.Date getDate() {
		return date;
	}
	
	public void setMemo(String value) {
		this.memo = value;
	}
	
	public String getMemo() {
		return memo;
	}
	
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
