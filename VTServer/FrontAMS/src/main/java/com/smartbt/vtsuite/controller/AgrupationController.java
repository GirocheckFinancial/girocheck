/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartbt.vtsuite.controller;

import com.smartbt.girocheck.servercommon.display.AgrupationDisplay;
import com.smartbt.girocheck.servercommon.display.message.BaseResponse;
import com.smartbt.girocheck.servercommon.display.message.ResponseData;
import com.smartbt.girocheck.servercommon.display.message.ResponseDataList;
import com.smartbt.girocheck.servercommon.manager.AgrupationManager;
import com.smartbt.girocheck.servercommon.model.Agrupation;
import com.smartbt.vtsuite.servercommon.display.common.model.ClientDisplay;
import com.smartbt.vtsuite.utils.AuditLogMessage;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Roberto Rodriguez   :: <roberto.rodriguez@smartbt.com>
 */
@Path("VTAMS")
public class AgrupationController {
//     private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ApplicationController.class);
    
     private AgrupationManager agrupationManager = new AgrupationManager();
    
    @POST
    @Path("listAgrupations")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseDataList listAgrupations() throws Exception {
//        log.info("listAgrupations:: Incoming parameters : [none]");

        return agrupationManager.listAgrupations();
    }
    
    /**
     *
     * @param agrupation
     * @return
     * @throws Exception
     */
    @PUT
    @Path("saveOrUpdateAgrupation")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseData saveOrUpdateAgrupation(AgrupationDisplay agrupation) throws Exception {
//        log.info("saveOrUpdateAgrupation :: Incoming parameter : \n agrupation: ");
         agrupation.print();
        return agrupationManager.saveOrUpdateAgrupation(agrupation);
        
    }
    
    @POST
    @Path("getAgrupation")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseData getAgrupation(@FormParam("id") int idAgrupation) {
//        log.info("Incoming parameters : \n idAgrupation: " + idAgrupation);
        return agrupationManager.getAgrupation(idAgrupation);
    }

    
    /**
     * Delete a UserRole
     *
     * @param entityType
     * @param idRole
     * @return
     */
    @POST
    @Path("deleteAgrupation")
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse deleteAgrupation(@FormParam("id") int id) {
//        log.info("** deleteAgrupation :: Incoming parameters : \n agrupation: " + id);
       
        return  agrupationManager.deleteAgrupation( id);
    }
    
     @POST
    @Path("searchAgrupations")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseDataList searchAgrupations(@FormParam("search") String search) throws Exception {
//        log.info("searchAgrupations:: Incoming parameters : [search] :: " + search);

        return agrupationManager.searchAgrupations( search );
    }
}
