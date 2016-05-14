/*
 ** File: BoardingPlatformWindow.java
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
import com.smartbt.vtsuite.vtams.client.classes.enums.ParameterType;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseEntityTabSetWindow;
import com.smartbt.vtsuite.vtams.client.gui.window.tab.BoardingManagmentTab;
import com.smartbt.vtsuite.vtams.client.gui.window.tab.parameter.ApplicationParameterTab;
import com.smartbt.vtsuite.vtams.client.gui.window.tab.parameter.DefaultParametersTab;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomUserPrivileges;

/**
 * The Boarding Platform Window
 *
 * @author Ariamnet Lopez, Ariel Saavedra
 */
public class BoardingPlatformWindow extends BaseEntityTabSetWindow {

    /**
     * Constructor
     *
     */
    public BoardingPlatformWindow() {
        super();

        if (Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_BOARDING_MANAGEMENT)) {
        managementWindow.addTab(new BoardingManagmentTab());
        }
//        if (Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_BOARDING_ROLES)) {
//            managementWindow.addTab(new RolesTab(EntityType.CLERK));
//        }
//
        if (Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_BOARDING_APLICATION)) {
           managementWindow.addTab(new ApplicationParameterTab());
        }
    }
}
