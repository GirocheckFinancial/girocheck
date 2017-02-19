
package com.smartbt.vtsuite.boundary;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PCAResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PCAResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TransID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ResponseCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ApprovalNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SettlementID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Roundtrip" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="5" minOccurs="0"/>
 *         &lt;element name="Custom" type="{http://fis.certegy.pca.com/}Custom" maxOccurs="50" minOccurs="0"/>
 *         &lt;element name="CertegyUID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CheckABA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CheckAcct" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CheckNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CheckType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Fee" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PCAResponse", propOrder = {
    "transID",
    "responseCode",
    "approvalNumber",
    "settlementID",
    "roundtrip",
    "custom",
    "certegyUID",
    "checkABA",
    "checkAcct",
    "checkNumber",
    "checkType",
    "fee"
})
public class PCAResponse {

    @XmlElement(name = "TransID", required = true)
    protected String transID;
    @XmlElement(name = "ResponseCode", required = true)
    protected String responseCode;
    @XmlElement(name = "ApprovalNumber")
    protected String approvalNumber;
    @XmlElement(name = "SettlementID")
    protected String settlementID;
    @XmlElement(name = "Roundtrip")
    protected List<String> roundtrip;
    @XmlElement(name = "Custom")
    protected List<Custom> custom;
    @XmlElement(name = "CertegyUID")
    protected String certegyUID;
    @XmlElement(name = "CheckABA")
    protected String checkABA;
    @XmlElement(name = "CheckAcct")
    protected String checkAcct;
    @XmlElement(name = "CheckNumber")
    protected String checkNumber;
    @XmlElement(name = "CheckType")
    protected String checkType;
    @XmlElement(name = "Fee")
    protected BigDecimal fee;
    
    

    /**
     * Gets the value of the transID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransID() {
        return transID;
    }

    /**
     * Sets the value of the transID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransID(String value) {
        this.transID = value;
    }

    /**
     * Gets the value of the responseCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResponseCode() {
        return responseCode;
    }

    /**
     * Sets the value of the responseCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResponseCode(String value) {
        this.responseCode = value;
    }

    /**
     * Gets the value of the approvalNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApprovalNumber() {
        return approvalNumber;
    }

    /**
     * Sets the value of the approvalNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApprovalNumber(String value) {
        this.approvalNumber = value;
    }

    /**
     * Gets the value of the settlementID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSettlementID() {
        return settlementID;
    }

    /**
     * Sets the value of the settlementID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSettlementID(String value) {
        this.settlementID = value;
    }

    /**
     * Gets the value of the roundtrip property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the roundtrip property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRoundtrip().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getRoundtrip() {
        if (roundtrip == null) {
            roundtrip = new ArrayList<String>();
        }
        return this.roundtrip;
    }

    /**
     * Gets the value of the custom property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the custom property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCustom().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Custom }
     * 
     * 
     */
    public List<Custom> getCustom() {
        if (custom == null) {
            custom = new ArrayList<Custom>();
        }
        return this.custom;
    }

    /**
     * Gets the value of the certegyUID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCertegyUID() {
        return certegyUID;
    }

    /**
     * Sets the value of the certegyUID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCertegyUID(String value) {
        this.certegyUID = value;
    }

    /**
     * Gets the value of the checkABA property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCheckABA() {
        return checkABA;
    }

    /**
     * Sets the value of the checkABA property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCheckABA(String value) {
        this.checkABA = value;
    }

    /**
     * Gets the value of the checkAcct property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCheckAcct() {
        return checkAcct;
    }

    /**
     * Sets the value of the checkAcct property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCheckAcct(String value) {
        this.checkAcct = value;
    }

    /**
     * Gets the value of the checkNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCheckNumber() {
        return checkNumber;
    }

    /**
     * Sets the value of the checkNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCheckNumber(String value) {
        this.checkNumber = value;
    }

    /**
     * Gets the value of the checkType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCheckType() {
        return checkType;
    }

    /**
     * Sets the value of the checkType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCheckType(String value) {
        this.checkType = value;
    }

    /**
     * Gets the value of the fee property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getFee() {
        return fee;
    }

    /**
     * Sets the value of the fee property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setFee(BigDecimal value) {
        this.fee = value;
    }

}
