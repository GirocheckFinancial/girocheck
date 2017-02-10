
package com.smartbt.vtsuite.boundary.ws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sendSingleICL complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sendSingleICL">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="username" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="locationId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="depositName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="micr" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="customerItemId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tiffImage" type="{http://web.service.fileloader.tc.com/}image"/>
 *         &lt;element name="highQualityImage" type="{http://web.service.fileloader.tc.com/}image" minOccurs="0"/>
 *         &lt;element name="auxFields" type="{http://web.service.fileloader.tc.com/}auxField" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sendSingleICL", propOrder = {
    "username",
    "password",
    "locationId",
    "amount",
    "depositName",
    "micr",
    "customerItemId",
    "tiffImage",
    "highQualityImage",
    "auxFields"
})
public class SendSingleICL {

    @XmlElement(required = true)
    protected String username;
    @XmlElement(required = true)
    protected String password;
    protected int locationId;
    @XmlElement(required = true)
    protected String amount;
    @XmlElementRef(name = "depositName", type = JAXBElement.class, required = false)
    protected JAXBElement<String> depositName;
    @XmlElement(required = true)
    protected String micr;
    protected String customerItemId;
    @XmlElement(required = true)
    protected Image tiffImage;
    @XmlElementRef(name = "highQualityImage", type = JAXBElement.class, required = false)
    protected JAXBElement<Image> highQualityImage;
    @XmlElement(nillable = true)
    protected List<AuxField> auxFields;

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
     * Gets the value of the locationId property.
     * 
     */
    public int getLocationId() {
        return locationId;
    }

    /**
     * Sets the value of the locationId property.
     * 
     */
    public void setLocationId(int value) {
        this.locationId = value;
    }

    /**
     * Gets the value of the amount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAmount(String value) {
        this.amount = value;
    }

    /**
     * Gets the value of the depositName property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDepositName() {
        return depositName;
    }

    /**
     * Sets the value of the depositName property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDepositName(JAXBElement<String> value) {
        this.depositName = value;
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
     * Gets the value of the customerItemId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerItemId() {
        return customerItemId;
    }

    /**
     * Sets the value of the customerItemId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerItemId(String value) {
        this.customerItemId = value;
    }

    /**
     * Gets the value of the tiffImage property.
     * 
     * @return
     *     possible object is
     *     {@link Image }
     *     
     */
    public Image getTiffImage() {
        return tiffImage;
    }

    /**
     * Sets the value of the tiffImage property.
     * 
     * @param value
     *     allowed object is
     *     {@link Image }
     *     
     */
    public void setTiffImage(Image value) {
        this.tiffImage = value;
    }

    /**
     * Gets the value of the highQualityImage property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Image }{@code >}
     *     
     */
    public JAXBElement<Image> getHighQualityImage() {
        return highQualityImage;
    }

    /**
     * Sets the value of the highQualityImage property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Image }{@code >}
     *     
     */
    public void setHighQualityImage(JAXBElement<Image> value) {
        this.highQualityImage = value;
    }

    /**
     * Gets the value of the auxFields property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the auxFields property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAuxFields().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AuxField }
     * 
     * 
     */
    public List<AuxField> getAuxFields() {
        if (auxFields == null) {
            auxFields = new ArrayList<AuxField>();
        }
        return this.auxFields;
    }

}
