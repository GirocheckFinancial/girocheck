/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.girocheck;

import com.smartbt.girocheck.scan.CertegyInfoRequest;
import com.smartbt.girocheck.scan.CertegyInfoRes;
import com.smartbt.girocheck.scan.CheckInfoRequest;
import com.smartbt.girocheck.scan.CheckInfoRes;
import com.smartbt.girocheck.scan.PersonalInfoRequest;
import com.smartbt.girocheck.scan.PersonalInfoRes;
import com.smartbt.vtsuite.manager.FrontManager;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import java.util.Date;
import java.util.Map;
import javax.jws.WebService;

/**
 *
 * @author Edward Beckett :: <Edward.Beckett@smartbt.com>
 */
@WebService(serviceName = "Scan", portName = "ScanPort", endpointInterface = "com.smartbt.girocheck.scan.Scan", targetNamespace = "http://scan.girocheck.smartbt.com/", wsdlLocation = "WEB-INF/wsdl/Scan.wsdl")
public class Scan {

    public PersonalInfoRes personalInfo(PersonalInfoRequest arg0) throws Exception {

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[IStreamFront Scan] Receiving personalInfo at " + (new Date()), null);

        return new PersonalInfoRes().build(FrontManager.processTransaction(arg0));
    }

    public CheckInfoRes checkInfo(CheckInfoRequest arg0) throws Exception {
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "***[IStreamFront Scan] Receiving checkInfo at " + (new Date()), null);
          //    return new CheckInfoRes().mock("sbt", "sbt");
       return new CheckInfoRes().build(FrontManager.processTransaction(arg0));
    }

    public CertegyInfoRes certegyInfo(CertegyInfoRequest arg0) throws Exception {

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[IStreamFront Scan] certegyInfo", null);
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[IStreamFront Scan] certegyInfo argument: ", null);

        return new CertegyInfoRes().build(FrontManager.processTransaction(arg0));
    }

}
