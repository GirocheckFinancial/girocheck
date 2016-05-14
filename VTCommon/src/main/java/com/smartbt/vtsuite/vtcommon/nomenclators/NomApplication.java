/*
 ** File: NomApplication.java
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
public enum NomApplication {

    /**
     * APPLICATION - MOBILE
     */
    VT_MOBILE(1),
    /**
     * APPLICATION - AMS
     */
    VT_AMS(2),
    /**
     * APPLICATION - APPLICATION
     */
    VT_APPLICATION(3);
    private int id;

    private NomApplication(int id) {
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
