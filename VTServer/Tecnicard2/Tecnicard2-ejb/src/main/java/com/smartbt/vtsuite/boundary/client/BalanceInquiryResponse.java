
package com.smartbt.vtsuite.boundary.client;

import com.smartbt.girocheck.servercommon.utils.IMap;
import com.smartbt.girocheck.servercommon.enums.ParameterName;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BalanceInquiryResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BalanceInquiryResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{https://SistemasGalileo.com/Services/}MainResponseContainer">
 *       &lt;sequence>
 *         &lt;element name="Balance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="InTransitFunds" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BalanceInquiryResponse", propOrder = {
    "balance",
    "inTransitFunds"
})
public class BalanceInquiryResponse
    extends MainResponseContainer implements IMap
{
    private static String EXPECTED_RESULT_CODE = "0";

    @XmlElement(name = "Balance")
    protected String balance;
    @XmlElement(name = "InTransitFunds")
    protected String inTransitFunds;

      @Override
        public Map toMap() {
            Map map = super.getMap(EXPECTED_RESULT_CODE);
             map.put(ParameterName.BALANCE, balance);
             map.put(ParameterName.IN_TRANSIT_FUNDS, inTransitFunds);
           return map;
        }
    
      
    /**
     * Gets the value of the balance property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBalance() {
        return balance;
    }

    /**
     * Sets the value of the balance property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBalance(String value) {
        this.balance = value;
    }

    /**
     * Gets the value of the inTransitFunds property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInTransitFunds() {
        return inTransitFunds;
    }

    /**
     * Sets the value of the inTransitFunds property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInTransitFunds(String value) {
        this.inTransitFunds = value;
    }

}
