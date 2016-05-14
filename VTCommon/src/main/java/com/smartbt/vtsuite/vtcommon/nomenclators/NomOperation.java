/*
 ** File: NomOperation.java
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
public enum NomOperation {

    /**
     * OPERATION - SALE
     */
    SALE(1),
    /**
     * OPERATION - REFUND
     */
    REFUND(2),
    /**
     * OPERATION - VOID
     */
    VOID(3),
    /**
     * OPERATION - AUTH
     */
    AUTH(4),
    /**
     * OPERATION - DELAY
     */
    DELAY(5),
    /**
     * OPERATION - QUICK
     */
    QUICK(6),
    /**
     * OPERATION - VERIFICATION
     */
    VERIFICATION(7),
    /**
     * OPERATION - GUARANTEE
     */
    GUARANTEE(8),
    /**
     * OPERATION - INQUIRY
     */
    INQUIRY(9),
    /**
     * OPERATION - VOID_VERIFICATION
     */
    VOID_VERIFICATION(10),
    /**
     * OPERATION - VOID_GUARANTEE
     */
    VOID_GUARANTEE(11),
    /**
     * OPERATION - FORCE
     */
    FORCE(12),
    /**
     * OPERATION - REDEMPTION
     */
    REDEMPTION(13),
    /**
     * OPERATION - ADD_VALUE
     */
    ADD_VALUE(14),
    /**
     * OPERATION - ACTIVATION
     */
    ACTIVATION(15),
    /**
     * OPERATION - VOID_ACTIVATION
     */
    VOID_ACTIVATION(16),
    /**
     * OPERATION - VOID_SALE
     */
    VOID_SALE(17),
    /**
     * OPERATION - BALANCE_INQUIRY
     */
    BALANCE_INQUIRY(18),
     /**
     * OPERATION - ADJUST
     */
    ADJUST(19),
    /**
     * OPERATION - REVERSAL
     */
    //just for server
    REVERSAL(-1);

    private NomOperation(int id) {
        this.id = id;
    }
    private int id;

    /**
     * Get id
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * getViewValue
     *
     * @return the value
     */
    public String getViewValue() {
        switch (this) {
            case REFUND:
                return "REFUND";
            case VOID:
                return "VOID";
            case SALE:
                return "SALE";
            case AUTH:
                return "AUTH";
            case DELAY:
                return "DELAY";
            case QUICK:
                return "QUICK";
            case VERIFICATION:
                return "VERIFICATION";
            case GUARANTEE:
                return "GUARANTEE";
            case INQUIRY:
                return "INQUIRY";
            case VOID_VERIFICATION:
                return "VOID_VERIFICATION";
            case VOID_GUARANTEE:
                return "VOID_GUARANTEE";
            case FORCE:
                return "FORCE";
            default:
                return "";
        }
    }
}
