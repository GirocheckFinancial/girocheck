/*
 ** File: DefaultParamTab.java
 **
 ** Date Created: January 2014
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
package com.smartbt.vtsuite.vtams.client.gui.window.tab.parameter;


import com.smartbt.vtsuite.vtams.client.classes.i18n.I18N;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseTab;
import com.smartbt.vtsuite.vtams.client.gui.window.management.DefaultParametersManagementWindow;

/**
 * The Watchdog Tab
 *
 * @author Ariel Saavedra
 */
public class DefaultParametersTab extends BaseTab {

    /**
     * Constructor
     *
     */
    public DefaultParametersTab() {
        super(I18N.GET.TAB_APPLICATION_PARAMETERS_TITLE());

        DefaultParametersManagementWindow defaultParametersManagementWindow = new DefaultParametersManagementWindow();

        mainVLayout.removeMember(filterLayout);
        mainVLayout.removeMember(listLayout);

        mainVLayout.addMember(defaultParametersManagementWindow);
    }
}
