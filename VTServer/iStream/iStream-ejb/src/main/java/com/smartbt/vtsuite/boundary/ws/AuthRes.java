
package com.smartbt.vtsuite.boundary.ws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AuthRes complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AuthRes">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="entity" type="{http://web.service.scanner.tc.com/}entityData" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="fullList" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="numItemsNotInBatch" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="se" type="{http://web.service.scanner.tc.com/}serviceError" minOccurs="0"/>
 *         &lt;element name="sendStats" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="vi" type="{http://web.service.scanner.tc.com/}versionInfo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AuthRes", propOrder = {
    "entity",
    "fullList",
    "numItemsNotInBatch",
    "se",
    "sendStats",
    "vi"
})
public class AuthRes {

    @XmlElement(nillable = true)
    protected List<EntityData> entity;
    protected boolean fullList;
    protected int numItemsNotInBatch;
    protected ServiceError se;
    protected boolean sendStats;
    protected VersionInfo vi;

    /**
     * Gets the value of the entity property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the entity property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEntity().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EntityData }
     * 
     * 
     */
    public List<EntityData> getEntity() {
        if (entity == null) {
            entity = new ArrayList<EntityData>();
        }
        return this.entity;
    }

    /**
     * Gets the value of the fullList property.
     * 
     */
    public boolean isFullList() {
        return fullList;
    }

    /**
     * Sets the value of the fullList property.
     * 
     */
    public void setFullList(boolean value) {
        this.fullList = value;
    }

    /**
     * Gets the value of the numItemsNotInBatch property.
     * 
     */
    public int getNumItemsNotInBatch() {
        return numItemsNotInBatch;
    }

    /**
     * Sets the value of the numItemsNotInBatch property.
     * 
     */
    public void setNumItemsNotInBatch(int value) {
        this.numItemsNotInBatch = value;
    }

    /**
     * Gets the value of the se property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceError }
     *     
     */
    public ServiceError getSe() {
        return se;
    }

    /**
     * Sets the value of the se property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceError }
     *     
     */
    public void setSe(ServiceError value) {
        this.se = value;
    }

    /**
     * Gets the value of the sendStats property.
     * 
     */
    public boolean isSendStats() {
        return sendStats;
    }

    /**
     * Sets the value of the sendStats property.
     * 
     */
    public void setSendStats(boolean value) {
        this.sendStats = value;
    }

    /**
     * Gets the value of the vi property.
     * 
     * @return
     *     possible object is
     *     {@link VersionInfo }
     *     
     */
    public VersionInfo getVi() {
        return vi;
    }

    /**
     * Sets the value of the vi property.
     * 
     * @param value
     *     allowed object is
     *     {@link VersionInfo }
     *     
     */
    public void setVi(VersionInfo value) {
        this.vi = value;
    }

}
