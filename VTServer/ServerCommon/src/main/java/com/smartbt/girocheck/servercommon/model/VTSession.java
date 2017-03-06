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
package com.smartbt.girocheck.servercommon.model;
import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
@XmlRootElement
public class VTSession implements Serializable {
	public VTSession() {
	}
	
	private int id;
	
	private User user;
	
	private String token;
	
	private java.util.Date lastUpdate;
	
	private String sessionInfo; 
	
	public void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return id;
	}		
	
	public void setToken(String value) {
		this.token = value;
	}
	
	public String getToken() {
		return token;
	}
	
	public void setLastUpdate(java.util.Date value) {
		this.lastUpdate = value;
	}
	
	public java.util.Date getLastUpdate() {
		return lastUpdate;
	}
	
	public void setSessionInfo(String value) {
		this.sessionInfo = value;
	}
	
	public String getSessionInfo() {
		return sessionInfo;
	}
	
	public void setUser(User value) {
		this.user = value;
	}
	
	public User getUser() {
		return user;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
   
	
}
