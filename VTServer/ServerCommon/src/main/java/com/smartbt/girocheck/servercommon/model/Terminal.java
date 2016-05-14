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
public class Terminal implements Serializable {
	public Terminal() {
	}
	
	private int id;
	
	private com.smartbt.girocheck.servercommon.model.Merchant merchant;
	
	private String serialNumber;
	
	private String description;
	
	private Boolean active;
        
        private String idPOSOrderExp;
	
	private java.util.Date activationDate;
	
	private Integer deactivationDate;
	
	private String girocheckUser;
	
	private String girocheckPassword;
	
	
	private java.util.Set<com.smartbt.girocheck.servercommon.model.TerminalParameterValue> terminal_Parameter_Value = new java.util.HashSet<com.smartbt.girocheck.servercommon.model.TerminalParameterValue>();
	
	private java.util.Set<com.smartbt.girocheck.servercommon.model.Transaction> transaction = new java.util.HashSet<com.smartbt.girocheck.servercommon.model.Transaction>();
	
	public void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return id;
	}
	
	public int getORMID() {
		return getId();
	}
	
	public void setSerialNumber(String value) {
		this.serialNumber = value;
	}
	
	public String getSerialNumber() {
		return serialNumber;
	}
	
	public void setDescription(String value) {
		this.description = value;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setActive(boolean value) {
		setActive(new Boolean(value));
	}
	
	public void setActive(Boolean value) {
		this.active = value;
	}
	
	public Boolean getActive() {
		return active;
	}
	
	public void setActivationDate(java.util.Date value) {
		this.activationDate = value;
	}
	
	public java.util.Date getActivationDate() {
		return activationDate;
	}
	
	public void setDeactivationDate(int value) {
		setDeactivationDate(new Integer(value));
	}
	
	public void setDeactivationDate(Integer value) {
		this.deactivationDate = value;
	}
	
	public Integer getDeactivationDate() {
		return deactivationDate;
	}
	
	
	
	
	public void setGirocheckUser(String value) {
		this.girocheckUser = value;
	}
	
	public String getGirocheckUser() {
		return girocheckUser;
	}
	
	public void setGirocheckPassword(String value) {
		this.girocheckPassword = value;
	}
	
	public String getGirocheckPassword() {
		return girocheckPassword;
	}
	
	
	
	public void setMerchant(com.smartbt.girocheck.servercommon.model.Merchant value) {
		this.merchant = value;
	}
	
	public com.smartbt.girocheck.servercommon.model.Merchant getMerchant() {
		return merchant;
	}
	
	public void setTerminal_Parameter_Value(java.util.Set<com.smartbt.girocheck.servercommon.model.TerminalParameterValue> value) {
		this.terminal_Parameter_Value = value;
	}
	
	public java.util.Set<com.smartbt.girocheck.servercommon.model.TerminalParameterValue> getTerminal_Parameter_Value() {
		return terminal_Parameter_Value;
	}
	
	
	public void setTransaction(java.util.Set<com.smartbt.girocheck.servercommon.model.Transaction> value) {
		this.transaction = value;
	}
	
	public java.util.Set<com.smartbt.girocheck.servercommon.model.Transaction> getTransaction() {
		return transaction;
	}
	
	
	public String toString() {
		return String.valueOf(getId());
	}

    /**
     * @return the idPOSOrderExp
     */
    public String getIdPOSOrderExp() {
        return idPOSOrderExp;
    }

    /**
     * @param idPOSOrderExp the idPOSOrderExp to set
     */
    public void setIdPOSOrderExp(String idPOSOrderExp) {
        this.idPOSOrderExp = idPOSOrderExp;
    }
	
}
