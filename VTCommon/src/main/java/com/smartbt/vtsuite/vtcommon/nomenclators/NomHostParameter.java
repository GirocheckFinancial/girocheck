/*
 ** File: NomHostParameter.java
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
 * @author Maite Gonzalez
 */
public enum NomHostParameter {

    /**
     * HOST_PARAMETER - HOST_IP
     */
    HOST_IP(1),
    /**
     * HOST_PARAMETER - HOST_PORT
     */
    HOST_PORT(2),
    /**
     * HOST_PARAMETER - HOST_SSL
     */
    HOST_SSL(3);

    private NomHostParameter(int id) {
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
     * Get id
     *
     * @return the value
     */
    public String getViewValue() {
        switch (this) {
            case HOST_IP:
                return "HOST_IP";

            case HOST_PORT:
                return "HOST_PORT";

            case HOST_SSL:
                return "HOST_SSL";

            default:
                return "";
        }
    }
}
