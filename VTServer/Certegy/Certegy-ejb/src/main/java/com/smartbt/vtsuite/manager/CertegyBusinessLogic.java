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

import com.smartbt.girocheck.common.VTSuiteMessages;
import com.smartbt.girocheck.servercommon.enums.ResultCode;
import com.smartbt.girocheck.servercommon.utils.IMap;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import com.smartbt.vtsuite.boundary.PCA;
import com.smartbt.vtsuite.boundary.PCARequest;
import com.smartbt.vtsuite.boundary.PCAService;
import java.util.Map;

/**
 * Mpowa Business Logic Class
 */
public class CertegyBusinessLogic {

    private static CertegyBusinessLogic INSTANCE;
    
    private PCAService service;
    private PCA port;

    public static synchronized CertegyBusinessLogic get() {
        if (INSTANCE == null) {
            INSTANCE = new CertegyBusinessLogic();
        }
        return INSTANCE;
    }

//    private IStreamSrvHostWS service;
//    private IStreamSrvHostWSSoap port;
//    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger( CertegyBusinessLogic.class );
    /**
     * Constructor
     */
    public CertegyBusinessLogic() {
        service = new PCAService();
        port = service.getPCAPort(); 
    }

    public DirexTransactionResponse process(DirexTransactionRequest request) throws Exception {
        Map transactionData = request.getTransactionData();
        DirexTransactionResponse direxTransactionResponse = new DirexTransactionResponse();

        TransactionType transactionType = request.getTransactionType();

        IMap response = null;
        Map map;

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CertegyBusinessLogic] proccessing:: " + transactionType, null);

        switch (transactionType) {
            case CERTEGY_AUTHENTICATION:
                combinedEnrollmentAuthentication(transactionData);
                break;
            case CERTEGY_REVERSE_REQUEST:
                reverseRequest(transactionData);
                break; 
        }

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CertegyBusinessLogic] Finish " + transactionType, null);

        direxTransactionResponse.setResultCode(ResultCode.SUCCESS);
        direxTransactionResponse.setResultMessage(VTSuiteMessages.SUCCESS);

        return direxTransactionResponse;
    }

    public void combinedEnrollmentAuthentication(Map params) {  
        PCARequest request = new PCARequest();
        port.authorize(request);
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CertegyBusinessLogic] Calling method insertTransaction", null);
    }

    public void reverseRequest(Map params) {
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CertegyBusinessLogic] Calling method cancelationRequest", null);
    }
 
}
