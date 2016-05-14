/*
 ** File: WatchdogAlertServerTab.java
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
package com.smartbt.vtsuite.vtams.client.gui.window.tab;


import com.smartbt.vtsuite.vtams.client.classes.Settings;
import com.smartbt.vtsuite.vtams.client.classes.i18n.I18N;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseTab;
import com.smartbt.vtsuite.vtams.client.gui.window.search.SearchWatchdogWindow;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomUserPrivileges;

/**
 * The WatchdogAlertServer Tab
 *
 * @author Ariel Saavedra
 */
public class WatchdogAlertServerTab extends BaseTab {

    /**
     * Constructor
     *
     */
    public WatchdogAlertServerTab() {
        super(I18N.GET.TAB_WATCHDOG_TITLE());
        mainVLayout.removeMember(filterLayout);
        mainVLayout.removeMember(listLayout);
        
//        if (Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_WATCHDOG)) {
            mainVLayout.addMember(new SearchWatchdogWindow());
//        }
    }
}
