
package com.smartbt.vtsuite.boundary.util;

/**
 *
 * @author Roberto Rodriguez
 */
public class CodeGenerator {

        public static void main(String args[]){
            
            String entrada = " String datos = map.get( ParameterName.DATOS ).toString();\n" +
"        String corresponsales = map.get( ParameterName.CORRESPONSALES ).toString();\n" +
"        String rutaEjecutar = map.get( ParameterName.RUTA_EJECUTAR ).toString();";
            
            String entArray[] = entrada.split("\n");
            
            String parameterName = "";
            String enumName = "";
            
            for (int i = 0; i < entArray.length; i++) {
                String a = entArray[i];
               
                String parameter = a.substring(a.indexOf("map.get"), a.indexOf(");"));
                System.out.println("parameter: "+ parameter);
                String pName = parameter.substring(parameter.indexOf("ParameterName.") + 14, parameter.indexOf(").toString()")).trim();
                System.out.println("pName: "+ pName);
                String ent = "MapUtil.getStringValueFromMap(map, ParameterName."+ pName +", true)";
                System.out.println("ent: "+ ent);
                System.out.println(a.replace(parameter, ent));
            }
   
        }
    
}
