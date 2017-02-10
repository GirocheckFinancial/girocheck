
package com.smartbt.vtsuite.boundary.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for auxField complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="auxField">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="auxFieldName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="auxFieldValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "auxField", propOrder = {
    "auxFieldName",
    "auxFieldValue"
})
public class AuxField {

    protected String auxFieldName;
    protected String auxFieldValue;

    /**
     * Gets the value of the auxFieldName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuxFieldName() {
        return auxFieldName;
    }

    /**
     * Sets the value of the auxFieldName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuxFieldName(String value) {
        this.auxFieldName = value;
    }

    /**
     * Gets the value of the auxFieldValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuxFieldValue() {
        return auxFieldValue;
    }

    /**
     * Sets the value of the auxFieldValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuxFieldValue(String value) {
        this.auxFieldValue = value;
    }

}
