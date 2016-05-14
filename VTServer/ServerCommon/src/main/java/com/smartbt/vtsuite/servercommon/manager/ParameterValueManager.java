/*
 ** File: ParameterManager.java
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
import com.smartbt.vtsuite.servercommon.dao.ParameterValueDAO;
import com.smartbt.girocheck.servercommon.display.message.BaseResponse;
import com.smartbt.girocheck.servercommon.display.message.ResponseDataList;
import com.smartbt.vtsuite.servercommon.model.Application;
import com.smartbt.vtsuite.servercommon.model.ApplicationParameter;
import com.smartbt.vtsuite.servercommon.model.ApplicationParameterValue;
import com.smartbt.vtsuite.servercommon.utils.validators.SbtServerCommonValidators;
import com.smartbt.vtsuite.servercommon.validators.ParameterValueValidator;
import com.smartbt.vtsuite.vtcommon.Constants;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.ValidationException;
import org.apache.log4j.Logger;

/**
 *
 * @author Ariel Saavedra
 */
public class ParameterValueManager {

    private static final Logger log = Logger.getLogger(ParameterValueManager.class);
    SbtServerCommonValidators validators = new SbtServerCommonValidators();
    private ParameterValueDAO parameterDAO = ParameterValueDAO.get();

    public ResponseDataList getNotContainedParameters(int idEntity, EntityType entityType) {
        ResponseDataList response = new ResponseDataList();
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
        response.setData(parameterDAO.getNotContainedParameters(idEntity, entityType));
        return response;
    }

    public ResponseDataList searchParametersValue(int idEntity, EntityType entityType, String searchFilter, int pageNumber, int rowsPerPage) {
        ResponseDataList response = new ResponseDataList();
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
        response.setData(getParameterList());

        int total = 1;
        response.setTotalPages(total);
        return response;
    }
    
    private List<ApplicationParameterValue> getParameterList(){
        List<ApplicationParameterValue> list = new ArrayList<ApplicationParameterValue>();
        
        for (int i = 0; i < 5; i++) {
            ApplicationParameterValue parameter = new ApplicationParameterValue();
            
            parameter.setValue("value" + i);
            
            Application application = new Application();
            application.setId(1);
            ApplicationParameter ap = new ApplicationParameter();
            ap.setId(i);
            ap.setParameter("Parameter" + i);
            ap.setDefaultValue("Default value");
            ap.setDescription("Description");
            
            parameter.setApplication(application);
            parameter.setApplicationParameter(ap);
            parameter.setId(i);
            parameter.setValue("Parameter "+ i);
            list.add(parameter);
        }
        return list;
    }

    public BaseResponse addParameterValue(int idEntity, EntityType entityType, int idParameter, String value) throws Exception {
        ParameterValueValidator.addParameterValue(idEntity, entityType, idParameter, value);
        BaseResponse response = new BaseResponse();

        parameterDAO.addParameterValue(idEntity, entityType, idParameter, value);
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);

        return response;
    }

    public BaseResponse updateParameterValue(EntityType entityType, int idParameterValue, String value) throws ValidationException {
        ParameterValueValidator.updateParameterValue(entityType, idParameterValue, value);
        BaseResponse response = new BaseResponse();

        parameterDAO.updateParameterValue(entityType, idParameterValue, value);
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
        return response;
    }

    public BaseResponse deleteParameterValue(EntityType entityType, int idParameterValue) throws ValidationException {
        ParameterValueValidator.deleteParameterValue(entityType, idParameterValue);
        BaseResponse response = new BaseResponse();
        parameterDAO.deleteParameterValue(entityType, idParameterValue);
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
        return response;
    }
}
