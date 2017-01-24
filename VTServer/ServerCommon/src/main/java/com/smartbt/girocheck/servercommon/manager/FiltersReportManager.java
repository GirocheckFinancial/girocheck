package com.smartbt.girocheck.servercommon.manager;

import com.smartbt.girocheck.servercommon.model.FiltersReport;
import com.smartbt.girocheck.servercommon.dao.ReportDAO;

/**
 *
 * @author Roberto Rodriguez
 */


public class FiltersReportManager {
    
    protected static FiltersReportManager filtersReportManager;
    protected ReportDAO reportDAO = ReportDAO.get();
        
    public FiltersReportManager() {
    }

    public static FiltersReportManager get() {
        if ( filtersReportManager == null ) {
            filtersReportManager = new FiltersReportManager();
        }
        return filtersReportManager;
    }
     
    public int saveFiltersReport(FiltersReport filters) throws Exception { 
        return reportDAO.saveFiltersReport(filters);
    }
    
}
