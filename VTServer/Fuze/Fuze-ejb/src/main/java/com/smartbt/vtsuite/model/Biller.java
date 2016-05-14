package com.smartbt.vtsuite.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Alejo
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "biller", propOrder = {
    "id",
    "velocities",
    "name",
    "address",
    "city",
    "state",
    "zip",
    "type",
    "estimated_posting_time"
})
public class Biller implements Serializable{
    @XmlElement(name = "id")
    protected int id;
    @XmlElement(name = "velocities")
    protected int velocities;
    @XmlElement(name = "name")
    protected String name;
    @XmlElement(name = "address")
    protected String address;
    @XmlElement(name = "city")
    protected String city;
    @XmlElement(name = "state")
    protected String state;
    @XmlElement(name = "zip")
    protected String zip;
    @XmlElement(name = "type")
    protected int type;
    @XmlElement(name = "estimated_posting_time")
    private String estimated_posting_time;

    public Biller(){}
    
    public Map toMap() {
//        Map map = super.getMap(EXPECTED_RESULT_CODE);
        Map map = new HashMap();
//        map.put(ParameterName.CURRENCY_CODE, currencyCode);
//        map.put(ParameterName.CURRENCY_SYMBOL, currencySymbol);
//        map.put(ParameterName.CURRENCY_NAME, currencyName);
//        map.put(ParameterName.INITIAL_BALANCE, initialBalance);
//        map.put(ParameterName.FINAL_BALANCE, finalBalance);
//        if (transactionsList != null) {
//            map.put(ParameterName.TRANSACTIONS_LIST, transactionsList.toMap());
//        }
        return map;
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
     * @return the velocities
     */
    public int getVelocities() {
        return velocities;
    }

    /**
     * @param velocities the velocities to set
     */
    public void setVelocities(int velocities) {
        this.velocities = velocities;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the zip
     */
    public String getZip() {
        return zip;
    }

    /**
     * @param zip the zip to set
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * @return the estimated_posting_time
     */
    public String getEstimated_posting_time() {
        return estimated_posting_time;
    }

    /**
     * @param estimated_posting_time the estimated_posting_time to set
     */
    public void setEstimated_posting_time(String estimated_posting_time) {
        this.estimated_posting_time = estimated_posting_time;
    }
    
    
    
}
