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
 * Stores all countries.
 */
@XmlRootElement
public class Country implements Serializable {
	public Country() {
	}
	
	private int id;
	
	private String name;
	
	private String abbreviation;
	
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
	
	public void setName(String value) {
		this.name = value;
	}
	
	public String getName() {
		return name;
	}
	
	public void setAbbreviation(String value) {
		this.abbreviation = value;
	}
	
	public String getAbbreviation() {
		return abbreviation;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
