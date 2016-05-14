
package com.smartbt.vtsuite.util;

import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.datatype.XMLGregorianCalendar;

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
                throw new Exception(requestParameterName.toString() + " required for transaction: "+ getTType( map));
            }else{
                return "";
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
                throw new Exception(requestParameterName.toString() + " required for transaction: "+ getTType( map));
         }
        return resp;
    }
    
  public static byte[] getByteArrayFromMap(Map map, ParameterName requestParameterName){
      if(map.containsKey(requestParameterName) && map.get(requestParameterName) != null &&  map.get(requestParameterName) instanceof byte[])
         return (byte[])map.get(requestParameterName);
       return new byte[0];
        
  }
    
  public static List<Object> getObjectListFromMap(Map map, ParameterName requestParameterName){
      if(map.containsKey(requestParameterName) && map.get(requestParameterName) != null && (map.get(requestParameterName) instanceof List))
         return (List<Object>)map.get(requestParameterName);
            return new ArrayList<Object>();
   }
    
  public static XMLGregorianCalendar getGregorianCalendarFromMap(Map map, ParameterName requestParameterName){
      if(map.containsKey(requestParameterName) && map.get(requestParameterName) != null && (map.get(requestParameterName) instanceof XMLGregorianCalendar))
         return (XMLGregorianCalendar)map.get(requestParameterName);
            return null;
   }
  
  private static TransactionType getTType(Map map){
      TransactionType transactionType = TransactionType.TRANSACTION_TYPE;
      
      if(map.containsKey(TransactionType.TRANSACTION_TYPE))
          return (TransactionType)map.get(TransactionType.TRANSACTION_TYPE);
      
      return transactionType;
  }
    
}
