/*
 ** File: WatchdogMessage.java
 **
 ** Date Created: December 2013
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
package com.smartbt.vtsuite.servercommon.watchdog.messages;

import com.smartbt.vtsuite.servercommon.model.Clerk;
import com.smartbt.vtsuite.servercommon.model.Terminal;
import com.smartbt.vtsuite.servercommon.model.User;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomWatchdog;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Ariel Saavedra
 */
public class WatchdogMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    private NomWatchdog watchdogType;
    private Clerk clerk;
    private User user;
    private Terminal terminal;
    private Object data;
    private List<Object> dataList;

    public Clerk getClerk() {
        return clerk;
    }

    public void setClerk(Clerk clerk) {
        this.clerk = clerk;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Terminal getTerminal() {
        return terminal;
    }

    public void setTerminal(Terminal terminal) {
        this.terminal = terminal;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public NomWatchdog getWatchdogType() {
        return watchdogType;
    }

    public void setWatchdogType(NomWatchdog watchdogType) {
        this.watchdogType = watchdogType;
    }

    public List<Object> getDataList() {
        return dataList;
    }

    public void setDataList(List<Object> dataList) {
        this.dataList = dataList;
    }
}
