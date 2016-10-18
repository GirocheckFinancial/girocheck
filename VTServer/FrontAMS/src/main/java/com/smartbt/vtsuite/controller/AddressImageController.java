/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartbt.vtsuite.controller;

import com.smartbt.girocheck.servercommon.display.message.ResponseData;
import com.smartbt.girocheck.servercommon.manager.TransactionManager;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Alejo
 */

@Path("VTAMS")
public class AddressImageController {
    
//    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(AddressImageController.class);
    
    TransactionManager tManager = TransactionManager.get();
    
    @POST
    @Path("getAddressFormImage")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseData getAddressFormImage(@FormParam("serialNumber") String serialNumber, @FormParam("rotate") boolean rotate) throws Exception {
//        log.info("Incoming parameters : \n serialNumber: " + serialNumber);
          return tManager.getAddressImageFromClientByTerminalSerialNumber(serialNumber,rotate);        
    }
    
}
