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

import java.io.Serializable;
/**
 * Stores all clerk privileges.
 */
@javax.xml.bind.annotation.XmlRootElement
public class ClerkPrivilege implements Serializable {
	public ClerkPrivilege() {
	}
	
	private int id;
	
	private String name;
	
	private String description;
	
	private java.util.Collection<com.smartbt.vtsuite.servercommon.model.ClerkRolePrivilege> clerkRolePrivilege = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.ClerkRolePrivilege>();
	
	private java.util.Collection<com.smartbt.vtsuite.servercommon.model.HostModeOperation> hostModeOperation = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.HostModeOperation>();
	
	private java.util.Collection<com.smartbt.vtsuite.servercommon.model.ApplicationClerkPrivilege> applicationClerkPrivilege = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.ApplicationClerkPrivilege>();
	
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
	
	public int getORMID() {
		return getId();
	}
	
	/**
	 * Clerk privilege name.
	 */
	public void setName(String value) {
		this.name = value;
	}
	
	/**
	 * Clerk privilege name.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Clerk privilege description.
	 */
	public void setDescription(String value) {
		this.description = value;
	}
	
	/**
	 * Clerk privilege description.
	 */
	public String getDescription() {
		return description;
	}
	
	public void setClerkRolePrivilege(java.util.Collection<com.smartbt.vtsuite.servercommon.model.ClerkRolePrivilege> value) {
		this.clerkRolePrivilege = value;
	}
	
	public java.util.Collection<com.smartbt.vtsuite.servercommon.model.ClerkRolePrivilege> getClerkRolePrivilege() {
		return clerkRolePrivilege;
	}
	
	
	public void setHostModeOperation(java.util.Collection<com.smartbt.vtsuite.servercommon.model.HostModeOperation> value) {
		this.hostModeOperation = value;
	}
	
	public java.util.Collection<com.smartbt.vtsuite.servercommon.model.HostModeOperation> getHostModeOperation() {
		return hostModeOperation;
	}
	
	
	public void setApplicationClerkPrivilege(java.util.Collection<com.smartbt.vtsuite.servercommon.model.ApplicationClerkPrivilege> value) {
		this.applicationClerkPrivilege = value;
	}
	
	public java.util.Collection<com.smartbt.vtsuite.servercommon.model.ApplicationClerkPrivilege> getApplicationClerkPrivilege() {
		return applicationClerkPrivilege;
	}
	
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
