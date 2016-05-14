/*
 ** File: NomClerkPrivileges.java
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
 *
 * @author Carlos
 */
public enum NomClerkPrivileges {

    /**
     *
     */
    ALLOW_CARD_PAYMENT(1),
    /**
     *
     */
    ALLOW_CARD_PAYMENT_MANUAL(2),
    /**
     *
     */
    ALLOW_MY_TRANSACTIONS(8),
    /**
     *
     */
    ALLOW_MY_CUSTOMER(9),
    /**
     *
     */
    ALLOW_DASHBOARD(5),
    /**
     *
     */
    ALLOW_REFUND(3),
    /**
     *
     */
    ALLOW_REFUND_MANUAL(4),
    /**
     *
     */
    ALLOW_CHANGE_SETTINGS(7),
    /**
     *
     */
    ALLOW_VOID(6);
    private int id;

    private NomClerkPrivileges(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @return
     */
    public String getIdAsString() {
        return id + "";
    }
}
