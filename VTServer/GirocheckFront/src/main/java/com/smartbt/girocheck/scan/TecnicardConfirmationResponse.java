
package com.smartbt.girocheck.scan;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tecnicardConfirmationResponse", propOrder = {
    "_return"
})
public class TecnicardConfirmationResponse {

    @XmlElement(name = "return")
    protected TecnicardConfirmationRes _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link CheckAuthRes }
     *     
     */
    public TecnicardConfirmationRes getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link CheckAuthRes }
     *     
     */
    public void setReturn(TecnicardConfirmationRes value) {
        this._return = value;
    }

}
