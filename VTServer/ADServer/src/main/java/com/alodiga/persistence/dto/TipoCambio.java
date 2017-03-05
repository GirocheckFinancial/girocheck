/*
 @Autor: Roberto Rodriguez
 Email: robertoSoftwareEngineer@gmail.com

 @Copyright 2016 
 */
package com.alodiga.persistence.dto;

import java.io.StringWriter;
import javax.xml.bind.JAXB;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rrodriguez
 */
@XmlRootElement(name = "TIPO_CAMBIO")
public class TipoCambio {
    private String agenciaOrigen;
    private String tipoCorresponsal;
    private Double valor;

    public TipoCambio() {
    }

    
    
    public TipoCambio(String tipoCorresponsal, Double valor) {
        this.tipoCorresponsal = tipoCorresponsal;
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "tipoCorresponsal = " + tipoCorresponsal + ", valor = " + valor;
    }
public String toXML(){
    StringWriter sw = new StringWriter();
        JAXB.marshal(this, sw);
      return sw.toString().replace("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>", "").trim();
}
    
    
    /**
     * @return the tipoCorresponsal
     */
    
    public String getTipoCorresponsal() {
        return tipoCorresponsal;
    }

    /**
     * @param tipoCorresponsal the tipoCorresponsal to set
     */
     @XmlElement( name = "TIPO_CORRESPONSAL" ) 
    public void setTipoCorresponsal(String tipoCorresponsal) {
        this.tipoCorresponsal = tipoCorresponsal;
    }

    /**
     * @return the valor
     */
    public Double getValor() {
        return valor;
    }

    @XmlElement( name = "VALOR" ) 
    public void setValor(Double valor) {
        this.valor = valor;
    }

    /**
     * @return the agenciaOrigen
     */
    public String getAgenciaOrigen() {
        return agenciaOrigen;
    }

    /**
     * @param agenciaOrigen the agenciaOrigen to set
     */
    @XmlElement( name = "AGENCIA_ORIGEN" )
    public void setAgenciaOrigen(String agenciaOrigen) {
        this.agenciaOrigen = agenciaOrigen;
    }
    
    
}
