/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.vtsuite.controller;

import com.smartbt.girocheck.servercommon.dao.FeeScheduleDAO;
import com.smartbt.girocheck.servercommon.display.FeeScheduleDisplay;
import com.smartbt.girocheck.servercommon.display.message.BaseResponse;
import com.smartbt.girocheck.servercommon.display.message.ResponseData;
import com.smartbt.girocheck.servercommon.display.message.ResponseDataList;
import com.smartbt.girocheck.servercommon.manager.FeeScheduleManager;
import java.security.NoSuchAlgorithmException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import javax.xml.bind.ValidationException;

/**
 *
 * @author suresh
 */
@Path("VTAMS")
public class FeeScheduleController {
    @Context
    private HttpHeaders context;
    @Context
    private HttpServletResponse response;
    @Context
    private HttpServletRequest request;
    
    private FeeScheduleManager manager = new FeeScheduleManager();
    
    @Context
    private SecurityContext securityContext;
    
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(FeeScheduleController.class);
    
    /**
     * Search all the Users by a given filter
     *
     * @param searchFilter
     * @param pageNumber
     * @param rowsPerPage
     * @return
     */
    @POST
    @Path("searchFeeSchedule")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseDataList searchFeeSchedule(@FormParam("pageNumber") int pageNumber, @FormParam("rowsPerPage") int rowsPerPage) throws Exception {

        return manager.searchFeeSchedule(pageNumber, rowsPerPage);
    }

    @PUT
    @Path("addFeeSchedule")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseData addFeeSchedule(FeeScheduleDisplay fee) throws ValidationException, NoSuchAlgorithmException, Exception {

        return manager.addFeeSchedule(fee);
    }

    /**
     * Update a FeeSchedule
     *
     * @param FeeSchedule
     * @return
     * @throws java.lang.Exception
     */
    @PUT
    @Path("updateFeeSchedule")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse updateFeeSchedule(FeeScheduleDisplay fee) throws ValidationException, NoSuchAlgorithmException, Exception {

        return manager.updateFeeSchedule(fee);
    }
    
        
//  @DELETE
    @POST
    @Path("deleteFeeSchedule")
    //@Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse deleteFeeSchedule(@FormParam("id") int id) throws Exception {
        log.info("Incoming parameters : \n idUser to delete FeeScheduleController.deleteFeeSchedule() : " + id);
//        return AuditLogMessage.logDeleteFeeSchedule(""+idFeeSchedule,manager.deleteFeeSchedule(entityType, id));
        return manager.deleteFeeSchedule(id);
    }
    
    
    @POST
    @Path("searchTransactionMethod")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseDataList searchTransactionMethod() throws Exception {

        return manager.searchTransactionMethod();
    }
}
