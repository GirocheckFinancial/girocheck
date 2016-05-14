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

import java.io.Serializable;
/**
 * Stores address. Eg: client's address, merchant's address, card's address.
 */
@javax.xml.bind.annotation.XmlRootElement
public class Address implements Serializable {
	public Address() {
	}
	
	private int id;
	
	private com.smartbt.vtsuite.servercommon.model.AddressType addressType;
	
	private com.smartbt.vtsuite.servercommon.model.State state;
	
	private com.smartbt.vtsuite.servercommon.model.Country country;
	
	private String address1;
	
	private String address2;
	
	private String city;
	
	private String zip;
	
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
	
	public int getORMID() {
		return getId();
	}
	
	/**
	 * Address first part.
	 */
	public void setAddress1(String value) {
		this.address1 = value;
	}
	
	/**
	 * Address first part.
	 */
	public String getAddress1() {
		return address1;
	}
	
	/**
	 * Address second part.
	 */
	public void setAddress2(String value) {
		this.address2 = value;
	}
	
	/**
	 * Address second part.
	 */
	public String getAddress2() {
		return address2;
	}
	
	/**
	 * City
	 */
	public void setCity(String value) {
		this.city = value;
	}
	
	/**
	 * City
	 */
	public String getCity() {
		return city;
	}
	
	/**
	 * Zip code.
	 */
	public void setZip(String value) {
		this.zip = value;
	}
	
	/**
	 * Zip code.
	 */
	public String getZip() {
		return zip;
	}
	
	public void setCountry(com.smartbt.vtsuite.servercommon.model.Country value) {
		this.country = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.Country getCountry() {
		return country;
	}
	
	public void setAddressType(com.smartbt.vtsuite.servercommon.model.AddressType value) {
		this.addressType = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.AddressType getAddressType() {
		return addressType;
	}
	
	public void setState(com.smartbt.vtsuite.servercommon.model.State value) {
		this.state = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.State getState() {
		return state;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
