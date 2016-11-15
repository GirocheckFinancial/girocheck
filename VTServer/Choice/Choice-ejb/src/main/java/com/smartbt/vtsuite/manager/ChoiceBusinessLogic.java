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

/**
 * Mpowa Business Logic Class
 */
public class ChoiceBusinessLogic {

    private static ChoiceBusinessLogic INSTANCE;

    public static synchronized ChoiceBusinessLogic get() {
        if (INSTANCE == null) {
            INSTANCE = new ChoiceBusinessLogic();
        }
        return INSTANCE;
    }

//    private IStreamSrvHostWS service;
//    private IStreamSrvHostWSSoap port;
//    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger( ChoiceBusinessLogic.class );
    /**
     * Constructor
     */
    public ChoiceBusinessLogic() {
//        service = new IStreamSrvHostWS();
//        port = service.getIStreamSrvHostWSSoap();
//        
//        String url= "";
//        try {
//            
//            url = System.getProperty("WS_TECNICARD_PRODUCTION_URL");
//
//            if(url == null){
//                url = "https://bizsrv.tcmsystem.net/IStreamWS/iStreamSrvHost.asmx?WSDL";
//            }
//            
//            BindingProvider bindingProvider = (BindingProvider) port;
//            bindingProvider.getRequestContext().put(
//                    BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
//                    url);
//
//        } catch (Exception ex) {
//            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[TecnicardBusinessLogic] Error", ex.getMessage());
//        }

    }

    public DirexTransactionResponse process(DirexTransactionRequest request) throws Exception {
        Map transactionData = request.getTransactionData();
        DirexTransactionResponse direxTransactionResponse = new DirexTransactionResponse();

        TransactionType transactionType = request.getTransactionType();

        IMap response = null;
        Map map;

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[ChoiceBusinessLogic] proccessing:: " + transactionType, null);

        switch (transactionType) {
            case CHOICE_INSERT_TRANSACTION:
                insertTransaction(transactionData);
                break;
            case CHOICE_CANCELATION_REQUEST:
                cancelationRequest(transactionData);
                break;
            case CHOICE_NOTIFY_PAYMENT:
                notifyPayment(transactionData);
                break;
        }

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[ChoiceBusinessLogic] Finish " + transactionType, null);

        direxTransactionResponse.setResultCode(ResultCode.SUCCESS);
        direxTransactionResponse.setResultMessage(VTSuiteMessages.SUCCESS);

        return direxTransactionResponse;
    }

    public void insertTransaction(Map params) { 
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[ChoiceBusinessLogic] Calling method insertTransaction", null);
    }

    public void cancelationRequest(Map params) {
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[ChoiceBusinessLogic] Calling method cancelationRequest", null);
    }

    public void notifyPayment(Map params) {
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[ChoiceBusinessLogic] Calling method notifyPayment", null);
    }

}
