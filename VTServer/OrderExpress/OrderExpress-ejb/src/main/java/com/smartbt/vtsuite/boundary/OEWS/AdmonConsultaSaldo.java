
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
 *         &lt;element name="saldo" type="{http://tempuri.org/}ConsultaSaldo" minOccurs="0"/>
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
    "saldo"
})
@XmlRootElement(name = "AdmonConsultaSaldo")
public class AdmonConsultaSaldo {

    protected ConsultaSaldo saldo;

    /**
     * Gets the value of the saldo property.
     * 
     * @return
     *     possible object is
     *     {@link ConsultaSaldo }
     *     
     */
    public ConsultaSaldo getSaldo() {
        return saldo;
    }

    /**
     * Sets the value of the saldo property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConsultaSaldo }
     *     
     */
    public void setSaldo(ConsultaSaldo value) {
        this.saldo = value;
    }

}
