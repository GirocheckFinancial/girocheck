/*
 @Autor: Roberto Rodriguez
 Email: robertoSoftwareEngineer@gmail.com

 @Copyright 2016 
 */
package com.alodiga.persistence.dto;

import java.io.StringWriter;
import javax.xml.bind.JAXB;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rrodriguez
 */
@XmlRootElement(name = "COTIZAR")
public class CotizarRequest{
    private String monto;
    private String agenciaOrigen;
    private String codCorresponsal;
    private String codFormaPago;
    private String incluyeComision;
    
    
    public CotizarRequest() {
    }

    public CotizarRequest(String monto, String agenciaOrigen, String codCorresponsal, String codFormaPago, String incluyeComision) {
        this.monto = monto;
        this.agenciaOrigen = agenciaOrigen;
        this.codCorresponsal = codCorresponsal;
        this.codFormaPago = codFormaPago;
        this.incluyeComision = incluyeComision;
    } 
    
     public String toXML() {
        StringWriter sw = new StringWriter();
        JAXB.marshal(this, sw);
        return sw.toString().replace("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>", "").trim();
    }
 
    /**
     * @return the monto
     */
    public String getMonto() {
        return monto;
    }

    /**
     * @param monto the monto to set
     */
     @XmlElement( name = "MONTO" ) 
    public void setMonto(String monto) {
        this.monto = monto;
    }

    /**
     * @return the agenciaOrigen
     */
    public String getAgenciaOrigen() {
        return agenciaOrigen;
    }

    @XmlElement( name = "AGENCIA_ORIGEN" ) 
    public void setAgenciaOrigen(String agenciaOrigen) {
        this.agenciaOrigen = agenciaOrigen;
    }

    /**
     * @return the codCorresponsal
     */
    public String getCodCorresponsal() {
        return codCorresponsal;
    }

     @XmlElement( name = "COD_CORRESPONSAL" )
    public void setCodCorresponsal(String codCorresponsal) {
        this.codCorresponsal = codCorresponsal;
    }

    /**
     * @return the codFormaPago
     */
    public String getCodFormaPago() {
        return codFormaPago;
    }

    /**
     * @param codFormaPago the codFormaPago to set
     */
    @XmlElement( name = "COD_FORMA_PAGO" )
    public void setCodFormaPago(String codFormaPago) {
        this.codFormaPago = codFormaPago;
    }

    /**
     * @return the incluyeComision
     */
    public String getIncluyeComision() {
        return incluyeComision;
    }

    /**
     * @param incluyeComision the incluyeComision to set
     */
    @XmlElement( name = "INCLUYE_COMISION" ) 
    public void setIncluyeComision(String incluyeComision) {
        this.incluyeComision = incluyeComision;
    }
    
    
    
}