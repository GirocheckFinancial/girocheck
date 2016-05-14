/*
 ** File: BaseTab.java
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

import com.smartbt.vtsuite.vtams.client.gui.component.PaginationForm;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;

/**
 * The Base Tab
 *
 * @author Ariamnet Lopez, Ariel Saavedra
 */
public class BaseTab extends Tab {

    protected VLayout mainVLayout;
    protected HLayout filterLayout;
    protected VLayout listLayout;
    protected PaginationForm paginationForm;

    /**
     * Constructor
     *
     * @param title the tab title
     */
    public BaseTab(String title) {
        super(title);
        
        mainVLayout = new VLayout();
        
        paginationForm = new PaginationForm();

        filterLayout = new HLayout();
        filterLayout.setAutoHeight();
        listLayout = new VLayout();

        mainVLayout.addMember(filterLayout);
        mainVLayout.addMember(listLayout);

        setPane(mainVLayout);
    }

    public VLayout getMainVLayout() {
        return mainVLayout;
    }
}
