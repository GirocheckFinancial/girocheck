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
package com.smartbt.vtsuite.mock;

import com.smartbt.girocheck.servercommon.enums.ResultCode;
import com.smartbt.girocheck.servercommon.enums.ResultMessage;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import com.smartbt.vtsuite.boundary.PCARequest;
import com.smartbt.vtsuite.boundary.PCAReverseRequest;
import java.util.Map;

/**
 * Mpowa Business Logic Class
 */
public class MockCertegyBusinessLogic {
 
    private static MockCertegyBusinessLogic INSTANCE;
 
    public static synchronized MockCertegyBusinessLogic get() {
        if (INSTANCE == null) {
            INSTANCE = new MockCertegyBusinessLogic();
        }
        return INSTANCE;
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
        
        return "00";
    }

    public String reverseRequest(Map params) {
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CertegyBusinessLogic] Calling method cancelationRequest", null);

        PCAReverseRequest request = PCAReverseRequest.build(params);
        return "00";
    }

}
