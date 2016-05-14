/*
 ** File: NomCardBrand.java
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
package com.smartbt.vtsuite.vtcommon.nomenclators;

/**
 * Nomenclature class
 *
 */
public enum NomCardBrand {

    // TODO provide actual bin ranges
    /**
     * CARD - VISA
     */
    VISA(1),
    /**
     * CARD - MASTERCARD
     */
    MASTERCARD(2),
    /**
     * CARD - AMEX
     */
    AMEX(3),
    /**
     * CARD - DISCOVER
     */
    DISCOVER(4),
    /**
     * CARD - DEBIT
     */
    DEBIT(5);
    private int id;

    private NomCardBrand(Integer id) {
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
     * getShortText
     *
     * @return the abbreviation
     */
    public String getShortText() {

        if (this.toString().equals(VISA.toString())) {
            return "VISA";
        }
        if (this.toString().equals(MASTERCARD.toString())) {
            return "MC";
        }
        if (this.toString().equals(AMEX.toString())) {
            return "AMEX";
        }
        if (this.toString().equals(DISCOVER.toString())) {
            return "DSC";
        }
        return "DEBIT";
    }

    /**
     * infer
     *
     * @param pan
     * @return the CardBrand
     */
    public static NomCardBrand infer(String pan) {
        NomCardBrand inferredBrand;

        String digit1 = "0";

        if (pan != null) {
            digit1 = pan.substring(0, 1);

            // in case track1
            if (digit1.compareTo("B") == 0) {
                digit1 = pan.substring(1, 2);
            }
        }

        switch (Integer.parseInt(digit1)) {
            case 0:
                inferredBrand = NomCardBrand.VISA;
                break;
            case 3:
                inferredBrand = NomCardBrand.AMEX;
                break;
            case 4:
                inferredBrand = NomCardBrand.VISA;
                break;
            case 5:
                inferredBrand = NomCardBrand.MASTERCARD;
                break;
            case 6:
                inferredBrand = NomCardBrand.DISCOVER;
                break;
            default:
                inferredBrand = NomCardBrand.DEBIT;
                break;
        }

        return inferredBrand;
    }
}
