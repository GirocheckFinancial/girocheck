/*
 ** File: AuditWorkshopWindow.java
 **
 ** Date Created: October 2013
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
import com.smartbt.vtsuite.vtams.client.gui.window.tab.AuditEntityTab;
import com.smartbt.vtsuite.vtams.client.gui.window.tab.AuditMerchantTab;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomApplication;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomUserPrivileges;

/**
 * The Boarding Platform Window
 *
 * @author Ariel Saavedra
 */
public class AuditWorkshopWindow extends BaseEntityTabSetWindow {

    /**
     * Constructor
     *
     */
    public AuditWorkshopWindow() {
        super();
//        if (Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_AUDIT_MERCHANT)) {
            AuditMerchantTab auiAuditMerchantTab = new AuditMerchantTab();
            managementWindow.addTab(auiAuditMerchantTab);
//        }
//        if (Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_AUDIT_AMS)) {
            AuditEntityTab auditEntityTab = new AuditEntityTab(EntityType.AMS, NomApplication.VT_AMS.getId());
            managementWindow.addTab(auditEntityTab);
//        }
    }
}
