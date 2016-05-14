/*
 ** File: NomApplication.java
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
public enum NomWatchdog {

    /**
     * TRANSACTION_MODE - Refund Transaction exceeded the amount specified
     */
    REFUNDS_AMOUNT(1),
    /**
     * TRANSACTION_MODE - Too many voids performed by a user
     */
    VOIDS(2),
    /**
     * Trigger a message, the message is used to inform of an activity
     */
    INFO(3),
    /**
     * User skipped CVV entry
     */
    CVV(4),
    /**
     * Too many Refunds performed by a user
     */
    REFUNDS_QUANTITY(5),
    /**
     * Too many Swipes
     */
    SWIPE(6),
    /**
     * User skipped electronic signature entry
     */
    SIGNATURE(7),
    /**
     * User performed transaction on same card and same amount
     */
    REPEAT(8),
    /**
     * User deleted a customer's account information
     */
    CARD_DELETED(9),
    /**
     * User reprint a receipt
     */
    REPRINT(10);

    private int id;

    private NomWatchdog(int id) {
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
}
