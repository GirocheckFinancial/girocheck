/*
 ** File: DashboardController.java
 **
 ** Date Created: December 2013
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
import com.smartbt.vtsuite.manager.DashboardAMSManager;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Ariel Saavedra
 */
@Path("VTAMS")
public class DashboardController {

    private DashboardAMSManager manager = new DashboardAMSManager();
//    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(DashboardController.class);

    /**
     * Get list of environmental dashboard
     *
     * @return
     */
    @POST
    @Path("getDashboardEnvironmental")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseDataList getDashboardEnvironmental() throws Exception {
//        log.info("Incoming parameters : \n searchFilter: [none]");

        return manager.getDashboardEnvironmental();
    }
}
