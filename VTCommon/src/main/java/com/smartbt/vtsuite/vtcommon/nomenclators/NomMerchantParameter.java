/*
 ** File: NomMerchantParameter.java
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
public enum NomMerchantParameter {

    /**
     * MERCHANT_PARAMETER - HEADER_LINE_1
     */
    HEADER_LINE_1(1),
    /**
     * MERCHANT_PARAMETER - HEADER_LINE_2
     */
    HEADER_LINE_2(2),
    /**
     * MERCHANT_PARAMETER - HEADER_LINE_3
     */
    HEADER_LINE_3(3),
    /**
     * MERCHANT_PARAMETER - HEADER_LINE_4
     */
    HEADER_LINE_4(4),
    /**
     * MERCHANT_PARAMETER - HEADER_LINE_5
     */
    HEADER_LINE_5(5),
    /**
     * MERCHANT_PARAMETER - PROMISSORY_VERBIAGE_1
     */
    PROMISSORY_VERBIAGE_1(6),
    /**
     * MERCHANT_PARAMETER - PROMISSORY_VERBIAGE_2
     */
    PROMISSORY_VERBIAGE_2(7),
    /**
     * MERCHANT_PARAMETER - PROMISSORY_VERBIAGE_3
     */
    PROMISSORY_VERBIAGE_3(8),
    /**
     * MERCHANT_PARAMETER - IS_RESTAURANT
     */
    IS_RESTAURANT(9),
    /**
     * MERCHANT_PARAMETER - GRATUITY_RATE_1
     */
    GRATUITY_RATE_1(10),
    /**
     * MERCHANT_PARAMETER - GRATUITY_RATE_2
     */
    GRATUITY_RATE_2(11),
    /**
     * MERCHANT_PARAMETER - GRATUITY_RATE_3
     */
    GRATUITY_RATE_3(12);
    private int id;

    private NomMerchantParameter(int id) {
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
