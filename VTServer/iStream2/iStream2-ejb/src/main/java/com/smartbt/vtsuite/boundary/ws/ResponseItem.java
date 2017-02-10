
package com.smartbt.vtsuite.boundary.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for responseItem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="responseItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="checkId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="customerItemId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "responseItem", propOrder = {
    "checkId",
    "customerItemId"
})
public class ResponseItem {

    protected long checkId;
    protected String customerItemId;

    /**
     * Gets the value of the checkId property.
     * 
     */
    public long getCheckId() {
        return checkId;
    }

    /**
     * Sets the value of the checkId property.
     * 
     */
    public void setCheckId(long value) {
        this.checkId = value;
    }

    /**
     * Gets the value of the customerItemId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerItemId() {
        return customerItemId;
    }

    /**
     * Sets the value of the customerItemId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerItemId(String value) {
        this.customerItemId = value;
    }

}
