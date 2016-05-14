 
package com.smartbt.vtsuite.entryPoint;

/**
 *
 * @author Roberto
 */


public class CodeGenerator {
     public static void main(String[] args){
        System.out.println("Running...");
        
        String methodName = "checkAuthSubmit";
        
        String entrada = "    IdType\n" +
"State \n" +
"DateOfBirth\n" +
"Address \n" +
"City \n" +
"IdState\n" +
"TelephoneAreaCode \n" +
"Cellphone \n" +
"ZipCode \n" +
"MiddleName\n" +
"Telephone \n" +
"FaxAreaCode\n" +
"LastName \n" +
"WorkphoneAreaCode\n" +
"Country\n" +
"CellphoneAreaCode \n" +
"PersonTitle \n" +
"Email \n" +
"CurrentAddress \n" +
"RBService \n" +
"FirstName \n" +
"Id \n" +
"MaidenName\n" +
"Workphone \n" +
"IdCountry \n" +
"Faxphone \n" +
"Card \n" +
"IdExpiration ";
        
        String str[] = entrada.split("\n");
       generateConstantAttributes(str);
        
        
//        String salida = "\"transactionNumber\",\n" +
//"    \"date\",\n" +
//"    \"amount";
//        
//        
//        salida = salida.replace("\"", "").replace(",", "");
//        
//        entrada = entrada.replace("protected java.lang.String", "").replace(";","");
//        String[]  items = entrada.split('\n' + "");
//        
//         System.out.println("*********** clientMethod  ******************");
//                clientMethod(items, methodName);
//         
//         System.out.println("");
//         System.out.println("");
//         System.out.println("");
//        
//         System.out.println("*********** businessLogicMethod  ******************");
//                businessLogicMethod(items, methodName);
//         
//         System.out.println("");
//         System.out.println("");
//         System.out.println("");
//         System.out.println("*********** responseToMapMethod  ******************");
//         items = (salida.length() == 0)? new String[0] : salida.split('\n' + "");
//                responseToMapMethod(items);
    }
     
     //CLASE PARA EL CLIENTE 
     public static void clientMethod(String[] array, String methodName){
         System.out.println("        public static Map "+methodName+"() {");
         System.out.println("            Map map = new HashMap();");
         System.out.println("");
         System.out.println("           map.put(TecnicardRequestParameterName.TRANSACTION_TYPE , TecnicardTransactionType."+methodName+");");
         System.out.println("");
         
         String parameters = "";
          for (int i = 0; i < array.length; i++) {
              String str = array[i].trim();
              if(str.equals("pCardNumber")){
                     System.out.println("          map.put(TecnicardRequestParameterName." + str+ ", \"5448353882100474\");");
              }else{
                  if(str.endsWith("mount")){
                     System.out.println("          map.put(TecnicardRequestParameterName." + str+ ", \"12.00\");");
                   }else{
                     System.out.println("          map.put(TecnicardRequestParameterName." + str+ ", \"12\");");
                  }
              }
               parameters += str + ((i < array.length - 1) ? ", "+ '\n' : "");
         }
          System.out.println("             return map;");
          System.out.println("          }");
          
           System.out.println("");
         System.out.println("");
          System.out.println("......... PUT THIS IN THE TecnicardRequestParameterName ........");
          System.out.println(parameters);
     }

     
     // Clase para el response
     public static  void responseToMapMethod(String[] array){
          System.out.println("          @Override");
          System.out.println("   public Map toMap() {");
          System.out.println("        Map map = super.getMap();");
        
          String parameters = "";
          for (int i = 0; i < array.length; i++) {
              String str = array[i].trim();
              System.out.println("      map.put(TecnicardResponseParameterName." + str+ ", " + str + ");");
              parameters += str + ((i < array.length - 1) ? ", "+ '\n' : "");
         }
        
          System.out.println("    return map;");
          System.out.println("    }");
          
         System.out.println("");
         System.out.println("");
          System.out.println("......... PUT THIS IN THE TecnicardResponseParameterName ........");
          System.out.println(parameters);
          
          
     }
     
     //METODO PARA EL BUSINESS LOGIC
     public static  void businessLogicMethod(String[] array, String methodName){
      System.out.println("  public IMap "+methodName+"(Map map){");
      String parameters = "";
      for (int i = 0; i < array.length; i++) {
              String str = array[i].trim();
              System.out.println("       String "+str+" = map.get(TecnicardRequestParameterName."+ str +").toString();");
              parameters += str + ((i < array.length - 1) ? ", " : ""); 
         }
        
        System.out.println("    return port."+ methodName+"("+ parameters+");");     
        System.out.println(" }");     
}
     
   public static void generateConstantAttributes(String[] str) {
        
       for (String string : str) {
           string = string.trim();
           System.out.println(string);
         //  System.out.println("public static final String " + string + " = \""+ string +"\";");
       }
    }
     
}
