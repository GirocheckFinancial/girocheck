/*
 ** File: NodeType.java
 **
 ** Date Created: May 2013
 **
 ** Copyright @ 2004-2014 Smart Business Technology, Inc.
 **
 ** All rights reserved. No part of this software may be 
 ** reproduced, transmitted, transcribed, stored in a retrieval 
 ** system, or translated into any language or computer language, 
 ** in any form or by any means, electronic, mechanical, magnetic, 
 ** optical, chemical, manual or otherwise, without the prior 
 ** written permission of Smart Business Technology, Inc.
 **
 */
package com.smartbt.vtsuite.vtams.client.classes.enums;

/**
 * The NodeType class
 *
 * @author Ariamnet Lopez
 */
public enum NodeType {

    /**
     * Root node identifier
     */
    ROOT("root"),
    /**
     * Customer node identifier
     */
    CUSTOMER("customer"),
    /**
     * Merchant node identifier
     */
    MERCHANT("merchant"),
    /**
     * Terminal node identifier
     */
    TERMINAL("terminal"),
    /**
     * Device node identifier
     */
    DEVICE("device");
    
    private String value;

    NodeType(String value) {
        this.value = value;
    }

    /**
     * Get value
     *
     * @return the value
     */
    public String getValue() {
        return this.value;
    }
}
