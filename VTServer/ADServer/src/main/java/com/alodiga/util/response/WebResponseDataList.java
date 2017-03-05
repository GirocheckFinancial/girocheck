/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.alodiga.util.response;

import java.util.Collection;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Roberto Rodriguez   :: <roberto.rodriguez@smartbt.com>
 */
@XmlRootElement
public class WebResponseDataList<T> extends WebResponse {

    private List<T> list;
    private Integer totalElements; 
    public WebResponseDataList() {
    }

    public WebResponseDataList(List<T> data) {
        super();
        this.list = data; 
    }
 
    public WebResponseDataList(List<T> data, Integer totalElements) {
        this(data); 
        this.totalElements = totalElements;
    }
 

    /**
     * @return the totalElements
     */
    public Integer getTotalElements() {
        return totalElements;
    }

    /**
     * @param totalElements the totalElements to set
     */
    public void setTotalElements(Integer totalElements) {
        this.totalElements = totalElements;
    }

    /**
     * @return the list
     */
    public List<T> getList() {
        return list;
    }

    /**
     * @param list the list to set
     */
    public void setList(List<T> list) {
        this.list = list;
    }
}