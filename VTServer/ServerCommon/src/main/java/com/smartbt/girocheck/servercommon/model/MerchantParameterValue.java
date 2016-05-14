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
public class MerchantParameterValue implements Serializable {
	public MerchantParameterValue() {
	}
	
	private int id;
	
	private com.smartbt.girocheck.servercommon.model.MerchantParameter parameter;
	
	private com.smartbt.girocheck.servercommon.model.Merchant merchant;
	
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
	
	public void setParameter(com.smartbt.girocheck.servercommon.model.MerchantParameter value) {
		this.parameter = value;
	}
	
	public com.smartbt.girocheck.servercommon.model.MerchantParameter getParameter() {
		return parameter;
	}
	
	public void setMerchant(com.smartbt.girocheck.servercommon.model.Merchant value) {
		this.merchant = value;
	}
	
	public com.smartbt.girocheck.servercommon.model.Merchant getMerchant() {
		return merchant;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
