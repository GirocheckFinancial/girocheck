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

    
}
