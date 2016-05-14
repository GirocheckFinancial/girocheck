/*
 ** File: Messages.java
 **
 ** Date Created: February 2014
 **
 ** Copyright @ 2004-2014 Smart Business Technology, Inc.
 **
 ** All rights reserved. No part of this software may be 
 ** reproduced, transmitted, transcribed, stored in a retrieval 
 ** system, or translated into any language or computer language, 
 ** in any form or by any means, electronic, mechanical, magnetic, 
 ** optical, chemical, manual or otherwise, without the prior 
 ** written permission of Smart Business Technology, Inc.
 **
 */
package com.smartbt.girocheck.servercommon.i18n;

/**
 *
 * @author Ariel Saavedra
 */
public enum Messages {

    /**
     * *************************************************************************
     *********** WATCHDOGS
     * *************************************************************************
     */
    WATCHDOG_CARD_DELETED,
    WATCHDOG_REPEAT_TRANS,
    WATCHDOG_REFUND_AMOUNT,
    WATCHDOG_VOID,
    WATCHDOG_REFUND_QUANTITY,
    WATCHDOG_SWIPES_QUANTITY,
    WATCHDOG_CVV_SKIPPED,
    /**
     * *************************************************************************
     *********** VALIDATIONS
     * *************************************************************************
     */
    ERROR_ROLE_REPEATED,
    ERROR_APPLICATION_REPEATED,
    ERROR_PARAMETER_ASSOCIATED_WITH_APPLICATIONS,
    ERROR_PARAMETER_ASSOCIATED_WITH_TERMINALS,
    ERROR_SENDING_EMAIL,
    ERROR_MERCHANT_REPEATED,
    ERROR_TERMINAL_NUMBER_REPEATED,
    ERROR_MERCHANT_NUMBER_NOT_NULL,
    ERROR_MERCHANT_NAME_NOT_NULL,
    ERROR_CLERK_USERNAME_NOT_NULL,
    ERROR_TERMINAL_ID_NOT_NULL,
    ERROR_CLERK_REPEATED,
    ERROR_ADDING_LOGO_TO_MERCHANT,
    
    /**
     * *************************************************************************
     *********** MESSAGES
     * *************************************************************************
     */
    MOBILE_RECEIPT,
    APPLICATION_RECEIPT,
    /**
     * *************************************************************************
     *********** AUDIT_LOGS
     * *************************************************************************
     */
    AUDIT_LOG_APPLICATION_ADDED,
    AUDIT_LOG_CLERK_UPDATED,
    AUDIT_LOG_USER_ADDED,
    AUDIT_LOG_USER_DELETED,
    AUDIT_LOG_CLIENT_ADDED,
    AUDIT_LOG_CLIENT_DELETED,
    AUDIT_LOG_CLIENT_UPDATED,
    AUDIT_LOG_CLIENTS_ALL_DELETED,
    AUDIT_LOG_CLIENTS_IMPORTED,
    AUDIT_LOG_PARAMETER_VALUE_UPDATED,
    AUDIT_LOG_APPLICATION_PARAMETER_UPDATED,
    AUDIT_LOG_APPLICATION_PARAMETER_ADDED,
    AUDIT_LOG_APPLICATION_PARAMETER_DELETED,
    AUDIT_LOG_PARAMETER_DELETED,
    AUDIT_LOG_ROLE_PRIVILEGE_ADDED,
    AUDIT_LOG_ROLE_PRIVILEGE_DELETED,
    AUDIT_LOG_USER_UPDATED,
    AUDIT_LOG_ROLE_ADDED,
    AUDIT_LOG_ROLE_UPDATED,
    AUDIT_LOG_ROLE_DELETED,
    AUDIT_LOG_MERCHANT_UPDATED,
    AUDIT_LOG_SETTINGS_UPDATED,
    AUDIT_LOG_DO_TRANSACTION,
    AUDIT_LOG_VOID_TRANSACTION,
    AUDIT_LOG_FIRST_TIME_INSTALLATION_COMPLETE,

    /**
     * *************************************************************************
     *********** AMS BOARDING
     * *************************************************************************
     */    
    
    AMS_BOARDING_EMAIL_TITTLE,
    AMS_BOARDING_EMAIL_BODY,
    
    /**
     * *************************************************************************
     * ********** RECEIPT
     * *************************************************************************
     */
    TID_RECEIPT_INPUT_TITLE,
    ENTRY_METHOD_RECEIPT_INPUT_TITLE,
    CVV_RECEIPT_INPUT_TITLE,
    SEQUENCE_RECEIPT_INPUT_TITLE,
    BATCH_RECEIPT_INPUT_TITLE,
    APPROVAL_RECEIPT_INPUT_TITLE,
    SUBTOTAL_RECEIPT_INPUT_TITLE,
    TAX_RECEIPT_INPUT_TITLE,
    TIP_RECEIPT_INPUT_TITLE,
    TOTAL_RECEIPT_INPUT_TITLE,
    CURRENT_BALANCE_RECEIPT_INPUT_TITLE,
    CC_PREFIX,
    SWIPED_ENTRY_MSG,
    MANUAL_ENTRY_MSG,
    CURRENCY_SIGN,
    DATE_FORMAT,
    HOUR_FORMAT
}
