/*
 ** File: PrivilegesController.java
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
import com.smartbt.girocheck.servercommon.display.message.ResponseDataList;
import com.smartbt.girocheck.servercommon.manager.PrivilegesManager;
import com.smartbt.vtsuite.utils.AuditLogMessage;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.MediaType;
/**
 *
 * @author Ariel Saavedra
 */
@Path("VTAMS")
public class PrivilegesController {

    @Context
    private HttpHeaders context;
    private PrivilegesManager manager = new PrivilegesManager();
//    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(PrivilegesController.class);
    @Context
    private HttpServletResponse response;
    @Context
    private HttpServletRequest request;
    @Context
    private SecurityContext securityContext;

    /**
     *
     * @param entityType
     * @param entityNotContain
     * @param idRole
     * @param pageNumber
     * @param rowsPerPage
     * @return
     */
    @POST
    @Path("searchRolePrivileges")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseDataList searchRolePrivileges(@FormParam("entityType") EntityType entityType, @FormParam("entityNotContain") boolean entityNotContain, @FormParam("idRole") int idRole,
            @FormParam("pageNumber") int pageNumber, @FormParam("rowsPerPage") int rowsPerPage) throws Exception {
//        log.info(" PrivilegesController.searchRolePrivileges() Incoming parameters : \n entityType: " + entityType.toString() + "\n entityNotContain: " + entityNotContain + "\n idRole: " + idRole);

        return manager.searchRolePrivileges(entityType, entityNotContain, idRole, pageNumber, rowsPerPage);
    }

    /**
     *
     * @param entityType
     * @param idRole
     * @param idPrivilege
     * @return
     */
    @PUT
    @Path("addRolePrivilege")
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse addRolePrivilege(@FormParam("entityType") EntityType entityType, @FormParam("idRole") int idRole, @FormParam("idPrivilege") int idPrivilege) throws Exception {
//        log.info("Incoming parameters : \n idRole: " + idRole + "\n idPrivilege: " + idPrivilege);
        
//        return AuditLogMessage.logAddRolePrivilege(entityType.toString(), idPrivilege, idRole,
              return manager.addRolePrivilege(entityType, idRole, idPrivilege);
    }

    /**
     *
     * @param entityType
     * @param id
     * @return
     */
    @POST
    @Path("deleteRolePrivilege")
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse deleteRolePrivilege(@FormParam("rolePrivilegeId") int rolePrivilegeId) throws Exception {
//        log.info("Incoming parameters : idRolePrivilege: " + rolePrivilegeId);
              return  manager.deleteRolePrivilege(rolePrivilegeId);
    }
}
