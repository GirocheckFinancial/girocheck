
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
 *         &lt;element name="WMCardLoadResult" type="{https://SistemasGalileo.com/Services/}CardLoadResponse" minOccurs="0"/>
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
    "wmCardLoadResult"
})
@XmlRootElement(name = "WMCardLoadResponse")
public class WMCardLoadResponse {

    @XmlElement(name = "WMCardLoadResult")
    protected CardLoadResponse wmCardLoadResult;

    /**
     * Gets the value of the wmCardLoadResult property.
     * 
     * @return
     *     possible object is
     *     {@link CardLoadResponse }
     *     
     */
    public CardLoadResponse getWMCardLoadResult() {
        return wmCardLoadResult;
    }

    /**
     * Sets the value of the wmCardLoadResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link CardLoadResponse }
     *     
     */
    public void setWMCardLoadResult(CardLoadResponse value) {
        this.wmCardLoadResult = value;
    }

}
