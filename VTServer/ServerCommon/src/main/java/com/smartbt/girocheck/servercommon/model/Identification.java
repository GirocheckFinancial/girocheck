package com.smartbt.girocheck.servercommon.model;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Alejo
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "identification", propOrder = {
    "sensitiveData",
    "state",
    "expDate"
})
public class Identification implements Serializable{
    
    @XmlElement(name = "sensitiveData")
    protected SensitiveData sensitiveData;
    @XmlElement(name = "state")
    protected String state;
    @XmlElement(name = "expDate")
    protected String expDate;

    public Identification() {
    }

    /**
     * @return the sensitiveData
     */
    public SensitiveData getSensitiveData() {
        return sensitiveData;
    }

    /**
     * @param sensitiveData the sensitiveData to set
     */
    public void setSensitiveData(SensitiveData sensitiveData) {
        this.sensitiveData = sensitiveData;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the expDate
     */
    public String getExpDate() {
        return expDate;
    }

    /**
     * @param expDate the expDate to set
     */
    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }
    
    
    
}
