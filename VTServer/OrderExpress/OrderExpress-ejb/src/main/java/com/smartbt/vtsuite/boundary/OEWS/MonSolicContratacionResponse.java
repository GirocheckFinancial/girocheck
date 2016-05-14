
package com.smartbt.vtsuite.boundary.OEWS;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Mon_SolicContratacionResult" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "monSolicContratacionResult"
})
@XmlRootElement(name = "Mon_SolicContratacionResponse")
public class MonSolicContratacionResponse {

    @XmlElement(name = "Mon_SolicContratacionResult")
    protected int monSolicContratacionResult;

    /**
     * Gets the value of the monSolicContratacionResult property.
     * 
     */
    public int getMonSolicContratacionResult() {
        return monSolicContratacionResult;
    }

    /**
     * Sets the value of the monSolicContratacionResult property.
     * 
     */
    public void setMonSolicContratacionResult(int value) {
        this.monSolicContratacionResult = value;
    }

}
