
package com.smartbt.vtsuite.boundary.OEWS;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Rescarga complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Rescarga">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="accion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id_recarga" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="telefono" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id_pais" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="id_marca" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="id_productos" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="id_cliente" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="id_teller" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="msjFactura" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id_error" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="comentario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Rescarga", propOrder = {
    "accion",
    "idRecarga",
    "telefono",
    "idPais",
    "idMarca",
    "idProductos",
    "idCliente",
    "idTeller",
    "msjFactura",
    "idError",
    "comentario"
})
public class Rescarga {

    protected String accion;
    @XmlElement(name = "id_recarga")
    protected String idRecarga;
    protected String telefono;
    @XmlElement(name = "id_pais")
    protected int idPais;
    @XmlElement(name = "id_marca")
    protected int idMarca;
    @XmlElement(name = "id_productos")
    protected int idProductos;
    @XmlElement(name = "id_cliente")
    protected int idCliente;
    @XmlElement(name = "id_teller")
    protected int idTeller;
    protected String msjFactura;
    @XmlElement(name = "id_error")
    protected int idError;
    protected String comentario;

    /**
     * Gets the value of the accion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccion() {
        return accion;
    }

    /**
     * Sets the value of the accion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccion(String value) {
        this.accion = value;
    }

    /**
     * Gets the value of the idRecarga property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdRecarga() {
        return idRecarga;
    }

    /**
     * Sets the value of the idRecarga property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdRecarga(String value) {
        this.idRecarga = value;
    }

    /**
     * Gets the value of the telefono property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Sets the value of the telefono property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelefono(String value) {
        this.telefono = value;
    }

    /**
     * Gets the value of the idPais property.
     * 
     */
    public int getIdPais() {
        return idPais;
    }

    /**
     * Sets the value of the idPais property.
     * 
     */
    public void setIdPais(int value) {
        this.idPais = value;
    }

    /**
     * Gets the value of the idMarca property.
     * 
     */
    public int getIdMarca() {
        return idMarca;
    }

    /**
     * Sets the value of the idMarca property.
     * 
     */
    public void setIdMarca(int value) {
        this.idMarca = value;
    }

    /**
     * Gets the value of the idProductos property.
     * 
     */
    public int getIdProductos() {
        return idProductos;
    }

    /**
     * Sets the value of the idProductos property.
     * 
     */
    public void setIdProductos(int value) {
        this.idProductos = value;
    }

    /**
     * Gets the value of the idCliente property.
     * 
     */
    public int getIdCliente() {
        return idCliente;
    }

    /**
     * Sets the value of the idCliente property.
     * 
     */
    public void setIdCliente(int value) {
        this.idCliente = value;
    }

    /**
     * Gets the value of the idTeller property.
     * 
     */
    public int getIdTeller() {
        return idTeller;
    }

    /**
     * Sets the value of the idTeller property.
     * 
     */
    public void setIdTeller(int value) {
        this.idTeller = value;
    }

    /**
     * Gets the value of the msjFactura property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMsjFactura() {
        return msjFactura;
    }

    /**
     * Sets the value of the msjFactura property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMsjFactura(String value) {
        this.msjFactura = value;
    }

    /**
     * Gets the value of the idError property.
     * 
     */
    public int getIdError() {
        return idError;
    }

    /**
     * Sets the value of the idError property.
     * 
     */
    public void setIdError(int value) {
        this.idError = value;
    }

    /**
     * Gets the value of the comentario property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * Sets the value of the comentario property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComentario(String value) {
        this.comentario = value;
    }

}
