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
import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.model.Transaction;
import com.smartbt.vtsuite.common.VTSuiteMessages;
import com.smartbt.vtsuite.vtcommon.Constants;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Roberto Rodriguez :: <roberto.rodriguez@smartbt.com>
 */
public class TransactionManager {

    private TransactionDAO transactionDAO = TransactionDAO.get();

    protected static TransactionManager _this;

    public static TransactionManager get() {
        if (_this == null) {
            _this = new TransactionManager();
        }
        return _this;
    }

    public Map activityReport(Map input) {
        Date dateStart = null;
        Date dateEnd = null;

        if (input.containsKey(ParameterName.START_DATE) && input.get(ParameterName.START_DATE) != null) {
            dateStart = (Date) input.get(ParameterName.START_DATE);
        }

        if (input.containsKey(ParameterName.END_DATE) && input.get(ParameterName.END_DATE) != null) {
            dateEnd = (Date) input.get(ParameterName.END_DATE);
        }

        

        if (dateStart == null && dateEnd == null) {
            System.out.println("Both dates are NULL");
            dateEnd = new Date();
            dateStart= new Date(); 
        } else {
            if (dateEnd == null) {
                 System.out.println("dateEnd is NULL");
                dateEnd = getDateWithMonthDifference(dateStart, 1);
                System.out.println("Setting dateEnd to " + dateEnd);
            } else {
                if (dateStart == null) {
                     System.out.println("dateStart is NULL");
                    dateStart = getDateWithMonthDifference(dateEnd, -1);
                      System.out.println("Setting dateStart to " + dateStart);
                } else {
                    if ((dateEnd.getTime() - dateStart.getTime()) / (1000 * 60 * 60 * 24) > 30) {
                        System.out.println("DateEnd < dateStart > 30 days");
                        dateStart = getDateWithMonthDifference(dateEnd, -1);
                        System.out.println("Setting dateStart to " + dateStart);
                    }
                }
            }
        }
        
        dateEnd.setHours(23);
        dateEnd.setMinutes(59);
        dateEnd.setSeconds(59);

        dateStart.setHours(0);
        dateStart.setMinutes(0);
        dateStart.setSeconds(0);

        System.out.println("TransactionManager -> activityReport Dates::");
        System.out.println("startDate = " + dateStart);
        System.out.println("endDate = " + dateEnd);

        input.put(ParameterName.START_DATE, dateStart);
        input.put(ParameterName.END_DATE, dateEnd);

        return transactionDAO.activityReport(input);
    }

    private static Date getDateWithMonthDifference(Date date, Integer difference) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, difference);
        return c.getTime();
    }

    public Transaction findById(int id) {
        return transactionDAO.findById(id);
    }

    public boolean isCanceled(String requestId, boolean cancelable) {
        return transactionDAO.isCanceled(requestId, cancelable);
    }

    public boolean cancelTransaction(String requestId) {
        return transactionDAO.cancelTransaction(requestId);
    }

    public void saveOrUpdate(Transaction transaction) {
        transactionDAO.saveOrUpdate(transaction);
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
        
        return transactionDAO.searchTransactions(searchFilter, startRangeDate, endRangeDate, pageNumber * rowsPerPage, rowsPerPage, transactionType, operation, filterAmmount, ammountType, opType, ammount, pending);
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
