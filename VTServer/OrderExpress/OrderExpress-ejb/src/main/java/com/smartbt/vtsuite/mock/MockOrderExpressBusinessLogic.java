/**
 **
 ** Date Created: February 2013 * * Copyright @ 2004-2013 Smart Business
 * Technology, Inc. * * All rights reserved. No part of this software may be *
 * reproduced, transmitted, transcribed, stored in a retrieval * system, or
 * translated into any language or computer language, * in any form or by any
 * means, electronic, mechanical, magnetic, * optical, chemical, manual or
 * otherwise, without the prior * written permission of Smart Business
 * Technology, Inc. *
 */
package com.smartbt.vtsuite.mock;

import com.smartbt.vtsuite.manager.*;
import com.smartbt.vtsuite.boundary.client.impl.LOTEENTRADA;
import com.smartbt.vtsuite.boundary.client.impl.LOTESALIDA;
import com.smartbt.vtsuite.boundary.client.impl.ObjectFactory;
import com.smartbt.girocheck.common.AbstractBusinessLogicModule;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.messageFormat.IdType;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import com.smartbt.vtsuite.boundary.OEWS.ContratacionesResponse;
import com.smartbt.vtsuite.boundary.OEWS.Service1;
import com.smartbt.vtsuite.boundary.OEWS.Service1Soap;
import com.smartbt.vtsuite.boundary.client.impl.LOTEENTRADAD;
import com.smartbt.vtsuite.boundary.client.impl.LOTEENTRADAL;
import com.smartbt.vtsuite.boundary.client.impl.LOTEENTRADARP;
import com.smartbt.vtsuite.boundary.client.impl.LOTESALIDAD;
import com.smartbt.vtsuite.boundary.client.impl.LOTESALIDAL;
import com.smartbt.vtsuite.boundary.client.impl.LOTESALIDARP;
import com.smartbt.vtsuite.utils.MapUtil;
import com.smartbt.vtsuite.utils.OEUtils;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import static java.util.concurrent.TimeUnit.SECONDS;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.ws.BindingProvider;

/**
 *
 * @author Edward Beckett :: <Edward.Beckett@smartbt.com>
 */
public class MockOrderExpressBusinessLogic extends AbstractBusinessLogicModule {


    @Override
    public void preprocess(DirexTransactionRequest tr) throws Exception {
    }

    @Override
    public DirexTransactionResponse process(DirexTransactionRequest request) throws Exception {

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[MockOrderExpressBusinessLogic] processing ...", null);

        DirexTransactionResponse direxTransactionResponse = new DirexTransactionResponse();

        TransactionType transactionType = request.getTransactionType();
        Map map = request.getTransactionData();
        Map response = map;
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[MockOrderExpressBusinessLogic] processing transaction type: " + transactionType.toString(), null);
        switch (transactionType) {
            case ORDER_EXPRESS_CONTRATACIONES:
                response = contrataciones(map);
                break;
            case ORDER_EXPRESS_REPORTAPAGO:
                response = reportaPago(map);
                break;
            case ORDER_EXPRESS_DEVOLUCION:
                response = devolucion(map);
                break;
            case ORDER_EXPRESS_LOGS:
                response = OETimerLogExecutor(map);
                break;
        }

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[MockOrderExpressBusinessLogic] Proccess finish", null);
        direxTransactionResponse.setTransactionType(transactionType);
        direxTransactionResponse.setTransactionData(response);

        return direxTransactionResponse;
    }

    /**
     * Performs postprocess operations
     *
     * transactionRequest The transaction request transactionResponse The
     * transaction response
     *
     * @param transactionRequest
     * @param transactionResponse
     * @throws Exception
     */
    @Override
    public void postprocess(DirexTransactionRequest transactionRequest, DirexTransactionResponse transactionResponse) throws Exception {
    }

    /**
     * This method takes the DirexTransactionRequest Map and converts the
     * parameters to to entry Object, passes the raw XML object into a
     * Base64String and returns the response as a Map object.
     *
     * @param map
     * @return
     * @throws JAXBException
     */
    private Map contrataciones(Map map) throws Exception {
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[MockOrderExpressBusinessLogic] ContratacionesMethod()...", null);

        ObjectFactory factory = new ObjectFactory();

        // Build ...
        //Entry
        LOTEENTRADA entry = factory.createLOTEENTRADA();
        //Match
        LOTEENTRADA.CORRESPONSAL corresponsal = factory.createLOTEENTRADACORRESPONSAL();
        //Transaction
        LOTEENTRADA.TRANSACCIONE transaction = factory.createLOTEENTRADATRANSACCIONE();
        //Input
        LOTEENTRADA.TRANSACCIONE.CONTRATACIONINPUT input = factory.createLOTEENTRADATRANSACCIONECONTRATACIONINPUT();
        //Client
        LOTEENTRADA.TRANSACCIONE.CONTRATACIONINPUT.CLIENTE client = factory.createLOTEENTRADATRANSACCIONECONTRATACIONINPUTCLIENTE();
        //Client Documentation
        LOTEENTRADA.TRANSACCIONE.CONTRATACIONINPUT.CLIENTE.DOCUMENTACIONCLIENTE clientDocumentation = factory.createLOTEENTRADATRANSACCIONECONTRATACIONINPUTCLIENTEDOCUMENTACIONCLIENTE();
        //Client 'B' 
        LOTEENTRADA.TRANSACCIONE.CONTRATACIONINPUT.CLIENTEB clientB = factory.createLOTEENTRADATRANSACCIONECONTRATACIONINPUTCLIENTEB();
        //Client 'B' DOCUMENTATION
        LOTEENTRADA.TRANSACCIONE.CONTRATACIONINPUT.CLIENTEB.DOCUMENTACIONCLIENTEB clientDocumentationB = factory.createLOTEENTRADATRANSACCIONECONTRATACIONINPUTCLIENTEBDOCUMENTACIONCLIENTEB();

        //Set Classes ...
        client.setDOCUMENTACIONCLIENTE(clientDocumentation);
        clientB.setDOCUMENTACIONCLIENTEB(clientDocumentationB);

        input.setCLIENTE(client);
        input.setCLIENTEB(clientB);

        transaction.setCONTRATACIONINPUT(input);

        entry.setCORRESPONSAL(corresponsal);
        entry.setTRANSACCIONE(transaction);

        /**
         * DEFAULT CONFIG VALUES ARE SET IN @see
         * com.smartbt.vtsuite.utils.OrderExpressConstantValues
         */
        //  fixed VALUES
        transaction.setREQUESTTYPE("T");
        input.setIDSERVICE("21");

        String hostName = MapUtil.getStringValueFromMap(map, ParameterName.HOSTNAME, true);
        String operation = MapUtil.getStringValueFromMap(map, ParameterName.OPERATION, true);

        if (hostName.equalsIgnoreCase("TECNICARD") && operation.equalsIgnoreCase("02")) {
            Double amount = (Double) map.get(ParameterName.AMMOUNT);

            input.setDEPOSIT(!"NULL".equals(MapUtil.getDoubleValueFromMap(map, ParameterName.PAYOUT_AMMOUNT, false)) ? OEUtils.singleQuote(((double) amount - 3.95) + "") : "NULL");
            input.setTAX("NULL");
            input.setTOTAL(!"NULL".equals(MapUtil.getStringValueFromMap(map, ParameterName.AMMOUNT, false)) ? OEUtils.singleQuote(amount + "") : "NULL");
            input.setRATE("1.00");
            input.setRELIEVE(!"NULL".equals(MapUtil.getDoubleValueFromMap(map, ParameterName.PAYOUT_AMMOUNT, false)) ? OEUtils.singleQuote(((double) amount - 3.95) + "") : "NULL");
            input.setSERVICE(!"NULL".equals(MapUtil.getStringValueFromMap(map, ParameterName.FEE_AMMOUNT, false)) ? OEUtils.singleQuote("3.95") : "NULL");
        } else {
            input.setDEPOSIT(!"NULL".equals(MapUtil.getDoubleValueFromMap(map, ParameterName.PAYOUT_AMMOUNT, false)) ? OEUtils.singleQuote(MapUtil.getStringValueFromMap(map, ParameterName.PAYOUT_AMMOUNT, false)) : "NULL");
            input.setTAX("NULL");
            input.setTOTAL(!"NULL".equals(MapUtil.getStringValueFromMap(map, ParameterName.AMMOUNT, false)) ? OEUtils.singleQuote(MapUtil.getStringValueFromMap(map, ParameterName.AMMOUNT, false)) : "NULL");
            input.setRATE("1.00");
            input.setRELIEVE(!"NULL".equals(MapUtil.getDoubleValueFromMap(map, ParameterName.PAYOUT_AMMOUNT, false)) ? OEUtils.singleQuote(MapUtil.getStringValueFromMap(map, ParameterName.PAYOUT_AMMOUNT, false)) : "NULL");
            input.setSERVICE(!"NULL".equals(MapUtil.getStringValueFromMap(map, ParameterName.FEE_AMMOUNT, false)) ? OEUtils.singleQuote(MapUtil.getStringValueFromMap(map, ParameterName.FEE_AMMOUNT, false)) : "NULL");
        }
        corresponsal.setIDMERCHANT(System.getProperty("PARAM_OE_ID_MERCHANT"));  // estet valor ex fijo para Girocheck??    o es uno para cada merchant? si

        //1 x cada producto
        if (operation.equals("01")) {
            input.setIDDESTINY(System.getProperty("PARAM_OE_ID_DESTINY_CH") != null ? OEUtils.singleQuote(System.getProperty("PARAM_OE_ID_DESTINY_CH")) : "84693");//7574 - poner la variable en el glassfish
            input.setPAYMENTMETHOD(System.getProperty("PARAM_OE_PAYMENT_METHOD_CH") != null ? OEUtils.singleQuote(System.getProperty("PARAM_OE_PAYMENT_METHOD_CH")) : "02");

            input.setPAYMENTIMPORT(!"NULL".equals(MapUtil.getStringValueFromMap(map, ParameterName.PAYOUT_AMMOUNT, false)) ? OEUtils.singleQuote(MapUtil.getStringValueFromMap(map, ParameterName.PAYOUT_AMMOUNT, false)) : "NULL");
            input.setPAYMENTCOMISSION(!"NULL".equals(MapUtil.getStringValueFromMap(map, ParameterName.FEE_AMMOUNT, false)) ? OEUtils.singleQuote(MapUtil.getStringValueFromMap(map, ParameterName.FEE_AMMOUNT, false)) : "NULL");
        } else {
            input.setIDDESTINY(System.getProperty("PARAM_OE_ID_DESTINY_CASH") != null ? OEUtils.singleQuote(System.getProperty("PARAM_OE_ID_DESTINY_CASH")) : "84693");//7574 - poner la variable en el glassfish
            input.setPAYMENTMETHOD(System.getProperty("PARAM_OE_PAYMENT_METHOD_CASH") != null ? OEUtils.singleQuote(System.getProperty("PARAM_OE_PAYMENT_METHOD_CASH")) : "01");

            input.setPAYMENTIMPORT(!"NULL".equals(MapUtil.getStringValueFromMap(map, ParameterName.PAYMENTIMPORT, false)) ? OEUtils.singleQuote(MapUtil.getStringValueFromMap(map, ParameterName.PAYMENTIMPORT, false)) : "NULL");
            input.setPAYMENTCOMISSION(!"NULL".equals(MapUtil.getStringValueFromMap(map, ParameterName.PAYMENTCOMISSION, false)) ? OEUtils.singleQuote(MapUtil.getStringValueFromMap(map, ParameterName.PAYMENTCOMISSION, false)) : "NULL");
        }

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[MockOrderExpressBusinessLogic] IDPOS&IDTELLER", null);
        if (map.containsKey(ParameterName.IDPOS) && map.get(ParameterName.IDPOS) != null) {
            input.setIDPOS(!"NULL".equals(MapUtil.getStringValueFromMap(map, ParameterName.IDPOS, false)) ? OEUtils.singleQuote(MapUtil.getStringValueFromMap(map, ParameterName.IDPOS, false)) : "NULL");
        }
        if (map.containsKey(ParameterName.IDTELLER) && map.get(ParameterName.IDTELLER) != null) {
            input.setIDTELLER(!"NULL".equals(MapUtil.getStringValueFromMap(map, ParameterName.IDTELLER, false)) ? OEUtils.singleQuote(MapUtil.getStringValueFromMap(map, ParameterName.IDTELLER, false)) : "NULL");
        }
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[MockOrderExpressBusinessLogic] IDPOS: " + map.get(ParameterName.IDPOS).toString() + " IDTELLER: " + map.get(ParameterName.IDTELLER).toString(), null);

        input.setPAYMENTCHECK(!"NULL".equals(MapUtil.getStringValueFromMap(map, ParameterName.PAYMENTCHECK, false)) ? OEUtils.singleQuote(MapUtil.getStringValueFromMap(map, ParameterName.PAYMENTCHECK, false)) : "NULL");

        input.setNOTES(!"NULL".equals(MapUtil.getStringValueFromMap(map, ParameterName.OPERATION, false)) ? OEUtils.singleQuote(MapUtil.getStringValueFromMap(map, ParameterName.OPERATION, false)) : "NULL");

        client.setMNAME("''");
        client.setLNAME("''");
        client.setCOLONY("''");
        clientB.setMNAMEB("''");
        clientB.setLNAMEB("''");
        clientB.setCOLONYB("''");

        corresponsal.setLOGIN(OEUtils.singleQuote(System.getProperty("PARAM_OE_LOGIN")));
        corresponsal.setPASSWORD(OEUtils.singleQuote(System.getProperty("PARAM_OE_PASSWORD")));

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        input.setDATETIME(OEUtils.singleQuote(dateFormat.format(new Date())));

        input.setBANKAUTHO((!"NULL".equals(MapUtil.getStringValueFromMap(map, ParameterName.REQUEST_ID, false))) ? OEUtils.singleQuote(MapUtil.getStringValueFromMap(map, ParameterName.REQUEST_ID, false)) : "NULL");  //auto-incrementable

        // input.setIDBENEFICIARY( "1" );
        client.setFNAME(OEUtils.singleQuote(MapUtil.getStringValueFromMap(map, ParameterName.FIRST_NAME, false)));
        clientB.setFNAMEB(OEUtils.singleQuote(MapUtil.getStringValueFromMap(map, ParameterName.FIRST_NAME, false)));

        client.setSNAME(OEUtils.singleQuote(MapUtil.getStringValueFromMap(map, ParameterName.LAST_NAME, false)));
        clientB.setSNAMEB(OEUtils.singleQuote(MapUtil.getStringValueFromMap(map, ParameterName.LAST_NAME, false)));

        Date bornDate = MapUtil.getDateValueFromMap(map, ParameterName.BORNDATE_AS_DATE, false);
        if (bornDate != null) {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
            client.setBORNDATE(OEUtils.singleQuote(df.format(bornDate)));
            clientB.setBORNDATEB(OEUtils.singleQuote(df.format(bornDate)));
        }

        client.setSTREET(OEUtils.singleQuote(addressSplitter(MapUtil.getStringValueFromMap(map, ParameterName.ADDRESS, false)).get(1)));
        clientB.setSTREETB(OEUtils.singleQuote(addressSplitter(MapUtil.getStringValueFromMap(map, ParameterName.ADDRESS, false)).get(1)));
        client.setNUMBER(OEUtils.singleQuote(addressSplitter(MapUtil.getStringValueFromMap(map, ParameterName.ADDRESS, false)).get(0)));
        clientB.setNUMBERB(OEUtils.singleQuote(addressSplitter(MapUtil.getStringValueFromMap(map, ParameterName.ADDRESS, false)).get(0)));

        client.setZIPCODE(OEUtils.singleQuote(MapUtil.getStringValueFromMap(map, ParameterName.ZIPCODE, false)));
        clientB.setZIPCODEB(OEUtils.singleQuote(MapUtil.getStringValueFromMap(map, ParameterName.ZIPCODE, false)));

        if (map.containsKey(ParameterName.IDCOUNTRY)) {
            String idCountry = "2";//(String) map.get( ParameterName.IDCOUNTRY );
            client.setIDPAIS(idCountry + "");
            clientB.setIDPAISB(idCountry + "");
        }

        if (map.containsKey(ParameterName.OEIDSTATE)) {
            String idState = (String) map.get(ParameterName.OEIDSTATE);
            client.setIDESTADO(idState + "");
            clientB.setIDESTADOB(idState + "");
        }

        client.setPOBLACION(OEUtils.singleQuote(MapUtil.getStringValueFromMap(map, ParameterName.CITY, false)));//
        clientB.setPOBLACIONB(OEUtils.singleQuote(MapUtil.getStringValueFromMap(map, ParameterName.CITY, false)));//

        client.setTELEPHONE1(!"NULL".equals(MapUtil.getStringValueFromMap(map, ParameterName.TELEPHONE, false)) ? OEUtils.singleQuote(MapUtil.getStringValueFromMap(map, ParameterName.TELEPHONE, false)) : "NULL");
        clientB.setTELEPHONE1B(!"NULL".equals(MapUtil.getStringValueFromMap(map, ParameterName.TELEPHONE, false)) ? OEUtils.singleQuote(MapUtil.getStringValueFromMap(map, ParameterName.TELEPHONE, false)) : "NULL");

        client.setIDOCUPACION("117");
        clientB.setIDOCUPACIONB("117");

        //falta implementar la logica del ID
        //Client Documentation
        if (map.containsKey(ParameterName.IDTYPE)) {
            if (map.containsKey(ParameterName.ID) || map.containsKey(ParameterName.ID)) {

                IdType idType = (IdType) map.get(ParameterName.IDTYPE);
                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[MockOrderExpressBusinessLogic] Proccess cadena with idType: " + idType, null);
                String id = OEUtils.singleQuote(MapUtil.getStringValueFromMap(map, ParameterName.ID, true));
                switch (idType) {
                    case VISA:
                        clientDocumentation.setVISA(id);
                        clientDocumentationB.setVISAB(id);
                        break;
                    case PASSPORT:
                        clientDocumentation.setPASAPORTE(id);
                        clientDocumentationB.setPASAPORTEB(id);
                        break;
                    case GREEN_CARD:
                        clientDocumentation.setGREENCARD(id);
                        clientDocumentationB.setGREENCARDB(id);
                        break;

                    case MATRICULA_CONSULAR:
                        clientDocumentation.setMATRICULACONSULAR(id);
                        clientDocumentationB.setMATRICULACONSULARB(id);
                        break;
                    case DRIVER_LICENSE:
                        clientDocumentation.setLICENCIA(id);
                        clientDocumentationB.setLICENCIAB(id);
                        break;
                    case SSN:
                    default:
                        clientDocumentation.setLICENCIA(id);
                        clientDocumentationB.setLICENCIAB(id);

//                    clientDocumentation.setSS( id  );
//                    clientDocumentationB.setSSB( id );
                        break;
                }

            }
        }

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[MockOrderExpressBusinessLogic] Proccess cadena", null);
        String cadena = OEUtils.processRequest(entry);

        String datos = "EX";
//        String corresponsales = "OEINC";
        String corresponsales = "GIRO_CHECK";
        String rutaEjecutar = null;

        ContratacionesResponse response = new ContratacionesResponse();

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[MockOrderExpressBusinessLogic] Printing cadena value: " + cadena, null);

        Map responseMap = new HashMap();

        responseMap.put(ParameterName.AUTHO_NUMBER, "OE: AUTHO_NUMBER");
        responseMap.put(ParameterName.OP_CODE, "001");
        responseMap.put(ParameterName.OP_CODE2, "1");
        responseMap.put(ParameterName.IDCONSIGNOR, "OE IDCONSIGNOR");
        responseMap.put(ParameterName.IDBENEFICIARY, "IDBENEFICIARY");
        responseMap.put(ParameterName.BANK_AUTHO, "BANK_AUTHO");

        return responseMap;

    }

    /*
     *
     *
     * NEW WS IMPLEMNTATION !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! 
     *
     *
     */
    private Map reportaPago(Map map) throws Exception {

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[MockOrderExpressBusinessLogic] reportaPago Method()...", null);

        // Build ...
        //Entry
        LOTEENTRADARP entry = new LOTEENTRADARP();
        //Match
        LOTEENTRADARP.CORRESPONSAL corresponsal = new LOTEENTRADARP.CORRESPONSAL();
        //Transaction
        LOTEENTRADARP.TRANSACCIONE transaction = new LOTEENTRADARP.TRANSACCIONE();
        //Input
        LOTEENTRADARP.TRANSACCIONE.REPORTAPAGOINPUT input = new LOTEENTRADARP.TRANSACCIONE.REPORTAPAGOINPUT();
        //Client Documentation
        LOTEENTRADARP.TRANSACCIONE.REPORTAPAGOINPUT.DOCUMENTACIONCLIENTE clientDocumentation = new LOTEENTRADARP.TRANSACCIONE.REPORTAPAGOINPUT.DOCUMENTACIONCLIENTE();

        input.setDocumentacioncliente(clientDocumentation);

        transaction.setReportaPagoInput(input);

        entry.setCorresponsal(corresponsal);
        entry.setTransaccione(transaction);

        transaction.setRequesttype("R");

        corresponsal.setIdmerchant(System.getProperty("PARAM_OE_ID_MERCHANTPAGO"));  // estet valor ex fijo para Girocheck??    o es uno para cada merchant? si

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[MockOrderExpressBusinessLogic] IDTELLERPAGO", null);
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[MockOrderExpressBusinessLogic] AUTHO_NUMBER", null);

        input.setIdteller(!"NULL".equals(MapUtil.getStringValueFromMap(map, ParameterName.IDTELLERPAGO, false)) ? OEUtils.singleQuote(MapUtil.getStringValueFromMap(map, ParameterName.IDTELLERPAGO, false)) : "NULL");
        input.setAutonumber(!"NULL".equals(MapUtil.getStringValueFromMap(map, ParameterName.AUTHO_NUMBER, false)) ? OEUtils.singleQuote(MapUtil.getStringValueFromMap(map, ParameterName.AUTHO_NUMBER, false)) : "NULL");

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[MockOrderExpressBusinessLogic] IDTELLERPAGO: " + map.get(ParameterName.IDTELLER).toString() + " AUTHO_NUMBER: " + map.get(ParameterName.AUTHO_NUMBER).toString(), null);

        corresponsal.setLogin(OEUtils.singleQuote(System.getProperty("PARAM_OE_LOGINPAGO")));
        corresponsal.setPassword(OEUtils.singleQuote(System.getProperty("PARAM_OE_PASSWORDPAGO")));

        //Client Documentation
        if (map.containsKey(ParameterName.IDTYPE) && map.containsKey(ParameterName.ID)) {

            IdType idType = (IdType) map.get(ParameterName.IDTYPE);
            String id = OEUtils.singleQuote(MapUtil.getStringValueFromMap(map, ParameterName.ID, true));

            switch (idType) {
                case VISA:
                    clientDocumentation.setVisa(id);
                    break;
                case PASSPORT:
                    clientDocumentation.setPasaporte(id);
                    break;
                case GREEN_CARD:
                    clientDocumentation.setGreencard(id);
                    break;

                case MATRICULA_CONSULAR:
                    clientDocumentation.setMatriculaconsular(id);
                    break;
                case DRIVER_LICENSE:
                    clientDocumentation.setLicencia(id);
                    break;
                case SSN:
                default:
                    clientDocumentation.setSs(id);
                    break;
            }

        }

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[MockOrderExpressBusinessLogic] Proccess cadenaRP", null);
        String cadena = OEUtils.processRequestRP(entry);

        String datos = "EX";
        String corresponsales = "GIRO_CHECK";
        String rutaEjecutar = null;

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[MockOrderExpressBusinessLogic] Printing cadenaRP value: " + cadena, null);
        Map responseMap = new HashMap();

        responseMap.put(ParameterName.AUTHO_NUMBER, "OE: AUTHO_NUMBER");
        responseMap.put(ParameterName.OP_CODE, "001");
        responseMap.put(ParameterName.OP_CODE2, "OE OP_CODE2");
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[MockOrderExpressBusinessLogic] Ready to return.", null);
        return responseMap;
    }

    private Map devolucion(Map map) throws Exception {

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[MockOrderExpressBusinessLogic] devolucionMethod()...", null);

        // Build ...
        //Entry
        LOTEENTRADAD entry = new LOTEENTRADAD();
        //Match
        LOTEENTRADAD.CORRESPONSAL corresponsal = new LOTEENTRADAD.CORRESPONSAL();
        //Transaction
        LOTEENTRADAD.TRANSACCIONE transaction = new LOTEENTRADAD.TRANSACCIONE();
        //Input
        LOTEENTRADAD.TRANSACCIONE.DEVOLUCIONINPUT input = new LOTEENTRADAD.TRANSACCIONE.DEVOLUCIONINPUT();

        transaction.setDevolucioninput(input);

        entry.setCorresponsal(corresponsal);
        entry.setTransaccione(transaction);

        //  fixed VALUES
        transaction.setRequesttype("D");

        corresponsal.setIdmerchant(System.getProperty("PARAM_OE_ID_MERCHANT"));  // estet valor ex fijo para Girocheck??    o es uno para cada merchant? si

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[MockOrderExpressBusinessLogic] IDTELLER", null);

        input.setIdTeller(!"NULL".equals(MapUtil.getStringValueFromMap(map, ParameterName.IDTELLER, false)) ? MapUtil.getStringValueFromMap(map, ParameterName.IDTELLER, false) : "NULL");

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[MockOrderExpressBusinessLogic] IDTELLER: " + map.get(ParameterName.IDTELLER).toString(), null);

        corresponsal.setLogin(OEUtils.singleQuote(System.getProperty("PARAM_OE_LOGIN")));
        corresponsal.setPassword(OEUtils.singleQuote(System.getProperty("PARAM_OE_PASSWORD")));

        input.setAutonumber((!"NULL".equals(MapUtil.getStringValueFromMap(map, ParameterName.AUTHO_NUMBER, false))) ? OEUtils.singleQuote(MapUtil.getStringValueFromMap(map, ParameterName.AUTHO_NUMBER, false)) : "NULL");

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[MockOrderExpressBusinessLogic] Proccess cadenaD", null);
        String cadena = OEUtils.processRequestD(entry);

        String datos = "EX";
        String corresponsales = "GIRO_CHECK";
        String rutaEjecutar = null;
        String devolucionResult = "";

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[MockOrderExpressBusinessLogic] Printing cadenaD value: " + cadena, null);
        Map responseMap = new HashMap();

        responseMap.put(ParameterName.AUTHO_NUMBER, "OE: AUTHO_NUMBER");
        responseMap.put(ParameterName.OP_CODE, "001");
        responseMap.put(ParameterName.OP_CODE2, "001");
        return responseMap;
    }

    private Map oElog(Map map) throws Exception {

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[MockOrderExpressBusinessLogic] OELogMethod()...", null);

        // Build ...
        //Entry
        LOTEENTRADAL entry = new LOTEENTRADAL();
        //Match
        LOTEENTRADAL.CORRESPONSAL corresponsal = new LOTEENTRADAL.CORRESPONSAL();
        //Transaction
        LOTEENTRADAL.TRANSACCIONE transaction = new LOTEENTRADAL.TRANSACCIONE();
        //Input
        LOTEENTRADAL.TRANSACCIONE.LOGINPUT input = new LOTEENTRADAL.TRANSACCIONE.LOGINPUT();

        transaction.setLoginput(input);

        entry.setCorresponsal(corresponsal);
        entry.setTransaccione(transaction);

        //  fixed VALUES
        transaction.setRequesttype("L");

        corresponsal.setIdmerchant(System.getProperty("PARAM_OE_ID_MERCHANT"));  // estet valor ex fijo para Girocheck??    o es uno para cada merchant? si

        input.setIdTeller(!"NULL".equals(MapUtil.getStringValueFromMap(map, ParameterName.IDTELLER, false)) ? MapUtil.getStringValueFromMap(map, ParameterName.IDTELLER, false) : "NULL");

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[MockOrderExpressBusinessLogic] IDTELLER: " + map.get(ParameterName.IDTELLER).toString(), null);

        corresponsal.setLogin(OEUtils.singleQuote(System.getProperty("PARAM_OE_LOGIN")));
        corresponsal.setPassword(OEUtils.singleQuote(System.getProperty("PARAM_OE_PASSWORD")));

        input.setAutonumber((!"NULL".equals(MapUtil.getStringValueFromMap(map, ParameterName.AUTHO_NUMBER, false))) ? OEUtils.singleQuote(MapUtil.getStringValueFromMap(map, ParameterName.AUTHO_NUMBER, false)) : "NULL");

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[MockOrderExpressBusinessLogic] Proccess cadenaL", null);
        String cadena = OEUtils.processRequestL(entry);

        String datos = "EX";
        String corresponsales = "GIRO_CHECK";
        String rutaEjecutar = null;
        String logResult = "";

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[MockOrderExpressBusinessLogic] Printing cadenaL value: " + cadena, null);

        Map responseMap = new HashMap();

        responseMap.put(ParameterName.OESTATUS, "3");

        return responseMap;
    }

    public LinkedList<String> addressSplitter(String address) {

        LinkedList<String> data = new LinkedList();
        String number;
        String street;

        int pos = 0;
        address = address.trim();
        while (address.charAt(pos) != ' ') {
            pos++;
        }

        number = address.substring(0, pos).trim();
        street = address.substring(pos).trim();

        data.add(number);
        data.add(street);

        return data;
    }

    /**
     * ******OE TIMER START*******
     */
    private static Map reqOELogMethod;
    private Map respOELogMethod = new HashMap();
    private static boolean timeOutOELog = true;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1000);

    private Map OETimerLogExecutor(Map req) throws InterruptedException {

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[MockOrderExpressBusinessLogic] OETimerLogExecutor() start processing ...", null);

        String temp;
        long period;
        long delay;
        reqOELogMethod = req;

        temp = System.getProperty("OE_TIMER_PERIOD");

        if (temp == null) {
            period = 5;
        } else {
            period = Long.parseLong(temp);
        }

        temp = System.getProperty("OE_TIMER_TOTAL");

        if (temp == null) {
            delay = 60;
        } else {
            delay = Long.parseLong(temp);
        }

        final Runnable logTask = new Runnable() {

            @Override
            public void run() {

                try {
                    respOELogMethod = oElog(reqOELogMethod);
                } catch (Exception ex) {
                    CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[MockOrderExpressBusinessLogic] OETimerLogExecutor() Error calling the OELog Method ", ex.getMessage());
                    scheduler.shutdownNow();
                    timeOutOELog = false;
                }
                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[MockOrderExpressBusinessLogic] OETimerLogExecutor() Doing verification code.", null);

                if (oEVerificationCode(respOELogMethod)) {
                    scheduler.shutdownNow();
                    timeOutOELog = false;
                }
                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[MockOrderExpressBusinessLogic] OETimerLogExecutor() ShutdownNow executed: " + !timeOutOELog, null);
            }
        };

        final ScheduledFuture<?> timerHandle = scheduler.scheduleAtFixedRate(logTask, 0, period, SECONDS);//rate execution time 

        scheduler.schedule(new Runnable() {
            @Override
            public void run() {
                boolean taskDone = timerHandle.cancel(true);
                scheduler.shutdown();
                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[MockOrderExpressBusinessLogic] OETimerLogExecutor() Time finished: " + taskDone, null);
            }
        }, delay, SECONDS);//complete time of executions

        scheduler.awaitTermination(delay + 5, SECONDS);

        respOELogMethod.put(ParameterName.OELOGTIMEOUT, timeOutOELog);

        return respOELogMethod;

    }

    public boolean oEVerificationCode(Map response) {

        String status = (String) response.get(ParameterName.OESTATUS);

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[MockOrderExpressBusinessLogic] oEVerificationCode() status: [" + status + "]", null);

        if (status != null) {

            if (status.equals("0") || status.equals("1") || status.equals("2") || status.equals("400")) {
                return false;
            }

        }

        return true;
    }
    /**
     * ****************OE TIMER END*****************
     */

}
