package com.smartbt.girocheck.servercommon.model;

import java.io.Serializable;

/**
 *
 * @author Alejo
 */


public class FeeSchedules implements Serializable{
    
    private int id;
    private TransactionMethod method;
    private int merchant;
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
     * @return the merchant
     */
    public int getMerchant() {
        return merchant;
    }

    /**
     * @param merchant the merchant to set
     */
    public void setMerchant(int merchant) {
        this.merchant = merchant;
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
    
}
