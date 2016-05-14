/*
 ** File: ParameterValueDisplay.java
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

/**
 * The Parameter Value Class - containing all sets/gets
 */
public class ParameterValueDisplay implements Serializable{

    private Integer id;
    private String value;
    private ParameterDisplay parameter;

    /**
     * The default constructor
     */
    public ParameterValueDisplay() {
    }

    /**
     *
     * @return
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getValue() {
        return value;
    }

    /**
     *
     * @param value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     *
     * @return
     */
    public ParameterDisplay getParameter() {
        return parameter;
    }

    /**
     *
     * @param parameter
     */
    public void setParameter(ParameterDisplay parameter) {
        this.parameter = parameter;
    }
}
