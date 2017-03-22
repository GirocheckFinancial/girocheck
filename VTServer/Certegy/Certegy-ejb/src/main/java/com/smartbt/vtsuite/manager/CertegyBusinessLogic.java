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

import com.smartbt.girocheck.servercommon.enums.ResultCode;
import com.smartbt.girocheck.servercommon.enums.ResultMessage;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import com.smartbt.vtsuite.boundary.PCA;
import com.smartbt.vtsuite.boundary.PCARequest;
import com.smartbt.vtsuite.boundary.PCAResponse;
import com.smartbt.vtsuite.boundary.PCAReverseRequest;
import com.smartbt.vtsuite.boundary.PCAReverseResponse;
import com.smartbt.vtsuite.boundary.PCAService;
import java.math.BigDecimal; 
import java.util.HashMap;
import java.util.Map;

/**
 * Mpowa Business Logic Class
 */
public class CertegyBusinessLogic {

    public static final String CERTEGY_SITE_ID = "2897891071345202";
    public static final BigDecimal CERTEGY_VERSION = new BigDecimal("1.2");

    private static CertegyBusinessLogic INSTANCE;

    private PCAService service;
    private PCA port;
 
    public static synchronized CertegyBusinessLogic get() {
        if (INSTANCE == null) {
            INSTANCE = new CertegyBusinessLogic();
        }
        return INSTANCE;
    }

    public CertegyBusinessLogic() {
        service = new PCAService();
        port = service.getPCAPort();
    }

    public DirexTransactionResponse process(DirexTransactionRequest request) throws Exception {
        Map transactionData = request.getTransactionData();
        DirexTransactionResponse direxTransactionResponse = new DirexTransactionResponse();

        TransactionType transactionType = request.getTransactionType();

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CertegyBusinessLogic] proccessing:: " + transactionType, null);

        String resultCode = "";
        switch (transactionType) {
            case CERTEGY_AUTHENTICATION:
                resultCode = combinedEnrollmentAuthentication(transactionData);
                break;
            case CERTEGY_REVERSE_REQUEST:
                resultCode = reverseRequest(transactionData);
                break;
        }
        
        System.out.println("[CertegyBusinessLogic] :: resultCode = " + resultCode);

        if (resultCode == null || !resultCode.equals("00")) {
            direxTransactionResponse = DirexTransactionResponse.forException(ResultCode.CERTEGY_DENY, ResultMessage.CERTEGY_DENY);
            direxTransactionResponse.setErrorCode(resultCode);
            return direxTransactionResponse;
        }

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CertegyBusinessLogic] Finish " + transactionType, null);

        direxTransactionResponse = DirexTransactionResponse.forSuccess();

        return direxTransactionResponse;
    }

    public String combinedEnrollmentAuthentication(Map params) {
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CertegyBusinessLogic] Calling method insertTransaction", null);

        PCARequest request = PCARequest.build(params);
        
        System.out.println(request.toString());
        
        PCAResponse response = port.authorize(request);
        return response != null ? response.getResponseCode() : "";
    }

    public String reverseRequest(Map params) {
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CertegyBusinessLogic] Calling method cancelationRequest", null);

        PCAReverseRequest request = PCAReverseRequest.build(params);
        PCAReverseResponse response = port.reverse(request);
        return response != null ? response.getResponseCode() : "";
    }
 
}
