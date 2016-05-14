
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
 *         &lt;element name="WMCardToBankConfirmationResult" type="{https://SistemasGalileo.com/Services/}CardToBankConfirmationRes" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cardToBankConfirmationResponse", propOrder = {
    "_return"
})
public class CardToBankConfirmationResponse {

    @XmlElement(name = "return")
    protected CardToBankConfirmationRes _return;

    /**
     * Gets the value of the wmCardToBankConfirmationResult property.
     * 
     * @return
     *     possible object is
     *     {@link CardToBankRes }
     *     
     */
    public CardToBankConfirmationRes getReturn() {
        return _return;
    }

    /**
     * Sets the value of the wmCardToBankConfirmationResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link CardToBankConfirmationRes }
     *     
     */
    public void setCardToBankConfirmationResult(CardToBankConfirmationRes value) {
        this._return = value;
    }

}
