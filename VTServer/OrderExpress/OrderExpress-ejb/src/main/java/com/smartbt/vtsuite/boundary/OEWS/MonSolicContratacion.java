
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
 *         &lt;element name="soli" type="{http://tempuri.org/}solicitudCont" minOccurs="0"/>
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
    "soli"
})
@XmlRootElement(name = "Mon_SolicContratacion")
public class MonSolicContratacion {

    protected SolicitudCont soli;

    /**
     * Gets the value of the soli property.
     * 
     * @return
     *     possible object is
     *     {@link SolicitudCont }
     *     
     */
    public SolicitudCont getSoli() {
        return soli;
    }

    /**
     * Sets the value of the soli property.
     * 
     * @param value
     *     allowed object is
     *     {@link SolicitudCont }
     *     
     */
    public void setSoli(SolicitudCont value) {
        this.soli = value;
    }

}
