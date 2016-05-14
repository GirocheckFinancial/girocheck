/*
 ** File: WatchdogGeneralProcessor.java
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
package com.smartbt.vtsuite.servercommon.watchdog.processor;

import com.smartbt.vtsuite.servercommon.watchdog.manager.WatchdogManager;
import com.smartbt.vtsuite.servercommon.dao.WatchdogDAO;
import com.smartbt.vtsuite.servercommon.model.WatchdogEntity;
import com.smartbt.vtsuite.servercommon.watchdog.messages.WatchdogMessage;
import com.smartbt.vtsuite.servercommon.watchdog.utils.MessageDescription;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomWatchdog;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Ariel Saavedra
 */
public class WatchdogGeneralProcessor {

    private static final Logger log = Logger.getLogger(WatchdogGeneralProcessor.class);

    public static void proccess(WatchdogMessage message) {
        List<WatchdogEntity> watchdogEntities
                = WatchdogDAO.get().getWatchdogEntitiesByEntity(EntityType.TERMINAL, message.getTerminal().getId());

        for (WatchdogEntity watchdogEntity : watchdogEntities) {
            //Check CARD DELETED -------------------------------------------------------------------------------------------------------------
            if (watchdogEntity.getWatchdog().getId() == NomWatchdog.CARD_DELETED.getId()) {

                WatchdogManager.sendWatchdogAlert(
                        WatchdogManager.createWatchdogAlert(watchdogEntity,
                                MessageDescription.cardDeletedMsg()),
                        message.getClerk());

            }

        }
    }
}
