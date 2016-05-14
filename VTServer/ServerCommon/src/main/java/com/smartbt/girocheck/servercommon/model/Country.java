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

public class Country implements Serializable {

    public Country() {
    }
    
    public Country(int id) {
        this.id = id;
    }

    private int id;

    private String name;

    private String abbreviation;

    private Integer code;

    private java.util.Set<com.smartbt.girocheck.servercommon.model.Address> address = new java.util.HashSet<com.smartbt.girocheck.servercommon.model.Address>();

    private void setId( int value ) {
        this.id = value;
    }

    public int getId() {
        return id;
    }

    public int getORMID() {
        return getId();
    }

    public void setName( String value ) {
        this.name = value;
    }

    public String getName() {
        return name;
    }

    public void setAbbreviation( String value ) {
        this.abbreviation = value;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAddress( java.util.Set<com.smartbt.girocheck.servercommon.model.Address> value ) {
        this.address = value;
    }

    public java.util.Set<com.smartbt.girocheck.servercommon.model.Address> getAddress() {
        return address;
    }

    public String toString() {
        return String.valueOf( getId() );
    }

    public void setCode( Integer code ) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

}
