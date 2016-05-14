
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
 *         &lt;element name="ObtenFactRemBenResult" type="{http://tempuri.org/}ObtenUltimaFacResult" minOccurs="0"/>
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
    "obtenFactRemBenResult"
})
@XmlRootElement(name = "ObtenFactRemBenResponse")
public class ObtenFactRemBenResponse {

    @XmlElement(name = "ObtenFactRemBenResult")
    protected ObtenUltimaFacResult obtenFactRemBenResult;

    /**
     * Gets the value of the obtenFactRemBenResult property.
     * 
     * @return
     *     possible object is
     *     {@link ObtenUltimaFacResult }
     *     
     */
    public ObtenUltimaFacResult getObtenFactRemBenResult() {
        return obtenFactRemBenResult;
    }

    /**
     * Sets the value of the obtenFactRemBenResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ObtenUltimaFacResult }
     *     
     */
    public void setObtenFactRemBenResult(ObtenUltimaFacResult value) {
        this.obtenFactRemBenResult = value;
    }

}
