/*
 ** File: WatchdogEntityTab.java
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
package com.smartbt.vtsuite.vtams.client.gui.window.tab;


import com.smartbt.vtsuite.vtams.client.classes.Settings;
import com.smartbt.vtsuite.vtams.client.classes.i18n.I18N;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseTab;
import com.smartbt.vtsuite.vtams.client.gui.listener.EditorListener;
import com.smartbt.vtsuite.vtams.client.gui.listener.FilterListenerImp;
import com.smartbt.vtsuite.vtams.client.gui.listener.ListListener;
import com.smartbt.vtsuite.vtams.client.gui.window.editor.WatchdogEntityEditor;
import com.smartbt.vtsuite.vtams.client.gui.window.filter.WatchdogEntityFilterForm;
import com.smartbt.vtsuite.vtams.client.gui.window.list.WatchdogEntityListGrid;
import com.smartbt.vtsuite.vtcommon.Constants;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomUserPrivileges;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.tab.events.TabSelectedEvent;
import com.smartgwt.client.widgets.tab.events.TabSelectedHandler;

/**
 * The WatchdogEntity Tab
 *
 * @author Ariel Saavedra
 */
public class WatchdogEntityTab extends BaseTab {

    private WatchdogEntityFilterForm filterForm;
    private WatchdogEntityListGrid listGrid;
    private WatchdogEntityEditor editorWindow;
    private int idEntity=1000000000;
    private EntityType entityType;

    /**
     * Constructor
     *
     * @param entityType
     * @param recordEntity
     */
    public WatchdogEntityTab(EntityType entityType, final Record recordEntity) {
        super(I18N.GET.TAB_WATCHDOG_RULES_TITLE());
        this.idEntity = recordEntity.getAttributeAsInt("id");
        this.entityType = entityType;

        filterForm = new WatchdogEntityFilterForm();
        listGrid = new WatchdogEntityListGrid();
        editorWindow = new WatchdogEntityEditor(entityType, recordEntity);

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

            @Override
            public void AddActionExecuted() {
                Add();
            }

            @Override
            public void UpdateActionExecuted() {
                Update(listGrid.getSelectedRecord());
            }

            @Override
            public void DeleteActionExecuted() {
                Delete();
            }
        });

        listGrid.addListener(new ListListener() {
            /**
             * Method to execute when a Select event is fired.
             *
             */
            public void SelectActionExecuted(Record record) {             
//                if (Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_WATCHDOG_RULES_UPDATE)) {
                    Update(record);
//                }
            }

            /**
             * Method to execute when a Selection Change event is fired.
             *
             */
            public void SelectionChangeActionExecuted(Record record) {                
//                    filterForm.getDeleteButton().setDisabled((!Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_WATCHDOG_RULES_DELETE))
//                            || record == null);
//                    filterForm.getUpdateButton().setDisabled((!Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_WATCHDOG_RULES_UPDATE))
//                            || record == null);
                    filterForm.getDeleteButton().setDisabled((false)
                            || record == null);
                    filterForm.getUpdateButton().setDisabled((false)
                            || record == null);
               
            }

            /**
             * Method to execute when a Data Arrive event is fired.
             *
             */
            public void DataArrivedHandlerExecuted() {
                filterForm.getDeleteButton().setDisabled(true);
                filterForm.getUpdateButton().setDisabled(true);
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
        listLayout.addMember(listGrid);
    }

    /**
     * Filter method
     *
     */
    public void Filter() {
        Criteria formCriteria = new Criteria();

        formCriteria.addCriteria("idEntity", idEntity);
        formCriteria.addCriteria("entityType", entityType.toString());

        filterForm.setDisabled(true);

        listGrid.invalidateCache();
        listGrid.setData(new RecordList());//ISSUE (The call back is not called if the Criteria is the same)
        listGrid.fetchData(formCriteria, new DSCallback() {
            public void execute(DSResponse response, Object rawData, DSRequest request) {
                filterForm.setDisabled(false);
            }
        }, null);
    }

    /**
     * Add method
     *
     */
    public void Add() {
        editorWindow.updateRecord(null);
        editorWindow.show();
    }

    /**
     * Update method
     *
     * @param record the record to update
     */
    public void Update(Record record) {
        editorWindow.updateRecord(record);
        editorWindow.show();
    }

    /**
     * Delete method
     *
     */
    public void Delete() {
        filterForm.setDisabled(true);
        Record clientRecord = new Record();
        clientRecord.setAttribute("id", listGrid.getSelectedRecord().getAttribute("id"));
        listGrid.getDataSource().removeData(clientRecord, new DSCallback() {
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
                filterForm.setDisabled(false);
            }
        });
    }

    /**
     * Save method
     *
     */
    public void Save() {
        Record recordToSave = editorWindow.getRecord();
        editorWindow.hide();
        editorWindow.getDataForm().getDataSource().addData(recordToSave, new DSCallback() {
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

                    Filter();
//                    if (entityType == EntityType.ENTERPRISE) {
//                        SC.say(I18N.GET.INFORMATION_WINDOW_TITLE, "The info has been sent.");
//                    }
                } else {
                    SC.warn(I18N.GET.WINDOW_ERROR_TITLE(), I18N.GET.MESSAGE_ERROR_ACTION());
                }
            }
        });
    }

}
