
package com.smartbt.vtsuite.utils;

import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author Roberto Rodriguez
 */
public class MapUtil {

      public static String getStringValueFromMap(Map map, ParameterName requestParameterName, boolean required)throws Exception{
        if(map.containsKey(requestParameterName)){
            if(map.get(requestParameterName) == null)
                return "";
            return map.get(requestParameterName).toString();
        }else{
            if(required){
                throw new Exception(requestParameterName.toString() + " required for OrderExpress transaction");
            }else{
                return "NULL";
            }
        }
    }

      public static int getIntegerValueFromMap(Map map, ParameterName requestParameterName, boolean required)throws Exception{
       int resp = 0;
        if(map.containsKey(requestParameterName)){
            if(map.get(requestParameterName) != null){
                try{
                    resp = (Integer)map.get(requestParameterName);
                }catch(ClassCastException e){
                    throw new Exception("Integer value expected from property: " + requestParameterName.toString());
                }
            }else{
                if(required)
                throw new Exception(requestParameterName.toString() + " can't be null.");
            }
        }else{
            if(required)
                throw new Exception(requestParameterName.toString() + " required for transaction Contrataciones.");
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
