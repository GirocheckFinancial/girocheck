/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.vtsuite.controller;

import com.smartbt.girocheck.servercommon.display.message.ResponseData;
import com.smartbt.girocheck.common.VTSuiteMessages;
import com.smartbt.girocheck.servercommon.display.ReportDisplay;
import com.smartbt.girocheck.servercommon.manager.ReportFiltersManager;
import com.smartbt.girocheck.servercommon.model.ReportFilters;
import com.smartbt.vtsuite.vtcommon.Constants;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Alejo
 */
@Path("VTAMS")
public class TransactionReportController {
    
    ReportFiltersManager reportFiltersManager = ReportFiltersManager.get();
    
    @POST
    @Path("searchTransactionReport")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseData searchTransactionReport( 
            @FormParam("searchFilter") String searchFilter, @FormParam("startRangeDate") String startRangeDateStr, @FormParam("endRangeDate") String endRangeDateStr,
            @FormParam("transactionType") int transactionType, @FormParam("operation") String operation,
            @FormParam("filterAmmount") boolean filterAmmount, @FormParam("ammountType") int ammountType, @FormParam("opType") int opType, @FormParam("ammount") String amount, 
            @FormParam("pending") boolean pending, @FormParam("reportType") boolean reportType) throws Exception {

        
        ReportFilters reportFilters = new ReportFilters();
        ResponseData response = new ResponseData();
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
        
        
        if(startRangeDateStr != null && !startRangeDateStr.isEmpty() && !"null".equals(startRangeDateStr)){
            reportFilters.setStartRangeDate(startRangeDateStr);
        }
        
        if(endRangeDateStr != null && !endRangeDateStr.isEmpty() && !"null".equals(endRangeDateStr)){
            reportFilters.setEndRangeDate(endRangeDateStr);
        }
        
        if(amount !=null && !amount.isEmpty() && !"null".equals(amount)){
            reportFilters.setAmount(amount);
        }
        
        //save the report filter in the db
        int reportFiltersId = reportFiltersManager.saveReportFilters(reportFilters);
        
        ReportDisplay reportDisplay = new ReportDisplay();
        
        String url;
        System.out.println("TransactionReportController with filters id = "+reportFiltersId);
        if(reportType){
            url="/VTReporting/index.jsp?reportType=DetailsListing&id="+reportFiltersId;
        }else{
            url="/VTReporting/index.jsp?reportType=Details&id="+reportFiltersId;
        }
        reportDisplay.setUrl(url);
        response.setData(reportDisplay);
        
        return response;
    }
    
}
