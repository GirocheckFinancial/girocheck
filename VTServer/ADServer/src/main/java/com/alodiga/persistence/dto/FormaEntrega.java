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
@XmlRootElement(name = "FORMAS_PAGO")
public class FormaEntrega {
    private String nombre;
    private String codigo;

    public FormaEntrega() {
    }

    public FormaEntrega(String nombre) {
        this.nombre = nombre;
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
   // 
    @XmlElement(name = "NOMBRE_FORMA_PAGO")
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
    @XmlElement(name = "COD_FORMA_PAGO")
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    
}
