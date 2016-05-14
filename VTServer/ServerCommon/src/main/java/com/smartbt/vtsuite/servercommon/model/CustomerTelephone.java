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
public class CustomerTelephone extends com.smartbt.vtsuite.servercommon.model.Telephone implements Serializable {
	public CustomerTelephone() {
	}
	
	private com.smartbt.vtsuite.servercommon.model.Customer customer;
	
	public void setCustomer(com.smartbt.vtsuite.servercommon.model.Customer value) {
		this.customer = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.Customer getCustomer() {
		return customer;
	}
	
	public String toString() {
		return super.toString();
	}
	
}
