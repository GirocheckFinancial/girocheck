/*
 ** File: NomMode.java
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
public enum NomMode {

    /**
     * MODE - TEST
     */
    TEST(1),
    /**
     * MODE - CREDIT
     */
    CREDIT(2),
    /**
     * MODE - DEBIT
     */
    DEBIT(3),
    /**
     * MODE - MOCK
     */
    MOCK(4),
    /**
     * MODE - CHECK
     */
    CHECK(5),
    /**
     * MODE - GIFT
     */
    GIFT(6),
    /**
     * MODE - EBT
     */
    EBT(7),
    /**
     * MODE - DIRECT_DEBIT
     */
    DIRECT_DEBIT(8);

    private NomMode(int id) {
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
            case TEST:
                return "TEST";
            case CREDIT:
                return "CREDIT";
            case DEBIT:
                return "DEBIT";
            case MOCK:
                return "MOCK";
            case CHECK:
                return "CHECK";
            case GIFT:
                return "GIFT";
            case EBT:
                return "EBT";
            case DIRECT_DEBIT:
                return "DIRECT_DEBIT";

            default:
                return "";
        }
    }
}
