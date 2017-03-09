/*
 ** File: GeneralController.java
 **
 ** Date Created: October 2014
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

import com.smartbt.girocheck.servercommon.display.message.ResponseData;
import com.smartbt.girocheck.servercommon.utils.Utils;
import java.util.LinkedHashMap;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.smartbt.vtsuite.manager.RegistrationManager;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Roberto Rodriguez
 */
@RestController
@RequestMapping("/v1/gen")
public class GeneralController {
    public static final String TOKEN = "TOKEN";
    @Autowired
    RegistrationManager regManager;
  
    private static final Logger log = Logger.getLogger(GeneralController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }
    
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseData register(@RequestBody LinkedHashMap params, HttpSession session) throws Exception{
        
        String username = (String) params.get("username");
        String password = (String) params.get("password");
        String ssn = (String) params.get("ssn");       
        String email = (String) params.get("email");
        String phone = (String) params.get("phone");         
        String cardNumber = (String) params.get("cardNumber");        
          
        System.out.println("GeneralController.register: \n username: " + username
                + "\n password: " + password
                + "\n ssn: " + ssn
                + "\n email: " + email
                + "\n phone: " + phone                
                + "\n cardNumber: **** **** **** " + cardNumber.substring(cardNumber.length() - 4));
        
        String token = Utils.generateToken();
        session.setAttribute(TOKEN, token);
        return regManager.register(username,password,ssn,email,phone,cardNumber,token);
    }
    
    @RequestMapping(value = "/replaceCard", method = RequestMethod.POST)
    public ResponseData replaceCard(@RequestBody LinkedHashMap params, HttpSession session) throws Exception{        
        
        String clientId = (String) params.get("clientId");         
        String cardNumber = (String) params.get("cardNumber");
          
        System.out.println("GeneralController.replaceCard: \n clientId: " + clientId
                    + "\n cardNumber: **** **** **** " + cardNumber.substring(cardNumber.length() - 4));
        
        String token = (String)session.getAttribute(TOKEN);
        return regManager.replaceCard(clientId,cardNumber,token);
    }
    
    @RequestMapping(value = "/updateProfile", method = RequestMethod.POST)
    public ResponseData updateProfile(@RequestBody LinkedHashMap params) throws Exception{        
        
        String clientId = (String) params.get("clientId");         
        String username = (String) params.get("username");
        String email = (String) params.get("email");
        String phone = (String) params.get("phone");
        String password = (String) params.get("password");                
        
          
         System.out.println("GeneralController.register: \n username: " + username
                + "\n password: " + password               
                + "\n email: " + email
                + "\n phone: " + phone                
                + "\n clientId: " + clientId);
        
        String token = Utils.generateToken();
        return regManager.updateProfile(clientId,username,email,phone,password,token);
    }
    

}
