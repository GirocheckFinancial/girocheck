
package com.smartbt.vtsuite.boundary.OEWS;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ConsultaSaldoResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ConsultaSaldoResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="store_code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fecha" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="concepto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="debito" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="credito" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="balance" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="balanceIni" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="balanceFec" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="fehaAnt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConsultaSaldoResult", propOrder = {
    "storeCode",
    "fecha",
    "concepto",
    "debito",
    "credito",
    "balance",
    "balanceIni",
    "balanceFec",
    "fehaAnt"
})
public class ConsultaSaldoResult {

    @XmlElement(name = "store_code")
    protected String storeCode;
    protected String fecha;
    protected String concepto;
    protected double debito;
    protected double credito;
    protected double balance;
    protected double balanceIni;
    protected double balanceFec;
    protected String fehaAnt;

    /**
     * Gets the value of the storeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStoreCode() {
        return storeCode;
    }

    /**
     * Sets the value of the storeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStoreCode(String value) {
        this.storeCode = value;
    }

    /**
     * Gets the value of the fecha property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * Sets the value of the fecha property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFecha(String value) {
        this.fecha = value;
    }

    /**
     * Gets the value of the concepto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConcepto() {
        return concepto;
    }

    /**
     * Sets the value of the concepto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConcepto(String value) {
        this.concepto = value;
    }

    /**
     * Gets the value of the debito property.
     * 
     */
    public double getDebito() {
        return debito;
    }

    /**
     * Sets the value of the debito property.
     * 
     */
    public void setDebito(double value) {
        this.debito = value;
    }

    /**
     * Gets the value of the credito property.
     * 
     */
    public double getCredito() {
        return credito;
    }

    /**
     * Sets the value of the credito property.
     * 
     */
    public void setCredito(double value) {
        this.credito = value;
    }

    /**
     * Gets the value of the balance property.
     * 
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Sets the value of the balance property.
     * 
     */
    public void setBalance(double value) {
        this.balance = value;
    }

    /**
     * Gets the value of the balanceIni property.
     * 
     */
    public double getBalanceIni() {
        return balanceIni;
    }

    /**
     * Sets the value of the balanceIni property.
     * 
     */
    public void setBalanceIni(double value) {
        this.balanceIni = value;
    }

    /**
     * Gets the value of the balanceFec property.
     * 
     */
    public double getBalanceFec() {
        return balanceFec;
    }

    /**
     * Sets the value of the balanceFec property.
     * 
     */
    public void setBalanceFec(double value) {
        this.balanceFec = value;
    }

    /**
     * Gets the value of the fehaAnt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFehaAnt() {
        return fehaAnt;
    }

    /**
     * Sets the value of the fehaAnt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFehaAnt(String value) {
        this.fehaAnt = value;
    }

}
