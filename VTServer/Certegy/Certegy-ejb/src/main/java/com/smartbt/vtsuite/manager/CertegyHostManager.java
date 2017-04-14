/*
 ** File: CertegyHostManager.java
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
import com.smartbt.vtsuite.mock.MockCertegyBusinessLogic;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomHost;

/**
 * The Host Manager class
 */
public class CertegyHostManager {

    private static CertegyHostManager INSTANCE; 

    public static synchronized CertegyHostManager get() {
        if (INSTANCE == null) {
            INSTANCE = new CertegyHostManager();
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

        SubTransaction subTransaction = new SubTransaction();
        subTransaction.setType(request.getTransactionType().getCode());
        subTransaction.setHost(NomHost.CERTEGY.getId());
        
        String prodProperty = System.getProperty("PROD");
        Boolean isProd = prodProperty != null && prodProperty.equalsIgnoreCase("true");
        System.out.println("CertegyHostManager -> isProd = " + isProd);
        
        if (isProd) {
            response = CertegyBusinessLogic.get().process(request);
        } else {
            response = MockCertegyBusinessLogic.get().process(request);
        }

        try {  
            if (response.wasApproved()) {
                subTransaction.setResultCode(response.getResultCode().getCode());
                subTransaction.setResultMessage(response.getResultMessage());
            } else {
                subTransaction.setResultCode(ResultCode.CERTEGY_HOST_ERROR.getCode());
                subTransaction.setResultMessage(response.getResultMessage());
                subTransaction.setErrorCode(response.getErrorCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
            response = DirexTransactionResponse.forException(ResultCode.CERTEGY_HOST_ERROR, e);
            subTransaction.setResultCode(ResultCode.CERTEGY_HOST_ERROR.getCode());
            subTransaction.setResultMessage(e.getMessage());
        }

        response.getTransaction().addSubTransaction(subTransaction);

        System.out.println("[CertegyHostManager]  response.getTransaction().getSub_Transaction().size()" + response.getTransaction().getSub_Transaction().size());
        return response;
    }

}
