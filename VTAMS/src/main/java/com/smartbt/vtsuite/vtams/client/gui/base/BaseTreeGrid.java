/*
 ** File: BaseTreeGrid.java
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

import java.util.ArrayList;
import com.smartbt.vtsuite.vtams.client.gui.listener.TreeListener;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.tree.TreeGrid;

/**
 * The Base Tree Grid
 *
 * @author Ariamnet Lopez
 */
public class BaseTreeGrid extends TreeGrid {

    private ArrayList<TreeListener> listeners = new ArrayList<TreeListener>();

    /**
     * Add Tree listener
     *
     * @param listener the Tree listener
     */
    public void addStandardListener(TreeListener listener) {
        listeners.add(listener);
    }

    /**
     * Remove Tree listener
     *
     * @param listener the Tree listener
     */
    public void removeStandardListener(TreeListener listener) {
        listeners.remove(listener);
    }

    /**
     * Select action to execute
     *
     * @param node the selected record
     */
    public void SelectActionExecuted(BaseTreeNode node) {
        for (TreeListener listener : listeners) {
            listener.SelectActionExecuted(node);
        }
    }

    /**
     * Constructor
     *
     */
    public BaseTreeGrid() {
        setShowHeader(false);
        setLeaveScrollbarGap(false);
        setCanAcceptDroppedRecords(true);
        setCanReparentNodes(false);
        setSelectionType(SelectionStyle.SINGLE);
        setCanAutoFitFields(false);
        setCanResizeFields(false);
        setLoadDataOnDemand(true);
    }

    protected void setAsDragable() {
        setShowEdges(true);
        setBorder("0px");
        setBodyStyleName("normal");
        setShowHeader(false);
        setLeaveScrollbarGap(false);
        setEmptyMessage("<br>Drag & drop parts here");
        //setManyItemsImage("clerk.png");
        //setAppImgDir("icons/");
        //setCanReorderRecords(true);
        setCanAcceptDroppedRecords(true);
        setCanDragRecordsOut(true);
    }
}
