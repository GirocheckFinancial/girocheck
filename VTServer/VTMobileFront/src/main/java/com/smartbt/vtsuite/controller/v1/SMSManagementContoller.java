/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.vtsuite.controller.v1;

import com.smartbt.girocheck.servercommon.display.message.ResponseData; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.smartbt.vtsuite.manager.SMSManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author suresh
 */
@RestController
@RequestMapping("/v1/sms")
public class SMSManagementContoller {

    @Autowired
    SMSManager smsManager;

    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public String ping() throws Exception {

        return "PING!!";
    }

    @RequestMapping(value = "/optOut", method = RequestMethod.GET)
    public ResponseData optOut(HttpServletRequest request, HttpSession session) throws Exception {
        String recipentNumber = (String) request.getParameter("recipentNumber");
        String messageKeyword = (String) request.getParameter("messageKeyword"); 
        ResponseData response = smsManager.optOut(recipentNumber, messageKeyword);        
        return response;
    }
}
