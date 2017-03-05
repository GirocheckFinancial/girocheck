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
@XmlRootElement(name = "LIST")
public class EstadoList{

    List<Estado> list = new ArrayList<Estado>();

    public EstadoList() {
    }
    
    public EstadoList(String xml) {
        xml = "<LIST>" + xml + "</LIST>";
        System.out.println("parsing: " + xml);
        EstadoList _this =  (EstadoList) JAXB.unmarshal(new StringReader(xml), EstadoList.class);
        if(_this != null){
         setList(_this.getList());   
        }
    }

    public List<Estado> getList() {
        return list;
    }

    @XmlElement(name = "ESTADO")
    public void setList( List<Estado> list) {
        this.list = list;
    }

}
