/*
 ** File: UserFilterForm.java
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
package com.smartbt.vtsuite.vtams.client.gui.window.filter;

import com.smartbt.vtsuite.vtams.client.classes.Settings;
import com.smartbt.vtsuite.vtams.client.classes.i18n.I18N;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseFilterForm;
import com.smartbt.vtsuite.vtams.client.gui.component.BaseButtonItem;
import com.smartbt.vtsuite.vtams.client.gui.listener.FilterListener;
import com.smartbt.vtsuite.vtams.client.utils.Utils;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomUserPrivileges;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;

/**
 * The User Filter Form
 *
 * @author Ariamnet Lopez, Ariel Saavedra
 */
public class UserFilterForm extends BaseFilterForm {

    private BaseButtonItem changePasswordButton;
    /**
     * Constructor
     *
     * @param entityType
     */
    public UserFilterForm(EntityType entityType) {
        super();
        Utils.debug( "UserFilterForm() received EntityType" + entityType.toString() );
        checkPrivileges(entityType);
        
        changePasswordButton = new BaseButtonItem("changePasswordButton", I18N.GET.BUTTON_CHANGE_PASSWORD_TITLE());        
        changePasswordButton.setAlign(Alignment.RIGHT);
        changePasswordButton.setDisabled(true);
        changePasswordButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                ChangePasswordActionExecuted();
            }
        });
        
        setFields(new FormItem[]{searchText, filterButton, addButton, updateButton, deleteButton, changePasswordButton});
    }
    
    public void ChangePasswordActionExecuted() {
        for (FilterListener listener : super.listeners) {
            listener.ChangePasswordActionExecuted();
        }
    }

    private void checkPrivileges(EntityType entityType) {

                addButton.setDisabled(!Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_ADMINISTRATION_USERS_UPDATE));
                updateButton.setDisabled(!Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_ADMINISTRATION_USERS_UPDATE));
                deleteButton.setDisabled(!Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_ADMINISTRATION_USERS_UPDATE));
//                changePasswordButton.setDisabled(!Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_ADMINISTRATION_USERS_CHANGE_PASSWORD));
        
    }

    /**
     * @return the changePasswordButton
     */
    public BaseButtonItem getChangePasswordButton() {
        return changePasswordButton;
    }

    /**
     * @param changePasswordButton the changePasswordButton to set
     */
    public void setChangePasswordButton(BaseButtonItem changePasswordButton) {
        this.changePasswordButton = changePasswordButton;
    }
}
