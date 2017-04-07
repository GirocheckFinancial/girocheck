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
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import com.smartbt.vtsuite.boundary.PCA;
import com.smartbt.vtsuite.boundary.PCAEchoRequest;
import com.smartbt.vtsuite.boundary.PCAEchoResponse;
import com.smartbt.vtsuite.boundary.PCARequest;
import com.smartbt.vtsuite.boundary.PCAResponse;
import com.smartbt.vtsuite.boundary.PCAReverseRequest;
import com.smartbt.vtsuite.boundary.PCAReverseResponse;
import com.smartbt.vtsuite.boundary.PCAService;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.util.Map;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

/**
 * Mpowa Business Logic Class
 */
public class CertegyBusinessLogic {

    public static final String CERTEGY_SITE_ID = "2897891071345202";
    public static final BigDecimal CERTEGY_VERSION = new BigDecimal("1.2");
    private static CertegyBusinessLogic INSTANCE;
    private PCAService secondaryService;
    private PCA port;
    private com.smartbt.vtsuite.boundary.prod.PCAService primaryService;
    Boolean isCertegyProdConnect = true;
   


    public static synchronized CertegyBusinessLogic get() {
        if (INSTANCE == null) {
            INSTANCE = new CertegyBusinessLogic();
        }
        return INSTANCE;
    }

    public CertegyBusinessLogic() {
        isCertegyProdConnect = doConnectCertegyProdURL();
        if (!isCertegyProdConnect) {
            secondaryService = new PCAService();           
            port = secondaryService.getPCAPort();
            System.out.println("*************** CERTEGY SECONDARY URL CONNECTION GOT ***********************" + secondaryService.getWSDLDocumentLocation().getPath());
        }
    }

    public DirexTransactionResponse process(DirexTransactionRequest request) throws Exception {
        
               
        Map transactionData = request.getTransactionData();
        DirexTransactionResponse direxTransactionResponse = new DirexTransactionResponse();

        TransactionType transactionType = request.getTransactionType();

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CertegyBusinessLogic] proccessing:: " + transactionType, null);

        String resultCode = "";
        Boolean success = false;
        switch (transactionType) {
            case CERTEGY_AUTHENTICATION:
                resultCode = combinedEnrollmentAuthentication(transactionData);
                success = (resultCode != null && resultCode.equals("00"));
                break;
            case CERTEGY_REVERSE_REQUEST:
                resultCode = reverseRequest(transactionData);
                success = (resultCode != null && resultCode.equals("13"));
                break;
        }

        System.out.println("[CertegyBusinessLogic] :: resultCode = " + resultCode);

        if (!success) {
            direxTransactionResponse = DirexTransactionResponse.forException(ResultCode.CERTEGY_DENY, ResultMessage.CERTEGY_DENY);
            direxTransactionResponse.setErrorCode(resultCode);
            return direxTransactionResponse;
        }

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CertegyBusinessLogic] Finish " + transactionType, null);

        direxTransactionResponse = DirexTransactionResponse.forSuccess();

        return direxTransactionResponse;
    }

    public String combinedEnrollmentAuthentication(Map params) {
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CertegyBusinessLogic] Calling method insertTransaction", null);

        PCARequest request = PCARequest.build(params);

        System.out.println(request.toString());

        PCAResponse response = port.authorize(request);
        return response != null ? response.getResponseCode() : "";
    }

    public String reverseRequest(Map params) {
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CertegyBusinessLogic] Calling method cancelationRequest", null);

        params.remove(ParameterName.CHECK_ISSUE_DATE);
        
        PCAReverseRequest request = PCAReverseRequest.build(params);
        System.out.println(request.toString());
        PCAReverseResponse response = port.reverse(request);
        return response != null ? response.getResponseCode() : "";
    }

    private Boolean doConnectCertegyProdURL() {

        String prodURL = System.getProperty("CERTEGY_PRIMARY_PROD_WSDL");//add this property in glassfish domain if not present
        
        try {

            if (prodURL != null && !prodURL.isEmpty()) {
                System.out.println("*************** CONNECTING TO CERTEGY PRODUCTION URL " + prodURL);                
                URL url = new URL("file:/"+prodURL);
                QName serviceName =new QName("http://fis.certegy.pca.com/","PCAService") ;
                
                QName portName =new QName("http://fis.certegy.pca.com/","PCAPort") ;
                
                
                Service ser= Service.create(url, serviceName);                        
                        
                port =ser.getPort(portName, PCA.class);  
                
                System.out.println("***************GOT CERTEGY PORT BINDING "+port);
                
                PCAEchoRequest request = new PCAEchoRequest();
                request.setSiteID(CERTEGY_SITE_ID);
                request.setEchoNumber(new BigInteger("1000"));
                PCAEchoResponse certegyResponse = port.echo(request);
                System.out.println("***************GOT CERTEGEY ECHO RESPONSE*****************"+" SITEID = "+certegyResponse.getSiteID()+" , ECHO NUMBER = "+certegyResponse.getEchoNumber());

            } else {
                isCertegyProdConnect = false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("*************** UNABLE TO CONNECT CERTEGY PRODUCTION URL " + prodURL);
            isCertegyProdConnect = false;
        }
        return isCertegyProdConnect;
    }   
}
