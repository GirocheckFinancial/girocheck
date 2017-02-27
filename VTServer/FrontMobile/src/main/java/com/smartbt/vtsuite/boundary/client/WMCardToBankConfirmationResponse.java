
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
    "wmCardToBankConfirmationResult"
})
@XmlRootElement(name = "WMCardToBankConfirmationResponse")
public class WMCardToBankConfirmationResponse {

    @XmlElement(name = "WMCardToBankConfirmationResult")
    protected CardToBankConfirmationResponse wmCardToBankConfirmationResult;

    /**
     * Gets the value of the wmCardToBankResult property.
     * 
     * @return
     *     possible object is
     *     {@link CardToBankResponse }
     *     
     */
    public CardToBankConfirmationResponse getWMCardToBankConfirmationResult() {
        return wmCardToBankConfirmationResult;
    }

    /**
     * Sets the value of the wmCardToBankResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link CardToBankResponse }
     *     
     */
    public void setWMCardToBankConfirmationResult(CardToBankConfirmationResponse value) {
        this.wmCardToBankConfirmationResult = value;
    }

}
