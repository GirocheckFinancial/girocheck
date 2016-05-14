/*
 ** File: ParameterFilterForm.java
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
import com.smartbt.vtsuite.vtams.client.gui.base.BaseFilterForm;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomUserPrivileges;
import com.smartgwt.client.widgets.form.fields.FormItem;

/**
 * The Parameter Filter Form
 *
 * @author Ariamnet Lopez, Ariel Saavedra
 */
public class EntityParameterValueFilterForm extends BaseFilterForm {

    /**
     * Constructor
     *
     * @param entityType
     */
    public EntityParameterValueFilterForm() {
        super();

        //addButton.setDisabled(false);
        addButton.setVisible(true);
       // updateButton.setVisible(true);
        //deleteButton.setVisible(true);

     //   checkPrivileges(entityType);
        setFields(new FormItem[]{searchText, filterButton, addButton, updateButton, deleteButton});
    }

//    private void checkPrivileges(EntityType entityType) {
//        switch (entityType) {
//            case CUSTOMER:
//            case MERCHANT:
//            case TERMINAL:
//                updateButton.setDisabled(!Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_MERCHANT_SETTINGS_PARAMETERS_UPDATE));
//                // deleteButton.setDisabled(!Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_MERCHANT_SET));
//                addButton.setDisabled(!Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_MERCHANT_SETTINGS_PARAMETERS_ADD));
//                break;
//            case AMS:
//                updateButton.setDisabled(!Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_SETTINGS_PARAMETERS_UPDATE));
//                // deleteButton.setDisabled(!Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_SETTINGS_PARAMETERS_));
//                addButton.setDisabled(!Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_SETTINGS_PARAMETERS_ADD));
//                break;
//            case APPLICATION_PARAMETER:
//                //updateButton.setDisabled(!Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_SETTINGS_PARAMETERS_UPDATE));
//                // deleteButton.setDisabled(!Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_SETTINGS_PARAMETERS_));
//                //addButton.setDisabled(!Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_SETTINGS_PARAMETERS_ADD));
//                break;
//        }
//    }
}
