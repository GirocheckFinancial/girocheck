/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.vtsuite.controller.v1;

import com.smartbt.girocheck.servercommon.display.message.ResponseData;
import com.smartbt.vtsuite.manager.TransactionManager;
import java.util.Map;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author suresh
 */
@Path("v1/tx")
public class TransactionController {

    private TransactionManager manager = new TransactionManager();

    @GET
    @Path("history")
    @Produces(MediaType.APPLICATION_JSON)
    public Map history(
            @QueryParam("page") Integer page,
            @QueryParam("start") Integer start,
            @QueryParam("limit") Integer limit,
            @QueryParam("startDate") String startDate,
            @QueryParam("endDate") String endDate,
            @QueryParam("clientId") Integer clientId) throws Exception {
        System.out.println("TransactionController.history:: Incoming parameters : \n clientId :: " + clientId+ " \n page :: " + page + " \n start :: " + start + " \n limit :: " + limit + " \n startDate :: " + startDate + "\n endDate :: " + endDate);

        return manager.transactionHistory(clientId, page, start, limit, startDate, endDate);
    }
}
