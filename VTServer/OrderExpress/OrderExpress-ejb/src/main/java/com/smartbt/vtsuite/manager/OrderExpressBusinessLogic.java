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
package com.smartbt.vtsuite.manager;

import com.smartbt.vtsuite.boundary.client.impl.LOTEENTRADA;
import com.smartbt.vtsuite.boundary.client.impl.LOTESALIDA;
import com.smartbt.vtsuite.boundary.client.impl.ObjectFactory;
import com.smartbt.girocheck.common.AbstractBusinessLogicModule;
import com.smartbt.girocheck.servercommon.enums.EnumCountry;
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
public class OrderExpressBusinessLogic extends AbstractBusinessLogicModule {

    private Service1 service = new Service1();
    private Service1Soap port = service.getService1Soap();

    /**
     * Constructor
     */
    public OrderExpressBusinessLogic() {

        String url = "";
        try {
            //development url:http://10.10.2.119:8081/stored/Service1.asmx
            url = System.getProperty("WS_OE_PRODUCTION_URL");

            if (url == null) {
                url = "http://gwin.orderexpress.com.mx:8081/stored/Service1.asmx?wsdl";
            }

            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[OrderExpressBusinessLogic] URL = " + url, null);

            BindingProvider bindingProvider = (BindingProvider) port;
            bindingProvider.getRequestContext().put(
                    BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
                    url);

        } catch (Exception ex) {
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[OrderExpressBusinessLogic] Error", ex.getMessage());
        }

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[OrderExpressBusinessLogic] URL value: " + url, null);

    }

    @Override
    public void preprocess(DirexTransactionRequest tr) throws Exception {
    }

    @Override
    public DirexTransactionResponse process(DirexTransactionRequest request) throws Exception {

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[OrderExpressBusinessLogic] processing ...", null);

        DirexTransactionResponse direxTransactionResponse = new DirexTransactionResponse();

        TransactionType transactionType = request.getTransactionType();
        Map map = request.getTransactionData();
        Map response = map;
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[OrderExpressBusinessLogic] processing transaction type: " + transactionType.toString(), null);
        if(map.containsKey(ParameterName.AUTHO_NUMBER)){
             CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[OrderExpressBusinessLogic] AUTHO_NUMBER = " + map.get(ParameterName.AUTHO_NUMBER), null);
        }
        
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

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[OrderExpressBusinessLogic] Proccess finish", null);
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
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, ">>> [OrderExpressBusinessLogic] ContratacionesMethod()...", null);

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

        Double amount = (Double) map.get(ParameterName.AMMOUNT);
        String fee = map.get(ParameterName.FEE_AMMOUNT) + "";

        Double payout = (Double) map.get(ParameterName.PAYOUT_AMMOUNT);

        if (amount > 2999.99) {
            System.out.println("[OrderExpressBusinessLogic] contrataciones ->  setting amount to 1. because amount was:: " + amount);
            amount = 1D;
        }

        input.setTOTAL(amount + "");
        input.setSERVICE(fee);

        input.setDEPOSIT(payout + "");
        input.setRELIEVE(payout + "");

        if (hostName.equalsIgnoreCase("TECNICARD") && operation.equalsIgnoreCase("02")) {

//            input.setDEPOSIT(MapUtil.getDoubleValueFromMap(map, ParameterName.PAYOUT_AMMOUNT, false));
            input.setTAX("NULL");
//            input.setTOTAL(MapUtil.getStringValueFromMap(map, ParameterName.AMMOUNT, false));
            input.setRATE("1.00");
//            input.setRELIEVE(MapUtil.getDoubleValueFromMap(map, ParameterName.PAYOUT_AMMOUNT, false));
//            input.setSERVICE(MapUtil.getStringValueFromMap(map, ParameterName.FEE_AMMOUNT, false));
        } else {
//            input.setDEPOSIT(MapUtil.getDoubleValueFromMap(map, ParameterName.PAYOUT_AMMOUNT, false));
            input.setTAX("NULL");
//            input.setTOTAL(MapUtil.getStringValueFromMap(map, ParameterName.AMMOUNT, false));
            input.setRATE("1.00");
//            input.setRELIEVE(MapUtil.getDoubleValueFromMap(map, ParameterName.PAYOUT_AMMOUNT, false));
//            input.setSERVICE(MapUtil.getStringValueFromMap(map, ParameterName.FEE_AMMOUNT, false));
        }
        corresponsal.setIDMERCHANT(System.getProperty("PARAM_OE_ID_MERCHANT"));  // estet valor ex fijo para Girocheck??    o es uno para cada merchant? si

        //1 x cada producto
        if (operation.equals("01")) {
            input.setIDDESTINY(System.getProperty("PARAM_OE_ID_DESTINY_CH") != null ? OEUtils.singleQuote(System.getProperty("PARAM_OE_ID_DESTINY_CH")) : "84693");//7574 - poner la variable en el glassfish
            input.setPAYMENTMETHOD(System.getProperty("PARAM_OE_PAYMENT_METHOD_CH") != null ? OEUtils.singleQuote(System.getProperty("PARAM_OE_PAYMENT_METHOD_CH")) : "02");

            input.setPAYMENTIMPORT(MapUtil.getStringValueFromMap(map, ParameterName.PAYOUT_AMMOUNT, false));
            input.setPAYMENTCOMISSION(MapUtil.getStringValueFromMap(map, ParameterName.FEE_AMMOUNT, false));
        } else {
            input.setIDDESTINY(System.getProperty("PARAM_OE_ID_DESTINY_CASH") != null ? OEUtils.singleQuote(System.getProperty("PARAM_OE_ID_DESTINY_CASH")) : "84693");//7574 - poner la variable en el glassfish
            input.setPAYMENTMETHOD(System.getProperty("PARAM_OE_PAYMENT_METHOD_CASH") != null ? OEUtils.singleQuote(System.getProperty("PARAM_OE_PAYMENT_METHOD_CASH")) : "01");

            input.setPAYMENTIMPORT(!"NULL".equals(MapUtil.getStringValueFromMap(map, ParameterName.PAYMENTIMPORT, false)) ? OEUtils.singleQuote(MapUtil.getStringValueFromMap(map, ParameterName.PAYMENTIMPORT, false)) : "NULL");
            input.setPAYMENTCOMISSION(!"NULL".equals(MapUtil.getStringValueFromMap(map, ParameterName.PAYMENTCOMISSION, false)) ? OEUtils.singleQuote(MapUtil.getStringValueFromMap(map, ParameterName.PAYMENTCOMISSION, false)) : "NULL");
        }

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[OrderExpressBusinessLogic] IDPOS&IDTELLER", null);
        if (map.containsKey(ParameterName.IDPOS) && map.get(ParameterName.IDPOS) != null) {
            input.setIDPOS(!"NULL".equals(MapUtil.getStringValueFromMap(map, ParameterName.IDPOS, false)) ? OEUtils.singleQuote(MapUtil.getStringValueFromMap(map, ParameterName.IDPOS, false)) : "NULL");
        }
        if (map.containsKey(ParameterName.IDTELLER) && map.get(ParameterName.IDTELLER) != null) {
            input.setIDTELLER(!"NULL".equals(MapUtil.getStringValueFromMap(map, ParameterName.IDTELLER, false)) ? OEUtils.singleQuote(MapUtil.getStringValueFromMap(map, ParameterName.IDTELLER, false)) : "NULL");
        }
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[OrderExpressBusinessLogic] IDPOS: " + map.get(ParameterName.IDPOS).toString() + " IDTELLER: " + map.get(ParameterName.IDTELLER).toString(), null);

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

        System.out.println("OEBusinessLogic -> Setting idCountry = 2");

//        if ( map.containsKey( ParameterName.IDCOUNTRY ) ) {
        String idCountry = "2";//(String) map.get( ParameterName.IDCOUNTRY );
        client.setIDPAIS(idCountry + "");
        clientB.setIDPAISB(idCountry + "");
//        }

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
                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[OrderExpressBusinessLogic] Proccess cadena with idType: " + idType, null);
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

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[OrderExpressBusinessLogic] Proccess cadena", null);
        String cadena = OEUtils.processRequest(entry);

        String datos = "EX";
//        String corresponsales = "OEINC";
        String corresponsales = "GIRO_CHECK";
        String rutaEjecutar = null;

        ContratacionesResponse response = new ContratacionesResponse();

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[OrderExpressBusinessLogic] Printing cadena value: " + cadena, null);
        String contratacionesResult = port.contrataciones(cadena, datos, corresponsales, rutaEjecutar);

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[OrderExpressBusinessLogic] contratacionesResult value: " + contratacionesResult, null);
        response.setContratacionesResult(contratacionesResult);

        Map responseMap = new HashMap();
//        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[OrderExpressBusinessLogic] decoding contratacionesResult data",null);
        try {
            LOTESALIDA lotesalida = (LOTESALIDA) OEUtils.unMarshallResponse(JAXBContext.newInstance(LOTESALIDA.class
            ), OEUtils.decodeBase64String(response.getContratacionesResult()));
            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[OrderExpressBusinessLogic] contratacionesResult data decoded.", null);

            responseMap.put(ParameterName.AUTHO_NUMBER, lotesalida.getTRANSACCIONO().getCONTRATACIONOUTPUT().getAUTHONUMBER());
            responseMap.put(ParameterName.OP_CODE, lotesalida.getTRANSACCIONO().getCONTRATACIONOUTPUT().getOPCODE());
            responseMap.put(ParameterName.OP_CODE2, lotesalida.getTRANSACCIONO().getCONTRATACIONOUTPUT().getOPCODE2());
            responseMap.put(ParameterName.IDCONSIGNOR, lotesalida.getTRANSACCIONO().getCONTRATACIONOUTPUT().getIDCONSIGNOR());
            responseMap.put(ParameterName.IDBENEFICIARY, lotesalida.getTRANSACCIONO().getCONTRATACIONOUTPUT().getIDBENEFICIARY());
            responseMap.put(ParameterName.BANK_AUTHO, lotesalida.getTRANSACCIONO().getCONTRATACIONOUTPUT().getBANKAUTHO());
        } catch (Exception e) {
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[OrderExpressBusinessLogic] Error getting data from lotesalilda.", null);
            e.printStackTrace();
        }
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[OrderExpressBusinessLogic] Ready to return.", null);
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

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[OrderExpressBusinessLogic] reportaPago Method()...", null);

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

//        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[OrderExpressBusinessLogic] IDTELLERPAGO",null);
//        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[OrderExpressBusinessLogic] AUTHO_NUMBER",null);
        input.setIdteller(!"NULL".equals(MapUtil.getStringValueFromMap(map, ParameterName.IDTELLERPAGO, false)) ? OEUtils.singleQuote(MapUtil.getStringValueFromMap(map, ParameterName.IDTELLERPAGO, false)) : "NULL");
        input.setAutonumber(!"NULL".equals(MapUtil.getStringValueFromMap(map, ParameterName.AUTHO_NUMBER, false)) ? OEUtils.singleQuote(MapUtil.getStringValueFromMap(map, ParameterName.AUTHO_NUMBER, false)) : "NULL");

//        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[OrderExpressBusinessLogic] IDTELLERPAGO: " +map.get(ParameterName.IDTELLER).toString() + " AUTHO_NUMBER: "+map.get(ParameterName.AUTHO_NUMBER).toString(),null);
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

//        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[OrderExpressBusinessLogic] Proccess cadenaRP",null);
        String cadena = OEUtils.processRequestRP(entry);

        String datos = "EX";
        String corresponsales = "GIRO_CHECK";
        String rutaEjecutar = null;

//        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[OrderExpressBusinessLogic] Printing cadenaRP value: " +cadena,null);
        String reportaPagoResult = port.reportaPago(cadena, datos, corresponsales, rutaEjecutar);
//        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[OrderExpressBusinessLogic] printing reportaPagoResult.lenght(): " + reportaPagoResult.length(),null);
//
//        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[OrderExpressBusinessLogic] reportaPagoResult value: " + reportaPagoResult,null);

        Map responseMap = new HashMap();

//        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[OrderExpressBusinessLogic] decoding reportaPagoResult data",null);
        try {
            LOTESALIDARP lotesalida = (LOTESALIDARP) OEUtils.unMarshallResponseRP(JAXBContext.newInstance(LOTESALIDARP.class
            ), OEUtils.decodeBase64String(reportaPagoResult));

//        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[OrderExpressBusinessLogic] reportaPagoResult data decoded.",null);
//
//        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[OrderExpressBusinessLogic] reportaPagoResult autho number value3: "+lotesalida.getTransacciono().getReportapagooutput().getAuthonumber(),null);
            responseMap.put(ParameterName.AUTHO_NUMBER, lotesalida.getTransacciono().getReportapagooutput().getAuthonumber());
            responseMap.put(ParameterName.OP_CODE, lotesalida.getTransacciono().getReportapagooutput().getOpcode());
            responseMap.put(ParameterName.OP_CODE2, lotesalida.getTransacciono().getReportapagooutput().getOpcode2());

        } catch (Exception e) {
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[OrderExpressBusinessLogic] Error getting data from lotesalidaRP.", null);
            e.printStackTrace();
        }
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[OrderExpressBusinessLogic] Ready to return.", null);
        return responseMap;
    }

    private Map devolucion(Map map) throws Exception {

//        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[OrderExpressBusinessLogic] devolucionMethod()...",null);
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

//        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[OrderExpressBusinessLogic] IDTELLER",null);
        input.setIdTeller(!"NULL".equals(MapUtil.getStringValueFromMap(map, ParameterName.IDTELLER, false)) ? MapUtil.getStringValueFromMap(map, ParameterName.IDTELLER, false) : "NULL");

//        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[OrderExpressBusinessLogic] IDTELLER: " +map.get(ParameterName.IDTELLER).toString(),null);
        corresponsal.setLogin(OEUtils.singleQuote(System.getProperty("PARAM_OE_LOGIN")));
        corresponsal.setPassword(OEUtils.singleQuote(System.getProperty("PARAM_OE_PASSWORD")));

        input.setAutonumber((!"NULL".equals(MapUtil.getStringValueFromMap(map, ParameterName.AUTHO_NUMBER, false))) ? OEUtils.singleQuote(MapUtil.getStringValueFromMap(map, ParameterName.AUTHO_NUMBER, false)) : "NULL");

//        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[OrderExpressBusinessLogic] Proccess cadenaD",null);
        String cadena = OEUtils.processRequestD(entry);

        String datos = "EX";
        String corresponsales = "GIRO_CHECK";
        String rutaEjecutar = null;
        String devolucionResult = "";

//        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[OrderExpressBusinessLogic] Printing cadenaD value: " +cadena,null);
        try {
            devolucionResult = port.devolucion(cadena, datos, corresponsales, rutaEjecutar);
        } catch (Exception e) {
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[OrderExpressBusinessLogic] Devolucion Exception from port.devolucion(...).", null);
            e.printStackTrace();
        }
//        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[OrderExpressBusinessLogic] printing devolucionResult.lenght(): " + devolucionResult.length(),null);

//        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[OrderExpressBusinessLogic] devolucionResult value: " + devolucionResult,null);
        Map responseMap = new HashMap();
//        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[OrderExpressBusinessLogic] decoding devolucionResult data",null);
        try {
            LOTESALIDAD lotesalida = (LOTESALIDAD) OEUtils.unMarshallResponseD(JAXBContext.newInstance(LOTESALIDAD.class
            ), OEUtils.decodeBase64String(devolucionResult));
//        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[OrderExpressBusinessLogic] devolucionResult data decoded.",null);

//        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[OrderExpressBusinessLogic] devolucionResult authoNumber value: " + lotesalida.getTransacciono().getDevolucionoutput().getAuthonumber(),null);
            responseMap.put(ParameterName.AUTHO_NUMBER, lotesalida.getTransacciono().getDevolucionoutput().getAuthonumber());
            responseMap.put(ParameterName.OP_CODE, lotesalida.getTransacciono().getDevolucionoutput().getOpcode());
            responseMap.put(ParameterName.OP_CODE2, lotesalida.getTransacciono().getDevolucionoutput().getOpcode2());

        } catch (Exception e) {
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[OrderExpressBusinessLogic] Error getting data from lotesalidaD.", null);
            e.printStackTrace();
        }
//        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[OrderExpressBusinessLogic] Ready to return.",null);
        return responseMap;
    }

    private Map oElog(Map map) throws Exception {

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[OrderExpressBusinessLogic] OELogMethod()...", null);

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

//        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[OrderExpressBusinessLogic] IDTELLER: " +map.get(ParameterName.IDTELLER).toString(),null);
        corresponsal.setLogin(OEUtils.singleQuote(System.getProperty("PARAM_OE_LOGIN")));
        corresponsal.setPassword(OEUtils.singleQuote(System.getProperty("PARAM_OE_PASSWORD")));

        input.setAutonumber((!"NULL".equals(MapUtil.getStringValueFromMap(map, ParameterName.AUTHO_NUMBER, false))) ? OEUtils.singleQuote(MapUtil.getStringValueFromMap(map, ParameterName.AUTHO_NUMBER, false)) : "NULL");

//        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[OrderExpressBusinessLogic] Proccess cadenaL",null);
        String cadena = OEUtils.processRequestL(entry);

        String datos = "EX";
        String corresponsales = "GIRO_CHECK";
        String rutaEjecutar = null;
        String logResult = "";

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[OrderExpressBusinessLogic] Printing OELogs cadena : " +cadena,null);
        try {
            logResult = port.logs(cadena, datos, corresponsales, rutaEjecutar);
        } catch (Exception e) {
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[OrderExpressBusinessLogic] Log Exception from port.logs(...).", null);
            e.printStackTrace();
        }
//        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[OrderExpressBusinessLogic] printing logResult.lenght(): " + logResult.length(),null);
//
//        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[OrderExpressBusinessLogic] logResult value: " + logResult,null);

        Map responseMap = new HashMap();
        String outputCadena = OEUtils.decodeBase64String(logResult);
//        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[OrderExpressBusinessLogic] decoding logResult data",null);
        try {
            LOTESALIDAL lotesalida = (LOTESALIDAL) OEUtils.unMarshallResponseL(JAXBContext.newInstance(LOTESALIDAL.class
            ), outputCadena);
//        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[OrderExpressBusinessLogic] logResult data decoded.",null);

            System.out.println("");
             CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[OrderExpressBusinessLogic] Printing OELogs output cadena : " + outputCadena,null);
       
            responseMap = lotesalida.toMap();

        } catch (Exception e) {
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[OrderExpressBusinessLogic] Error getting data from lotesalidaL.", null);
            e.printStackTrace();
        }
//        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[OrderExpressBusinessLogic] Ready to return.",null);

        if (responseMap != null) {
            CustomeLogger.Output("[OEBusinessLogic] Response from method log OE OP_CODE : " + responseMap.get(ParameterName.OP_CODE));
            CustomeLogger.Output("[OEBusinessLogic] Response from method log OE OESTATUS : " + responseMap.get(ParameterName.OESTATUS));
            CustomeLogger.Output("[OEBusinessLogic] Response from method log OE IDTELLER : " + responseMap.get(ParameterName.IDTELLER));
            CustomeLogger.Output("[OEBusinessLogic] Response from method log OE OEDATE_TIME : " + responseMap.get(ParameterName.OEDATE_TIME));
            CustomeLogger.Output("[OEBusinessLogic] Response from method log OE OENOTES : " + responseMap.get(ParameterName.OENOTES));
            CustomeLogger.Output("[OEBusinessLogic] Response from method log OE AUTHO_NUMBER : " + responseMap.get(ParameterName.AUTHO_NUMBER));
        }

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

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[OrderExpressBusinessLogic] OETimerLogExecutor() start processing ...", null);

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
                    CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[OrderExpressBusinessLogic] OETimerLogExecutor() Error calling the OELog Method ", ex.getMessage());
                    scheduler.shutdownNow();
                    timeOutOELog = false;
                }
                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[OrderExpressBusinessLogic] OETimerLogExecutor() Doing verification code.", null);

                if (oEVerificationCode(respOELogMethod)) {
                    scheduler.shutdownNow();
                    timeOutOELog = false;
                }
                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[OrderExpressBusinessLogic] OETimerLogExecutor() ShutdownNow executed: " + !timeOutOELog, null);
            }
        };

        final ScheduledFuture<?> timerHandle = scheduler.scheduleAtFixedRate(logTask, 0, period, SECONDS);//rate execution time 

        scheduler.schedule(new Runnable() {
            @Override
            public void run() {
                boolean taskDone = timerHandle.cancel(true);
                scheduler.shutdown();
                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[OrderExpressBusinessLogic] OETimerLogExecutor() Time finished: " + taskDone, null);
            }
        }, delay, SECONDS);//complete time of executions

        scheduler.awaitTermination(delay + 5, SECONDS);

        respOELogMethod.put(ParameterName.OELOGTIMEOUT, timeOutOELog);

        return respOELogMethod;

    }

    public boolean oEVerificationCode(Map response) {

        String status = (String) response.get(ParameterName.OESTATUS);

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[OrderExpressBusinessLogic] oEVerificationCode() status: [" + status + "]", null);

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
