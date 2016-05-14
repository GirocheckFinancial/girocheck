/*
 ** File: PrivilegeType.java
 **
 ** Date Created: December 2013
 **
 ** Copyright @ 2004-2014 Smart Business Technology, Inc.
 **
 ** All rights reserved. No part of this software may be 
 ** reproduced, transmitted, transcribed, stored in a retrieval 
 ** system, or translated into any language or computer language, 
 ** in any form or by any means, electronic, mechanical, magnetic, 
 ** optical, chemical, manual or otherwise, without the prior 
 ** written permission of Smart Business Technology, Inc.
 **
 */
package com.smartbt.vtsuite.vtams.client.classes.enums;

/**
 * The PrivilegeType class
 *
 * @author Ariel Saavedra
 */
public enum PrivilegeType {

    /**
     * Root node identifier
     */
    ALLOW_MERCHANT_SERVICES("allowMerchantServices"),
    /**
     * Customer node identifier
     */
    ALLOW_MERCHANT_TRANSACTIONS("allowMerchantTransactions"),
    /**
     * Merchant node identifier
     */
    ALLOW_MERCHANT_USERS("allowMerchantUsers");

    private String value;

    PrivilegeType(String value) {
        this.value = value;
    }

    /**
     * Get value
     *
     * @return the value
     */
    public String getValue() {
        return this.value;
    }
}
