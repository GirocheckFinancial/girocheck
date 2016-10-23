/*
 ** File: ClientController.java
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

import com.smartbt.girocheck.servercommon.display.message.BaseResponse;
import com.smartbt.girocheck.servercommon.display.message.ResponseData;
import com.smartbt.girocheck.servercommon.display.message.ResponseDataList;
import com.smartbt.girocheck.servercommon.display.ClientDisplay;
import com.smartbt.girocheck.servercommon.manager.ClientManager;
import com.smartbt.vtsuite.utils.AuditLogMessage;
import com.smartbt.vtsuite.vtcommon.enums.ActivityFilter;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomApplication;
import java.io.InputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;/*
 import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
 import org.glassfish.jersey.media.multipart.FormDataParam*/

import javax.ws.rs.core.SecurityContext;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
//import org.hibernate.validator.constraints.Email;
//import org.hibernate.validator.constraints.NotEmpty;
//import org.hibernate.validator.constraints.Range;

/**
 *
 * @author Ariel Saavedra
 */
@Path("VTAMS")
public class ClientController {

    @Context
    private HttpHeaders context;
    @Context
    private HttpServletResponse response;
    @Context
    private HttpServletRequest request;
    private ClientManager manager = new ClientManager();
//    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ClientController.class);
    @Context
    private SecurityContext securityContext;

    /**
     * Search all Clients by a given Merchant's id and a filter
     *
     * @param idEntity
     * @param entityType
     * @param searchFilter
     * @param activityFilter
     * @param pageNumber
     * @param rowsPerPage
     * @return
     * @throws java.lang.Exception
     */
    @POST
    @Path("searchClients")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseDataList searchClients(@FormParam("idEntity") int idEntity, @FormParam("entityType") EntityType entityType,
          @FormParam("searchFilter") String searchFilter, @FormParam("activityFilter") ActivityFilter activityFilter,
          @FormParam("pageNumber") int pageNumber, @FormParam("rowsPerPage") int rowsPerPage) throws Exception {
//        log.info("Incoming parameters : \n idEntity: " + idEntity + " \n EntityType: " + entityType.toString() + " \n searchFilter: " + searchFilter
//                + " \n activityFilter: " + (activityFilter == null ? "null" : activityFilter.toString()) + "\n pageNumber: " + pageNumber + "\n rowsPerPage: " + rowsPerPage);
//        return manager.searchClients(idEntity, entityType, searchFilter, activityFilter, pageNumber, rowsPerPage, NomApplication.VT_AMS);
          return manager.searchClients(searchFilter, pageNumber, rowsPerPage, NomApplication.VT_AMS);
    }

   
    /**
     * set client to given ClientDisplay
     *
     * @param client
     * @return
     * @throws java.lang.Exception
     */
//    @PUT
//    @Path("saveOrUpdateClient")
//    @Consumes("application/json")
//    @Produces(MediaType.APPLICATION_JSON)
//    public BaseResponse saveOrUpdateClient(ClientDisplay client) throws Exception {
//        log.info("Incoming parameter : \n client: " + client);
//        BaseResponse resp = manager.saveOrUpdateClient(client);
//        return AuditLogMessage.logSaveOrUpdateClient(client.getId(), resp);
//    }

 

    /**
     * delete all clients
     *
     * @param idEntity
     * @param entityType
     * @return
     */
//    @POST
//    @Path("deleteAllClients")
//    @Produces(MediaType.APPLICATION_JSON)
//    public BaseResponse deleteAllClients(@FormParam("idEntity") int idEntity, @FormParam("entityType") EntityType entityType) throws Exception {
//        log.info("Incoming parameter : \n idEntity: " + idEntity + " \n EntityType: " + entityType.toString());
//
//        return AuditLogMessage.logDeleteAllClients(entityType.toString(), idEntity, manager.deleteAllClients(idEntity, entityType));
//    }

    /**
     * Import CLients from csv file
     *
     * @param idMerchant
     * @param logoImageInputStream
     * @param logoImageDetails
     * @return
     * @throws java.lang.Exception
     */
//    @POST
//    @Path("importClients")
//    @Consumes(MediaType.MULTIPART_FORM_DATA)
//    @Produces(MediaType.APPLICATION_JSON)
//    public BaseResponse importClients(@FormDataParam("id") int idMerchant,
//            @FormDataParam("file") InputStream logoImageInputStream,
//            @FormDataParam("file") FormDataContentDisposition logoImageDetails) throws Exception {
//
//        log.info("Incoming parameters : \n idMerchant: " + idMerchant + "\n logoImage: " + (logoImageDetails == null ? "null" : logoImageDetails.getFileName()));
//
//        BaseResponse resp= manager.updateWithStream(logoImageInputStream, idMerchant);
//
//        return AuditLogMessage.logImportClients(idMerchant, resp);
//    }
}
