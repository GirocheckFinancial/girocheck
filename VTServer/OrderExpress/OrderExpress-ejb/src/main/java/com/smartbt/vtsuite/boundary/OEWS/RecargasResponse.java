
package com.smartbt.vtsuite.boundary.OEWS;

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
 *         &lt;element name="RecargasResult" type="{http://tempuri.org/}RescargaResponse" minOccurs="0"/>
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
    "recargasResult"
})
@XmlRootElement(name = "RecargasResponse")
public class RecargasResponse {

    @XmlElement(name = "RecargasResult")
    protected RescargaResponse recargasResult;

    /**
     * Gets the value of the recargasResult property.
     * 
     * @return
     *     possible object is
     *     {@link RescargaResponse }
     *     
     */
    public RescargaResponse getRecargasResult() {
        return recargasResult;
    }

    /**
     * Sets the value of the recargasResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link RescargaResponse }
     *     
     */
    public void setRecargasResult(RescargaResponse value) {
        this.recargasResult = value;
    }

}
