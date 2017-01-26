
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
 *         &lt;element name="WMEchoResult" type="{https://SistemasGalileo.com/Services/}EchoResponse" minOccurs="0"/>
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
    "wmEchoResult"
})
@XmlRootElement(name = "WMEchoResponse")
public class WMEchoResponse {

    @XmlElement(name = "WMEchoResult")
    protected EchoResponse wmEchoResult;

    /**
     * Gets the value of the wmEchoResult property.
     * 
     * @return
     *     possible object is
     *     {@link EchoResponse }
     *     
     */
    public EchoResponse getWMEchoResult() {
        return wmEchoResult;
    }

    /**
     * Sets the value of the wmEchoResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link EchoResponse }
     *     
     */
    public void setWMEchoResult(EchoResponse value) {
        this.wmEchoResult = value;
    }

}
