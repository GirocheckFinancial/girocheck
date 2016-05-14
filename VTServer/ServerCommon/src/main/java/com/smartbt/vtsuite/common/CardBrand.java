/*
 ** File: CardBrand.java
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
package com.smartbt.vtsuite.common;

/**
 * Card Brand Class
 */
public enum CardBrand {

    // TODO provide actual bin ranges
    /**
     *
     */
    VISA(1),
    /**
     *
     */
    MASTERCARD(2),
    /**
     *
     */
    AMEX(3),
    /**
     *
     */
    DISCOVER(4),
    /**
     *
     */
    DEBIT(0);

    /**
     *
     * @param pan
     * @return
     */
    public static String infer(long pan) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private Integer value;

    private CardBrand(Integer value) {
        this.value = value;
    }

    /**
     * Get Card Brand Value
     *
     * @return Value
     */
    public Integer getValue() {
        return value;
    }

    /**
     * Infer brand from account number
     *
     * @param pan Primary Account Number.
     * @return Numeric code representing the card brand.
     */
    public static CardBrand infer(String pan) {
        CardBrand inferredBrand;

        String digit1 = "0";

        if (pan != null) {

            digit1 = pan.substring(0, 1);

            // in case track1
            if (digit1.compareTo("B") == 0) {
                digit1 = pan.substring(1, 2);
            }
        }

        switch (Integer.parseInt(digit1)) {
            case 3:
                inferredBrand = CardBrand.AMEX;
                break;
            case 4:
                inferredBrand = CardBrand.VISA;
                break;
            case 5:
                inferredBrand = CardBrand.MASTERCARD;
                break;
            case 6:
                inferredBrand = CardBrand.DISCOVER;
                break;
            default:
                inferredBrand = CardBrand.DEBIT;
                break;
        }

        return inferredBrand;
    }
}
