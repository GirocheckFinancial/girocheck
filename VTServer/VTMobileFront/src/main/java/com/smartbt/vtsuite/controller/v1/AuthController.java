/*
 ** File: AuthController.java
 **
 ** Date Created: November 2014
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
package com.smartbt.vtsuite.controller.v1;

import com.smartbt.girocheck.common.VTSuiteMessages;
import com.smartbt.girocheck.servercommon.display.message.ResponseData;
import com.smartbt.girocheck.servercommon.display.mobile.MobileClientDisplay;
import com.smartbt.girocheck.servercommon.utils.PasswordUtil;
import com.smartbt.girocheck.servercommon.utils.Utils;
import com.smartbt.vtsuite.conf.interceptors.ExceptionInterceptor;
import static com.smartbt.vtsuite.controller.v1.GeneralController.TOKEN;
import com.smartbt.vtsuite.manager.AuthManager;
import com.smartbt.vtsuite.manager.TransactionManager;
import com.smartbt.vtsuite.vtcommon.Constants;
import java.util.LinkedHashMap;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Roberto Rodriguez
 */
@RestController 
@RequestMapping("/v1/auth")
public class AuthController {
    
    @Autowired
    AuthManager authManager;
    
    @Autowired
    TransactionManager transactionManager;
    
    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public String ping() throws Exception {
        
        return "PING!!";
    }
   
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseData login(@RequestBody LinkedHashMap params, HttpSession session) throws Exception {
        String username = (String) params.get("username");
        String password = (String) params.get("password"); 
        
        String token = Utils.generateToken();
        session.setAttribute(TOKEN, token);
        
        System.out.println("AuthController.login");
        
        ResponseData response = ResponseData.OK();
        String encryptPassword = PasswordUtil.encryptPassword(password);
        MobileClientDisplay mobileClient = authManager.getMobileClientDisplayByUserAndPassword(username, encryptPassword);
        
        if (mobileClient == null) {
            response.setStatus(Constants.CODE_INVALID_USER);
            response.setStatusMessage(VTSuiteMessages.INVALID_LOGIN_CREDENTIALS);
        } else {            
            
            mobileClient.setToken(token);            
            String balance = transactionManager.balanceInquiry(mobileClient.getCard(), mobileClient.getToken());
            mobileClient.setBalance(balance);
            response.setData(mobileClient);
        }
         
        return response;
    }
    
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ResponseData logout(@RequestBody LinkedHashMap params, HttpSession session) throws Exception {       
        
        System.out.println("AuthController.logout");        
        String token = (String) params.get("token"); 
        
        ResponseData response = ResponseData.OK();        
        
        if (token == null || token.isEmpty()) {
            response.setStatus(Constants.INVALID_TOKEN);
            response.setStatusMessage(VTSuiteMessages.INVALID_TOKEN);
        } else {      
            session.removeAttribute(TOKEN);            
        }
        
        return response;
    }
}