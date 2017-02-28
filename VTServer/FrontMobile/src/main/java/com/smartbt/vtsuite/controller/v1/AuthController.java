/**
 *
 * Copyright @ 2004-2014 Smart Business Technology, Inc.
 *
 * All rights reserved. No part of this software may be reproduced, transmitted,
 * transcribed, stored in a retrieval system, or translated into any language or
 * computer language, in any form or by any means, electronic, mechanical,
 * magnetic, optical, chemical, manual or otherwise, without the prior written
 * permission of Smart Business Technology, Inc.
 *
 */
package com.smartbt.vtsuite.controller.v1;

import com.smartbt.vtsuite.manager.LoginAMSManager;
import com.smartbt.girocheck.servercommon.display.message.BaseResponse;
import com.smartbt.girocheck.servercommon.display.message.ResponseData;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.ValidationException;

@Path("v1/auth")
public class AuthController {

    private LoginAMSManager manager = new LoginAMSManager();

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseData login(LinkedHashMap params) throws ValidationException, NoSuchAlgorithmException, Exception {
        System.out.println("AuthController.login:: Incoming parameters : \n username :: " + params.get("username") + " \n password :: " + params.get("password") + " \n pin :: " + params.get("pin") + " \n deviceId :: " + params.get("deviceId"));

        String username = (String) params.get("username");
        String password = (String) params.get("password");
        String pin = (String) params.get("pin");
        String deviceId = (String) params.get("deviceId");

        if (pin != null && !pin.isEmpty()) {

            if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
                //If get's here, the user is setting a PIN
//                TODO
//                -Get MobileUser by username and password
//                -Set the PIN to the MobileUser
//                -Update deviceId in MobileUser
//                -Create sesion
//                -Return success 
            } else {
                //If get's here, the user is trying to login with the PIN
                //TODO
                //-Get MobileUser by PIN and deviceId
                //-Create sesion
                //-Return success 
            }
        } else {
            //Normal login
            //TODO
            // -Get MobileUser by username and password 
            // -Update deviceId in MobileUser
            // -Create sesion
            // -Return success 
        }
//        return manager.authenticateUser( username, password);

        
        
        Map data = new HashMap();
        data.put("clientId", "1"); //TODO Set the clientId here
        data.put("token", "provissional_token"); //TODO generate a TOKEN using the same logic than FrontAMS
        data.put("balance", "9.38"); //TODO consume balance inquiry and send the balance here
        
        return new ResponseData(data);
    }

//    @POST
//    @Path("deleteSession")
////    @Consumes("application/json")
//    @Produces(MediaType.APPLICATION_JSON)
//    public BaseResponse deleteSession(@FormParam("token") String token) {
////        log.info("Incoming parameters : \n token to delete AuthController.deleteSession() : " + token);
//
////        return AuditLogMessage.logDeleteUser(""+idUser,manager.deleteUser(entityType, idUser));
//        return manager.deleteSession(token);
//    }

    @GET
    @Path("ping")
    @Produces(MediaType.APPLICATION_JSON)
    public String ping() {
        return "WORKING!!!";
    }
}
 
 
