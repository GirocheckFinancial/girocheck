/*
 ** File: ClientTab.java
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
import com.smartbt.vtsuite.vtams.client.classes.Settings;
import com.smartbt.vtsuite.vtams.client.classes.i18n.I18N;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseDatasource;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseTab;
import com.smartbt.vtsuite.vtams.client.gui.listener.FilterListenerImp;
import com.smartbt.vtsuite.vtams.client.gui.listener.ListListener;
import com.smartbt.vtsuite.vtams.client.gui.listener.PaginationListener;
import com.smartbt.vtsuite.vtams.client.gui.window.editor.ClientEditor;
import com.smartbt.vtsuite.vtams.client.gui.window.filter.ClientFilterForm;
import com.smartbt.vtsuite.vtams.client.gui.window.list.ClientListGrid;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomUserPrivileges;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.tab.events.TabSelectedEvent;
import com.smartgwt.client.widgets.tab.events.TabSelectedHandler;

/**
 * The Client Tab
 *
 * @author Ariel Saavedra
 */
public class ClientTab extends BaseTab {

    private ClientFilterForm filterForm;
    private ClientListGrid listGrid;
    private ClientEditor editorWindow;
    private int idEntity;
    private EntityType entityType;

    /**
     * Constructor
     *
     * @param type
     * @param idEntity
     */
    public ClientTab(EntityType type, int idEntity) {
        this(type);
        this.idEntity = idEntity;
    }

    public ClientTab(EntityType type) {
        super(I18N.GET.TAB_CLIENT_TITLE());

        this.entityType = type;

        filterForm = new ClientFilterForm();
        listGrid = new ClientListGrid(entityType);
//
        editorWindow = new ClientEditor();

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
            public void FilterActionExecuted() {
                Filter();
            }

            @Override
            public void AddActionExecuted() {
//                Add();
            }

            @Override
            public void UpdateActionExecuted() {
               
            }           

            @Override
            public void ImportActionExecuted() {
//                importClients();
            }

            @Override
            public void DeleteActionExecuted() {
//                Delete();
            }

            @Override
            public void DeleteAllActionExecuted() {
//                DeleteAll();
            }
        });
        
        filterForm.getSmsReceiveButton().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                receiveSMS(listGrid.getSelectedRecord());
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
//                if (Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_MERCHANT_CUSTOMER_UPDATE)) {             
                Update(record);
//               }
            }

            /**
             * Method to execute when a Selection Change event is fired.
             *
             */
            @Override
            public void SelectionChangeActionExecuted(Record record) {                
                if (Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_SMS_MESSAGES)) {         
                    Boolean bOptOut = record.getAttributeAsBoolean("optOut");
                    if (bOptOut) {                        
                        filterForm.getSmsReceiveButton().setDisabled(false);
                    } else {                       
                        filterForm.getSmsReceiveButton().setDisabled(true);
                    }
                }else{                    
                    filterForm.getSmsReceiveButton().setDisabled(true);
                }
            }

            /**
             * Method to execute when a Data Arrive event is fired.
             *
             */
            public void DataArrivedHandlerExecuted() {
                filterForm.getDeleteButton().setDisabled(true);
                filterForm.getUpdateButton().setDisabled(true);
                filterForm.getSmsReceiveButton().setDisabled(true);
            }
        });

//        editorWindow.addListener(new EditorListener() {
//            /**
//             * Method to execute when a Save event is fired.
//             *
//             */
//            public void SaveActionExecuted() {
//                Save();
//            }
//
//            /**
//             * Method to execute when a Close event is fired.
//             *
//             */
//            public void CloseActionExecuted() {
//                editorWindow.hide();
//            }
//        });

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

        formCriteria.addCriteria("idEntity", idEntity);
        formCriteria.addCriteria("entityType", entityType.toString());

        formCriteria.addCriteria("pageNumber", paginationForm.getRequestPageNumber());
        formCriteria.addCriteria("rowsPerPage", paginationForm.getRowsPerPage());

        filterForm.setDisabled(true);

        listGrid.invalidateCache();
        listGrid.setData(new RecordList());//ISSUE (The call back is not called if the Criteria is the same)
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
//        editorWindow.updateRecord(null);
//        editorWindow.show();
    }

    /**
     * Update method
     *
     * @param record the record to update
     */
    public void Update(Record record) {
        //editorWindow.updateRecord(record);
        //editorWindow.show();
    }

    public void receiveSMS(Record record) {
        record.setAttribute("optOut", Boolean.FALSE);
        final Record updateRecord = record;
        SC.confirm(I18N.GET.WINDOW_SMS_OPT_OUT_TITLE(), I18N.GET.SMS_OPT_OUT_VALUE_CONFIRM_ACTION(), new BooleanCallback() {
            public void execute(Boolean value) {
                if (value == Boolean.TRUE) {
                    updateClient(updateRecord);
                }else{
                    Filter();
                }
            }
        });
    }

    /**
     * Delete method
     *
     */
    public void Delete() {
//        filterForm.setDisabled(true);
//        Record clientRecord = new Record();
//        clientRecord.setAttribute("id", listGrid.getSelectedRecord().getAttribute("id"));
//        listGrid.getDataSource().removeData(clientRecord, new DSCallback() {
//            /**
//             * Callback to invoke on completion
//             *
//             * @param response Response sent by the server in response to a
//             * DataSource request.
//             * @param rawData data
//             * @param request Request sent to the server to initiate a
//             * DataSource operation.
//             */
//            public void execute(DSResponse response, Object rawData, DSRequest request) {
//                Filter();
//                filterForm.setDisabled(false);
//            }
//        });
    }

    /**
     * Delete All method
     *
     */
    public void DeleteAll() {
//        Criteria deleteAllcriteria = new Criteria();
//
//        deleteAllcriteria.addCriteria("idEntity", idEntity);
//        deleteAllcriteria.addCriteria("entityType", entityType.toString());
//
//        filterForm.setDisabled(true);
//
//        BaseDatasource datasource = new BaseDatasource();
//        datasource.setFetchDataURL(Properties.DELETE_ALL_CLIENT_WS);
//
//        datasource.fetchData(deleteAllcriteria, new DSCallback() {
//            public void execute(DSResponse response, Object rawData, DSRequest request) {
//                Filter();
//                filterForm.setDisabled(false);
//            }
//        });
    }

    /**
     * Save method
     *
     */
    public void Save() {
//        //editorWindow.saveRecord(idEntity);
//        editorWindow.getDataForm().getDataSource().addData(editorWindow.getRecord(idEntity), new DSCallback() {
//            /**
//             * Callback to invoke on completion
//             *
//             * @param response Response sent by the server in response to a
//             * DataSource request.
//             * @param rawData data
//             * @param request Request sent to the server to initiate a
//             * DataSource operation.
//             */
//            public void execute(DSResponse response, Object rawData, DSRequest request) {
//                if (response.getStatus() == Constants.CODE_SUCCESS) {
//                    editorWindow.hide();
//                    Filter();
//                } else {
//                    SC.warn(I18N.GET.WINDOW_ERROR_TITLE(), I18N.GET.MESSAGE_ERROR_ACTION());
//                }
//            }
//        });
    }

    public void importClients() {
//        final UploadFileEditor uploadFileEditor = new UploadFileEditor(idEntity,
//                Properties.IMPORT_CLIENTS_WS,
//                I18N.GET.WINDOW_UPLOAD_CSVFILE_BOX_TITLE(),
//                "Select a csv file");
//        uploadFileEditor.addListener(new EditorListener() {
//            public void SaveActionExecuted() {
//                JavaScriptMethodHelper.addUploadFileCallback(new JavaScriptMethodCallback() {
//                    public void execute(String obj) {
//                        Filter();
//                    }
//                });
//                uploadFileEditor.submitForm();
//                uploadFileEditor.hide();
//            }
//
//            public void CloseActionExecuted() {
//                uploadFileEditor.hide();
//            }
//        });
//        uploadFileEditor.show();
    }

    public void updateClient(Record record) {

        BaseDatasource ds = new BaseDatasource();

        Criteria criteria = new Criteria();
        criteria.addCriteria("id", record.getAttributeAsInt("id"));

        ds.setFetchDataURL(Properties.UPDATE_OPTOUT_CLIENTS_WS);
        ds.fetchData(criteria, new DSCallback() {
            public void execute(DSResponse response, Object rawData, DSRequest request) {
                Filter();

            }
        });

    }
}
