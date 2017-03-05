/*
 @Autor: Roberto Rodriguez
 Email: robertoSoftwareEngineer@gmail.com

 @Copyright 2016 
 */
package com.alodiga.persistence.dto;

import java.io.StringReader;
import javax.xml.bind.JAXB;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rrodriguez
 */
@XmlRootElement(name = "RCOTIZAR")
public class CotizarResult {
    private Double tarifa;
    private Double tasaDeCambio;
    private Double montoEntregar;
    private Double dineroEntregado;
    private Double totalPagar;

   
    
    public CotizarResult() {
    }

    public CotizarResult(Double tarifa, Double tasaDeCambio) {
        this.tarifa = tarifa;
        this.tasaDeCambio = tasaDeCambio;
    }

     public static CotizarResult getFromXML(String xml) {
        if(xml == null || xml.isEmpty()){
            return new CotizarResult();
        }
        return (CotizarResult) JAXB.unmarshal(new StringReader(xml), CotizarResult.class);
    }
    /**
     * @return the tarifa
     */
    public Double getTarifa() {
        return tarifa;
    }

     @XmlElement( name = "TARIFA" ) 
    public void setTarifa(Double tarifa) {
        this.tarifa = tarifa;
    }

    /**
     * @return the tasaDeCambio
     */
    public Double getTasaDeCambio() {
        return tasaDeCambio;
    }

     @XmlElement( name = "TIPO_CAMBIO" )
    public void setTasaDeCambio(Double tasaDeCambio) {
        this.tasaDeCambio = tasaDeCambio;
    }

    /**
     * @return the montoEntregar
     */
    public Double getMontoEntregar() {
        return montoEntregar;
    }
 
    /**
     * @param montoEntregar the montoEntregar to set
     */
      @XmlElement( name = "MONTO_ENTREGAR" )
    public void setMontoEntregar(Double montoEntregar) {
        this.montoEntregar = montoEntregar;
    }

    /**
     * @return the dineroEntregado
     */
    public Double getDineroEntregado() {
        return dineroEntregado;
    }

     @XmlElement( name = "DINERO_ENTREGADO" )
    public void setDineroEntregado(Double dineroEntregado) {
        this.dineroEntregado = dineroEntregado;
    }

    /**
     * @return the totalPagar
     */
    public Double getTotalPagar() {
        return totalPagar;
    }

    @XmlElement( name = "TOTAL_PAGAR" )
    public void setTotalPagar(Double totalPagar) {
        this.totalPagar = totalPagar;
    }
}
