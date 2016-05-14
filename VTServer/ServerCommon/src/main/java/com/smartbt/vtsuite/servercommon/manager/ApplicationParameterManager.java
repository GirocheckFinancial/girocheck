/*
 ** File: ApplicationParameterManager.java
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
package com.smartbt.vtsuite.servercommon.manager;

import com.smartbt.vtsuite.common.VTSuiteMessages;
import com.smartbt.vtsuite.servercommon.dao.ApplicationParameterDAO;
import com.smartbt.girocheck.servercommon.display.message.BaseResponse;
import com.smartbt.girocheck.servercommon.display.message.ResponseDataList;
import com.smartbt.vtsuite.servercommon.model.ApplicationParameter;
import com.smartbt.vtsuite.servercommon.validators.ApplicationParameterValidator;
import com.smartbt.vtsuite.vtcommon.Constants;
import org.apache.log4j.Logger;

/**
 *
 * @author Ariel Saavedra
 */
public class ApplicationParameterManager {

    private static final Logger log = Logger.getLogger(ApplicationParameterManager.class);
    private ApplicationParameterDAO applicationParameterDAO = ApplicationParameterDAO.get();

    /**
     * Save or update an application parameters
     *
     * @param applicationParameter
     * @return 
     * @throws java.lang.Exception
     */
    public BaseResponse saveOrUpdate(ApplicationParameter applicationParameter) throws Exception {
        ApplicationParameterValidator.saveOrUpdate(applicationParameter);
        BaseResponse response = new BaseResponse();
        applicationParameterDAO.saveOrUpdate(applicationParameter);
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
        return response;
    }

    /**
     * delete an application parameter
     *
     * @param applicationParameterId
     * @return
     * @throws javax.xml.bind.ValidationException
     */
    public BaseResponse delete(int applicationParameterId) throws Exception {
        ApplicationParameterValidator.delete(applicationParameterId);
        BaseResponse response = new BaseResponse();
        applicationParameterDAO.delete(applicationParameterId);
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
        return response;
    }

    /**
     * Search all application parameters
     *
     * @param searchFilter
     * @param pageNumber
     * @param rowsPerPage
     * @return ResponseDataList
     * @throws java.lang.Exception
     */
    public ResponseDataList search(String searchFilter, int pageNumber, int rowsPerPage)throws Exception{
        ApplicationParameterValidator.search(searchFilter, pageNumber, rowsPerPage);
        ResponseDataList response = new ResponseDataList();
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
        response.setData(applicationParameterDAO.search(searchFilter, pageNumber * rowsPerPage, rowsPerPage));

        int total = applicationParameterDAO.search(searchFilter, -1, -1).size();
        response.setTotalPages((int) Math.ceil((float) total / (float) rowsPerPage));
        return response;
    }

}
