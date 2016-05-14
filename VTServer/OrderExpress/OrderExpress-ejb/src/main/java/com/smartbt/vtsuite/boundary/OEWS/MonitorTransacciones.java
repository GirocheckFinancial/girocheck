
package com.smartbt.vtsuite.boundary.OEWS;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for monitor_transacciones complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="monitor_transacciones">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FechaIN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FechaFI" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IdPayer" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="IdTip" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Factura" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Profile" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "monitor_transacciones", propOrder = {
    "fechaIN",
    "fechaFI",
    "idPayer",
    "idTip",
    "factura",
    "profile"
})
public class MonitorTransacciones {

    @XmlElement(name = "FechaIN")
    protected String fechaIN;
    @XmlElement(name = "FechaFI")
    protected String fechaFI;
    @XmlElement(name = "IdPayer")
    protected int idPayer;
    @XmlElement(name = "IdTip")
    protected int idTip;
    @XmlElement(name = "Factura")
    protected String factura;
    @XmlElement(name = "Profile")
    protected int profile;

    /**
     * Gets the value of the fechaIN property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaIN() {
        return fechaIN;
    }

    /**
     * Sets the value of the fechaIN property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaIN(String value) {
        this.fechaIN = value;
    }

    /**
     * Gets the value of the fechaFI property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaFI() {
        return fechaFI;
    }

    /**
     * Sets the value of the fechaFI property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaFI(String value) {
        this.fechaFI = value;
    }

    /**
     * Gets the value of the idPayer property.
     * 
     */
    public int getIdPayer() {
        return idPayer;
    }

    /**
     * Sets the value of the idPayer property.
     * 
     */
    public void setIdPayer(int value) {
        this.idPayer = value;
    }

    /**
     * Gets the value of the idTip property.
     * 
     */
    public int getIdTip() {
        return idTip;
    }

    /**
     * Sets the value of the idTip property.
     * 
     */
    public void setIdTip(int value) {
        this.idTip = value;
    }

    /**
     * Gets the value of the factura property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFactura() {
        return factura;
    }

    /**
     * Sets the value of the factura property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFactura(String value) {
        this.factura = value;
    }

    /**
     * Gets the value of the profile property.
     * 
     */
    public int getProfile() {
        return profile;
    }

    /**
     * Sets the value of the profile property.
     * 
     */
    public void setProfile(int value) {
        this.profile = value;
    }

}
