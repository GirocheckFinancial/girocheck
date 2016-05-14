/*
 ** File: DefaultParamTab.java
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
package com.smartbt.vtsuite.vtams.client.gui.window.tab.parameter;

import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartbt.vtsuite.vtams.client.gui.window.filter.ApplicationParameterValueFilterForm;

import com.smartbt.vtsuite.vtams.client.classes.Settings;
import com.smartbt.vtsuite.vtams.client.classes.i18n.I18N;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseDatasource;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseInterface;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseTab;
import com.smartbt.vtsuite.vtams.client.gui.listener.EditorListener;
import com.smartbt.vtsuite.vtams.client.gui.listener.FilterListenerImp;
import com.smartbt.vtsuite.vtams.client.gui.listener.ListListener;
import com.smartbt.vtsuite.vtams.client.gui.listener.PaginationListener;
import com.smartbt.vtsuite.vtams.client.gui.window.editor.parameter.ApplicationParameterValueEditor;
import com.smartbt.vtsuite.vtams.client.gui.window.list.ParameterListGrid;
import com.smartbt.vtsuite.vtcommon.Constants;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomUserPrivileges;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.tab.events.TabSelectedEvent;
import com.smartgwt.client.widgets.tab.events.TabSelectedHandler;

/**
 * The Default Param Tab
 *
 * @author Ariamnet Lopez
 */
public class ApplicationParameterValueTab extends BaseTab implements BaseInterface {

    private ApplicationParameterValueFilterForm filterForm;
    private ApplicationParameterValueEditor editorWindow;
    private ParameterListGrid listGrid;

    /**
     * Constructor
     *
     */
    public ApplicationParameterValueTab(String title) {
        super(title);

        filterForm = new ApplicationParameterValueFilterForm();
        listGrid = new ParameterListGrid(EntityType.APPLICATION_PARAMETER_VALUE);
        editorWindow = new ApplicationParameterValueEditor();

        addTabSelectedHandler(new TabSelectedHandler() {
            /**
             * Method to execute when the tab is selected.
             *
             * @param event the event
             */
            public void onTabSelected(TabSelectedEvent event) {
                Filter();
            }
        });

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

            /**
             * Method to execute when an Update event is fired.
             *
             */
            @Override
            public void UpdateActionExecuted() {
                Update(listGrid.getSelectedRecord());
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
//                if (Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_BOARDING_APP_PARAM_APP_MANAGEMENT_UPDATE)) {
                if (Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_BOARDING)) {
                    Update(record);
                }
            }

            /**
             * Method to execute when a Selection Change event is fired.
             *
             */
            public void SelectionChangeActionExecuted(Record record) {
//                filterForm.getUpdateButton().setDisabled(!Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_BOARDING_APP_PARAM_APP_MANAGEMENT_UPDATE)
//                        || record == null);
//                filterForm.getDeleteButton().setDisabled(!Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_BOARDING_APP_PARAM_APP_MANAGEMENT_DELETE)||
//                        record == null || record.getAttributeAsRecord("parameter").getAttributeAsBoolean("readOnly"));
                filterForm.getUpdateButton().setDisabled(!Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_BOARDING)
                        || record == null);
                filterForm.getDeleteButton().setDisabled(!Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_BOARDING)||
                        record == null || record.getAttributeAsRecord("parameter").getAttributeAsBoolean("readOnly"));
            }

            /**
             * Method to execute when a Data Arrived event is fired.
             *
             */
            public void DataArrivedHandlerExecuted() {
                filterForm.getUpdateButton().setDisabled(true);
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
        Criteria formCriteria = paginationForm.getLastLinkPressed() == null ? filterForm.getValuesAsCriteria() : paginationForm.getCriteria();
        paginationForm.setCriteria(formCriteria);

        formCriteria.addCriteria("entityType", EntityType.APPLICATION_PARAMETER_VALUE.toString());

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

    /**
     * Add method
     *
     */
    public void Add() {
        editorWindow.getApplicationSelect().setOptionDataSource(new BaseDatasource(filterForm.getApplicationSelect().getSelectedRecord()));
        editorWindow.getApplicationSelect().setDefaultToFirstOption(true);
        editorWindow.getApplicationSelect().setDisabled(true);

        Record record = new Record();
        record.setAttribute("idEntity", filterForm.getApplicationSelect().getValue());
        record.setAttribute("entityType", EntityType.APPLICATION_PARAMETER_VALUE.toString());
        editorWindow.addRecord(record);
        editorWindow.show();
    }

    /**
     * Update method
     *
     */
    public void Update(Record record) {
        editorWindow.getApplicationSelect().setOptionDataSource(new BaseDatasource(filterForm.getApplicationSelect().getSelectedRecord()));
        editorWindow.getApplicationSelect().setDefaultToFirstOption(true);
        editorWindow.getApplicationSelect().setDisabled(true);

        editorWindow.getParameterSelect().setOptionDataSource(new BaseDatasource(record.getAttributeAsRecord("parameter")));
        editorWindow.getParameterSelect().setDefaultToFirstOption(true);
        editorWindow.getParameterSelect().setDisabled(true);

        editorWindow.getValueSelect().updateValueDataType(record.getAttributeAsRecord("dataType"));

        Record oldRecord = new Record();
        oldRecord.setAttribute("idParameterValue", record.getAttributeAsString("id"));
        record.setAttribute("idEntity", filterForm.getApplicationSelect().getValue());
        oldRecord.setAttribute("entityType", EntityType.APPLICATION_PARAMETER_VALUE.toString());
        oldRecord.setAttribute("value", record.getAttributeAsString("value"));

        editorWindow.updateRecord(oldRecord);
        editorWindow.show();
    }

    /**
     * Delete method
     *
     */
    public void Delete() {
        Record recordToDelete = listGrid.getSelectedRecord();
        recordToDelete.setAttribute("entityType", EntityType.APPLICATION_PARAMETER_VALUE.toString());
        listGrid.removeData(recordToDelete, new DSCallback() {
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
