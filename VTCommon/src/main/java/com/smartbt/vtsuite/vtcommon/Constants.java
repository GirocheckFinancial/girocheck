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
package com.smartbt.vtsuite.vtcommon;

/**
 * VTSuiteMessages Class - containing all VTSuiteMessages's constants and
 * messages.
 */
public class Constants {

    /**
     * ******************** CODES **********************
     */
    /**
     * Error Code - CODE_ERROR_GENERAL
     */
    public static final int CODE_ERROR_GENERAL = 500;
    /**
     * Error Code
     */
    public static int CODE_ERROR_HSM = 501;
    /**
     * Error Code - CODE_ERROR_HSM
     */
    public static int CODE_ERROR_CONNECTION_HOST = 502;
    /**
     * Error Code - CODE_ERROR_CONNECTION_HOST
     */
    public static int CODE_ERROR_VERSION = 503;
    /**
     * SUCCESS Code - CODE_ERROR_VERSION
     */
    public static final int CODE_SUCCESS = 100;
    /**
     * Error Code - CODE_SUCCESS
     */
    public static final int CODE_INVALID_GROUP = 400;
    /**
     * Error Code - CODE_INVALID_GROUP
     */
    public static final int CODE_INVALID_USER = 401;
    /**
     * Error Code - CODE_INVALID_USER
     */
    public static final int CODE_USER_NOT_FOUND = 402;
    /**
     * Error Code - CODE_BADLOGIN
     */
    public static final int CODE_BADLOGIN = 403;
    /**
     * Error Code - CODE_INVALID_ENTRY_DATA
     */
    public static int CODE_INVALID_ENTRY_DATA = 405;
    /**
     * Error Code - CODE_SESSION_VALID
     */
    public static int CODE_SESSION_VALID = 406;
    /**
     * Error Code - CODE_SESSION_EXPIRE
     */
    public static int CODE_SESSION_EXPIRE = 407;
    /**
     * Error Code - CODE_SESSION_LOST
     */
    public static int CODE_SESSION_LOST = 408;
    /**
     * Error Code - CODE_WRONG_USER
     */
    public static final int CODE_WRONG_USER = 409;
    /**
     * Error Code - CODE_NOT_PRIVILEGE
     */
    public static final int CODE_NOT_PRIVILEGE = 410;
       /**
     * Error Code - CODE_JMS_ERROR
     */
    public static final int CODE_JMS_ERROR = 411;
    
    /**
     * ******************** VTAPPLET CODES **********************
     */
    /**
     * VTApplet - Success Code
     */
    public static final int VTAPPLET_SUCCESS_CODE = 111;
    /**
     * VTApplet - Cancel Code
     */
    public static final int VTAPPLET_CANCEL_CODE = 101;
    /**
     * VTApplet - PIN Error Code
     */
    public static final int VTAPPLET_PIN_ERROR_CODE = 102;
    /**
     * VTApplet - VTApplet Error Code
     */
    public static final int VTAPPLET_ERROR_CODE = 103;
    /**
     * VTApplet - Device Error Code
     */
    public static final int VTAPPLET_DEVICE_ERROR_CODE = 104;
    /**
     * VTApplet - Device Not Found Error Code
     */
    public static final int VTAPPLET_DEVICE_NOT_FOUND_ERROR_CODE = 105;
    /**
     * VTApplet - Not supported Error Code
     */
    public static final int VTAPPLET_NOT_SUPPORTED_ERROR_CODE = 106;
    /**
     * VTApplet - Busy Error Code
     */
    public static final int VTAPPLET_BUSY_ERROR_CODE = 107;
    /**
     * VTApplet - Bad Format Error Code
     */
    public static final int VTAPPLET_BAD_FORMAT_ERROR_CODE = 108;
    /**
     * VTApplet - Unknown Command Error Code
     */
    public static final int VTAPPLET_UNKNOWN_CMD_ERROR_CODE = 109;
    /**
     * VTApplet - VTApplet Not Found Error Code
     */
    public static final int VTAPPLET_NOT_FOUND_CMD_ERROR_CODE = 110;
    
    public static final int CODE_INACTIVE_USER = 412;  
    
    public static final int REPORT_ERROR_CODE = 311;    
    
    public static final int INVALID_PASSWORD = 413; 
    
    public static final int FEE_RECORD_EXISTS = 414; 
    
    public static final int LOGIN_FAILED = 800;
    public static final int CLIENT_DOES_NOT_EXIST = 801;
    public static final int MOBILE_CLIENT_ALREADY_EXIST = 802;
    public static final int USERNAME_IN_USE = 803;
    public static final int CARD_BELONG_TO_ANOTHER_CLIENT = 804;
    public static final int REQUIRED_FIELD = 805;
    public static final int MOBILE_CLIENT_NOT_EXIST = 806;
    public static final int FORGOT_PASSWORD_KEY_MISMATCH = 807;
    public static final int FORGOT_PASSWORD_KEY_EXPIRED = 808;
    public static final int INVALID_TOKEN = 809;
    public static final int COULD_NOT_SEND_ACCESS_CODE = 810;
    public static final int INVALID_OLD_PASSWORD = 811;
    
    
    public static final int CARD_NOT_PERSONALIZED = 902;
    
    public static final int SUCCESS = 100;
    
}