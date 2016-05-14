/*
 ** File: DirexDataElement.java
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
package com.smartbt.vtsuite.servercommon.messageformats.general;

/**
 * Direx Data Element Class
 */
public enum DirexDataElement implements DataElement {

    /**
     * Processor.
     */
    PROCESSOR,
    /**
     * Operation.
     */
    OPERATION,
    /**
     * Mode.
     */
    MODE,
    /**
     * Merchant identification.
     */
    MERCHANT_ID,
    /**
     * Terminal identification.
     */
    TERMINAL_ID,
    /**
     * Clerk identification.
     */
    CLERK_ID,
    /**
     * User name.
     */
    USERNAME,
    /**
     * Password.
     */
    PASSWORD,
    /**
     * Was swiped.
     */
    WAS_SWIPED,
    /**
     * Primary Account Number.
     */
    PRIMARY_ACCOUNT_NUMBER,
    /**
     * Expiration date.
     */
    EXPIRATION_DATE,
    /**
     * Track number 1.
     */
    TRACK_I,
    /**
     * Track number 2.
     */
    TRACK_II,
    /**
     * MICR
     */
    MICR,
    /**
     * Transaction amount.
     */
    AMOUNT,
    /**
     * Sequence number.
     */
    SEQUENCE_NUMBER,
    /**
     * Batch number.
     */
    BATCH_NUMBER,
    /**
     * Account type.
     */
    ACCOUNT_TYPE,
    /**
     * PIN block.
     */
    PIN_BLOCK,
    /**
     * KSN.
     */
    KSN,
    /**
     * Host transaction time
     */
    CREATED_AT,
    /**
     * Host merchant identification.
     */
    HOST_MERCHANT_ID,
    /**
     * Host Terminal identification.
     */
    HOST_TERMINAL_ID,
    /**
     * Host address.
     */
    HOST_ADDRESS,
    /**
     * Host port.
     */
    HOST_PORT,
    /**
     * Host secure flag.
     */
    HOST_SECURE_FLAG,
    /**
     * Entry method.
     */
    ENTRY_METHOD,
    /**
     * Diagnostics.
     */
    DIAGNOSTICS,
    /**
     * Terminal parameter set identification.
     */
    TERMINAL_PARAMETER_SET_ID,
    /**
     * Merchant parameter set identification.
     */
    MERCHANT_PARAMETER_SET_ID,
    /**
     * Response literal.
     */
    RESPONSE_LITERAL,
    /**
     * Approval flag.
     */
    APPROVAL_FLAG,
    /**
     * Approval code.
     */
    APPROVAL_CODE,
    /**
     * Approval number.
     */
    APPROVAL_NUMBER,
    /**
     * Authorized amount.
     */
    AUTHORIZED_AMOUNT;
}
