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
 * Stores sensitive information in encrypted format.
 */
@XmlRootElement
public class SensitiveData implements Serializable {
	public SensitiveData() {
	}
	
	private int id;
	
	private String encryptedData;
	
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
	 * Data in encrypted format.
	 */
	public void setEncryptedData(String value) {
		this.encryptedData = value;
	}
	
	/**
	 * Data in encrypted format.
	 */
	public String getEncryptedData() {
		return encryptedData;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
