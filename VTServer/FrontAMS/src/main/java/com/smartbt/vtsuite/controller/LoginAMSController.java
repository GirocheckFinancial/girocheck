/**
 *
 * Copyright @ 2004-2014 Smart Business Technology, Inc.
 *
 * All rights reserved. No part of this software may be 
 * reproduced, transmitted, transcribed, stored in a retrieval
 * system, or translated into any language or computer language, 
 * in any form or by any means, electronic, mechanical, magnetic,  
 * optical, chemical, manual or otherwise, without the prior  
 * written permission of Smart Business Technology, Inc.  
 *
 */

package com.smartbt.vtsuite.controller;

import com.smartbt.vtsuite.manager.LoginAMSManager;
import com.smartbt.girocheck.servercommon.display.message.BaseResponse;
import java.security.NoSuchAlgorithmException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.ValidationException;

/**
 *
 * @author Edward Beckett :: <Edward.Beckett@smartbt.com>
 */
@Path( "VTAMS" )
public class LoginAMSController {

    private LoginAMSManager manager = new LoginAMSManager();

//    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger( LoginAMSController.class );

    @POST
    @Path("authenticateUser")
    @Produces( MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public BaseResponse authenticateUser( @FormParam( "username" ) String username, @FormParam( "password" ) String password ) throws ValidationException, NoSuchAlgorithmException, Exception {
            System.out.println( "Incoming parameters : \n username :: " + username + " \n password :: " + password);
        
        return manager.authenticateUser( username, password);
       
    }
    
    @POST
    @Path("deleteSession")
//    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse deleteSession(@FormParam("token") String token) {
//        log.info("Incoming parameters : \n token to delete LoginAMSController.deleteSession() : " + token);
        
//        return AuditLogMessage.logDeleteUser(""+idUser,manager.deleteUser(entityType, idUser));
        return manager.deleteSession(token);
    }
}
