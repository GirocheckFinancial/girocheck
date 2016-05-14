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
 * Stores all clerk's role.
 */
@XmlRootElement
public class ClerkRole implements Serializable {
	public ClerkRole() {
	}
	
	private int id;
	
	private String name;
	
	private String description;
	
	private boolean active;
	
	private java.util.Collection<com.smartbt.vtsuite.servercommon.model.ClerkRolePrivilege> clerkRolePrivilege = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.ClerkRolePrivilege>();
	
	private java.util.Collection<com.smartbt.vtsuite.servercommon.model.Clerk> clerk = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.Clerk>();
	
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
	
	public void setName(String value) {
		this.name = value;
	}
	
	public String getName() {
		return name;
	}
	
	public void setDescription(String value) {
		this.description = value;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setActive(boolean value) {
		this.active = value;
	}
	
	public boolean getActive() {
		return active;
	}
	
	public void setClerkRolePrivilege(java.util.Collection<com.smartbt.vtsuite.servercommon.model.ClerkRolePrivilege> value) {
		this.clerkRolePrivilege = value;
	}
	
	public java.util.Collection<com.smartbt.vtsuite.servercommon.model.ClerkRolePrivilege> getClerkRolePrivilege() {
		return clerkRolePrivilege;
	}
	
	
	public void setClerk(java.util.Collection<com.smartbt.vtsuite.servercommon.model.Clerk> value) {
		this.clerk = value;
	}
	
	public java.util.Collection<com.smartbt.vtsuite.servercommon.model.Clerk> getClerk() {
		return clerk;
	}
	
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
