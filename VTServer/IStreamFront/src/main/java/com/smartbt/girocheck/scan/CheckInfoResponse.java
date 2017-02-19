
package com.smartbt.girocheck.scan;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


 
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "checkInfoResponse", propOrder = {
    "_return"
})
public class CheckInfoResponse {

    @XmlElement(name = "return")
    protected CheckInfoRes _return;

    public void setReturn(CheckInfoRes _return) {
        this._return = _return;
    }

    public CheckInfoRes getReturn() {
        return _return;
    }

    
}
