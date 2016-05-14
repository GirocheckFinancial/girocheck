
package com.smartbt.vtsuite.boundary.OEWS;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ObtenUltimaFacResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ObtenUltimaFacResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="agency" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Id_Destiny" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Id_agency" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Store_code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Id_service" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Deposit" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="account_num" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ObtenUltimaFacResult", propOrder = {
    "agency",
    "idDestiny",
    "idAgency",
    "storeCode",
    "description",
    "idService",
    "deposit",
    "accountNum"
})
public class ObtenUltimaFacResult {

    protected String agency;
    @XmlElement(name = "Id_Destiny")
    protected int idDestiny;
    @XmlElement(name = "Id_agency")
    protected int idAgency;
    @XmlElement(name = "Store_code")
    protected String storeCode;
    protected String description;
    @XmlElement(name = "Id_service")
    protected int idService;
    @XmlElement(name = "Deposit")
    protected double deposit;
    @XmlElement(name = "account_num")
    protected String accountNum;

    /**
     * Gets the value of the agency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgency() {
        return agency;
    }

    /**
     * Sets the value of the agency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgency(String value) {
        this.agency = value;
    }

    /**
     * Gets the value of the idDestiny property.
     * 
     */
    public int getIdDestiny() {
        return idDestiny;
    }

    /**
     * Sets the value of the idDestiny property.
     * 
     */
    public void setIdDestiny(int value) {
        this.idDestiny = value;
    }

    /**
     * Gets the value of the idAgency property.
     * 
     */
    public int getIdAgency() {
        return idAgency;
    }

    /**
     * Sets the value of the idAgency property.
     * 
     */
    public void setIdAgency(int value) {
        this.idAgency = value;
    }

    /**
     * Gets the value of the storeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStoreCode() {
        return storeCode;
    }

    /**
     * Sets the value of the storeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStoreCode(String value) {
        this.storeCode = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the idService property.
     * 
     */
    public int getIdService() {
        return idService;
    }

    /**
     * Sets the value of the idService property.
     * 
     */
    public void setIdService(int value) {
        this.idService = value;
    }

    /**
     * Gets the value of the deposit property.
     * 
     */
    public double getDeposit() {
        return deposit;
    }

    /**
     * Sets the value of the deposit property.
     * 
     */
    public void setDeposit(double value) {
        this.deposit = value;
    }

    /**
     * Gets the value of the accountNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountNum() {
        return accountNum;
    }

    /**
     * Sets the value of the accountNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountNum(String value) {
        this.accountNum = value;
    }

}
