/*
 ** File: BaseListGrid.java
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
package com.smartbt.vtsuite.vtams.client.gui.base;

import com.smartbt.vtsuite.vtams.client.gui.listener.ListListener;
import com.smartgwt.client.data.Record;
import java.util.ArrayList;
import java.util.Iterator;

import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.types.SortArrow;
import com.smartgwt.client.types.SortDirection;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.events.DataArrivedEvent;
import com.smartgwt.client.widgets.grid.events.DataArrivedHandler;
import com.smartgwt.client.widgets.grid.events.RecordDoubleClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordDoubleClickHandler;
import com.smartgwt.client.widgets.grid.events.SelectionChangedHandler;
import com.smartgwt.client.widgets.grid.events.SelectionEvent;

/**
 * The Base ListGrid
 * 
 * @author Ariamnet Lopez
 */
public class BaseListGrid extends ListGrid {

    private ArrayList<ListListener> listeners = new ArrayList<ListListener>();

    /**
     * Add List listener
     *
     * @param listener the List listener
     */
    public void addListener(ListListener listener) {
        listeners.add(listener);
    }

    /**
     * Removes List listener
     *
     * @param listener the List listener
     */
    public void removeListener(ListListener listener) {
        listeners.remove(listener);
    }

    /**
     * Select action to execute
     *
     * @param record the selected record
     */
    public void SelectActionExecuted(Record record) {
        for (ListListener listener : listeners) {
            listener.SelectActionExecuted(record);
        }
    }

    /**
     * Selection Change action to execute
     *
     * @param record the selected record
     */
    public void SelectionChangeActionExecuted(Record record) {
        for (ListListener listener : listeners) {
            listener.SelectionChangeActionExecuted(record);
        }
    }

    /**
     * Data Arrived action to execute
     *
     */
    public void DataArrivedActionExecuted() {
        for (ListListener listener : listeners) {
            listener.DataArrivedHandlerExecuted();
        }
    }

    /**
     * Constructor
     *
     */
    public BaseListGrid() {
        super();

        setEmptyMessage("");
        setSelectionType(SelectionStyle.SINGLE);
        setWidth100();

        setSortDirection(SortDirection.ASCENDING);
        setShowSortArrow(SortArrow.CORNER);

        addRecordDoubleClickHandler(new RecordDoubleClickHandler() {
            public void onRecordDoubleClick(RecordDoubleClickEvent event) {
                SelectActionExecuted(event.getRecord());
            }
        });

        addSelectionChangedHandler(new SelectionChangedHandler() {
            public void onSelectionChanged(SelectionEvent event) {
                SelectionChangeActionExecuted(event.getRecord());
            }
        });

        addDataArrivedHandler(new DataArrivedHandler() {
            public void onDataArrived(DataArrivedEvent event) {
                DataArrivedActionExecuted();
            }
        });
    }
}
