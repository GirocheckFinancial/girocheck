
package com.smartbt.vtsuite.boundary.ws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for imageRef complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="imageRef">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="crc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="data" type="{http://web.service.scanner.tc.com/}draftAuxData" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="imageRef" type="{http://web.service.scanner.tc.com/}imageRef" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="itemType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="optOut" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "imageRef", propOrder = {
    "amount",
    "crc",
    "data",
    "imageRef",
    "itemType",
    "optOut"
})
public class ImageRef {

    protected String amount;
    protected String crc;
    @XmlElement(nillable = true)
    protected List<DraftAuxData> data;
    @XmlElement(nillable = true)
    protected List<ImageRef> imageRef;
    protected String itemType;
    protected String optOut;

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
     * Gets the value of the data property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the data property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getData().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DraftAuxData }
     * 
     * 
     */
    public List<DraftAuxData> getData() {
        if (data == null) {
            data = new ArrayList<DraftAuxData>();
        }
        return this.data;
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
     * Gets the value of the itemType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItemType() {
        return itemType;
    }

    /**
     * Sets the value of the itemType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItemType(String value) {
        this.itemType = value;
    }

    /**
     * Gets the value of the optOut property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOptOut() {
        return optOut;
    }

    /**
     * Sets the value of the optOut property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOptOut(String value) {
        this.optOut = value;
    }

}
