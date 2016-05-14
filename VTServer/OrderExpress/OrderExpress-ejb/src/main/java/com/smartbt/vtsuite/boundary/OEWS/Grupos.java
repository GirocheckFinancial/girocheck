
package com.smartbt.vtsuite.boundary.OEWS;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for grupos complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="grupos">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id_grupo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="id_producto" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "grupos", propOrder = {
    "idGrupo",
    "idProducto",
    "descripcion"
})
public class Grupos {

    @XmlElement(name = "id_grupo")
    protected int idGrupo;
    @XmlElement(name = "id_producto")
    protected int idProducto;
    protected String descripcion;

    /**
     * Gets the value of the idGrupo property.
     * 
     */
    public int getIdGrupo() {
        return idGrupo;
    }

    /**
     * Sets the value of the idGrupo property.
     * 
     */
    public void setIdGrupo(int value) {
        this.idGrupo = value;
    }

    /**
     * Gets the value of the idProducto property.
     * 
     */
    public int getIdProducto() {
        return idProducto;
    }

    /**
     * Sets the value of the idProducto property.
     * 
     */
    public void setIdProducto(int value) {
        this.idProducto = value;
    }

    /**
     * Gets the value of the descripcion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Sets the value of the descripcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcion(String value) {
        this.descripcion = value;
    }

}
