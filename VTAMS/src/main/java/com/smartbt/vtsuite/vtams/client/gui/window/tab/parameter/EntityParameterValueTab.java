/*
 ** File: ParameterTab.java
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
import com.smartbt.vtsuite.vtams.client.gui.window.list.ParameterListGrid;
import com.smartbt.vtsuite.vtams.client.gui.window.filter.EntityParameterValueFilterForm;

import com.smartbt.vtsuite.vtams.client.classes.Settings;
import com.smartbt.vtsuite.vtams.client.classes.i18n.I18N;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseDatasource;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseInterface;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseTab;
import com.smartbt.vtsuite.vtams.client.gui.listener.EditorListener;
import com.smartbt.vtsuite.vtams.client.gui.listener.ListListener;
import com.smartbt.vtsuite.vtams.client.gui.listener.FilterListenerImp;
import com.smartbt.vtsuite.vtams.client.gui.listener.PaginationListener;
import com.smartbt.vtsuite.vtams.client.gui.window.editor.parameter.EntityParameterValueEditor;
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
 * The Parameter Tab
 *
 * @author Ariamnet Lopez, Ariel Saavedra
 */
public class EntityParameterValueTab extends BaseTab implements BaseInterface {

    private EntityParameterValueFilterForm filterForm;
    private ParameterListGrid listGrid;
    private EntityParameterValueEditor editorWindow;
    private int idEntity;
    private EntityType entityType;

    /**
     * Constructor
     *
     * @param type the entity type. See
     * {@link com.smartbt.vtsuite.vtams.client.classes.EntityType EntityType}
     * @param idEntity the entity id (could be a Customer, Merchant, Terminal,
     * etc.)
     */
    public EntityParameterValueTab(EntityType type, int idEntity) {
        this(type);
        this.idEntity = idEntity;
    }

    /**
     * Constructor
     *
     * @param entityType the entity type. See
     * {@link com.smartbt.vtsuite.vtams.client.classes.EntityType EntityType}
     */
    public EntityParameterValueTab(final EntityType entityType) {
        super(I18N.GET.TAB_PARAMETERS_TITLE());

        this.entityType = entityType;
        filterForm = new EntityParameterValueFilterForm();

        listGrid = new ParameterListGrid(entityType);
        editorWindow = new EntityParameterValueEditor(entityType);

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
             * Method to execute when an Update event is fired.
             *
             */
            @Override
            public void UpdateActionExecuted() {
                Update(listGrid.getSelectedRecord());
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
              //  if (Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_MERCHANT_SETTINGS_PARAMETERS_UPDATE)) {
                    Update(record);
             //   }
            }

            /**
             * Method to execute when a Selection Change event is fired.
             *
             */
            public void SelectionChangeActionExecuted(Record record) {
                filterForm.getUpdateButton().setDisabled(false);
                filterForm.getDeleteButton().setDisabled(false);
//                filterForm.getUpdateButton().setDisabled(!Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_MERCHANT_SETTINGS_PARAMETERS_UPDATE)
//                        || record == null);
//                filterForm.getDeleteButton().setDisabled(!Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_MERCHANT_SETTINGS_PARAMETERS_DELETE)||
//                        record == null || entityType == EntityType.MERCHANT || record.getAttributeAsRecord("parameter").getAttributeAsBoolean("readOnly"));
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

        formCriteria.addCriteria("idEntity", idEntity);
        formCriteria.addCriteria("entityType", entityType.toString());

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
        Record record = new Record();
        record.setAttribute("idEntity", idEntity);
        record.setAttribute("entityType", entityType.toString());
        editorWindow.addRecord(record);
        editorWindow.show();
    }

    /**
     * Update method
     *
     * @param record the record to update
     */
    public void Update(Record record) {
        //int parameterValueId = record.getAttributeAsRecord("parameter").getAttributeAsInt("id");

        record.getAttributeAsRecord("parameter").setAttribute("id", record.getAttributeAsInt("id"));//Passing id parameterValue
        editorWindow.getParameterSelect().setOptionDataSource(new BaseDatasource(record.getAttributeAsRecord("parameter")));
        editorWindow.getParameterSelect().setDefaultToFirstOption(true);
        editorWindow.getParameterSelect().setDisabled(true);

        editorWindow.getValueSelect().updateValueDataType(record.getAttributeAsRecord("dataType"));

        Record oldRecord = new Record();
        record.setAttribute("idEntity", idEntity);
        oldRecord.setAttribute("idParameterValue", record.getAttributeAsString("id"));
        oldRecord.setAttribute("entityType", entityType.toString());
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
        recordToDelete.setAttribute("entityType", entityType.toString());
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
