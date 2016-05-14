/*
 * Copyright @ 2004-2014 Smart Business Technology, Inc.
 *
 * All rights reserved. No part of this software may be 
 * reproduced, transmitted, transcribed, stored in a retrieval
 * system, or translated into any language or computer language, 
 * in any form or by any means, electronic, mechanical, magnetic,  
 * optical, chemical, manual or otherwise, without the prior  
 * written permission of Smart Business Technology, Inc.  
 *
 *
 */

package com.smartbt.girocheck.servercommon.manager;

import com.smartbt.girocheck.servercommon.dao.TransactionDAO;
import com.smartbt.girocheck.servercommon.display.message.ResponseData;
import com.smartbt.girocheck.servercommon.display.message.ResponseDataList;
import com.smartbt.girocheck.servercommon.model.Transaction;
import com.smartbt.vtsuite.common.VTSuiteMessages;
import com.smartbt.vtsuite.vtcommon.Constants;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Roberto Rodriguez   :: <roberto.rodriguez@smartbt.com>
 */
public class TransactionManager {
    private TransactionDAO transactionDAO = TransactionDAO.get();
   
    
    public Transaction findById(int id){
        return transactionDAO.findById( id );
    }
    
    public boolean isCanceled(String requestId, boolean cancelable){
        return transactionDAO.isCanceled( requestId, cancelable);
    }
    
     public boolean cancelTransaction(String requestId){
         return transactionDAO.cancelTransaction( requestId );
     }

    public void saveOrUpdate( Transaction transaction ) {
        transactionDAO.saveOrUpdate( transaction );
    }
    
    public ResponseData getAddressImageFromClientByTerminalSerialNumber(String serialNumber, boolean rotate)
            throws SQLException {

        ResponseData response = new ResponseData();

        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
        try {
            response.setData(transactionDAO.getAddressImageFromClientByTerminalSerialNumber(serialNumber, rotate));

        } catch (Exception ex) {
            if (ex.getMessage().equals("EmptyException")) {
                response.setStatus(Constants.CODE_INVALID_ENTRY_DATA);
                response.setStatusMessage("No Transactions.");
            } else {
                response.setStatus(Constants.CODE_INVALID_ENTRY_DATA);
                response.setStatusMessage(VTSuiteMessages.ERROR_GENERAL);
            }
        }

        return response;

    }
    
    public ResponseDataList searchTransactions(String searchFilter, Date startRangeDate, Date endRangeDate,
            int transactionType, String operation, int pageNumber, int rowsPerPage, boolean filterAmmount, int ammountType, int opType, String ammount, boolean pending) throws Exception {
        ResponseDataList response = new ResponseDataList();

        response.setData(transactionDAO.searchTransactions(searchFilter, startRangeDate, endRangeDate, pageNumber * rowsPerPage, rowsPerPage, transactionType, operation,  filterAmmount,  ammountType,  opType,  ammount, pending));

        int total = transactionDAO.searchTransactions(searchFilter, startRangeDate, endRangeDate,-1, -1, transactionType, operation,  filterAmmount,  ammountType,  opType,  ammount, pending).size();
        response.setTotalPages((int) Math.ceil((float) total / (float) rowsPerPage));
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
        return response;
    }
    
    public ResponseData getTransactionImage(int idTransaction) throws SQLException {

        ResponseData response = new ResponseData();

        response.setData(transactionDAO.getTransactionImage(idTransaction));

        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
        return response;
    }

    public List<Transaction> getAll() {
        return transactionDAO.getAll();
    }
}
