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
@XmlRootElement(name = "FORMAS_PAGO")
public class FormaEntregaList {

    private List<FormaEntrega> formasEntrega = new ArrayList<FormaEntrega>();

    public FormaEntregaList() {
    }

    public FormaEntregaList(List<FormaEntrega> formasEntrega) {
        this.formasEntrega = formasEntrega;
    }
 

    /**
     * @return the formasEntrega
     */
    public List<FormaEntrega> getFormasEntrega() {
        return formasEntrega;
    }

    /**
     * @param formasEntrega the formasEntrega to set
     */
       @XmlElement(name = "FORMA_PAGO")
    public void setFormasEntrega(List<FormaEntrega> formasEntrega) {
        this.formasEntrega = formasEntrega;
    }

}
