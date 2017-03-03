/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.vtsuite.controller.v1;

import com.smartbt.girocheck.servercommon.display.message.ResponseData;
import com.smartbt.vtsuite.manager.RegistrationManager;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.ValidationException;

/**
 *
 * @author suresh
 */
@Path("v1/reg")
public class RegistrationController {
    private RegistrationManager regManager = new RegistrationManager();   
    
    @POST
    @Path("register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseData register(LinkedHashMap params) throws ValidationException, NoSuchAlgorithmException, Exception {
        System.out.println("AuthController.login:: Incoming parameters : \n phone :: " + params.get("phone") + " \n email :: " + params.get("email") + " \n ssn :: " + params.get("ssn") + " \n deviceId :: " + params.get("deviceId")+ " \n username :: " + params.get("username")+ " \n password :: " + params.get("password"));
        return regManager.register(params);
    }
        
 }
