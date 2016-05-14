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
 * Stores merchants' receipt information.
 */
@XmlRootElement
public class ReceiptInformation implements Serializable {
	public ReceiptInformation() {
	}
	
	private int idMerchant;
	
	private java.sql.Blob headerImage;
	
	private String headerLine1;
	
	private String headerLine2;
	
	private String headerLine3;
	
	private String headerLine4;
	
	private String headerLine5;
	
	private String footerLine1;
	
	private String footerLine2;
	
	private String footerLine3;
	
	private String footerLine4;
	
	private String footerLine5;
	
	/**
	 * Merchant's reference which the receipt belongs.
	 */
	private void setIdMerchant(int value) {
		this.idMerchant = value;
	}
	
	/**
	 * Merchant's reference which the receipt belongs.
	 */
	public int getIdMerchant() {
		return idMerchant;
	}		
	
	/**
	 * Merchant's logo.
	 */
	public void setHeaderImage(java.sql.Blob value) {
		this.headerImage = value;
	}
	
	/**
	 * Merchant's logo.
	 */
	public java.sql.Blob getHeaderImage() {
		return headerImage;
	}
	
	/**
	 * Merchant's name.
	 */
	public void setHeaderLine1(String value) {
		this.headerLine1 = value;
	}
	
	/**
	 * Merchant's name.
	 */
	public String getHeaderLine1() {
		return headerLine1;
	}
	
	/**
	 * Merchant's address part I.
	 */
	public void setHeaderLine2(String value) {
		this.headerLine2 = value;
	}
	
	/**
	 * Merchant's address part I.
	 */
	public String getHeaderLine2() {
		return headerLine2;
	}
	
	/**
	 * Merchant's address part II.
	 */
	public void setHeaderLine3(String value) {
		this.headerLine3 = value;
	}
	
	/**
	 * Merchant's address part II.
	 */
	public String getHeaderLine3() {
		return headerLine3;
	}
	
	/**
	 * Merchant's office phone.
	 */
	public void setHeaderLine4(String value) {
		this.headerLine4 = value;
	}
	
	/**
	 * Merchant's office phone.
	 */
	public String getHeaderLine4() {
		return headerLine4;
	}
	
	/**
	 * Merchant's office fax
	 */
	public void setHeaderLine5(String value) {
		this.headerLine5 = value;
	}
	
	/**
	 * Merchant's office fax
	 */
	public String getHeaderLine5() {
		return headerLine5;
	}
	
	/**
	 * Receipt Footer Message Information1
	 */
	public void setFooterLine1(String value) {
		this.footerLine1 = value;
	}
	
	/**
	 * Receipt Footer Message Information1
	 */
	public String getFooterLine1() {
		return footerLine1;
	}
	
	/**
	 * Receipt Footer Message Information2.
	 */
	public void setFooterLine2(String value) {
		this.footerLine2 = value;
	}
	
	/**
	 * Receipt Footer Message Information2.
	 */
	public String getFooterLine2() {
		return footerLine2;
	}
	
	/**
	 * Receipt Footer Message Information3.
	 */
	public void setFooterLine3(String value) {
		this.footerLine3 = value;
	}
	
	/**
	 * Receipt Footer Message Information3.
	 */
	public String getFooterLine3() {
		return footerLine3;
	}
	
	/**
	 * Receipt Footer Message Information4.
	 */
	public void setFooterLine4(String value) {
		this.footerLine4 = value;
	}
	
	/**
	 * Receipt Footer Message Information4.
	 */
	public String getFooterLine4() {
		return footerLine4;
	}
	
	/**
	 * Receipt Footer Message Information5.
	 */
	public void setFooterLine5(String value) {
		this.footerLine5 = value;
	}
	
	/**
	 * Receipt Footer Message Information5.
	 */
	public String getFooterLine5() {
		return footerLine5;
	}
	
	public String toString() {
		return String.valueOf(getIdMerchant());
	}
	
}
