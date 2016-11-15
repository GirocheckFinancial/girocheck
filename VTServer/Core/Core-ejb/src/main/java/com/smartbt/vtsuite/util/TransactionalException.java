/*
 @Autor: Roberto Rodriguez
 Email: robertoSoftwareEngineer@gmail.com

 @Copyright 2016 
 */
package com.smartbt.vtsuite.util;

import com.smartbt.girocheck.servercommon.enums.ResultCode;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;

/**
 *
 * @author rrodriguez
 */
public class TransactionalException extends Exception{ 
    private DirexTransactionResponse response;
    private TransactionType transactionType; 
    private ResultCode resultCode;

    // this is when  !response.approved()
    public TransactionalException(DirexTransactionResponse response) {
        this.resultCode = response.getResultCode(); 
        this.response = response;
        this.transactionType = response.getTransactionType(); 
    }
     
     //this is when caughting Exception
    public TransactionalException(ResultCode resultCode, TransactionType transactionType, Exception e) {
        this( resultCode,  transactionType,  e.getMessage());
        e.printStackTrace();
    }
    

     //this is when want to throw an Exception
    public TransactionalException(ResultCode resultCode, TransactionType transactionType, String message) {
        super(message);
        this.resultCode = resultCode; 
        this.response = null;
        this.transactionType = transactionType; 
    }

      

     
     

    /**
     * @return the response
     */
    public DirexTransactionResponse getResponse() {
        return response;
    }

    /**
     * @param response the response to set
     */
    public void setResponse(DirexTransactionResponse response) {
        this.response = response;
    }

    /**
     * @return the transactionType
     */
    public TransactionType getTransactionType() {
        return transactionType;
    }

    /**
     * @param transactionType the transactionType to set
     */
    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    } 

    /**
     * @return the resultCode
     */
    public ResultCode getResultCode() {
        return resultCode;
    }

    /**
     * @param resultCode the resultCode to set
     */
    public void setResultCode(ResultCode resultCode) {
        this.resultCode = resultCode;
    }
 
}
