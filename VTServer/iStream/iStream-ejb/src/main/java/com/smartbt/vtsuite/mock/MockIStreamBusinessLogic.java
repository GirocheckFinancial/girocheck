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

import com.smartbt.vtsuite.boundary.ws.CheckAuthLocationConfigRequest;
import com.smartbt.vtsuite.boundary.ws.CheckAuthLocationConfigRes;
import com.smartbt.vtsuite.boundary.ws.CheckAuthPollItem;
import com.smartbt.vtsuite.boundary.ws.CheckAuthPollRequest;
import com.smartbt.vtsuite.boundary.ws.CheckAuthPollRes;
import com.smartbt.vtsuite.boundary.ws.CheckAuthRes;
import com.smartbt.vtsuite.boundary.ws.Code;
import com.smartbt.vtsuite.boundary.ws.EnhancedCheckAuthPollItem;
import com.smartbt.vtsuite.boundary.ws.EnhancedCheckAuthPollRequest;
import com.smartbt.vtsuite.boundary.ws.EnhancedCheckAuthPollRes;
import com.smartbt.vtsuite.boundary.ws.Scan;
import com.smartbt.vtsuite.boundary.ws.Scan_Service;
import com.smartbt.girocheck.common.AbstractBusinessLogicModule;

import com.smartbt.girocheck.servercommon.log.LogUtil;
import com.smartbt.girocheck.servercommon.enums.ResultCode;
import com.smartbt.girocheck.servercommon.enums.ResultMessage;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import com.smartbt.girocheck.servercommon.utils.IMap;
import com.smartbt.vtsuite.boundary.ws.CheckAuthRequest;
import com.smartbt.vtsuite.boundary.ws.CheckAuthSubmitRes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Mpowa Business Logic Class
 */
public class MockIStreamBusinessLogic extends AbstractBusinessLogicModule {

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(MockIStreamBusinessLogic.class);

    private static MockIStreamBusinessLogic INSTANCE;
     
    /**
     * Constructor
     */
    public MockIStreamBusinessLogic() {
    }

    @Override
    public void preprocess(DirexTransactionRequest tr) throws Exception {

    }

    @Override
    public DirexTransactionResponse process(DirexTransactionRequest request) throws Exception {

        IMap response = null;
        Map transactionResponseMap = new HashMap();

        Map transactionData = request.getTransactionData();

        TransactionType transactionType = request.getTransactionType();

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug,"[MockIStreamBusinessLogic]:: transactionType = " + transactionType ,null);
        
        switch (transactionType) {
            case ISTREAM_CHECK_AUTH_LOCATION_CONFIG:
                response = checkAuthLocationConfig(new CheckAuthLocationConfigRequest().build(transactionData));
                break;
            case ISTREAM_CHECK_AUTH:
               response = checkAuth(transactionData);
                break;
            case ISTREAM_CHECK_AUTH_POLL:
                response = checkAuthPoll(new CheckAuthPollRequest().build(transactionData));
                break;
            case ISTREAM_ENHANCED_CHECK_AUTH_POLL:
                response = enhancedCheckAuthPoll(new EnhancedCheckAuthPollRequest().build(transactionData));
                break;
            case ISTREAM_CHECK_AUTH_SUBMIT:
                 checkAuthSubmit();
                break;

        }
        
         LogUtil.logAndStore("IStream BL", "            Finish "+ transactionType);

        if (response != null) {
            transactionResponseMap = response.toMap();
        }

        DirexTransactionResponse direxTransactionResponse = new DirexTransactionResponse();
        direxTransactionResponse.setTransactionData(transactionResponseMap);

        return direxTransactionResponse;

    }

    /**
     * Performs postprocess operations
     *
     * @param transactionRequest The transaction request
     * @param transactionResponse The transaction response
     * @throws Exception
     */
    @Override
    public void postprocess(DirexTransactionRequest transactionRequest, DirexTransactionResponse transactionResponse) throws Exception {
    }

   
    public CheckAuthPollRes checkAuthPoll( CheckAuthPollRequest arg0){
        CheckAuthPollRes response = new CheckAuthPollRes();
        
        CheckAuthPollItem item = new CheckAuthPollItem();
        item.setAdditionalInfo("Poll: Aditional Info");
        item.setCheckId(arg0.getFirstCheckId().toString());
        item.setPayoutAmount("1.00");
        item.setResponse("Poll: response");
        item.setSystemAmount("2.00");
        
        response.getItems().add(item);
        return response;
    }

     public CheckAuthRes checkAuth(Map transactionData) throws Exception{ 
         CheckAuthRequest request = new CheckAuthRequest();
         request.build(transactionData);
         request.printAsXML();
         CheckAuthRes response = new CheckAuthRes();
            
        response.setCheckId("1");
         
         return response;
     }

    public EnhancedCheckAuthPollRes enhancedCheckAuthPoll(EnhancedCheckAuthPollRequest arg0){
        EnhancedCheckAuthPollRes response = new EnhancedCheckAuthPollRes();
        
        EnhancedCheckAuthPollItem item = new EnhancedCheckAuthPollItem();
        
        item.setAdditionalInfo("EnhancedPoll: additionalInfo" );
        item.setAddressCurrent("EnhancedPoll: addressCurrent" );
        item.setCardLoadFee("EnhancedPoll: cardLoadFee" );
        item.setCheckId("EnhancedPoll: checkId" );
        item.setEntityId(123);
        item.setEntityName("EnhancedPoll: entityName");
        item.setFeeAmount("12.00");
        item.setLastFour("4531");
        item.setPayoutAmount("20.00");
        item.setResponse("EnhancedPoll: response");
        item.setSystemAmount("10.00");
        item.setTransactionDate(null);
        
        response.getItems().add(item);
        return response;
    }


     public CheckAuthLocationConfigRes checkAuthLocationConfig(  CheckAuthLocationConfigRequest arg0){
         CheckAuthLocationConfigRes response = new CheckAuthLocationConfigRes();
         
         Code code1 = new Code();
         code1.setExtra("Config: extra");
         code1.setExtra2("Config: extra2");
         code1.setLabel("AUTHFEEM");
         code1.setValue("1");
         
         Code code2 = new Code();
         code2.setExtra("");
         code2.setExtra2("");
         code2.setLabel("AUTHFEEP");
         code2.setValue("2");
         
         List codeList = new ArrayList();
         codeList.add(code1);
         codeList.add(code2);
         
         response.setConfigList(codeList);
         
         return response;
     }
     public CheckAuthSubmitRes checkAuthSubmit(){
         CheckAuthSubmitRes response = new CheckAuthSubmitRes();
               
         response.setResultCode(ResultCode.SUCCESS.toString());
         response.setResultMessage(ResultMessage.SUCCESS.getMessage());
         
         return response;
     }
    
}
