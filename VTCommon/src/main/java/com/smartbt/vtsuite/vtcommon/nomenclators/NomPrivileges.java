/*
 ** File: NomPrivileges.java
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
public enum NomPrivileges {

    /**
     * PRIVILEGES - ALLOW_CARD_PAYMENT
     */
    ALLOW_CARD_PAYMENT(1),
    /**
     * PRIVILEGES - ALLOW_CARD_PAYMENT_MANUAL
     */
    ALLOW_CARD_PAYMENT_MANUAL(2),
    /**
     * PRIVILEGES - ALLOW_REFUND
     */
    ALLOW_REFUND(3),
    /**
     * PRIVILEGES - ALLOW_REFUND_MANUAL
     */
    ALLOW_REFUND_MANUAL(4),
    /**
     * PRIVILEGES - ALLOW_DASHBOARD
     */
    ALLOW_DASHBOARD(5),
    /**
     * PRIVILEGES - ALLOW_VOID
     */
    ALLOW_VOID(6),
    /**
     * PRIVILEGES - ALLOW_CHANGE_SETTINGS
     */
    ALLOW_CHANGE_SETTINGS(7),
    /**
     * PRIVILEGES - ALLOW_MY_TRANSACTIONS
     */
    ALLOW_MY_TRANSACTIONS(8),
    /**
     * PRIVILEGES - ALLOW_MY_CUSTOMERS
     */
    ALLOW_MY_CUSTOMERS(9),
    /**
     * PRIVILEGES - ALLOW_CREDIT_SALE_SWIPE
     */
    ALLOW_CREDIT_SALE_SWIPE(10),
    /**
     * PRIVILEGES - ALLOW_DEBIT_SALE_SWIPE
     */
    ALLOW_DEBIT_SALE_SWIPE(11),
    /**
     * PRIVILEGES - ALLOW_CREDIT_SALE_MANUAL
     */
    ALLOW_CREDIT_SALE_MANUAL(12),
    /**
     * PRIVILEGES - ALLOW_CREDIT_REFUND_SWIPE
     */
    ALLOW_CREDIT_REFUND_SWIPE(13),
    /**
     * PRIVILEGES - ALLOW_DEBIT_REFUND_SWIPE
     */
    ALLOW_DEBIT_REFUND_SWIPE(14),
    /**
     * PRIVILEGES - ALLOW_CREDIT_SALE_VOID
     */
    ALLOW_CREDIT_SALE_VOID(15),
    /**
     * PRIVILEGES - ALLOW_DEBIT_SALE_VOID
     */
    ALLOW_DEBIT_SALE_VOID(16),
    /**
     * PRIVILEGES - ALLOW_CREDIT_BALANCE_INQ
     */
    ALLOW_CREDIT_BALANCE_INQ(17),
    /**
     * PRIVILEGES - ALLOW_DEBIT_BALANCE_INQ
     */
    ALLOW_DEBIT_BALANCE_INQ(18),
    /**
     * PRIVILEGES - ALLOW_CREDIT_FORCE
     */
    ALLOW_CREDIT_FORCE(19),
    /**
     * PRIVILEGES - ALLOW_CHECK_PAYMENT
     */
    ALLOW_CHECK_PAYMENT(20),
    /**
     * PRIVILEGES - ALLOW_RECURRING_PAYMENT
     */
    ALLOW_RECURRING_PAYMENT(21),
    /**
     * PRIVILEGES - ALLOW_MY_DASHBOARD
     */
    ALLOW_MY_DASHBOARD(22),
    /**
     * PRIVILEGES - ALLOW_REPORTS
     */
    ALLOW_REPORTS(23),
    /**
     * PRIVILEGES - ALLOW_WATCHDOGS
     */
    ALLOW_WATCHDOGS(24),
    /**
     * PRIVILEGES - ALLOW_SETTINGS
     */
    ALLOW_SETTINGS(25);
    private int id;

    private NomPrivileges(int id) {
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
}
