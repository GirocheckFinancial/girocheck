
package com.smartbt.vtsuite.boundary.client;

import com.smartbt.girocheck.servercommon.enums.ParameterName;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MainResponseContainer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MainResponseContainer">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SessionTag" type="{https://SistemasGalileo.com/Services/}SessionTag" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MainResponseContainer", propOrder = {
    "sessionTag"
})
@XmlSeeAlso({
    CardHolderValidationResponse.class,
    CardLoadResponse.class,
    BalanceInquiryResponse.class,
    EchoResponse.class,
    CardToBankResponse.class,
    CardToBankConfirmationResponse.class,
    CardCreationResponse.class,
    LastTransactionsResponse.class,
    CardActivationResponse.class,
    CashToCardResponse.class,
    CardValidationResponse.class
})
public abstract class MainResponseContainer {

    @XmlElement(name = "SessionTag")
    protected SessionTag sessionTag;

    /**
     * Gets the value of the sessionTag property.
     * 
     * @return
     *     possible object is
     *     {@link SessionTag }
     *     
     */
    public SessionTag getSessionTag() {
        return sessionTag;
    }

    /**
     * Sets the value of the sessionTag property.
     * 
     * @param value
     *     allowed object is
     *     {@link SessionTag }
     *     
     */
    public void setSessionTag(SessionTag value) {
        this.sessionTag = value;
    }

    public Map getMap(String expectedResultCode){
        Map map = new HashMap();
          map.put(ParameterName.SESSION_TAG_MAP , sessionTag.getMap(expectedResultCode));

          return map;
    }
}
