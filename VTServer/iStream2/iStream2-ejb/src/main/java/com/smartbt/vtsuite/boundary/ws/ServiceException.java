
package com.smartbt.vtsuite.boundary.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ServiceException complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceException">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="clientErrors" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="errors" type="{http://web.service.fileloader.tc.com/}actionMessages" minOccurs="0"/>
 *         &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceException", propOrder = {
    "clientErrors",
    "errors",
    "message"
})
public class ServiceException {

    protected String clientErrors;
    protected ActionMessages errors;
    protected String message;

    /**
     * Gets the value of the clientErrors property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClientErrors() {
        return clientErrors;
    }

    /**
     * Sets the value of the clientErrors property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClientErrors(String value) {
        this.clientErrors = value;
    }

    /**
     * Gets the value of the errors property.
     * 
     * @return
     *     possible object is
     *     {@link ActionMessages }
     *     
     */
    public ActionMessages getErrors() {
        return errors;
    }

    /**
     * Sets the value of the errors property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActionMessages }
     *     
     */
    public void setErrors(ActionMessages value) {
        this.errors = value;
    }

    /**
     * Gets the value of the message property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the value of the message property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessage(String value) {
        this.message = value;
    }

}
