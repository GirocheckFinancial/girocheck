/*
 ** File: RolePrivilegeDisplay.java
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
 * The Role Privilege Display Class - containing all sets/gets
 */
@XmlRootElement
public class RolePrivilegeDisplay implements Serializable{

    private Integer id;
    private RoleDisplay role;
    private PrivilegeDisplay privilege;

    /**
     * The default constructor
     */
    public RolePrivilegeDisplay() {
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
    public RoleDisplay getRole() {
        return role;
    }

    /**
     *
     * @param role
     */
    public void setRole(RoleDisplay role) {
        this.role = role;
    }

    /**
     *
     * @return
     */
    public PrivilegeDisplay getPrivilege() {
        return privilege;
    }

    /**
     *
     * @param privilege
     */
    public void setPrivilege(PrivilegeDisplay privilege) {
        this.privilege = privilege;
    }
}
