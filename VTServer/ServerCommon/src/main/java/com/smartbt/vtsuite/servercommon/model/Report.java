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
public class Report implements Serializable {
	public Report() {
	}
	
	private int id;
	
	private String key;
	
	private String report;
	
	private java.sql.Timestamp createdAt;
	
	public void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return id;
	}		
	
	public void setKey(String value) {
		this.key = value;
	}
	
	public String getKey() {
		return key;
	}
	
	public void setReport(String value) {
		this.report = value;
	}
	
	public String getReport() {
		return report;
	}
	
	public void setCreatedAt(java.sql.Timestamp value) {
		this.createdAt = value;
	}
	
	public java.sql.Timestamp getCreatedAt() {
		return createdAt;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
