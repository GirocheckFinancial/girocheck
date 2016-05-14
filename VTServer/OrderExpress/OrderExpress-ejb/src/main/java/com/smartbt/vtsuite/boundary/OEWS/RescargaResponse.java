
package com.smartbt.vtsuite.boundary.OEWS;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RescargaResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RescargaResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id_RecargaResp" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="id_StatusResp" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="id_errorResp" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="comentarioResp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rsp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="msj" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Factura" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id_MarcaResp" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RescargaResponse", propOrder = {
    "idRecargaResp",
    "idStatusResp",
    "idErrorResp",
    "comentarioResp",
    "rsp",
    "msj",
    "factura",
    "idMarcaResp"
})
public class RescargaResponse {

    @XmlElement(name = "id_RecargaResp")
    protected int idRecargaResp;
    @XmlElement(name = "id_StatusResp")
    protected int idStatusResp;
    @XmlElement(name = "id_errorResp")
    protected int idErrorResp;
    protected String comentarioResp;
    protected String rsp;
    protected String msj;
    @XmlElement(name = "Factura")
    protected String factura;
    @XmlElement(name = "id_MarcaResp")
    protected int idMarcaResp;

    /**
     * Gets the value of the idRecargaResp property.
     * 
     */
    public int getIdRecargaResp() {
        return idRecargaResp;
    }

    /**
     * Sets the value of the idRecargaResp property.
     * 
     */
    public void setIdRecargaResp(int value) {
        this.idRecargaResp = value;
    }

    /**
     * Gets the value of the idStatusResp property.
     * 
     */
    public int getIdStatusResp() {
        return idStatusResp;
    }

    /**
     * Sets the value of the idStatusResp property.
     * 
     */
    public void setIdStatusResp(int value) {
        this.idStatusResp = value;
    }

    /**
     * Gets the value of the idErrorResp property.
     * 
     */
    public int getIdErrorResp() {
        return idErrorResp;
    }

    /**
     * Sets the value of the idErrorResp property.
     * 
     */
    public void setIdErrorResp(int value) {
        this.idErrorResp = value;
    }

    /**
     * Gets the value of the comentarioResp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComentarioResp() {
        return comentarioResp;
    }

    /**
     * Sets the value of the comentarioResp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComentarioResp(String value) {
        this.comentarioResp = value;
    }

    /**
     * Gets the value of the rsp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRsp() {
        return rsp;
    }

    /**
     * Sets the value of the rsp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRsp(String value) {
        this.rsp = value;
    }

    /**
     * Gets the value of the msj property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMsj() {
        return msj;
    }

    /**
     * Sets the value of the msj property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMsj(String value) {
        this.msj = value;
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
     * Gets the value of the idMarcaResp property.
     * 
     */
    public int getIdMarcaResp() {
        return idMarcaResp;
    }

    /**
     * Sets the value of the idMarcaResp property.
     * 
     */
    public void setIdMarcaResp(int value) {
        this.idMarcaResp = value;
    }

}
