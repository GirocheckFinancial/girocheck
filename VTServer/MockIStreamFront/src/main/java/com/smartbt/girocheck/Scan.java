/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartbt.girocheck;

import com.smartbt.girocheck.scan.CertegyInfoRequest;
import com.smartbt.girocheck.scan.CertegyInfoRes;
import com.smartbt.girocheck.scan.PersonalInfoRequest;
import com.smartbt.girocheck.scan.PersonalInfoRes;
import com.smartbt.girocheck.servercommon.log.LogUtil;
import com.smartbt.girocheck.servercommon.enums.ResultCode;
import com.smartbt.girocheck.servercommon.enums.ResultMessage;
import com.smartbt.vtsuite.manager.FrontManager;
import com.smartbt.vtsuite.manager.MockManager;
import java.util.Date;
import javax.jws.WebService;

/**
 *
 * @author Edward Beckett :: <Edward.Beckett@smartbt.com>
 */
@WebService( serviceName = "Scan", portName = "ScanPort", endpointInterface = "com.smartbt.girocheck.scan.Scan", targetNamespace = "http://scan.girocheck.smartbt.com/", wsdlLocation = "WEB-INF/wsdl/Scan.wsdl" )
public class Scan {

     public PersonalInfoRes personalInfo( PersonalInfoRequest arg0 ) throws Exception {
         LogUtil.logAndStore("personalInfo", "FirstName = "+ arg0.getFirstName());
      
         
         
         MockManager.sendTheEmail("PersonalInfo", arg0.toMap());
         
         PersonalInfoRes res = new PersonalInfoRes();
         res.setCheckId(arg0.getCheckId());
         res.setResultCode(ResultCode.SUCCESS.getCode() + "");
         res.setResultMessage(ResultMessage.SUCCESS.getMessage());
         return res;
    }
  
      public CertegyInfoRes certegyInfo( CertegyInfoRequest arg0 ) throws Exception {
          MockManager.sendTheEmail("certegyInfo", arg0.toMap());
         CertegyInfoRes res = new CertegyInfoRes();
         res.setCheckId(arg0.getCheckId());
         res.setResultCode(ResultCode.SUCCESS.getCode() + "");
         res.setResultMessage(ResultMessage.SUCCESS.getMessage());
         return res;
    }
    
}
