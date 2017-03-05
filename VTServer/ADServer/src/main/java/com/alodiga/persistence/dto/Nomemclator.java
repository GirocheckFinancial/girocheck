/*
 @Autor: Roberto Rodriguez
 Email: robertoSoftwareEngineer@gmail.com

 @Copyright 2016 
 */
package com.alodiga.persistence.dto;

import javax.xml.bind.annotation.XmlElement; 

/**
 *
 * @author rrodriguez
 */

public class Nomemclator{
    private String nombre;
    private String codigo;

    public Nomemclator() {
    }

    public Nomemclator(String nombre, String codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
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
  
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}