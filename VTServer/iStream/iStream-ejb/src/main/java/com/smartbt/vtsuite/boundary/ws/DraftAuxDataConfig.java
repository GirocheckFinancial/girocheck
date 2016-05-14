
package com.smartbt.vtsuite.boundary.ws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for draftAuxDataConfig complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="draftAuxDataConfig">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="columnOptions" type="{http://web.service.scanner.tc.com/}draftAuxDataColumnOption" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="autoCopy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="columnId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="dataType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="maxLength" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="minLength" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="required" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sortOrder" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="valueMask" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "draftAuxDataConfig", propOrder = {
    "columnOptions",
    "autoCopy",
    "columnId",
    "dataType",
    "maxLength",
    "minLength",
    "name",
    "required",
    "sortOrder",
    "valueMask"
})
public class DraftAuxDataConfig {

    @XmlElement(nillable = true)
    protected List<DraftAuxDataColumnOption> columnOptions;
    protected String autoCopy;
    protected int columnId;
    protected String dataType;
    protected int maxLength;
    protected int minLength;
    protected String name;
    protected String required;
    protected int sortOrder;
    protected String valueMask;

    /**
     * Gets the value of the columnOptions property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the columnOptions property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getColumnOptions().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DraftAuxDataColumnOption }
     * 
     * 
     */
    public List<DraftAuxDataColumnOption> getColumnOptions() {
        if (columnOptions == null) {
            columnOptions = new ArrayList<DraftAuxDataColumnOption>();
        }
        return this.columnOptions;
    }

    /**
     * Gets the value of the autoCopy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAutoCopy() {
        return autoCopy;
    }

    /**
     * Sets the value of the autoCopy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAutoCopy(String value) {
        this.autoCopy = value;
    }

    /**
     * Gets the value of the columnId property.
     * 
     */
    public int getColumnId() {
        return columnId;
    }

    /**
     * Sets the value of the columnId property.
     * 
     */
    public void setColumnId(int value) {
        this.columnId = value;
    }

    /**
     * Gets the value of the dataType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataType() {
        return dataType;
    }

    /**
     * Sets the value of the dataType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataType(String value) {
        this.dataType = value;
    }

    /**
     * Gets the value of the maxLength property.
     * 
     */
    public int getMaxLength() {
        return maxLength;
    }

    /**
     * Sets the value of the maxLength property.
     * 
     */
    public void setMaxLength(int value) {
        this.maxLength = value;
    }

    /**
     * Gets the value of the minLength property.
     * 
     */
    public int getMinLength() {
        return minLength;
    }

    /**
     * Sets the value of the minLength property.
     * 
     */
    public void setMinLength(int value) {
        this.minLength = value;
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
     * Gets the value of the required property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequired() {
        return required;
    }

    /**
     * Sets the value of the required property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequired(String value) {
        this.required = value;
    }

    /**
     * Gets the value of the sortOrder property.
     * 
     */
    public int getSortOrder() {
        return sortOrder;
    }

    /**
     * Sets the value of the sortOrder property.
     * 
     */
    public void setSortOrder(int value) {
        this.sortOrder = value;
    }

    /**
     * Gets the value of the valueMask property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValueMask() {
        return valueMask;
    }

    /**
     * Sets the value of the valueMask property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValueMask(String value) {
        this.valueMask = value;
    }

}
