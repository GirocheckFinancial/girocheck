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
import com.smartbt.vtsuite.vtams.client.gui.base.BaseTab;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.ClientDS;
import com.smartbt.vtsuite.vtams.client.gui.listener.FilterListenerImp;
import com.smartbt.vtsuite.vtams.client.gui.listener.ListListener;
import com.smartbt.vtsuite.vtams.client.gui.listener.PaginationListener;
import com.smartbt.vtsuite.vtams.client.gui.window.filter.ClientFilterForm;
import com.smartbt.vtsuite.vtams.client.gui.window.list.ClientListGrid;
import com.smartbt.vtsuite.vtams.client.utils.Utils;
import com.smartbt.vtsuite.vtcommon.Constants;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.tab.events.TabSelectedEvent;
import com.smartgwt.client.widgets.tab.events.TabSelectedHandler;

/**
 * The Client Tab
 *
 * @author Roberto Rodriguez
 */
public class ClientBlackListTab extends BaseTab {

    private ClientFilterForm filterForm;
    private ClientListGrid listGrid;
    private Integer selectedId = 0; 
    CheckboxItem blackListCheckbox = new CheckboxItem("blackList", "In Black List");

    /**
     * Constructor
     *
     * @param type
     * @param idEntity
     */
    public ClientBlackListTab() {
        super("Card to Bank Black List");

        blackListCheckbox.setValue(true);

        filterForm = new ClientFilterForm() {
            public FormItem[] getFormFields() {
                return new FormItem[]{searchText, blackListCheckbox, filterButton, addButton, deleteButton};
            }
        };

        listGrid = new ClientListGrid(null);

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
                Add();
            }

            @Override
            public void UpdateActionExecuted() {
//                Update(listGrid.getSelectedRecord());
            }

            @Override
            public void ImportActionExecuted() {
//                importClients();
            }

            @Override
            public void DeleteActionExecuted() {
                Delete();
            }

            @Override
            public void DeleteAllActionExecuted() {
//                DeleteAll();
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

            public void SelectActionExecuted(Record record) {

            }

            public void SelectionChangeActionExecuted(Record record) {
                Update(record);
            }

            public void DataArrivedHandlerExecuted() {
                filterForm.getDeleteButton().setDisabled(true);
                filterForm.getAddButton().setDisabled(true);
                selectedId = 0;
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

        formCriteria.addCriteria("pageNumber", paginationForm.getRequestPageNumber());
        formCriteria.addCriteria("rowsPerPage", paginationForm.getRowsPerPage());

        Boolean blackList = blackListCheckbox.getValueAsBoolean();
        blackList = blackList != null ? blackList : false;

        formCriteria.addCriteria("blackList", blackList);

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
        Utils.debug("Update..");

        Boolean blackList = record.getAttributeAsBoolean("blackList");
        blackList = blackList != null ? blackList : false;
        Utils.debug("blackList = " + blackList);

        Integer id = record.getAttributeAsInt("id");
        Utils.debug("id = " + id);
        selectedId = id;

        filterForm.getDeleteButton().setDisabled(!blackList);
        filterForm.getAddButton().setDisabled(blackList); 

    }

    
     public void Add() {
        updateBlackListStatus(true);
    }
    
    public void Delete() {
         updateBlackListStatus(false);
    }

   private void updateBlackListStatus(Boolean blackList){
       ClientDS clientDS = new ClientDS();
        Record record = new Record();
        record.setAttribute("id", selectedId);
        record.setAttribute("blackList", blackList);

        clientDS.setAddDataURL(Properties.UPDATE_CLIENT_BLACK_LIST_WS);
        
        clientDS.addData(record, new DSCallback() {
            public void execute(DSResponse response, Object rawData, DSRequest request) {
                
                if (response.getStatus() == Constants.CODE_SUCCESS) {
                    Filter();
                } else {
                    Utils.debug("updateBlackListStatus :: comeBack -> failed");
                }
            }
        });
   }
}
