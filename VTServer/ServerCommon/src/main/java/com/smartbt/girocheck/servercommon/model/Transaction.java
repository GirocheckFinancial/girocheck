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

import com.smartbt.girocheck.servercommon.enums.TransactionType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class Transaction implements Serializable {

    public Transaction() {
    }

    private int id;

    private com.smartbt.girocheck.servercommon.model.Terminal terminal;

    private com.smartbt.girocheck.servercommon.model.Client client;

    private String operation;

    private Integer resultCode;

    private String resultMessage;

    private Date dateTime;

    private com.smartbt.girocheck.servercommon.model.CreditCard data_sc1;

    private Integer transactionType;

    private String key;

    private String account;

    private String requestId;

    private String istream_id;

    private Boolean single;

    private String errorCode;

    private String orderExpressId;

    private boolean transactionFinished;

//    private java.sql.Blob achForm;//quitar ach form
    private java.sql.Blob truncatedCheck;

    private Double ammount;
    private Double feeAmmount;
    private Double payoutAmmount;
//    private String cardNumber;

    private Boolean cancelated;
    private Boolean cancelable = true;
    
    private String balanceAfterLoad;

    private java.util.Set<com.smartbt.girocheck.servercommon.model.SubTransaction> sub_Transaction = new java.util.HashSet<com.smartbt.girocheck.servercommon.model.SubTransaction>();

    private com.smartbt.girocheck.servercommon.model.Check check;
     

    public void addSubTransaction(SubTransaction subTransaction) {
        if (sub_Transaction.isEmpty()) {
            subTransaction.setOrder(1);
        } else {
            ArrayList<SubTransaction> curentList = new ArrayList<SubTransaction>(sub_Transaction);
            Collections.sort(curentList);
            subTransaction.setOrder(curentList.get(curentList.size() - 1).getOrder() + 1);

        }

        System.out.println("Transaction inserting subtransaction :: " + TransactionType.get(subTransaction.getType()));
        subTransaction.setTransaction(this);
        sub_Transaction.add(subTransaction);

    }

    public void addSubTransactionList(Set<SubTransaction> list) {
        if (list == null) {
            return;
        }

        List<SubTransaction> subtransactions = new ArrayList<SubTransaction>(list);
        Collections.sort(subtransactions);

        for (SubTransaction subTransaction : subtransactions) {
            if (sub_Transaction.isEmpty()) {
                subTransaction.setOrder(1);
            } else {
                ArrayList<SubTransaction> curentList = new ArrayList<SubTransaction>(sub_Transaction);
                Collections.sort(curentList);
                subTransaction.setOrder(curentList.get(curentList.size() - 1).getOrder() + 1);
            }

            subTransaction.setTransaction(this);
            System.out.println("Transaction inserting subtransaction :: " + TransactionType.get(subTransaction.getType()));
            sub_Transaction.add(subTransaction);
        }
    }
    
    public boolean containSubTransaction(TransactionType type){
        for (SubTransaction sub_Transaction1 : sub_Transaction) {
            if(sub_Transaction1.getType() == type.getCode()){
                return true;
            }
        }
        return false;
    }

    public void setCancelable(Boolean cancelable) {
        this.cancelable = cancelable;
    }

    public Boolean isCancelable() {
        return cancelable;
    }

    public void setCancelated(Boolean cancelated) {
        this.cancelated = cancelated;
    }

    public Boolean isCancelated() {
        return cancelated;
    }

    public void setTruncatedCheck(java.sql.Blob truncatedCheck) {
        this.truncatedCheck = truncatedCheck;
    }

    public java.sql.Blob getTruncatedCheck() {
        return truncatedCheck;
    }

    //quitar ach form
//    public void setAchForm(java.sql.Blob value) {
//		this.achForm = value;
//	}
//	public java.sql.Blob getAchForm() {
//		return achForm;
//	}
    private void setId(int value) {
        this.id = value;
    }

    public int getId() {
        return id;
    }

//    public void setCardNumber(String cardNumber) {
//        this.cardNumber = cardNumber;
//    }
//
//    public String getCardNumber() {
//        return cardNumber;
//    }

    public int getORMID() {
        return getId();
    }

    public void setOperation(String value) {
        this.operation = value;
    }

    public String getOperation() {
        return operation;
    }

    public void setResultCode(int value) {
        setResultCode(new Integer(value));
    }

    public void setResultCode(Integer value) {
        this.resultCode = value;
    }

    public Integer getResultCode() {
        return resultCode;
    }

    public void setResultMessage(String value) {
        if (value != null && value.length() > 254) {
            value = value.substring(0, 254);
        }
        this.resultMessage = value;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setDateTime(Date value) {
        this.dateTime = value;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setTransactionType(int value) {
        setTransactionType(new Integer(value));
    }

    public void setTransactionType(Integer value) {
        this.transactionType = value;
    }

    public Integer getTransactionType() {
        return transactionType;
    }

    public void setKey(String value) {
        this.key = value;
    }

    public String getKey() {
        return key;
    }

    public void setAccount(String value) {
        this.account = value;
    }

    public String getAccount() {
        return account;
    }

    public void setRequestId(String value) {
        this.requestId = value;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setIstream_id(String value) {
        this.istream_id = value;
    }

    public String getIstream_id() {
        return istream_id;
    }

    public void setSingle(boolean value) {
        setSingle(new Boolean(value));
    }

    public void setSingle(Boolean value) {
        this.single = value;
    }

    public Boolean getSingle() {
        return single;
    }

    public void setErrorCode(String value) {
        this.errorCode = value;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setOrderExpressId(String value) {
        this.orderExpressId = value;
    }

    public String getOrderExpressId() {
        return orderExpressId;
    }

    public void setTerminal(com.smartbt.girocheck.servercommon.model.Terminal value) {
        this.terminal = value;
    }

    public com.smartbt.girocheck.servercommon.model.Terminal getTerminal() {
        return terminal;
    }

    public void setClient(com.smartbt.girocheck.servercommon.model.Client value) {
        this.client = value;
    }

    public com.smartbt.girocheck.servercommon.model.Client getClient() {
        return client;
    }

    public void setData_sc1(com.smartbt.girocheck.servercommon.model.CreditCard value) {
        this.data_sc1 = value;
    }

    public com.smartbt.girocheck.servercommon.model.CreditCard getData_sc1() {
        return data_sc1;
    }

    public void setSub_Transaction(java.util.Set<com.smartbt.girocheck.servercommon.model.SubTransaction> value) {
        this.sub_Transaction = value;
    }

    public java.util.Set<com.smartbt.girocheck.servercommon.model.SubTransaction> getSub_Transaction() {
        return sub_Transaction;
    }

    public void setCheck(com.smartbt.girocheck.servercommon.model.Check value) {
        this.check = value;
    }

    public com.smartbt.girocheck.servercommon.model.Check getCheck() {
        return check;
    }

    public String toString() {
        return String.valueOf(getId());
    }

    /**
     * @return the ammount
     */
    public Double getAmmount() {
        return ammount;
    }

    /**
     * @param ammount the ammount to set
     */
    public void setAmmount(Double ammount) {
        this.ammount = ammount;
    }

    /**
     * @return the feeAmmount
     */
    public Double getFeeAmmount() {
        return feeAmmount;
    }

    /**
     * @param feeAmmount the feeAmmount to set
     */
    public void setFeeAmmount(Double feeAmmount) {
        this.feeAmmount = feeAmmount;
    }

    /**
     * @return the payoutAmmount
     */
    public Double getPayoutAmmount() {
        return payoutAmmount;
    }

    /**
     * @param payoutAmmount the payoutAmmount to set
     */
    public void setPayoutAmmount(Double payoutAmmount) {
        this.payoutAmmount = payoutAmmount;
    }

    /**
     * @return the transactionFinished
     */
    public boolean isTransactionFinished() {
        return transactionFinished;
    }

    /**
     * @param transactionFinished the transactionFinished to set
     */
    public void setTransactionFinished(boolean transactionFinished) {
        this.transactionFinished = transactionFinished;
    }

    /**
     * @return the afterLoadBalance
     */
    public String getBalanceAfterLoad() {
        return balanceAfterLoad;
    }

    /**
     * @param afterLoadBalance the afterLoadBalance to set
     */
    public void setBalanceAfterLoad(String afterLoadBalance) {
        this.balanceAfterLoad = afterLoadBalance;
    }
     
}
