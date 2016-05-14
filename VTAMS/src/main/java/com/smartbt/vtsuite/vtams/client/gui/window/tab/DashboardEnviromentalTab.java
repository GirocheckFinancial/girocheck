/*
 ** File: DashboardEnviromentalTab.java
 **
 ** Date Created: December 2013
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
import com.smartbt.vtsuite.vtams.client.gui.window.list.DashboardEnvironmentalListGrid;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.widgets.tab.events.TabSelectedEvent;
import com.smartgwt.client.widgets.tab.events.TabSelectedHandler;

/**
 * The DashboardEnviromentalTab Tab
 *
 * @author Ariel Saavedra
 */
public class DashboardEnviromentalTab extends BaseTab implements BaseInterface {

    private DashboardEnvironmentalListGrid listGrid;

    /**
     * Constructor
     *
     */
    public DashboardEnviromentalTab() {
        super(I18N.GET.TAB_ENVIRONMENTAL_TITLE());

        listGrid = new DashboardEnvironmentalListGrid();

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
        
        listLayout.addMember(listGrid);
    }

    /**
     * Filter method
     *
     */
    public void Filter() {
        listGrid.invalidateCache();
        listGrid.setData(new RecordList());//ISSUE (The call back is not called if the Criteria is the same)
        listGrid.fetchData();
    }

    /**
     * Add method
     *
     */
    public void Add() {
        // No supported for DashboardEnviromental
    }

    /**
     * Update method
     *
     * @param record the record to update
     */
    public void Update(Record record) {
        // No supported for DashboardEnviromental
    }

    /**
     * Delete method
     *
     */
    public void Delete() {
        // No supported for DashboardEnviromental
    }

    /**
     * Save method
     *
     */
    public void Save() {
        // No supported for DashboardEnviromental
    }
}
