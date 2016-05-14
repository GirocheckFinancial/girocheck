package com.smartbt.girocheck.servercommon.manager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartbt.girocheck.servercommon.dao.TransactionReportDAO;
import com.smartbt.girocheck.servercommon.model.ReportFilters;

/**
 *
 * @author Alejo
 */


public class ReportFiltersManager {
    
     protected static ReportFiltersManager reportFilters;
        ObjectMapper objectMapper = new ObjectMapper();

    public ReportFiltersManager() {
    }

    public static ReportFiltersManager get() {
        if ( reportFilters == null ) {
            reportFilters = new ReportFiltersManager();
        }
        return reportFilters;
    }
    
    TransactionReportDAO transactionReportDAO = TransactionReportDAO.get();
    
    public int saveReportFilters(ReportFilters filters) throws Exception {

        return transactionReportDAO.saveReportFilters(filters);
    }
    
}
