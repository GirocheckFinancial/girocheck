/*
 ** File: MerchantManagementWindow.java
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
import com.smartbt.vtsuite.vtams.client.gui.window.tab.ClientTab;
import com.smartbt.vtsuite.vtams.client.gui.window.tab.RecurringTab;
import com.smartbt.vtsuite.vtams.client.gui.window.tab.SettingsTab;
import com.smartbt.vtsuite.vtams.client.gui.window.tab.TransactionTab;
import com.smartbt.vtsuite.vtams.client.gui.window.tab.WatchdogTab;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomUserPrivileges;
import com.smartgwt.client.data.Record;

/**
 * The Merchant Management Window
 *
 * @author Ariamnet Lopez, Ariel Saavedra
 */
public class MerchantManagementWindow extends BaseManagementWindow {

    /**
     * Constructor
     *
     * @param recordEntity
     */
    public MerchantManagementWindow(Record recordEntity) {
        super();

//        if (Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_MERCHANT_TRANSACTIONS)) {
            TransactionTab transactionTab = new TransactionTab(EntityType.MERCHANT, recordEntity.getAttributeAsInt("id"));
            addTab(transactionTab);
//        }

//        if (Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_MERCHANT_CUSTOMER)) {
//            ClientTab clientTab = new ClientTab(EntityType.MERCHANT, recordEntity.getAttributeAsInt("id"));
//            addTab(clientTab);
//        }
//        if (Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_MERCHANT_RECURRING)) {
//            RecurringTab recurringTab = new RecurringTab(recordEntity.getAttributeAsInt("id"));
//            addTab(recurringTab);
//            //TODO: Enable for future versions 
//            recurringTab.setDisabled(true);
//        }
//        if (Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_MERCHANT_SETTINGS)) {
            SettingsTab settingsTab = new SettingsTab(EntityType.MERCHANT, recordEntity);
            addTab(settingsTab);
//        }
//        if (Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_WATCHDOG)) {
            WatchdogTab watchdogTab = new WatchdogTab(EntityType.MERCHANT, recordEntity);
            addTab(watchdogTab);
//        }
    }
}
