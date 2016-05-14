/*
 ** File: WatchdogManager.java
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
package com.smartbt.vtsuite.servercommon.watchdog.manager;

import com.smartbt.vtsuite.servercommon.watchdog.processor.WatchdogGeneralProcessor;
import com.smartbt.vtsuite.servercommon.watchdog.processor.WatchdogINFOProcessor;
import com.smartbt.vtsuite.servercommon.watchdog.processor.WatchdogTransactionProcessor;
import com.smartbt.vtsuite.servercommon.dao.ClerkDAO;
import com.smartbt.vtsuite.servercommon.dao.UserDAO;
import com.smartbt.vtsuite.servercommon.dao.WatchdogDAO;
import com.smartbt.vtsuite.servercommon.model.Clerk;
import com.smartbt.vtsuite.servercommon.model.User;
import com.smartbt.vtsuite.servercommon.model.WatchdogAlert;
import com.smartbt.vtsuite.servercommon.model.WatchdogAlertClerk;
import com.smartbt.vtsuite.servercommon.model.WatchdogEntity;
import com.smartbt.vtsuite.servercommon.model.WatchdogEntityClerk;
import com.smartbt.vtsuite.servercommon.watchdog.messages.WatchdogMessage;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * The Watchdog Manager class
 */
public class WatchdogManager {

    private static final Logger log = Logger.getLogger(WatchdogManager.class);

    /**
     * The default constructor
     */
    public WatchdogManager() {
    }

    public void processWatchdog(Serializable object) throws Exception {
        if (object instanceof WatchdogMessage) {
            WatchdogMessage watchdogMessage = (WatchdogMessage) object;

            if (watchdogMessage.getWatchdogType() != null) {
                switch (watchdogMessage.getWatchdogType()) {
                    case INFO:
                        WatchdogINFOProcessor.proccess(watchdogMessage);
                        break;
                    case CARD_DELETED:
                        WatchdogGeneralProcessor.proccess(watchdogMessage);
                        break;
                }
            } else {
                WatchdogTransactionProcessor.proccess(watchdogMessage);
            }
        }
    }

    public static WatchdogAlert createWatchdogAlert(WatchdogEntity watchdogEntity, String description) {
        WatchdogAlert watchdogAlert = new WatchdogAlert();

        Date createAt = new Date();
        List<WatchdogAlertClerk> watchdogAlertClerksDestination = new LinkedList<WatchdogAlertClerk>();

        for (WatchdogEntityClerk watchdogEntityClerk : watchdogEntity.getWatchdogEntityClerk()) {
            WatchdogAlertClerk clerkDestination = new WatchdogAlertClerk();
            clerkDestination.setClerk(watchdogEntityClerk.getClerk());
            clerkDestination.setWatchdogAlert(watchdogAlert);
            watchdogAlertClerksDestination.add(clerkDestination);
        }

        watchdogAlert.setWatchdog(watchdogEntity.getWatchdog());

        watchdogAlert.setWatchdogAlertClerk(watchdogAlertClerksDestination);
        watchdogAlert.setValue(description);
        watchdogAlert.setCreatedAt(createAt);
        return watchdogAlert;
    }

    public static void sendWatchdogAlert(WatchdogAlert watchdogAlert, User userOrigination) {
        watchdogAlert.setUserOrigination(UserDAO.get().findById(userOrigination.getId()));
        WatchdogDAO.get().addWatchdogAlert(watchdogAlert);

        log.info("[Watchdogs] Sending watchdog " + watchdogAlert.getWatchdog().getName() + " to " + watchdogAlert.getWatchdogAlertClerk().size() + " clerks.");
    }

    public static void sendWatchdogAlert(WatchdogAlert watchdogAlert, Clerk clerkOrigination) {
        watchdogAlert.setClerkOrigination(ClerkDAO.get().findById(clerkOrigination.getId()));
        WatchdogDAO.get().addWatchdogAlert(watchdogAlert);

        log.info("[Watchdogs] Sending watchdog " + watchdogAlert.getWatchdog().getName() + " to " + watchdogAlert.getWatchdogAlertClerk().size() + " clerks.");
    }
}
