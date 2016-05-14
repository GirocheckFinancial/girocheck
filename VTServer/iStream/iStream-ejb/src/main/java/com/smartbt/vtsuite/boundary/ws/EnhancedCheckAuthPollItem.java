
package com.smartbt.vtsuite.boundary.ws;

import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.utils.IMap;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;



/**
 * <p>Java class for enhancedCheckAuthPollItem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="enhancedCheckAuthPollItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="additionalInfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="addressCurrent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cardLoadFee" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="checkId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="entityId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="entityName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="feeAmount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastFour" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="payoutAmount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="response" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="systemAmount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="transactionDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "enhancedCheckAuthPollItem", propOrder = {
    "additionalInfo",
    "addressCurrent",
    "cardLoadFee",
    "checkId",
    "entityId",
    "entityName",
    "feeAmount",
    "lastFour",
    "payoutAmount",
    "response",
    "systemAmount",
    "transactionDate"
})
public class EnhancedCheckAuthPollItem implements IMap{

    protected String additionalInfo;
    protected String addressCurrent;
    protected String cardLoadFee;
    protected String checkId;
    protected int entityId;
    protected String entityName;
    protected String feeAmount;
    protected String lastFour;
    protected String payoutAmount;
    protected String response;
    protected String systemAmount;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar transactionDate;
    
    
    @Override
   public Map toMap() {
      Map map = new HashMap();
      map.put(ParameterName.ADDITIONAL_INFO, additionalInfo);
      map.put(ParameterName.ADDRESS_CURRENT, addressCurrent);
      map.put(ParameterName.CARDLOAD_FEE, cardLoadFee);
      map.put(ParameterName.CHECK_ID, checkId);
      map.put(ParameterName.ENTITY_ID, entityId);
      map.put(ParameterName.ENTITY_NAME, entityName);
      map.put(ParameterName.FEE_AMMOUNT, feeAmount);
      map.put(ParameterName.LAST_FOUR, lastFour);
      map.put(ParameterName.PAYOUT_AMMOUNT, payoutAmount);
      map.put(ParameterName.RESPONSE, response);
      map.put(ParameterName.SYSTEM_AMOUNT, systemAmount);
      map.put(ParameterName.TRANSACTION_DATE, transactionDate);
    return map;
    }
    /**
     * Gets the value of the additionalInfo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdditionalInfo() {
        return additionalInfo;
    }

    /**
     * Sets the value of the additionalInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdditionalInfo(String value) {
        this.additionalInfo = value;
    }

    /**
     * Gets the value of the addressCurrent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressCurrent() {
        return addressCurrent;
    }

    /**
     * Sets the value of the addressCurrent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressCurrent(String value) {
        this.addressCurrent = value;
    }

    /**
     * Gets the value of the cardLoadFee property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardLoadFee() {
        return cardLoadFee;
    }

    /**
     * Sets the value of the cardLoadFee property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardLoadFee(String value) {
        this.cardLoadFee = value;
    }

    /**
     * Gets the value of the checkId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCheckId() {
        return checkId;
    }

    /**
     * Sets the value of the checkId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCheckId(String value) {
        this.checkId = value;
    }

    /**
     * Gets the value of the entityId property.
     * 
     */
    public int getEntityId() {
        return entityId;
    }

    /**
     * Sets the value of the entityId property.
     * 
     */
    public void setEntityId(int value) {
        this.entityId = value;
    }

    /**
     * Gets the value of the entityName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntityName() {
        return entityName;
    }

    /**
     * Sets the value of the entityName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntityName(String value) {
        this.entityName = value;
    }

    /**
     * Gets the value of the feeAmount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFeeAmount() {
        return feeAmount;
    }

    /**
     * Sets the value of the feeAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFeeAmount(String value) {
        this.feeAmount = value;
    }

    /**
     * Gets the value of the lastFour property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastFour() {
        return lastFour;
    }

    /**
     * Sets the value of the lastFour property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastFour(String value) {
        this.lastFour = value;
    }

    /**
     * Gets the value of the payoutAmount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayoutAmount() {
        return payoutAmount;
    }

    /**
     * Sets the value of the payoutAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayoutAmount(String value) {
        this.payoutAmount = value;
    }

    /**
     * Gets the value of the response property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResponse() {
        return response;
    }

    /**
     * Sets the value of the response property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResponse(String value) {
        this.response = value;
    }

    /**
     * Gets the value of the systemAmount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSystemAmount() {
        return systemAmount;
    }

    /**
     * Sets the value of the systemAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSystemAmount(String value) {
        this.systemAmount = value;
    }

    /**
     * Gets the value of the transactionDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTransactionDate() {
        return transactionDate;
    }

    /**
     * Sets the value of the transactionDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTransactionDate(XMLGregorianCalendar value) {
        this.transactionDate = value;
    }

}
