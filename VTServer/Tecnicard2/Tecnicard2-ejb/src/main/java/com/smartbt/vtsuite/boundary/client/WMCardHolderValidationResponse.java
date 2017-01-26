
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
 *         &lt;element name="WMCardHolderValidationResult" type="{https://SistemasGalileo.com/Services/}CardHolderValidationResponse" minOccurs="0"/>
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
    "wmCardHolderValidationResult"
})
@XmlRootElement(name = "WMCardHolderValidationResponse")
public class WMCardHolderValidationResponse {

    @XmlElement(name = "WMCardHolderValidationResult")
    protected CardHolderValidationResponse wmCardHolderValidationResult;

    /**
     * Gets the value of the wmCardHolderValidationResult property.
     * 
     * @return
     *     possible object is
     *     {@link CardHolderValidationResponse }
     *     
     */
    public CardHolderValidationResponse getWMCardHolderValidationResult() {
        return wmCardHolderValidationResult;
    }

    /**
     * Sets the value of the wmCardHolderValidationResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link CardHolderValidationResponse }
     *     
     */
    public void setWMCardHolderValidationResult(CardHolderValidationResponse value) {
        this.wmCardHolderValidationResult = value;
    }

}
