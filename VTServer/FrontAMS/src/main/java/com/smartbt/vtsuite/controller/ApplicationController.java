/*
 ** File: ApplicationController.java
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

import com.smartbt.girocheck.servercommon.display.message.BaseResponse;
import com.smartbt.girocheck.servercommon.display.message.ResponseDataList;
import com.smartbt.vtsuite.servercommon.manager.ApplicationManager;
import com.smartbt.vtsuite.servercommon.model.Application;
import com.smartbt.vtsuite.utils.AuditLogMessage;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Ariel Saavedra
 */
@Path("VTAMS")
public class ApplicationController {

    @Context
    private SecurityContext securityContext;

    private ApplicationManager manager = new ApplicationManager();
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ApplicationController.class);

   
//    @POST
//    @Path("listAgrupations")
//    @Produces(MediaType.APPLICATION_JSON)
//    public ResponseDataList getApplications() throws Exception {
//        log.info("listAgrupations:: Incoming parameters : [none]");
//
//        return manager.getApplications();
//    }
}
