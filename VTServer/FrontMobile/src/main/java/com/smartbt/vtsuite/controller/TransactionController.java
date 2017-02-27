/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.vtsuite.controller;

import com.smartbt.girocheck.servercommon.display.message.ResponseData;
import com.smartbt.vtsuite.manager.TransactionManager;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author suresh
 */

@Path( "VTAMS" )
public class TransactionController {
     private TransactionManager manager = new TransactionManager();
    
    @POST
    @Path("transactionHistory")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseData transactionHistory(@FormParam("clientId") int clientId,@FormParam("startPage") int startPage,@FormParam("limit") int limit,
         @FormParam("startDate") String startDateStr, @FormParam("endDate") String endDateStr) throws Exception {
        return manager.transactionHistory(clientId,startPage,limit,startDateStr,endDateStr);
    }
    
}
