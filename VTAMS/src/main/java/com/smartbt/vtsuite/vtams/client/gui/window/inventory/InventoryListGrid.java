/*
 ** File: TransactionListGrid.java
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
package com.smartbt.vtsuite.vtams.client.gui.window.inventory;
 
import com.smartbt.vtsuite.vtams.client.gui.base.BaseListGrid;
import com.smartbt.vtsuite.vtams.client.gui.component.TextListGridField; 
import com.smartgwt.client.data.SortSpecifier;
import com.smartgwt.client.types.SortDirection;

/**
 * The Transaction ListGrid
 *
 * @author Ariamnet Lopez, Ariel Saavedra
 */
public class InventoryListGrid extends BaseListGrid {

    private TextListGridField merchantField = new TextListGridField("merchant", "Merchant", false);
    private TextListGridField inventoryField = new TextListGridField("inventory", "Card Inventory", false);
    private TextListGridField thresholdField = new TextListGridField("threshold", "Alert Threshold", false);
   

    /**
     * Constructor
     *
     * @param entityType
     */
    public InventoryListGrid() {
        super();
 
        setEmptyMessage("There are not merchants to show.");
        setInitialSort(new SortSpecifier[]{
            new SortSpecifier("merchant", SortDirection.ASCENDING)
        });
        
        setHeight(400);
        setWidth("1000");
        setDataSource(new InventoryDS());
        
        
        setFields(merchantField,
                inventoryField,
                thresholdField);
   //     resizeField(2, 110);
    }
}
