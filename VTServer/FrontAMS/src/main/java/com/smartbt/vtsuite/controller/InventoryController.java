/*
 ** File: TransactionController.java
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

import com.smartbt.girocheck.servercommon.display.InventoryDisplay;
import com.smartbt.girocheck.servercommon.display.MerchantDisplay;
import com.smartbt.girocheck.servercommon.display.message.ResponseData;
import com.smartbt.girocheck.servercommon.display.message.ResponseDataList;
import com.smartbt.girocheck.servercommon.manager.InventoryManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author
 */
@Path("VTAMS")
public class InventoryController {

    @Context
    private HttpHeaders context;
    @Context
    private HttpServletResponse response;
    @Context
    private HttpServletRequest request;
    private InventoryManager manager = InventoryManager.get();

    @POST
    @Path("searchInventory")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseDataList searchInventory(
            @FormParam("pageNumber") int pageNumber, @FormParam("rowsPerPage") int rowsPerPage) throws Exception {

        return manager.searchInventory(pageNumber, rowsPerPage);
    }

    @PUT
    @Path("saveOrUpdateInventory")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseData saveOrUpdateInventory(InventoryDisplay inventory) throws Exception {
        System.out.println("saveOrUpdateInventory :: Incoming parameter : \n inventory: " + inventory.getInventory() + ", Threshold = " + inventory.getThreshold());
       
        return manager.saveOrUpdateInventory(inventory);

    }
}
