
package com.smartbt.vtsuite.boundary.client;

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
 *         &lt;element name="pRequestID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pCardNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pIdType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "pRequestID",
    "pCardNumber",
    "pId",
    "pIdType"
})
@XmlRootElement(name = "WMCardHolderValidation")
public class WMCardHolderValidation {

    protected String pRequestID;
    protected String pCardNumber;
    protected String pId;
    protected String pIdType;

    /**
     * Gets the value of the pRequestID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPRequestID() {
        return pRequestID;
    }

    /**
     * Sets the value of the pRequestID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPRequestID(String value) {
        this.pRequestID = value;
    }

    /**
     * Gets the value of the pCardNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPCardNumber() {
        return pCardNumber;
    }

    /**
     * Sets the value of the pCardNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPCardNumber(String value) {
        this.pCardNumber = value;
    }

    /**
     * Gets the value of the pId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPId() {
        return pId;
    }

    /**
     * Sets the value of the pId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPId(String value) {
        this.pId = value;
    }

    /**
     * Gets the value of the pIdType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPIdType() {
        return pIdType;
    }

    /**
     * Sets the value of the pIdType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPIdType(String value) {
        this.pIdType = value;
    }

}
