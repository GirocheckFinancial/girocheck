
package com.smartbt.vtsuite.boundary.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 *         &lt;element name="WMLastTransactionsResult" type="{https://SistemasGalileo.com/Services/}LastTransactionsResponse" minOccurs="0"/>
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
    "wmLastTransactionsResult"
})
@XmlRootElement(name = "WMLastTransactionsResponse")
public class WMLastTransactionsResponse {

    @XmlElement(name = "WMLastTransactionsResult")
    protected LastTransactionsResponse wmLastTransactionsResult;

    /**
     * Gets the value of the wmLastTransactionsResult property.
     * 
     * @return
     *     possible object is
     *     {@link LastTransactionsResponse }
     *     
     */
    public LastTransactionsResponse getWMLastTransactionsResult() {
        return wmLastTransactionsResult;
    }

    /**
     * Sets the value of the wmLastTransactionsResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link LastTransactionsResponse }
     *     
     */
    public void setWMLastTransactionsResult(LastTransactionsResponse value) {
        this.wmLastTransactionsResult = value;
    }

}
