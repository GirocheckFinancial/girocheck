
package com.smartbt.vtsuite.boundary.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for image complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="image">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="imgBackBinary" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="imgFrontBinary" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "image", propOrder = {
    "imgBackBinary",
    "imgFrontBinary"
})
public class Image {

    protected byte[] imgBackBinary;
    protected byte[] imgFrontBinary;

    /**
     * Gets the value of the imgBackBinary property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getImgBackBinary() {
        return imgBackBinary;
    }

    /**
     * Sets the value of the imgBackBinary property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setImgBackBinary(byte[] value) {
        this.imgBackBinary = value;
    }

    /**
     * Gets the value of the imgFrontBinary property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getImgFrontBinary() {
        return imgFrontBinary;
    }

    /**
     * Sets the value of the imgFrontBinary property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setImgFrontBinary(byte[] value) {
        this.imgFrontBinary = value;
    }

}
