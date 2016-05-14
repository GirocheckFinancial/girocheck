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
 * Stores all processors.
 */
@XmlRootElement
public class Host implements Serializable {
	public Host() {
	}
	
	private int id;
	
	private String name;
	
	private java.util.Collection<com.smartbt.vtsuite.servercommon.model.Transaction> transaction = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.Transaction>();
	
	private java.util.Collection<com.smartbt.vtsuite.servercommon.model.MerchantHostParameter> merchantHostParameters = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.MerchantHostParameter>();
	
	private java.util.Collection<com.smartbt.vtsuite.servercommon.model.TerminalHostParameter> terminalHostParameters = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.TerminalHostParameter>();
	
	private java.util.Collection<com.smartbt.vtsuite.servercommon.model.HostMode> hostMode = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.HostMode>();
	
	private java.util.Collection<com.smartbt.vtsuite.servercommon.model.HostParameterValue> hostParameterValue = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.HostParameterValue>();
	
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
	
	/**
	 * The processor name. Eg: "Rbs", "Certegy".
	 */
	public void setName(String value) {
		this.name = value;
	}
	
	/**
	 * The processor name. Eg: "Rbs", "Certegy".
	 */
	public String getName() {
		return name;
	}
	
	public void setTransaction(java.util.Collection<com.smartbt.vtsuite.servercommon.model.Transaction> value) {
		this.transaction = value;
	}
	
	public java.util.Collection<com.smartbt.vtsuite.servercommon.model.Transaction> getTransaction() {
		return transaction;
	}
	
	
	public void setMerchantHostParameters(java.util.Collection<com.smartbt.vtsuite.servercommon.model.MerchantHostParameter> value) {
		this.merchantHostParameters = value;
	}
	
	public java.util.Collection<com.smartbt.vtsuite.servercommon.model.MerchantHostParameter> getMerchantHostParameters() {
		return merchantHostParameters;
	}
	
	
	public void setTerminalHostParameters(java.util.Collection<com.smartbt.vtsuite.servercommon.model.TerminalHostParameter> value) {
		this.terminalHostParameters = value;
	}
	
	public java.util.Collection<com.smartbt.vtsuite.servercommon.model.TerminalHostParameter> getTerminalHostParameters() {
		return terminalHostParameters;
	}
	
	
	public void setHostMode(java.util.Collection<com.smartbt.vtsuite.servercommon.model.HostMode> value) {
		this.hostMode = value;
	}
	
	public java.util.Collection<com.smartbt.vtsuite.servercommon.model.HostMode> getHostMode() {
		return hostMode;
	}
	
	
	public void setHostParameterValue(java.util.Collection<com.smartbt.vtsuite.servercommon.model.HostParameterValue> value) {
		this.hostParameterValue = value;
	}
	
	public java.util.Collection<com.smartbt.vtsuite.servercommon.model.HostParameterValue> getHostParameterValue() {
		return hostParameterValue;
	}
	
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
