/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartbt.vtsuite.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Alejo
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "data", propOrder = {
    "count",
    "billers",
    
    "transaction_id",
    "timestamp",
    "reference",
    "receipt_text",
    "estimated_posting_time",
    "pending_balance",
        
    "status",
    "amount"
})
public class Data implements Serializable{
    
    //Data from BillerSearchMethod
    @XmlElement(name = "count")
    protected int count;
    
    @XmlElementWrapper(name = "billers")
    @XmlElement(name = "biller")
    protected ArrayList<Biller> billers;
    
    //Data from PaymentMethod
    @XmlElement(name = "transaction_id")
    protected String transaction_id;
    @XmlElement(name = "timestamp")
    protected String timestamp;
    @XmlElement(name = "reference")
    protected String reference;
    @XmlElement(name = "receipt_text")
    protected String receipt_text;
    @XmlElement(name = "pending_balance")
    protected String pending_balance;
    @XmlElement(name = "estimated_posting_time")
    protected String estimated_posting_time;
    
    //Data from LookUpMethod
    @XmlElement(name = "status")
    private String status;
    @XmlElement(name = "amount")
    private String amount;
    
    
    public Data(){}

    /**
     * @return the count
     */
    public int getCount() {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * @return the transaction_id
     */
    public String getTransaction_id() {
        return transaction_id;
    }

    /**
     * @param transaction_id the transaction_id to set
     */
    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    /**
     * @return the timestamp
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp the timestamp to set
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * @return the reference
     */
    public String getReference() {
        return reference;
    }

    /**
     * @param reference the reference to set
     */
    public void setReference(String reference) {
        this.reference = reference;
    }

    /**
     * @return the receipt_text
     */
    public String getReceipt_text() {
        return receipt_text;
    }

    /**
     * @param receipt_text the receipt_text to set
     */
    public void setReceipt_text(String receipt_text) {
        this.receipt_text = receipt_text;
    }

    /**
     * @return the pending_balance
     */
    public String getPending_balance() {
        return pending_balance;
    }

    /**
     * @param pending_balance the pending_balance to set
     */
    public void setPending_balance(String pending_balance) {
        this.pending_balance = pending_balance;
    }

    /**
     * @return the billers
     */
    public ArrayList<Biller> getBillers() {
        return billers;
    }

    /**
     * @param billers the billers to set
     */
    public void setBillers(ArrayList<Biller> billers) {
        this.billers = billers;
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

}
