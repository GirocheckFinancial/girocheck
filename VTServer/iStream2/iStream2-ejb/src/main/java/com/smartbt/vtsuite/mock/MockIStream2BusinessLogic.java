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

import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.enums.ResultCode;
import com.smartbt.girocheck.servercommon.enums.ResultMessage;
import com.smartbt.girocheck.servercommon.utils.IMap;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import com.smartbt.vtsuite.boundary.util.MapUtil;
import com.smartbt.vtsuite.boundary.ws.AuxField;
import com.smartbt.vtsuite.boundary.ws.Image;
import static com.smartbt.vtsuite.manager.IStream2BusinessLogic.ISTREAM_PASSWORD;
import static com.smartbt.vtsuite.manager.IStream2BusinessLogic.ISTREAM_USERNAME;
import static com.smartbt.vtsuite.manager.IStream2BusinessLogic.requestToString;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Mpowa Business Logic Class
 */
public class MockIStream2BusinessLogic {

    private static MockIStream2BusinessLogic INSTANCE;

    public static synchronized MockIStream2BusinessLogic get() {
        if (INSTANCE == null) {
            INSTANCE = new MockIStream2BusinessLogic();
        }
        return INSTANCE;
    }

    public DirexTransactionResponse process(DirexTransactionRequest request) throws Exception {
        try {
            Map transactionData = request.getTransactionData();
            DirexTransactionResponse direxTransactionResponse = DirexTransactionResponse.forSuccess();
            String userName = ISTREAM_USERNAME; //"GCTLS";
            String password = ISTREAM_PASSWORD; //"jpl500";  
            String locationIdStr = MapUtil.getStringValueFromMap(transactionData, ParameterName.TERMINAL_ID_ISTREAM, true);
            Integer locationId = Integer.parseInt(locationIdStr);
            String ammount = MapUtil.getStringValueFromMap(transactionData, ParameterName.AMMOUNT, true);
            String depositName = "Deposit at " + (new Date());
            String micr = MapUtil.getStringValueFromMap(transactionData, ParameterName.MICR, true);
            String cutomerItemId = MapUtil.getStringValueFromMap(transactionData, ParameterName.CHECK_ID, true);

            byte[] checkBack = (byte[]) transactionData.get(ParameterName.CHECK_BACK);
            byte[] checkFront = (byte[]) transactionData.get(ParameterName.CHECK_FRONT);

            Image tiffImage = new Image();
            tiffImage.setImgBackBinary(checkBack);
            tiffImage.setImgFrontBinary(checkFront);
            Image highQualityImage = new Image();

            highQualityImage.setImgBackBinary(checkBack);
            highQualityImage.setImgFrontBinary(checkFront);
            List<AuxField> auxFields = new ArrayList();
            TransactionType transactionType = request.getTransactionType();

            IMap response = null;
            Map transactionResponseMap = new HashMap();

            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[iStream2BusinessLogic] proccessing:: " + transactionType, null);

            switch (transactionType) {
                case ISTREAM2_SEND_SINGLE_ICL:
                    String log = requestToString(userName, password, "" + locationId, ammount, depositName, micr, cutomerItemId, checkBack, checkFront, null);
                    System.out.println("----------- IStrean -> SendSingleICL -----------");
                    System.out.println(log);
                    transactionResponseMap = new HashMap();
                    break;
            }

            direxTransactionResponse.setTransactionData(transactionResponseMap);
            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[iStream2BusinessLogic] Finish " + transactionType, null);

            return direxTransactionResponse;
//        return DirexTransactionResponse.forException(ResultCode.ISTREAM2_HOST_RETURN_PROCESSING_FALSE , ResultMessage.ISTREAM_RETURN_CHECK_ID_NULL);
        } catch (Exception e) {
            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[iStream2BusinessLogic] Host connection failed", null);
            e.printStackTrace();
            return DirexTransactionResponse.forException(ResultCode.ISTREAM2_HOST_ERROR, ResultMessage.ISTREAM_FAILED, e.getMessage(), "0");

        }
    }
//    public void sendSingleICL(Map params) {
//        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[iStream2BusinessLogic] Calling method sendSingleICL", null);
//    }
}
