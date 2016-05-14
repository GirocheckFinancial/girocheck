/*
 ** File: NomTerminalParameter.java
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
package com.smartbt.vtsuite.vtcommon.nomenclators;

/**
 *
 * Nomenclature class
 */
public enum NomTerminalParameter {

    /**
     * TERMINAL_PARAMETER - SESSION_TIME_OUT
     */
    SESSION_TIME_OUT(1),
    /**
     * TERMINAL_PARAMETER - START_ON_CARD_PAYMENT
     */
    START_ON_CARD_PAYMENT(2),
    /**
     * TERMINAL_PARAMETER - RETURN_TO_CARD_PAYMENT
     */
    RETURN_TO_CARD_PAYMENT(3),
    /**
     * TERMINAL_PARAMETER - TIP
     */
    TIP(4),
    /**
     * TERMINAL_PARAMETER - TAX
     */
    TAX(5),
    /**
     * TERMINAL_PARAMETER - TAX_PERCENTAGE
     */
    TAX_PERCENTAGE(6),
    /**
     * TERMINAL_PARAMETER - MERCHANT_EMAIL_COPY
     */
    MERCHANT_EMAIL_COPY(7),
    /**
     * TERMINAL_PARAMETER - RECEIPT_MESSAGE
     */
    RECEIPT_MESSAGE(8),
    /**
     * TERMINAL_PARAMETER - CONFIRM_LEAVING_CARDPAYMENT
     */
    CONFIRM_LEAVING_CARDPAYMENT(9),
    /**
     * TERMINAL_PARAMETER - DISPLAY_CONFIRM_SCREEN_CARDPAYMENT
     */
    DISPLAY_CONFIRM_SCREEN_CARDPAYMENT(10),
    /**
     * TERMINAL_PARAMETER - SKIP_CUSTOMER_CARDPAYMENT
     */
    SKIP_CUSTOMER_CARDPAYMENT(11),
    /**
     * TERMINAL_PARAMETER - SHOW_PINPAD_CARDPAYMENT
     */
    SHOW_PINPAD_CARDPAYMENT(12),
    /**
     * TERMINAL_PARAMETER - SHOW_RECEIPT_CARDPAYMENT
     */
    SHOW_RECEIPT_CARDPAYMENT(13),
    /**
     * TERMINAL_PARAMETER - REQUIRED_FIELDS_CARDPAYMENT
     */
    REQUIRED_FIELDS_CARDPAYMENT(14),
    /**
     * TERMINAL_PARAMETER - EMAIL_RECEIPT_CARDPAYMENT
     */
    EMAIL_RECEIPT_CARDPAYMENT(15),
    /**
     * TERMINAL_PARAMETER - SUBTOTAL_CARDPAYMENT
     */
    SUBTOTAL_CARDPAYMENT(16),
    /**
     * TERMINAL_PARAMETER - PO_NUMBER_CARDPAYMENT
     */
    PO_NUMBER_CARDPAYMENT(17),
    /**
     * TERMINAL_PARAMETER - INVOICE_CARDPAYMENT
     */
    INVOICE_CARDPAYMENT(18),
    /**
     * TERMINAL_PARAMETER - SERVER_CARDPAYMENT
     */
    SERVER_CARDPAYMENT(19),
    /**
     * TERMINAL_PARAMETER - LAST4_CARDPAYMENT
     */
    LAST4_CARDPAYMENT(20),
    /**
     * TERMINAL_PARAMETER - MANUAL_ENTRY_CARDPAYMENT
     */
    MANUAL_ENTRY_CARDPAYMENT(21),
    /**
     * TERMINAL_PARAMETER - SAVE_CARD_CARDPAYMENT
     */
    SAVE_CARD_CARDPAYMENT(22),
    /**
     * TERMINAL_PARAMETER - MC_PCARDIII_CARDPAYMENT
     */
    MC_PCARDIII_CARDPAYMENT(23),
    /**
     * TERMINAL_PARAMETER - VISA_PCARDIII_CARDPAYMENT
     */
    VISA_PCARDIII_CARDPAYMENT(24),
    /**
     * TERMINAL_PARAMETER - NEW_TRANS_CARDPAYMENT
     */
    NEW_TRANS_CARDPAYMENT(25),
    /**
     * TERMINAL_PARAMETER - REPEAT_CARDPAYMENT
     */
    REPEAT_CARDPAYMENT(26),
    /**
     * TERMINAL_PARAMETER - EMAIL_CARDPAYMENT
     */
    EMAIL_CARDPAYMENT(27),
    /**
     * TERMINAL_PARAMETER - SKIP_SIGNATURE_CARDPAYMENT
     */
    SKIP_SIGNATURE_CARDPAYMENT(28);
    private int id;

    private NomTerminalParameter(int id) {
        this.id = id;
    }

    /**
     * Get id
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Get id
     *
     * @return the id
     */
    public String getIdAsString() {
        return id + "";
    }

    /**
     * Get value
     *
     * @return the value
     */
    public String getValue() {
        switch (id) {
            case 1:
                return "sessionTimeOut";
            case 2:
                return "startOnCardPayment";
            case 3:
                return "returnToCardPayment";
            case 4:
                return "tip";
            case 5:
                return "tax";
            case 6:
                return "taxPercentage";
            case 7:
                return "merchantCopy";
            case 8:
                return "receiptMessage";
        }
        return "";
    }
}
