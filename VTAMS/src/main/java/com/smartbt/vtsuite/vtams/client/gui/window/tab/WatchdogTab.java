/*
 ** File: WatchdogTab.java
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
package com.smartbt.vtsuite.vtams.client.gui.window.tab;

import com.smartbt.vtsuite.vtams.client.classes.i18n.I18N;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;

import com.smartbt.vtsuite.vtams.client.gui.base.BaseTab;
import com.smartbt.vtsuite.vtams.client.gui.window.management.WatchdogManagementWindow;
import com.smartgwt.client.data.Record;

/**
 * The Watchdog Tab
 *
 * @author Ariel Saavedra
 */
public class WatchdogTab extends BaseTab {

    /**
     * Constructor
     *
     * @param type
     * @param recordEntity
     */
    public WatchdogTab(EntityType type, Record recordEntity) {
        super(I18N.GET.TAB_WATCHDOG_TITLE());

        WatchdogManagementWindow watchdogManagementWindow = new WatchdogManagementWindow(type, recordEntity);

        mainVLayout.removeMember(filterLayout);
        mainVLayout.removeMember(listLayout);

        mainVLayout.addMember(watchdogManagementWindow);
    }
}
