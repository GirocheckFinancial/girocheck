/*
 ** File: BaseEntityDetailWindow.java
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

import com.smartbt.vtsuite.vtams.client.gui.base.BaseWindow; 
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * The Base Entity Detail Window
 *
 * @author Roberto Rodriguez
 */
public class InventoryWindow extends BaseWindow {

    protected VLayout mainLayout = new VLayout();

    protected SectionStack inventoryStack = new SectionStack();

    protected SectionStackSection inventoryStackSection = new SectionStackSection(" Card Inventory");

    protected InventoryGridPanel inventoryGridPanel;

    public InventoryWindow() {
        super(); 
        setHeight100();
        setWidth100();
        setShowHeader(false);
        setShowEdges(false);
        setStyleName("base-entity-window");
        setBodyStyle("base-entity-window"); 
        inventoryStackSection.setExpanded(true);
        inventoryStackSection.setCanCollapse(false);
      
        inventoryGridPanel = new InventoryGridPanel();
        inventoryGridPanel.setHeight100();
       
        inventoryStackSection.addItem(inventoryGridPanel);
  
        inventoryStack.addSection(inventoryStackSection);
     
        inventoryStack.setVisibilityMode(VisibilityMode.MULTIPLE);
 
        mainLayout.setMargin(1); 
        mainLayout.addMember(inventoryStack);

        
        
        addItem(mainLayout); 
        inventoryGridPanel.Filter(); 
    }

    /*
     OPTION:
     1 - onLoad
     2 - onSearch
     3 - onPageChange
     */


}
