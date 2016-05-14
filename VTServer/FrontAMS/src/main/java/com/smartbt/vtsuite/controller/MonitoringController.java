/*
 ** File: MonitoringController.java
 **
 ** Date Created: January 2014
 **
 ** Copyright @ 2004-2014 Smart Business Technology, Inc.
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
import com.smartbt.vtsuite.servercommon.manager.MonitoringManager;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Ariel Saavedra
 */
@Path("VTAMS")
public class MonitoringController {

    private MonitoringManager manager = new MonitoringManager();
//    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(MonitoringController.class);

    /**
     * Get a monitoring by a given id
     *
     * @param idMonitoring
     * @return ResponseData
     * @throws java.lang.Exception
     *
     */
    @POST
    @Path("getMonitoring")
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse getMonitoring(@FormParam("id") int idMonitoring) throws Exception {
//        log.info("Incoming parameter : \n monitoringId: " + idMonitoring);
        return manager.getMonitoring(idMonitoring);
    }

    
    /**
     * Search all the Monitoring by a given filter
     *
     * @param entityType
     * @param idEntity
     * @param searchFilter
     * @param pageNumber
     * @param rowsPerPage
     * @return ResponseDataList
     * @throws java.lang.Exception
     */
    @POST
    @Path("searchMonitoring")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseDataList searchMonitoring(@FormParam("entityType") EntityType entityType, @FormParam("idEntity") int idEntity,
            @FormParam("searchFilter") String searchFilter, @FormParam("pageNumber") int pageNumber, @FormParam("rowsPerPage") int rowsPerPage) throws Exception {
//        log.info("Incoming parameter : \n entityType: " + entityType.toString() + "\n idEntity: " + idEntity + "\n searchFilter: " + searchFilter
//                + "\n rowsPerPage: " + rowsPerPage + "\n pageNumber: " + pageNumber);

        return manager.searchMonitoring(entityType, idEntity, searchFilter, pageNumber, rowsPerPage);
    }

    /**
     * Start monitoring terminal
     *
     * @param idTerminal
     * @return BaseResponse
     * @throws java.lang.Exception
     */
    @POST
    @Path("startMonitoringTerminal")
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse startMonitoringTerminal(@FormParam("id") int idTerminal) throws Exception {
//        log.info("Incoming parameter : \nidTerminal: " + idTerminal);
        return manager.startMonitoringTerminal(idTerminal);
    }

     /**
     * Stop monitoring terminal
     *
     * @param idTerminal
     * @return 
     * @throws java.lang.Exception
     */
    @POST
    @Path("stopMonitoringTerminal")
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse stopMonitoringTerminal(@FormParam("id") int idTerminal) throws Exception {
//        log.info("Incoming parameter : \nidTerminal: " + idTerminal);
        return manager.stopMonitoringTerminal(idTerminal);
    }
}
