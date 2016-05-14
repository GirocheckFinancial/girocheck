package com.smartbt.vtsuite.entryPoint;

/*
 ** File: TecnicardClient.java
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
import com.smartbt.vtsuite.MDB.TecnicardMDB;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

/**1
 *
 * @author Edward Beckett :: <Edward.Beckett@smartbt.com>
 */
public class TecnicardClient {
    
    //static String[] SUFFIX_LIST = {"474","482","490","508","516","524","532","540","557","565"};
    static String[] SUFFIX_LIST = {"672","680","698","706","714","722","730","748","755","763"};

    static int INDEX_SUFFIX = 0;
    
    public static String getSuffix(){
        
        return SUFFIX_LIST[INDEX_SUFFIX];
    }
    
    public static void changeSuffix(){
        
        INDEX_SUFFIX = (INDEX_SUFFIX+1)%10;
        
    }
    
  public static void main( String[] args ) {
        
            TecnicardMessage message = new TecnicardMessage();
            DirexTransactionRequest request = new DirexTransactionRequest();
            
          //http://74.255.192.104/Galileo.TCMS.ExtSRV.Web/iStreamSrvHost.asmx?WSDL
            System.out.println("SELECT THE METHOD YOU WANT TO TEST");
            System.out.println("  1 - wmCardValidation");      
            System.out.println("  2 - wmCardActivation");     
            System.out.println("  3 - wmCardPersonalization");     
            System.out.println("  4 - wmCardLoad");
            System.out.println("  5 - wmBalanceInquiry");
            System.out.println("  6 - wmCardToBank");
            System.out.println("  7 - wmCardHolderValidation");
            System.out.println("  8 - wmEcho");
            System.out.println("  9 - wmLastTransactions");
            System.out.println("  10 - wmCashToCard");
            System.out.println();
            System.out.println();
            
            System.out.println("  0 - change Suffix");
            Scanner cin = new Scanner(System.in);
            
            int option = cin.nextInt();
            
            switch(option){
                case 1: 
                    request.setTransactionData(wmCardValidation());
                    break;
                case 2: 
                    request.setTransactionData(wmCardActivation());
                    break;
                case 3: 
                    request.setTransactionData(wmCardPersonalization());
                    break;
                case 4: 
                    request.setTransactionData(wmCardLoad());
                    break;
                case 5: 
                    request.setTransactionData(wmBalanceInquiry());
                    break;
                case 6: 
                    request.setTransactionData(wmCardToBank());
                    break;
                case 7: 
                    request.setTransactionData(wmCardHolderValidation());
                    break;
                case 8: 
                    request.setTransactionData(wmEcho());
                    break;
                case 9: 
                    request.setTransactionData(wmLastTransactions());
                    break;
                case 10: 
                    request.setTransactionData(wmCashToCard());
                    break;
                case 0: 
                    changeSuffix();
                    System.out.println("Suffix was change to "+ getSuffix());  
                    main(new String[]{});
                    break;
                default:System.out.println("SELECT OPERATION BETWEEN 1-4");  
                        main(new String[]{});     
  }
            
            System.out.println();
            System.out.println();
            System.out.println("YOU SELECTED: "+ request.getTransactionData().get(TransactionType.TRANSACTION_TYPE));
           
            request.setCorrelation("11111");
            message.setObject(request);
            
            
            TecnicardMDB a = new TecnicardMDB();
            
            long time = Calendar.getInstance().getTimeInMillis();
            a.onMessage(message);
            
            long time2 = Calendar.getInstance().getTimeInMillis();

            long dif = time2 - time;
            
            System.out.println("---------- ANSWER TIME: "+ (dif) + " milliseconds.  -------------" );
            System.out.println();
            System.out.println();
            System.out.println();
            
        main(new String[]{});

    }
    
    public static Map wmCardValidation() {
            Map map = new HashMap();

           map.put(TransactionType.TRANSACTION_TYPE , TransactionType.TECNICARD_CARD_VALIDATION);

          map.put(ParameterName.AMMOUNT, "1.50");
          map.put(ParameterName.CARD_NUMBER, "5448353882100" + getSuffix());
          map.put(ParameterName.REQUEST_ID, "12");
             return map;
          }
  
 private static Map wmCardActivation(){
      Map map = new HashMap();
            map.put(TransactionType.TRANSACTION_TYPE , TransactionType.TECNICARD_CARD_ACTIVATION);
            
            map.put(ParameterName.CARD_NUMBER , "5448353882100" + getSuffix());
            map.put(ParameterName.REQUEST_ID , "2");
            map.put(ParameterName.ENTITY_ID , "1850004860002");
           
            return map;
 }  
 private static Map wmCardPersonalization(){
      Map map = new HashMap();
            map.put(TransactionType.TRANSACTION_TYPE , TransactionType.TECNICARD_CARD_PERSONALIZATION);
            
            map.put(ParameterName.IDTYPE , "1");
            map.put(ParameterName.STATE , "FL");
            map.put(ParameterName.DATEOF_BIRTH , "19880103");
            map.put(ParameterName.ADDRESS , "9592 Palmetto Club Dr");
            map.put(ParameterName.CITY , "MIami");
            map.put(ParameterName.IDSTATE , "10");
            map.put(ParameterName.TELEPHONE_AREA_CODE , "305");
            map.put(ParameterName.CELL_PHONE , "4579007");
            map.put(ParameterName.ZIPCODE , "33157");
            map.put(ParameterName.MIDDLE_NAME , "M");
            map.put(ParameterName.TELEPHONE , "4579008");
            map.put(ParameterName.FAX_AREA_CODE , "305");
            map.put(ParameterName.LAST_NAME , "Rodriguez");
            map.put(ParameterName.REQUEST_ID , "1");
            map.put(ParameterName.WORKPHONE_AREA_CODE , "305");
            map.put(ParameterName.COUNTRY , "EUA");
            map.put(ParameterName.CELL_PHONE_AREA , "305");
            map.put(ParameterName.PERSON_TITLE , "Mr.");
            map.put(ParameterName.EMAIL , "roberto.rodriguez@smartbt.com");
            map.put(ParameterName.CURRENT_ADDRESS , "Y");
            map.put(ParameterName.RB_SERVICE , "1");
            map.put(ParameterName.FIRST_NAME , "Roberto");
            map.put(ParameterName.ID , "757-37-4530");
            map.put(ParameterName.MAIDEN_NAME , "M");
            map.put(ParameterName.WORK_PHONE , "4579009");
            map.put(ParameterName.IDCOUNTRY , "840");
            map.put(ParameterName.FAX_PHONE , "4579000");
            map.put(ParameterName.CARD , "5448353882100" + getSuffix());
            map.put(ParameterName.IDEXPIRATION , "");
           
            return map;
 }  

    public static Map wmCardLoad(){
        Map map = new HashMap();
            map.put(TransactionType.TRANSACTION_TYPE , TransactionType.TECNICARD_CARD_LOAD);
            
            map.put(ParameterName.AMMOUNT , "10.00");
            map.put(ParameterName.CHECK_FEE , "2.00");
            map.put(ParameterName.CARD_NUMBER , "5448353882100" + getSuffix());
            map.put(ParameterName.ENTITY_ID , "1850004860002");
            map.put(ParameterName.REQUEST_ID , "12345");
        return map;
    }
    
      public static Map wmBalanceInquiry() {
                   Map map = new HashMap();

                  map.put(TransactionType.TRANSACTION_TYPE , TransactionType.TECNICARD_BALANCE_INQUIRY);

                        map.put(ParameterName.CARD_NUMBER, "5448353882100" + getSuffix());
                        map.put(ParameterName.REQUEST_ID, "12");
             return map;
          }
      
      
      public static Map wmCardToBank() {
            Map map = new HashMap();

           map.put(TransactionType.TRANSACTION_TYPE , TransactionType.TECNICARD_CARD_TO_BANK);

          map.put(ParameterName.AMMOUNT, "10.00");
          map.put(ParameterName.ROUTING_BANK_NUMBER, "12");
          map.put(ParameterName.CARD_NUMBER, "5448353882100" + getSuffix());
          map.put(ParameterName.ACCOUNT_NUMBER, "12");
          map.put(ParameterName.REQUEST_ID, "12");
             return map;
          }
      
    /*
    pAmount: "1.50"
          pCardNumber: "5448353882100" + getSuffix()
          pRequestID: "12"
      
      */
      
      public static Map wmCardHolderValidation() {
            Map map = new HashMap();

           map.put(TransactionType.TRANSACTION_TYPE , TransactionType.TECNICARD_CARD_HOLDER_VALIDATION);

          map.put(ParameterName.ID, "757-37-4530");
          map.put(ParameterName.IDTYPE, "1");
          map.put(ParameterName.CARD_NUMBER, "5448353882100" + getSuffix());
          map.put(ParameterName.REQUEST_ID, "12");
             return map;
          }
      
      public static Map wmEcho() {
            Map map = new HashMap();

           map.put(TransactionType.TRANSACTION_TYPE , TransactionType.TECNICARD_ECHO);

          map.put(ParameterName.REQUEST_ID, "12");
             return map;
          }
      
      public static Map wmLastTransactions() {
            Map map = new HashMap();

           map.put(TransactionType.TRANSACTION_TYPE , TransactionType.TECNICARD_LAST_TRANSACTIONS);

          map.put(ParameterName.TRANSACTION_QUANTITY, "12");
          map.put(ParameterName.START_DATE, "20140202");
          map.put(ParameterName.CARD_NUMBER, "5448353882100" + getSuffix());
          map.put(ParameterName.END_DATE, "20140203");
          map.put(ParameterName.REQUEST_ID, "12");
             return map;
          }
      
       public static Map wmCashToCard() {
            Map map = new HashMap();

           map.put(TransactionType.TRANSACTION_TYPE , TransactionType.TECNICARD_CASH_TO_CARD);

          map.put(ParameterName.AMMOUNT, "12.00");
          map.put(ParameterName.CARD_NUMBER, "5448353882100" + getSuffix());
          map.put(ParameterName.ENTITY_ID, "1850004860002");
          map.put(ParameterName.REQUEST_ID, "12");
             return map;
          }
}
