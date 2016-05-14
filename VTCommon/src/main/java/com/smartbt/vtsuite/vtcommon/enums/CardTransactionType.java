/*
 ** File: CardTransactionType.java
 **
 ** Date Created: October 2013
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
package com.smartbt.vtsuite.vtcommon.enums;

/**
 * The CardTransactionType class
 *
 * @author Ariamnet Lopez
 */
public enum CardTransactionType {

    /**
     * CREDIT_SALE
     */
    CREDIT_SALE(1, "Credit", "Sale"),
    /**
     * DEBIT_SALE
     */
    DEBIT_SALE(2, "Debit", "Sale"),
    /**
     * CREDIT_REFUND
     */
    CREDIT_REFUND(3, "Credit", "Refund"),
    /**
     * DEBIT_INQUIRY
     */
    DEBIT_INQUIRY(4, "Debit", "Inquiry"),
    /**
     * CREDIT_FORCE
     */
    CREDIT_FORCE(5, "Credit", "Force"),
    /**
     * DEBIT_REFUND
     */
    DEBIT_REFUND(6, "Debit", "Refund"),
    /**
     * TIP_ADJUSTMENT
     */
    TIP_ADJUSTMENT(7, "Credit", "Adjustment");
    
    private int id;
    private String mode;
    private String operation;

    CardTransactionType(int id, String mode, String operation) {
        this.id = id;
        this.mode = mode;
        this.operation = operation;
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
     * Get mode
     *
     * @return the mode
     */
    public String getMode() {
        return this.mode;
    }

    /**
     * Get operation
     *
     * @return the operation
     */
    public String getOperation() {
        return this.operation;
    }
}
