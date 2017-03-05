/*
 @Autor: Roberto Rodriguez
 Email: robertoSoftwareEngineer@gmail.com

 @Copyright 2016 
 */
package com.alodiga.persistence.dto;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXB;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rrodriguez
 */
@XmlRootElement(name = "CORRESPONSALES")
public class CorresponsalList {

    private List<Corresponsal> corresponsales = new ArrayList<Corresponsal>();

    public CorresponsalList() {
    }

    public CorresponsalList(List<Corresponsal> corresponsales) {
        this.corresponsales = corresponsales;
    }


    /**
     * @return the corresponsales
     */
    public List<Corresponsal> getCorresponsales() {
        return corresponsales;
    }

    /**
     * @param corresponsales the corresponsales to set
     */
    @XmlElement(name = "CORRESPONSAL")
    public void setCorresponsales(List<Corresponsal> corresponsales) {
        System.out.println("CorresponsalList -> setCorresponsales");
        this.corresponsales = corresponsales;
    }

}
