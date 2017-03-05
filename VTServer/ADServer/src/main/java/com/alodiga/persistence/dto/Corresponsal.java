/*
 @Autor: Roberto Rodriguez
 Email: robertoSoftwareEngineer@gmail.com

 @Copyright 2016 
 */
package com.alodiga.persistence.dto;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rrodriguez
 */
@XmlRootElement(name = "CORRESPONSAL")
public class Corresponsal {

    private Double tipoCambio;
    private Double tarifa;
    private String nombre;
    private String codigo;
    private String moneda;

    private FormaEntregaList formasEntrega;

    public Corresponsal() {
    }

    public Corresponsal(String nombre, String codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
    }

    public Corresponsal(Double tipoCambio, Double tarifa, String nombre, String codigo) {
        this(nombre, codigo);
        this.tipoCambio = tipoCambio;
        this.tarifa = tarifa;
    }

    /**
     * @return the moneda
     */
    public String getMoneda() {
        return moneda;
    }

    /**
     * @param moneda the moneda to set
     */
    @XmlElement(name = "MONEDA")
    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    /**
     * @return the tipoCambio
     */
    public Double getTipoCambio() {
        return tipoCambio;
    }

    /**
     * @param tipoCambio the tipoCambio to set
     */
    @XmlElement(name = "TIPO_CAMBIO")
    public void setTipoCambio(Double tipoCambio) {
        this.tipoCambio = tipoCambio;
    }

    /**
     * @return the tarifa
     */
    public Double getTarifa() {
        return tarifa;
    }

    /**
     * @param tarifa the tarifa to set
     */
    @XmlElement(name = "TARIFA")
    public void setTarifa(Double tarifa) {
        this.tarifa = tarifa;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    @XmlElement(name = "NOMBRE_CORRESPONSAL")
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    @XmlElement(name = "COD_CORRESPONSAL")
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the formaEntregaList
     */
    public List<FormaEntrega> getFormaEntregaList() {
        if(formasEntrega != null){
            return formasEntrega.getFormasEntrega();
        }
        return null;
    }
//
//    /**
//     * @param formaEntregaList the formaEntregaList to set
//     */
//    @XmlElement(name = "FORMAS_PAGO")
//    public void setFormaEntregaList(FormaEntregaList formaEntregaList) {
//        this.formaEntregaList = formaEntregaList;
//    }

    /**
     * @return the formasEntrega
     */
//    public FormaEntregaList getFormasEntrega() {
//        return formasEntrega;
//    }

    /**
     * @param formasEntrega the formasEntrega to set
     */
     @XmlElement(name = "FORMAS_PAGO")
    public void setFormasEntrega(FormaEntregaList formasEntrega) {
        this.formasEntrega = formasEntrega;
    }
}
