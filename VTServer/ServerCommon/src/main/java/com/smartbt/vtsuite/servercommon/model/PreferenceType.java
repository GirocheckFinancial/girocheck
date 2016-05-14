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
 * Stores all preference type that will be available. E.g.: 
 * name: Always email receipt?, description: Describes if the system will always send a receipt to the customer by email.
 */
@XmlRootElement
public class PreferenceType implements Serializable {
	public PreferenceType() {
	}
	
	private int id;
	
	private String name;
	
	private String description;
	
	private java.util.Collection<com.smartbt.vtsuite.servercommon.model.ClientPreference> clientPreference = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.ClientPreference>();
	
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
	 * Preference's name.
	 */
	public void setName(String value) {
		this.name = value;
	}
	
	/**
	 * Preference's name.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Preference's description.
	 */
	public void setDescription(String value) {
		this.description = value;
	}
	
	/**
	 * Preference's description.
	 */
	public String getDescription() {
		return description;
	}
	
	public void setClientPreference(java.util.Collection<com.smartbt.vtsuite.servercommon.model.ClientPreference> value) {
		this.clientPreference = value;
	}
	
	public java.util.Collection<com.smartbt.vtsuite.servercommon.model.ClientPreference> getClientPreference() {
		return clientPreference;
	}
	
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
