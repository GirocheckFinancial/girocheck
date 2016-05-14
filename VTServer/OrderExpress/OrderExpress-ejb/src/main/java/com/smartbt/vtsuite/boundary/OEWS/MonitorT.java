
package com.smartbt.vtsuite.boundary.OEWS;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="monitor" type="{http://tempuri.org/}monitor_transacciones" minOccurs="0"/>
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
    "monitor"
})
@XmlRootElement(name = "monitorT")
public class MonitorT {

    protected MonitorTransacciones monitor;

    /**
     * Gets the value of the monitor property.
     * 
     * @return
     *     possible object is
     *     {@link MonitorTransacciones }
     *     
     */
    public MonitorTransacciones getMonitor() {
        return monitor;
    }

    /**
     * Sets the value of the monitor property.
     * 
     * @param value
     *     allowed object is
     *     {@link MonitorTransacciones }
     *     
     */
    public void setMonitor(MonitorTransacciones value) {
        this.monitor = value;
    }

}
