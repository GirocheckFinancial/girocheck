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
import java.sql.SQLException;

public class PersonalIdentification implements Serializable {

    public PersonalIdentification() {
    }

    private int id;

    private com.smartbt.girocheck.servercommon.model.Client client;

    private Integer idType;

    private com.smartbt.girocheck.servercommon.model.State state;

    private java.util.Date expirationDate;

    private java.sql.Blob idFront;

    private java.sql.Blob idBack;

    private String identification;

    private String key;

    private void setId(int value) {
        this.id = value;
    }

    public int getId() {
        return id;
    }

    public int getORMID() {
        return getId();
    }

    public void setIdType(int value) {
        setIdType(new Integer(value));
    }

    public void setIdType(Integer value) {
        this.idType = value;
    }

    public Integer getIdType() {
        return idType;
    }

    public void setExpirationDate(java.util.Date value) {
        this.expirationDate = value;
    }

    public java.util.Date getExpirationDate() {
        return expirationDate;
    }

    public void setIdFront(java.sql.Blob value) {
        this.idFront = value;
    }

    public java.sql.Blob getIdFront() {
        return idFront;
    }

//    public byte[] getIdFrontAsByteArray() throws SQLException {
//        try {
//            if (idFront == null || idFront.length() <= 0) {
//                return null;
//            }
//            int length = (int) idFront.length();
//            return idFront.getBytes(0, length);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//
//    }

    public void setIdBack(java.sql.Blob value) {
        this.idBack = value;
    }

//    public byte[] getIdBackAsByteArray() throws SQLException {
//        try {
//            if (idBack == null || idBack.length() <= 0) {
//                return null;
//            }
//            int length = (int) idBack.length();
//            return idBack.getBytes(0, length);
//        } catch (Exception e) {
//             e.printStackTrace();
//            return null;
//        }
//    }

    public java.sql.Blob getIdBack() {
        return idBack;
    }

    public void setIdentification(String value) {
        this.identification = value;
    }

    public String getIdentification() {
        return identification;
    }

    public void setKey(String value) {
        this.key = value;
    }

    public String getKey() {
        return key;
    }

    public void setClient(com.smartbt.girocheck.servercommon.model.Client value) {
        this.client = value;
    }

    public com.smartbt.girocheck.servercommon.model.Client getClient() {
        return client;
    }

    public void setState(com.smartbt.girocheck.servercommon.model.State value) {
        this.state = value;
    }

    public com.smartbt.girocheck.servercommon.model.State getState() {
        return state;
    }

    public String toString() {
        return String.valueOf(getId());
    }

}
