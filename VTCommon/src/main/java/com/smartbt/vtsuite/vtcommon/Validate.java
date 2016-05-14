/*
 ** File: Validate.java
 **
 ** Date Created: April 2013
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
package com.smartbt.vtsuite.vtcommon;

/**
 * Nomenclature class
 */
public class Validate {

    /**
     * Within specified time
     *
     * @param startTime From time
     * @param maxMillis Max time
     * @return true/false
     */
    public static boolean isTime(long startTime, long maxMillis) {
        return (System.currentTimeMillis() - startTime) >= maxMillis;
    }

    /**
     * Is positive
     *
     * @param value The number
     * @return true/false
     */
    public static boolean isPositiveNumber(String value) {
        if (value == null || value.isEmpty()) {
            return false;
        }
        for (int i = 0; i < value.length(); i++) {
            if (!Character.isDigit(value.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Is positive amount
     *
     * @param value The amount
     * @return true/false
     */
    public static boolean isPositiveUSDollar(String value) {
        if (value == null || value.isEmpty()) {
            return false;
        }
        int i = 0;
//        if (value.charAt(0) == '-') {
//            if (value.length() > 1) {
//                i++;
//            } else {
//                return false;
//            }
//        }
        for (; i < value.length(); i++) {
            if (!Character.isDigit(value.charAt(i)) && value.charAt(i) != '.' && value.charAt(i) != ',') {
                return false;
            }
        }
        return true;
    }
}
