/*
 @Autor: Roberto Rodriguez
 Email: robertoSoftwareEngineer@gmail.com

 @Copyright 2016 
 */
package com.alodiga.persistence.dto;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rrodriguez
 */
@XmlRootElement(name = "PAIS")
public class Pais extends Nomemclator {

//    private String moneda;
//     private List<Corresponsal> corresponsales = new ArrayList<Corresponsal>();
   @XmlElement(name = "CORRESPONSALES")
    private CorresponsalList corresponsales;

    public Pais() {
    }

    public Pais(String nombre, String codigo, String moneda) {
        this(nombre, codigo);
//        this.moneda = moneda;
    }

    public Pais(String nombre, String codigo) {
        super(nombre, codigo);
    }

    @XmlElement(name = "NOMBRE_PAIS")
    public void setNombre(String nombre) {
        System.out.println("setNombre = " + nombre);
        super.setNombre(nombre);
    }

    /**
     * @param codigo the codigo to set
     */
    @XmlElement(name = "CODIGO_PAIS")
    public void setCodigo(String codigo) {
        System.out.println("setCodigo = " + codigo);
        super.setCodigo(codigo);
    }

//    /**
//     * @return the moneda
//     */
//    public String getMoneda() {
//        return moneda;
//    }
//
//    /**
//     * @param moneda the moneda to set
//     */
//    public void setMoneda(String moneda) {
//        this.moneda = moneda;
//    }
    /**
     * @return the corresponsales
     */
//    public List<Corresponsal> getCorresponsales() {
//        return corresponsales;
//    }
    /**
     * @param corresponsales the corresponsales to set
     */
//    @ElementList(required=true, inline=true)
//    public void setCorresponsales(List<Corresponsal> corresponsales) {
//        this.corresponsales = corresponsales;
//    }
    public List<Corresponsal> getCorresponsales() {
        System.out.println("corresponsales == null  = " + corresponsales == null);
        if (corresponsales != null) {
            System.out.println("tiene " + corresponsales.getCorresponsales().size());
            return corresponsales.getCorresponsales();
        } else {
            return null;
        }
    }

    /**
     * @param corresponsales the corresponsales to set
     */
//    @XmlElement(name = "CORRESPONSALES")
//    public void setCorresponsales(String corresponsalesString) {
//        System.out.println("corresponsalesString = " + corresponsalesString);
//        List<Corresponsal> corresponsales = null;
//        this.corresponsales = new CorresponsalList(corresponsales);
//   //     this.corresponsales = corresponsales;
//    }
    @XmlElement(name = "CORRESPONSALES")
    public void setCorresponsales(CorresponsalList corresponsales) {
        System.out.println("corresponsalesString = ");
        //  this.corresponsales = corresponsales;
    }

}
