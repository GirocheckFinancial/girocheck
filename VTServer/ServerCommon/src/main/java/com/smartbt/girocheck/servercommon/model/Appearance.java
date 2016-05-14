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
@XmlType(name = "appearance", propOrder = {
    "gender"
})
public class Appearance implements Serializable{
    @XmlElement(name = "gender")
    protected int gender;

    public Appearance() {
    }

    /**
     * @return the gender
     */
    public int getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(int gender) {
        this.gender = gender;
    }
    
    
}
