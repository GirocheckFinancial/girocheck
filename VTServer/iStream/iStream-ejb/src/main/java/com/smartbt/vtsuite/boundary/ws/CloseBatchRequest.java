
package com.smartbt.vtsuite.boundary.ws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for closeBatchRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="closeBatchRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="closeType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dollarValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="entityNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="imageRef" type="{http://web.service.scanner.tc.com/}imageRef" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numberItems" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="scanUser" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="scannerManufactuer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sendUser" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="serial" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "closeBatchRequest", propOrder = {
    "closeType",
    "dollarValue",
    "entityNumber",
    "imageRef",
    "name",
    "numberItems",
    "password",
    "scanUser",
    "scannerManufactuer",
    "sendUser",
    "serial"
})
public class CloseBatchRequest {

    protected String closeType;
    protected String dollarValue;
    protected String entityNumber;
    @XmlElement(nillable = true)
    protected List<ImageRef> imageRef;
    protected String name;
    protected int numberItems;
    protected String password;
    protected String scanUser;
    protected String scannerManufactuer;
    protected String sendUser;
    protected String serial;

    /**
     * Gets the value of the closeType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCloseType() {
        return closeType;
    }

    /**
     * Sets the value of the closeType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCloseType(String value) {
        this.closeType = value;
    }

    /**
     * Gets the value of the dollarValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDollarValue() {
        return dollarValue;
    }

    /**
     * Sets the value of the dollarValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDollarValue(String value) {
        this.dollarValue = value;
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
     * Gets the value of the imageRef property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the imageRef property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getImageRef().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ImageRef }
     * 
     * 
     */
    public List<ImageRef> getImageRef() {
        if (imageRef == null) {
            imageRef = new ArrayList<ImageRef>();
        }
        return this.imageRef;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the numberItems property.
     * 
     */
    public int getNumberItems() {
        return numberItems;
    }

    /**
     * Sets the value of the numberItems property.
     * 
     */
    public void setNumberItems(int value) {
        this.numberItems = value;
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
     * Gets the value of the scanUser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScanUser() {
        return scanUser;
    }

    /**
     * Sets the value of the scanUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScanUser(String value) {
        this.scanUser = value;
    }

    /**
     * Gets the value of the scannerManufactuer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScannerManufactuer() {
        return scannerManufactuer;
    }

    /**
     * Sets the value of the scannerManufactuer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScannerManufactuer(String value) {
        this.scannerManufactuer = value;
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

}
