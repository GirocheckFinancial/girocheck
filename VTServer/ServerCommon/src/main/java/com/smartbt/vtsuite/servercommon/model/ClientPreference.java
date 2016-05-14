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
 * Establish the relationship (m:m) between client and preference type.
 */
@XmlRootElement
public class ClientPreference implements Serializable {
	public ClientPreference() {
	}
	
	private int id;
	
	private com.smartbt.vtsuite.servercommon.model.Client client;
	
	private com.smartbt.vtsuite.servercommon.model.PreferenceType preferenceType;
	
	private String value;
	
	/**
	 * Table record identification
	 */
	public void setId(int value) {
		this.id = value;
	}
	
	/**
	 * Table record identification
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * The value the client has for specific preference type.
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	/**
	 * The value the client has for specific preference type.
	 */
	public String getValue() {
		return value;
	}
	
	public void setClient(com.smartbt.vtsuite.servercommon.model.Client value) {
		this.client = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.Client getClient() {
		return client;
	}
	
	public void setPreferenceType(com.smartbt.vtsuite.servercommon.model.PreferenceType value) {
		this.preferenceType = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.PreferenceType getPreferenceType() {
		return preferenceType;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
