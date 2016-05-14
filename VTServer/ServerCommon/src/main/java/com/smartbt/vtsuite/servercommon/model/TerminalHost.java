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
import org.codehaus.jackson.annotate.JsonIgnore;
@XmlRootElement
public class TerminalHost implements Serializable {
	public TerminalHost() {
	}
	
	private int id;
	
	private com.smartbt.vtsuite.servercommon.model.Terminal terminal;
	
	private com.smartbt.vtsuite.servercommon.model.Host host;
	
	private Boolean terminalCapture;
	
	private java.util.Collection<com.smartbt.vtsuite.servercommon.model.TerminalHostParameterValue> terminalHostParameterValue = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.TerminalHostParameterValue>();
	
	public void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return id;
	}	
	@JsonIgnore
	public void setTerminalCapture(boolean value) {
		setTerminalCapture(new Boolean(value));
	}
	
	public void setTerminalCapture(Boolean value) {
		this.terminalCapture = value;
	}
	
	public Boolean getTerminalCapture() {
		return terminalCapture;
	}
	
	public void setHost(com.smartbt.vtsuite.servercommon.model.Host value) {
		this.host = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.Host getHost() {
		return host;
	}
	
	public void setTerminal(com.smartbt.vtsuite.servercommon.model.Terminal value) {
		this.terminal = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.Terminal getTerminal() {
		return terminal;
	}
	
	public void setTerminalHostParameterValue(java.util.Collection<com.smartbt.vtsuite.servercommon.model.TerminalHostParameterValue> value) {
		this.terminalHostParameterValue = value;
	}
	
	public java.util.Collection<com.smartbt.vtsuite.servercommon.model.TerminalHostParameterValue> getTerminalHostParameterValue() {
		return terminalHostParameterValue;
	}
	
	
	public void addTerminalHostParameterValue(com.smartbt.vtsuite.servercommon.model.TerminalHostParameterValue thpv) {
		
		        getTerminalHostParameterValue().add(thpv);
		        thpv.setTerminalHost(this);
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
