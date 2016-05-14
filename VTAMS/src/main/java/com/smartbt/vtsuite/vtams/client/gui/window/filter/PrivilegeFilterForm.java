/*
 ** File: PrivilegeFilterForm.java
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

import com.smartbt.vtsuite.vtams.client.classes.Properties;
import com.smartbt.vtsuite.vtams.client.classes.Settings;
import com.smartbt.vtsuite.vtams.client.classes.i18n.I18N;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseDatasource;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseFilterForm;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseInterface;
import com.smartbt.vtsuite.vtams.client.gui.component.BaseButtonItem;
import com.smartbt.vtsuite.vtams.client.gui.component.BaseSelectItem;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.RoleDS;
import com.smartbt.vtsuite.vtams.client.gui.listener.EditorListener;
import com.smartbt.vtsuite.vtams.client.gui.window.editor.RoleEditor;
import com.smartbt.vtsuite.vtams.client.utils.Utils;
import com.smartbt.vtsuite.vtcommon.Constants;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomUserPrivileges;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.form.fields.events.DataArrivedEvent;
import com.smartgwt.client.widgets.form.fields.events.DataArrivedHandler;

/**
 * The Privilege Filter Form
 *
 * @author Ariamnet Lopez
 */
public class PrivilegeFilterForm extends BaseFilterForm implements BaseInterface {

    private BaseSelectItem roleSelect;
    private BaseButtonItem newRoleButton;
    private BaseButtonItem deleteRoleButton;
    private RoleEditor editorWindow;
    private EntityType entityType;

    /**
     * Constructor
     *
     * @param entityType
     */
    public PrivilegeFilterForm(EntityType entityType) {
        super();
        this.entityType = entityType;
        editorWindow = new RoleEditor(entityType);

        roleSelect = new BaseSelectItem("idRole", I18N.GET.LABEL_ROLE_TITLE(), new RoleDS(), false);
        Criteria criteria = new Criteria();
        criteria.addCriteria("entityType", entityType.toString());
        roleSelect.setPickListCriteria(criteria);

        roleSelect.setEmptyDisplayValue(I18N.GET.MESSAGE_EMPTY_ROLE_SELECT());
        roleSelect.setDefaultToFirstOption(true);
        roleSelect.setAutoFetchData(true);

        roleSelect.addChangedHandler(new ChangedHandler() {
            public void onChanged(ChangedEvent event) {
                Filter();
            }
        });

        roleSelect.addDataArrivedHandler(new DataArrivedHandler() {
            public void onDataArrived(DataArrivedEvent event) {
                Filter();
            }
        });

        newRoleButton = new BaseButtonItem("newRoleButton", I18N.GET.BUTTON_ADD_ROLE_TITLE());
        newRoleButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                Add();
            }
        });
        
        deleteRoleButton = new BaseButtonItem("deleteRoleButton", I18N.GET.BUTTON_ROLE_DELETE_TITLE());
        deleteRoleButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                DeleteRole();
            }
        });
        
        editorWindow.addListener(new EditorListener() {
            public void SaveActionExecuted() {
                Save();
            }

            public void CloseActionExecuted() {
                editorWindow.hide();
            }
        });

        checkPrivileges(entityType);
        setFields(new FormItem[]{roleSelect, newRoleButton, deleteRoleButton, addButton, deleteButton});
    }

    private void checkPrivileges(EntityType entityType) {
//        switch (entityType) {
//            case CLERK:
//                newRoleButton.setDisabled(!Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_BOARDING_ROLES_NEW));
//                addButton.setDisabled(!Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_BOARDING_ROLES_ADD_PRIVILEGE));
//                deleteButton.setDisabled(!Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_BOARDING_ROLES_DELETE_PRIVILEGE));
//                break;
//            case AMS:
                newRoleButton.setDisabled(!Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_ADMINISTRATION_ROLES_NEW));
                deleteRoleButton.setDisabled(!Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_ADMINISTRATION_ROLES_DELETE));
                addButton.setDisabled(!Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_ADMINISTRATION_ROLES_ADD_PRIVILEGE));
                deleteButton.setDisabled(!Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_ADMINISTRATION_ROLES_DELETE_PRIVILEGE));
//                break;
//        }
    }

    /**
     * Get Role Select
     *
     * @return BaseSelectItem
     */
    public BaseSelectItem getRoleSelect() {
        return roleSelect;
    }

    /**
     * Filter method
     *
     */
    public void Filter() {
        FilterActionExecuted();
//        switch (entityType) {
//            case CLERK:
//                addButton.setDisabled(!Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_BOARDING_ROLES_ADD_PRIVILEGE));
//                break;
//            case AMS:
                addButton.setDisabled(!Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_ADMINISTRATION_ROLES_ADD_PRIVILEGE));
//                break;
//        }
    }

    /**
     * Add method
     *
     */
    public void Add() {
        editorWindow.show();
    }

    /**
     * Update method
     *
     * @param record the record to update
     */
    public void Update(Record record) {
        // No supported for Roles
    }

    /**
     * Delete method
     *
     */
    
    public void Delete(){}
    
    public void DeleteRole() {
        
        Utils.debug("Entered Delete() with a Roledelete button");
        
        BaseDatasource ds = new BaseDatasource();

        Criteria criteria = new Criteria();
        criteria.addCriteria( "idRole", Integer.parseInt(roleSelect.getValue().toString()));
        
        Utils.debug("Entered PrivilegeFilterFormTab.Delete() with role id: " + Integer.parseInt(roleSelect.getValue().toString()));
//        recordToDelete.setAttribute("entityType", entityType.toString());
        
        ds.setFetchDataURL( Properties.DELETE_ROLE_WS );
        ds.fetchData( criteria, new DSCallback() {
            
//        listGrid.removeData(recordToDelete, new DSCallback() {
//        UserDS userds = new UserDS(entityType);
//        userds.removeData(recordToDelete, new DSCallback() {
            /**
             * Callback to invoke on completion
             *
             * @param response Response sent by the server in response to a
             * DataSource request.
             * @param rawData data
             * @param request Request sent to the server to initiate a
             * DataSource operation.
             */
            public void execute(DSResponse response, Object rawData, DSRequest request) {

                roleSelect.fetchData();
                roleSelect.redraw();
                roleSelect.clearValue();
                
                Filter();
            }
        });
    }

    /**
     * Save Role method
     *
     */
    public void Save() {
        editorWindow.getDataForm().saveData(new DSCallback() {
            /**
             * Callback to invoke on completion
             *
             * @param response Response sent by the server in response to a
             * DataSource request.
             * @param rawData data
             * @param request Request sent to the server to initiate a
             * DataSource operation.
             */
            public void execute(DSResponse response, Object rawData, DSRequest request) {
                if (response.getStatus() == Constants.CODE_SUCCESS) {
                    editorWindow.hide();
                    roleSelect.fetchData();
                }
            }
        });
    }
}
