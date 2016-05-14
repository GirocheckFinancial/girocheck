package com.smartbt.vtsuite.entryPoint;

/*1

 ** File: IStreamClient.java
 **
 ** Date Created: April 2013
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
import com.smartbt.vtsuite.MDB.IStreamMDB;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.enums.ParameterName;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Edward Beckett :: <Edward.Beckett@smartbt.com>
 */
public class IStreamClient {

//    private static IStreamClient client;
//    private IStream_Service service = new IStream_Service();
//    private IStream port = service.getIStreamPort();
//    private CheckAuthSubmit checkAuthSubmit = null;
//    private CashAuthSubmit cashAuthSubmit = null;

    
    public static void main( String[] args ) {
        
            IStreamMessage message = new IStreamMessage();
            DirexTransactionRequest request = new DirexTransactionRequest();
            
            System.out.println("SELECT THE METHOD YOU WANT TO TEST");
            System.out.println("  1 - checkAuth");                   //com.sun.xml.ws.client.ClientTransportException: The server sent HTTP status code 302: Found
            System.out.println("  2 - checkAuthLocationConfig");     //com.sun.xml.ws.client.ClientTransportException: The server sent HTTP status code 302: Found
            System.out.println("  3 - checkAuthSubmit");             //com.sun.xml.ws.client.ClientTransportException: The server sent HTTP status code 302: Found
            System.out.println("  4 - enhancedCheckAuthPoll");       //com.sun.xml.ws.client.ClientTransportException: The server sent HTTP status code 302: Found
            
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
                default:System.out.println("SELECT OPERATION BETWEEN 1-4");  
                        main(new String[]{});
            }
            
            request.setTransactionData(map);
            request.setCorrelation("11111");
            message.setObject(request);
            
                
            IStreamMDB a = new IStreamMDB();
            long time = Calendar.getInstance().getTimeInMillis();
            a.onMessage(message);
            
            long time2 = Calendar.getInstance().getTimeInMillis();

            long dif = time2 - time;
            
            System.out.println("---------- ANSWER TIME: "+ (dif) + " milliseconds.  -------------" );
            System.out.println();
            System.out.println();
            System.out.println();
            
            main(new String[]{});
           // System.out.println("Hello world");
        

    }
    

 
 
  
  public static Map checkAuth() {
            Map map = new HashMap();

           map.put(TransactionType.TRANSACTION_TYPE , TransactionType.ISTREAM_CHECK_AUTH);

          map.put(ParameterName.ADDRESS_CORRECT, "y");
          map.put(ParameterName.AMMOUNT, "12.00");
          map.put(ParameterName.BACK_TIFF, new byte[]{});
          map.put(ParameterName.BATCH_NAME, "12");
          map.put(ParameterName.CARD_NUMBER, "5448353882100698");
          map.put(ParameterName.CHECK_BACK, new byte[]{});
          map.put(ParameterName.CHECK_FRONT, new byte[]{});
          map.put(ParameterName.CRC, "12");
          map.put(ParameterName.DATA, new ArrayList<Object>());
          map.put(ParameterName.EMAIL_ADDRESS, new ArrayList<String>());
          map.put(ParameterName.ENTITY_ID, "AN6omas");
          map.put(ParameterName.FRONT_TIFF, new byte[]{});
          map.put(ParameterName.IDBACK, new byte[]{});
          map.put(ParameterName.IDFRONT, new byte[]{});
          map.put(ParameterName.IQARAW_DATA, "12");
          map.put(ParameterName.MICR, "1324234234");
          map.put(ParameterName.PASSWORD, "pau194");
          map.put(ParameterName.PHONE, "1234567890");
          map.put(ParameterName.SCAN_TIME, null);
          map.put(ParameterName.SCANNER_MANUFACTURER, "alphaNumericValue2");
          map.put(ParameterName.SEND_TIME, null);
          map.put(ParameterName.USER, "sbttest");
          map.put(ParameterName.SERIAL, "alphaNumericValue3");
          map.put(ParameterName.SSN, "123456789");
             return map;
          }
  
  
   private static Map checkAuthLocationConfig(){
        Map map = new HashMap();
        map.put(TransactionType.TRANSACTION_TYPE , TransactionType.ISTREAM_CHECK_AUTH_LOCATION_CONFIG);
        map.put(ParameterName.ENTITY_ID ,254724);
        map.put(ParameterName.PASSWORD , "pau194");
        map.put(ParameterName.USER , "sbttest");
        return map;

}
   
     public static Map checkAuthSubmit() {
            Map map = new HashMap();

           map.put(TransactionType.TRANSACTION_TYPE , TransactionType.ISTREAM_CASH_AUTH_SUBMIT);

          map.put(ParameterName.ACTION, "12");
          map.put(ParameterName.CHECK_ID, "12");
          map.put(ParameterName.PASSWORD, "pau194");
          map.put(ParameterName.USER, "sbttest");
             return map;
          }
   
     public static Map enhancedCheckAuthPoll() {
            Map map = new HashMap();

           map.put(TransactionType.TRANSACTION_TYPE , TransactionType.ISTREAM_ENHANCED_CHECK_AUTH_POLL);

          map.put(ParameterName.CHECK_ID, new ArrayList<Object>());
          map.put(ParameterName.ENTITY_ID, "254724");
          map.put(ParameterName.PASSWORD, "pau194");
          map.put(ParameterName.USER, "sbttest");
             return map;
          }
   
     public static Map checkAuthPoll() {
            Map map = new HashMap();

           map.put(TransactionType.TRANSACTION_TYPE , TransactionType.ISTREAM_CHECK_AUTH_POLL);

          map.put(ParameterName.CHECK_ID, new ArrayList<Object>());
          map.put(ParameterName.ENTITY_ID, "254724");
          map.put(ParameterName.PASSWORD, "pau194");
          map.put(ParameterName.USER, "sbttest");
             return map;
          }
     
     /*
  checkAuthLocationConfig,
                
     ***checkAuth,
                
     enhancedCheckAuthPoll,
                
     ***checkAuthSubmit,
     
             
              private static Map cashAuthMap(){
      Map map = new HashMap();
            map.put(IStreamRequestParameterName.TRANSACTION_TYPE , IStreamTransactionType.cashAuth);
            
            map.put(IStreamRequestParameterName.addressCorrect , "12345");
            map.put(IStreamRequestParameterName.amount , "12345");
            map.put(IStreamRequestParameterName.batchName , "12345");
            map.put(IStreamRequestParameterName.cardNumber , "12345");
            map.put(IStreamRequestParameterName.emailAddress ,new ArrayList<String>());
            map.put(IStreamRequestParameterName.entityNumber , "12345");
            map.put(IStreamRequestParameterName.frontTiff , "12345");
            map.put(IStreamRequestParameterName.idBack , "12345");
            map.put(IStreamRequestParameterName.idFront , "12345");
            map.put(IStreamRequestParameterName.phone , "12345");
            map.put(IStreamRequestParameterName.scannerManufacturer , "12345");
            map.put(IStreamRequestParameterName.serial , "12345");
            map.put(IStreamRequestParameterName.ssn , "12345");
            map.put(IStreamRequestParameterName.sendUser , "12345");
            map.put(IStreamRequestParameterName.password , "12345");
            return map;
 }
              */
}