/*
 ** File: WatchdogTab.java
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


import com.smartbt.vtsuite.vtams.client.classes.Properties;
import com.smartbt.vtsuite.vtams.client.classes.Settings;
import com.smartbt.vtsuite.vtams.client.classes.i18n.I18N;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseDatasource;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseTab;
import com.smartbt.vtsuite.vtams.client.gui.listener.FilterListenerImp;
import com.smartbt.vtsuite.vtams.client.gui.listener.ListListener;
import com.smartbt.vtsuite.vtams.client.gui.listener.PaginationListener;
import com.smartbt.vtsuite.vtams.client.gui.window.filter.WatchdogAlertFilterForm;
import com.smartbt.vtsuite.vtams.client.gui.window.list.WatchdogAlertListGrid;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomUserPrivileges;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.widgets.tab.events.TabSelectedEvent;
import com.smartgwt.client.widgets.tab.events.TabSelectedHandler;

/**
 * The Watchdog Tab
 *
 * @author Ariel Saavedra
 */
public final class WatchdogAlertTab extends BaseTab {

    private WatchdogAlertFilterForm filterForm;
    private WatchdogAlertListGrid listGrid;
    private int idEntity;
    private EntityType entityType;

    /**
     * Constructor
     *
     * @param entityType
     * @param idEntity
     */
    public WatchdogAlertTab(EntityType entityType, int idEntity) {
        super(I18N.GET.TAB_WATCHDOG_ALERT_TITLE());
        this.idEntity = idEntity;
        this.entityType = entityType;
     
        filterForm = new WatchdogAlertFilterForm();
        listGrid = new WatchdogAlertListGrid();

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
            public void DeleteActionExecuted() {
               Delete();
            }

            @Override
            public void DeleteAllActionExecuted() {
               DeleteAll();
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
            }

            /**
             * Method to execute when a Selection Change event is fired.
             *
             */
            public void SelectionChangeActionExecuted(Record record) {
//                filterForm.getDeleteButton().setDisabled((!Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_WATCHDOG_ALERT_DELETE))
//                            ||record == null);
                filterForm.getDeleteButton().setDisabled((false)
                            ||record == null);
            }

            /**
             * Method to execute when a Data Arrive event is fired.
             *
             */
            public void DataArrivedHandlerExecuted() {
                filterForm.getDeleteButton().setDisabled(true);
            }
        });
        
        filterLayout.addMember(filterForm);
        filterLayout.addMember(paginationForm);
        listLayout.addMember(listGrid);

        //Filter();
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
     * Delete method
     *
     */
    public void Delete() {
        filterForm.setDisabled(true);
        Record criteriRecord = new Record();
        criteriRecord.setAttribute("id", listGrid.getSelectedRecord().getAttribute("id"));
        listGrid.getDataSource().removeData(criteriRecord, new DSCallback() {
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
     * Delete All method
     *
     */
    public void DeleteAll() {
        Criteria deleteAllcriteria = new Criteria();
        
        deleteAllcriteria.addCriteria("idEntity", idEntity);
        deleteAllcriteria.addCriteria("entityType", entityType.toString());
        
        filterForm.setDisabled(true);
        
        BaseDatasource datasource = new BaseDatasource();
        datasource.setFetchDataURL(Properties.DELETE_ALL_WATCHDOG_ALERTS_BY_ENTITY);
        
        datasource.fetchData(deleteAllcriteria, new DSCallback() {
            public void execute(DSResponse response, Object rawData, DSRequest request) {
                Filter();
                filterForm.setDisabled(false);
            }
        });
    }
}
