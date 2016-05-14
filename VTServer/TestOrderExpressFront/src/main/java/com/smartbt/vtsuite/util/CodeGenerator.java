 
package com.smartbt.vtsuite.util;

import com.smartbt.girocheck.servercommon.enums.ParameterName;
import java.util.Map;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 *
 * @author Roberto
 */


public class CodeGenerator {
     public static void main(String[] args){
        System.out.println("Running...");
        
        String methodName = "checkAuthSubmit";
        
        String entrada = "\"requestType\",\n" +
"    \"terminalId\",\n" +
"    \"login\",\n" +
"    \"password\",\n" +
"    \"idPos\",\n" +
"    \"idService\",\n" +
"    \"dateTime\",\n" +
"    \"deposit\",\n" +
"    \"tax\",\n" +
"    \"total\",\n" +
"    \"rate\",\n" +
"    \"relieve\",\n" +
"    \"idDestiny\",\n" +
"    \"bankAutho\",\n" +
"    \"firstName\",\n" +
"    \"lastName\",\n" +
"    \"bornDate\",\n" +
"    \"street\",\n" +
"    \"number\",\n" +
"    \"zipCode\",\n" +
"    \"idPais\",\n" +
"    \"idEstado\",\n" +
"    \"poblacion\",\n" +
"    \"telephone\",\n" +
"    \"idOcupation\",\n" +
"    \"idType\",\n" +
"    \"id\"";
        
       
        entrada = entrada.replace("\"", "").replace(",", "");
        
        
        String[]  items = entrada.split('\n' + "");
        
       generate(items);
    }
     
     
     public static void generate(String[] items) {
        
         for (String string : items) {
            string = string.trim();
             System.out.println("private String "+string+" ;");
         }
    }
     
     public static void generate2(String[] items) {
        
         for (String string : items) {
            string = string.trim();
             System.out.println("\""+ string +"\",");
         }
    }
     
     public static void generate3(String[] items) {
        
         for (String string : items) {
            string = string.trim();
             System.out.println("protected String "+ string +";");
         }
    }
     
    
     private static String capitalize(String line){
        return Character.toUpperCase(line.charAt(0)) + line.substring(1);
      }
}
