/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartbt.vtsuite.util;

import java.util.Arrays;

/**
 *
 * @author Alejo
 */
public class FixUtil {
    
//    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(FixUtil.class);
    
    public static String fixAmmount(String ammount){
        String fixedAmmount = ammount.replace("&", "");
        
        fixedAmmount = fixedAmmount.replace(".", ",");
        
        String[] splited = fixedAmmount.split(",");

//        System.out.println("split lenght " +splited.length);
//        System.out.println("splited[0] " +splited[0]);
//        log.debug("Tecnicard [FixUtil] split lenght " +splited.length);
//        log.debug("Tecnicard [FixUtil] splited[0] " +splited[0]);
         
        String rest = "";
        if (splited.length == 2) {

            char[] charArray = splited[1].toCharArray();
//            log.debug("Tecnicard [FixUtil] charArray " + Arrays.toString(charArray));
//            log.debug("Tecnicard [FixUtil] charArray[0] " + charArray[0]);
//            log.debug("Tecnicard [FixUtil] charArray.length " + charArray.length);
            
            if (charArray.length == 1) {
                rest = charArray[0] + "0";
            } else if(charArray.length > 2){
                rest = charArray[0]+""+charArray[1]+"";
            }else {
                rest = splited[1];
            }

            fixedAmmount = splited[0] + "." + rest;
//            log.debug("Tecnicard [FixUtil] 1 " + fixedAmmount);
            
        } else {
            fixedAmmount = fixedAmmount + ".00";
//            log.debug("Tecnicard [FixUtil] 2 "+fixedAmmount);
        }
        return fixedAmmount;
    }
    
}
