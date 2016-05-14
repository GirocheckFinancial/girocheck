/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.vtsuite.manager;

import com.smartbt.girocheck.servercommon.display.message.ResponseDataList;
import com.smartbt.vtsuite.common.VTSuiteMessages;
import com.smartbt.vtsuite.vtcommon.Constants;
import java.io.InputStream;
import java.util.Date;

/**
 *
 * @author Alejo
 */
public class TransactionReportManager {
    
        public ResponseDataList searchTransactionReport(String searchFilter, Date startRangeDate, Date endRangeDate,
            int transactionType, String operation, boolean filterAmmount, int ammountType, int opType, String ammount, boolean pending) throws Exception {
        ResponseDataList response = new ResponseDataList();

        //response.setData(transactionReportDAO.searchTransactionReport(searchFilter, startRangeDate, endRangeDate, transactionType, operation,  filterAmmount,  ammountType,  opType,  ammount, pending));

//        int total = transactionReportDAO.searchTransactionReport(searchFilter, startRangeDate, endRangeDate, transactionType, operation,  filterAmmount,  ammountType,  opType,  ammount, pending).size();
//        response.setTotalPages((int) Math.ceil((float) total / (float) rowsPerPage));
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
        return response;
    }
    
}
