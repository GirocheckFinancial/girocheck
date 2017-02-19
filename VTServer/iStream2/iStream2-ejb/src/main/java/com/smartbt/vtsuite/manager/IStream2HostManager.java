/*
 ** File: IStream2HostManager.java
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
import com.smartbt.girocheck.servercommon.enums.ResultCode;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
import com.smartbt.girocheck.servercommon.model.SubTransaction;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import com.smartbt.vtsuite.mock.MockIStream2BusinessLogic;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomHost;

/**
 * The Host Manager class
 */
public class IStream2HostManager {

    private static IStream2HostManager INSTANCE;

    public static synchronized IStream2HostManager get() {
        if (INSTANCE == null) {
            INSTANCE = new IStream2HostManager();
        }
        return INSTANCE;
    }

    public IStream2HostManager() {
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

        SubTransaction subTransaction = new SubTransaction();
        subTransaction.setType(request.getTransactionType().getCode());
        subTransaction.setHost(NomHost.ISTREAM2.getId());

        String prodProperty = System.getProperty("PROD");
        Boolean isProd = prodProperty != null && prodProperty.equalsIgnoreCase("true");

        if (isProd) {
            response = IStream2BusinessLogic.get().process(request);
        } else {
            response = MockIStream2BusinessLogic.get().process(request);
        }

        try { 
            if (response.wasApproved()) {
                CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[IStream2HostManager] transaction executed successfully", null);
                subTransaction.setResultCode(response.getResultCode().getCode());
                subTransaction.setResultMessage(response.getResultMessage());
            } else {
                CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[IStream2HostManager] transaction failed", null);
                subTransaction.setResultCode(ResultCode.ISTREAM2_HOST_ERROR.getCode());
                subTransaction.setResultMessage(response.getResultMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            response = DirexTransactionResponse.forException(ResultCode.ISTREAM2_HOST_ERROR, e);
            subTransaction.setResultCode(ResultCode.ISTREAM2_HOST_ERROR.getCode());
            subTransaction.setResultMessage(e.getMessage());
        }

        response.getTransaction().addSubTransaction(subTransaction);

        System.out.println("[IStream2HostManager]  response.getTransaction().getSub_Transaction().size()" + response.getTransaction().getSub_Transaction().size());
        return response;
    }

}
