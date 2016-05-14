/*
 ** File: WatchdogFilterForm.java
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
package com.smartbt.vtsuite.vtams.client.gui.window.filter;

import com.smartbt.vtsuite.vtams.client.classes.Settings;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseFilterForm;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomUserPrivileges;
import com.smartgwt.client.widgets.form.fields.FormItem;

/**
 * The Transaction Filter Form
 *
 * @author Ariel Saavedra
 */
public class WatchdogEntityFilterForm extends BaseFilterForm {

    /**
     * Constructor
     *
     */
    public WatchdogEntityFilterForm() {
        super();

        checkPrivileges();
        setFields(new FormItem[]{addButton, updateButton, deleteButton});
    }

    private void checkPrivileges() {
//            addButton.setDisabled(!Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_WATCHDOG_RULES_ADD));
//            updateButton.setDisabled(!Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_WATCHDOG_RULES_UPDATE));
//            deleteButton.setDisabled(!Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_WATCHDOG_RULES_DELETE));
            addButton.setDisabled(false);
            updateButton.setDisabled(false);
            deleteButton.setDisabled(false);
    }
}
