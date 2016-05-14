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
public class Telephone implements Serializable {
	public Telephone() {
	}
	
	private int id;
	
	private com.smartbt.vtsuite.servercommon.model.TelephoneType telephoneType;
	
	private String number;
	
	public void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return id;
	}
	
	public void setNumber(String value) {
		this.number = value;
	}
	
	public String getNumber() {
		return number;
	}
	
	public void setTelephoneType(com.smartbt.vtsuite.servercommon.model.TelephoneType value) {
		this.telephoneType = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.TelephoneType getTelephoneType() {
		return telephoneType;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
