
package com.smartbt.vtsuite.boundary.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getCheckImage complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getCheckImage">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="getCheckImageRequest" type="{http://web.service.fileloader.tc.com/}getCheckImageRequest" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getCheckImage", propOrder = {
    "getCheckImageRequest"
})
public class GetCheckImage {

    protected GetCheckImageRequest getCheckImageRequest;

    /**
     * Gets the value of the getCheckImageRequest property.
     * 
     * @return
     *     possible object is
     *     {@link GetCheckImageRequest }
     *     
     */
    public GetCheckImageRequest getGetCheckImageRequest() {
        return getCheckImageRequest;
    }

    /**
     * Sets the value of the getCheckImageRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetCheckImageRequest }
     *     
     */
    public void setGetCheckImageRequest(GetCheckImageRequest value) {
        this.getCheckImageRequest = value;
    }

}
