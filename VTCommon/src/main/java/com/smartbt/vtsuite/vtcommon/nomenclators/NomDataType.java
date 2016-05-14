/*
 ** File: NomDataType.java
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
 * author Ariel Saavedra
 */
public enum NomDataType {
    /**
     * DATA_TYPE - STRING
     */
    STRING(1),
     /**
     * DATA_TYPE - INTEGER
     */
    INTEGER(2),
     /**
     * DATA_TYPE - BOOLEAN
     */
    BOOLEAN(3),
    /**
     * DATA_TYPE - DATE
     */
    DATE(4),
        /**
     * DATA_TYPE - DOUBLE
     */
    DOUBLE(5),
    /**
     * DATA_TYPE - EMAIL
     */
    EMAIL(6);
    private int id;

    private NomDataType(int id) {
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
     * getDataTypeById
     *
     * @return the dataType
     */
    public static NomDataType getDataTypeById(int id) {
        switch (id) {
            case 1:
                return STRING;
            case 2:
                return INTEGER;
            case 3:
                return BOOLEAN;
            case 4:
                return DATE;
            case 5:
                return DOUBLE;
            case 6:
                return EMAIL;
            default:
                return null;
        }
    }
}
