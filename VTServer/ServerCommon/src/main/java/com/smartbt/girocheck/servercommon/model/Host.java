package com.smartbt.girocheck.servercommon.model;

import java.io.Serializable;

/**
 *
 * @author Alejo
 */


public class Host implements Serializable{
    
    private int id;
    private String binNumber;
    private String hostName;
    private boolean defaultHost;
    private String hostCode;
    
    public Host(){}

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
     * @return the binNumber
     */
    public String getBinNumber() {
        return binNumber;
    }

    /**
     * @param binNumber the binNumber to set
     */
    public void setBinNumber(String binNumber) {
        this.binNumber = binNumber;
    }

    /**
     * @return the hostName
     */
    public String getHostName() {
        return hostName;
    }

    /**
     * @param hostName the hostName to set
     */
    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    /**
     * @return the defaultHost
     */
    public boolean isDefaultHost() {
        return defaultHost;
    }

    /**
     * @param defaultHost the defaultHost to set
     */
    public void setDefaultHost(boolean defaultHost) {
        this.defaultHost = defaultHost;
    }

    /**
     * @return the hostCode
     */
    public String getHostCode() {
        return hostCode;
    }

    /**
     * @param hostCode the hostCode to set
     */
    public void setHostCode(String hostCode) {
        this.hostCode = hostCode;
    }
    
    
}
