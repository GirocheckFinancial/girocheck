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
 * Stores client's card information.
 */
@XmlRootElement
public class Card extends com.smartbt.vtsuite.servercommon.model.Account implements Serializable {
	public Card() {
	}
	
	private String cardHolderName;
	
	private String expDate = "";
	
	private String billingZipCode;
	
	private java.util.Collection<com.smartbt.vtsuite.servercommon.model.CardAddress> cardAddress = new java.util.ArrayList<com.smartbt.vtsuite.servercommon.model.CardAddress>();
	
	/**
	 * The name that appears in the card.
	 */
	public void setCardHolderName(String value) {
		this.cardHolderName = value;
	}
	
	/**
	 * The name that appears in the card.
	 */
	public String getCardHolderName() {
		return cardHolderName;
	}
	
	/**
	 * Card's expiration date.
	 */
	public void setExpDate(String value) {
		this.expDate = value;
	}
	
	/**
	 * Card's expiration date.
	 */
	public String getExpDate() {
		return expDate;
	}
	
	public void setBillingZipCode(String value) {
		this.billingZipCode = value;
	}
	
	public String getBillingZipCode() {
		return billingZipCode;
	}
	
	public void setCardAddress(java.util.Collection<com.smartbt.vtsuite.servercommon.model.CardAddress> value) {
		this.cardAddress = value;
	}
	
	public java.util.Collection<com.smartbt.vtsuite.servercommon.model.CardAddress> getCardAddress() {
		return cardAddress;
	}
	
	
	public String toString() {
		return super.toString();
	}
	
}
