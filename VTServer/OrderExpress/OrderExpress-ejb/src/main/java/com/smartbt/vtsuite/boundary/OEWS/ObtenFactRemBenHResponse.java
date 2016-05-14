
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
 *         &lt;element name="ObtenFactRemBenHResult" type="{http://tempuri.org/}ArrayOfObtenUltimaFacHResult" minOccurs="0"/>
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
    "obtenFactRemBenHResult"
})
@XmlRootElement(name = "ObtenFactRemBenHResponse")
public class ObtenFactRemBenHResponse {

    @XmlElement(name = "ObtenFactRemBenHResult")
    protected ArrayOfObtenUltimaFacHResult obtenFactRemBenHResult;

    /**
     * Gets the value of the obtenFactRemBenHResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfObtenUltimaFacHResult }
     *     
     */
    public ArrayOfObtenUltimaFacHResult getObtenFactRemBenHResult() {
        return obtenFactRemBenHResult;
    }

    /**
     * Sets the value of the obtenFactRemBenHResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfObtenUltimaFacHResult }
     *     
     */
    public void setObtenFactRemBenHResult(ArrayOfObtenUltimaFacHResult value) {
        this.obtenFactRemBenHResult = value;
    }

}
