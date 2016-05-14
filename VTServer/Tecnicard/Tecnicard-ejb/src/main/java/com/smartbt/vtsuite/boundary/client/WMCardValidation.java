
package com.smartbt.vtsuite.boundary.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="pRequestID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pCardNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pAmount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "pRequestID",
    "pCardNumber",
    "pAmount"
})
@XmlRootElement(name = "WMCardValidation")
public class WMCardValidation {

    protected String pRequestID;
    protected String pCardNumber;
    protected String pAmount;

    /**
     * Gets the value of the pRequestID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPRequestID() {
        return pRequestID;
    }

    /**
     * Sets the value of the pRequestID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPRequestID(String value) {
        this.pRequestID = value;
    }

    /**
     * Gets the value of the pCardNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPCardNumber() {
        return pCardNumber;
    }

    /**
     * Sets the value of the pCardNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPCardNumber(String value) {
        this.pCardNumber = value;
    }

    /**
     * Gets the value of the pAmount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPAmount() {
        return pAmount;
    }

    /**
     * Sets the value of the pAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPAmount(String value) {
        this.pAmount = value;
    }

}
