/*
 ** File: NomMerchantHostParameter.java
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
public enum NomMerchantHostParameter {

    /**
     * MERCHANT_HOST_PARAMETER - WORLDPAY_MERCHANT_NUMBER
     */
    WORLDPAY_MERCHANT_NUMBER(1);

    private NomMerchantHostParameter(int id) {
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
            case WORLDPAY_MERCHANT_NUMBER:
                return "WORLDPAY_MERCHANT_NUMBER";

            default:
                return "";
        }
    }
}
