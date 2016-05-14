/*
 ** File: ApplicationParameterController.java
 **
 ** Date Created: Janauary 2014
 **
 ** Copyright @ 2004-2014 Smart Business Technology, Inc.
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

import com.smartbt.girocheck.servercommon.display.ApplicationParameterDisplay;
import com.smartbt.girocheck.servercommon.display.message.BaseResponse;
import com.smartbt.girocheck.servercommon.display.message.ResponseData;
import com.smartbt.girocheck.servercommon.manager.ApplicationParameterManager;
import com.smartbt.girocheck.servercommon.display.message.ResponseDataList;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/*
 *  @author Roberto Rodriguez :: <roberto.rodriguez@smartbt.com>
 */
@Path( "VTAMS" )
public class ParameterController {

    private ApplicationParameterManager manager = new ApplicationParameterManager();
//    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger( ParameterController.class );

    /**
     * @param searchFilter
     * @param pageNumber
     * @param rowsPerPage
     * @return
     * @throws java.lang.Exception
     */
    @POST
    @Path( "searchParameters" )
    @Produces( MediaType.APPLICATION_JSON )
    public ResponseDataList searchApplicationParameters( @FormParam( "searchFilter" ) String searchFilter, @FormParam( "pageNumber" ) int pageNumber, @FormParam( "rowsPerPage" ) int rowsPerPage ) throws Exception {
//        log.info( "----------- Incoming parameters : \n searchFilter: " + searchFilter + "\n pageNumber: " + pageNumber + "\n rowsPerPage: " + rowsPerPage );

        return manager.search( searchFilter, pageNumber, rowsPerPage );
    }

    /**
     *
     * @param applicationParameter
     * @return
     * @throws java.lang.Exception
     */
    @PUT
    @Path( "saveOrUpdateApplicationParameter" )
    @Produces( MediaType.APPLICATION_JSON )
    public ResponseData<ApplicationParameterDisplay> saveOrUpdateApplicationParameter( ApplicationParameterDisplay applicationParameter ) throws Exception {
//        log.info( "saveOrUpdateApplicationParameter:  Incoming parameters : \n applicationParameter: " );
        applicationParameter.print();
        return manager.saveOrUpdate( applicationParameter);
    }
//
//    /**
//     *
//     * @param applicationParameterId
//     * @return
//     * @throws java.lang.Exception
//     */
    @DELETE
    @Path("deleteApplicationParameter")
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse deleteApplicationParameter(@QueryParam("id") int applicationParameterId) throws Exception {
//        log.info("deleteApplicationParameter : \n applicationParameterId: " + applicationParameterId);

        return  manager.delete(applicationParameterId);
    }
}
