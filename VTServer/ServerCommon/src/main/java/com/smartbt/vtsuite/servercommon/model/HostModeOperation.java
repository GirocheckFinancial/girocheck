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
public class HostModeOperation implements Serializable {
	public HostModeOperation() {
	}
	
	private int id;
	
	private com.smartbt.vtsuite.servercommon.model.HostMode hostMode;
	
	private com.smartbt.vtsuite.servercommon.model.Operation operation;
	
	private com.smartbt.vtsuite.servercommon.model.ClerkPrivilege privilege;
	
	private String ggLastUpdateBy;
	
	private java.sql.Timestamp ggLastUpdateByDt;
	
	private String ggCreateBy;
	
	private java.sql.Timestamp ggCreateByDt;
	
	public void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return id;
	}		
	
	public void setGgLastUpdateBy(String value) {
		this.ggLastUpdateBy = value;
	}
	
	public String getGgLastUpdateBy() {
		return ggLastUpdateBy;
	}
	
	public void setGgLastUpdateByDt(java.sql.Timestamp value) {
		this.ggLastUpdateByDt = value;
	}
	
	public java.sql.Timestamp getGgLastUpdateByDt() {
		return ggLastUpdateByDt;
	}
	
	public void setGgCreateBy(String value) {
		this.ggCreateBy = value;
	}
	
	public String getGgCreateBy() {
		return ggCreateBy;
	}
	
	public void setGgCreateByDt(java.sql.Timestamp value) {
		this.ggCreateByDt = value;
	}
	
	public java.sql.Timestamp getGgCreateByDt() {
		return ggCreateByDt;
	}
	
	public void setHostMode(com.smartbt.vtsuite.servercommon.model.HostMode value) {
		this.hostMode = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.HostMode getHostMode() {
		return hostMode;
	}
	
	public void setOperation(com.smartbt.vtsuite.servercommon.model.Operation value) {
		this.operation = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.Operation getOperation() {
		return operation;
	}
	
	public void setPrivilege(com.smartbt.vtsuite.servercommon.model.ClerkPrivilege value) {
		this.privilege = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.ClerkPrivilege getPrivilege() {
		return privilege;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
