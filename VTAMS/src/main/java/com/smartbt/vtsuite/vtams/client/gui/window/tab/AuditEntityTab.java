/*
 ** File: AuditEntityTab.java
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


import com.smartbt.vtsuite.vtams.client.classes.i18n.I18N;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseInterface;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseTab;
import com.smartbt.vtsuite.vtams.client.gui.listener.FilterListenerImp;
import com.smartbt.vtsuite.vtams.client.gui.listener.PaginationListener;
import com.smartbt.vtsuite.vtams.client.gui.window.filter.DateTextFilterForm;
import com.smartbt.vtsuite.vtams.client.gui.window.list.AuditLogListGrid;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.widgets.tab.events.TabSelectedEvent;
import com.smartgwt.client.widgets.tab.events.TabSelectedHandler;

/**
 * The Audit Entity Tab
 *
 * @author Ariel Saavedra
 */
public final class AuditEntityTab extends BaseTab implements BaseInterface {

    private DateTextFilterForm filterForm;
    private AuditLogListGrid listGrid;
    private int idEntity;
    private EntityType entityType;

    /**
     * Constructor
     *
     * @param entityType
     * @param idEntity
     */
    public AuditEntityTab(EntityType entityType, int idEntity) {
        super(entityType == EntityType.AMS ? I18N.GET.TAB_AUDIT_AMS_TITLE() : I18N.GET.TAB_AUDIT_TITLE());
        switch (entityType) {
            case CUSTOMER:
            case MERCHANT:
            case TERMINAL:
                filterLayout.setStyleName("filterFormTopLineLog");
                break;
        }

        this.idEntity = idEntity;
        this.entityType = entityType;

        this.filterForm = new DateTextFilterForm();

        this.listGrid = new AuditLogListGrid(entityType);

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

        paginationForm.addListener(new PaginationListener() {
            public void PreviousActionExecuted() {
                Filter();
            }

            public void NextActionExecuted() {
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

        formCriteria.addCriteria("idEntity", idEntity);
        formCriteria.addCriteria("entityType", entityType.toString());

        formCriteria.addCriteria("pageNumber", paginationForm.getRequestPageNumber());
        formCriteria.addCriteria("rowsPerPage", paginationForm.getRowsPerPage());

        // This line is needed to make it run asynchronously
//        DSRequest dsRequest = new DSRequest();
//        dsRequest.setShowPrompt(false);
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

    public void Add() {
        //Not supported for Audit Log
    }

    public void Update(Record record) {
        //Not supported for Audit Log
    }

    public void Delete() {
        //Not supported for Audit Log
    }

    public void Save() {
        //Not supported for Audit Log
    }

}
