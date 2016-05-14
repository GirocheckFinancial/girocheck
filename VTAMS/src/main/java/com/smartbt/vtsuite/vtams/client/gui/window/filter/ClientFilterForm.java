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
import com.smartbt.vtsuite.vtams.client.gui.base.BaseFilterForm;
import com.smartbt.vtsuite.vtcommon.enums.ActivityFilter;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomUserPrivileges;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.SpacerItem;
import java.util.LinkedHashMap;

/**
 * The User Filter Form
 *
 * @author Ariel Saavedra
 */
public class ClientFilterForm extends BaseFilterForm {

    /**
     * Constructor
     *
     * @param entityType
     */
    public ClientFilterForm(EntityType entityType) {
        super();
//        CheckboxItem activityFilter = new CheckboxItem("activityFilter");
//        activityFilter.setTitle("Active");
//        activityFilter.setAllowEmptyValue(true);
//        activityFilter.setDefaultValue(ActivityFilter.ACTIVE.toString());
//
//        LinkedHashMap valueMap = new LinkedHashMap();
//        valueMap.put(ActivityFilter.ACTIVE.toString(), true);
//        valueMap.put(ActivityFilter.INACTIVE.toString(), false);
//        activityFilter.setValueMap(valueMap);

//        checkPrivileges();
        setFields(new FormItem[]{searchText, filterButton});
//        switch (entityType) {
//            case CUSTOMER: {
//                setFields(new FormItem[]{activityFilter, searchText, filterButton, new SpacerItem(), updateButton, deleteButton, deleteAllButton});
//                break;
//            }
//            case MERCHANT: {
//                setFields(new FormItem[]{activityFilter, searchText, filterButton, new SpacerItem(), addButton, updateButton, importButton, deleteButton, deleteAllButton});
//                break;
//            }
//        }
    }

    private void checkPrivileges() {
//        updateButton.setDisabled(!Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_MERCHANT_CUSTOMER_UPDATE));
//        deleteButton.setDisabled(!Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_MERCHANT_CUSTOMER_DELETE));
//        deleteAllButton.setDisabled(!Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_MERCHANT_CUSTOMER_DELETE_ALL));
//        addButton.setDisabled(!Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_MERCHANT_CUSTOMER_ADD));
//        importButton.setDisabled(!Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_MERCHANT_CUSTOMER_IMPORT));
        updateButton.setDisabled(false);
        deleteButton.setDisabled(false);
        deleteAllButton.setDisabled(false);
        addButton.setDisabled(false);
        importButton.setDisabled(false);
    }
}
