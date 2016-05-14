/*
 ** File: RolesTab.java
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
package com.smartbt.vtsuite.vtams.client.gui.window.tab;

import com.smartbt.vtsuite.vtams.client.classes.Properties;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartbt.vtsuite.vtams.client.gui.window.filter.PrivilegeFilterForm;
import com.smartbt.vtsuite.vtams.client.gui.window.list.RolePrivilegeListGrid;

import com.smartbt.vtsuite.vtams.client.classes.Settings;
import com.smartbt.vtsuite.vtams.client.classes.i18n.I18N;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseDatasource;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseInterface;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseTab;
import com.smartbt.vtsuite.vtams.client.gui.listener.EditorListener;
import com.smartbt.vtsuite.vtams.client.gui.listener.ListListener;
import com.smartbt.vtsuite.vtams.client.gui.window.editor.PrivilegeRoleEditor;
import com.smartbt.vtsuite.vtams.client.gui.listener.FilterListenerImp;
import com.smartbt.vtsuite.vtams.client.gui.listener.PaginationListener;
import com.smartbt.vtsuite.vtams.client.utils.Utils;
import com.smartbt.vtsuite.vtcommon.Constants;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomUserPrivileges;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.util.SC;

/**
 * The Privilege Tab
 *
 * @author Ariamnet Lopez, Ariel Saavedra
 */
public class RolesTab extends BaseTab implements BaseInterface {

    private PrivilegeFilterForm filterForm;
    private RolePrivilegeListGrid listGrid;
    private PrivilegeRoleEditor editorWindow;
    private EntityType entityType;

    /**
     * Constructor
     *
     * @param entityType the entity type. See
     * {@link com.smartbt.vtsuite.vtams.client.classes.EntityType EntityType}
     */
    public RolesTab(final EntityType entityType) {
        super(I18N.GET.TAB_ROLES_TITLE());

        this.entityType = entityType;

        filterForm = new PrivilegeFilterForm(entityType);
        listGrid = new RolePrivilegeListGrid(entityType);
        editorWindow = new PrivilegeRoleEditor(entityType);

        filterForm.addListener(new FilterListenerImp() {
            /**
             * Method to execute when a Filter event is fired.
             *
             */
            @Override
            public void FilterActionExecuted() {
                Filter();
            }

            /**
             * Method to execute when an Add event is fired.
             *
             */
            @Override
            public void AddActionExecuted() {
                Add();
            }

            /**
             * Method to execute when a Delete event is fired.
             *
             */
            @Override
            public void DeleteActionExecuted() {
                Delete();
            }
        });

        paginationForm.addListener(new PaginationListener() {
            public void PreviousActionExecuted() {
                Filter();
            }

            public void NextActionExecuted() {
                Filter();
            }
        });
        listGrid.addListener(new ListListener() {
            /**
             * Method to execute when a Select event is fired.
             *
             */
            public void SelectActionExecuted(Record record) {
                // No supported for Privilege
            }

            /**
             * Method to execute when a Selection Change event is fired.
             *
             */
            public void SelectionChangeActionExecuted(Record record) {

              filterForm.getDeleteButton().setDisabled(!Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_ADMINISTRATION_ROLES_DELETE_PRIVILEGE)
                                || record == null);
//                filterForm.getDeleteButton().setDisabled(record == null);

            }

            /**
             * Method to execute when a Data Arrive event is fired.
             *
             */
            public void DataArrivedHandlerExecuted() {
                filterForm.getDeleteButton().setDisabled(true);
            }
        });

        editorWindow.addListener(new EditorListener() {
            /**
             * Method to execute when a Save event is fired.
             *
             */
            public void SaveActionExecuted() {
                Save();
            }

            /**
             * Method to execute when a Close event is fired.
             *
             */
            public void CloseActionExecuted() {
                editorWindow.hide();
            }
        });

        filterLayout.addMember(filterForm);
        filterLayout.addMember(paginationForm);
        listLayout.addMember(listGrid);
    }

    /**
     * Filter method
     *
     */
    public void Filter() {      
        if (filterForm.getRoleSelect().getValue() != null) {
            Criteria formCriteria = paginationForm.getLastLinkPressed() == null ? filterForm.getValuesAsCriteria() : paginationForm.getCriteria();
            paginationForm.setCriteria(formCriteria);

            formCriteria.addCriteria("entityType", entityType.toString());
            formCriteria.addCriteria("idRole", filterForm.getRoleSelect().getValue().toString());
            formCriteria.addCriteria("pageNumber", paginationForm.getRequestPageNumber());
            formCriteria.addCriteria("rowsPerPage", paginationForm.getRowsPerPage());

            filterForm.setDisabled(true);

            listGrid.invalidateCache();
            listGrid.fetchData(formCriteria, new DSCallback() {
                public void execute(DSResponse response, Object rawData, DSRequest request) {
                    paginationForm.updatePage(response.getAttributeAsInt("totalPages"));
                    filterForm.setDisabled(false);
                }
            }, null);
        }
    }

    /**
     * Add method
     *
     */
    public void Add() {
        editorWindow.getRoleSelect().setOptionDataSource(new BaseDatasource(filterForm.getRoleSelect().getSelectedRecord()));
        editorWindow.getRoleSelect().setDefaultToFirstOption(true);
        editorWindow.getRoleSelect().setDisabled(true);

        Record newRecord = new Record();
        newRecord.setAttribute("idRole", filterForm.getRoleSelect().getValueAsString());
        newRecord.setAttribute("entityType", entityType.toString());
        editorWindow.addRecord(newRecord);
        editorWindow.show();
    }

    /**
     * Update method
     *
     * @param record the record to update
     */
    public void Update(Record record) {
        // No supported for Privilege
    }

    /**
     * Delete method
     *
     */
    public void Delete() {
        Utils.debug("Entered roleTab.Delete()");
        Record recordToDelete = listGrid.getSelectedRecord();
        BaseDatasource ds = new BaseDatasource();

        Criteria criteria = new Criteria();

        criteria.addCriteria( "rolePrivilegeId", recordToDelete.getAttributeAsInt("id"));
        
        ds.setFetchDataURL( Properties.DELETE_ROLE_PRIVILEGE_WS );
        ds.fetchData( criteria, new DSCallback() {
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
                Filter();
            }
        });
    }

    /**
     * Save method
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
                    Filter();
                } else {
                    SC.warn(I18N.GET.WINDOW_ERROR_TITLE(), I18N.GET.MESSAGE_ERROR_ACTION());
                }
            }
        });
    }
}
