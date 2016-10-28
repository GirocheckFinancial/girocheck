/**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 *
 * This is an automatic generated file. It will be regenerated every time you
 * generate persistence class.
 *
 * Modifying its content may cause the program not work, or your work may lost.
 */
/**
 * Licensee: License Type: Evaluation
 */
package com.smartbt.girocheck.servercommon.model;

import java.io.Serializable;

public class CreditCard implements Serializable {

    public CreditCard() {        
    }

    private int id;

    private com.smartbt.girocheck.servercommon.model.CardProgram card_program;

    private String cardNumber;
    
    private String maskCardNumber;

    private String key;
     
    private com.smartbt.girocheck.servercommon.model.CardType card_type;

    private com.smartbt.girocheck.servercommon.model.Client client;

    private com.smartbt.girocheck.servercommon.model.Merchant merchant;

    private Integer cardStatus;
    
    private java.util.Set<com.smartbt.girocheck.servercommon.model.Transaction> transaction1 = new java.util.HashSet<com.smartbt.girocheck.servercommon.model.Transaction>();

    private void setId( int value ) {
        this.id = value;
    }

    public int getId() {
        return id;
    }

    public int getORMID() {
        return getId();
    }

    public void setCardNumber( String value ) {
        this.cardNumber = value;
    }
    
    public String getCardNumber() {
        return cardNumber;
    }

    public void setKey( String value ) {
        this.key = value;
    }

    public String getKey() {
        return key;
    }

    public void setCardStatus( Integer cardStatus ) {
        this.cardStatus = cardStatus;
    }

    public Integer getCardStatus() {
        return cardStatus;
    }

    public void setCard_program( com.smartbt.girocheck.servercommon.model.CardProgram value ) {
        this.card_program = value;
    }

    public com.smartbt.girocheck.servercommon.model.CardProgram getCard_program() {
        return card_program;
    }

    public void setCard_type( com.smartbt.girocheck.servercommon.model.CardType value ) {
        this.card_type = value;
    }

    public com.smartbt.girocheck.servercommon.model.CardType getCard_type() {
        return card_type;
    }

    public void setClient( com.smartbt.girocheck.servercommon.model.Client value ) {
        this.client = value;
    }

    public com.smartbt.girocheck.servercommon.model.Client getClient() {
        return client;
    }

    public void setMerchant( com.smartbt.girocheck.servercommon.model.Merchant value ) {
        this.merchant = value;
    }

    public com.smartbt.girocheck.servercommon.model.Merchant getMerchant() {
        return merchant;
    }

    public void setTransaction1( java.util.Set<com.smartbt.girocheck.servercommon.model.Transaction> value ) {
        this.transaction1 = value;
    }

    public java.util.Set<com.smartbt.girocheck.servercommon.model.Transaction> getTransaction1() {
        return transaction1;
    }

    public String toString() {
        return String.valueOf( getId() );
    }

    /**
     * @return the maskCardNumber
     */
    public String getMaskCardNumber() {
        return maskCardNumber;
    }

    /**
     * @param maskCardNumber the maskCardNumber to set
     */
    public void setMaskCardNumber(String maskCardNumber) {
        this.maskCardNumber = maskCardNumber;
    } 
 
    }
