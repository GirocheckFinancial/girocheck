/*
 ** File: NomClerkRole.java
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
public enum NomClerkRole {

    /**
     * CLERK_ROLE - ADMIN
     */
    VT_ADMIN(1),
    /**
     * CLERK_ROLE - MANAGER
     */
    VT_MANAGER(2),
    /**
     * CLERK_ROLE - TRANSACTION
     */
    VT_NO_TRANSACTION(3);

    private NomClerkRole(int id) {
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
            case VT_ADMIN:
                return "VT_ADMIN";

            case VT_MANAGER:
                return "VT_MANAGER";

            case VT_NO_TRANSACTION:
                return "VT_NO_TRANSACTION";

            default:
                return "";
        }
    }
}
