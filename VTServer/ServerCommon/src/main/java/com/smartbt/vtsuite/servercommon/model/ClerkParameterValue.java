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
 * Establish the relationship (m:m) between clerk and parameters.
 */
@XmlRootElement
public class ClerkParameterValue implements Serializable {
	public ClerkParameterValue() {
	}
	
	private int id;
	
	private com.smartbt.vtsuite.servercommon.model.Clerk clerk;
	
	private com.smartbt.vtsuite.servercommon.model.ClerkParameter clerkParameter;
	
	private String value;
	
	public void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return id;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setClerkParameter(com.smartbt.vtsuite.servercommon.model.ClerkParameter value) {
		this.clerkParameter = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.ClerkParameter getClerkParameter() {
		return clerkParameter;
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
