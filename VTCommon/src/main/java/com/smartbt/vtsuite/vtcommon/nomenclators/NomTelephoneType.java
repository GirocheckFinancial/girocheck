/*
 ** File: NomTelephoneType.java
 **
 ** Date Created: November 2013
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
 * @author Aril Saavedra
 */
public enum NomTelephoneType {

    /**
     * TELEPHONE_TYPE - OFFICE
     */
    OFFICE(1),
    /**
     * TELEPHONE_TYPE - MOBILE
     */
    MOBILE(2),
    /**
     * TELEPHONE_TYPE - HOME
     */
    HOME(3),
    /**
     * TELEPHONE_TYPE - FAX
     */
    FAX(4);
    private int id;

    private NomTelephoneType(int id) {
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
