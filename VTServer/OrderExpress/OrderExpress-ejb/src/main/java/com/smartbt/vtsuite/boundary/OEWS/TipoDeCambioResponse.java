
package com.smartbt.vtsuite.boundary.OEWS;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="tipoDeCambioResult" type="{http://tempuri.org/}ArrayOfOficinaResult" minOccurs="0"/>
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
    "tipoDeCambioResult"
})
@XmlRootElement(name = "tipoDeCambioResponse")
public class TipoDeCambioResponse {

    protected ArrayOfOficinaResult tipoDeCambioResult;

    /**
     * Gets the value of the tipoDeCambioResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfOficinaResult }
     *     
     */
    public ArrayOfOficinaResult getTipoDeCambioResult() {
        return tipoDeCambioResult;
    }

    /**
     * Sets the value of the tipoDeCambioResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfOficinaResult }
     *     
     */
    public void setTipoDeCambioResult(ArrayOfOficinaResult value) {
        this.tipoDeCambioResult = value;
    }

}
