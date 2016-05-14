
package com.smartbt.vtsuite.boundary.OEWS;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ObtenUltimaFac complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ObtenUltimaFac">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="pIdControlApp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pIdRemitente" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="pIdBeneficiario" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ObtenUltimaFac", propOrder = {
    "pIdControlApp",
    "pIdRemitente",
    "pIdBeneficiario"
})
public class ObtenUltimaFac {

    protected String pIdControlApp;
    protected int pIdRemitente;
    protected int pIdBeneficiario;

    /**
     * Gets the value of the pIdControlApp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPIdControlApp() {
        return pIdControlApp;
    }

    /**
     * Sets the value of the pIdControlApp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPIdControlApp(String value) {
        this.pIdControlApp = value;
    }

    /**
     * Gets the value of the pIdRemitente property.
     * 
     */
    public int getPIdRemitente() {
        return pIdRemitente;
    }

    /**
     * Sets the value of the pIdRemitente property.
     * 
     */
    public void setPIdRemitente(int value) {
        this.pIdRemitente = value;
    }

    /**
     * Gets the value of the pIdBeneficiario property.
     * 
     */
    public int getPIdBeneficiario() {
        return pIdBeneficiario;
    }

    /**
     * Sets the value of the pIdBeneficiario property.
     * 
     */
    public void setPIdBeneficiario(int value) {
        this.pIdBeneficiario = value;
    }

}
