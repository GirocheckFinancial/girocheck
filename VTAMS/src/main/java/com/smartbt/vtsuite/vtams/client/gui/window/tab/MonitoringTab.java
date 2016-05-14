/*
 ** File: MonitoringTab.java
 **
 ** Date Created: January 2014
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
import com.smartbt.vtsuite.vtams.client.classes.i18n.I18N;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseTab;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.MonitoringDS;
import com.smartbt.vtsuite.vtams.client.gui.listener.FilterListenerImp;
import com.smartbt.vtsuite.vtams.client.gui.listener.ListListener;
import com.smartbt.vtsuite.vtams.client.gui.listener.PaginationListener;
import com.smartbt.vtsuite.vtams.client.gui.window.detail.MonitoringDetailWindow;
import com.smartbt.vtsuite.vtams.client.gui.window.filter.MonitoringFilterForm;
import com.smartbt.vtsuite.vtams.client.gui.window.list.MonitoringListGrid;
import com.smartbt.vtsuite.vtcommon.Constants;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.tab.events.TabSelectedEvent;
import com.smartgwt.client.widgets.tab.events.TabSelectedHandler;
import java.util.Date;

/**
 * The Monitoring Tab
 *
 * @author Ariel Saavedra
 */
public final class MonitoringTab extends BaseTab {

    private MonitoringFilterForm filterForm;
    private MonitoringListGrid listGrid;
    private MonitoringDetailWindow detailWindow;
    private EntityType entityType;
    private Record recordEntity;

    /**
     * Constructor
     *
     * @param entityType
     * @param recordEntity
     */
    public MonitoringTab(EntityType entityType, final Record recordEntity) {
        super(I18N.GET.TAB_MONITORING_TITLE());

        filterLayout.setStyleName("filterFormTopLineLog");

        this.entityType = entityType;
        this.recordEntity = recordEntity;

        filterForm = new MonitoringFilterForm(recordEntity);
        filterForm.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {
                updateMonitoringStatus(recordEntity);
            }
        });

        listGrid = new MonitoringListGrid(entityType);

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
                //Update(record);
            }

            /**
             * Method to execute when a Selection Change event is fired.
             *
             */
            public void SelectionChangeActionExecuted(Record record) {
            }

            /**
             * Method to execute when a Data Arrive event is fired.
             *
             */
            public void DataArrivedHandlerExecuted() {
            }
        });

        filterLayout.addMember(filterForm);
        filterLayout.addMember(paginationForm);
        listLayout.addMember(listGrid);

        Filter();
    }

    /**
     * Filter method
     *
     */
    public void Filter() {
        Criteria formCriteria = paginationForm.getLastLinkPressed() == null ? filterForm.getValuesAsCriteria() : paginationForm.getCriteria();
        paginationForm.setCriteria(formCriteria);

        formCriteria.addCriteria("idEntity", recordEntity.getAttribute("id"));
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
     * Update method
     *
     * @param record the record to update
     */
    public void Update(Record record) {
        detailWindow = new MonitoringDetailWindow(record);
        detailWindow.show();
    }

    private void updateMonitoringStatus(final Record recordEntity) {
        MonitoringDS monitoringDS = new MonitoringDS();

        final boolean isTerminalBeingMonitored = filterForm.isTerminalBeingMonitored(recordEntity);
        monitoringDS.setFetchDataURL(isTerminalBeingMonitored
                ? Properties.STOP_MONITORING_TERMINAL : Properties.START_MONITORING_TERMINAL);

        Criteria criteria = new Criteria();
        criteria.setAttribute("id", recordEntity.getAttribute("id"));

        monitoringDS.fetchData(criteria, new DSCallback() {

            public void execute(DSResponse response, Object rawData, DSRequest request) {
                if (response.getStatus() == Constants.CODE_SUCCESS) {
                    recordEntity.getAttributeAsRecord("entityRecord").setAttribute("monitored", !isTerminalBeingMonitored);
                    if (!isTerminalBeingMonitored) {
                        recordEntity.getAttributeAsRecord("entityRecord").setAttribute("startedMonitorAt", new Date());
                    }
                    filterForm.checkActivationButton(recordEntity);
                } else {
                    SC.warn(I18N.GET.WINDOW_ERROR_TITLE(), I18N.GET.MESSAGE_ERROR_ACTION());
                }
            }
        });
    }
}
