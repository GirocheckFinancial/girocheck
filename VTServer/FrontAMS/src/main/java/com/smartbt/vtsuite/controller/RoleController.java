/*
 ** File: RoleController.java
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
import com.smartbt.girocheck.servercommon.display.RoleDisplay;
import com.smartbt.girocheck.servercommon.manager.RoleManager;
import com.smartbt.vtsuite.utils.AuditLogMessage;
import com.smartbt.vtsuite.vtcommon.Constants;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.ValidationException;
/**
 *
 * @author Ariel Saavedra
 */
@Path("VTAMS")
public class RoleController {

    @Context
    private HttpHeaders context;
    @Context
    private HttpServletResponse response;
    @Context
    private HttpServletRequest request;
    private RoleManager manager = new RoleManager();
//    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(RoleController.class);
    @Context
    private SecurityContext securityContext;

    /**
     * Get the ClerkRoles
     *
     * @param entityType
     * @return
     */
    @POST
    @Path("getRoles")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseDataList getRoles(@FormParam("entityType") EntityType entityType) throws Exception {
//        log.info("Incoming parameters :" + " \n entityType: " + entityType.toString());
//        log.info("Entered method getRoles() :" + " \n entityType: " + entityType.toString());

       // return getRolesList();
        return manager.getRoles(entityType);
    }
    
    private ResponseDataList getRolesList(){
        ResponseDataList response = new ResponseDataList();
        
        List<RoleDisplay> roleDisplays = new LinkedList<RoleDisplay>();
        RoleDisplay admin = new RoleDisplay(1, "Administrator");
        RoleDisplay manager = new RoleDisplay(2, "Manager");
        roleDisplays.add(admin);
        roleDisplays.add(manager);
        
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
        response.setData(roleDisplays);
        return response;
    }

    /**
     * Add a UserRole
     *
     * @param entityType
     * @param name
     * @param description
     * @return
     * @throws javax.xml.bind.ValidationException
     */
    @PUT
    @Path("addRole")
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse addRole(@FormParam("entityType") EntityType entityType, @FormParam("name") String name, @FormParam("description") String description) throws ValidationException, Exception {
//        log.info("Incoming parameters : \n entityType: " + entityType.toString() + " \n name: " + name+ " \n description: " + description);

//        return AuditLogMessage.logAddRole(entityType.toString(), name,
          return manager.addRole(entityType, name, description);
    }

    /**
     * Update a UserRole
     *
     * @param entityType
     * @param name
     * @param idRole
     * @param description
     * @return
     */
    @PUT
    @Path("updateRole")
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse updateRole(@FormParam("entityType") EntityType entityType, @FormParam("idRole") int idRole, @FormParam("name") String name, @FormParam("description") String description) {
//        log.info("Incoming parameters : \n name: " + name + " \n entityType: " + entityType.toString() + " \n idRole: " + idRole);

//        return AuditLogMessage.logUpdateRole(entityType.toString(), name, idRole,
             return    manager.updateRole(entityType, idRole, name, description);
    }

    /**
     * Delete a UserRole
     *
     * @param entityType
     * @param idRole
     * @return
     */
    @POST
    @Path("deleteRole")
//    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse deleteRole(@FormParam("idRole") int idRole) throws Exception {
//        log.info("Incoming parameters : \n idRole to delete: " + idRole);
        
//        return AuditLogMessage.logDeleteRole(entityType.toString(), idRole,
            return manager.deleteRole(idRole);
    }
}
