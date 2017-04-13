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

import com.smartbt.girocheck.servercommon.display.message.ResponseData;
import com.smartbt.girocheck.servercommon.display.message.ResponseDataList;
import com.smartbt.girocheck.servercommon.display.ClientDisplay;
import com.smartbt.girocheck.servercommon.manager.ClientManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;/*
 import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
 import org.glassfish.jersey.media.multipart.FormDataParam*/

import javax.ws.rs.core.SecurityContext;
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
    //@FormParam("idEntity") int idEntity,
    // @FormParam("entityType") EntityType entityType,
    @POST
    @Path("searchClients")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseDataList searchClients(
            @FormParam("searchFilter") String searchFilter,
            @FormParam("blackList") Boolean blackList,
            @FormParam("optOut") Boolean optOut,
            @FormParam("pageNumber") int pageNumber, @FormParam("rowsPerPage") int rowsPerPage) throws Exception {

        return manager.searchClients(searchFilter, pageNumber, rowsPerPage, blackList, optOut);
    }

    @PUT
    @Path("updateClientBlackList")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseData updateClientBlackList(ClientDisplay clientDisplay) throws Exception {

        return manager.updateClientBlackList(clientDisplay);

    }

    @POST
    @Path("updateClientOptOut")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseData updateClientOptOut(@FormParam("id") int id) throws Exception {
        return manager.updateClientOptOut(id);
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
