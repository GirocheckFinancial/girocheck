/*
 ** File: BoardingController.java
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

import com.smartbt.girocheck.servercommon.i18n.I18N;
import com.smartbt.vtsuite.manager.BoardingAMSManager;
import com.smartbt.girocheck.servercommon.display.message.BaseResponse;
import com.smartbt.girocheck.servercommon.display.message.ResponseDataList;
import com.smartbt.vtsuite.servercommon.manager.BoardingManager;
import com.smartbt.vtsuite.servercommon.model.Customer;
import com.smartbt.vtsuite.servercommon.utils.DateUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.ValidationException;

/**
 *
 * @author Ariel Saavedra
 */
@Path("VTAMS")
public class BoardingController {

    @Context
    private HttpHeaders context;
    @Context
    private HttpServletResponse response;
    @Context
    private HttpServletRequest request;

    private BoardingManager manager = new BoardingManager();

    private BoardingAMSManager boardingAMSManager = new BoardingAMSManager();

//    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(BoardingController.class);

    /**
     * Search all the BoardingStatus by a given filter
     *
     * @param searchFilter
     * @param startRangeDateStr
     * @param endRangeDateStr
     * @param pageNumber
     * @param rowsPerPage
     * @return
     * @throws java.lang.Exception
     */
    @POST
    @Path("searchBoardingStatus")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseDataList searchBoardingStatus(@FormParam("searchFilter") String searchFilter, @FormParam("startRangeDate") String startRangeDateStr, @FormParam("endRangeDate") String endRangeDateStr,
            @FormParam("pageNumber") int pageNumber, @FormParam("rowsPerPage") int rowsPerPage) throws Exception {
//        log.info("Incoming parameters : \n searchFilter: " + searchFilter
//                + "\n startRangeDate: " + startRangeDateStr + "\n rowsPerPage: " + rowsPerPage
//                + "\n pageNumber: " + pageNumber + "\n endRangeDate: " + endRangeDateStr);

        return manager.searchBoardingStatus(searchFilter, DateUtils.getDateByString(startRangeDateStr), DateUtils.getDateByString(endRangeDateStr), pageNumber, rowsPerPage);
    }

    @PUT
    @Path("saveOrUpdateMerchantList")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse saveOrUpdateCustomer(Customer customer) throws ValidationException {
//        log.info("Incoming parameters : Boarding Data");
        return boardingAMSManager.saveOrUpdateCustomer(customer);
    }

}
