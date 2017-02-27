
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
 *         &lt;element name="WMCashToCardResult" type="{https://SistemasGalileo.com/Services/}CashToCardResponse" minOccurs="0"/>
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
    "wmCashToCardResult"
})
@XmlRootElement(name = "WMCashToCardResponse")
public class WMCashToCardResponse {

    @XmlElement(name = "WMCashToCardResult")
    protected CashToCardResponse wmCashToCardResult;

    /**
     * Gets the value of the wmCashToCardResult property.
     * 
     * @return
     *     possible object is
     *     {@link CashToCardResponse }
     *     
     */
    public CashToCardResponse getWMCashToCardResult() {
        return wmCashToCardResult;
    }

    /**
     * Sets the value of the wmCashToCardResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link CashToCardResponse }
     *     
     */
    public void setWMCashToCardResult(CashToCardResponse value) {
        this.wmCashToCardResult = value;
    }

}
