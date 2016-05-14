/*
 ** File: AddressDisplay.java
 **
 ** Date Created: February 2013
 **
 ** Copyright @ 2004-2013 Smart Business Technology, Inc.
 **
 ** All rights reserved. No part of this software may be 
 ** reproduced, transmitted, transcribed, stored in a retrieval 
 ** system, or translated into any language or computer language, 
 ** in any form or by any means, electronic, mechanical, magnetic, 
 ** optical, chemical, manual or otherwise, without the prior 
 ** written permission of Smart Business Technology, Inc.
 **
 */
package com.smartbt.girocheck.servercommon.display;

import com.smartbt.girocheck.servercommon.display.StateDisplay;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Address Display Class - containing all sets/gets
 */
@XmlRootElement
public class AddressDisplay implements Serializable {

    private Integer id;
    private String address;
    private String city;
    private CountryDisplay country;
    private String zip;
    private StateDisplay state;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId( Integer id ) {
        this.id = id;
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
    public void setAddress( String address ) {
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
    public void setCity( String city ) {
        this.city = city;
    }

    public void setCountry( CountryDisplay country ) {
        this.country = country;
    }

    public CountryDisplay getCountry() {
        return country;
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
    public void setZip( String zip ) {
        this.zip = zip;
    }

    public void setState( StateDisplay state ) {
        this.state = state;
    }

    public StateDisplay getState() {
        return state;
    }

    

   
    
}
