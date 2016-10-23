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
import java.util.Date;
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
        
        // Pasword restrictions field
        
        private Date lastTimeUpdatePassword;
	
        private String last5passwords;
        
        private Integer failedAttempts;
        
        private Date lastTimeFailedAttempt;
        
        
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
     * @return the lastTimeUpdatePassword
     */
    public Date getLastTimeUpdatePassword() {
        return lastTimeUpdatePassword;
    }

    /**
     * @param lastTimeUpdatePassword the lastTimeUpdatePassword to set
     */
    public void setLastTimeUpdatePassword(Date lastTimeUpdatePassword) {
        this.lastTimeUpdatePassword = lastTimeUpdatePassword;
    }

    /**
     * @return the last5passwords
     */
    public String getLast5passwords() {
        return last5passwords;
    }

    /**
     * @param last5passwords the last5passwords to set
     */
    public void setLast5passwords(String last5passwords) {
        this.last5passwords = last5passwords;
    }

    /**
     * @return the failedAttempts
     */
    public Integer getFailedAttempts() {
        return failedAttempts;
    }

    /**
     * @param failedAttempts the failedAttempts to set
     */
    public void setFailedAttempts(Integer failedAttempts) {
        this.failedAttempts = failedAttempts;
    }

    /**
     * @return the lastTimeFailedAttempt
     */
    public Date getLastTimeFailedAttempt() {
        return lastTimeFailedAttempt;
    }

    /**
     * @param lastTimeFailedAttempt the lastTimeFailedAttempt to set
     */
    public void setLastTimeFailedAttempt(Date lastTimeFailedAttempt) {
        this.lastTimeFailedAttempt = lastTimeFailedAttempt;
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
