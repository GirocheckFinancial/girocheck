
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
 *         &lt;element name="WMCardValidationResult" type="{https://SistemasGalileo.com/Services/}CardValidationResponse" minOccurs="0"/>
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
    "wmCardValidationResult"
})
@XmlRootElement(name = "WMCardValidationResponse")
public class WMCardValidationResponse {

    @XmlElement(name = "WMCardValidationResult")
    protected CardValidationResponse wmCardValidationResult;

    /**
     * Gets the value of the wmCardValidationResult property.
     * 
     * @return
     *     possible object is
     *     {@link CardValidationResponse }
     *     
     */
    public CardValidationResponse getWMCardValidationResult() {
        return wmCardValidationResult;
    }

    /**
     * Sets the value of the wmCardValidationResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link CardValidationResponse }
     *     
     */
    public void setWMCardValidationResult(CardValidationResponse value) {
        this.wmCardValidationResult = value;
    }

}
