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
 * Establish the relationship between clerk's role and privilege.
 */
@XmlRootElement
public class ClerkRolePrivilege implements Serializable {
	public ClerkRolePrivilege() {
	}
	
	private int id;
	
	private com.smartbt.vtsuite.servercommon.model.ClerkRole clerkRole;
	
	private com.smartbt.vtsuite.servercommon.model.ClerkPrivilege clerkPrivilege;
	
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
	
	public void setClerkRole(com.smartbt.vtsuite.servercommon.model.ClerkRole value) {
		this.clerkRole = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.ClerkRole getClerkRole() {
		return clerkRole;
	}
	
	public void setClerkPrivilege(com.smartbt.vtsuite.servercommon.model.ClerkPrivilege value) {
		this.clerkPrivilege = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.ClerkPrivilege getClerkPrivilege() {
		return clerkPrivilege;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
