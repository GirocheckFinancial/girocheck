
package com.smartbt.vtsuite.boundary.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sendDetailedACHCustomerResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sendDetailedACHCustomerResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://web.service.fileloader.tc.com/}transactionLoaderReply" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sendDetailedACHCustomerResponse", propOrder = {
    "_return"
})
public class SendDetailedACHCustomerResponse {

    @XmlElement(name = "return")
    protected TransactionLoaderReply _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link TransactionLoaderReply }
     *     
     */
    public TransactionLoaderReply getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link TransactionLoaderReply }
     *     
     */
    public void setReturn(TransactionLoaderReply value) {
        this._return = value;
    }

}
