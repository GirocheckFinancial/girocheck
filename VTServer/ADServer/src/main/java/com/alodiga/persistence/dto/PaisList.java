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
@XmlRootElement(name = "PAISES")
public class PaisList{

    List<Pais> list = new ArrayList<Pais>();

    public PaisList() {
    }
    
    public PaisList(String xml) { 
        System.out.println("parsing: " + xml);
        PaisList _this =  (PaisList) JAXB.unmarshal(new StringReader(xml), PaisList.class);
        if(_this != null){
         setList(_this.getList());   
        }
    }

    public List<Pais> getList() {
        return list;
    }

    @XmlElement(name = "PAIS")
    public void setList( List<Pais> list) {
        this.list = list;
    }

}
