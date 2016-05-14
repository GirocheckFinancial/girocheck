
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
 *         &lt;element name="AdmonConsultaSaldoResult" type="{http://tempuri.org/}ArrayOfConsultaSaldoResult" minOccurs="0"/>
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
    "admonConsultaSaldoResult"
})
@XmlRootElement(name = "AdmonConsultaSaldoResponse")
public class AdmonConsultaSaldoResponse {

    @XmlElement(name = "AdmonConsultaSaldoResult")
    protected ArrayOfConsultaSaldoResult admonConsultaSaldoResult;

    /**
     * Gets the value of the admonConsultaSaldoResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfConsultaSaldoResult }
     *     
     */
    public ArrayOfConsultaSaldoResult getAdmonConsultaSaldoResult() {
        return admonConsultaSaldoResult;
    }

    /**
     * Sets the value of the admonConsultaSaldoResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfConsultaSaldoResult }
     *     
     */
    public void setAdmonConsultaSaldoResult(ArrayOfConsultaSaldoResult value) {
        this.admonConsultaSaldoResult = value;
    }

}
