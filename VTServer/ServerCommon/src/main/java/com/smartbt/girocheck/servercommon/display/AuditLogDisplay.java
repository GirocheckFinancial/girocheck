/*
 ** File: AuditLog.java
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
package com.smartbt.girocheck.servercommon.display;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ariel Saavedra
 */
@XmlRootElement
public class AuditLogDisplay implements Serializable {

    private int id;
    private UserDisplay user;
    private String information;
    private Date createdAt;
    
//    private TerminalDisplay terminal;
//    private MerchantDisplay merchant;

    /**
     *
     */
    public AuditLogDisplay() {
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
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public UserDisplay getUser() {
        return user;
    }

    /**
     *
     * @param user
     */
    public void setUser(UserDisplay user) {
        this.user = user;
    }

    /**
     *
     * @return
     */
    public String getInformation() {
        return information;
    }

    /**
     *
     * @param information
     */
    public void setInformation(String information) {
        this.information = information;
    }

    /**
     *
     * @return
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     *
     * @param createdAt
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

//    /**
//     *
//     * @return
//     */
//    public TerminalDisplay getTerminal() {
//        return terminal;
//    }
//
//    /**
//     *
//     * @param terminal
//     */
//    public void setTerminal(TerminalDisplay terminal) {
//        this.terminal = terminal;
//    }
//
//    /**
//     *
//     * @return
//     */
//    public MerchantDisplay getMerchant() {
//        return merchant;
//    }
//
//    /**
//     *
//     * @param merchant
//     */
//    public void setMerchant(MerchantDisplay merchant) {
//        this.merchant = merchant;
//    }
}
