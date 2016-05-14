/*
 ** File: ClerkController.java
 **
 ** Date Created: October 2013
 **
 ** Copyright @ 2004-2013 Smart Business Technology, Inc.
 **
 ** All rights reserved. No part of this software may be 
 ** reproduced, transmitted, transcribed, stored in a retrieval 
 ** system, or translated into any language or computer language, 
 ** in any form or by any means, electronic, mechanical, magnetic, 
 ** optical, chemical, manual or otherwise, without the prior 
 ** written permission of Smart Business Technology, Inc.
 **
 */
package com.smartbt.vtsuite.controller;

import com.smartbt.vtsuite.common.VTSuiteMessages;
import com.smartbt.girocheck.servercommon.display.message.BaseResponse;
import com.smartbt.girocheck.servercommon.display.message.ResponseDataList;
import com.smartbt.vtsuite.servercommon.display.common.model.CustomerDisplay;
import com.smartbt.vtsuite.servercommon.display.common.model.MerchantDisplay;
import com.smartbt.vtsuite.servercommon.display.common.model.RoleDisplay;
import com.smartbt.vtsuite.servercommon.display.common.model.TerminalDisplay;
import com.smartbt.vtsuite.servercommon.display.common.model.UserDisplay;
import com.smartbt.vtsuite.servercommon.manager.ClerkManager;
import com.smartbt.vtsuite.utils.AuditLogMessage;
import com.smartbt.vtsuite.vtcommon.Constants;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomApplication;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

/**
 *
 * @author Ariel Saavedra
 */
@Path("VTAMS")
public class ClerkController {

    @Context
    private HttpHeaders context;
    @Context
    private HttpServletResponse response;
    @Context
    private HttpServletRequest request;
    @Context
    private SecurityContext securityContext;
    private ClerkManager manager = new ClerkManager();
//    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ClerkController.class);

    /**
     * Search all Clerks by a given Merchant's id and a filter
     *
     * @param idEntity
     * @param entityType
     * @param searchFilter
     * @param rowsPerPage
     * @param pageNumber
     * @return
     * @throws java.lang.Exception
     */
    @POST
    @Path("searchClerks")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseDataList searchClerks(@FormParam("idEntity") int idEntity, @FormParam("entityType") EntityType entityType,
            @FormParam("searchFilter") String searchFilter, @FormParam("pageNumber") int pageNumber, @FormParam("rowsPerPage") int rowsPerPage) throws Exception {
//        log.info("Incoming parameters : \n idEntity: " + idEntity + " \n EntityType: " + (entityType == null ? "" : entityType.toString())
//                + " \n searchFilter: " + searchFilter + "\n pageNumber: " + pageNumber + "\n rowsPerPage: " + rowsPerPage);

        ResponseDataList response = new ResponseDataList();
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);

        response.setData(listClerks());
//        response.setData(clerkDAO.searchClerks(idEntity, searchFilter, entityType, pageNumber * rowsPerPage, rowsPerPage));
        int totalTrans = 1;//clerkDAO.searchClerks(idEntity, searchFilter, entityType, -1, -1).size();
        response.setTotalPages(totalTrans);
        return response;
    }

    /**
     *
     * @param idEntity
     * @param entityType
     * @param pageNumber
     * @param rowsPerPage
     * @return
     * @throws java.lang.Exception
     */
    @POST
    @Path("getClerksByEntityType")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseDataList getClerksByEntityType(@FormParam("idEntity") int idEntity, @FormParam("entityType") EntityType entityType,
            @FormParam("pageNumber") int pageNumber, @FormParam("rowsPerPage") int rowsPerPage) throws Exception {
//        log.info("Incoming parameters : \n idEntity: " + idEntity + " \n EntityType: " + entityType.toString()
//                + "\n pageNumber: " + pageNumber + "\n rowsPerPage: " + rowsPerPage);
       ResponseDataList response = new ResponseDataList();
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);

        response.setData(listClerks());
//        response.setData(clerkDAO.searchClerks(idEntity, searchFilter, entityType, pageNumber * rowsPerPage, rowsPerPage));
        int totalTrans = 1;//clerkDAO.searchClerks(idEntity, searchFilter, entityType, -1, -1).size();
        response.setTotalPages(totalTrans);
        return response;
    }
        
     private List<UserDisplay>  listClerks(){
        List<UserDisplay>  list = new ArrayList<UserDisplay>();
        
        UserDisplay u = new UserDisplay();
        u.setId(1);
        u.setRole(new RoleDisplay(1, "Admin"));
        u.setUsername("Clerk_1");
        u.setFirstName("FirstName");
        u.setLastName("LastName");
        u.setActive(Boolean.TRUE);
        u.setPassword("aa");
        u.setCustomer(new CustomerDisplay(1, "Girocheck"));
        u.setMerchant(new MerchantDisplay(1, "Girocheck's Merchant"));
        u.setTerminal(new TerminalDisplay(1, "Girocheck's Terminal"));
        
        list.add(u);
        return list;
    }

    /**
     * Update a Clerk
     *
     * @param clerk
     * @return
     * @throws java.lang.Exception
     */
    @PUT
    @Path("updateClerk")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse updateClerk(UserDisplay clerk) throws Exception {
//        log.info("Incoming parameters : \n clerk: " + clerk.getFirstName());

        return AuditLogMessage.logUpdateClerk(clerk.getId().toString(), clerk.getRole().getName(), manager.updateClerk(clerk, NomApplication.VT_AMS));
    }
}
