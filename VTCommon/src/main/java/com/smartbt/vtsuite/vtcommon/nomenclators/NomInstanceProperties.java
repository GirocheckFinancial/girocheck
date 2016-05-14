/*
 ** File: NomInstanceProperties.java
 **
 ** Date Created: July 2013
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
 * Instance Properties
 */
public enum NomInstanceProperties {

    /**
     * INSTANCE_PROPERTY - LDAP_CONFIG
     */
    LDAP_CONFIG(1);

    private NomInstanceProperties(int id) {
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
            case LDAP_CONFIG:
                return "ldap_config";

            default:
                return "";
        }
    }
}
