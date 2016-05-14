
package com.smartbt.vtsuite.boundary.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for enhancedCheckAuthPoll complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="enhancedCheckAuthPoll">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arg0" type="{http://web.service.scanner.tc.com/}enhancedCheckAuthPollRequest" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "enhancedCheckAuthPoll", propOrder = {
    "arg0"
})
public class EnhancedCheckAuthPoll {

    protected EnhancedCheckAuthPollRequest arg0;

    /**
     * Gets the value of the arg0 property.
     * 
     * @return
     *     possible object is
     *     {@link EnhancedCheckAuthPollRequest }
     *     
     */
    public EnhancedCheckAuthPollRequest getArg0() {
        return arg0;
    }

    /**
     * Sets the value of the arg0 property.
     * 
     * @param value
     *     allowed object is
     *     {@link EnhancedCheckAuthPollRequest }
     *     
     */
    public void setArg0(EnhancedCheckAuthPollRequest value) {
        this.arg0 = value;
    }

}
