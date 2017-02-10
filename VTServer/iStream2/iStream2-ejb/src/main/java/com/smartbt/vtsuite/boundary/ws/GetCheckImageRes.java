
package com.smartbt.vtsuite.boundary.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getCheckImageRes complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getCheckImageRes">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="back" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="checkId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="front" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getCheckImageRes", propOrder = {
    "back",
    "checkId",
    "front"
})
public class GetCheckImageRes {

    protected byte[] back;
    protected long checkId;
    protected byte[] front;

    /**
     * Gets the value of the back property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getBack() {
        return back;
    }

    /**
     * Sets the value of the back property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setBack(byte[] value) {
        this.back = value;
    }

    /**
     * Gets the value of the checkId property.
     * 
     */
    public long getCheckId() {
        return checkId;
    }

    /**
     * Sets the value of the checkId property.
     * 
     */
    public void setCheckId(long value) {
        this.checkId = value;
    }

    /**
     * Gets the value of the front property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getFront() {
        return front;
    }

    /**
     * Sets the value of the front property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setFront(byte[] value) {
        this.front = value;
    }

}
