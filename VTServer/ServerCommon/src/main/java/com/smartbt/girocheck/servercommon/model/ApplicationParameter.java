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
public class ApplicationParameter implements Serializable {
	public ApplicationParameter() {
	}
	
	private int id;
	
	private String name;
	
	private String value;
	
	private Integer dataType;
	
	private Boolean readOnly;
	
	private Integer application;
	
	private String description;
	
	public void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return id;
	}
	
	public int getORMID() {
		return getId();
	}
	
	public void setName(String value) {
		this.name = value;
	}
	
	public String getName() {
		return name;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setDataType(int value) {
		setDataType(new Integer(value));
	}
	
	public void setDataType(Integer value) {
		this.dataType = value;
	}
	
	public Integer getDataType() {
		return dataType;
	}
	
	public void setReadOnly(boolean value) {
		setReadOnly(new Boolean(value));
	}
	
	public void setReadOnly(Boolean value) {
		this.readOnly = value;
	}
	
	public Boolean getReadOnly() {
		return readOnly;
	}
	
	public void setApplication(int value) {
		setApplication(new Integer(value));
	}
	
	public void setApplication(Integer value) {
		this.application = value;
	}
	
	public Integer getApplication() {
		return application;
	}
	
	public void setDescription(String value) {
		this.description = value;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
