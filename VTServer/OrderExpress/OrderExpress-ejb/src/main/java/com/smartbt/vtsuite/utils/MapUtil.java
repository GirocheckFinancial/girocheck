package com.smartbt.vtsuite.utils;

import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Roberto Rodriguez
 */
public class MapUtil {

    public static String getStringValueFromMap(Map map, ParameterName requestParameterName, boolean required) throws Exception {
        if (map.containsKey(requestParameterName)) {
            if (map.get(requestParameterName) == null) {
                return "";
            }
            return map.get(requestParameterName).toString();
        } else {
            if (required) {
                throw new Exception(requestParameterName.toString() + " required for OrderExpress transaction");
            } else {
                return "NULL";
            }
        }
    }

    public static void main(String[] args) throws Exception {
       
        System.out.println( round("3.14"));
        Map map = new HashMap();
        
        float f = 3.1234F;
        Double d = Double.parseDouble(f + "");
//        map.put(ParameterName.FEE_AMMOUNT,d);
//       Double fee = (Double) map.get(ParameterName.FEE_AMMOUNT);
        System.out.println(d);
    }

    public static String getDoubleValueFromMap(Map map, ParameterName requestParameterName, boolean required) throws Exception {
        if (map.containsKey(requestParameterName)) {
            if (map.get(requestParameterName) == null) {
                return "";
            }
            String val = map.get(requestParameterName).toString();

            return round(val);

        } else {
            if (required) {
                throw new Exception(requestParameterName.toString() + " required for OrderExpress transaction");
            } else {
                return "NULL";
            }
        }
    }
    
    
    
    public static String round(String original){
        String val = original;
         if (original.contains(".")) {
                int dot = original.indexOf(".");
                if(original.length() > dot + 3){
                   val = original.substring(0, dot) + "." + original.substring(dot + 1, dot + 3);
                   Integer thirdDigit = Integer.parseInt(original.charAt(dot + 3) + "");
                   if(thirdDigit > 5){
                       val = (Double.parseDouble(val) + 0.01) + "";
                   }
                }
            }
         return val;
    }

    public static int getIntegerValueFromMap(Map map, ParameterName requestParameterName, boolean required) throws Exception {
        int resp = 0;
        if (map.containsKey(requestParameterName)) {
            if (map.get(requestParameterName) != null) {
                try {
                    resp = (Integer) map.get(requestParameterName);
                } catch (ClassCastException e) {
                    throw new Exception("Integer value expected from property: " + requestParameterName.toString());
                }
            } else {
                if (required) {
                    throw new Exception(requestParameterName.toString() + " can't be null.");
                }
            }
        } else {
            if (required) {
                throw new Exception(requestParameterName.toString() + " required for transaction Contrataciones.");
            }
        }
        return resp;
    }

    public static Date getDateValueFromMap(Map map, ParameterName requestParameterName, boolean required) throws Exception {
        if (map.containsKey(requestParameterName)) {
            if (map.get(requestParameterName) == null) {
                return null;
            }
            return (Date) map.get(requestParameterName);
        } else {
            if (required) {
                throw new Exception(requestParameterName.toString() + " required for transaction: " + getTType(map));
            } else {
                return null;
            }
        }
    }

    private static TransactionType getTType(Map map) {
        TransactionType transactionType = TransactionType.TRANSACTION_TYPE;

        if (map.containsKey(TransactionType.TRANSACTION_TYPE)) {
            return (TransactionType) map.get(TransactionType.TRANSACTION_TYPE);
        }

        return transactionType;
    }
}
