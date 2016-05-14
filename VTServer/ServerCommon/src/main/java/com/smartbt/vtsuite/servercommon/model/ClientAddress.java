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
public class ClientAddress extends com.smartbt.vtsuite.servercommon.model.Address implements Serializable {
	public ClientAddress() {
	}
	
	private com.smartbt.vtsuite.servercommon.model.Client client;
	
	public void setClient(com.smartbt.vtsuite.servercommon.model.Client value) {
		this.client = value;
	}
	
	public com.smartbt.vtsuite.servercommon.model.Client getClient() {
		return client;
	}
	
	public String toString() {
		return super.toString();
	}
	
}
