/*
 ** File: DirexTransactionResponse.java
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
package com.smartbt.girocheck.servercommon.messageFormat;

import com.smartbt.girocheck.servercommon.enums.ResultMessage;
import com.smartbt.girocheck.servercommon.enums.ResultCode;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.log.LogUtil;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Direx Transaction Response Class
 */
public class DirexTransactionResponse extends DirexTransaction{

    private boolean approved = true;
    
    private ResultCode resultCode;
    
    private String errorCode = "0";
    
    private String resultMessage;
    private String terminalResultMessage;

    public DirexTransactionResponse() {        
    }

    public static DirexTransactionResponse forSuccess(){
        DirexTransactionResponse response = new DirexTransactionResponse();
        response.setResultCode(ResultCode.SUCCESS);
        response.setResultMessage(ResultMessage.SUCCESS.getMessage());
        response.setTerminalResultMessage(ResultMessage.SUCCESS.getMessage());
        return response;
    }

    public static DirexTransactionResponse forException(ResultCode resultCode, Exception ex) {
        
        DirexTransactionResponse response = forException( resultCode,  ex.getMessage());
        response.setTerminalResultMessage(ResultMessage.FAILED.getTerminalMessage());
        response.setErrorCode("");
        return response;

    }

    public static DirexTransactionResponse forException(ResultCode resultCode, ResultMessage resultMessage) {
        DirexTransactionResponse response = forException( resultCode,  resultMessage.getMessage());
        response.setTerminalResultMessage(resultMessage.getTerminalMessage());
        return response;
    }

    public static DirexTransactionResponse forException(ResultCode resultCode, String resultMessage) {
        DirexTransactionResponse exRsp = new DirexTransactionResponse(); 
        exRsp.setApproved(false);
        exRsp.setResultCode(resultCode);
        exRsp.setResultMessage(resultMessage);
        exRsp.setTerminalResultMessage(resultMessage);
        return exRsp;
    }

    public static DirexTransactionResponse forException(ResultCode resultCode, ResultMessage resultMessage, String resultMessageDetail, String errorCode) {
        DirexTransactionResponse exRsp = forException( resultCode, resultMessage.getMessage()+" "+resultMessageDetail);
        exRsp.setTerminalResultMessage(resultMessage.getTerminalMessage());
        exRsp.setErrorCode( errorCode );
        return exRsp;
    }
    
    public static DirexTransactionResponse forException(TransactionType transactionType, ResultCode resultCode, ResultMessage resultMessage, String resultMessageDetail) {
        DirexTransactionResponse response = forException( resultCode,  resultMessage.getMessage()+resultMessageDetail);
        response.setTerminalResultMessage(resultMessage.getTerminalMessage());
        response.setTransactionType( transactionType );
        return response;
    }
    
    public static DirexTransactionResponse forException(TransactionType transactionType, ResultCode resultCode, ResultMessage resultMessage) {
        DirexTransactionResponse response = forException( resultCode,  resultMessage);
        response.setTransactionType( transactionType );
        response.setTerminalResultMessage(resultMessage.getTerminalMessage());
        return response;
    }
     
    

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public boolean wasApproved() {
        return approved;
    }

    public void setResultCode(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setErrorCode( String errorCode ) {
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    /**
     * @return the terminalResultMessage
     */
    public String getTerminalResultMessage() {
        if(terminalResultMessage == null){
            terminalResultMessage = resultMessage;
        }
        return terminalResultMessage;
    }

    /**
     * @param terminalResultMessage the terminalMessage to set
     */
    public void setTerminalResultMessage(String terminalMessage) {
        this.terminalResultMessage = terminalMessage;
    }
}
