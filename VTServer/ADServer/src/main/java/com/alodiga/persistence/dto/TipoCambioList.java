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
public class TipoCambioList{

    List<TipoCambio> list = new ArrayList<TipoCambio>();

    public TipoCambioList() {
    }
    
    public TipoCambioList(String xml) {
        xml = "<LIST>" + xml + "</LIST>";
        System.out.println("parsing: " + xml);
        TipoCambioList _this =  (TipoCambioList) JAXB.unmarshal(new StringReader(xml), TipoCambioList.class);
        if(_this != null){
         setList(_this.getList());   
        }
    }

    public List<TipoCambio> getList() {
        return list;
    }

    @XmlElement(name = "TIPO_CAMBIO")
    public void setList( List<TipoCambio> list) {
        this.list = list;
    }

}
