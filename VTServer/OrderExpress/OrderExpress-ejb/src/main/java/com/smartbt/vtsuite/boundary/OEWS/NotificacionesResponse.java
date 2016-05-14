
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
 *         &lt;element name="NotificacionesResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "notificacionesResult"
})
@XmlRootElement(name = "NotificacionesResponse")
public class NotificacionesResponse {

    @XmlElement(name = "NotificacionesResult")
    protected String notificacionesResult;

    /**
     * Gets the value of the notificacionesResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNotificacionesResult() {
        return notificacionesResult;
    }

    /**
     * Sets the value of the notificacionesResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotificacionesResult(String value) {
        this.notificacionesResult = value;
    }

}
