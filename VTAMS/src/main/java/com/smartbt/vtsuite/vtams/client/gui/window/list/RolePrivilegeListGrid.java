/*
 ** File: PrivilegeListGrid.java
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
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.RolePrivilegeDS;
import com.smartbt.vtsuite.vtams.client.helpers.DateHelper;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 * The Privilege ListGrid
 *
 * @author Ariamnet Lopez, Ariel Saavedra
 */
public class RolePrivilegeListGrid extends BaseListGrid {

    private TextListGridField nameField = new TextListGridField("name", I18N.GET.LIST_FIELD_NAME_TITLE(), false);
    private TextListGridField descriptionField = new TextListGridField("description", I18N.GET.LIST_FIELD_DESCRIPTION_TITLE(), false);

    /**
     * Constructor
     *
     */
    public RolePrivilegeListGrid(EntityType entityType) {
        super();
        setEmptyMessage(I18N.GET.MESSAGE_EMPTY_PRIVILEGE_LIST());

        setDataSource(new RolePrivilegeDS());

        nameField.setDataPath("privilege/name");
        descriptionField.setDataPath("privilege/description");

//        applicationsField.setCellFormatter(new CellFormatter() {
//            public String format(Object value, ListGridRecord record, int rowNum, int colNum) {
//                String applications = "";
//                for (Record appRecord : record.getAttributeAsRecord("privilege").getAttributeAsRecordArray("applicationClerkPrivilege")) {
//                    applications += appRecord.getAttributeAsRecord("application").getAttribute("name") + ", ";
//                }
//                return applications.isEmpty() ? "" : applications.substring(0, applications.length() - 2);
//            }
//        });
//        if (entityType == EntityType.CLERK) {
//            setFields(nameField, descriptionField, applicationsField);
//        } else {
            setFields(nameField, descriptionField);
//        }
    }
}
