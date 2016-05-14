/*
 ** File: StringHelpers.java
 **
 ** Date Created: February 2013
 **
 ** Copyright @ 2004-2013 Smart Business Technology, Inc.
 **
 ** All rights reserved. No part of this software may be 
 ** reproduced, transmitted, transcribed, stored in a retrieval 
 ** system, or translated into any language or computer language, 
 ** in any form or by any means, electronic, mechanical, magnetic, 
 ** optical, chemical, manual or otherwise, without the prior 
 ** written permission of Smart Business Technology, Inc.
 **
 */
package com.smartbt.vtsuite.servercommon.utils.string;

/**
 * String Helpers Class
 */
public class StringHelpers {

    /**
     * Pads a string
     *
     * @param s The String to be pad.
     * @param n Number of spaces to pad.
     * @return Right justifies the string.
     */
    public static String padRightSpaces(String s, int n) {
        return String.format("%1$-" + n + "s", s);
    }

    /**
     * Pads a string
     *
     * @param s The String to be pad.
     * @param n Number of spaces to pad.
     * @return Left justifies the string
     */
    public static String padLeftSpaces(String s, int n) {
        return String.format("%1$" + n + "s", s);
    }

    /**
     * Pads a string with zeroes.
     *
     * @param i Position from which to pad with zeroes.
     * @param n Number of spaces to pad.
     * @return Right justifies the string with zeroes.
     */
    public static String padRightZeroes(Integer i, int n) {
        return padRightSpaces(String.valueOf(i), n).replace(' ', '0');
    }

    /**
     * Pads a string
     *
     * @param i Position from which to pad with zeroes.
     * @param n Number of spaces to pad.
     * @return Left justifies the string with zeroes
     */
    public static String padLeftZeroes(Integer i, int n) {
        return padLeftSpaces(String.valueOf(i), n).replace(' ', '0');
    }

    /**
     * Masks a string completely with "X".
     *
     * @param str String to be mask.
     * @return The masked string
     */
    public static String maskAll(String str) {
        if (str == null) {
            return "";
        }
        return String.format(String.format("%%0%dd", str.length()), 0).replace("0", "X");
    }

    /**
     * Masks a string according to its length to show only last four.
     *
     * @param str String to be mask.
     * @return The masked string
     */
    public static String mask(String str) {

        if (str == null) {
            return "";
        }

        String lastDigits = "";
        int len = str.length();

        // fyi: bank cards are 12-19 digits long

        if (len >= 12) {
            lastDigits = str.substring(len - 4, len);
        } else {
            // show none
        }

        // make a series of XXXXs according to len of string so resulting string is exact len as input string
        String x = String.format(String.format("%%0%dd", len - lastDigits.length()), 0).replace("0", "X");
        return String.format("%s%s", x, lastDigits);
    }
}
