/*
 ** File: WatchdogINFOProcessor.java
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

import static com.smartbt.vtsuite.servercommon.watchdog.manager.WatchdogManager.createWatchdogAlert;
import static com.smartbt.vtsuite.servercommon.watchdog.manager.WatchdogManager.sendWatchdogAlert;
import com.smartbt.vtsuite.servercommon.model.WatchdogEntity;
import com.smartbt.vtsuite.servercommon.watchdog.messages.WatchdogMessage;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomWatchdog;
import org.apache.log4j.Logger;

/**
 *
 * @author Ariel Saavedra
 */
public class WatchdogINFOProcessor {

    private static final Logger log = Logger.getLogger(WatchdogINFOProcessor.class);

    public static void proccess(WatchdogMessage message) {
        if(message.getWatchdogType() == NomWatchdog.INFO){
            WatchdogEntity watchdogEntity = (WatchdogEntity) message.getData();
             if (message.getClerk() != null) {
                    sendWatchdogAlert(
                            createWatchdogAlert(watchdogEntity, watchdogEntity.getValue()),
                            message.getClerk());
                } else {
                    sendWatchdogAlert(
                            createWatchdogAlert(watchdogEntity, watchdogEntity.getValue()),
                            message.getUser());
                }
        }                
    }
}
