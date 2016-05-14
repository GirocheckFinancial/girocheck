
package com.smartbt.girocheck.scan;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tecnicardConfirmation", propOrder = {
    "arg0"
})
public class TecnicardConfirmation {

    protected TecnicardConfirmationRequest arg0;

    /**
     * Gets the value of the arg0 property.
     * 
     * @return
     *     possible object is
     *     {@link CheckAuthRequest }
     *     
     */
    public TecnicardConfirmationRequest getArg0() {
        return arg0;
    }

    /**
     * Sets the value of the arg0 property.
     * 
     * @param value
     *     allowed object is
     *     {@link CheckAuthRequest }
     *     
     */
    public void setArg0(TecnicardConfirmationRequest value) {
        this.arg0 = value;
    }

}
