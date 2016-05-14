/*
 ** File: DeviceDisplay.java
 **
 ** Date Created: February 2013
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
package com.smartbt.vtsuite.servercommon.display.common.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Address Display Class - containing all sets/gets
 */
@XmlRootElement
public class PhoneTypeDisplay implements Serializable {
    
    /**
     *
     */
    public PhoneTypeDisplay() {
    }

    private int id;
    private String name;
    private String description;
    
    /**
     *
     * @param value
     */
    public void setId(int value) {
        this.id = value;
    }
    
    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }
    
    /**
     *
     * @param value
     */
    public void setName(String value) {
        this.name = value;
    }
    
    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }
    
    /**
     *
     * @param value
     */
    public void setDescription(String value) {
        this.description = value;
    }
    
    /**
     *
     * @return
     */
    public String getDescription() {
        return description;
    }
}
