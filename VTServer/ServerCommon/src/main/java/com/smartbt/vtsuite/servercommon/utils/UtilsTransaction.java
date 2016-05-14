/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.vtsuite.servercommon.utils;

import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Maite Gonzalez
 */
public class UtilsTransaction {

    public static Integer toIntAmount(Double amount) {
        String amountStr = amount.toString();
        String decimalPart = amountStr.substring(amountStr.indexOf(".")+1, amountStr.length());

        if (decimalPart.length() < 2) {
            int charsToBePad = 2 - decimalPart.length();
            //We should pad with '0' to complete two decimals
            amountStr = StringUtils.rightPad(amountStr, amountStr.length() + charsToBePad, '0');
        }

        return Integer.valueOf(amountStr.replace(".", ""));
    }
}
