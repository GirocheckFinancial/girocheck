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
import com.smartbt.vtsuite.servercommon.dao.ApplicationDAO;
import com.smartbt.vtsuite.servercommon.model.Application;
import com.smartbt.vtsuite.servercommon.validators.ApplicationValidator;
import com.smartbt.vtsuite.vtcommon.Constants;
import org.apache.log4j.Logger;

/**
 *
 * @author Ariel Saavedra
 */
public class ApplicationManager {

    private static final Logger log = Logger.getLogger(ApplicationManager.class);
    private ApplicationDAO applicationDAO = ApplicationDAO.get();

    /**
     * Get all the Applications
     *
     * @return
     * @throws java.lang.Exception
     */
    public ResponseDataList getApplications() throws Exception {
        ApplicationValidator.getApplications();
        ResponseDataList response = new ResponseDataList();
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
        response.setData(applicationDAO.getApplications());
        return response;
    }

    /**
     * Add a Application
     *
     * @param application
     * @return
     * @throws java.lang.Exception
     */
    public BaseResponse addApplication(Application application) throws Exception {
        ApplicationValidator.addApplication(application);
        BaseResponse response = new BaseResponse();
        applicationDAO.addApplication(application);
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
        return response;
    }
}
