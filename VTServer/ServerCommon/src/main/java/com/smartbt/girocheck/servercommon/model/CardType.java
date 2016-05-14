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
public class CardType implements Serializable {
	public CardType() {
	}
	
	private int id;
	
	private String name;
	
	private java.util.Set<com.smartbt.girocheck.servercommon.model.CreditCard> data_SC = new java.util.HashSet<com.smartbt.girocheck.servercommon.model.CreditCard>();
	
	private void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return id;
	}
	
	public int getORMID() {
		return getId();
	}
	
	public void setName(String value) {
		this.name = value;
	}
	
	public String getName() {
		return name;
	}
	
	public void setData_SC(java.util.Set<com.smartbt.girocheck.servercommon.model.CreditCard> value) {
		this.data_SC = value;
	}
	
	public java.util.Set<com.smartbt.girocheck.servercommon.model.CreditCard> getData_SC() {
		return data_SC;
	}
	
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
