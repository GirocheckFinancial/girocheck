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
 * Establish the relationship (m:m) between groups and parameters.
 */
@XmlRootElement
public class GroupParameterValue implements Serializable {
	public GroupParameterValue() {
	}
	
	private int id;
	
	private com.smartbt.vtsuite.servercommon.model.Group group;
	
	private com.smartbt.vtsuite.servercommon.model.GroupParameter parameterGroup;
	
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
	
	public void setParameterGroup(com.smartbt.vtsuite.servercommon.model.GroupParameter value) {
		this.parameterGroup = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.GroupParameter getParameterGroup() {
		return parameterGroup;
	}
	
	public void setGroup(com.smartbt.vtsuite.servercommon.model.Group value) {
		this.group = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.Group getGroup() {
		return group;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
