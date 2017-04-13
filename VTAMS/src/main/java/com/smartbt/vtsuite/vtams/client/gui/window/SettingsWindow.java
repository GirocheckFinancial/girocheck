/*
 ** File: SettingsWindow.java
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
package com.smartbt.vtsuite.vtams.client.gui.window;

import com.smartbt.vtsuite.vtams.client.classes.Settings;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseEntityTabSetWindow;
import com.smartbt.vtsuite.vtams.client.gui.window.tab.ClientTab;
import com.smartbt.vtsuite.vtams.client.gui.window.tab.RolesTab;
import com.smartbt.vtsuite.vtams.client.gui.window.tab.UserTab;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomUserPrivileges;

/**
 * The Settings Window
 *
 * @author Ariamnet Lopez, Ariel Saavedra, Alejo
 */
public class SettingsWindow extends BaseEntityTabSetWindow {

    /**
     * Constructor
     *
     */
    public SettingsWindow() {
        super();
//        if (Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_SETTINGS_PARAMETERS)) {
//            EntityParameterValueTab parameterTab = new EntityParameterValueTab(EntityType.AMS, NomApplication.VT_AMS.getId());
//            managementWindow.addTab(parameterTab);
//        }
        if (Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_ADMINISTRATION_USERS)) {
            UserTab userTab = new UserTab(EntityType.AMS, null);
            managementWindow.addTab(userTab);
        }
        if (Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_ADMINISTRATION_ROLES)) { // Utils.debug( "before loadRecord" );
            RolesTab privilegeTab = new RolesTab(EntityType.AMS);
            managementWindow.addTab(privilegeTab);
        }

        if (Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_ADMINISTRATION)) { // Utils.debug( "before loadRecord" );
            ClientTab clientTab = new ClientTab(EntityType.AMS);
            managementWindow.addTab(clientTab);
        }
    }
}
