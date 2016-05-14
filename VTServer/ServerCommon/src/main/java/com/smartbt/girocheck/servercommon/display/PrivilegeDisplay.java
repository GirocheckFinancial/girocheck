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
package com.smartbt.girocheck.servercommon.display;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Role Privilege Display Class - containing all sets/gets
 */
@XmlRootElement
public class PrivilegeDisplay implements Serializable{

    private Integer id;
    private String name;
    private String description;
//    private List<ApplicationPrivilegeDisplay> applicationClerkPrivilege;

    /**
     * The default constructor
     */
    public PrivilegeDisplay() {
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
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
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
    /**
     *
     * @return
     */
//    public List<ApplicationPrivilegeDisplay> getApplicationClerkPrivilege() {
//        return applicationClerkPrivilege;
//    }
//
//    /**
//     *
//     * @param applicationClerkPrivilege
//     */
//    public void setApplicationClerkPrivilege(List<ApplicationPrivilegeDisplay> applicationClerkPrivilege) {
//        this.applicationClerkPrivilege = applicationClerkPrivilege;
//    }
}
