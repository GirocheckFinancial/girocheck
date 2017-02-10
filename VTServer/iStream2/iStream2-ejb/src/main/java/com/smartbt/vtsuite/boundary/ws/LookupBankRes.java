
package com.smartbt.vtsuite.boundary.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for lookupBankRes complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="lookupBankRes">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="aba" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="address" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="customerName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mainRoutingNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="recieverCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="recordTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "lookupBankRes", propOrder = {
    "aba",
    "address",
    "customerName",
    "mainRoutingNumber",
    "recieverCode",
    "recordTypeCode"
})
public class LookupBankRes {

    protected String aba;
    protected String address;
    protected String customerName;
    protected String mainRoutingNumber;
    protected String recieverCode;
    protected String recordTypeCode;

    /**
     * Gets the value of the aba property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAba() {
        return aba;
    }

    /**
     * Sets the value of the aba property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAba(String value) {
        this.aba = value;
    }

    /**
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddress(String value) {
        this.address = value;
    }

    /**
     * Gets the value of the customerName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Sets the value of the customerName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerName(String value) {
        this.customerName = value;
    }

    /**
     * Gets the value of the mainRoutingNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMainRoutingNumber() {
        return mainRoutingNumber;
    }

    /**
     * Sets the value of the mainRoutingNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMainRoutingNumber(String value) {
        this.mainRoutingNumber = value;
    }

    /**
     * Gets the value of the recieverCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecieverCode() {
        return recieverCode;
    }

    /**
     * Sets the value of the recieverCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecieverCode(String value) {
        this.recieverCode = value;
    }

    /**
     * Gets the value of the recordTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecordTypeCode() {
        return recordTypeCode;
    }

    /**
     * Sets the value of the recordTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecordTypeCode(String value) {
        this.recordTypeCode = value;
    }

}
