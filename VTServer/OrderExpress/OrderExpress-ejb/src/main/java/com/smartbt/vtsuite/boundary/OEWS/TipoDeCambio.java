
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
 *         &lt;element name="ofi" type="{http://tempuri.org/}Oficina" minOccurs="0"/>
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
    "ofi"
})
@XmlRootElement(name = "tipoDeCambio")
public class TipoDeCambio {

    protected Oficina ofi;

    /**
     * Gets the value of the ofi property.
     * 
     * @return
     *     possible object is
     *     {@link Oficina }
     *     
     */
    public Oficina getOfi() {
        return ofi;
    }

    /**
     * Sets the value of the ofi property.
     * 
     * @param value
     *     allowed object is
     *     {@link Oficina }
     *     
     */
    public void setOfi(Oficina value) {
        this.ofi = value;
    }

}
