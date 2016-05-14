/*
 ** File: GeneralController.java
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

import com.smartbt.girocheck.common.SessionAMSUser;
import com.smartbt.vtsuite.common.VTSuiteMessages;
import com.smartbt.vtsuite.conf.security.UserContext;
import com.smartbt.girocheck.servercommon.display.CountryDisplay;
import com.smartbt.vtsuite.manager.GeneralAMSManager;
import com.smartbt.girocheck.servercommon.display.message.BaseResponse;
import com.smartbt.girocheck.servercommon.display.message.ResponseData;
import com.smartbt.girocheck.servercommon.display.message.ResponseDataList;
import com.smartbt.girocheck.servercommon.manager.CardProgramManager;
import com.smartbt.girocheck.servercommon.manager.CountryManager;
import com.smartbt.girocheck.servercommon.manager.StateManager;
import com.smartbt.vtsuite.servercommon.display.common.model.StateDisplay;
import com.smartbt.vtsuite.servercommon.manager.GeneralManager;
import com.smartbt.vtsuite.servercommon.manager.SystemPropertyManager;
import com.smartbt.vtsuite.servercommon.model.TelephoneType;
import com.smartbt.vtsuite.vtcommon.Constants;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

/**
 *
 * @author Ariel Saavedra
 */
@Path("VTAMS")
public class GeneralController {

    @Context
    private SecurityContext securityContext;
    private GeneralManager manager = new GeneralManager();
    private GeneralAMSManager localManager = new GeneralAMSManager();
    private SystemPropertyManager systemPropertyManager = new SystemPropertyManager();
//    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(GeneralController.class);

    /**
     * HeartBeat WebService
     *
     * @return
     */
    @POST
//    @RolesAllowed("Administrator")
    @Path("doHeartBeat")
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse doHeartBeat() {
//        log.info("Incoming parameters : [none]");
//        log.info("Incoming parameters doHeartBeat() -> SessionAMSUser.get(): "+SessionAMSUser.get().getUsername());        
        return localManager.getSettings(SessionAMSUser.get());
    }

    /**
     * Get system property by a given name
     *
     * @param name the system property name
     * @return
     */
    @POST
    @Path("getSystemProperty")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseData getSystemProperty(@FormParam("name") String name) {
//        log.info("Incoming parameters : \n name: " + name);

        return systemPropertyManager.getSystemProperty(name);
    }

    /**
     *
     * @return
     */
    @POST
    @Path("listStates")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseDataList listStates() {
        return new StateManager().listStates();
    }
    /**
     *
     * @return
     */
    @POST
    @Path("listCountry")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseDataList listCountry() {
       return new CountryManager().listCountries();
    }
   
    
    @POST
    @Path("listCardProgram")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseDataList listCardProgram() {
       return new CardProgramManager().listCardProgram();
    }

    /**
     *
     * @return
     */
    @POST
    @Path("listPhoneTypes")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseDataList listPhoneTypes() {
        ResponseDataList response = new ResponseDataList();
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
       
        
        List<TelephoneType> list = new ArrayList<TelephoneType>();
        TelephoneType t = new TelephoneType();
        t.setId(1);
        t.setName("Movil");
        list.add(t);
        
         response.setData(list);
         return response;
//        return manager.listPhoneTypes();
    }

    /**
     *
     * @return
     */
    @POST
    @Path("listDataTypes")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseDataList listDataTypes() {
        return manager.listDataTypes();
    }
}
