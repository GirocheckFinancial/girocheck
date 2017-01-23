/*
 ** File: IStreamHostManager.java
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

//import com.smartbt.vtsuite.servercommon.manager.AuditManager;
import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.enums.ResultCode;
import com.smartbt.girocheck.servercommon.enums.ResultMessage;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
import com.smartbt.girocheck.servercommon.model.SubTransaction;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import com.smartbt.vtsuite.mock.MockIStreamBusinessLogic;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomHost;

/**
 * The Host Manager class
 */
public class IStreamHostManager {

    private MockIStreamBusinessLogic mockBusinessLogic = new MockIStreamBusinessLogic();

    private static IStreamHostManager INSTANCE;

    public static synchronized IStreamHostManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new IStreamHostManager();
        }
        return INSTANCE;
    }

    /**
     * Process Direx Transaction Request.
     *
     * @param request
     * @return DirexTransactionResponse The transaction response object
     * @throws Exception
     */
    public DirexTransactionResponse processTransaction(DirexTransactionRequest request) throws Exception {

        DirexTransactionResponse response;

        try {
            String prodProperty = System.getProperty("PROD");
            Boolean isProd = prodProperty != null && prodProperty.equalsIgnoreCase("true");

            if (isProd) {
                response = (DirexTransactionResponse) IStreamBusinessLogic.getInstance().process(request, 2);
            } else {
                response = mockBusinessLogic.process(request);
            }

        } catch (Exception e) {
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[IStreamHostManager]:: Istream failed", null);
            e.printStackTrace();
            return DirexTransactionResponse.forException(ResultCode.ISTREAM_HOST_ERROR, ResultMessage.ISTREAM_FAILED, " Description: " + e.getMessage(), "");
        }

        if (response.wasApproved()) {
            SubTransaction subTransaction = new SubTransaction();
            subTransaction.setType(request.getTransactionType().getCode());
            subTransaction.setResultCode(ResultCode.SUCCESS.getCode());
            subTransaction.setResultMessage(ResultMessage.SUCCESS.getMessage());
            subTransaction.setHost(NomHost.ISTREAM.getId());

            if (response.getTransactionData().containsKey(ParameterName.CHECK_ID)) {
                subTransaction.setErrorCode((String) response.getTransactionData().get(ParameterName.CHECK_ID));
            }
            response.getTransaction().addSubTransaction(subTransaction);
        }

        return response;

    }

}
