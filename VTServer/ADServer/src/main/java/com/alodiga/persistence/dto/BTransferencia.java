/*
 @Autor: Roberto Rodriguez
 Email: robertoSoftwareEngineer@gmail.com

 @Copyright 2016 
 */
package com.alodiga.persistence.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rrodriguez
 */
@XmlRootElement(name = "BTRANSFERENCIA")
public class BTransferencia extends Transferencia {

    private String fechaDel;
    private String fechaAl;
    private String nombreR;
    private String nombreD;
    private String agenciaOrigen;
    private Integer page;
    private Integer limit;

    public BTransferencia() {
    }

    public BTransferencia(String fechaDel, String fechaAl, String nombreR, String nombreD, String agenciaOrigen, Integer page, Integer limit) {
        this.fechaDel = get(fechaDel);
        this.fechaAl = get(fechaAl);
        this.nombreR = get(nombreR);
        this.nombreD = get(nombreD);
        this.agenciaOrigen = get(agenciaOrigen);
        this.page = page;
        this.limit = limit;
    }

    private String get(String prop) {
        if (prop == null || prop.trim().isEmpty()) {
            prop = "";
        }
        return prop;
    }

    /**
     * @return the fechaDel
     */
    public String getFechaDel() {
        return fechaDel;
    }

    /**
     * @param fechaDel the fechaDel to set
     */
    @XmlElement(name = "FECHADEL")
    public void setFechaDel(String fechaDel) {
        this.fechaDel = fechaDel;
    }

    /**
     * @return the fechaAl
     */
    public String getFechaAl() {
        return fechaAl;
    }

    /**
     * @param fechaAl the fechaAl to set
     */
    @XmlElement(name = "FECHAAL")
    public void setFechaAl(String fechaAl) {
        this.fechaAl = fechaAl;
    }

    /**
     * @return the nombreR
     */
    public String getNombreR() {
        return nombreR;
    }

    /**
     * @param nombreR the nombreR to set
     */
    @XmlElement(name = "NOMBRER")
    public void setNombreR(String nombreR) {
        this.nombreR = nombreR;
    }

    /**
     * @return the nombreD
     */
    public String getNombreD() {
        return nombreD;
    }

    /**
     * @param nombreD the nombreD to set
     */
    @XmlElement(name = "NOMBRED")
    public void setNombreD(String nombreD) {
        this.nombreD = nombreD;
    }

    /**
     * @return the agenciaOrigen
     */
    public String getAgenciaOrigen() {
        return agenciaOrigen;
    }

    @XmlElement(name = "AGENCIA_ORIGEN")
    public void setAgenciaOrigen(String agenciaOrigen) {
        this.agenciaOrigen = agenciaOrigen;
    }

    /**
     * @return the page
     */
    public Integer getPage() {
        return page;
    }

    /**
     * @param page the page to set
     */
    @XmlElement(name = "PAGE")
    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    @XmlElement(name = "LIMIT")
    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
