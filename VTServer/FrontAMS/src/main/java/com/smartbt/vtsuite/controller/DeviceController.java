/*
 ** File: DeviceController.java
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
package com.smartbt.vtsuite.controller;

import com.smartbt.girocheck.servercommon.display.message.ResponseData;
import com.smartbt.girocheck.servercommon.display.message.ResponseDataList;
import com.smartbt.vtsuite.servercommon.manager.DeviceManager;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomUserPrivileges;
import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
/**
 *
 * @author Ariel Saavedra
 */
@Path("VTAMS")
public class DeviceController {

    @Context
    private HttpHeaders context;
    @Context
    private HttpServletResponse response;
    @Context
    private HttpServletRequest request;

    private DeviceManager manager = new DeviceManager();
//    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(DeviceController.class);
    /**
     * Get the Device by a given id
     *
     * @param idDevice
     * @return
     */
    @POST
    @Path("getDevice")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseData getDevice(@FormParam("id") int idDevice) {
//        log.info("Incoming parameters : \n idDevice: " + idDevice);

        return manager.getDevice(idDevice);
    }
    
     /**
     * Get the Devices by a given Terminal's id
     *
     * @param idTerminal
     * @return
     */
    @POST
    @Path("getDevicesByTerminal")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseDataList getDevicesByTerminal(@FormParam("idTerminal") int idTerminal) {
//        log.info("Incoming parameters : \n idTerminal: " + idTerminal);

        return manager.getDevicesByTerminal(idTerminal);
    }
}
