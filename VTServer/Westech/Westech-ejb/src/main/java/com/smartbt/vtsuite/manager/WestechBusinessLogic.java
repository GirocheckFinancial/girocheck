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
import java.util.Map;

 
public class WestechBusinessLogic {

    private static WestechBusinessLogic INSTANCE;

    public static synchronized WestechBusinessLogic get() {
        if (INSTANCE == null) {
            INSTANCE = new WestechBusinessLogic();
        }
        return INSTANCE;
    }

 
    public WestechBusinessLogic() {
 
    }

    public DirexTransactionResponse process(DirexTransactionRequest request) throws Exception {
        Map transactionData = request.getTransactionData();
        DirexTransactionResponse direxTransactionResponse = new DirexTransactionResponse();

        TransactionType transactionType = request.getTransactionType();

        IMap response = null;
        Map map;

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[WestechBusinessLogic] proccessing:: " + transactionType, null);

        switch (transactionType) {
            case WESTECH_CHECKAUTH:
                checkAuth(transactionData);
                break; 
        }

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[WestechBusinessLogic] Finish " + transactionType, null);

        direxTransactionResponse.setResultCode(ResultCode.SUCCESS);
        direxTransactionResponse.setResultMessage(VTSuiteMessages.SUCCESS);

        return direxTransactionResponse;
    }

    public void checkAuth(Map params) { 
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[WestechBusinessLogic] Calling method insertTransaction", null);
    }
 
}
