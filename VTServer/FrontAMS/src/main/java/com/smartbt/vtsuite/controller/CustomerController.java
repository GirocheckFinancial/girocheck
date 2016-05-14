/*
 ** File: CustomerController.java
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

import com.smartbt.girocheck.servercommon.display.message.ResponseDataList;
import com.smartbt.vtsuite.servercommon.manager.CustomerManager;
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
public class CustomerController {

    @Context
    private HttpHeaders context;
    @Context
    private HttpServletResponse response;
    @Context
    private HttpServletRequest request;

    private CustomerManager manager = new CustomerManager();
//    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(CustomerController.class);

    /**
     * Get the Customer by a given id
     *
     * @param idCustomer
     * @return
     */
    @POST
    @Path("getCustomer")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseDataList getCustomer(@FormParam("id") int idCustomer) {
//        log.info("Incoming parameters : \n idCustomer: " + idCustomer);
        return manager.getCustomer(idCustomer);
    }

    /**
     * Search all Customers
     *
     * @return
     */
    @POST
    @Path("getCustomers")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseDataList getCustomers() {
//        log.info("Incoming parameters :[none] ");
        return manager.getCustomers();
    }
}
