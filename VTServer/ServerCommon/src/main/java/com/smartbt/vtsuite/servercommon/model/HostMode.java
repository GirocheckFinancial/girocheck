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
public class HostMode implements Serializable {
	public HostMode() {
	}
	
	private int id;
	
	private com.smartbt.vtsuite.servercommon.model.Host host;
	
	private com.smartbt.vtsuite.servercommon.model.Mode mode;
	
	private java.util.Collection<com.smartbt.vtsuite.servercommon.model.HostModeMerchant> hostModeMerchant = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.HostModeMerchant>();
	
	private java.util.Collection<com.smartbt.vtsuite.servercommon.model.HostModeOperation> hostModeOperation = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.HostModeOperation>();
	
	public void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return id;
	}		
	
	public void setMode(com.smartbt.vtsuite.servercommon.model.Mode value) {
		this.mode = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.Mode getMode() {
		return mode;
	}
	
	public void setHost(com.smartbt.vtsuite.servercommon.model.Host value) {
		this.host = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.Host getHost() {
		return host;
	}
	
	public void setHostModeMerchant(java.util.Collection<com.smartbt.vtsuite.servercommon.model.HostModeMerchant> value) {
		this.hostModeMerchant = value;
	}
	
	public java.util.Collection<com.smartbt.vtsuite.servercommon.model.HostModeMerchant> getHostModeMerchant() {
		return hostModeMerchant;
	}
	
	
	public void setHostModeOperation(java.util.Collection<com.smartbt.vtsuite.servercommon.model.HostModeOperation> value) {
		this.hostModeOperation = value;
	}
	
	public java.util.Collection<com.smartbt.vtsuite.servercommon.model.HostModeOperation> getHostModeOperation() {
		return hostModeOperation;
	}
	
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
