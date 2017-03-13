package com.smartbt.girocheck.servercommon.display.mobile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;

/**
 *
 * @author rrodriguez
 */
public class MobileClientDisplay implements Serializable {

    private Integer clientId;
    @JsonIgnore
    private String card;
    private String token;
    private String balance;
    private String clientName;
    private String clientEmail;
    private String clientPhone;
    private String mobileClientUserName;
 

    /**
     * @return the card
     */
    public String getCard() {
        return card;
    }

    /**
     * @param card the card to set
     */
    public void setCard(String card) {
        this.card = card;
    }

    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return the balance
     */
    public String getBalance() {
        return balance;
    }

    /**
     * @param balance the balance to set
     */
    public void setBalance(String balance) {
        this.balance = balance;
    }

    /**
     * @return the clientId
     */
    public Integer getClientId() {
        return clientId;
    }

    /**
     * @param clientId the clientId to set
     */
    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    /**
     * @return the clientName
     */
    public String getClientName() {
        return clientName;
    }

    /**
     * @param clientName the clientName to set
     */
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    /**
     * @return the clientEmail
     */
    public String getClientEmail() {
        return clientEmail;
    }

    /**
     * @param clientEmail the clientEmail to set
     */
    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    /**
     * @return the clientPhone
     */
    public String getClientPhone() {
        return clientPhone;
    }

    /**
     * @param clientPhone the clientPhone to set
     */
    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    /**
     * @return the mobileClientUserName
     */
    public String getMobileClientUserName() {
        return mobileClientUserName;
    }

    /**
     * @param mobileClientUserName the mobileClientUserName to set
     */
    public void setMobileClientUserName(String mobileClientUserName) {
        this.mobileClientUserName = mobileClientUserName;
    }

    

    
}
