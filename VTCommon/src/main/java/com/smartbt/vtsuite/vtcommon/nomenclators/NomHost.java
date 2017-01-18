/*
 ** File: NomHost.java
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
public enum NomHost {

    /**
     * HOST - IStream
     */
    ISTREAM(1),
    /**
     * HOST - TECNICARD
     */
    TECNICARD(2),
    /**
     * HOST - ORDEREXPRESS
     */
    ORDER_EXPRESS(3),
    /**
     * HOST - FUZE
     */
    FUZE(4),
    
    CHOICE(5),
    
    WESTECH(6),
    
    CERTEGY(7),
    
    ISTREAM2(8);

    private NomHost(int id) {
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
    
    //TODO this is not needed  enum.toString gives this result
    public String getViewValue() {
        switch (this) {
            case ISTREAM:
                return "ISTREAM";
            case TECNICARD:
                return "TECNICARD";
            case ORDER_EXPRESS:
                return "ORDER_EXPRESS";
            case FUZE:
                return "FUZE";
            case CHOICE:
                return "CHOICE";
            default:
                return "";
        }
    }
}
