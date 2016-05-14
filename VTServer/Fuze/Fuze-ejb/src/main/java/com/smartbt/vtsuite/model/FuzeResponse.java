/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartbt.vtsuite.model;

import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.utils.IMap;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Alejo
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "status",
    "message",
    
    "data",
    "time"
})
@XmlRootElement( name = "xml" )//header of the xml
public class FuzeResponse implements IMap{
    
    @XmlElement(name = "status")
    protected String status;
    @XmlElement(name = "message")
    protected String message;
    
    @XmlElement(name = "data")
    protected Data data;
    @XmlElement(name = "time")
    protected String time;

    public FuzeResponse(){};

    @Override
    public Map toMap() {
        Map map = new HashMap();

        map.put(ParameterName.STATUS, status);
        map.put(ParameterName.MESSAGE, message);
        if (status.equals("100")) {
            map.put(ParameterName.BILLER_ID, data.getBillers().get(0).getId());
            map.put(ParameterName.TIME, data.getBillers().get(0).getEstimated_posting_time());
            map.put(ParameterName.FUZE_TRANSACTION_STATUS, data.getStatus());
        }
        return map;

    }

    public Map toMapProcessPayment() {
        Map map = new HashMap();

        map.put(ParameterName.STATUS, status);
        map.put(ParameterName.MESSAGE, message);
        if (status.equals("100")) {
            map.put(ParameterName.RECEIPT_TEXT, data.getReceipt_text());
            map.put(ParameterName.PENDING_BALANCE, data.getPending_balance());
            map.put(ParameterName.TIME, data.getEstimated_posting_time());
        }
        return map;

    }
    
    public Map toMapLookUpTransaction() {
        Map map = new HashMap();

        map.put(ParameterName.STATUS, status);
        map.put(ParameterName.MESSAGE, message);
        if (status.equals("100")) {
            map.put(ParameterName.FUZE_TRANSACTION_STATUS, data.getStatus());
            map.put(ParameterName.AMMOUNT, data.getAmount());
        }
        return map;

    }
    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the time
     */
    public String getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * @return the data
     */
    public Data getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Data data) {
        this.data = data;
    }
    
    

}
