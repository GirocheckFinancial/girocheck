/*
 ** File: NomApplication.java
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
package com.smartbt.vtsuite.vtcommon.nomenclators;

/**
 *
 * Nomenclature class
 */
public enum NomAddressType {

    /**
     * ADDRESS - OFFICE
     */
    OFFICE(1),
    /**
     * ADDRESS - PERSONAL
     */
    PERSONAL(2),
    /**
     * ADDRESS - BILLING
     */
    BILLING(3),
    /**
     * ADDRESS - SHIPING
     */
    SHIPING(4);
    private int id;

    private NomAddressType(int id) {
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
