/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.vtsuite.manager;

import com.smartbt.girocheck.servercommon.display.message.ResponseData;
import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
import com.smartbt.girocheck.servercommon.model.MobileClient;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import com.smartbt.vtsuite.common.VTSuiteMessages;
import com.smartbt.vtsuite.dao.RegistrationDAO;
import com.smartbt.vtsuite.mock.MockFrontMobileBusinessLogic;
import com.smartbt.vtsuite.vtcommon.Constants;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author suresh
 */
public class RegistrationManager {
 
    protected static RegistrationManager _this;

    public static RegistrationManager get() {
        if (_this == null) {
            _this = new RegistrationManager();
        }
        return _this;
    }

    public ResponseData register(LinkedHashMap params) {

        ResponseData response = new ResponseData();

        String username = (String) params.get("username");
        String password = (String) params.get("password");
        String ssn = (String) params.get("ssn");
        String deviceId = (String) params.get("deviceId");
        String email = (String) params.get("email");
        String phone = (String) params.get("phone");
        
        if(ssn!=null && ssn.equals("0")){
           Map registrationMap = MockFrontMobileBusinessLogic.get().processRegistration();
           response.setStatus(Constants.CODE_SUCCESS);
           response.setStatusMessage(VTSuiteMessages.SUCCESS);
           response.setData(registrationMap);
        }
        return response;
    }
}
