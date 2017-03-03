/**
 *
 * Copyright
 *
 * @ 2004-2014 Smart Business Technology, Inc.
 *
 * All rights reserved. No part of this software may be reproduced, transmitted,
 * transcribed, stored in a retrieval system, or translated into any language or
 * computer language, in any form or by any means, electronic, mechanical,
 * magnetic, optical, chemical, manual or otherwise, without the prior written
 * permission of Smart Business Technology, Inc.
 *
 */
package com.smartbt.vtsuite.controller.v1;

import com.smartbt.girocheck.common.VTSuiteMessages;
import com.smartbt.girocheck.servercommon.dao.VTSessionDAO;
import com.smartbt.vtsuite.manager.LoginManager;
import com.smartbt.girocheck.servercommon.display.message.ResponseData;
import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.model.MobileClient;
import com.smartbt.girocheck.servercommon.model.VTSession;
import com.smartbt.vtsuite.manager.TransactionManager;
import com.smartbt.vtsuite.vtcommon.Constants;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.ValidationException;

@Path("v1/auth")
public class AuthController {

    private LoginManager manager = new LoginManager();
    private TransactionManager txnManager = new TransactionManager();

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseData login(LinkedHashMap params) throws ValidationException, NoSuchAlgorithmException, Exception {
        System.out.println("AuthController.login:: Incoming parameters : \n username :: " + params.get("username") + " \n password :: " + params.get("password") + " \n pin :: " + params.get("pin") + " \n deviceId :: " + params.get("deviceId"));
        MobileClient mobileClient = new MobileClient();
        ResponseData response = new ResponseData();
        VTSession userSession = null;
        String username = (String) params.get("username");
        String password = (String) params.get("password");
        String pin = (String) params.get("pin");
        String deviceId = (String) params.get("deviceId");
        try {
            if (pin != null && !pin.isEmpty()) {

                if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
                    //If get's here, the user is trying to login with the username and password 
                    //And setting a pin at the same time
                    mobileClient = manager.getMobileClientByUserNameAndPwd(username, password);                   

                } else {
                    //If get's here, the user is trying to login with the PIN  
                    mobileClient = manager.getMobileClientByPINAndDeviceId(pin, deviceId); 
                }
            } else {
                //Normal login       
                mobileClient = manager.getMobileClientByUserNameAndPwd(username, password);
                if (mobileClient != null) {
                    mobileClient.setDeviceId(deviceId);
                    mobileClient = manager.saveorUpdate(mobileClient);
                }                  

            }

            //DONE--- This block is common for 3 types of login 
            //moved to after the 3 types of login logic
            if (mobileClient != null) {
                userSession = createSession(mobileClient);
                if (userSession != null) {
                    response.setStatus(Constants.CODE_SUCCESS);
                    response.setStatusMessage(VTSuiteMessages.SUCCESS);
                } else {
                    failLogin(response);
                }
            } else {
                failLogin(response);
            }

            //---------------------
            if (response.getStatus() == Constants.CODE_SUCCESS) {
                //to return clientid, token and technicard balance
                Map data = new HashMap();
                if (mobileClient != null) {
                    data.put("clientId", mobileClient.getClient().getId());
                }
                if (userSession != null) {
                    data.put("token", userSession.getToken());
                }
                //DONE is balanceInquiry :-)
                //DONE (NEW requirement)
                //Like we fo in register, we need to differenciate 
                //when it fails by login or by consulting balance

                //If balanceInquiry fails, return balance as -1
                //Notice there are two possible type of failures:
                //1-Tecnicard return undexpected result code
                //2-There was an exception calling balance Inquiry
                
                Map balanceMap = txnManager.balanceInquiry(mobileClient.getClient().getId());
                data.put("balance", balanceMap.get(ParameterName.BALANCE)); //DONE consume balance inquiry and send the balance here                              
                response.setData(data);
            }
        } catch (Exception e) {           
           e.printStackTrace();
        }
        return response;
    }

    private void failLogin(ResponseData response) {
        failLogin(response, VTSuiteMessages.INVALID_LOGIN_CREDENTIALS);
    }

    private void failLogin(ResponseData response, String message) {
        response.setStatus(Constants.CODE_INVALID_USER);
        response.setStatusMessage(message);
    }

    private VTSession createSession(MobileClient user) {
        VTSession session = VTSessionDAO.get().saveOrUpdateSession(user);
        return session;
    }

    @GET
    @Path("ping")
    @Produces(MediaType.APPLICATION_JSON)
    public String ping() {
        return "WORKING!!!";
    }
}
