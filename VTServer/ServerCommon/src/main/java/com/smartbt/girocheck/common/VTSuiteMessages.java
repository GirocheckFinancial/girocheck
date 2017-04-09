/*
 ** File: VTSuiteMessages.java
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
package com.smartbt.girocheck.common;

/**
 * VTSuiteMessages Class - containing all VTSuiteMessages's constants and
 * messages.
 */
public class VTSuiteMessages {

    /**
     * ********************* MESSAGES SUCCESS ****************
     */
    /**
     * Message
     */
    public final static String SUCCESS = "SUCCESS";
    /**
     * Message Format Version
     */
    public final static String MESSAGE_FORMAT_VERSION = "04A";
    /**
     * ********************* MESSAGES ERROR ****************
     *
     * /**
     * Message
     */
    public final static String ERROR_GENERAL = "System Error, please try again or call customer support.";
    /**
     * Message
     */
    public final static String ERROR_TRANSACTION_TERMINAL = "ERROR WITH FIELD TERMINAL";
    /**
     * Message
     */
    public final static String ERROR_TRANSACTION_MERCHANT = "ERROR WITH FIELD MERCHANT";
    /**
     * Message
     */
    public final static String ERROR_TRANSACTION_CHK = "ERROR WITH FIELD CHK";
    /**
     * Message
     */
    public final static String ERROR_TRANSACTION_MODE = "ERROR WITH FIELD MODE";
    /**
     * Message
     */
    public final static String ERROR_TRANSACTION_OPERATION = "ERROR WITH FIELD OPERATION";
    /**
     * Message
     */
    public final static String ERROR_TRANSACTION_PAN = "ERROR WITH FIELD PAN";
    /**
     * Message
     */
    public final static String ERROR_TRANSACTION_EXPDATE = "ERROR WITH FIELD EXPDATE";
    /**
     * Message
     */
    public final static String ERROR_TRANSACTION_TK2 = "ERROR WITH FIELD TK2";
    /**
     * Message
     */
    public final static String ERROR_TRANSACTION_CVV = "ERROR WITH FIELD CVV";
    /**
     * Message
     */
    public final static String ERROR_TRANSACTION_CVVFLAG = "ERROR WITH FIELD CVV FLAG";
    /**
     * Message
     */
    public final static String ERROR_TRANSACTION_DATE = "ERROR WITH FIELD DATE";
    /**
     * Message
     */
    public final static String ERROR_TRANSACTION_TIME = "ERROR WITH FIELD TIME";
    /**
     * Message
     */
    public final static String ERROR_TRANSACTION_AMOUNT = "ERROR WITH FIELD AMOUNT";
    /**
     * Message
     */
    public final static String FAIL_INC_SEQNUM = "FAILED TO INCREMENT SEQUENCE NUMBER ";
    /**
     * Message
     */
    public final static String FAIL_LOG_TRANSACTION = "FAILED TO LOG TRANSACTION ";
    /**
     * Message
     */
    public final static String ERROR_TRANSMITING_TRANSACTION_HOST = "ERROR TRANSMITING OR RECEIVING TRANSACTION ";
    /**
     * Message
     */
    public final static String FAIL_INSERT_SIGNATURE = "FAILED TO INSERT SIGNATURE";
    /**
     * Message
     */
    public final static String FAIL_GET_TRANSACTION = "FAILED TO GET TRANSACTION";
    /**
     * Message
     */
    public final static String FAIL_GET_SIGNATURE = "FAILED TO GET SIGNATURE";

    /**
     * ********************* MESSAGES NOT FOUND ******************************
     */
    /**
     * Message
     */
    public final static String INVALID_GROUP = "INVALID GROUP";
    /**
     * Message
     */
    public final static String INVALID_USER = "INVALID USER";
    
    public final static String USER_BLOCKED = "User Blocked";
    /**
     * Message
     */
    public final static String USER_NOT_FOUND = "USER NOT FOUND";
    /**
     * Message
     */
    public final static String JMS_WRONG_TYPE = "JMS OBJECT MESSAGE OF WRONG TYPE ";
    /**
     * Message
     */
    public final static String BEAN_EXCEPTION = "EXCEPTION IN BEAN ";
    /**
     * Message
     */
    public final static String NOT_TERMINAL_HOST_PARAMETER_FOUND = "FAILED TO GET TERMINAL HOST PARAMETER ";
    /**
     * Message
     */
    public final static String NOT_MERCHANT_HOST_PARAMETER_FOUND = "FAILED TO GET MERCHANT HOST PARAMETER ";
    /**
     * Message
     */
    public final static String NOT_HOST_PARAMETER_FOUND = "FAILED TO GET HOST PARAMETER ";
    /**
     * Message
     */
    public final static String FAILED_TO_VERIFY_USER_ACCESS_CORE = "FAILED TO VERIFY USER ACCESS_CORE ";
    /**
     * Message
     */
    public final static String FAILED_TO_VERIFY_USER_ACCESS_FRONT = "FAILED TO VERIFY USER ACCESS FRONT ";
    /**
     * Message
     */
    public final static String FAILED_TO_VERIFY_TERMINAL_ACCESS_CORE = "FAILED TO VERIFY TERMINAL ACCESS CORE ";
    /**
     * Message
     */
    public final static String FAILED_TO_GET_TERMINAL = "FAILED TO GET TERMINAL ";
    /**
     * Message
     */
    public final static String FAILED_TO_GET_MODE = "FAILED TO GET MODE ";
    /**
     * Message
     */
    public final static String FAILED_TO_GET_OPERATION = "FAILED TO GET OPERATION ";
    /**
     * Message
     */
    public final static String FAILED_TO_GET_HOST_BY_MERCHANT_MODE = "FAILED TO GET HOST BY MERCHANT AND MODE ";
    /**
     * Message
     */
    public final static String FAILED_TO_GET_SENSITIVE_DATA = "FAILED TO GET  SENSITIVE DATA ";
    /**
     * Message
     */
    public final static String USER_CAN_NOT_PERFORM_TRANSACTION = "USER CAN NOT PERFORM TRANSACTION ";
    /**
     * Message
     */
    public final static String ACCESING_TO_DB = "ERROR ACCESING TO DB ";
    /**
     * Message
     */
    public final static String VALUE_IS_EMPTY = "THE VALUE IS NULL OR EMPTY ";
    /**
     * Message
     */
    public final static String VALUE_DATA_TYPE_IS_INCORRECT = "THE VALUE'S DATA TYPE IS INCORRECT";
    /**
     * Message
     */
    public final static String FIELD_VALUE_IS_EMPTY = "THE FIELD VALUE IS EMPTY ";
    /**
     * Message
     */
    public final static String FILTER_MUST_HAS_VALUE = "THE FILTER MUST HAS VALUE ";
    /**
     * Message
     */
    public final static String FILTER_IS_NOT_ALLOWED = "THE FILTER IS NOT ALLOWED ";
    /**
     * Message
     */
    public final static String CUSTOMER_DOES_NOT_EXIST = "THIS CUSTOMER DOES NOT EXIST ";
    /**
     * Message
     */
    public final static String CLIENT_DOES_NOT_EXIST = "THIS CLIENT DOES NOT EXIST ";
    /**
     * Message
     */
    public final static String MERCHANT_DOES_NOT_EXIST = "THIS MERCHANT DOES NOT EXIST ";
    /**
     * Message
     */
    public final static String PROPERTY_DOES_NOT_EXIST = "THIS PROPERTY DOES NOT EXIST ";
    /**
     * Message
     */
    public final static String MERCHANT_PARAMETER_VALUE_DOES_NOT_EXIST = "THIS MERCHANT PARAMETER VALUE DOES NOT EXIST ";
    /**
     * Message
     */
    public final static String USER_DOES_NOT_EXIST = "THIS USER DOES NOT EXIST ";
    
    public final static String PRIVILEGE_DOES_NOT_EXIST = "THIS PRIVILEGE DOES NOT EXIST ";
    
    /**
     * Message
     */
    public final static String USERNAME_IS_NULL = "USERNAME IS NULL";
    
    /**
     * Message
     */
    public final static String PASSWORD_IS_NULL = "PASSWORD IS NULL";
    public final static String INVALID_PASSWORD = "Invalid password format.";
    public final static String LAST_5_PASSW = "Can not repeat one of the last 5 passwords.";
    
    /**
     * Message
     */
    public final static String CANNOT_ENCRYPT_NULL_PASSWORD = "CANNOT ENCRYPT NULL PASSWORD";
    
    /**
     * Message
     */
    public final static String TERMINAL_DOES_NOT_EXIST = "THIS TERMINAL DOES NOT EXIST ";
    /**
     * Message
     */
    public final static String TERMINAL_PARAMETER_VALUE_DOES_NOT_EXIST = "THIS TERMINAL PARAMETER VALUE DOES NOT EXIST ";
    /**
     * Message
     */
    public final static String DEVICE_DOES_NOT_EXIST = "THIS DEVICE DOES NOT EXIST ";
    /**
     * Message
     */
    public final static String CLERK_DOES_NOT_EXIST = "THIS CLERK DOES NOT EXIST ";
    /**
     * Message
     */
    public final static String TRANSACTION_DOES_NOT_EXIST = "THIS TRANSACTION DOES NOT EXIST ";
    /**
     * Message
     */
    public final static String PARAMETER_DOES_NOT_EXIST = "THIS PARAMETER DOES NOT EXIST ";
    /**
     * Message
     */
    public final static String PARAMETER_VALUE_DOES_NOT_EXIST = "THIS PARAMETER VALUE DOES NOT EXIST ";
    /**
     * Message
     */
    public final static String PARAMETER_READ_ONLY = "THIS PARAMETER IS READ ONLY AND IT CANNOT BE MODIFIED";
    /**
     * Message
     */
    public final static String APPLICATION_DOES_NOT_EXIST = "THIS APPLICATION DOES NOT EXIST ";
    /**
     * Message
     */
    public final static String ROLE_DOES_NOT_EXIST = "THIS ROLE DOES NOT EXIST ";
    /**
     * Message
     */
    public final static String USER_ROLE_DOES_NOT_EXIST = "THIS USER ROLE DOES NOT EXIST ";
    /**
     * Message
     */
    public final static String USER_PRIVILEGE_DOES_NOT_EXIST = "THIS USER PRIVILEGE DOES NOT EXIST ";
    /**
     * Message
     */
    public final static String CLERK_ROLE_DOES_NOT_EXIST = "THIS CLERK_ROLE DOES NOT EXIST ";
    /**
     * Message
     */
    public final static String CLERK_PRIVILEGE_DOES_NOT_EXIST = "THIS CLERK_PRIVILEGE DOES NOT EXIST ";
    /**
     * Message
     */
    public final static String CLERK_ROLE_PRIVILEGE_DOES_NOT_EXIST = "THIS CLERK_ROLE_PRIVILEGE DOES NOT EXIST ";
    /**
     * Message
     */
    public final static String USER_ROLE_PRIVILEGE_DOES_NOT_EXIST = "THIS USER_ROLE_PRIVILEGE DOES NOT EXIST ";
    /**
     * Message
     */
    public final static String ASSOCIATION_ALREADY_EXIST = "THIS ASSOCIATION ALREADY EXIST";
    /**
     * Message
     */
    public final static String INVALID_LOGIN_CREDENTIALS = "Invalid Credentials";
    public final static String INACTIVE_USER = "Inactive user";

    /**
     * Message
     */
    public final static String NO_HOST_FOUND = "NO HOST FOUND ";

    /**
     * Message
     */
    public final static String UNABLE_VOID = "UNABLE TO VOID  TRANSACTION ";
    /**
     * Message
     */
    public final static String UNABLE_FINALIZE = "UNABLE TO MODIFY A FINALIZED TRANSACTION ";

    /**
     * Message
     */
    public final static String ERROR_HSM = "ERROR WITH HSM ";
    /**
     * Message
     */
    public final static String ERROR_ROLE_REPEATED = "There is already a role with the entered name";
    /**
     * Message
     */
    public final static String ERROR_APPLICATION_REPEATED = "There is already an application with the entered name";
    /**
     * Message
     */
    public final static String ERROR_INVALID_CHARACTERS = "Invalid characters entered.";

    /**
     * Message
     */
    public final static String ERROR_LENGTH = "The length of the field is not allowed.";
    
    /**
     * ********************* MESSAGES RECEIPT ******************************
     */
    
    /**     
     * Message
     */
    public static String MOBILE_RECEIPT = "Your Mobile E-Receipt";
    /**
     * Message
     */
    public static String APPLICATION_RECEIPT = "Your E-Receipt";

    
    public final static String ERROR_MERCHANT_REPEATED = "There is already an merchant with the number: ";
   
    
    public final static String MERCHANT_NUMBER_NOT_NULL = "The merchant must have a number.";
    
    public final static String MERCHANT_NAME_NOT_NULL = "The merchant must have a name.";
    
    public final static String CLERK_USERNAME_NOT_NULL = "The clerk must have a username.";
    
    public final static String TERMINAL_ID_NOT_NULL = "The terminal must have a terminalId.";
    
    public static String ERROR_CLERK_REPEATED = "There is already an clerk with the username: ";
    public static String PASSWORD_EXPIRED = "Password Expired";
    
    public final static String FEESCHEDULE_DOES_NOT_EXIST = "THIS FEESCHEDULE DOES NOT EXIST ";
    
    public final static String ERROR_FEE_SCHEDULE_METHOD = "There is another fee schedule record with the same method.";
    
    public static final String LOGIN_FAILED = "Login Failed. Please Contact Customer Support.";
    
    
    
    
    /**
     * Get Default Error Message
     *
     * @param ex Exception
     * @return Error message string
     */
    public final static String getErrorMsg(Exception ex) {
        String errorMsg = ERROR_GENERAL;
        return errorMsg;
    }
}
