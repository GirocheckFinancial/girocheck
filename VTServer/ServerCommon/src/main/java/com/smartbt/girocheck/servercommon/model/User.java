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
public class User implements Serializable {
	public User() {
	}
	
	private int id;
	
	private String username;
	
	private String password;
	
	private String firstName;
	
	private String lastName;
	
	private Boolean active;
	
	private String email;
	
	private com.smartbt.girocheck.servercommon.model.Role role;
	
	private void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return id;
	}
	
	public int getORMID() {
		return getId();
	}
	
	public void setUsername(String value) {
		this.username = value;
	}
	
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
	
	public void setLastName(String value) {
		this.lastName = value;
	}
	
	public String getLastName() {
		return lastName;
	}
	
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
	
	public void setRole(com.smartbt.girocheck.servercommon.model.Role value) {
		this.role = value;
	}
	
	public com.smartbt.girocheck.servercommon.model.Role getRole() {
		return role;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}

    /**
     * @return the userRole
     */
//    public com.smartbt.vtsuite.servercommon.model.UserRole getUserRole() {
//        return userRole;
//    }

    /**
     * @param userRole the userRole to set
     */
//    public void setUserRole(com.smartbt.vtsuite.servercommon.model.UserRole userRole) {
//        this.userRole = userRole;
//    }
	
}
