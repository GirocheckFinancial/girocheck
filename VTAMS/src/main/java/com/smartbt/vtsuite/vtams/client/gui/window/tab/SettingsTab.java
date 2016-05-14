/*
 ** File: SettingsTab.java
 **
 ** Date Created: April 2013
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
import com.smartbt.vtsuite.vtams.client.gui.window.management.SettingsManagementWindow;
import com.smartgwt.client.data.Record;

/**
 * The Settings Tab
 *
 * @author Ariamnet Lopez
 */
public class SettingsTab extends BaseTab {

    /**
     * Constructor
     *
     * @param type
     * @param recordEntity
     */
    public SettingsTab(EntityType type, Record recordEntity) {
        super(I18N.GET.TAB_SETTINGS_TITLE());

        SettingsManagementWindow settingsManagementWindow = new SettingsManagementWindow(type, recordEntity);

        mainVLayout.removeMember(filterLayout); 
        mainVLayout.removeMember(listLayout);
        
        mainVLayout.addMember(settingsManagementWindow);
    }
}
