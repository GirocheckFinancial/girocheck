
package com.smartbt.girocheck.scan;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for personalInfoResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="personalInfoResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://scan.girocheck.smartbt.com/}PersonalInfoRes" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "orderExpressResponse", propOrder = {
    "_return"
})
public class OrderExpressResponse {

    @XmlElement(name = "return")
    protected OrderExpressRes _return;

    public void setReturn(OrderExpressRes _return) {
        this._return = _return;
    }

    public OrderExpressRes getReturn() {
        return _return;
    }

    
}
