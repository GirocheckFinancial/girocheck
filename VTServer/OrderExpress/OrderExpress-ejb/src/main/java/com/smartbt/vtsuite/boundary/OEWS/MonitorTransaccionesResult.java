
package com.smartbt.vtsuite.boundary.OEWS;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for monitor_transaccionesResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="monitor_transaccionesResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="factura" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fecCon" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idStatus" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cliente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="beneficiario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="destino" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="servicio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dollares" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="pesos" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="fondeada" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="oficina" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fondeo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descontada" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "monitor_transaccionesResult", propOrder = {
    "factura",
    "fecCon",
    "idStatus",
    "status",
    "cliente",
    "beneficiario",
    "destino",
    "servicio",
    "dollares",
    "pesos",
    "fondeada",
    "oficina",
    "fondeo",
    "descontada"
})
public class MonitorTransaccionesResult {

    protected String factura;
    protected String fecCon;
    protected int idStatus;
    protected String status;
    protected String cliente;
    protected String beneficiario;
    protected String destino;
    protected String servicio;
    protected double dollares;
    protected double pesos;
    protected String fondeada;
    protected String oficina;
    protected String fondeo;
    protected String descontada;

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
     * Gets the value of the fecCon property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFecCon() {
        return fecCon;
    }

    /**
     * Sets the value of the fecCon property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFecCon(String value) {
        this.fecCon = value;
    }

    /**
     * Gets the value of the idStatus property.
     * 
     */
    public int getIdStatus() {
        return idStatus;
    }

    /**
     * Sets the value of the idStatus property.
     * 
     */
    public void setIdStatus(int value) {
        this.idStatus = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the cliente property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCliente() {
        return cliente;
    }

    /**
     * Sets the value of the cliente property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCliente(String value) {
        this.cliente = value;
    }

    /**
     * Gets the value of the beneficiario property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBeneficiario() {
        return beneficiario;
    }

    /**
     * Sets the value of the beneficiario property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBeneficiario(String value) {
        this.beneficiario = value;
    }

    /**
     * Gets the value of the destino property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestino() {
        return destino;
    }

    /**
     * Sets the value of the destino property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestino(String value) {
        this.destino = value;
    }

    /**
     * Gets the value of the servicio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServicio() {
        return servicio;
    }

    /**
     * Sets the value of the servicio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServicio(String value) {
        this.servicio = value;
    }

    /**
     * Gets the value of the dollares property.
     * 
     */
    public double getDollares() {
        return dollares;
    }

    /**
     * Sets the value of the dollares property.
     * 
     */
    public void setDollares(double value) {
        this.dollares = value;
    }

    /**
     * Gets the value of the pesos property.
     * 
     */
    public double getPesos() {
        return pesos;
    }

    /**
     * Sets the value of the pesos property.
     * 
     */
    public void setPesos(double value) {
        this.pesos = value;
    }

    /**
     * Gets the value of the fondeada property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFondeada() {
        return fondeada;
    }

    /**
     * Sets the value of the fondeada property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFondeada(String value) {
        this.fondeada = value;
    }

    /**
     * Gets the value of the oficina property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOficina() {
        return oficina;
    }

    /**
     * Sets the value of the oficina property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOficina(String value) {
        this.oficina = value;
    }

    /**
     * Gets the value of the fondeo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFondeo() {
        return fondeo;
    }

    /**
     * Sets the value of the fondeo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFondeo(String value) {
        this.fondeo = value;
    }

    /**
     * Gets the value of the descontada property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescontada() {
        return descontada;
    }

    /**
     * Sets the value of the descontada property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescontada(String value) {
        this.descontada = value;
    }

}
