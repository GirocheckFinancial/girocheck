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
import java.util.Date;

public class Check implements Serializable {

    public Check() {
    }
    private int id;
    private com.smartbt.girocheck.servercommon.model.Client client1;
    private com.smartbt.girocheck.servercommon.model.Transaction transaction;
    private String code;
    private java.sql.Blob checkBack;
    private java.sql.Blob checkFront;
    private String micr;
    private String crc;
    private String key;
    private String makerName;
    private String makerAddress;
    private String makerCity;
    private String makerState;
    private String makerZip;
    private String makerPhone;
    private String locationId;
    private String paymentCheck;
    private String status;
    private Date creationDate;
    private Date processingDate;

    private void setId(int value) {
        this.id = value;
    }

    public int getId() {
        return id;
    }

    public int getORMID() {
        return getId();
    }

    public void setCode(String value) {
        this.code = value;
    }

    public String getCode() {
        return code;
    }

    public void setCheckBack(java.sql.Blob value) {
        this.checkBack = value;
    }

    public java.sql.Blob getCheckBack() {
        return checkBack;
    }

    public void setCheckFront(java.sql.Blob value) {
        this.checkFront = value;
    }

    public java.sql.Blob getCheckFront() {
        return checkFront;
    }

    public void setMicr(String value) {
        this.micr = value;
    }

    public String getMicr() {
        return micr;
    }

    public void setCrc(String value) {
        this.crc = value;
    }

    public String getCrc() {
        return crc;
    }

    public void setKey(String value) {
        this.key = value;
    }

    public String getKey() {
        return key;
    }

    public void setMakerName(String value) {
        this.makerName = value;
    }

    public String getMakerName() {
        return makerName;
    }

    public void setMakerAddress(String value) {
        this.makerAddress = value;
    }

    public String getMakerAddress() {
        return makerAddress;
    }

    public void setMakerCity(String value) {
        this.makerCity = value;
    }

    public String getMakerCity() {
        return makerCity;
    }

    public void setMakerState(String value) {
        this.makerState = value;
    }

    public String getMakerState() {
        return makerState;
    }

    public void setMakerZip(String value) {
        this.makerZip = value;
    }

    public String getMakerZip() {
        return makerZip;
    }

    public void setMakerPhone(String value) {
        this.makerPhone = value;
    }

    public String getMakerPhone() {
        return makerPhone;
    }

    public void setLocationId(String value) {
        this.locationId = value;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setClient1(com.smartbt.girocheck.servercommon.model.Client value) {
        this.client1 = value;
    }

    public com.smartbt.girocheck.servercommon.model.Client getClient1() {
        return client1;
    }

    public void setTransaction(com.smartbt.girocheck.servercommon.model.Transaction value) {
        this.transaction = value;
    }

    public com.smartbt.girocheck.servercommon.model.Transaction getTransaction() {
        return transaction;
    }

    public String toString() {
        return String.valueOf(getId());
    }

    /**
     * @return the paymentCheck
     */
    public String getPaymentCheck() {
        return paymentCheck;
    }

    /**
     * @param paymentCheck the paymentCheck to set
     */
    public void setPaymentCheck(String paymentCheck) {
        this.paymentCheck = paymentCheck;
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
     * @return the creationDate
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * @param creationDate the creationDate to set
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * @return the processingDate
     */
    public Date getProcessingDate() {
        return processingDate;
    }

    /**
     * @param processingDate the processingDate to set
     */
    public void setProcessingDate(Date processingDate) {
        this.processingDate = processingDate;
    }
}
