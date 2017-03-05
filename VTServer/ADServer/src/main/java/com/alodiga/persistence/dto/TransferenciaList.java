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
public class TransferenciaList{

    List<Transferencia> list = new ArrayList<Transferencia>();

    public TransferenciaList() {
    }
    
    public TransferenciaList(String xml) {
        xml = "<LIST>" + xml + "</LIST>";
        System.out.println("parsing: " + xml);
        TransferenciaList _this =  (TransferenciaList) JAXB.unmarshal(new StringReader(xml), TransferenciaList.class);
        if(_this != null){
         setList(_this.getList());   
        }
    }

    public List<Transferencia> getList() {
        return list;
    }

    @XmlElement(name = "TRANSFERENCIA")
    public void setList( List<Transferencia> list) {
        this.list = list;
    }

}
