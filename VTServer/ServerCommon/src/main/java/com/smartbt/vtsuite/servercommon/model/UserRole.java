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
 * Stores all user's roles.
 */
@XmlRootElement
public class UserRole implements Serializable {
	public UserRole() {
	}
	
	private int id;
	
	private String name;
	
	private String description = "";
	
	private Boolean active;
	
	private java.util.Collection<com.smartbt.vtsuite.servercommon.model.User> user = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.User>();
	
	private java.util.Collection<com.smartbt.vtsuite.servercommon.model.UserRolePrivilege> userRolePrivilege = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.UserRolePrivilege>();
	
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
	 * Role's name.
	 */
	public void setName(String value) {
		this.name = value;
	}
	
	/**
	 * Role's name.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Date in which the role was created.
	 */
	public void setDescription(String value) {
		this.description = value;
	}
	
	/**
	 * Date in which the role was created.
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Flag for activate/deactivate the role.
	 */
	public void setActive(boolean value) {
		setActive(new Boolean(value));
	}
	
	/**
	 * Flag for activate/deactivate the role.
	 */
	public void setActive(Boolean value) {
		this.active = value;
	}
	
	/**
	 * Flag for activate/deactivate the role.
	 */
	public Boolean getActive() {
		return active;
	}
	
	public void setUser(java.util.Collection<com.smartbt.vtsuite.servercommon.model.User> value) {
		this.user = value;
	}
	
	public java.util.Collection<com.smartbt.vtsuite.servercommon.model.User> getUser() {
		return user;
	}
	
	
	public void setUserRolePrivilege(java.util.Collection<com.smartbt.vtsuite.servercommon.model.UserRolePrivilege> value) {
		this.userRolePrivilege = value;
	}
	
	public java.util.Collection<com.smartbt.vtsuite.servercommon.model.UserRolePrivilege> getUserRolePrivilege() {
		return userRolePrivilege;
	}
	
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
