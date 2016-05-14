/**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 * 
 * This is an automatic generated file. It will be regenerated every time 
 * you generate persistence class.
 * 
 * Modifying its content may cause the program not work, or your work may lost.
 */

/**
 * Licensee: 
 * License Type: Evaluation
 */
package com.smartbt.girocheck.servercommon.model;

import java.io.Serializable;
public class TerminalParameterValue implements Serializable {
	public TerminalParameterValue() {
	}
	
	private int id;
	
	private com.smartbt.girocheck.servercommon.model.Terminal terminal;
	
	private com.smartbt.girocheck.servercommon.model.TerminalParameter parameter;
	
	private String value;
	
	private void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return id;
	}
	
	public int getORMID() {
		return getId();
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setParameter(com.smartbt.girocheck.servercommon.model.TerminalParameter value) {
		this.parameter = value;
	}
	
	public com.smartbt.girocheck.servercommon.model.TerminalParameter getParameter() {
		return parameter;
	}
	
	public void setTerminal(com.smartbt.girocheck.servercommon.model.Terminal value) {
		this.terminal = value;
	}
	
	public com.smartbt.girocheck.servercommon.model.Terminal getTerminal() {
		return terminal;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
