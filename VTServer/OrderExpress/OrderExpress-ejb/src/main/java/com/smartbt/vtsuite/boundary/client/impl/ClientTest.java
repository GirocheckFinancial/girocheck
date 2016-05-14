//package com.smartbt.vtsuite.boundary.client.impl;
//
///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//import com.smartbt.vtsuite.boundary.client.ContratacionesResponse;
//import com.smartbt.vtsuite.boundary.client.OrderExpressService;
//import com.smartbt.vtsuite.boundary.client.OrderExpressSoap;
//import com.smartbt.vtsuite.utils.OEUtils;
//import static com.smartbt.vtsuite.utils.OEUtils.decodeBase64String;
//import static com.smartbt.vtsuite.utils.OEUtils.marshallToBase64;
//import static com.smartbt.vtsuite.utils.OEUtils.unMarshallResponse;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import javax.xml.bind.*;
//import javax.xml.ws.soap.SOAPFaultException;
//
///**
// *
// * @author Edward Beckett :: <Edward.Beckett@smartbt.com>
// */
//public class ClientTest {
//
//    public static void main( String... args ) throws Exception {
//
//        OrderExpressService service = new OrderExpressService();
//
//        OrderExpressSoap port = service.OrderExpressSoap();
//
//        ObjectFactory objectFactory = new ObjectFactory();
//
//        //ENTRY
//        LOTEENTRADA entry = objectFactory.createLOTEENTRADA();
//        //MATCH
//        LOTEENTRADA.CORRESPONSAL corresponsal = objectFactory.createLOTEENTRADACORRESPONSAL();
//        //TRANSACTION 
//        LOTEENTRADA.TRANSACCIONE transaction = objectFactory.createLOTEENTRADATRANSACCIONE();
//        //INPUT
//        LOTEENTRADA.TRANSACCIONE.CONTRATACIONINPUT input = objectFactory.createLOTEENTRADATRANSACCIONECONTRATACIONINPUT();
//        //CLIENT
//        LOTEENTRADA.TRANSACCIONE.CONTRATACIONINPUT.CLIENTE client = objectFactory.createLOTEENTRADATRANSACCIONECONTRATACIONINPUTCLIENTE();
//        //CLIENT DOCUMENTATION 
//        LOTEENTRADA.TRANSACCIONE.CONTRATACIONINPUT.CLIENTE.DOCUMENTACIONCLIENTE clientDocumentation = objectFactory.createLOTEENTRADATRANSACCIONECONTRATACIONINPUTCLIENTEDOCUMENTACIONCLIENTE();
//        //CLIENT 'B' 
//        LOTEENTRADA.TRANSACCIONE.CONTRATACIONINPUT.CLIENTEB clientB = objectFactory.createLOTEENTRADATRANSACCIONECONTRATACIONINPUTCLIENTEB();
//        //CLIENT 'B' DOCUMENTATION
//        LOTEENTRADA.TRANSACCIONE.CONTRATACIONINPUT.CLIENTEB.DOCUMENTACIONCLIENTEB clientDocumentationB = objectFactory.createLOTEENTRADATRANSACCIONECONTRATACIONINPUTCLIENTEBDOCUMENTACIONCLIENTEB();
//
//        //ENTRY ID
//        entry.setIDLOTE( "NULL" );
//
//        //SET MATCH
//        entry.setCORRESPONSAL( corresponsal );
//
//        //SET BATCH MATCH PARAMS       
//        corresponsal.setIDMERCHANT( "33" );
//        corresponsal.setLOGIN( OEUtils.singleQuote( "oeinc" ) );
//        corresponsal.setPASSWORD( OEUtils.singleQuote( "oe&inc" ) );
//
//        //SET TRANSACTION 
//        entry.setTRANSACCIONE( transaction );
//
//        //SET TRANSACTION PARAMS
//        transaction.setREQUESTTYPE( "T" );
//        transaction.setIDTRANSACCION( "NULL" );
//
//        //SET TRANSACTION INPUT
//        transaction.setCONTRATACIONINPUT( input );
//
//        //BATCH TRANSACTION INPUT PARAMS
//        input.setAUTONUMBER( "NULL" );
//        input.setIDPOS( "1376" );
//        input.setIDSERVICE( "1" );
//        input.setCARDNUMBER( "NULL" );
//        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS");
//        Date now = new Date();
//        input.setDATETIME( OEUtils.singleQuote( fmt.format( now ) ) );
//        input.setDEPOSIT( "500" );
//        input.setSERVICE( "2.00" );
//        input.setDISCOUNT( "NULL" );
//        input.setTAX( "NULL" );
//        input.setTOTAL( "502" );
//        input.setRATE( "10" );
//        input.setRELIEVE( "5000" );
//        input.setTRANSTYPE( "NULL" );
//        input.setIDREASON( "NULL" );
//        input.setIDDESTINY( "8109" );
//        input.setIDTELLER( "4280" );
//        input.setNOTES( "NULL" );
//        input.setACCOUNTNUM( "NULL" );
//        input.setCHECKNUM( "NULL" );
//        input.setACCOUNTRETURN( "NULL" );
//        input.setCHECKRETURN( "NULL" );
//        input.setIDCONSIGNOR( "NULL" );
//        input.setIDBENEFICIARY( "NULL" );
//        input.setBANKAUTHO( "NULL" );
//        input.setDEPOSITACCOUNT( "NULL" );
//        input.setIDTRANSREFUND( "NULL" );
//        input.setIDSTATUS( "NULL" );
//        input.setPAYMENTMETHOD( "1" );
//        input.setPAYMENTCHECK( "NULL" );
//        input.setPAYMENTIMPORT( "NULL" );
//        input.setPAYMENTCOMISSION( "NULL" );
//        input.setREPORTED( "NULL" );
//        input.setCREDITNUMBER( "NULL" );
//        input.setINFORMED( "1" );
//
//        //SET CLIENT
//        input.setCLIENTE( client );
//        //SET CLIENT 'B'
//        input.setCLIENTEB( clientB );
//
//        //CLIENT PARAMS        
//        //client.setTipo( "" );
//        client.setIDCLIENTE( "NULL" );
//        client.setPIN( "NULL" );
//        client.setSALUTE( "NULL" );
//        client.setFNAME( OEUtils.singleQuote( "ALEJANDRO ") );
//        client.setMNAME( OEUtils.singleQuote( "" ) );
//        client.setSNAME( OEUtils.singleQuote( "PINEDA" ) );
//        client.setLNAME( OEUtils.singleQuote( "GARIBAY" ) );
//        client.setBORNDATE( OEUtils.singleQuote( "1975-10-31" ) );
//        client.setTAXIDINI( "NULL" );
//        client.setTAXIDDATE( "NULL" );
//        client.setTAXIDHOMO( "NULL" );
//        client.setTAXIDINT( "NULL" );
//        client.setSTREET( OEUtils.singleQuote( "OHIO ST" ) );
//        client.setNUMBER( OEUtils.singleQuote( "685" ) );
//        client.setINTERIOR( "NULL" );
//        client.setCOLONY( OEUtils.singleQuote( "RESIDENCIA CENTRAL" ) );
//        client.setZIPCODE( "NULL" );
//        client.setIDPAIS( "2" );
//        client.setIDESTADO( "16" );
//        client.setPOBLACION( OEUtils.singleQuote( "CHICAGO" ) );
//        client.setEMAIL( "NULL" );
//        client.setTELEPHONE1( OEUtils.singleQuote( "3461254" ) );
//        client.setQUESTION1( "NULL" );
//        client.setANSWER1( "NULL" );
//        client.setDATECREATED( "NULL" );
//        client.setDATEMODIFIED( "NULL" );
//        client.setSTATUS( "NULL" );
//        client.setIDOCUPACION( "0" );
//
//        //SET DOCUMENATION
//        client.setDOCUMENTACIONCLIENTE( clientDocumentation );
//
//        //DOCUMENTATION PARAMS
//        clientDocumentation.setVISA( OEUtils.singleQuote( "1234567890" ) );
//        clientDocumentation.setPASAPORTE( "NULL" );
//        clientDocumentation.setGREENCARD( "NULL" );
//        clientDocumentation.setSS( "NULL" );
//        clientDocumentation.setMATRICULACONSULAR( "NULL" );
//        clientDocumentation.setIFE( "NULL" );
//        clientDocumentation.setLICENCIA( "NULL" );
//
//        //SET CLIENT 'B' 
//        //clientB.setTipo( "" );
//        clientB.setIDCLIENTEB( "NULL" );
//        clientB.setPINB( "NULL" );
//        clientB.setSALUTEB( "NULL" );
//        clientB.setFNAMEB( OEUtils.singleQuote( "ANGELES" ) );
//        clientB.setMNAMEB( "NULL" );
//        clientB.setSNAMEB( OEUtils.singleQuote( "HERNANDEZ" ) );
//        clientB.setLNAMEB(  OEUtils.singleQuote( "ALVEAR" ) );
//        clientB.setBORNDATEB( OEUtils.singleQuote( "1979-02-24" ) );
//        clientB.setTAXIDINIB( "NULL" );
//        clientB.setTAXIDDATEB( "NULL" );
//        clientB.setTAXIDHOMOB( "NULL" );
//        clientB.setTAXIDINTB( "NULL" );
//        clientB.setSTREETB( OEUtils.singleQuote( "SALVADOR A ROMERO" ) );
//        clientB.setNUMBERB( OEUtils.singleQuote( "23" ) );
//        clientB.setINTERIORB( "NULL" );
//        clientB.setCOLONYB( OEUtils.singleQuote( "CONSTITUYENTES" ) );
//        clientB.setZIPCODEB( "1434" );
//        clientB.setIDPAISB( "1" );
//        clientB.setIDESTADOB( "16" );
//        clientB.setPOBLACIONB( OEUtils.singleQuote( "MARVATIO" ) );
//        clientB.setEMAILB( "NULL" );
//        clientB.setTELEPHONE1B(  OEUtils.singleQuote( "4474780000" ) );
//        clientB.setQUESTION1B( "NULL" );
//        clientB.setANSWER1B( "NULL" );
//        clientB.setDATECREATEDB( "NULL" );
//        clientB.setDATEMODIFIEDB( "NULL" );
//        clientB.setSTATUSB( "NULL" );
//        clientB.setIDOCUPACIONB( "0" );
//
//        //SET CLIENT 'B' DOCUMENTATION
//        clientB.setDOCUMENTACIONCLIENTEB( clientDocumentationB );
//
//        clientDocumentationB.setVISAB( "NULL" );
//        clientDocumentationB.setPASAPORTEB( "NULL" );
//        clientDocumentationB.setGREENCARDB( OEUtils.singleQuote( "3939393" ) );
//        clientDocumentationB.setSSB( "NULL" );
//        clientDocumentationB.setMATRICULACONSULARB( "NULL" );
//        clientDocumentationB.setIFEB( "NULL" );
//        clientDocumentationB.setLICENCIAB( "NULL" );
//
//        /**
//         * BUILD OUT THE OBJECT IN XML AND CONVERT IT TO BASE64 STRING...
//         */
//        try {
//
//            JAXBContext xmlEntry = JAXBContext.newInstance( entry.getClass() );
//            String encoded = marshallToBase64( xmlEntry, entry );
//            ContratacionesResponse response = new ContratacionesResponse();
//            //String decoded = decodeBase64String(encoded);
//            System.out.println( entry );
//            System.out.println( encoded );
//            //System.out.println( decoded );
//
//            response.setContratacionesResult( port.contrataciones( encoded, "EX", "OEINC", "" ) );
//
//            System.out.println( "********RESPONSE***********" );
//            System.out.println( response.getContratacionesResult() );
//
//            JAXBContext ctx = JAXBContext.newInstance( LOTESALIDA.class );
//            String decoded = decodeBase64String( response.getContratacionesResult() );
//
//            LOTESALIDA lotesalida = (LOTESALIDA) unMarshallResponse( ctx, decoded );
//
//            System.out.println( "********RESULT***********" );
//
//            String autho_number = lotesalida.getTRANSACCIONO().getCONTRATACIONOUTPUT().getAUTHONUMBER();
//            String op_code = lotesalida.getTRANSACCIONO().getCONTRATACIONOUTPUT().getOPCODE();
//            String op_code2 = lotesalida.getTRANSACCIONO().getCONTRATACIONOUTPUT().getOPCODE2();
//            String id_cosignor = lotesalida.getTRANSACCIONO().getCONTRATACIONOUTPUT().getIDCONSIGNOR();
//            String id_beneficiary = lotesalida.getTRANSACCIONO().getCONTRATACIONOUTPUT().getIDBENEFICIARY();
//            String bank_autho = lotesalida.getTRANSACCIONO().getCONTRATACIONOUTPUT().getBANKAUTHO();
//            
//            System.out.println( 
//                    autho_number + "\n" +
//                    op_code  + "\n" +
//                    op_code2  + "\n" +
//                    id_cosignor  + "\n" +
//                    id_beneficiary
//            );
//
//        } catch ( JAXBException | SOAPFaultException e ) {
//            e.getMessage();
//        }
//
//    }
//
//}
