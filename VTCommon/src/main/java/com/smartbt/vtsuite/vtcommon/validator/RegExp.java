/*
 ** File: RegExp.java
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
package com.smartbt.vtsuite.vtcommon.validator;

/**
 * The RegExp Class
 *
 * @author Eduardo Juarez, Ariel Saavedra, Ariamnet Lopez
 */
public class RegExp {

    /*
     * ********************************************************************************************
     * *************************************************************************** SERVER REG EXP *
     * ********************************************************************************************
     */
    public final static String EXP_REG_SSN = "^([0-9]{9})$";
    public final static String EXP_REG_FULL_DATE = "^(0[1-9]|1[0-2])/([012][0-9]|3[01])/(([0-9]{4})|([0-9]{2}))\\s+([01]?[0-9]|2[0-3]):[0-5][0-9]";
    public final static String EXP_REG_DATE = "^(0[1-9]|1[0-2])/([012][0-9]|3[01])/(([0-9]{4})|([0-9]{2}))";
    public final static String EXP_REG_TIME = "^([01]?[0-9]|2[0-3]):[0-5][0-9]";
    public final static String EXP_REG_TERMINAL = "^(LK)[0-9]{6}$";
    public final static String EXP_REG_MERCHANT = "^[0-9]{15}$";
    public final static String EXP_REG_PAN = "^(?:4[0-9]{12}(?:[0-9]{3})?|5[1-5][0-9]{14}|6(?:011|5[0-9][0-9])[0-9]{12}|3[47][0-9]{13}|3(?:0[0-5]|[68][0-9])[0-9]{11}|(?:2131|1800|35\\d{3})\\d{11})$";
    public final static String EXP_REG_ZIP_CODE= "d{5}(-d{4})?";
    
    // public final static String EXP_REG_TEXT = "^[_a-zA-Z0-9-,@./!: ]{0,25}$";
    public final static String EXP_REG_OPERATION = "(?i)sale|refund|reversal|quick|delay";
    public final static String EXP_REG_BOOLEAN = "(?i)true|false";

    public final static String EXP_REG_VALID_ENCRYPTION_FORMATID = "32|37|38|45";
    public final static String EXP_REG_VALID_HEXADECIMAL = "^[0-9A-Fa-f]+$";
    /*
     * ********************************************************************************************
     * ******************************************************************** CLIENT FIELDS REG EXP *
     * ********************************************************************************************
     */
    /**
     * DO NOT CHANGE - Regular Expression to validate cvv number
     */
    public final static String EXP_REG_CVV = "^([0-9]{3,4})$|^$";

    /**
     * DO NOT CHANGE - Regular Expression to validate user entry on any number field. 
     * Allowed Characters: [0-9]
     */
    public final static String VALID_NUMBER_I_REG_EXP = "^[0-9]*$";
    /**
     * DO NOT CHANGE - Regular Expression to validate user entry on any text field. 
     * Allowed Characters: [a-z] [A-Z] [0-9]
     */
    public final static String VALID_TEXT_REG_EXP = "^[a-zA-Z0-9]*$";
    
    public final static String VALID_PASSW_REG_EXP = "^[a-zA-Z0-9!@#$%^*]*$";

    /**
     * DO NOT CHANGE - Regular Expression to validate user entry on any text field. 
     * Allowed Characters: [a-z] [A-Z] [ ] ['] [-]
     *
     * @param minLength
     * @param maxLength
     * @return
     */
    public final static String VALID_TEXT_REG_EXP(int minLength, int maxLength) {
        return VALID_TEXT_REG_EXP.substring(0, VALID_TEXT_REG_EXP.length() - 2) + "{" + minLength + "," + maxLength + "}$";
    }
    /**
     * DO NOT CHANGE - Regular Expression to validate user entry on any text field. 
     * Allowed Characters: [a-z] [A-Z] [0-9] [ ]
     */
    public static final String VALID_TEXT_I_REG_EXP = "^[a-zA-Z0-9 ]*$";
    /**
     * DO NOT CHANGE - Regular Expression to validate user entry on any text field. 
     * Allowed Characters: [a-z] [A-Z] [ ]
     */
    public static final String VALID_TEXT_II_REG_EXP = "^[a-zA-Z ]*$";
    /**
     * DO NOT CHANGE - Regular Expression to validate user entry on any text field. 
     * Allowed Characters: [a-z] [A-Z] [ ] ['] [-]
     */
    public static final String VALID_TEXT_III_REG_EXP = "^[a-zA-Z '-]*$";

    /**
     * DO NOT CHANGE - Regular Expression to validate user entry on any text field. 
     * Allowed Characters: [a-z] [A-Z] [ ] ['] [-]
     *
     * @param minLength
     * @param maxLength
     * @return
     */
    public final static String VALID_TEXT_III_REG_EXP(int minLength, int maxLength) {
        return VALID_TEXT_III_REG_EXP.substring(0, VALID_TEXT_III_REG_EXP.length() - 2) + "{" + minLength + "," + maxLength + "}$";
    }
    /**
     * DO NOT CHANGE - Regular Expression to validate user entry on any text field. 
     * Allowed Characters: [a-z] [A-Z] [0-9] [ ] ['] [-]
     */
    public static final String VALID_TEXT_IV_REG_EXP = "^[a-zA-Z0-9 '-]*$";
    /**
     * DO NOT CHANGE - Regular Expression to validate user entry on any text text field. 
     * Allowed Characters: [a-z] [A-Z] [0-9] [ ] ['] [.] [-]
     */
    public static final String VALID_TEXT_V_REG_EXP = "^[a-zA-Z0-9 '.-]*$";
    /**
     * DO NOT CHANGE - Regular Expression to validate user entry on any text text field. 
     * Allowed Characters: All including all symbols and non-alphanumeric characters
     */
    public static final String VALID_TEXT_VI_REG_EXP = "^[\\w\\W]*$";

    /**
     * DO NOT CHANGE - Regular Expression to validate user entry on any text field. 
     * Allowed Characters: All including all symbols and non-alphanumeric characters
     *
     * @param minLength
     * @param maxLength
     * @return
     */
    public final static String VALID_TEXT_VI_REG_EXP(int minLength, int maxLength) {
        return VALID_TEXT_VI_REG_EXP.substring(0, VALID_TEXT_VI_REG_EXP.length() - 2) + "{" + minLength + "," + maxLength + "}$";
    }
    /**
     * DO NOT CHANGE - Regular Expression to validate user entry on any text text field. 
     * Allowed Characters: [a-z] [A-Z] [0-9] [ ] ['] [.] [-]
     */
    public static final String VALID_TEXT_VII_REG_EXP = "^[a-zA-Z0-9 ',.-]*$";
    /**
     * DO NOT CHANGE - Regular Expression to validate user entry on any search text field. 
     * Allowed Characters: [a-z] [A-Z] [0-9] [ ] ['] [@] [.] [-] [,]
     */
    public static final String VALID_SEARCH_I_REG_EXP = "^[a-zA-Z0-9 '@.-]*$";
    /**
     * DO NOT CHANGE - Regular Expression to validate user entry on any search text field. 
     * Allowed Characters: [a-z] [A-Z] [0-9] [ ] ['] [@] [.] [-] [_] [?] [/] [!] [.] [:]
     */
    public final static String VALID_SEARCH_II_REG_EXP = "^[a-zA-Z0-9: '@._!\\?\\/-]*$";
    /**
     * DO NOT CHANGE - Regular Expression to validate user entry in the Credit Card Number field.
     */
    public static final String CREDIT_CARD_REG_EXP = "^(?:4[0-9]{12}(?:[0-9]{3})?|5[1-5][0-9]{14}|6(?:011|5[0-9][0-9])[0-9]{12}|3[47][0-9]{13}|3(?:0[0-5]|[68][0-9])[0-9]{11}|(?:2131|1800|35\\d{3})\\d{11})$";
    /**
     * DO NOT CHANGE - Regular Expression to validate user entry in the Email field.
     */
    public static final String EMAIL_REG_EXP = "^([a-zA-Z0-9_.\\-+])+@(([a-zA-Z0-9\\-])+\\.)+[a-zA-Z0-9]{2,4}$";
    /**
     * DO NOT CHANGE - Regular Expression to validate user entry in the Expiration Date field.
     * Format: MMYY
     */
    public static final String EXPDATE_REG_EXP = "^((0[1-9])|(1[0-2]))(\\d{2})$";
    /**
     * DO NOT CHANGE - Regular Expression to validate user entry in the Expiration Month field.
     * Format: MM
     */
    public static final String MONTH_REG_EXP = "^(0?[1-9]|1[012])$";
    /**
     * DO NOT CHANGE - Regular Expression to validate user entry in the CVV field. Format:
     * #####(###) ### - ####
     */
    public static final String PHONE_REG_EXP = "^(\\d{0,3}) ?\\(?(\\d{3})\\)? ?-? ?(\\d{3}) ?-? ?(\\d{4})$";
    /**
     * DO NOT CHANGE - Regular Expression to validate user entry in the CVV field. Format: ### or
     * ####
     */
    public static final String CVV_REG_EXP = "^[0-9]{3,4}$";
    /*
     * ********************************************************************************************
     * *************************************************************** CLIENT FIELDS TRANSFORM TO *
     * ********************************************************************************************
     */
    /**
     * DO NOT CHANGE - Transform format used for phone entry. Format: (#####){0,5}(###) ### - ####
     */
    public static final String PHONE_TRANSFORM_TO = "$1 ($2) $3 - $4";

    public final static String EXP_REG_TEXT(int minLength, int maxLength) {
        return "^[a-zA-Z0-9.@\\-_!\\? ]{" + minLength + "," + maxLength + "}$";
    }

    public final static String EXP_REG_INTEGER = "[0-9]+";

    public final static String EXP_REG_INTEGER(int minLength, int maxLength) {
        return "^[0-9]{" + minLength + "," + maxLength + "}$";
    }

    public final static String EXP_REG_FLOAT = "^([0-9]*(\\.[0-9]{2})?){3,20}$";

    public final static String EXP_REG_FLOAT(int minLength, int maxLength, int precision) {
        return "^([0-9]*(\\.[0-9]{" + precision + "})?){" + minLength + "," + maxLength + "}$";
    }
    public final static String EXP_REG_KEY_INTEGER = "[0-9]";
    public final static String EXP_REG_KEY_FLOAT = "[0-9.]";
    public final static String EXP_REG_KEY_EMAIL = "[a-zA-Z0-9.@\\-_]";
}
