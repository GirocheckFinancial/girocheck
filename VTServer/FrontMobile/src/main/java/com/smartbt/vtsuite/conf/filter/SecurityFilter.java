/*
 ** File: SecurityFilter.java
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
package com.smartbt.vtsuite.conf.filter;

import com.smartbt.girocheck.common.SessionAMSUser;
import com.smartbt.girocheck.servercommon.dao.VTSessionDAO;
import com.smartbt.girocheck.servercommon.display.message.BaseResponse;
import com.smartbt.girocheck.servercommon.model.VTSession;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import com.smartbt.vtsuite.vtcommon.Constants;
import java.io.IOException;
import java.util.Map;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Ariel Saavedra
 */
@Provider
@PreMatching
public class SecurityFilter implements ContainerRequestFilter {

    @Context
    private HttpHeaders context;
    @Context
    private UriInfo uriInfo;
    private static final String USER_NAME = "USER_NAME";
    private static final String GROUPS = "GROUPS";

    /**
     *
     * @param requestContext
     * @throws IOException
     */
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        
//        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[VTAMS] Incoming uri to the security filter" + uriInfo.getPath(),null);
    
        VTSession vTSession = null;
        if (!uriInfo.getPath().contains("VTAMS/authenticateUser")) {
            HibernateUtil.beginTransaction();
            BaseResponse baseResponse = new BaseResponse();

            String token = context.getRequestHeader("TOKEN") == null ? null : context.getRequestHeader("TOKEN").get(0);
             
            if(token != null && token.contains("_")){
                token = token.split("_")[1];
            }
             
            int code = VTSessionDAO.get().validateSession(token);
            if (code == Constants.CODE_SESSION_LOST) {
                baseResponse.setStatus(Constants.CODE_SESSION_LOST);
                requestContext.abortWith(Response
                        .status(Response.Status.OK)
                        .entity(baseResponse)
                        .build());
                return;
            }
             if (code == Constants.CODE_SESSION_EXPIRE) {
                baseResponse.setStatus(Constants.CODE_SESSION_EXPIRE);
                requestContext.abortWith(Response
                        .status(Response.Status.OK)
                        .entity(baseResponse)
                        .build());
                return;
            }
            vTSession = authenticate(token);
            SessionAMSUser.set(vTSession.getUser());
        }
    }
    
     /**
     * Perform the required authentication checks, and return the VTSession
     * instance for the authenticated user.
     */
    private VTSession authenticate(String token) {
        return VTSessionDAO.get().getSessionByToken(token);
    }
}