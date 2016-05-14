/*
 ** File: RolesWindow.java
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

import com.smartbt.vtsuite.vtams.client.gui.base.BaseEntityTabSetWindow;
import com.smartbt.vtsuite.vtams.client.gui.window.tab.RolesTab;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;

/**
 * The Boarding Platform Window
 *
 * @author Ariel Saavedra
 */
public class RolesWindow extends BaseEntityTabSetWindow {

    /**
     * Constructor
     *
     */
    public RolesWindow() {
        super();
        RolesTab rolesTab = new RolesTab(EntityType.CLERK);       
        managementWindow.addTab(rolesTab);
    }
}
