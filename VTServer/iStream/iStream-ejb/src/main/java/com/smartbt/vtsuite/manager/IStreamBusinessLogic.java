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

import com.smartbt.vtsuite.boundary.ws.CheckAuthLocationConfigRequest;
import com.smartbt.vtsuite.boundary.ws.CheckAuthPollRequest;
import com.smartbt.vtsuite.boundary.ws.CheckAuthRequest;
import com.smartbt.vtsuite.boundary.ws.CheckAuthSubmitRequest;
import com.smartbt.vtsuite.boundary.ws.EnhancedCheckAuthPollRequest;
import com.smartbt.vtsuite.boundary.ws.Scan;
import com.smartbt.vtsuite.boundary.ws.Scan_Service;
import com.smartbt.girocheck.common.AbstractBusinessLogicModule;
import com.smartbt.girocheck.servercommon.log.LogUtil;
import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.enums.ResultCode;
import com.smartbt.girocheck.servercommon.enums.ResultMessage;

import com.smartbt.girocheck.servercommon.utils.IMap;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import com.smartbt.vtsuite.boundary.ws.CheckAuthRes;
import java.net.URL;

import java.util.HashMap;
import java.util.Map;
import javax.xml.ws.BindingProvider;


public class IStreamBusinessLogic extends AbstractBusinessLogicModule{
    
//    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(IStreamBusinessLogic.class);

    private Scan_Service service = new Scan_Service();
    private Scan port = service.getScanPort();
    
     
    public IStreamBusinessLogic() {
            }
            

    public void preprocess(DirexTransactionRequest tr) throws Exception {

    }

 
    public DirexTransactionResponse process(DirexTransactionRequest request) throws Exception {

        IMap response = null;
        Map transactionResponseMap = new HashMap();

        Map transactionData = request.getTransactionData();

        TransactionType transactionType = request.getTransactionType();

//        log.info("[IStreamBusinessLogic] Processing "+ transactionType);
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[IStreamBusinessLogic] Processing "+ transactionType,null);
//        String url = "https://giro-1.istreamdeposit.com/Scan?wsdl";
//        String url = "https://istreamdeposit.com/Scan?wsdl";
        
        String url = System.getProperty("WS_ISTREAM_PRODUCTION_URL");

        if (url == null) {
            url = "https://istreamdeposit.com/Scan?wsdl";
        }
        
        try {

            BindingProvider bp = (BindingProvider) port;
            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, url);

        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//            log.debug("[IStreamBusinessLogic] ",ex);
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[IStreamBusinessLogic] Error ",ex.getMessage());
        }
        
//        log.info("[IStreamBusinessLogic] After BindingProvider modification ");
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[IStreamBusinessLogic] After BindingProvider modification.",null);
        switch (transactionType) {
            case ISTREAM_CHECK_AUTH_LOCATION_CONFIG:
                response = port.checkAuthLocationConfig(new CheckAuthLocationConfigRequest().build(transactionData));
                break;
            case ISTREAM_CHECK_AUTH:
               response = port.checkAuth(new CheckAuthRequest().build(transactionData));
               
               if(((CheckAuthRes)response).getCheckId() == null || ((CheckAuthRes)response).getCheckId().isEmpty())
                   return  DirexTransactionResponse.forException( ResultCode.ISTREAM_RETURN_CHECK_ID_NULL, ResultMessage.ISTREAM_RETURN_CHECK_ID_NULL );
               
                System.out.println("[ISTREAMHOST] CHECKID: " +((CheckAuthRes)response).getCheckId());
                break;
            case ISTREAM_CHECK_AUTH_POLL:
                response = port.checkAuthPoll(new CheckAuthPollRequest().build(transactionData));
                break;
            case ISTREAM_ENHANCED_CHECK_AUTH_POLL:
                response = port.enhancedCheckAuthPoll(new EnhancedCheckAuthPollRequest().build(transactionData));
                break;
            case ISTREAM_CHECK_AUTH_SUBMIT:
                response = port.checkAuthSubmit(new CheckAuthSubmitRequest().build(transactionData));
                break;
                
           default:
//               System.out.println( "default............" );
//               log.debug( "[IStreamBusinessLogic] default..." );
               CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[IStreamBusinessLogic] default...",null);
               response = port.checkAuth(new CheckAuthRequest().build(transactionData));
               
               if(((CheckAuthRes)response).getCheckId() == null || ((CheckAuthRes)response).getCheckId().isEmpty())
                   return  DirexTransactionResponse.forException( ResultCode.ISTREAM_RETURN_CHECK_ID_NULL, ResultMessage.ISTREAM_RETURN_CHECK_ID_NULL );
               
                break;     

        }

        if (response != null) {
            transactionResponseMap = response.toMap();
        }

        DirexTransactionResponse direxTransactionResponse = new DirexTransactionResponse();
        direxTransactionResponse.setTransactionData(transactionResponseMap);

        return direxTransactionResponse;

    }

   
    public void postprocess(DirexTransactionRequest transactionRequest, DirexTransactionResponse transactionResponse) throws Exception {
    }


}
