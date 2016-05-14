/*
 ** File: PrivilegeRoleEditor.java
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
package com.smartbt.vtsuite.vtams.client.gui.window.editor;

import com.smartbt.vtsuite.vtams.client.classes.i18n.I18N;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseEditorWindow;
import com.smartbt.vtsuite.vtams.client.gui.component.BaseSelectItem;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.PrivilegeDS;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.RolePrivilegeDS;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.RoleDS;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.form.fields.events.DataArrivedEvent;
import com.smartgwt.client.widgets.form.fields.events.DataArrivedHandler;
import com.smartgwt.client.widgets.grid.HoverCustomizer;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 * The PrivilegeRole Editor Window
 *
 * @author Ariamnet Lopez, Ariel Saavedra
 */
public final class PrivilegeRoleEditor extends BaseEditorWindow {

    private BaseSelectItem roleSelect;
    private BaseSelectItem privilegeSelect;

    /**
     * Constructor
     *
     */
    public PrivilegeRoleEditor(final EntityType entityType) {
        super(I18N.GET.WINDOW_PRIVILEGE_TITLE());

        roleSelect = new BaseSelectItem("idRole", I18N.GET.LABEL_ROLE_TITLE(), new RoleDS(), true);
        roleSelect.setEmptyDisplayValue(I18N.GET.MESSAGE_EMPTY_ROLE_SELECT());

        privilegeSelect = new BaseSelectItem("idPrivilege", I18N.GET.LABEL_PRIVILEGE_TITLE(), new PrivilegeDS(), true) {

            @Override
            public void showDescription() {

                ListGrid pickListProperties = new ListGrid();
                pickListProperties.setCanHover(true);
                pickListProperties.setShowHover(true);
                pickListProperties.setCellHeight(25);
                pickListProperties.setHoverCustomizer(new HoverCustomizer() {
                    @Override
                    public String hoverHTML(Object value, ListGridRecord record, int rowNum, int colNum) {
                        String descStr = record.getAttribute("description");
                        if (descStr == null) {
                            descStr = "[no description]";
                        }
                        String applications = "";
                        if (entityType == EntityType.CLERK) {
                            for (Record appRecord : record.getAttributeAsRecordArray("applicationClerkPrivilege")) {
                                applications += appRecord.getAttributeAsRecord("application").getAttribute("name") + ", ";
                            }
                        }
                        return descStr + (applications.isEmpty() ? "" : "<br><br>(" + applications.substring(0, applications.length() - 2) + ")");
                    }
                });

                setPickListProperties(pickListProperties);
            }
        };

        privilegeSelect.setEmptyDisplayValue(I18N.GET.MESSAGE_EMPTY_PRIVILEGE_SELECT());
        privilegeSelect.addDataArrivedHandler(new DataArrivedHandler() {
            public void onDataArrived(DataArrivedEvent event) {
                if (dataForm.isNewRecord()) {
                    privilegeSelect.enable();
                } else {
                    privilegeSelect.disable();
                }
            }
        });
        privilegeSelect.showDescription();

        dataForm.setDataSource(new RolePrivilegeDS());
        dataForm.setFields(roleSelect, privilegeSelect);
    }

    /**
     * Prepare to add a new record by setting to the editor the default values
     * of the record passed in.
     *
     * @param record the Privilege record
     */
    @Override
    public void addRecord(Record record) {
        record.setAttribute("entityNotContain", "true");
        super.addRecord(record);

        Criteria formCriteria = dataForm.getValuesAsCriteria();
        privilegeSelect.setPickListCriteria(formCriteria);
        privilegeSelect.fetchData();
        privilegeSelect.setDefaultToFirstOption(true);
        redraw();
    }

    public BaseSelectItem getRoleSelect() {
        return roleSelect;
    }

}
