/*
 ** File: UCRolePrivilegesDisplay.java
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
 * The UC Role Privileges Display Class - containing all sets/gets
 */
@XmlRootElement
public class UCRolePrivilegesDisplay implements Serializable{

    private Integer id;
    private RolePrivilegeDisplay privilege;

    /**
     * The default constructor
     */
    public UCRolePrivilegesDisplay() {
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
    public RolePrivilegeDisplay getPrivilege() {
        return privilege;
    }

    /**
     *
     * @param privilege
     */
    public void setPrivilege(RolePrivilegeDisplay privilege) {
        this.privilege = privilege;
    }
}
