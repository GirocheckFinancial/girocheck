/*
 ** File: AMSSettingDisplay.java
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
package com.smartbt.vtsuite.servercommon.display.ams;

import java.io.Serializable;
import java.util.Map;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The AMSSettingDisplay Class - containing all sets/gets
 */
@XmlRootElement
public class AMSSettingDisplay implements Serializable {

    private Map parameters;
    private Map rolePrivileges;

    /**
     * THe default constructor
     */
    public AMSSettingDisplay() {
    }

    /**
     *
     * @return
     */
    public Map getParameters() {
        return parameters;
    }

    /**
     *
     * @param parameters
     */
    public void setParameters(Map parameters) {
        this.parameters = parameters;
    }

    /**
     *
     * @return
     */
    public Map getRolePrivileges() {
        return rolePrivileges;
    }

    /**
     *
     * @param rolePrivileges
     */
    public void setRolePrivileges(Map rolePrivileges) {
        this.rolePrivileges = rolePrivileges;
    }
}
