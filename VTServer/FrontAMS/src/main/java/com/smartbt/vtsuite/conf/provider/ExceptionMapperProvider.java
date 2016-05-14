/*
 ** File: ExceptionMapperProvider.java
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
package com.smartbt.vtsuite.conf.provider;

import com.smartbt.girocheck.servercommon.display.message.BaseResponse;
import com.smartbt.vtsuite.vtcommon.Constants;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Ariel Saavedra
 */
@Provider
public class ExceptionMapperProvider implements ExceptionMapper<Exception> {

    @Context
    private UriInfo uriInfo;
//    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ExceptionMapperProvider.class);

    /**
     *
     * @param ex
     * @return
     */
    @Override
    public Response toResponse(Exception ex) {
        Response response;
        BaseResponse smartResponse = new BaseResponse();
        smartResponse.setStatusMessage(ex.getMessage());
        if (ex instanceof javax.ws.rs.ForbiddenException) {
            smartResponse.setStatus(Constants.CODE_NOT_PRIVILEGE);
            response = Response.status(Response.Status.OK).entity(smartResponse).build();
        } else if (ex instanceof javax.xml.bind.ValidationException) {
            smartResponse.setStatus(Constants.CODE_INVALID_ENTRY_DATA);
            response = Response.status(Response.Status.OK).entity(smartResponse).build();
        } else {
            ex.printStackTrace();
            smartResponse.setStatus(Constants.CODE_ERROR_GENERAL);
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(smartResponse).build();
        }
        response.getHeaders().add("rollback", true);
        return response;
    }
}
