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
import org.codehaus.jackson.annotate.JsonProperty;
@XmlRootElement
public class TerminalParameterValue implements Serializable {
	public TerminalParameterValue() {
	}
	
	private int id;
	@JsonIgnore
	private com.smartbt.vtsuite.servercommon.model.Terminal terminal;
	@JsonProperty("parameter")
	private com.smartbt.vtsuite.servercommon.model.ApplicationParameter applicationParameter;
	
	private String value;
	
	/**
	 * Table record identification.
	 */
	public void setId(int value) {
		this.id = value;
	}
	
	/**
	 * Table record identification.
	 */
	public int getId() {
		return id;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setTerminal(com.smartbt.vtsuite.servercommon.model.Terminal value) {
		this.terminal = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.Terminal getTerminal() {
		return terminal;
	}
	
	public void setApplicationParameter(com.smartbt.vtsuite.servercommon.model.ApplicationParameter value) {
		this.applicationParameter = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.ApplicationParameter getApplicationParameter() {
		return applicationParameter;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
