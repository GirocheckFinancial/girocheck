
package com.smartbt.vtsuite.boundary.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for checkAuthSubmitResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="checkAuthSubmitResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "checkAuthSubmitResponse", propOrder = {
    "_return"
})
public class CheckAuthSubmitResponse {
@XmlElement(name = "return")
    private CheckAuthRes _return;

    /**
     * @return the _return
     */
    public CheckAuthRes getReturn() {
        return _return;
    }

    /**
     * @param _return the _return to set
     */
    public void setReturn(CheckAuthRes _return) {
        this._return = _return;
    }

}
