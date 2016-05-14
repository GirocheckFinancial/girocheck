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
public class Role implements Serializable {
	public Role() {
	}
	
	private int id;
	
	private String name;
        
        private String description;
	
	private java.util.Set<com.smartbt.girocheck.servercommon.model.User> user = new java.util.HashSet<com.smartbt.girocheck.servercommon.model.User>();
	
	private java.util.Set<com.smartbt.girocheck.servercommon.model.RolePrivilege> rolPrivilege = new java.util.HashSet<com.smartbt.girocheck.servercommon.model.RolePrivilege>();
	
	private void setId(int value) {
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
	
	public void setUser(java.util.Set<com.smartbt.girocheck.servercommon.model.User> value) {
		this.user = value;
	}
	
	public java.util.Set<com.smartbt.girocheck.servercommon.model.User> getUser() {
		return user;
	}
	
	
	public void setRolPrivilege(java.util.Set<com.smartbt.girocheck.servercommon.model.RolePrivilege> value) {
		this.rolPrivilege = value;
	}
	
	public java.util.Set<com.smartbt.girocheck.servercommon.model.RolePrivilege> getRolPrivilege() {
		return rolPrivilege;
	}
	
	
	public String toString() {
		return String.valueOf(getId());
	}

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    
    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
	
}
