/*
 ** File: TransactionTab.java
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
import com.smartbt.vtsuite.vtams.client.classes.i18n.I18N;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseDatasource;
import com.smartbt.vtsuite.vtams.client.gui.window.transaction.TransactionListGrid;
import com.smartbt.vtsuite.vtams.client.gui.window.filter.DateTextFilterForm;

import com.smartbt.vtsuite.vtams.client.gui.base.BaseInterface;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseTab;
import com.smartbt.vtsuite.vtams.client.gui.listener.ListListener;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.ReceiptDS;
import com.smartbt.vtsuite.vtams.client.gui.listener.FilterListenerImp;
import com.smartbt.vtsuite.vtams.client.gui.listener.PaginationListener;
import com.smartbt.vtsuite.vtams.client.gui.window.detail.TransactionDetailWindow;
import com.smartbt.vtsuite.vtams.client.utils.Utils;
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
 * The Transaction Tab
 *
 * @author Ariamnet Lopez, Ariel Saavedra
 */
public class TransactionTab extends BaseTab implements BaseInterface {

    private DateTextFilterForm filterForm;
    private TransactionListGrid listGrid;
    private TransactionDetailWindow detailWindow;
    private int idEntity;
    private EntityType entityType;

    /**
     * Constructor
     *
     * @param entityType
     * @param idEntity the merchant id
     */
    public TransactionTab(EntityType entityType, int idEntity) {
        this(entityType);
        this.idEntity = idEntity;
        this.entityType = entityType;
    }

    /**
     * Constructor
     *
     */
    private TransactionTab(EntityType entityType) {
        super(I18N.GET.TAB_TRANSACTIONS_TITLE());

        filterForm = new DateTextFilterForm();
        listGrid = new TransactionListGrid();
        
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

//            public void ImportActionExecuted() {
//                Utils.debug("Transaction tab ImportActionExecuted()");
//                report(); 
//            }
            
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
                Update(record);
            }

            /**
             * Method to execute when a Selection Change event is fired.
             *
             */
            public void SelectionChangeActionExecuted(Record record) {
                // Do Nothing
            }

            /**
             * Method to execute when a Data Arrived event is fired.
             *
             */
            public void DataArrivedHandlerExecuted() {
                // Do Nothing
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

    /**
     * Add method
     *
     */
    public void Add() {
        // No supported for Transactions
    }

    /**
     * Update method
     *
     * @param record the record to update
     */
    @Override
    public void Update(Record record) {
        if(!record.getAttributeAsString("approvalCode").equals("0")){
            return;
        }
        
        ReceiptDS receiptDS = new ReceiptDS();

        Criteria criteria = new Criteria();
        criteria.addCriteria("idTransaction", record.getAttributeAsInt("id"));

        receiptDS.fetchData(criteria, new DSCallback() {
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
                Record[] records = response.getData();

                if ((records != null) && (records.length > 0)) {
                    Record receiptRecord = records[0];

                    detailWindow = new TransactionDetailWindow(receiptRecord);
                    detailWindow.show();
                }
            }
        });
    }

    /**
     * Delete method
     *
     */
    public void Delete() {
        // No supported for Transactions
    }

    /**
     * Save method
     *
     */
    public void Save() {
        // No supported for Transactions
    }
//    /**
//     * Report method
//     *
//     */
//    public void report() {
//        Utils.debug("report()");
//        Criteria formCriteria = filterForm.getCriteria();
//        
//        BaseDatasource ds = new BaseDatasource();
// 
//        
//        ds.setFetchDataURL( Properties.REPORTS_WS );
//        ds.fetchData( formCriteria, new DSCallback() {
//            
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
//            }
//        });
//    }
}
