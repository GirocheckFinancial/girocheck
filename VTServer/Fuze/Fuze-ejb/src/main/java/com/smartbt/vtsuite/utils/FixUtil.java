/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartbt.vtsuite.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

/**
 *
 * @author Alejo
 */
public class FixUtil {
    
//    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(FixUtil.class);
    
    public static String fixAmount(String amount){

        String fixedAmmount = amount.replace("&", "");
        
        fixedAmmount = amountToSend(fixedAmmount,2);
        
        fixedAmmount = fixedAmmount.replace(".", ",");
        
        String[] splited = fixedAmmount.split(",");

//        System.out.println("split lenght " +splited.length);
//        System.out.println("splited[0] " +splited[0]);
//        log.debug("[FixUtil] split lenght " +splited.length);
//        log.debug("[FixUtil] splited[0] " +splited[0]);
         
        String rest;
        if (splited.length == 2) {

            char[] charArray = splited[1].toCharArray();
//            System.out.println("charArray " + Arrays.toString(charArray));
//            System.out.println("charArray[0] " + charArray[0]);
//            System.out.println("charArray.length " + charArray.length);
//            log.debug("[FixUtil] charArray " + Arrays.toString(charArray));
//            log.debug("[FixUtil] charArray[0] " + charArray[0]);
//            log.debug("[FixUtil] charArray.length " + charArray.length);
            
            if (charArray.length == 1) {
                rest = charArray[0] + "0";
            } else if(charArray.length > 2){
                rest = charArray[0]+""+charArray[1]+"";
            }else {
                rest = splited[1];
            }

            fixedAmmount = splited[0] + "." + rest;
//            System.out.println("1 " + fixedAmmount);
//            log.debug("[FixUtil] 1 " + fixedAmmount);
            
        } else {
            fixedAmmount = fixedAmmount + ".00";
//            System.out.println("2 "+fixedAmmount);
//            log.debug("[FixUtil] 2 "+fixedAmmount);
        }
        return fixedAmmount;
    }
    
    public static String amountToSend(String amount, int places){
        
        Double val = Double.parseDouble(amount);

        if (places < 0) {
            throw new IllegalArgumentException();
        }

        BigDecimal bd = new BigDecimal(val);
        bd = bd.setScale(places, RoundingMode.HALF_UP);

        return bd.doubleValue() + "";
        
    }
    
}
