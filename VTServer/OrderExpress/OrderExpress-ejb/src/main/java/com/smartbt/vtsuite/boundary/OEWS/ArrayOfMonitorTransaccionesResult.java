
package com.smartbt.vtsuite.boundary.OEWS;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfMonitor_transaccionesResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfMonitor_transaccionesResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="monitor_transaccionesResult" type="{http://tempuri.org/}monitor_transaccionesResult" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfMonitor_transaccionesResult", propOrder = {
    "monitorTransaccionesResult"
})
public class ArrayOfMonitorTransaccionesResult {

    @XmlElement(name = "monitor_transaccionesResult", nillable = true)
    protected List<MonitorTransaccionesResult> monitorTransaccionesResult;

    /**
     * Gets the value of the monitorTransaccionesResult property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the monitorTransaccionesResult property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMonitorTransaccionesResult().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MonitorTransaccionesResult }
     * 
     * 
     */
    public List<MonitorTransaccionesResult> getMonitorTransaccionesResult() {
        if (monitorTransaccionesResult == null) {
            monitorTransaccionesResult = new ArrayList<MonitorTransaccionesResult>();
        }
        return this.monitorTransaccionesResult;
    }

}
