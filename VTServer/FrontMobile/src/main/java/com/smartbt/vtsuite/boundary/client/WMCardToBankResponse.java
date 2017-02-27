
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
 *         &lt;element name="WMCardToBankResult" type="{https://SistemasGalileo.com/Services/}CardToBankResponse" minOccurs="0"/>
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
    "wmCardToBankResult"
})
@XmlRootElement(name = "WMCardToBankResponse")
public class WMCardToBankResponse {

    @XmlElement(name = "WMCardToBankResult")
    protected CardToBankResponse wmCardToBankResult;

    /**
     * Gets the value of the wmCardToBankResult property.
     * 
     * @return
     *     possible object is
     *     {@link CardToBankResponse }
     *     
     */
    public CardToBankResponse getWMCardToBankResult() {
        return wmCardToBankResult;
    }

    /**
     * Sets the value of the wmCardToBankResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link CardToBankResponse }
     *     
     */
    public void setWMCardToBankResult(CardToBankResponse value) {
        this.wmCardToBankResult = value;
    }

}
