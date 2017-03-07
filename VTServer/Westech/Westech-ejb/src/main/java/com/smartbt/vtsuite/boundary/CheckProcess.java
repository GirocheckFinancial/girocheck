
package com.smartbt.vtsuite.boundary;

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
 *         &lt;element name="username" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="checkFront" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="checkBack" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="idProof" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="idProof2dBarcode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "username",
    "password",
    "checkFront",
    "checkBack",
    "idProof",
    "idProof2DBarcode"
})
@XmlRootElement(name = "CheckProcess")
public class CheckProcess {

    protected String username;
    protected String password;
    protected byte[] checkFront;
    protected byte[] checkBack;
    protected byte[] idProof;
    @XmlElement(name = "idProof2dBarcode")
    protected String idProof2DBarcode;

    /**
     * Gets the value of the username property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the value of the username property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsername(String value) {
        this.username = value;
    }

    /**
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * Gets the value of the checkFront property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getCheckFront() {
        return checkFront;
    }

    /**
     * Sets the value of the checkFront property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setCheckFront(byte[] value) {
        this.checkFront = value;
    }

    /**
     * Gets the value of the checkBack property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getCheckBack() {
        return checkBack;
    }

    /**
     * Sets the value of the checkBack property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setCheckBack(byte[] value) {
        this.checkBack = value;
    }

    /**
     * Gets the value of the idProof property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getIdProof() {
        return idProof;
    }

    /**
     * Sets the value of the idProof property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setIdProof(byte[] value) {
        this.idProof = value;
    }

    /**
     * Gets the value of the idProof2DBarcode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdProof2DBarcode() {
        return idProof2DBarcode;
    }

    /**
     * Sets the value of the idProof2DBarcode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdProof2DBarcode(String value) {
        this.idProof2DBarcode = value;
    }

}
