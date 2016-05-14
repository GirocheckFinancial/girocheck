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
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnore;

@XmlRootElement
public class WatchdogAlertClerk implements Serializable {
	public WatchdogAlertClerk() {
	}
	
	private int id;
	@JsonIgnore
	private com.smartbt.vtsuite.servercommon.model.WatchdogAlert watchdogAlert;
	
	private com.smartbt.vtsuite.servercommon.model.Clerk clerk;
	
	public void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return id;
	}
		
	public void setWatchdogAlert(com.smartbt.vtsuite.servercommon.model.WatchdogAlert value) {
		this.watchdogAlert = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.WatchdogAlert getWatchdogAlert() {
		return watchdogAlert;
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
