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
public class WatchdogEntityClerk implements Serializable {
	public WatchdogEntityClerk() {
	}
	
	private int id;
	@JsonIgnore
	private com.smartbt.vtsuite.servercommon.model.WatchdogEntity watchdogEntity;
	
	private com.smartbt.vtsuite.servercommon.model.Clerk clerk;
	
	public void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return id;
	}
		
	public void setWatchdogEntity(com.smartbt.vtsuite.servercommon.model.WatchdogEntity value) {
		this.watchdogEntity = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.WatchdogEntity getWatchdogEntity() {
		return watchdogEntity;
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
