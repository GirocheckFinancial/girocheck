/*
 ** File: NomEntryMethod.java
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
public enum NomEntryMethod {

    /**
     * ENTRY_METHOD - MANUAL
     */
    MANUAL(0),
    /**
     * ENTRY_METHOD - SWIPE
     */
    SWIPE(2);
    private int id;

    private NomEntryMethod(int id) {
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
     * getTextFromId
     *
     * @return the value
     */
    public static String getTextFromId(int id) {
        switch (id) {
            case 0:
                return "Manual";
            case 2:
                return "Swipe";

            default:
                return "Unknown";
        }
    }
}
