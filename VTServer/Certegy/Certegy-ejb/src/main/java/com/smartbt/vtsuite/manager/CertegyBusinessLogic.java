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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;

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
        if (isCertegyProdConnect) {
            primaryService = new com.smartbt.vtsuite.boundary.prod.PCAService();
            port = primaryService.getPCAPort();
            System.out.println("*************** CERTEGY PRIMARY URL CONNECTION GOT *********************** " + primaryService.getWSDLDocumentLocation().getPath());
        } else {
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
        switch (transactionType) {
            case CERTEGY_AUTHENTICATION:
                resultCode = combinedEnrollmentAuthentication(transactionData);
                break;
            case CERTEGY_REVERSE_REQUEST:
                resultCode = reverseRequest(transactionData);
                break;
        }

        System.out.println("[CertegyBusinessLogic] :: resultCode = " + resultCode);

        if (resultCode == null || !resultCode.equals("00")) {
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

        PCAReverseRequest request = PCAReverseRequest.build(params);
        PCAReverseResponse response = port.reverse(request);
        return response != null ? response.getResponseCode() : "";
    }

    private Boolean doConnectCertegyProdURL() {

        String prodURL = System.getProperty("CERTEGY_PRIMARY_PROD_URL");//add this property in glassfish domain if not present
        String certFile = System.getProperty("CERTEGY_CERT_KEY_FILE");//add this property in glassfish domain if not present and add cetificate file in domain config folder
        String certKeyPhrase = System.getProperty("CERTEGY_CERT_KEY_PHRASE");//add this property in glassfish domain if not present
        try {

            if (prodURL != null && !prodURL.isEmpty() && certFile != null && !certFile.isEmpty() && certKeyPhrase != null && !certKeyPhrase.isEmpty()) {

                System.out.println("*************** CERTEGY CONNECTING TO PRODUCTION URL " + prodURL);

                URL url = new URL(prodURL);
                HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
                con.setSSLSocketFactory(getFactory(new File(certFile), certKeyPhrase));
                con.setAllowUserInteraction(true);

                // optional default is GET
                con.setRequestMethod("GET");

                int responseCode = con.getResponseCode();

                System.out.println("Sending 'GET' request to URL : " + url);
                System.out.println("Response Code : " + responseCode);

                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);

                }
                com.smartbt.vtsuite.boundary.prod.PCAService prodService = new com.smartbt.vtsuite.boundary.prod.PCAService();
                PCA prodPort = prodService.getPCAPort();
                PCAEchoRequest request = new PCAEchoRequest();
                request.setSiteID(CERTEGY_SITE_ID);
                request.setEchoNumber(new BigInteger("1000"));
                PCAEchoResponse certegyResponse = prodPort.echo(request);

                System.out.println("*************** CERTEGY CONNECTION GOT " + response.toString());

            } else {
                isCertegyProdConnect = false;
            }

        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("*************** UNABLE TO CONNECT CERTEGY PRODUCTION URL " + prodURL);
            isCertegyProdConnect = false;
        }
        return isCertegyProdConnect;
    }

    private static javax.net.ssl.SSLSocketFactory getFactory(File pKeyFile, String pKeyPassword) throws Exception {
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
        KeyStore keyStore = KeyStore.getInstance("PKCS12");

        InputStream keyInput = new FileInputStream(pKeyFile);
        keyStore.load(keyInput, pKeyPassword.toCharArray());
        keyInput.close();

        keyManagerFactory.init(keyStore, pKeyPassword.toCharArray());

        SSLContext context = SSLContext.getInstance("TLS");
        context.init(keyManagerFactory.getKeyManagers(), null, new SecureRandom());

        return context.getSocketFactory();
    }
}
