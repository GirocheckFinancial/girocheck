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
public class Address implements Serializable {
	public Address() {
	}
	
	private int id;
	
	private com.smartbt.girocheck.servercommon.model.Country country;
	
	private com.smartbt.girocheck.servercommon.model.State state;
	
	private String address;
	
	private String city;
	
	private String zipcode;
	
	private com.smartbt.girocheck.servercommon.model.Merchant merchant;
	
	private com.smartbt.girocheck.servercommon.model.Client client;
	
	public void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return id;
	}
	
	public int getORMID() {
		return getId();
	}
	
	public void setAddress(String value) {
		this.address = value;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setCity(String value) {
		this.city = value;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setZipcode(String value) {
		this.zipcode = value;
	}
	
	public String getZipcode() {
		return zipcode;
	}
	
	public void setCountry(com.smartbt.girocheck.servercommon.model.Country value) {
		this.country = value;
	}
	
	public com.smartbt.girocheck.servercommon.model.Country getCountry() {
		return country;
	}
	
	public void setState(com.smartbt.girocheck.servercommon.model.State value) {
		this.state = value;
	}
	
	public com.smartbt.girocheck.servercommon.model.State getState() {
		return state;
	}
	
	public void setMerchant(com.smartbt.girocheck.servercommon.model.Merchant value) {
		this.merchant = value;
	}
	
	public com.smartbt.girocheck.servercommon.model.Merchant getMerchant() {
		return merchant;
	}
	
	public void setClient(com.smartbt.girocheck.servercommon.model.Client value) {
		this.client = value;
	}
	
	public com.smartbt.girocheck.servercommon.model.Client getClient() {
		return client;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
        
        public String getFullAddress(){
            if(state!=null){
                return address+", "+city+", "+state.getName()+", "+zipcode;
            }else
                return address+", "+city+", NoState"+", "+zipcode;
        }
	
}
