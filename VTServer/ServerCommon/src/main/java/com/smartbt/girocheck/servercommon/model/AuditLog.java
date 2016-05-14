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
import org.codehaus.jackson.annotate.JsonIgnore;
/**
 * Stores the audit log information.
 */
@XmlRootElement
public class AuditLog implements Serializable {
	public AuditLog() {
	}
	
	private int id;
	
//	private com.smartbt.vtsuite.servercommon.model.Clerk clerk;
//	
//	private com.smartbt.vtsuite.servercommon.model.Terminal terminal;
	
	private com.smartbt.girocheck.servercommon.model.User user;
	
	private String details;
	
	private Integer category;
	
	private java.util.Date createdAt;
	
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
	
	public void setDetails(String value) {
		this.details = value;
	}
	
	public String getDetails() {
		return details;
	}
	
	/**
	 * Operation category. Eg: "400:error".
	 */
	public void setCategory(int value) {
		setCategory(new Integer(value));
	}
	
	/**
	 * Operation category. Eg: "400:error".
	 */
        @JsonIgnore
	public void setCategory(Integer value) {
		this.category = value;
	}
	
	/**
	 * Operation category. Eg: "400:error".
	 */
	public Integer getCategory() {
		return category;
	}
	
	public void setCreatedAt(java.util.Date value) {
		this.createdAt = value;
	}
	
	public java.util.Date getCreatedAt() {
		return createdAt;
	}
	
//	public void setClerk(com.smartbt.vtsuite.servercommon.model.Clerk value) {
//		this.clerk = value;
//	}
//	
//	public com.smartbt.vtsuite.servercommon.model.Clerk getClerk() {
//		return clerk;
//	}
//	
//	public void setTerminal(com.smartbt.vtsuite.servercommon.model.Terminal value) {
//		this.terminal = value;
//	}
//	
//	public com.smartbt.vtsuite.servercommon.model.Terminal getTerminal() {
//		return terminal;
//	}
	
	public void setUser(com.smartbt.girocheck.servercommon.model.User value) {
		this.user = value;
	}
	
	public com.smartbt.girocheck.servercommon.model.User getUser() {
		return user;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
