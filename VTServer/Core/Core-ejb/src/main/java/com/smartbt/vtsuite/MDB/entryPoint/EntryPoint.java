/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartbt.vtsuite.MDB.entryPoint;

import com.smartbt.vtsuite.MDB.CoreMDB;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.enums.ParameterName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Roberto
 */
public class EntryPoint {
    
   public static void main( String[] args ) {
        
        DirexTransactionRequest request = new DirexTransactionRequest();
        
        
         System.out.println("SELECT THE METHOD YOU WANT TO TEST");
            System.out.println("  1 - checkAuth");                   //com.sun.xml.ws.client.ClientTransportException: The server sent HTTP status code 302: Found
            System.out.println("  2 - checkAuthLocationConfig");     //com.sun.xml.ws.client.ClientTransportException: The server sent HTTP status code 302: Found
            System.out.println("  3 - checkAuthSubmit");             //com.sun.xml.ws.client.ClientTransportException: The server sent HTTP status code 302: Found
            System.out.println("  4 - enhancedCheckAuthPoll");       //com.sun.xml.ws.client.ClientTransportException: The server sent HTTP status code 302: Found
            System.out.println("  5 - checkAuthPoll");       //com.sun.xml.ws.client.ClientTransportException: The server sent HTTP status code 302: Found
            System.out.println("  6 -  wmBalanceInquiry()");       //com.sun.xml.ws.client.ClientTransportException: The server sent HTTP status code 302: Found
            
            Scanner cin = new Scanner(System.in);
            
            int option = cin.nextInt();
            
            Map map = new HashMap();
            
            switch(option){
                case 1: map = checkAuth();
                        break;
                case 2: map = checkAuthLocationConfig();
                        break;
                case 3: map = checkAuthSubmit();
                        break;
                case 4: map = enhancedCheckAuthPoll();
                        break;
                case 5: map = checkAuthPoll();
                        break;
                case 6: map =  wmBalanceInquiry();
                        break;
                default:System.out.println("SELECT OPERATION BETWEEN 1-5");  
                        main(new String[]{});
            }
        
        request.setTransactionData(map);
        request.setCorrelation("11111");
        
        CoreMDB core = new CoreMDB();
        
        CoreMessage message = new CoreMessage();
        message.setObject(request);
        
        core.onMessage(message);
        
        
         main(new String[]{});
    }
    
      
      public static Map wmCardToBank() {
            Map map = new HashMap();

           map.put(TransactionType.TRANSACTION_TYPE , TransactionType.TECNICARD_CARD_TO_BANK);

          map.put(ParameterName.AMMOUNT, "10.00");
          map.put(ParameterName.ROUTING_BANK_NUMBER, "12");
          map.put(ParameterName.CARD_NUMBER, "5448353882100482");
          map.put(ParameterName.ACCOUNT_NUMBER, "12");
          map.put(ParameterName.REQUEST_ID, "12");
             return map;
          }
      
        public static Map checkAuth() {
            Map map = new HashMap();

           map.put(TransactionType.TRANSACTION_TYPE , TransactionType.ISTREAM_CHECK_AUTH);

          map.put(ParameterName.ADDRESS_CORRECT, "Y");
          map.put(ParameterName.AMMOUNT, "12.00");
          map.put(ParameterName.BACK_TIFF, new byte[]{});
          map.put(ParameterName.BATCH_NAME, "alphaNumericValue1");
          map.put(ParameterName.CARD_NUMBER, "5448353882100482");
          map.put(ParameterName.CHECK_BACK, new byte[]{});
          map.put(ParameterName.CHECK_FRONT, new byte[]{});
          map.put(ParameterName.CRC, "12313");
          map.put(ParameterName.DATA, new ArrayList<Object>());
          map.put(ParameterName.EMAIL_ADDRESS, new ArrayList<String>());
          map.put(ParameterName.ENTITY_ID, "AN6omas");
          map.put(ParameterName.FRONT_TIFF, new byte[]{});
          map.put(ParameterName.IDBACK, new byte[]{});
          map.put(ParameterName.IDFRONT, new byte[]{});
          map.put(ParameterName.MICR, "1324234234");
          map.put(ParameterName.PASSWORD, "hon237");
          map.put(ParameterName.PHONE, "1234567890");
          map.put(ParameterName.SCANNER_MANUFACTURER, "alphaNumericValue2");
          map.put(ParameterName.USER, "sbttest");
          map.put(ParameterName.SERIAL, "alphaNumericValue3");
          map.put(ParameterName.SSN, "123456789");
             return map;
          }
        
        public static Map wmBalanceInquiry() {
                   Map map = new HashMap();

                  map.put(TransactionType.TRANSACTION_TYPE , TransactionType.TECNICARD_BALANCE_INQUIRY);

                        map.put(ParameterName.CARD_NUMBER, "5448353882100482" );
                        map.put(ParameterName.REQUEST_ID, "12");
             return map;
          }
    
        
        private static Map checkAuthLocationConfig(){
        Map map = new HashMap();
        map.put(TransactionType.TRANSACTION_TYPE , TransactionType.ISTREAM_CHECK_AUTH_LOCATION_CONFIG);
        map.put(ParameterName.ENTITY_ID , 4113776);
        map.put(ParameterName.PASSWORD , "hon237");
        map.put(ParameterName.USER , "sbttest");
        return map;

}
   
     public static Map checkAuthSubmit() {
            Map map = new HashMap();

           map.put(TransactionType.TRANSACTION_TYPE , TransactionType.ISTREAM_CHECK_AUTH_SUBMIT);

          map.put(ParameterName.ACTION, "12");
          map.put(ParameterName.CHECK_ID, "12");
          map.put(ParameterName.PASSWORD, "hon237");
          map.put(ParameterName.USER, "sbttest");
             return map;
          }
   
     public static Map enhancedCheckAuthPoll() {
            Map map = new HashMap();

           map.put(TransactionType.TRANSACTION_TYPE , TransactionType.ISTREAM_ENHANCED_CHECK_AUTH_POLL);

          map.put(ParameterName.CHECK_ID, new ArrayList<Object>());
          map.put(ParameterName.ENTITY_ID, "4113776");
          map.put(ParameterName.PASSWORD, "hon237");
          map.put(ParameterName.USER, "sbttest");
             return map;
          }
     
      public static Map checkAuthPoll() {
            Map map = new HashMap();

           map.put(TransactionType.TRANSACTION_TYPE , TransactionType.ISTREAM_CHECK_AUTH_POLL);

          map.put(ParameterName.CHECK_ID, new ArrayList<Object>());
          map.put(ParameterName.ENTITY_ID, "254724");
          map.put(ParameterName.PASSWORD, "hon237");
          map.put(ParameterName.USER, "sbttest");
             return map;
          }
        
    public static Map contrataciones() {

       
        Map map = new HashMap();

        map.put( TransactionType.TRANSACTION_TYPE, TransactionType.ORDER_EXPRESS_CONTRATACIONES );
        map.put( ParameterName.IDLOTE, "NULL" );

        map.put( ParameterName.IDMERCHANT, "33" );
        map.put( ParameterName.LOGIN, "USER" );
        map.put( ParameterName.PASSWORD, "PASSWORD" );

        map.put( ParameterName.REQUEST_TYPE, "T" );
        map.put( ParameterName.IDTRANSACCION, "NULL" );

        map.put( ParameterName.AUTONUMBER, "NULL" );
        map.put( ParameterName.IDPOS, "1376" );
        map.put( ParameterName.IDSERVICE, "1" );
        map.put( ParameterName.CARD_NUMBER, "NULL" );
        map.put( ParameterName.DATETIME, "2010-02-14 14:15:43.000" );
        map.put( ParameterName.DEPOSIT, "500" );
        map.put( ParameterName.SERVICE, "2.00" );
        map.put( ParameterName.DISCOUNT, "NULL" );
        map.put( ParameterName.TAX, "NULL" );
        map.put( ParameterName.TOTAL, "502" );
        map.put( ParameterName.RATE, "10" );
        map.put( ParameterName.RELIEVE, "5000" );
        map.put( ParameterName.TRANSTYPE, "NULL" );
        map.put( ParameterName.IDREASON, "NULL" );
        map.put( ParameterName.IDDESTINY, "8109" );
        map.put( ParameterName.IDTELLER, "4280" );
        map.put( ParameterName.NOTES, "NULL" );
        map.put( ParameterName.ACCOUNTNUM, "NULL" );
        map.put( ParameterName.ACCOUNTRETURN, "NULL" );
        map.put( ParameterName.CHECKRETURN, "NULL" );
        map.put( ParameterName.IDCONSIGNOR, "NULL" );
        map.put( ParameterName.IDBENEFICIARY, "NULL" );
        map.put( ParameterName.BANK_AUTHO, "NULL" );
        map.put( ParameterName.DEPOSITACCOUNT, "NULL" );
        map.put( ParameterName.IDTRANSREFUND, "NULL" );
        map.put( ParameterName.IDSTATUS, "NULL" );
        map.put( ParameterName.PAYMENTMETHOD, "1" );
        map.put( ParameterName.PAYMENTCHECK, "NULL" );
        map.put( ParameterName.REPORTED, "NULL" );
        map.put( ParameterName.CREDITNUMBER, "NULL" );
        map.put( ParameterName.INFORMED, "1" );

        map.put( ParameterName.CLIENTE_TIPO, "" );
        map.put( ParameterName.IDCLIENTE, "NULL" );
        map.put( ParameterName.PIN, "NULL" );
        map.put( ParameterName.SALUTE, "NULL" );
        map.put( ParameterName.FIRST_NAME, "" );
        map.put( ParameterName.MIDDLE_NAME, "" );
        map.put( ParameterName.LAST_NAME, "PINEDA" );
        map.put( ParameterName.LNAME, "GARIBAY" );
        map.put( ParameterName.BORNDATE, "1975-10-31" );
        map.put( ParameterName.TAXIDINI, "NULL" );
        map.put( ParameterName.TAXIDHOMO, "NULL" );
        map.put( ParameterName.TAXIDINT, "NULL" );
        map.put( ParameterName.STREET, "OHIO STREET" );
        map.put( ParameterName.NUMBER, "686" );
        map.put( ParameterName.INTERIOR, "NULL" );
        map.put( ParameterName.COLONY, "RESIDENICA CENTRAL" );
        map.put( ParameterName.ZIPCODE, "NULL" );
        map.put( ParameterName.IDPAIS, "2" );
        map.put( ParameterName.IDESTADO, "16" );
        map.put( ParameterName.POBLACION, "CHICAGO" );
        map.put( ParameterName.EMAIL, "NULL" );
        map.put( ParameterName.TELEPHONE1, "3461254" );
        map.put( ParameterName.QUESTION1, "NULL" );
        map.put( ParameterName.ANSWER1, "NULL" );
        map.put( ParameterName.DATECREATED, "NULL" );
        map.put( ParameterName.DATEMODIFIED, "NULL" );
        map.put( ParameterName.STATUS, "NULL" );
        map.put( ParameterName.IDOCUPACION, "0" );
         
        map.put( ParameterName.VISA, "1234567890" );
        map.put( ParameterName.PASAPORTE, "NULL" );
        map.put( ParameterName.GREENCARD, "NULL" );
        map.put( ParameterName.SS, "NULL" );
        map.put( ParameterName.MATRICULACONSULAR, "NULL" );
        map.put( ParameterName.IFE, "NULL" );
        map.put( ParameterName.LICENCIA, "NULL" );

        map.put( ParameterName.CLIENTEB_TIPO, "" );
        map.put( ParameterName.IDCLIENTEB, "NULL" );
        map.put( ParameterName.SALUTEB, "NULL" );
        map.put( ParameterName.FNAMEB, "ANGELES" );
        map.put( ParameterName.MNAMEB, "NULL" );
        map.put( ParameterName.SNAMEB, "HERNANDEZ" );
        map.put( ParameterName.LNAMEB, "ALVEAR" );
        map.put( ParameterName.BORNDATEB, "1979-02-24" );
        map.put( ParameterName.TAXIDINIB, "NULL" );
        map.put( ParameterName.TAXIDDATEB, "NULL" );
        map.put( ParameterName.TAXIDHOMOB, "NULL" );
        map.put( ParameterName.TAXIDINTB, "NULL" );
        map.put( ParameterName.STREETB, "SALVADOR A ROMERO" );
        map.put( ParameterName.NUMBERB, "23" );
        map.put( ParameterName.INTERIORB, "NULL" );
        map.put( ParameterName.COLONYB, "CONSTITUYENTE" );
        map.put( ParameterName.ZIPCODEB, "1234" );
        map.put( ParameterName.IDPAISB, "1" );
        map.put( ParameterName.IDESTADOB, "16" );
        map.put( ParameterName.POBLACIONB, "MARAVATIO" );
        map.put( ParameterName.EMAILB, "NULL" );
        map.put( ParameterName.TELEPHONE1B, "4474780000" );
        map.put( ParameterName.QUESTION1B, "NULL" );
        map.put( ParameterName.ANSWER1B, "NULL" );
        map.put( ParameterName.DATECREATEDB, "NULL" );
        map.put( ParameterName.DATEMODIFIEDB, "NULL" );
        map.put( ParameterName.STATUSB, "NULL" );
        map.put( ParameterName.IDOCUPACIONB, "0" );
        
        map.put( ParameterName.VISAB, "NULL" );
        map.put( ParameterName.PASAPORTEB, "NULL" );
        map.put( ParameterName.GREENCARDB, "3939393" );
        map.put( ParameterName.SSB, "NULL" );
        map.put( ParameterName.MATRICULACONSULARB, "NULL" );
        map.put( ParameterName.IFEB, "NULL" );
        map.put( ParameterName.LICENCIAB, "NULL" );

        map.put( ParameterName.DATOS, "EX" );
        map.put( ParameterName.CORRESPONSALES, "" );

        return map;

    }
}
