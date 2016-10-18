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

import com.smartbt.girocheck.servercommon.display.message.ResponseData;
import com.smartbt.girocheck.servercommon.display.message.ResponseDataList;
import com.smartbt.girocheck.servercommon.manager.FeeBucketsManager;
import com.smartbt.girocheck.servercommon.manager.SubTransactionManager;
import com.smartbt.girocheck.servercommon.manager.TransactionManager;
import com.smartbt.vtsuite.servercommon.utils.DateUtils;
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
public class TransactionController {

    @Context
    private HttpHeaders context;
    @Context
    private HttpServletResponse response;
    @Context
    private HttpServletRequest request;
    private TransactionManager manager = TransactionManager.get();
    private SubTransactionManager subTransactionManager = new SubTransactionManager();
//    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(TransactionController.class);

    /**
     * Get the Transactions by a given Merchant's id
     *
     * @param idEntity
     * @param entityType
     * @param searchFilter
     * @param pageNumber
     * @param rowsPerPage
     * @param startRangeDateStr
     * @param endRangeDateStr
     * @return
     * @throws java.lang.Exception
     */
    @POST
    @Path("searchTransactions")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseDataList searchTransactions( 
            @FormParam("searchFilter") String searchFilter, @FormParam("startRangeDate") String startRangeDateStr, @FormParam("endRangeDate") String endRangeDateStr,
            @FormParam("transactionType") int transactionType, @FormParam("operation") String operation,
            @FormParam("pageNumber") int pageNumber, @FormParam("rowsPerPage") int rowsPerPage,
            @FormParam("filterAmmount") boolean filterAmount, @FormParam("ammountType") int amountType, @FormParam("opType") int opType, @FormParam("ammount") String amount, 
            @FormParam("pending") boolean pending) throws Exception {
        
//        log.info("Incoming parameters : \n  searchFilter: " + searchFilter
//                + "\n startRangeDate: " + startRangeDateStr + "\n rowsPerPage: " + rowsPerPage
//                + "\n pageNumber: " + pageNumber + "\n endRangeDate: " + endRangeDateStr + "\n transactionType: " + transactionType + "\n operation: " + operation
//                + "\n filterAmmount: " + filterAmmount + "\n ammountType: " + ammountType + "\n opType: " + opType + "\n ammount: " + ammount+"\n pending: " + pending);

        return manager.searchTransactions( searchFilter, DateUtils.getDateByString(startRangeDateStr), DateUtils.getDateByString(endRangeDateStr),transactionType, operation, pageNumber, rowsPerPage,
                filterAmount,amountType,opType,amount, pending);
    }

    @POST
    @Path("listSubTransactions")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseDataList listSubTransactions( @FormParam("idTransaction") int idTransaction) throws Exception {
        
//        log.info("listSubTransactions :: Incoming parameters : \n  idTransaction: " + idTransaction);

        return subTransactionManager.listSubTransactions( idTransaction);
    }
    
    @POST
    @Path("getTransactionImage")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseData getTransactionImage( @FormParam("idTransaction") int idTransaction) throws Exception {
        
//        log.info("TransactionController.getTransactionImage() :: Incoming parameters : \n  idTransaction: " + idTransaction);

        return manager.getTransactionImage(idTransaction);
    }
    
}
