/*
 ** File: SettingsManagementWindow.java
 **
 ** Date Created: May 2013
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
import com.smartbt.vtsuite.vtams.client.gui.window.tab.parameter.EntityParameterValueTab;
import com.smartbt.vtsuite.vtams.client.gui.window.tab.UserTab;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomUserPrivileges;
import com.smartgwt.client.data.Record;

/**
 * The Settings Management Window
 *
 * @author Ariamnet Lopez, Ariel Saavedra
 */
public class SettingsManagementWindow extends BaseManagementWindow {

    /**
     * Constructor
     *
     * @param type
     * @param recordEntity
     */
    public SettingsManagementWindow(EntityType type, Record recordEntity) {
        super();

        switch (type) {
            case CUSTOMER: {
//                if (Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_MERCHANT_SETTINGS_USERS)) {
                    addTab(new UserTab(type, recordEntity));
//                }
                break;
            }
            case MERCHANT:
            case TERMINAL: {
//                if (Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_MERCHANT_SETTINGS_PARAMETERS)) {
                    addTab(new EntityParameterValueTab(type, recordEntity.getAttributeAsInt("id")));
//                }
//                if (Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_MERCHANT_SETTINGS_USERS)) {
                    addTab(new UserTab(type, recordEntity));
//                }
                break;
            }
        }
    }
}
