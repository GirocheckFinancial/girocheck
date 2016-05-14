
package com.smartbt.vtsuite.boundary.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for deposit complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="deposit">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="batchId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="draftDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numItems" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="settleAmount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userAmount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "deposit", propOrder = {
    "batchId",
    "draftDate",
    "numItems",
    "settleAmount",
    "userAmount"
})
public class Deposit {

    protected String batchId;
    protected String draftDate;
    protected String numItems;
    protected String settleAmount;
    protected String userAmount;

    /**
     * Gets the value of the batchId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBatchId() {
        return batchId;
    }

    /**
     * Sets the value of the batchId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBatchId(String value) {
        this.batchId = value;
    }

    /**
     * Gets the value of the draftDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDraftDate() {
        return draftDate;
    }

    /**
     * Sets the value of the draftDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDraftDate(String value) {
        this.draftDate = value;
    }

    /**
     * Gets the value of the numItems property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumItems() {
        return numItems;
    }

    /**
     * Sets the value of the numItems property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumItems(String value) {
        this.numItems = value;
    }

    /**
     * Gets the value of the settleAmount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSettleAmount() {
        return settleAmount;
    }

    /**
     * Sets the value of the settleAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSettleAmount(String value) {
        this.settleAmount = value;
    }

    /**
     * Gets the value of the userAmount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserAmount() {
        return userAmount;
    }

    /**
     * Sets the value of the userAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserAmount(String value) {
        this.userAmount = value;
    }

}
