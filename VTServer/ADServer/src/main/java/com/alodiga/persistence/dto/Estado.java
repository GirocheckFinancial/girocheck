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
@XmlRootElement(name = "ESTADO")
public class Estado extends Nomemclator{

    public Estado() {
    }

    public Estado(String nombre, String codigo) {
        super(nombre, codigo);
    }
    
     @XmlElement( name = "NOMBRE" ) 
    public void setNombre(String nombre) {
        super.setNombre(nombre);
    }
 
    /**
     * @param codigo the codigo to set
     */
    @XmlElement( name = "CODIGO" ) 
    public void setCodigo(String codigo) {
        super.setCodigo(codigo);
    }
}