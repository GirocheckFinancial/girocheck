package com.smartbt.girocheck.servercommon.model;

import java.io.Serializable;

/**
 *
 * @author Alejo
 */


public class FeeSchedules implements Serializable{
    
    private int id;
    private TransactionMethod method;
    private Integer merchant;
    private Boolean isdefault;
    
    public FeeSchedules() {
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
 

    /**
     * @return the method
     */
    public TransactionMethod getMethod() {
        return method;
    }

    /**
     * @param method the method to set
     */
    public void setMethod(TransactionMethod method) {
        this.method = method;
    }

    /**
     * @return the isdefault
     */
    public Boolean getIsdefault() {
        return isdefault;
    }

    /**
     * @param isdefault the isdefault to set
     */
    public void setIsdefault(Boolean isdefault) {
        this.isdefault = isdefault;
    }

    /**
     * @return the merchant
     */
    public Integer getMerchant() {
        return merchant;
    }

    /**
     * @param merchant the merchant to set
     */
    public void setMerchant(Integer merchant) {
        this.merchant = merchant;
    }
    
}
