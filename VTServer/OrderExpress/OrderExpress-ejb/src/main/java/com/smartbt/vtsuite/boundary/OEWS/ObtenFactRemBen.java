
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
 *         &lt;element name="ultimaFact" type="{http://tempuri.org/}ObtenUltimaFac" minOccurs="0"/>
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
    "ultimaFact"
})
@XmlRootElement(name = "ObtenFactRemBen")
public class ObtenFactRemBen {

    protected ObtenUltimaFac ultimaFact;

    /**
     * Gets the value of the ultimaFact property.
     * 
     * @return
     *     possible object is
     *     {@link ObtenUltimaFac }
     *     
     */
    public ObtenUltimaFac getUltimaFact() {
        return ultimaFact;
    }

    /**
     * Sets the value of the ultimaFact property.
     * 
     * @param value
     *     allowed object is
     *     {@link ObtenUltimaFac }
     *     
     */
    public void setUltimaFact(ObtenUltimaFac value) {
        this.ultimaFact = value;
    }

}
