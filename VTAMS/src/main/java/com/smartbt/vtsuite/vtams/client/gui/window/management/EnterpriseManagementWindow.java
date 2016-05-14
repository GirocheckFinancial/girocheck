/*
 ** File: EnterpriseManagementWindow.java
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
import com.smartbt.vtsuite.vtams.client.gui.window.tab.WatchdogTab;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomUserPrivileges;
import com.smartgwt.client.data.Record;

/**
 * The Enterprise Management Window
 *
 * @author Ariel Saavedra
 */
public class EnterpriseManagementWindow extends BaseManagementWindow {

    /**
     * Constructor
     *
     * @param recordEntity
     */
    public EnterpriseManagementWindow(Record recordEntity) {
        super();       
//        if (Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_WATCHDOG)) {
            WatchdogTab watchdogTab = new WatchdogTab(EntityType.ENTERPRISE,recordEntity);
            addTab(watchdogTab);
//        }
    }
}
