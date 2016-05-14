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

public class State implements Serializable {

    public State() {
    }
    
     public State( int idState ) {
        this.id = idState;
    }

    private int id;

    private String name;

    private String abbreviation;

    private int code;

    private java.util.Set<com.smartbt.girocheck.servercommon.model.Address> address = new java.util.HashSet<com.smartbt.girocheck.servercommon.model.Address>();

    private java.util.Set<com.smartbt.girocheck.servercommon.model.PersonalIdentification> personalIdentification = new java.util.HashSet<com.smartbt.girocheck.servercommon.model.PersonalIdentification>();

   

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

    public void setPersonalIdentification( java.util.Set<com.smartbt.girocheck.servercommon.model.PersonalIdentification> value ) {
        this.personalIdentification = value;
    }

    public java.util.Set<com.smartbt.girocheck.servercommon.model.PersonalIdentification> getPersonalIdentification() {
        return personalIdentification;
    }

    public String toString() {
        return String.valueOf( getId() );
    }

    public void setCode( int code ) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
