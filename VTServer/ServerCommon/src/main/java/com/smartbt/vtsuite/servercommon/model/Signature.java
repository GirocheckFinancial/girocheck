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
 * Stores transactions' signature.
 */
@XmlRootElement
public class Signature implements Serializable {
	public Signature() {
	}
	
	private int id;
	
	private java.sql.Blob signature;
	
	public void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return id;
	}
	
	/**
	 * The client signature.
	 */
	public void setSignature(java.sql.Blob value) {
		this.signature = value;
	}
	
	/**
	 * The client signature.
	 */
	public java.sql.Blob getSignature() {
		return signature;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
