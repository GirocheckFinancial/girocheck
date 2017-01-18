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
public class IStream2BusinessLogic {

    private static IStream2BusinessLogic INSTANCE;

    public static synchronized IStream2BusinessLogic get() {
        if (INSTANCE == null) {
            INSTANCE = new IStream2BusinessLogic();
        }
        return INSTANCE;
    }

//    private IStreamSrvHostWS service;
//    private IStreamSrvHostWSSoap port;
//    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger( iStream2BusinessLogic.class );
    /**
     * Constructor
     */
    public IStream2BusinessLogic() {
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

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[iStream2BusinessLogic] proccessing:: " + transactionType, null);

        switch (transactionType) {
            case ISTREAM2_SEND_SINCE_ICL:
                sendSingleICL(transactionData);
                break; 
        }

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[iStream2BusinessLogic] Finish " + transactionType, null);

        direxTransactionResponse.setResultCode(ResultCode.SUCCESS);
        direxTransactionResponse.setResultMessage(VTSuiteMessages.SUCCESS);

        return direxTransactionResponse;
    }

    public void sendSingleICL(Map params) { 
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[iStream2BusinessLogic] Calling method sendSingleICL", null);
    }
 

}
