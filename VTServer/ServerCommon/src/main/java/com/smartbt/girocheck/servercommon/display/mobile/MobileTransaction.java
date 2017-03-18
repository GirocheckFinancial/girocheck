package com.smartbt.girocheck.servercommon.display.mobile;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Transaction Display Class - containing all sets/gets
 */
@XmlRootElement
public class MobileTransaction implements Serializable {

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

    private String type;
    private String date;
    private String description;
    private String amount;
    private String debitOrCredit;
    private String fee;
    private String statusCode;

    public MobileTransaction() {
    }

    public MobileTransaction(String type, String date, String amount, String fee, String debitOrCredit, String statusCode, String description) {
        this.type = type;
        this.date = date;
        this.description = description;
        this.amount = amount;
        this.fee = fee;
        this.debitOrCredit = debitOrCredit;
    } 

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the amount
     */
    public String getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }

    /**
     * @return the debitOrCredit
     */
    public String getDebitOrCredit() {
        return debitOrCredit;
    }

    /**
     * @param debitOrCredit the debitOrCredit to set
     */
    public void setDebitOrCredit(String debitOrCredit) {
        this.debitOrCredit = debitOrCredit;
    }

    /**
     * @return the fee
     */
    public String getFee() {
        return fee;
    }

    /**
     * @param fee the fee to set
     */
    public void setFee(String fee) {
        this.fee = fee;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the statusCode
     */
    public String getStatusCode() {
        return statusCode;
    }

    /**
     * @param statusCode the statusCode to set
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
}
