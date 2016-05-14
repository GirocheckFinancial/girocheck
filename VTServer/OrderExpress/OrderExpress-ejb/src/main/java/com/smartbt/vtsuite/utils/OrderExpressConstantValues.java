
package com.smartbt.vtsuite.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Roberto Rodriguez
 */
public class OrderExpressConstantValues {
    
     //DEFAULT VALUES FOR THE ORDER_EXPRESS CONTRATACIONES
      public static final String ID_MERCHANT = System.getProperty("PARAM_OE_ID_MERCHANT");
      public static final String LOGIN = "'OEINC'";
      public static final String PASSWORD = "'OE&INC'";
      public static final String REQUEST_TYPE = "T";
      public static final String ID_POS = "1376";
      public static final String ID_SERVICE = "1";
      public static final String ID_DESTINY = "7574";
      public static final String ID_TELLER = "4280";
      public static final String PAYMENTMETHOD = "1";
      public static final String INFORMED = "1";
      
      //CONSTANT DATE
       public static final DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS");
       public static final Date now = new Date();
       public static final String DATE_TIME = fmt.format( now );
      
     
      //CORRESPONSALES METHOD SIGNATURE DEFAULT PARAMS
      public static final String DATOS = "EX";
      public static final String CORRESPONSALES = "OEINC";
      public static final String RUTAEJECUTAR = ""; 
      
      //THESES VALUES ARE ALWAYS THE SAME
      public static final String DEPOSIT = "0.00";
      public static final String SERVICE = "0.00";
      public static final String TOTAL = "0.00";
      public static final String RATE = "0.00";
      public static final String RELIEVE = "0.00";
      public static final String NUMBER = "00.00";
      public static final String DISCOUNT = "NULL";
      public static final String TAX = "NULL";
      

      
      public static final String ID_OCUPACION = "117";
      
      
      //THIS VALUE IS AN AUTHOINCREMENTAL WE NEED TTO HAVE IN OUR DATA_BASE
      public static final String BANK_AUTHO = "1";
      
      
}
