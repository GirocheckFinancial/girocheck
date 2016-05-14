
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
 *         &lt;element name="monitorTResult" type="{http://tempuri.org/}ArrayOfMonitor_transaccionesResult" minOccurs="0"/>
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
    "monitorTResult"
})
@XmlRootElement(name = "monitorTResponse")
public class MonitorTResponse {

    protected ArrayOfMonitorTransaccionesResult monitorTResult;

    /**
     * Gets the value of the monitorTResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfMonitorTransaccionesResult }
     *     
     */
    public ArrayOfMonitorTransaccionesResult getMonitorTResult() {
        return monitorTResult;
    }

    /**
     * Sets the value of the monitorTResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfMonitorTransaccionesResult }
     *     
     */
    public void setMonitorTResult(ArrayOfMonitorTransaccionesResult value) {
        this.monitorTResult = value;
    }

}
