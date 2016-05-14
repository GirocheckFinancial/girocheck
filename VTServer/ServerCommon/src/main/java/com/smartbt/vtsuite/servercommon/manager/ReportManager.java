/*
 ** File: TransactionManager.java
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
import com.smartbt.girocheck.servercommon.display.message.ResponseData;
import com.smartbt.girocheck.servercommon.display.message.ResponseDataList;
import com.smartbt.vtsuite.servercommon.dao.ReportDAO;
import com.smartbt.vtsuite.servercommon.utils.validators.SbtServerCommonValidators;
import com.smartbt.vtsuite.vtcommon.Constants;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartbt.vtsuite.vtcommon.enums.ReportType;
import java.util.Date;
import org.apache.log4j.Logger;

/**
 *
 * @author Maite Gonzalez
 */
public class ReportManager {

    private static final Logger log = Logger.getLogger(TransactionManager.class);
    SbtServerCommonValidators validators = new SbtServerCommonValidators();
    private ReportDAO reportDAO = ReportDAO.get();

    /**
     * Create and save a report
     */
    public ResponseData generateAndSaveReport(String username, int idEntity, EntityType entityType, int idCkerk, int idTransactionMode,
        Date startRangeDate, Date endRangeDate, boolean justApproved, ReportType reportType) {
        ResponseData response = new ResponseData();
        try {
            response.setStatus(Constants.CODE_SUCCESS);
            response.setStatusMessage(VTSuiteMessages.SUCCESS);
            response.setData(reportDAO.generateAndSaveReport(username, idEntity, entityType, idCkerk, idTransactionMode, startRangeDate, endRangeDate, justApproved, reportType));

        } catch (Exception e) {
            log.error("----->  generateAndSaveReport: Error: " + e.getMessage() + "<-----", e);
            response.setStatus(Constants.CODE_ERROR_GENERAL);
            response.setStatusMessage(e.getMessage());
        }
        return response;
    }

    /**
     * Retrieve a previously generated report
     */
    public ResponseData getReport(String username, String token, int reportId) {
        ResponseData response = new ResponseData();
        try {
            response.setStatus(Constants.CODE_SUCCESS);
            response.setStatusMessage(VTSuiteMessages.SUCCESS);

            response.setData(reportDAO.getReport(username, token, reportId));
        } catch (Exception e) {
            log.error("----->  getReport: Error: " + e.getMessage() + "<-----", e);
            response.setStatus(Constants.CODE_ERROR_GENERAL);
            response.setStatusMessage(e.getMessage());
        }
        return response;
    }

}
