/*
 ** File: WatchdogController.java
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

import com.smartbt.girocheck.common.SessionAMSUser;
import com.smartbt.vtsuite.conf.security.UserContext;
import com.smartbt.girocheck.servercommon.display.message.BaseResponse;
import com.smartbt.girocheck.servercommon.display.message.ResponseDataList;
import com.smartbt.vtsuite.servercommon.manager.WatchdogManager;
import com.smartbt.vtsuite.servercommon.model.WatchdogEntity;
import com.smartbt.vtsuite.servercommon.utils.DateUtils;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Ariel Saavedra
 */
@Path("VTAMS")
public class WatchdogController {

    @Context
    private SecurityContext securityContext;

    private WatchdogManager manager = new WatchdogManager();
//    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(WatchdogController.class);

    /**
     * Save or Update a WatchdogEntity
     *
     * @param watchdogEntity
     * @return
     * @throws java.lang.Exception
     */
    @PUT
    @Path("saveOrUpdateWatchdogEntity")
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse saveOrUpdateWatchdogEntity(WatchdogEntity watchdogEntity) throws Exception {
//        log.info("Incoming parameter : \n watchdogEntity: " + watchdogEntity.toString());
        
//        watchdogEntity.setUserCreator(SessionAMSUser.get());
        return manager.saveOrUpdateWatchdogEntity(watchdogEntity);
    }

    /**
     * Delete Watchdog Entity
     *
     * @param watchdogEntityId
     * @return
     * @throws java.lang.Exception
     */
    @DELETE
    @Path("deleteWatchdogEntity")
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse deleteWatchdogEntity(@QueryParam("id") int watchdogEntityId) throws Exception {
//        log.info("Incoming parameter : \n watchdogEntityId: " + watchdogEntityId);
        return manager.deleteWatchdogEntity(watchdogEntityId);
    }

    /**
     * Delete WatchdogAlert
     *
     * @param watchdogAlertId
     * @return
     * @throws java.lang.Exception
     */
    @DELETE
    @Path("deleteWatchdogAlert")
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse deleteWatchdogAlert(@QueryParam("id") int watchdogAlertId) throws Exception {
//        log.info("Incoming parameter : \n watchdogAlertId: " + watchdogAlertId);
        return manager.deleteWatchdogAlert(watchdogAlertId);
    }

    /**
     * Delete WatchdogAlerts
     *
     * @param entityType
     * @param idEntity
     * @return
     * @throws java.lang.Exception
     */
    @POST
    @Path("deleteAllWatchdogAlertsByEntity")
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse deleteAllWatchdogAlertsByEntity(@FormParam("entityType") EntityType entityType, @FormParam("idEntity") int idEntity) throws Exception {
//        log.info("Incoming parameter : \n entityType: " + entityType.toString() + "\nidEntity: " + idEntity);
        return manager.deleteAllWatchdogAlertsByEntity(entityType, idEntity);
    }

    /**
     * Get Watchdog Entity
     *
     * @param watchdogEntityId
     * @return
     * @throws java.lang.Exception
     */
    @POST
    @Path("getWatchdogEntity")
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse getWatchdogEntity(@FormParam("id") int watchdogEntityId) throws Exception {
//        log.info("Incoming parameter : \n watchdogEntityId: " + watchdogEntityId);
        return manager.getWatchdogEntity(watchdogEntityId);
    }

    /**
     * Get Watchdogs
     *
     * @return
     * @throws java.lang.Exception
     */
    @POST
    @Path("getWatchdogs")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseDataList getWatchdogs() throws Exception {
//        log.info("Incoming parameter [none]");
        return manager.getWatchdogs();
    }

    /**
     * Get Watchdog Entities by Entity
     *
     * @param entityType
     * @param idEntity
     * @return
     * @throws java.lang.Exception
     */
    @POST
    @Path("getWatchdogEntitiesByEntity")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseDataList getWatchdogEntitiesByEntity(@FormParam("entityType") EntityType entityType, @FormParam("idEntity") int idEntity) throws Exception {
//        log.info("Incoming parameter : \n entityType: " + entityType.toString() + " idEntity: " + idEntity);
        return manager.getWatchdogEntitiesByEntity(entityType, idEntity);
    }

    /**
     * Get Watchdog Alerts by Entity
     *
     * @param entityType
     * @param idEntity
     * @param searchFilter
     * @param startRangeDateStr
     * @param endRangeDateStr
     * @param rowsPerPage
     * @param pageNumber
     * @return
     * @throws java.lang.Exception
     */
    @POST
    @Path("getWatchdogAlertsByEntity")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseDataList getWatchdogAlertsByEntity(@FormParam("entityType") EntityType entityType, @FormParam("idEntity") int idEntity,
            @FormParam("searchFilter") String searchFilter, @FormParam("startRangeDate") String startRangeDateStr, @FormParam("endRangeDate") String endRangeDateStr,
            @FormParam("pageNumber") int pageNumber, @FormParam("rowsPerPage") int rowsPerPage) throws Exception {
//        log.info("Incoming parameter : \n entityType: " + entityType.toString() + "\n idEntity: " + idEntity + "\n searchFilter: " + searchFilter
//                + "\n startRangeDate: " + startRangeDateStr + "\n rowsPerPage: " + rowsPerPage
//                + "\n pageNumber: " + pageNumber + "\n endRangeDate: " + endRangeDateStr);

        return manager.getWatchdogAlertsByEntity(entityType, idEntity, searchFilter,
                DateUtils.getDateByString(startRangeDateStr), DateUtils.getDateByString(endRangeDateStr), pageNumber, rowsPerPage);
    }
}
