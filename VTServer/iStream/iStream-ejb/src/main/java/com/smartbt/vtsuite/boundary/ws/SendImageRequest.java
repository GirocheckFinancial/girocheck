
package com.smartbt.vtsuite.boundary.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for sendImageRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sendImageRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="back" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="backTiff" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="crc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="entityNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="front" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="frontTiff" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="iqaRawData" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="micr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="scanTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="scannerManufacturer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sendTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="sendUser" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="serial" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="user" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sendImageRequest", propOrder = {
    "back",
    "backTiff",
    "crc",
    "entityNumber",
    "front",
    "frontTiff",
    "iqaRawData",
    "micr",
    "password",
    "scanTime",
    "scannerManufacturer",
    "sendTime",
    "sendUser",
    "serial",
    "user"
})
public class SendImageRequest {

    protected byte[] back;
    protected byte[] backTiff;
    protected String crc;
    protected String entityNumber;
    protected byte[] front;
    protected byte[] frontTiff;
    protected String iqaRawData;
    protected String micr;
    protected String password;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar scanTime;
    protected String scannerManufacturer;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar sendTime;
    protected String sendUser;
    protected String serial;
    protected String user;

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
     * Gets the value of the backTiff property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getBackTiff() {
        return backTiff;
    }

    /**
     * Sets the value of the backTiff property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setBackTiff(byte[] value) {
        this.backTiff = value;
    }

    /**
     * Gets the value of the crc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCrc() {
        return crc;
    }

    /**
     * Sets the value of the crc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCrc(String value) {
        this.crc = value;
    }

    /**
     * Gets the value of the entityNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntityNumber() {
        return entityNumber;
    }

    /**
     * Sets the value of the entityNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntityNumber(String value) {
        this.entityNumber = value;
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

    /**
     * Gets the value of the frontTiff property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getFrontTiff() {
        return frontTiff;
    }

    /**
     * Sets the value of the frontTiff property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setFrontTiff(byte[] value) {
        this.frontTiff = value;
    }

    /**
     * Gets the value of the iqaRawData property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIqaRawData() {
        return iqaRawData;
    }

    /**
     * Sets the value of the iqaRawData property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIqaRawData(String value) {
        this.iqaRawData = value;
    }

    /**
     * Gets the value of the micr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMicr() {
        return micr;
    }

    /**
     * Sets the value of the micr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMicr(String value) {
        this.micr = value;
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
     * Gets the value of the scanTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getScanTime() {
        return scanTime;
    }

    /**
     * Sets the value of the scanTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setScanTime(XMLGregorianCalendar value) {
        this.scanTime = value;
    }

    /**
     * Gets the value of the scannerManufacturer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScannerManufacturer() {
        return scannerManufacturer;
    }

    /**
     * Sets the value of the scannerManufacturer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScannerManufacturer(String value) {
        this.scannerManufacturer = value;
    }

    /**
     * Gets the value of the sendTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSendTime() {
        return sendTime;
    }

    /**
     * Sets the value of the sendTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSendTime(XMLGregorianCalendar value) {
        this.sendTime = value;
    }

    /**
     * Gets the value of the sendUser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSendUser() {
        return sendUser;
    }

    /**
     * Sets the value of the sendUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSendUser(String value) {
        this.sendUser = value;
    }

    /**
     * Gets the value of the serial property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSerial() {
        return serial;
    }

    /**
     * Sets the value of the serial property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSerial(String value) {
        this.serial = value;
    }

    /**
     * Gets the value of the user property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets the value of the user property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUser(String value) {
        this.user = value;
    }

}
