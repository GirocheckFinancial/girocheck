package com.smartbt.girocheck.servercommon.model;

import java.io.Serializable;
import java.sql.Blob;

/**
 *
 * @author Alejo
 */


public class AchCard implements Serializable{
    
    private int id;
    private Merchant merchant;
    private String cardNumber;
    private Blob achform;
    private com.smartbt.girocheck.servercommon.model.CreditCard data_sc1;
    
    public AchCard(){}
    
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
    public Merchant getMerchant() {
        return merchant;
    }

    /**
     * @param merchant the merchant to set
     */
    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    /**
     * @return the cardNumber
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * @param cardNumber the cardNumber to set
     */
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    /**
     * @return the achform
     */
    public Blob getAchform() {
        return achform;
    }

    /**
     * @param achform the achform to set
     */
    public void setAchform(Blob achform) {
        this.achform = achform;
    }

    /**
     * @return the data_sc1
     */
    public com.smartbt.girocheck.servercommon.model.CreditCard getData_sc1() {
        return data_sc1;
    }

    /**
     * @param data_sc1 the data_sc1 to set
     */
    public void setData_sc1(com.smartbt.girocheck.servercommon.model.CreditCard data_sc1) {
        this.data_sc1 = data_sc1;
    }

    
}
