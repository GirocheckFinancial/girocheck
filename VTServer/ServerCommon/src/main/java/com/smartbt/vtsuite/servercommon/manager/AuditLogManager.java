/*
 ** File: ApplicationManager.java
 **
 ** Date Created: March 2013
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
package com.smartbt.vtsuite.servercommon.manager;

import com.smartbt.vtsuite.common.VTSuiteMessages;
import com.smartbt.girocheck.servercommon.display.message.BaseResponse;
import com.smartbt.girocheck.servercommon.display.message.ResponseDataList;
import com.smartbt.vtsuite.servercommon.dao.AuditLogDAO;
import com.smartbt.vtsuite.servercommon.model.User;
import com.smartbt.vtsuite.servercommon.model.VTSession;
import com.smartbt.vtsuite.servercommon.validators.AuditLogValidator;
import com.smartbt.vtsuite.vtcommon.Constants;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import java.util.Date;
import org.apache.log4j.Logger;

/**
 *
 * @author Ariel Saavedra
 */
public class AuditLogManager {

    private static final Logger log = Logger.getLogger(AuditLogManager.class);
    private final AuditLogDAO auditLogDAO = AuditLogDAO.get();
    private static AuditLogManager auditLogManager;

    private AuditLogManager() {
    }

    public static AuditLogManager get() {
        if (auditLogManager == null) {
            auditLogManager = new AuditLogManager();
        }
        return auditLogManager;
    }

    /**
     * Add an AuditLog
     *
     * @param vTSession
     * @param user
     * @param details
     * @return
     */
    public BaseResponse addAuditLog(VTSession vTSession, User user, String details) {
        BaseResponse response = new BaseResponse();
        auditLogDAO.addAuditLog(vTSession, user, details);
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
        return response;
    }

    public ResponseDataList searchAuditLogs(int idEntity, EntityType entityType, String searchFilter, Date startRangeDate, Date endRangeDate, int pageNumber, int rowsPerPage) throws Exception {
        AuditLogValidator.searchAuditLogs(idEntity, entityType, searchFilter, startRangeDate, endRangeDate, pageNumber, rowsPerPage);
        ResponseDataList response = new ResponseDataList();
        response.setData(auditLogDAO.searchAuditLogs(idEntity, entityType, searchFilter, startRangeDate, endRangeDate, pageNumber * rowsPerPage, rowsPerPage));

        int total = auditLogDAO.searchAuditLogs(idEntity, entityType, searchFilter, startRangeDate, endRangeDate, -1, -1).size();
        response.setTotalPages((int) Math.ceil((float) total / (float) rowsPerPage));

        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
        return response;
    }
}
