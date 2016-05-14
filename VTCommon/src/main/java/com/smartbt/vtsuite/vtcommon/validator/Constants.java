/*
 ** File: Constants.java
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
 * The Constants class
 *
 * @author Ariamnet Lopez, Ariel Saavedra
 */
public class Constants {

    /*
     * ********************************************************************************************
     * ************************************************* DO NOT CHANGE - CLIENT FIELDS MAX LENGTH *
     * ********************************************************************************************
     */
    public static final int STANDARD_TEXT_MIN_LENGTH = 3;
    public static final int STANDARD_TEXT_MAX_LENGTH = 25;
    public static final int MEDIUM_TEXT_MAX_LENGTH = 50;
    public static final int HIGH_TEXT_MAX_LENGTH = 254;
    public static final int STANDARD_PHONE_TEXT_LENGTH = 13;
    
    public static final int CVV_MAX_LENGTH = 4;
    public static final int EXPDATE_LENGTH = 4;
    public static final int LAST_FOUR_MAX_LENGTH = 4;
    public static final int ZIP_MIN_LENGTH = 5;
    public static final int ZIP_MAX_LENGTH = 10;
    public static final int CC_MAX_LENGTH = 19;
    public static final int ACCOUNT_MAX_LENGTH = 30;
    public static final int PO_NUMBER_MAX_LENGTH = 20;
    public static final int PHONE_MAX_LENGTH = 20;
    public static final int AMOUNT_MAX_LENGTH = 8;
    public static final int AUTH_NUMBER_LENGTH = 6;
    
    /*
     * ********************************************************************************************
     * ************************************************** DO NOT CHANGE - CLIENT FIELDS CONSTANTS *
     * ********************************************************************************************
     */
    public static final int MIN_AMOUNT_ALLOWED = 001;
    public static final int MAX_AMOUNT_ALLOWED = 99999999;
    
    public static final int MIN_NOT_REQUIRED_AMOUNT_ALLOWED = 0;
    
    public static final int FORMATID_NOT_ENC_VALUE = 32;
    public static final int FORMATID_ENC_VALUE = 38;
}
