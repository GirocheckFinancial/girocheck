/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.vtsuite.controller;

import com.smartbt.girocheck.servercommon.display.message.BaseResponse;
import com.smartbt.girocheck.servercommon.display.message.ResponseDataList;
import com.smartbt.vtsuite.manager.CheckResendManager;
import com.smartbt.vtsuite.servercommon.utils.DateUtils;
import java.security.NoSuchAlgorithmException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import javax.xml.bind.ValidationException;

/**
 *
 * @author suresh
 */
@Path("VTAMS")
public class CheckResendController {

    @Context
    private HttpHeaders context;
    @Context
    private HttpServletResponse response;
    @Context
    private HttpServletRequest request;
    private CheckResendManager manager = new CheckResendManager();
    @Context
    private SecurityContext securityContext;
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(com.smartbt.vtsuite.controller.CheckResendController.class);

    /**
     * Search all the Users by a given filter
     *
     * @param searchFilter
     * @param pageNumber
     * @param rowsPerPage
     * @return
     */
    @POST
    @Path("searchCheckDetails")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseDataList searchCheckDetails(@FormParam("pageNumber") int pageNumber, @FormParam("rowsPerPage") int rowsPerPage,
        @FormParam("status") String status, @FormParam("startRangeDate") String startRangeDateStr, @FormParam("endRangeDate") String endRangeDateStr) throws Exception {
        return manager.searchCheckDetails(pageNumber, rowsPerPage,status,DateUtils.getDateByString(startRangeDateStr),DateUtils.getDateByString(endRangeDateStr));
    }
    
    @POST
    @Path("resendCheck")
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse resendCheck(@FormParam("id") int id) throws ValidationException, NoSuchAlgorithmException, Exception {
        System.out.println("[CheckResendController] resendCheck()");
        return manager.resendCheck(id);
    }
}
