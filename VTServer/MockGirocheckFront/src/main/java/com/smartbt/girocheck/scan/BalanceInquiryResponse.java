
package com.smartbt.girocheck.scan;

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
 *         &lt;element name="WMBalanceInquiryResult" type="{https://SistemasGalileo.com/Services/}BalanceInquiryRes" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "balanceInquiryResponse", propOrder = {
    "_return"
})
public class BalanceInquiryResponse {

    @XmlElement(name = "return")
    protected BalanceInquiryRes _return;

    /**
     * Gets the value of the wmBalanceInquiryResult property.
     * 
     * @return
     *     possible object is
     *     {@link BalanceInquiryRes }
     *     
     */
    public BalanceInquiryRes getBalanceInquiryResult() {
        return _return;
    }

    /**
     * Sets the value of the wmBalanceInquiryResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link BalanceInquiryRes }
     *     
     */
    public void setBalanceInquiryResult(BalanceInquiryRes value) {
        this._return = value;
    }

}
