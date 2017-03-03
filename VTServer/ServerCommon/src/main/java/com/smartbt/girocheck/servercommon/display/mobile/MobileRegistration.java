package com.smartbt.girocheck.servercommon.display.mobile;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author suresh
 */

@XmlRootElement
public class MobileRegistration implements Serializable{
    
    private static long serialVersionUID = 1L;

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @param aSerialVersionUID the serialVersionUID to set
     */
    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }
    
    private String  clientId;
    private String  token;
    private String  balance;
    private Boolean registered;
    
    public MobileRegistration() {
    }

    public MobileRegistration(String clientId, String token, String balance,Boolean registered) {
        this.clientId = clientId;
        this.token = token;
        this.balance = balance;
        this.registered = registered;
    }

    /**
     * @return the clientId
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * @param clientId the clientId to set
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
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
     * @return the registered
     */
    public Boolean getRegistered() {
        return registered;
    }

    /**
     * @param registered the registered to set
     */
    public void setRegistered(Boolean registered) {
        this.registered = registered;
    }
    
}
