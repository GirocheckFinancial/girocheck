/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.vtsuite.manager;

import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.enums.ResultCode;
import com.smartbt.girocheck.servercommon.enums.ResultMessage;
import com.smartbt.girocheck.servercommon.manager.TerminalManager;
import com.smartbt.girocheck.servercommon.manager.TransactionManager;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import java.util.Map;

/**
 *
 * @author Roberto Rodriguez :: <roberto.rodriguez@smartbt.com>
 */
public class CancelTransactionBusinessLogic {

    public DirexTransactionResponse handle( DirexTransactionRequest direxTransactionRequest ) throws Exception {

        Map transactionData = direxTransactionRequest.getTransactionData();

        if ( !transactionData.containsKey( ParameterName.REQUEST_ID ) || ( (String) transactionData.get( ParameterName.REQUEST_ID ) ).isEmpty() ) {
            return DirexTransactionResponse.forException( ResultCode.REQUEST_ID_REQUIRED, ResultMessage.REQUEST_ID_REQUIRED );
        }
        if ( !transactionData.containsKey( ParameterName.TERMINAL_ID ) || ( (String) transactionData.get( ParameterName.TERMINAL_ID ) ).isEmpty() ) {
            return DirexTransactionResponse.forException( ResultCode.TERMINAL_ID_REQUIRED, ResultMessage.TERMINAL_ID_REQUIRED );
        }
        if ( !transactionData.containsKey( ParameterName.USER ) || ( (String) transactionData.get( ParameterName.USER ) ).isEmpty() ) {
            return DirexTransactionResponse.forException( ResultCode.USER_REQUIRED, ResultMessage.USER_REQUIRED );
        }
        if ( !transactionData.containsKey( ParameterName.PASSWORD ) || ( (String) transactionData.get( ParameterName.PASSWORD ) ).isEmpty() ) {
            return DirexTransactionResponse.forException( ResultCode.PASSWORD_REQUIRED, ResultMessage.PASSWORD_REQUIRED );
        }

        String requestId = (String) transactionData.get( ParameterName.REQUEST_ID );
        String terminalId = (String) transactionData.get( ParameterName.TERMINAL_ID );
        String user = (String) transactionData.get( ParameterName.USER );
        String password = (String) transactionData.get( ParameterName.PASSWORD );

        TerminalManager terminalManager = new TerminalManager();
        TransactionManager transactionManager = new TransactionManager();

        DirexTransactionResponse response;
        
        try {
            HibernateUtil.beginTransaction();

            if ( !terminalManager.loginTerminal( terminalId, user, password ) ) {
                return DirexTransactionResponse.forException( ResultCode.LOGIN_FAILED, ResultMessage.LOGIN_FAILED );
            }

            if ( !transactionManager.cancelTransaction( requestId ) ) {
                return DirexTransactionResponse.forException( ResultCode.FAILED, ResultMessage.FAILED );
            }

            response = new DirexTransactionResponse();
            response.setResultCode( ResultCode.SUCCESS );
            response.setResultMessage( ResultMessage.SUCCESS.getMessage() );

            HibernateUtil.commitTransaction();
        } catch ( Exception e ) {
            e.printStackTrace();
            HibernateUtil.rollbackTransaction();
            response = DirexTransactionResponse.forException( ResultCode.FAILED, e);
        }
        return response;
    }
}
