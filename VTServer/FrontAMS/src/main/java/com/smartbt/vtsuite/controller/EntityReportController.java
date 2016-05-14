/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.vtsuite.controller;

import com.smartbt.girocheck.servercommon.display.ReportDisplay;
import com.smartbt.girocheck.servercommon.display.message.ResponseData;
import com.smartbt.girocheck.servercommon.manager.ReportFiltersManager;
import com.smartbt.girocheck.servercommon.model.ReportFilters;
import com.smartbt.vtsuite.common.VTSuiteMessages;
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
public class EntityReportController {
    
    ReportFiltersManager reportFiltersManager = ReportFiltersManager.get();
    
    @POST
    @Path("searchEntityReport")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseData searchEntityReport( 
            @FormParam("entityId") String entityId, /*@FormParam("startRangeDate") String startRangeDateStr, @FormParam("endRangeDate") String endRangeDateStr,
            @FormParam("transactionType") int transactionType, @FormParam("operation") String operation,
            @FormParam("filterAmmount") boolean filterAmmount, @FormParam("ammountType") int ammountType, @FormParam("opType") int opType, @FormParam("ammount") String amount, 
            @FormParam("pending") boolean pending, */@FormParam("entityType") String entityType) throws Exception {

        System.out.println("[EntityReportController] searchEntityReport() values received: entityId: "+entityId+" entityType: " + entityType);
        ReportFilters reportFilters = new ReportFilters();
        ResponseData response = new ResponseData();
        
        if(entityId !=null && !entityId.isEmpty() && !"null".equals(entityId)){
            reportFilters.setEntityId(Integer.parseInt(entityId));
        }
        
        //save the report filter in the db
        int reportFiltersId = reportFiltersManager.saveReportFilters(reportFilters);
        
        ReportDisplay reportDisplay = new ReportDisplay();
        
        String url="";
        System.out.println("[EntityReportController] searchEntityReport() with filters id = "+reportFiltersId);
        
        switch(entityType){
            case "AGRUPATION":
                url="/VTReporting/index.jsp?reportType=Grouping&id="+reportFiltersId;
                break;
            case "MERCHANT":
                url="/VTReporting/index.jsp?reportType=Merchant&id="+reportFiltersId;
                break;  
        }
        
        if("".equals(url)){
            response.setStatus(Constants.REPORT_ERROR_CODE);
            response.setStatusMessage("WRONG ENTITY SELECTED. Please select Gouping or Merchant.");
        }else{
            response.setStatus(Constants.CODE_SUCCESS);
            response.setStatusMessage(VTSuiteMessages.SUCCESS);
        }
        
        reportDisplay.setUrl(url);
        response.setData(reportDisplay);
        
        return response;
    }
    
}
