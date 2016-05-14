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
public class TerminalHostParameterValue implements Serializable {
	public TerminalHostParameterValue() {
	}
	
	private int id;
	
	private com.smartbt.vtsuite.servercommon.model.TerminalHost terminalHost;
	
	private com.smartbt.vtsuite.servercommon.model.TerminalHostParameter terminalHostParameter;
	
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
	
	public void setTerminalHost(com.smartbt.vtsuite.servercommon.model.TerminalHost value) {
		this.terminalHost = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.TerminalHost getTerminalHost() {
		return terminalHost;
	}
	
	public void setTerminalHostParameter(com.smartbt.vtsuite.servercommon.model.TerminalHostParameter value) {
		this.terminalHostParameter = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.TerminalHostParameter getTerminalHostParameter() {
		return terminalHostParameter;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
