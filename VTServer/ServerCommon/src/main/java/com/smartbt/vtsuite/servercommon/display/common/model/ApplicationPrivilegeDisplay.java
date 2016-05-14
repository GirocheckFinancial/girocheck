/*
 ** File: PrivilegeDisplay.java
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

import com.smartbt.vtsuite.servercommon.model.Application;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Role Privilege Display Class - containing all sets/gets
 */
@XmlRootElement
public class ApplicationPrivilegeDisplay implements Serializable{

    private Integer id;
    private Application application;
    private PrivilegeDisplay clerkPrivilege;
    private String description;

    /**
     * The default constructor
     */
    public ApplicationPrivilegeDisplay() {
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
    public Application getApplication() {
        return application;
    }

    /**
     *
     * @param application
     */
    public void setApplication(Application application) {
        this.application = application;
    }

    /**
     *
     * @return
     */
    public PrivilegeDisplay getPrivilege() {
        return clerkPrivilege;
    }

    /**
     *
     * @param clerkPrivilege
     */
    public void setPrivilege(PrivilegeDisplay clerkPrivilege) {
        this.clerkPrivilege = clerkPrivilege;
    }
    
      /**
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
