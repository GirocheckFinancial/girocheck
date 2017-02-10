
package com.smartbt.vtsuite.boundary.ws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for depositItem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="depositItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="auxFields" type="{http://web.service.fileloader.tc.com/}auxField" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="customerItemId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="highQualityImage" type="{http://web.service.fileloader.tc.com/}image" minOccurs="0"/>
 *         &lt;element name="micr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tiffImage" type="{http://web.service.fileloader.tc.com/}image" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "depositItem", propOrder = {
    "amount",
    "auxFields",
    "customerItemId",
    "highQualityImage",
    "micr",
    "tiffImage"
})
public class DepositItem {

    protected String amount;
    @XmlElement(nillable = true)
    protected List<AuxField> auxFields;
    protected String customerItemId;
    protected Image highQualityImage;
    protected String micr;
    protected Image tiffImage;

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
     * Gets the value of the highQualityImage property.
     * 
     * @return
     *     possible object is
     *     {@link Image }
     *     
     */
    public Image getHighQualityImage() {
        return highQualityImage;
    }

    /**
     * Sets the value of the highQualityImage property.
     * 
     * @param value
     *     allowed object is
     *     {@link Image }
     *     
     */
    public void setHighQualityImage(Image value) {
        this.highQualityImage = value;
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

}
