
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
 *         &lt;element name="pStartDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pEndDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pTransactionQuantity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "pStartDate",
    "pEndDate",
    "pTransactionQuantity"
})
@XmlRootElement(name = "WMLastTransactions")
public class WMLastTransactions {

    protected String pRequestID;
    protected String pCardNumber;
    protected String pStartDate;
    protected String pEndDate;
    protected String pTransactionQuantity;

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
     * Gets the value of the pStartDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPStartDate() {
        return pStartDate;
    }

    /**
     * Sets the value of the pStartDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPStartDate(String value) {
        this.pStartDate = value;
    }

    /**
     * Gets the value of the pEndDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPEndDate() {
        return pEndDate;
    }

    /**
     * Sets the value of the pEndDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPEndDate(String value) {
        this.pEndDate = value;
    }

    /**
     * Gets the value of the pTransactionQuantity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPTransactionQuantity() {
        return pTransactionQuantity;
    }

    /**
     * Sets the value of the pTransactionQuantity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPTransactionQuantity(String value) {
        this.pTransactionQuantity = value;
    }

}
