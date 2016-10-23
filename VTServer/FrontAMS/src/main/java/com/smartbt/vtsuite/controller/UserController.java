/*
 ** File: UserController.java
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
import com.smartbt.girocheck.servercommon.display.UserDisplay;
import com.smartbt.girocheck.servercommon.display.message.ResponseData;
import com.smartbt.girocheck.servercommon.manager.UserManager;
import java.security.NoSuchAlgorithmException;
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
public class UserController {

    @Context
    private HttpHeaders context;
    @Context
    private HttpServletResponse response;
    @Context
    private HttpServletRequest request;
    private UserManager manager = new UserManager();
//    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(UserController.class);
    @Context
    private SecurityContext securityContext;

    /**
     * Search all the Users by a given filter
     *
     * @param searchFilter
     * @param pageNumber
     * @param rowsPerPage
     * @return
     */
    @POST
    @Path("searchUsers")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseDataList searchUsers(@FormParam("searchFilter") String searchFilter, @FormParam("pageNumber") int pageNumber, @FormParam("rowsPerPage") int rowsPerPage) throws Exception {
//        log.info("Incoming parameters : \n searchFilter: " + searchFilter);

        return manager.searchUsers(searchFilter, pageNumber, rowsPerPage);
    }
    
    @PUT
    @Path("addUser")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseData addUser(UserDisplay user) throws ValidationException, NoSuchAlgorithmException, Exception {
//        log.info("Incoming parameters : \n entityType: " + " \n name: " + user.getUsername());

//        return AuditLogMessage.logAddUser(user.toString(), manager.addUser(user));
        return manager.addUser(user);
    }

    /**
     * Update a User
     *
     * @param user
     * @return
     * @throws java.lang.Exception
     */
    @PUT
    @Path("updateUser")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse updateUser(UserDisplay user) throws Exception {
//        log.info("Incoming parameters : \n user: " + user.toString());

//        return AuditLogMessage.logUpdateUser(user.toString(), manager.updateUser(user));
         return manager.updateUser(user);
    }
    
        
//    @DELETE
    @POST
    @Path("deleteUser")
//    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse deleteUser(@FormParam("id") int id) throws Exception {
//        log.info("Incoming parameters : \n idUser to delete UserController.deleteUser() : " + id);
        
//        return AuditLogMessage.logDeleteUser(""+idUser,manager.deleteUser(entityType, idUser));
        return manager.deleteUser(id);
    }
    
    @POST
    @Path("changePaswword")

    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse changePassword(@FormParam("userId") int id, @FormParam("password") String password) throws Exception {
        System.out.println("UserController.changePassword password = " + password);
        return manager.changePassword(id,password);
    }
    
    
       /**
     * Get the user by a given id
     *
     * @param userId
     * @return
     */
    @POST
    @Path("getUser")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseData getUser(@FormParam("userId") Integer userId) throws Exception {
      
        System.out.println("Incoming parameters : \n userId: " + userId);
        return new ResponseData(manager.getUserById(userId));
    }
}
