/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.alodiga.util.response;
 
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Roberto Rodriguez   :: <roberto.rodriguez@smartbt.com>
 */
@XmlRootElement
public class ReporteWebResponseDataList<T> extends WebResponseDataList<T> {
 
    private Double sumaDineroEntregado;
    private Double sumaTotalPagar;
    private Double sumaComision;
    public ReporteWebResponseDataList() {
    }

    public ReporteWebResponseDataList(List<T> data) {
        super(data); 
    }
 
    public ReporteWebResponseDataList(List<T> data, Integer totalElements) {
        super(data, totalElements); 
    }

    public ReporteWebResponseDataList(List<T> list, Integer totalElements, Double sumaDineroEntregado, Double sumaTotalPagar, Double sumaComision) {
        super(list, totalElements);  
        this.sumaDineroEntregado = sumaDineroEntregado;
        this.sumaTotalPagar = sumaTotalPagar;
        this.sumaComision = sumaComision;
    }
     
    public Integer getTotalElements() {
        return super.getTotalElements();
    }
 
    public List<T> getList() {
        return super.getList();
    }
  
    /**
     * @return the sumaComision
     */
    public Double getSumaComision() {
        return sumaComision;
    }

    /**
     * @param sumaComision the sumaComision to set
     */
    public void setSumaComision(Double sumaComision) {
        this.sumaComision = sumaComision;
    }

    /**
     * @return the sumaDineroEntregado
     */
    public Double getSumaDineroEntregado() {
        return sumaDineroEntregado;
    }

    /**
     * @param sumaDineroEntregado the sumaDineroEntregado to set
     */
    public void setSumaDineroEntregado(Double sumaDineroEntregado) {
        this.sumaDineroEntregado = sumaDineroEntregado;
    }

    /**
     * @return the sumaTotalPagar
     */
    public Double getSumaTotalPagar() {
        return sumaTotalPagar;
    }

    /**
     * @param sumaTotalPagar the sumaTotalPagar to set
     */
    public void setSumaTotalPagar(Double sumaTotalPagar) {
        this.sumaTotalPagar = sumaTotalPagar;
    }
 
}