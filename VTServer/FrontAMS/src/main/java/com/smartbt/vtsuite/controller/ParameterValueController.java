/*
 ** File: ParameterController.java
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
import com.smartbt.vtsuite.servercommon.manager.ParameterValueManager;
import com.smartbt.vtsuite.servercommon.model.Application;
import com.smartbt.vtsuite.servercommon.model.ApplicationParameter;
import com.smartbt.vtsuite.servercommon.model.ApplicationParameterValue;
import com.smartbt.vtsuite.utils.AuditLogMessage;
import com.smartbt.vtsuite.vtcommon.Constants;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import java.util.ArrayList;
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
public class ParameterValueController {

    @Context
    private HttpHeaders context;
    @Context
    private HttpServletResponse response;
    @Context
    private HttpServletRequest request;
    private ParameterValueManager manager = new ParameterValueManager();
//    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ParameterValueController.class);
    @Context
    private SecurityContext securityContext;

    /**
     *
     * @param idEntity
     * @param entityType
     * @return
     */
    @POST
    @Path("getNotContainedParameters")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseDataList getNotContainedParameters(@FormParam("idEntity") int idEntity, @FormParam("entityType") EntityType entityType) {
//        log.info("Incoming parameters : \n idEntity: " + idEntity + "\n EntityType: " + entityType);
        return manager.getNotContainedParameters(idEntity, entityType);
    }

    /**
     *
     * @param idEntity
     * @param entityType
     * @param searchFilter
     * @param pageNumber
     * @param rowsPerPage
     * @return
     */
    @POST
    @Path("searchParametersValue")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseDataList searchParametersValue(@FormParam("idEntity") int idEntity, @FormParam("entityType") EntityType entityType,
            @FormParam("searchFilter") String searchFilter, @FormParam("pageNumber") int pageNumber, @FormParam("rowsPerPage") int rowsPerPage) {
//        log.info("Incoming parameters : \n idEntity: " + idEntity + "\n EntityType: " + entityType);

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

    /**
     *
     * @param idEntity
     * @param entityType
     * @param idDefaultParameter
     * @param value
     * @return
     * @throws java.lang.Exception
     */
    @PUT
    @Path("addParameterValue")
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse addParameterValue(@FormParam("idEntity") int idEntity, @FormParam("entityType") EntityType entityType,
            @FormParam("idParameter") int idDefaultParameter, @FormParam("value") String value) throws Exception {
//        log.info("Incoming parameters : \n idEntity: " + idEntity + "\n EntityType: " + entityType + "\n idDefaultParameter: " + idDefaultParameter + "\n value: " + value);

        return AuditLogMessage.logAddParameterValue(entityType.toString(), idEntity, value,
                manager.addParameterValue(idEntity, entityType, idDefaultParameter, value));
    }

    /**
     *
     * @param entityType
     * @param idParameterValue
     * @param value
     * @return
     * @throws javax.xml.bind.ValidationException
     */
    @PUT
    @Path("updateParameterValue")
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse updateParameterValue(@FormParam("entityType") EntityType entityType,
            @FormParam("idParameterValue") int idParameterValue, @FormParam("value") String value) throws ValidationException {
//        log.info("Incoming parameters : \n idParameter: " + idParameterValue + "\n value: " + value);

        return AuditLogMessage.logUpdateParameterValue(entityType.toString(), idParameterValue, value,
                manager.updateParameterValue(entityType, idParameterValue, value));
    }

    /**
     *
     * @param entityType
     * @param idParameter
     * @return
     * @throws javax.xml.bind.ValidationException
     */
    @DELETE
    @Path("deleteParameterValue")
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse deleteParameterValue(@QueryParam("entityType") EntityType entityType, @QueryParam("id") int idParameter) throws ValidationException {
//        log.info("Incoming parameters : \n idParameter: " + idParameter);

        return AuditLogMessage.logDeleteParameterValue(entityType.toString(), idParameter,
                manager.deleteParameterValue(entityType, idParameter));
    }
}
