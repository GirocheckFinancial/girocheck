/*
 ** File: WatchdogManagementWindow.java
 **
 ** Date Created: December 2013
 **
 ** Copyright @ 2004-2014 Smart Business Technology, Inc.
 **
 ** All rights reserved. No part of this software may be 
 ** reproduced, transmitted, transcribed, stored in a retrieval 
 ** system, or translated into any language or computer language, 
 ** in any form or by any means, electronic, mechanical, magnetic, 
 ** optical, chemical, manual or otherwise, without the prior 
 ** written permission of Smart Business Technology, Inc.
 **
 */
package com.smartbt.vtsuite.vtams.client.gui.window.management;

import com.smartbt.vtsuite.vtams.client.classes.Settings;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseManagementWindow;
import com.smartbt.vtsuite.vtams.client.gui.window.tab.WatchdogAlertTab;
import com.smartbt.vtsuite.vtams.client.gui.window.tab.WatchdogEntityTab;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomUserPrivileges;
import com.smartgwt.client.data.Record;

/**
 * The Watchdog Management Window
 *
 * @author Ariamnet Lopez, Ariel Saavedra
 */
public class WatchdogManagementWindow extends BaseManagementWindow {

    /**
     * Constructor
     *
     * @param type the entity (could be a ENTERPRISE, Customer, Merchant,
     * Terminal)
     * @param recordEntity
     */
    public WatchdogManagementWindow(EntityType type, Record recordEntity) {
        super();
//        if (Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_WATCHDOG)) {
//            if (Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_WATCHDOG_RULES)) {
                addTab(new WatchdogEntityTab(type, recordEntity));
//            }
//            if (Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_WATCHDOG_ALERT)) {
                addTab(new WatchdogAlertTab(type, recordEntity.getAttributeAsInt("id")));
//            }
//        }
    }
}
