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
 * Establish the relationship (m:m) between user's role and privilege.
 */
@XmlRootElement
public class UserRolePrivilege implements Serializable {
	public UserRolePrivilege() {
	}
	
	private int id;
	
	private com.smartbt.vtsuite.servercommon.model.UserRole userRole;
	
	private com.smartbt.vtsuite.servercommon.model.UserPrivilege userPrivilege;
	
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
	
	public void setUserRole(com.smartbt.vtsuite.servercommon.model.UserRole value) {
		this.userRole = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.UserRole getUserRole() {
		return userRole;
	}
	
	public void setUserPrivilege(com.smartbt.vtsuite.servercommon.model.UserPrivilege value) {
		this.userPrivilege = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.UserPrivilege getUserPrivilege() {
		return userPrivilege;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
