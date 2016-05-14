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
package com.smartbt.girocheck.servercommon.model;
import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
@XmlRootElement
public class VTInstanceProperty implements Serializable {
	public VTInstanceProperty() {
	}
	
	private int id;
	
	private String propertyName;
	
	private String propertyValue;
	
	public void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return id;
	}
		
	public void setPropertyName(String value) {
		this.propertyName = value;
	}
	
	public String getPropertyName() {
		return propertyName;
	}
	
	public void setPropertyValue(String value) {
		this.propertyValue = value;
	}
	
	public String getPropertyValue() {
		return propertyValue;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
