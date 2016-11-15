/*
 ** File: ChoiceHostManager.java
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
import com.smartbt.vtsuite.vtcommon.nomenclators.NomHost;

/**
 * The Host Manager class
 */
public class ChoiceHostManager {

    private static ChoiceHostManager INSTANCE;
    private ChoiceBusinessLogic choiceBusinessLogic;

    public static synchronized ChoiceHostManager get() {
        if (INSTANCE == null) {
            INSTANCE = new ChoiceHostManager();
        }
        return INSTANCE;
    }

    public ChoiceHostManager() {
        choiceBusinessLogic = choiceBusinessLogic.get();
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
        subTransaction.setHost(NomHost.CHOICE.getId());

        try {
            response = choiceBusinessLogic.process(request);

            if (response.wasApproved()) {
                subTransaction.setResultCode(response.getResultCode().getCode());
                subTransaction.setResultMessage(response.getResultMessage());
            } else {
                subTransaction.setResultCode(ResultCode.CHOICE_HOST_ERROR.getCode());
                subTransaction.setResultMessage(response.getResultMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            response = DirexTransactionResponse.forException(ResultCode.CHOICE_HOST_ERROR, e);
            subTransaction.setResultCode(ResultCode.CHOICE_HOST_ERROR.getCode());
            subTransaction.setResultMessage(e.getMessage());
        }

        response.getTransaction().addSubTransaction(subTransaction);

        System.out.println("[ChoiceHostManager]  response.getTransaction().getSub_Transaction().size()" + response.getTransaction().getSub_Transaction().size());
        return response;
    }

}
