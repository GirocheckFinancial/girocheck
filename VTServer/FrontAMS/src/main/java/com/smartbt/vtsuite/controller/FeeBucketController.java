/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.vtsuite.controller;

import com.smartbt.girocheck.servercommon.display.message.BaseResponse;
import com.smartbt.girocheck.servercommon.display.message.ResponseData;
import com.smartbt.girocheck.servercommon.display.message.ResponseDataList;
import com.smartbt.girocheck.servercommon.manager.FeeBucketsManager;
import com.smartbt.girocheck.servercommon.model.FeeBucketsDisplay;
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
public class FeeBucketController {
    
     @Context
    private HttpHeaders context;
    @Context
    private HttpServletResponse response;
    @Context
    private HttpServletRequest request;
    
    private FeeBucketsManager manager = new FeeBucketsManager();
    
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
    @Path("searchFeeBucket")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseDataList searchFeeBucket(@FormParam("feeScheduleId") int idFeeSchedule,@FormParam("pageNumber") int pageNumber, @FormParam("rowsPerPage") int rowsPerPage) throws Exception {

        return manager.searchFeeBucket(idFeeSchedule,pageNumber, rowsPerPage);
    }
    
    @PUT
    @Path("addFeeBucket")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseData addFeeBucket(FeeBucketsDisplay display) throws ValidationException, NoSuchAlgorithmException, Exception {

        return manager.addFeeBucket(display);
    }
    @PUT
    @Path("updateFeeBucket")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse updateFeeBucket(FeeBucketsDisplay fee) throws ValidationException, NoSuchAlgorithmException, Exception {

        return manager.updateFeeBucket(fee); 
    }
    
    //  @DELETE
    @POST
    @Path("deleteFeeBucket")
    //@Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse deleteFeeBucket(@FormParam("id") int id) throws Exception {
        log.info("Incoming parameters : \n idUser to delete FeeScheduleController.deleteFeeSchedule() : " + id);
//        return AuditLogMessage.logDeleteFeeSchedule(""+idFeeSchedule,manager.deleteFeeSchedule(entityType, id));
        return manager.deleteFeeBucket(id);
    }
    
}
