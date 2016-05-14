package com.smartbt.girocheck.servercommon.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Alejo
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sensitiveData", propOrder = {
    "number"
})
public class SensitiveData implements Serializable {
    @XmlElement(name = "number")
    protected String number;

    public SensitiveData() {
    }

    /**
     * @return the number
     */
    public String getNumber() {
        return number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(String number) {
        this.number = number;
    }
    
}
