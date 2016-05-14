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
@javax.xml.bind.annotation.XmlRootElement
public class ApplicationClerkPrivilege implements Serializable {
	public ApplicationClerkPrivilege() {
	}
	
	private int id;
	
	private com.smartbt.vtsuite.servercommon.model.Application application;
	
	private com.smartbt.vtsuite.servercommon.model.ClerkPrivilege clerkPrivilege;
	
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
	
	public void setDescription(String value) {
		this.description = value;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setApplication(com.smartbt.vtsuite.servercommon.model.Application value) {
		this.application = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.Application getApplication() {
		return application;
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
