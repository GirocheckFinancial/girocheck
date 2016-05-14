/*
 ** File: AuditLogController.java
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

import com.smartbt.girocheck.servercommon.display.message.ResponseDataList;
import com.smartbt.vtsuite.servercommon.manager.AuditLogManager;
import com.smartbt.vtsuite.servercommon.manager.TransactionManager;
import com.smartbt.vtsuite.servercommon.utils.DateUtils;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Ariel Saavedra
 */
@Path("VTAMS")
public class AuditLogController {

    private TransactionManager manager = new TransactionManager();
//    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(AuditLogController.class);
    @Context
    private SecurityContext securityContext;
    AuditLogManager auditLogManager = AuditLogManager.get();

    /**
     * Search Audit Logs 
     * 
     * @param idEntity
     * @param entityType
     * @param searchFilter
     * @param startRangeDate
     * @param endRangeDate
     * @param pageNumber
     * @param rowsPerPage
     * @return
     * @throws java.lang.Exception
     */
    @POST
    @Path("searchAuditLogs")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseDataList searchAuditLogs(@FormParam("idEntity") int idEntity, @FormParam("entityType") EntityType entityType,
            @FormParam("searchFilter") String searchFilter, @FormParam("startRangeDate") String startRangeDate, @FormParam("endRangeDate") String endRangeDate,
            @FormParam("pageNumber") int pageNumber, @FormParam("rowsPerPage") int rowsPerPage) throws Exception {
//        log.info("Incoming parameters : \n idEntity: " + idEntity + " \n entityType: " + entityType.toString() + " \n searchFilter: " + searchFilter
//                + "\n startRangeDate: " + startRangeDate + "\n rowsPerPage: " + rowsPerPage
//                + "\n pageNumber: " + pageNumber + "\n endRangeDate: " + endRangeDate);
        
        return auditLogManager.searchAuditLogs(idEntity, entityType, searchFilter, DateUtils.getDateByString(startRangeDate), DateUtils.getDateByString(endRangeDate), pageNumber, rowsPerPage);
    }
}
