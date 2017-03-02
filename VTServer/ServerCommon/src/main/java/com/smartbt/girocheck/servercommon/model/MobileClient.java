package com.smartbt.girocheck.servercommon.model;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author suresh
 */

//@XmlRootElement
public class MobileClient implements Serializable {
    public MobileClient(){
        
    }
    
     private int id;
    
     private com.smartbt.girocheck.servercommon.model.Client client;
    
     private String deviceType;
    
     private String forgotPasswordKey;
     
     private com.smartbt.girocheck.servercommon.model.CreditCard card;
     
     private Timestamp registrationDate;
     
     private Timestamp keyExpirationTime;
     
     private String userName;
     
     private String password;
     
     private String pin;
     
     private String deviceId;
     
     


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
     * @return the deviceType
     */
    public String getDeviceType() {
        return deviceType;
    }

    /**
     * @param deviceType the deviceType to set
     */
    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    /**
     * @return the ForgotPasswordKey
     */
    public String getForgotPasswordKey() {
        return forgotPasswordKey;
    }

    /**
     * @param forgotPasswordKey the ForgotPasswordKey to set
     */
    public void setForgotPasswordKey(String forgotPasswordKey) {
        this.forgotPasswordKey = forgotPasswordKey;
    }

    /**
     * @return the card
     */
    public com.smartbt.girocheck.servercommon.model.CreditCard getCard() {
        return card;
    }

    /**
     * @param card the card to set
     */
    public void setCard(com.smartbt.girocheck.servercommon.model.CreditCard card) {
        this.card = card;
    }

    /**
     * @return the registrationDate
     */
    public Timestamp getRegistrationDate() {
        return registrationDate;
    }

    /**
     * @param registrationDate the registrationDate to set
     */
    public void setRegistrationDate(Timestamp registrationDate) {
        this.registrationDate = registrationDate;
    }

    /**
     * @return the client
     */
    public com.smartbt.girocheck.servercommon.model.Client getClient() {
        return client;
    }

    /**
     * @param client the client to set
     */
    public void setClient(com.smartbt.girocheck.servercommon.model.Client client) {
        this.client = client;
    }

    /**
     * @return the keyExpirationTime
     */
    public Timestamp getKeyExpirationTime() {
        return keyExpirationTime;
    }

    /**
     * @param keyExpirationTime the keyExpirationTime to set
     */
    public void setKeyExpirationTime(Timestamp keyExpirationTime) {
        this.keyExpirationTime = keyExpirationTime;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the pin
     */
    public String getPin() {
        return pin;
    }

    /**
     * @param pin the pin to set
     */
    public void setPin(String pin) {
        this.pin = pin;
    }

    /**
     * @return the deviceId
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * @param deviceId the deviceId to set
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
    
}
