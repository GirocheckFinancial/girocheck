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
@XmlRootElement
public class VTSession implements Serializable {
	public VTSession() {
	}
	
	private int id;
	
	private com.smartbt.vtsuite.servercommon.model.Clerk clerk;
	
	private com.smartbt.vtsuite.servercommon.model.Terminal terminal;
	
	private String token;
	
	private java.util.Date lastUpdated;
	
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
	
	public void setLastUpdated(java.util.Date value) {
		this.lastUpdated = value;
	}
	
	public java.util.Date getLastUpdated() {
		return lastUpdated;
	}
	
	public void setSessionInfo(String value) {
		this.sessionInfo = value;
	}
	
	public String getSessionInfo() {
		return sessionInfo;
	}
	
	public void setTerminal(com.smartbt.vtsuite.servercommon.model.Terminal value) {
		this.terminal = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.Terminal getTerminal() {
		return terminal;
	}
	
	public void setClerk(com.smartbt.vtsuite.servercommon.model.Clerk value) {
		this.clerk = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.Clerk getClerk() {
		return clerk;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
