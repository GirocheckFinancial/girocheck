/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartbt.girocheck;

import com.smartbt.girocheck.scan.CheckAuthRequest;
import com.smartbt.girocheck.scan.CheckAuthRes;
import com.smartbt.girocheck.scan.OrderExpressRequest;
import com.smartbt.girocheck.scan.OrderExpressRes;
import com.smartbt.girocheck.servercommon.log.LogUtil;
import com.smartbt.girocheck.servercommon.enums.ResultCode;
import com.smartbt.girocheck.servercommon.enums.ResultMessage;
import com.smartbt.vtsuite.manager.FrontManager;
import javax.jws.WebService;

/**
 *
 * @author Edward Beckett :: <Edward.Beckett@smartbt.com>
 */
@WebService( serviceName = "Scan", portName = "ScanPort", endpointInterface = "com.smartbt.girocheck.scan.Scan", targetNamespace = "http://scan.girocheck.smartbt.com/", wsdlLocation = "WEB-INF/wsdl/Scan.wsdl" )
public class Scan {

     public OrderExpressRes orderExpress( OrderExpressRequest arg0 ) throws Exception {
         LogUtil.logAndStore("orderExpress", "FirstName = "+ arg0.getFirstName());
       return new OrderExpressRes().build(FrontManager.processTransaction(arg0));
    }
  
      public CheckAuthRes checkAuth( CheckAuthRequest arg0 ) throws Exception {
          LogUtil.log( "Scan Interface", "Entro al Scan");
          
        return new CheckAuthRes().build(FrontManager.processTransaction(arg0));
    }
    
}
