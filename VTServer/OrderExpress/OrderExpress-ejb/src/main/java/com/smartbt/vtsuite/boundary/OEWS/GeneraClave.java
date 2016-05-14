
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
 *         &lt;element name="cadena" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Datos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Corresponsales" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ruta_ejecutar" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "cadena",
    "datos",
    "corresponsales",
    "rutaEjecutar"
})
@XmlRootElement(name = "GeneraClave")
public class GeneraClave {

    protected String cadena;
    @XmlElement(name = "Datos")
    protected String datos;
    @XmlElement(name = "Corresponsales")
    protected String corresponsales;
    @XmlElement(name = "ruta_ejecutar")
    protected String rutaEjecutar;

    /**
     * Gets the value of the cadena property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCadena() {
        return cadena;
    }

    /**
     * Sets the value of the cadena property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCadena(String value) {
        this.cadena = value;
    }

    /**
     * Gets the value of the datos property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDatos() {
        return datos;
    }

    /**
     * Sets the value of the datos property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatos(String value) {
        this.datos = value;
    }

    /**
     * Gets the value of the corresponsales property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCorresponsales() {
        return corresponsales;
    }

    /**
     * Sets the value of the corresponsales property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCorresponsales(String value) {
        this.corresponsales = value;
    }

    /**
     * Gets the value of the rutaEjecutar property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRutaEjecutar() {
        return rutaEjecutar;
    }

    /**
     * Sets the value of the rutaEjecutar property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRutaEjecutar(String value) {
        this.rutaEjecutar = value;
    }

}
