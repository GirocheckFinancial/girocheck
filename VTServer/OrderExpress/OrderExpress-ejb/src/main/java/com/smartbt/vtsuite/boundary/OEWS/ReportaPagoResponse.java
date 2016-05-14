
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
 *         &lt;element name="ReportaPagoResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "reportaPagoResult"
})
@XmlRootElement(name = "ReportaPagoResponse")
public class ReportaPagoResponse {

    @XmlElement(name = "ReportaPagoResult")
    protected String reportaPagoResult;

    /**
     * Gets the value of the reportaPagoResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReportaPagoResult() {
        return reportaPagoResult;
    }

    /**
     * Sets the value of the reportaPagoResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReportaPagoResult(String value) {
        this.reportaPagoResult = value;
    }

}
