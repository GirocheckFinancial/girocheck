/*
 ** File: UserListGrid.java
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
package com.smartbt.vtsuite.vtams.client.gui.window.list;

import com.smartbt.vtsuite.vtams.client.classes.i18n.I18N;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseListGrid;
import com.smartbt.vtsuite.vtams.client.gui.component.TextListGridField;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.UserDS;
import com.smartbt.vtsuite.vtams.client.utils.Utils;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 * The User ListGrid
 *
 * @author Ariamnet Lopez, Ariel Saavedra
 */
public class UserListGrid extends BaseListGrid {

    private TextListGridField userField = new TextListGridField("username", I18N.GET.LIST_FIELD_USERNAME_TITLE(), false);
    private TextListGridField firstNameField = new TextListGridField("firstName", I18N.GET.LIST_FIELD_FIRST_NAME_TITLE(), false);
    private TextListGridField lastNameField = new TextListGridField("lastName", I18N.GET.LIST_FIELD_LAST_NAME_TITLE(), false);
    private TextListGridField roleNameField = new TextListGridField("roleName", I18N.GET.WINDOW_ROLE_TITLE(), false);
    private TextListGridField statusField = new TextListGridField("active", I18N.GET.LIST_FIELD_STATUS_TITLE(), false);
    private TextListGridField emailField = new TextListGridField("email", I18N.GET.LIST_FIELD_EMAIL_TITLE(), false);

    /**
     * Constructor
     *
     * @param entityType
     */
    public UserListGrid(EntityType entityType) {
        super();
        roleNameField.setDataPath("role/name");

        statusField.setCellFormatter(new CellFormatter() {
            public String format(Object value, ListGridRecord record, int rowNum, int colNum) {
                Utils.debug("Entered UserListGrid() record.getAttributeAsBoolean(active) = "+record.getAttributeAsBoolean("active"));
                return record.getAttributeAsBoolean("active") ? "Active" : "Inactive";
            }
        });    
        setEmptyMessage(I18N.GET.MESSAGE_EMPTY_USER_LIST());
        setDataSource(new UserDS(entityType));

//        if (entityType != EntityType.AMS) {
//            levelField.setCellFormatter(new CellFormatter() {
//                public String format(Object value, ListGridRecord record, int rowNum, int colNum) {
//                    if (record.getAttributeAsRecord("customer").getAttribute("id") != null) {
//                        return EntityType.CUSTOMER.toString() + " (" + record.getAttributeAsRecord("customer").getAttribute("name") + ")";
//                    }
//                    if (record.getAttributeAsRecord("merchant").getAttribute("id") != null) {
//                        return EntityType.MERCHANT.toString() + " (" + record.getAttributeAsRecord("merchant").getAttribute("name") + ")";
//                    }
//                    if (record.getAttributeAsRecord("terminal").getAttribute("id") != null) {
//                        return EntityType.TERMINAL.toString() + " (" + record.getAttributeAsRecord("terminal").getAttribute("terminalId") + ")";
//                    }
//                    return " - ";
//                }
//            });
//            setFields(userField, lastNameField, firstNameField, roleNameField, statusField, levelField);
//        } else {
//            setFields(userField, lastNameField, firstNameField, roleNameField, statusField);
//        }
        setFields(userField, lastNameField, firstNameField, roleNameField, statusField, emailField);
    }
}