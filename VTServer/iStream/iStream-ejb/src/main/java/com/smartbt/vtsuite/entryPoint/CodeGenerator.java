 
package com.smartbt.vtsuite.entryPoint;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 *
 * @author Roberto
 */


public class CodeGenerator {
     public static void main(String[] args){
        System.out.println("Running...");
        
        String methodName = "checkAuthSubmit";
        
        String entrada = " \"action\",\n" +
"    \"checkId\",\n" +
"    \"password\",\n" +
"    \"user\"";
        
        String salida = "";
        
        
        salida = salida.replace("\"", "").replace(",", "");
        entrada = entrada.replace("\"", "").replace(",", "");
        
        
        String[]  items = entrada.split('\n' + "");
        
         System.out.println("*********** clientMethod  ******************");
                clientMethod(items, methodName);
         
         System.out.println("");
         System.out.println("");
         System.out.println("");
         System.out.println("*********** responseToMapMethod  ******************");
         items = (salida.length() == 0)? new String[0] : salida.split('\n' + "");
                responseToMapMethod(items);
         
                System.out.println("");
         System.out.println("");
         System.out.println("");
         System.out.println("*********** buildRequestMethod  ******************");
         items = (entrada.length() == 0)? new String[0] : entrada.split('\n' + "");
                buildRequestMethod( items,  methodName);
    }
     
     //CLASE PARA EL CLIENTE 
     public static void clientMethod(String[] array, String methodName){
         System.out.println("        public static Map "+methodName+"() {");
         System.out.println("            Map map = new HashMap();");
         System.out.println("");
         System.out.println("           map.put(IStreamRequestParameterName.TRANSACTION_TYPE , IStreamTransactionType."+methodName+");");
         System.out.println("");
         
         String parameters = "";
          for (int i = 0; i < array.length; i++) {
              String str = array[i].trim();
              if(str.equals("pCardNumber")){
                     System.out.println("          map.put(IStreamRequestParameterName." + str+ ", \"5448353882100474\");");
              }else{
                  if(str.endsWith("mount")){
                     System.out.println("          map.put(IStreamRequestParameterName." + str+ ", \"12.00\");");
                   }else{
                     System.out.println("          map.put(IStreamRequestParameterName." + str+ ", \"12\");");
                  }
              }
               parameters += str + ((i < array.length - 1) ? ", "+ '\n' : "");
         }
          System.out.println("             return map;");
          System.out.println("          }");
          
           System.out.println("");
         System.out.println("");
          System.out.println("......... PUT THIS IN THE IStreamRequestParameterName ........");
          System.out.println(parameters);
     }
     
     public static void buildRequestMethod(String[] array, String methodName){
         String returnType = capitalize(methodName);
         
         System.out.println("    @Override ");
         System.out.println("    public "+returnType+"Request build(Map map){ ");
         
         for (int i = 0; i < array.length; i++) {
             String str = array[i].trim();
             if(str.equals("entityId")){
                System.out.println("      entityId = (Integer)map.get(IStreamRequestParameterName.entityId);");
            }else{
                System.out.println("      " + str +"=  map.get(IStreamRequestParameterName."+str+").toString();");
             }
     }
         System.out.println("   return this; ");
         
        
         System.out.println("  } ");
     }

     
     // Clase para el response
     public static  void responseToMapMethod(String[] array){
          System.out.println("   @Override");
          System.out.println("   public Map toMap() {");
          System.out.println("      Map map = new HashMap();");
        
          String parameters = "";
          for (int i = 0; i < array.length; i++) {
              String str = array[i].trim();
              System.out.println("      map.put(IStreamResponseParameterName." + str+ ", " + str + ");");
              parameters += str + ((i < array.length - 1) ? ", "+ '\n' : "");
         }
        
          System.out.println("    return map;");
          System.out.println("    }");
          
         System.out.println("");
         System.out.println("");
          System.out.println("......... PUT THIS IN THE IStreamResponseParameterName ........");
          System.out.println(parameters);
          
     }
     
     public static void reflectThis(String ... that) {
         for (String string : that) {
             ToStringBuilder.reflectionToString(that);
             
         }
     }
     
//     //METODO PARA EL BUSINESS LOGIC
//     public static  void businessLogicMethod(String[] array, String methodName){
//      System.out.println("  public IMap "+methodName+"(Map map){");
//      String parameters = "";
//      for (int i = 0; i < array.length; i++) {
//              String str = array[i].trim();
//              System.out.println("       String "+str+" = map.get(IStreamRequestParameterName."+ str +").toString();");
//              parameters += str + ((i < array.length - 1) ? ", " : ""); 
//         }
//        
//        System.out.println("    return port."+ methodName+"("+ parameters+");");     
//        System.out.println(" }");     
//}
     
     
     private static String capitalize(String line){
        return Character.toUpperCase(line.charAt(0)) + line.substring(1);
      }
}
