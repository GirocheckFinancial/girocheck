/*
 ** File: IStreamBusinessLogic.java
 **
 ** Date Created: February 2013
 **
 ** Copyright @ 2004-2013 Smart Business Technology, Inc.
 **
 ** All rights reserved. No part of this software may be 
 ** reproduced, transmitted, transcribed, stored in a retrieval 
 ** system, or translated into any language or computer language, 
 ** in any form or by any means, electronic, mechanical, magnetic, 
 ** optical, chemical, manual or otherwise, without the prior 
 ** written permission of Smart Business Technology, Inc.
 **
 */
package com.smartbt.vtsuite.manager;

import com.smartbt.vtsuite.boundary.ws.CheckAuthLocationConfigRequest;
import com.smartbt.vtsuite.boundary.ws.CheckAuthPollRequest;
import com.smartbt.vtsuite.boundary.ws.CheckAuthRequest;
import com.smartbt.vtsuite.boundary.ws.CheckAuthSubmitRequest;
import com.smartbt.vtsuite.boundary.ws.EnhancedCheckAuthPollRequest;
import com.smartbt.vtsuite.boundary.ws.Scan;
import com.smartbt.vtsuite.boundary.ws.Scan_Service;
import com.smartbt.girocheck.servercommon.enums.ResultCode;
import com.smartbt.girocheck.servercommon.enums.ResultMessage;

import com.smartbt.girocheck.servercommon.utils.IMap;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import com.smartbt.vtsuite.boundary.ws.CheckAuthRes;

import java.util.HashMap;
import java.util.Map;
import javax.xml.ws.BindingProvider;

public class IStreamBusinessLogic {

    private static IStreamBusinessLogic INSTANCE;

    public static synchronized IStreamBusinessLogic getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new IStreamBusinessLogic();
        }
        return INSTANCE;
    }

//    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(IStreamBusinessLogic.class);
    private Scan_Service service;
    private Scan port;

    public IStreamBusinessLogic() {
        try {
            service = new Scan_Service();
            port = service.getScanPort();
        } catch (Exception e) {
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, ">[IStreamBusinessLogic] Istream failed to create the Connection Port.", null);

            try {
                service = new Scan_Service();
                port = service.getScanPort();
            } catch (Exception e2) {
                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, ">[IStreamBusinessLogic] Istream failed to create the Connection Port by second time.", null);
                e2.printStackTrace();
            }
        }

        String url = "https://girocheck.istreamdeposit.com/Scan?wsdl";

//        if (url == null) {
//          url = "https://istreamdeposit.com/Scan?wsdl";
//        }
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, ">[IStreamBusinessLogic] URL: " + url, null);

        try {
            BindingProvider bp = (BindingProvider) port;
            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, url);
        } catch (Exception ex) {
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[IStreamBusinessLogic] IStream Conection Error. Error calling " + url, null);
            ex.printStackTrace();
        }

    }

    public DirexTransactionResponse process(DirexTransactionRequest request, int attempt) throws Exception {
        try {
            IMap response = null;
            Map transactionResponseMap = new HashMap();

            Map transactionData = request.getTransactionData();

            TransactionType transactionType = request.getTransactionType();

            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[IStreamBusinessLogic] procesing " + transactionType, null);
            switch (transactionType) {
                case ISTREAM_CHECK_AUTH_LOCATION_CONFIG:
                    response = port.checkAuthLocationConfig(new CheckAuthLocationConfigRequest().build(transactionData));
                    break;
                case ISTREAM_CHECK_AUTH:
                    CheckAuthRequest checkAuthRequest = new CheckAuthRequest().build(transactionData);
                    checkAuthRequest.printAsXML();

//                String checkAuthRequestAsString = checkAuthRequest.getAsXML();
//                
//                CustomeLogger.Output(CustomeLogger.OutputStates.Info, checkAuthRequestAsString,null);
                    response = port.checkAuth(checkAuthRequest);

                    if (((CheckAuthRes) response).getCheckId() == null || ((CheckAuthRes) response).getCheckId().isEmpty()) {
                        return DirexTransactionResponse.forException(ResultCode.ISTREAM_RETURN_CHECK_ID_NULL, ResultMessage.ISTREAM_RETURN_CHECK_ID_NULL);
                    }

                    System.out.println("[ISTREAMHOST] CHECKID: " + ((CheckAuthRes) response).getCheckId());
                    break;
                case ISTREAM_CHECK_AUTH_POLL:
                    response = port.checkAuthPoll(new CheckAuthPollRequest().build(transactionData));
                    break;
                case ISTREAM_ENHANCED_CHECK_AUTH_POLL:
                    response = port.enhancedCheckAuthPoll(new EnhancedCheckAuthPollRequest().build(transactionData));
                    break;
                case ISTREAM_CHECK_AUTH_SUBMIT:
                    response = port.checkAuthSubmit(new CheckAuthSubmitRequest().build(transactionData));
                    break;

                default:
                    CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[IStreamBusinessLogic] default...", null);
                    response = port.checkAuth(new CheckAuthRequest().build(transactionData));

                    if (((CheckAuthRes) response).getCheckId() == null || ((CheckAuthRes) response).getCheckId().isEmpty()) {
                        return DirexTransactionResponse.forException(ResultCode.ISTREAM_RETURN_CHECK_ID_NULL, ResultMessage.ISTREAM_RETURN_CHECK_ID_NULL);
                    }

                    break;

            }

            if (response != null) {
                transactionResponseMap = response.toMap();
            }

            DirexTransactionResponse direxTransactionResponse = new DirexTransactionResponse();
            direxTransactionResponse.setTransactionData(transactionResponseMap);

            return direxTransactionResponse;

        } catch (Exception e) {
            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[IStreamBusinessLogic] Host connection failed, Re-submitting transaction... attempt # " + attempt, null);
            e.printStackTrace();
            if (attempt == 0) {
                return DirexTransactionResponse.forException(ResultCode.ISTREAM_HOST_ERROR, ResultMessage.ISTREAM_FAILED, "IStream failed all attempts to connect.", "0");
            } else {
                Thread.sleep(3000);
                return process(request, attempt - 1);
            }
        }

    }

}
