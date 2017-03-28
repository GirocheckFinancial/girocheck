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
import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.enums.ResultCode;
import com.smartbt.girocheck.servercommon.utils.IMap;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import com.smartbt.vtsuite.boundary.CheckProcessResult;
import com.smartbt.vtsuite.boundary.CheckService;
import com.smartbt.vtsuite.boundary.CheckServiceSoap;
import java.io.StringReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.JAXB;
import javaxt.utils.Base64;

public class WestechBusinessLogic {
    public static final String WT_USERNAME = "jacky";
    public static final  String WT_PASSWORD = "12345";
    private static WestechBusinessLogic INSTANCE;

    private CheckService service;
    private CheckServiceSoap port;

    public static synchronized WestechBusinessLogic get() {
        if (INSTANCE == null) {
            INSTANCE = new WestechBusinessLogic();
        }
        return INSTANCE;
    }

    public WestechBusinessLogic() {
        service = new CheckService();
        port = service.getCheckServiceSoap();
    }

    public DirexTransactionResponse process(DirexTransactionRequest request) throws Exception {
        Map transactionData = request.getTransactionData();
        DirexTransactionResponse direxTransactionResponse = new DirexTransactionResponse();

        TransactionType transactionType = request.getTransactionType();

        Map responseMap = null;

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[WestechBusinessLogic] proccessing:: " + transactionType, null);

        switch (transactionType) {
            case WESTECH_CHECK_PROCESS:
                responseMap = checkProcess(transactionData);
                break;
        }
        
        String status = (String)responseMap.get(ParameterName.STATUS);

        if(!status.endsWith("0")){
             direxTransactionResponse.setResultCode(ResultCode.WESTECH_HOST_UNEXPECTED_RESULT_CODE);
             direxTransactionResponse.setResultMessage(status);
             return direxTransactionResponse;
        }
        
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[WestechBusinessLogic] Finish " + transactionType, null);

        direxTransactionResponse.setResultCode(ResultCode.SUCCESS);
        direxTransactionResponse.setResultMessage(VTSuiteMessages.SUCCESS);
        
        if(responseMap != null){
            direxTransactionResponse.getTransactionData().putAll(responseMap);
        }

        return direxTransactionResponse;
    }

    public Map checkProcess(Map params) {
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[WestechBusinessLogic] Calling method insertTransaction", null);
        
        byte[] checkFront = (byte[])params.get(ParameterName.CHECK_FRONT);
        byte[] checkBack = (byte[])params.get(ParameterName.CHECK_BACK);
        byte[] idProof = (byte[])params.get(ParameterName.IDFRONT);
        
        String idProofXML = buildIdProofXML(params); 
        String log = printRequest(checkFront,checkBack,idProof, idProofXML);
        System.out.println(log);
        
        String xmlResult = port.checkProcess(WT_USERNAME, WT_PASSWORD, checkFront, checkBack, idProof, idProofXML);

        System.out.println("[WestechBusinessLogic] -> xmlResult = " + xmlResult);
        CheckProcessResult result = (CheckProcessResult) JAXB.unmarshal(new StringReader(xmlResult), CheckProcessResult.class);
        
        System.out.println("After parse -> result = " + result);
        System.out.println("");
        System.out.println("Westech CHECK_ID = " + result.getTransactionId());
        System.out.println("");
        return result.toMap();
    }

    
    public String buildIdProofXML(Map map){
        StringBuilder sb = new StringBuilder();
        sb.append("<IdProofInformation>").append('\n');
        sb.append("<IdNo>").append(map.get(ParameterName.ID)).append("</IdNo>").append('\n');
        sb.append("<Firstname>").append(map.get(ParameterName.FIRST_NAME)).append("</Firstname>").append('\n');
        sb.append("<Lastname>").append(map.get(ParameterName.LAST_NAME)).append("</Lastname>").append('\n');
        
        Date dob = (Date)map.get(ParameterName.BORNDATE_AS_DATE);
        DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
        if(dob != null){
         sb.append("<DateofBirth>").append(df.format(dob)).append("</DateofBirth>").append('\n');   
        }
         sb.append("<Address>").append(map.get(ParameterName.ADDRESS)).append("</Address>").append('\n');
         sb.append("<City>").append(map.get(ParameterName.CITY)).append("</City>").append('\n');
         System.out.println("WestechBusinessLogic. STATE_ABBREVIATION = " + map.get(ParameterName.STATE_ABBREVIATION));
         sb.append("<State>").append(map.get(ParameterName.STATE_ABBREVIATION)).append("</State>").append('\n');
         sb.append("<ZipCode>").append(map.get(ParameterName.ZIPCODE)).append("</ZipCode>").append('\n');
         
         Date expirationDate = (Date)map.get(ParameterName.EXPIRATION_DATE_AS_DATE);
         System.out.println("WestechBusinessLogic. buildIdProofXML -> expirationDate = " + expirationDate);
         if(expirationDate != null){
          sb.append("<ExpirationDate>").append(df.format(expirationDate)).append("</ExpirationDate>").append('\n');   
         }
         sb.append("</IdProofInformation>").append('\n');
        
        return sb.toString();
    }
    
    public static  String printRequest(byte[] checkFront,byte[]  checkBack,byte[]  idProof, String idProof2dBarcode){
         StringBuilder sb = new StringBuilder();
        sb.append("<WestechRequest>").append('\n');
        sb.append("<Username>").append(WT_USERNAME).append("</Username>").append('\n');
        sb.append("<Password>").append(WT_PASSWORD).append("</Password>").append('\n');
        
        if(checkFront != null && checkFront.length > 0){
            sb.append("<CheckFront>").append("AN IMAGE ::" + checkFront.length).append("</CheckFront>").append('\n');
        }
        
        if(checkBack != null && checkBack.length > 0){
            sb.append("<checkBack>").append("AN IMAGE :: " + checkBack.length).append("</checkBack>").append('\n');
        }
        
        if(idProof != null && idProof.length > 0){
            sb.append("<idProof>").append("AN IMAGE :: " + idProof.length).append("</idProof>").append('\n');
        }
        
        sb.append(idProof2dBarcode);
           
         sb.append("</WestechRequest>").append('\n');
        return sb.toString();
    }
    
//    <IdProofInformation>
//  <Firstname>Mr.Alan</Firstname>
//  <Lastname>C</Lastname>
//  <DateofBirth>06-01-1985</DateofBirth>
//  <Address>703 NW 62ND AVE. SUITE 230</Address>
//  <City>MIAMI</City>
// <State>FL</State>
// <ZipCode>33126</ZipCode>
//</IdProofInformation>
}
