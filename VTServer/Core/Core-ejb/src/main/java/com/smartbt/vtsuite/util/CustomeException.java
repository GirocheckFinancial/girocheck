/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.vtsuite.util;

import com.smartbt.girocheck.servercommon.enums.ResultCode;
import com.smartbt.girocheck.servercommon.model.Transaction;
import com.smartbt.girocheck.servercommon.utils.DirexException;

/**
 *
 * @author Alejo
 */
public class CustomeException extends DirexException{

    public CustomeException(ResultCode resultCode, String message) {
        super(resultCode, message);
    }
    
    private Transaction transaction;

    public CustomeException( ResultCode resultCode, String message, Transaction transaction ) {
        super( resultCode, message );
        this.transaction = transaction;
        this.transaction.setResultCode( resultCode.getCode() );
        this.transaction.setResultMessage( message );
    }

    public Transaction getTransaction() {
        return transaction;
    }
    
}
