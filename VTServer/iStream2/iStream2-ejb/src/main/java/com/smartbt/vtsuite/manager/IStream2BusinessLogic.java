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
import com.smartbt.vtsuite.boundary.ws.TLS;
import com.smartbt.vtsuite.boundary.ws.TLS_Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.ws.BindingProvider;

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
    private TLS_Service service;
    private TLS port;
//    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger( iStream2BusinessLogic.class );

    /**
     * Constructor
     */
    public IStream2BusinessLogic() {

        String url = "";
        try {

            service = new TLS_Service();
            port = service.getTLSPort();

            url = "https://stable-1.istreamdeposit.com/TLS?wsdl";

//            if(url == null){
//                url = "https://bizsrv.tcmsystem.net/IStreamWS/iStreamSrvHost.asmx?WSDL";
//            }

            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, ">[IStream2BusinessLogic] URL: " + url, null);

            BindingProvider bindingProvider = (BindingProvider) port;
            bindingProvider.getRequestContext().put(
                    BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
                    url);

        } catch (Exception ex) {
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[IStream2BusinessLogic] Error", ex.getMessage());
        }

    }

    public DirexTransactionResponse process(DirexTransactionRequest request) throws Exception {
      try{
        Map transactionData = request.getTransactionData();
        DirexTransactionResponse direxTransactionResponse = new DirexTransactionResponse();       
        String userName = MapUtil.getStringValueFromMap(transactionData, ParameterName.USER, true);        
        String password = MapUtil.getStringValueFromMap(transactionData, ParameterName.PASSWORD, true);
        Integer locationId = MapUtil.getIntegerValueFromMap(transactionData, ParameterName.LOCATION_ID, true);
        String ammount =  MapUtil.getStringValueFromMap(transactionData, ParameterName.AMMOUNT, true);
        String depositName =  MapUtil.getStringValueFromMap(transactionData, ParameterName.DEPOSIT, true);
        String micr =  MapUtil.getStringValueFromMap(transactionData, ParameterName.MICR, true);
        String cutomerItemId =  MapUtil.getStringValueFromMap(transactionData, ParameterName.CHECK_ID, true);
        Image tiffImage =new Image();
        tiffImage.setImgBackBinary((byte[])transactionData.get(ParameterName.CHECK_BACK));
        tiffImage.setImgFrontBinary((byte[])transactionData.get(ParameterName.CHECK_FRONT));
        Image highQualityImage = new Image();
        highQualityImage.setImgBackBinary((byte[])transactionData.get(ParameterName.CHECK_BACK));
        highQualityImage.setImgFrontBinary((byte[])transactionData.get(ParameterName.CHECK_FRONT));
        List<AuxField> auxFields = new ArrayList();
        TransactionType transactionType = request.getTransactionType();

        IMap response = null;
        Map transactionResponseMap = new HashMap();

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[iStream2BusinessLogic] proccessing:: " + transactionType, null);

        switch (transactionType) {
            case ISTREAM2_SEND_SINCE_ICL:
                response = (IMap) port.sendSingleICL(userName, password, locationId, ammount, depositName, micr, cutomerItemId, tiffImage, highQualityImage,auxFields );
                break;
        }

        if (response != null) {
            transactionResponseMap = response.toMap();
        }
        direxTransactionResponse.setTransactionData(transactionResponseMap);
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[iStream2BusinessLogic] Finish " + transactionType, null);

        return direxTransactionResponse;
      }catch(Exception e){ 
            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[iStream2BusinessLogic] Host connection failed",null);
            e.printStackTrace();
            return DirexTransactionResponse.forException( ResultCode.ISTREAM2_HOST_ERROR, ResultMessage.ISTREAM_FAILED, "IStream2 failed all attempts to connect.", "0" );
            
        } 
    }
//    public void sendSingleICL(Map params) {
//        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[iStream2BusinessLogic] Calling method sendSingleICL", null);
//    }
}
