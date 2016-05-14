/*
 ** File: TerminalController.java
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

import com.smartbt.girocheck.servercommon.display.MerchantDisplay;
import com.smartbt.girocheck.servercommon.display.TerminalDisplay;
import com.smartbt.girocheck.servercommon.display.message.BaseResponse;
import com.smartbt.girocheck.servercommon.display.message.ResponseData;
import com.smartbt.girocheck.servercommon.display.message.ResponseDataList;
import com.smartbt.girocheck.servercommon.manager.TerminalManager;
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
public class TerminalController {

    @Context
    private HttpHeaders context;
    @Context
    private HttpServletResponse response;
    @Context
    private HttpServletRequest request;

    private TerminalManager manager = new TerminalManager();
//    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(TerminalController.class);
 
    @POST
    @Path("getTerminalsByMerchant")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseDataList getTerminalsByMerchant(@FormParam("idMerchant") int idMerchant) {
//        log.info("getTerminalsByMerchant :: Incoming parameters : \n idMerchant: " + idMerchant);

           return manager.getTerminalByMerchant( idMerchant );
    }
    
    
    @PUT
    @Path("saveOrUpdateTerminal")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseData saveOrUpdateTerminal(TerminalDisplay terminal) throws Exception {
//        log.info("saveOrUpdateTerminal :: Incoming parameter : \n terminal: ");
         terminal.print();
        return manager.saveOrUpdateTerminal(terminal);
        
    }
    
    @POST
    @Path("getTerminalById")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseData getTerminalById(@FormParam("id") int id) throws Exception {
//        log.info("getTerminalById :: Incoming parameter : \n merchant: id :: " + id);
       
        return manager.getTerminalById( id );
        
    }
    
    @POST
    @Path("deleteTerminal")
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse deleteTerminal(@FormParam("id") int id) throws Exception {
//        log.info("deleteTerminal::  Incoming parameter : \n idEntity: id = " + id);
        return manager.deleteTerminal(id);
    }
  
}
