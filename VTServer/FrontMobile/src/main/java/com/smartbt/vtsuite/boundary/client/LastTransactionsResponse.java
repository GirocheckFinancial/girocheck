
package com.smartbt.vtsuite.boundary.client;

import com.smartbt.girocheck.servercommon.utils.IMap;
import com.smartbt.girocheck.servercommon.enums.ParameterName;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LastTransactionsResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LastTransactionsResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{https://SistemasGalileo.com/Services/}MainResponseContainer">
 *       &lt;sequence>
 *         &lt;element name="CurrencyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CurrencySymbol" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CurrencyName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="InitialBalance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FinalBalance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TransactionsList" type="{https://SistemasGalileo.com/Services/}ArrayOfTransaction" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LastTransactionsResponse", propOrder = {
    "currencyCode",
    "currencySymbol",
    "currencyName",
    "initialBalance",
    "finalBalance",
    "transactionsList"
})
public class LastTransactionsResponse
    extends MainResponseContainer implements IMap
{
 private static String EXPECTED_RESULT_CODE = "0";
    
    @XmlElement(name = "CurrencyCode")
    protected String currencyCode;
    @XmlElement(name = "CurrencySymbol")
    protected String currencySymbol;
    @XmlElement(name = "CurrencyName")
    protected String currencyName;
    @XmlElement(name = "InitialBalance")
    protected String initialBalance;
    @XmlElement(name = "FinalBalance")
    protected String finalBalance;
    @XmlElement(name = "TransactionsList")
    protected ArrayOfTransaction transactionsList;

           @Override
   public Map toMap() {
        Map map = super.getMap(EXPECTED_RESULT_CODE);
      map.put(ParameterName.CURRENCY_CODE, currencyCode);
      map.put(ParameterName.CURRENCY_SYMBOL, currencySymbol);
      map.put(ParameterName.CURRENCY_NAME, currencyName);
      map.put(ParameterName.INITIAL_BALANCE, initialBalance);
      map.put(ParameterName.FINAL_BALANCE, finalBalance);
      if(transactionsList != null)
        map.put(ParameterName.TRANSACTIONS_LIST, transactionsList.toMap());
    return map;
    }
    
    /**
     * Gets the value of the currencyCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrencyCode() {
        return currencyCode;
    }

    /**
     * Sets the value of the currencyCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrencyCode(String value) {
        this.currencyCode = value;
    }

    /**
     * Gets the value of the currencySymbol property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrencySymbol() {
        return currencySymbol;
    }

    /**
     * Sets the value of the currencySymbol property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrencySymbol(String value) {
        this.currencySymbol = value;
    }

    /**
     * Gets the value of the currencyName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrencyName() {
        return currencyName;
    }

    /**
     * Sets the value of the currencyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrencyName(String value) {
        this.currencyName = value;
    }

    /**
     * Gets the value of the initialBalance property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInitialBalance() {
        return initialBalance;
    }

    /**
     * Sets the value of the initialBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInitialBalance(String value) {
        this.initialBalance = value;
    }

    /**
     * Gets the value of the finalBalance property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFinalBalance() {
        return finalBalance;
    }

    /**
     * Sets the value of the finalBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFinalBalance(String value) {
        this.finalBalance = value;
    }

    /**
     * Gets the value of the transactionsList property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfTransaction }
     *     
     */
    public ArrayOfTransaction getTransactionsList() {
        return transactionsList;
    }

    /**
     * Sets the value of the transactionsList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfTransaction }
     *     
     */
    public void setTransactionsList(ArrayOfTransaction value) {
        this.transactionsList = value;
    }

}
