
package com.smartbt.vtsuite.boundary.client;

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
 *         &lt;element name="pRequestID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pCard" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pIdType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pIdExpiration" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pIdCountry" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pIdState" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pPersonTitle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pFirstName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pMiddleName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pLastName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pMaidenName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pDateOfBirth" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pCountry" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pState" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pZipCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pEmail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pTelephoneAreaCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pTelephone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pCellphoneAreaCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pCellphone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pWorkphoneAreaCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pWorkphone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pFaxAreaCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pFaxphone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pRBService" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pCurrentAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "pRequestID",
    "pCard",
    "pId",
    "pIdType",
    "pIdExpiration",
    "pIdCountry",
    "pIdState",
    "pPersonTitle",
    "pFirstName",
    "pMiddleName",
    "pLastName",
    "pMaidenName",
    "pDateOfBirth",
    "pCountry",
    "pState",
    "pCity",
    "pAddress",
    "pZipCode",
    "pEmail",
    "pTelephoneAreaCode",
    "pTelephone",
    "pCellphoneAreaCode",
    "pCellphone",
    "pWorkphoneAreaCode",
    "pWorkphone",
    "pFaxAreaCode",
    "pFaxphone",
    "prbService",
    "pCurrentAddress"
})
@XmlRootElement(name = "WMCardPersonalization")
public class WMCardPersonalization {

    protected String pRequestID;
    protected String pCard;
    protected String pId;
    protected String pIdType;
    protected String pIdExpiration;
    protected String pIdCountry;
    protected String pIdState;
    protected String pPersonTitle;
    protected String pFirstName;
    protected String pMiddleName;
    protected String pLastName;
    protected String pMaidenName;
    protected String pDateOfBirth;
    protected String pCountry;
    protected String pState;
    protected String pCity;
    protected String pAddress;
    protected String pZipCode;
    protected String pEmail;
    protected String pTelephoneAreaCode;
    protected String pTelephone;
    protected String pCellphoneAreaCode;
    protected String pCellphone;
    protected String pWorkphoneAreaCode;
    protected String pWorkphone;
    protected String pFaxAreaCode;
    protected String pFaxphone;
    @XmlElement(name = "pRBService")
    protected String prbService;
    protected String pCurrentAddress;

    /**
     * Gets the value of the pRequestID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPRequestID() {
        return pRequestID;
    }

    /**
     * Sets the value of the pRequestID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPRequestID(String value) {
        this.pRequestID = value;
    }

    /**
     * Gets the value of the pCard property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPCard() {
        return pCard;
    }

    /**
     * Sets the value of the pCard property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPCard(String value) {
        this.pCard = value;
    }

    /**
     * Gets the value of the pId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPId() {
        return pId;
    }

    /**
     * Sets the value of the pId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPId(String value) {
        this.pId = value;
    }

    /**
     * Gets the value of the pIdType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPIdType() {
        return pIdType;
    }

    /**
     * Sets the value of the pIdType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPIdType(String value) {
        this.pIdType = value;
    }

    /**
     * Gets the value of the pIdExpiration property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPIdExpiration() {
        return pIdExpiration;
    }

    /**
     * Sets the value of the pIdExpiration property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPIdExpiration(String value) {
        this.pIdExpiration = value;
    }

    /**
     * Gets the value of the pIdCountry property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPIdCountry() {
        return pIdCountry;
    }

    /**
     * Sets the value of the pIdCountry property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPIdCountry(String value) {
        this.pIdCountry = value;
    }

    /**
     * Gets the value of the pIdState property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPIdState() {
        return pIdState;
    }

    /**
     * Sets the value of the pIdState property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPIdState(String value) {
        this.pIdState = value;
    }

    /**
     * Gets the value of the pPersonTitle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPPersonTitle() {
        return pPersonTitle;
    }

    /**
     * Sets the value of the pPersonTitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPPersonTitle(String value) {
        this.pPersonTitle = value;
    }

    /**
     * Gets the value of the pFirstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPFirstName() {
        return pFirstName;
    }

    /**
     * Sets the value of the pFirstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPFirstName(String value) {
        this.pFirstName = value;
    }

    /**
     * Gets the value of the pMiddleName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPMiddleName() {
        return pMiddleName;
    }

    /**
     * Sets the value of the pMiddleName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPMiddleName(String value) {
        this.pMiddleName = value;
    }

    /**
     * Gets the value of the pLastName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPLastName() {
        return pLastName;
    }

    /**
     * Sets the value of the pLastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPLastName(String value) {
        this.pLastName = value;
    }

    /**
     * Gets the value of the pMaidenName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPMaidenName() {
        return pMaidenName;
    }

    /**
     * Sets the value of the pMaidenName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPMaidenName(String value) {
        this.pMaidenName = value;
    }

    /**
     * Gets the value of the pDateOfBirth property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPDateOfBirth() {
        return pDateOfBirth;
    }

    /**
     * Sets the value of the pDateOfBirth property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPDateOfBirth(String value) {
        this.pDateOfBirth = value;
    }

    /**
     * Gets the value of the pCountry property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPCountry() {
        return pCountry;
    }

    /**
     * Sets the value of the pCountry property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPCountry(String value) {
        this.pCountry = value;
    }

    /**
     * Gets the value of the pState property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPState() {
        return pState;
    }

    /**
     * Sets the value of the pState property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPState(String value) {
        this.pState = value;
    }

    /**
     * Gets the value of the pCity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPCity() {
        return pCity;
    }

    /**
     * Sets the value of the pCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPCity(String value) {
        this.pCity = value;
    }

    /**
     * Gets the value of the pAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPAddress() {
        return pAddress;
    }

    /**
     * Sets the value of the pAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPAddress(String value) {
        this.pAddress = value;
    }

    /**
     * Gets the value of the pZipCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPZipCode() {
        return pZipCode;
    }

    /**
     * Sets the value of the pZipCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPZipCode(String value) {
        this.pZipCode = value;
    }

    /**
     * Gets the value of the pEmail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPEmail() {
        return pEmail;
    }

    /**
     * Sets the value of the pEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPEmail(String value) {
        this.pEmail = value;
    }

    /**
     * Gets the value of the pTelephoneAreaCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPTelephoneAreaCode() {
        return pTelephoneAreaCode;
    }

    /**
     * Sets the value of the pTelephoneAreaCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPTelephoneAreaCode(String value) {
        this.pTelephoneAreaCode = value;
    }

    /**
     * Gets the value of the pTelephone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPTelephone() {
        return pTelephone;
    }

    /**
     * Sets the value of the pTelephone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPTelephone(String value) {
        this.pTelephone = value;
    }

    /**
     * Gets the value of the pCellphoneAreaCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPCellphoneAreaCode() {
        return pCellphoneAreaCode;
    }

    /**
     * Sets the value of the pCellphoneAreaCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPCellphoneAreaCode(String value) {
        this.pCellphoneAreaCode = value;
    }

    /**
     * Gets the value of the pCellphone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPCellphone() {
        return pCellphone;
    }

    /**
     * Sets the value of the pCellphone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPCellphone(String value) {
        this.pCellphone = value;
    }

    /**
     * Gets the value of the pWorkphoneAreaCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPWorkphoneAreaCode() {
        return pWorkphoneAreaCode;
    }

    /**
     * Sets the value of the pWorkphoneAreaCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPWorkphoneAreaCode(String value) {
        this.pWorkphoneAreaCode = value;
    }

    /**
     * Gets the value of the pWorkphone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPWorkphone() {
        return pWorkphone;
    }

    /**
     * Sets the value of the pWorkphone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPWorkphone(String value) {
        this.pWorkphone = value;
    }

    /**
     * Gets the value of the pFaxAreaCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPFaxAreaCode() {
        return pFaxAreaCode;
    }

    /**
     * Sets the value of the pFaxAreaCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPFaxAreaCode(String value) {
        this.pFaxAreaCode = value;
    }

    /**
     * Gets the value of the pFaxphone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPFaxphone() {
        return pFaxphone;
    }

    /**
     * Sets the value of the pFaxphone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPFaxphone(String value) {
        this.pFaxphone = value;
    }

    /**
     * Gets the value of the prbService property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPRBService() {
        return prbService;
    }

    /**
     * Sets the value of the prbService property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPRBService(String value) {
        this.prbService = value;
    }

    /**
     * Gets the value of the pCurrentAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPCurrentAddress() {
        return pCurrentAddress;
    }

    /**
     * Sets the value of the pCurrentAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPCurrentAddress(String value) {
        this.pCurrentAddress = value;
    }

}
