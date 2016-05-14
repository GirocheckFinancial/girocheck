/**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 * 
 * This is an automatic generated file. It will be regenerated every time 
 * you generate persistence class.
 * 
 * Modifying its content may cause the program not work, or your work may lost.
 */

/**
 * Licensee: 
 * License Type: Evaluation
 */
package com.smartbt.girocheck.servercommon.model;

import java.io.Serializable;
public class AgrupationParameterValue implements Serializable {
	public AgrupationParameterValue() {
	}
	
	private int id;
	
	private com.smartbt.girocheck.servercommon.model.Agrupation agrupation;
	
	private com.smartbt.girocheck.servercommon.model.AgrupationParameter parameter;
	
	private String value;
	
	private void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return id;
	}
	
	public int getORMID() {
		return getId();
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setAgrupation(com.smartbt.girocheck.servercommon.model.Agrupation value) {
		this.agrupation = value;
	}
	
	public com.smartbt.girocheck.servercommon.model.Agrupation getAgrupation() {
		return agrupation;
	}
	
	public void setParameter(com.smartbt.girocheck.servercommon.model.AgrupationParameter value) {
		this.parameter = value;
	}
	
	public com.smartbt.girocheck.servercommon.model.AgrupationParameter getParameter() {
		return parameter;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
