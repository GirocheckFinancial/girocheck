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
import org.codehaus.jackson.annotate.JsonIgnore;
/**
 * Stores all users.
 */
@XmlRootElement
public class User implements Serializable {
	public User() {
	}
	
        private static final long serialVersionUID = 1L;
        
	private int id;
	@JsonIgnore
	private com.smartbt.vtsuite.servercommon.model.UserRole userRole;
	
	private String username;
	
	private String password;
	
	private String firstName;
	
	private String middleInitial;
	
	private String lastName;
	
	private Boolean active;
	
	private String email;
	
	private java.util.Date deactivationDate;
	@JsonIgnore
	private java.util.Collection<com.smartbt.vtsuite.servercommon.model.AuditLog> auditLog = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.AuditLog>();
	
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
	 * User's user name.
	 */
	public void setUsername(String value) {
		this.username = value;
	}
	
	/**
	 * User's user name.
	 */
	public String getUsername() {
		return username;
	}
	
	public void setPassword(String value) {
		this.password = value;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setFirstName(String value) {
		this.firstName = value;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setMiddleInitial(String value) {
		this.middleInitial = value;
	}
	
	public String getMiddleInitial() {
		return middleInitial;
	}
	
	public void setLastName(String value) {
		this.lastName = value;
	}
	
	public String getLastName() {
		return lastName;
	}
	@JsonIgnore
	public void setActive(boolean value) {
		setActive(new Boolean(value));
	}
	
	public void setActive(Boolean value) {
		this.active = value;
	}
	
	public Boolean getActive() {
		return active;
	}
	
	public void setEmail(String value) {
		this.email = value;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setDeactivationDate(java.util.Date value) {
		this.deactivationDate = value;
	}
	
	public java.util.Date getDeactivationDate() {
		return deactivationDate;
	}
	
	public void setUserRole(com.smartbt.vtsuite.servercommon.model.UserRole value) {
		this.userRole = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.UserRole getUserRole() {
		return userRole;
	}
	
	public void setAuditLog(java.util.Collection<com.smartbt.vtsuite.servercommon.model.AuditLog> value) {
		this.auditLog = value;
	}
	
	public java.util.Collection<com.smartbt.vtsuite.servercommon.model.AuditLog> getAuditLog() {
		return auditLog;
	}
	
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
