
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
 *         &lt;element name="WMCardPersonalizationResult" type="{https://SistemasGalileo.com/Services/}CardCreationResponse" minOccurs="0"/>
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
    "wmCardPersonalizationResult"
})
@XmlRootElement(name = "WMCardPersonalizationResponse")
public class WMCardPersonalizationResponse {

    @XmlElement(name = "WMCardPersonalizationResult")
    protected CardCreationResponse wmCardPersonalizationResult;

    /**
     * Gets the value of the wmCardPersonalizationResult property.
     * 
     * @return
     *     possible object is
     *     {@link CardCreationResponse }
     *     
     */
    public CardCreationResponse getWMCardPersonalizationResult() {
        return wmCardPersonalizationResult;
    }

    /**
     * Sets the value of the wmCardPersonalizationResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link CardCreationResponse }
     *     
     */
    public void setWMCardPersonalizationResult(CardCreationResponse value) {
        this.wmCardPersonalizationResult = value;
    }

}
