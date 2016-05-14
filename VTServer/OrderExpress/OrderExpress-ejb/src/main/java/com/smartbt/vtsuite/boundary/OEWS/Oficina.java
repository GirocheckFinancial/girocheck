
package com.smartbt.vtsuite.boundary.OEWS;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Oficina complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Oficina">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id_player" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="id_teller" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Oficina", propOrder = {
    "idPlayer",
    "idTeller"
})
public class Oficina {

    @XmlElement(name = "id_player")
    protected int idPlayer;
    @XmlElement(name = "id_teller")
    protected int idTeller;

    /**
     * Gets the value of the idPlayer property.
     * 
     */
    public int getIdPlayer() {
        return idPlayer;
    }

    /**
     * Sets the value of the idPlayer property.
     * 
     */
    public void setIdPlayer(int value) {
        this.idPlayer = value;
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

}
