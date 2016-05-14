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
 * Stores all telephone type that will be available. E.g.: 
 * name: Home, description: Home telephone. 
 * name: Mobile, description: Mobile telephone.
 */
@XmlRootElement
public class TelephoneType implements Serializable {
	public TelephoneType() {
	}
	
	private int id;
	
	private String name;
	
	private String description;
	
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
	 * Telephone type name. Eg: "movil", "home", "work".
	 */
	public void setName(String value) {
		this.name = value;
	}
	
	/**
	 * Telephone type name. Eg: "movil", "home", "work".
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Telephone type description.
	 */
	public void setDescription(String value) {
		this.description = value;
	}
	
	/**
	 * Telephone type description.
	 */
	public String getDescription() {
		return description;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
