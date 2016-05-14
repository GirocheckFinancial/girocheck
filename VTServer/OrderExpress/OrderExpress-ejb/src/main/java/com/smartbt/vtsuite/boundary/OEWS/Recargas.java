
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
 *         &lt;element name="recarga" type="{http://tempuri.org/}Rescarga" minOccurs="0"/>
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
    "recarga"
})
@XmlRootElement(name = "Recargas")
public class Recargas {

    protected Rescarga recarga;

    /**
     * Gets the value of the recarga property.
     * 
     * @return
     *     possible object is
     *     {@link Rescarga }
     *     
     */
    public Rescarga getRecarga() {
        return recarga;
    }

    /**
     * Sets the value of the recarga property.
     * 
     * @param value
     *     allowed object is
     *     {@link Rescarga }
     *     
     */
    public void setRecarga(Rescarga value) {
        this.recarga = value;
    }

}
