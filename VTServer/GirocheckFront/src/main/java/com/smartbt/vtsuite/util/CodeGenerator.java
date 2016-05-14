 
package com.smartbt.vtsuite.util;

import java.util.Date;

/**
 *
 * @author Roberto
 */


public class CodeGenerator {
     public static void main(String[] args){
        System.out.println(new Date());
        
//        String methodName = "checkAuthSubmit";
//        
//        String entrada = "   idType\n" +
//"  id\n" +
//"telephone\n" +
//"telephoneAreaCode\n" +
//"cellphone\n" +
//"cellphoneAreaCode\n" +
//"faxphone\n" +
//"faxAreaCode\n" +
//"workphone\n" +
//"workphoneAreaCode\n" +
//"email\n" +
//"address \n" +
//"city \n" +
//"state\n" +
//"idState\n" +
//"zipCode\n" +
//"country\n" +
//"idCountry\n" +
//"personTitle\n" +
//"firstName\n" +
//"middleName\n" +
//"maidenName\n" +
//"lastName\n" +
//"lastNameM\n" +
//"bornDate";
//        
//       
//        entrada = entrada.replace("\"", "").replace(",", "");
//        
//        
//        String[]  items = entrada.split('\n' + "");
//        
//       generate(items);
    }
     
     
     public static void generate(String[] items) {
        
         for (String string : items) {
            string = string.trim();
             System.out.println("<xs:element name=\""+string+"\" type=\"xs:string\" minOccurs=\"0\"/>");
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
