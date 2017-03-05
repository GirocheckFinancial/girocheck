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
@XmlRootElement(name = "LISTAR_CORRESPONSALES")
public class ListarCorresponsalesRequest {

    private String agenciaOrigen;
    private String pais;
    private Double monto;

    public String toXML() {
        StringWriter sw = new StringWriter();
        JAXB.marshal(this, sw);
        return sw.toString().replace("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>", "").trim();
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
    @XmlElement(name = "AGENCIA_ORIGEN")
    public void setAgenciaOrigen(String agenciaOrigen) {
        this.agenciaOrigen = agenciaOrigen;
    }

    /**
     * @return the monto
     */
    public Double getMonto() {
        return monto;
    }

    /**
     * @param monto the monto to set
     */
    @XmlElement(name = "MONTO")
    public void setMonto(Double monto) {
        this.monto = monto;
    }

    /**
     * @return the pais
     */
    public String getPais() {
        return pais;
    }

    /**
     * @param pais the pais to set
     */
    @XmlElement(name = "PAIS")
    public void setPais(String pais) {
        this.pais = pais;
    }
}
