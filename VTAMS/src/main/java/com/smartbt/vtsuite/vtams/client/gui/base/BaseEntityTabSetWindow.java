/*
 ** File: BaseEntityTabSetWindow.java
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

import com.smartgwt.client.widgets.layout.VLayout;

/**
 * The Base Entity TabSet Window
 *
 * @author Ariamnet Lopez
 */
public class BaseEntityTabSetWindow extends BaseWindow {

    /**
     * The Base Management Window
     */
    public BaseManagementWindow managementWindow;

    /**
     * Constructor
     *
     */
    public BaseEntityTabSetWindow() {
        super();

        setHeight100();
        setWidth100();
        setShowHeader(false);
        setShowEdges(false);
        setStyleName("base-entity-window");
        setBodyStyle("base-entity-window");

        managementWindow = new BaseManagementWindow();

        VLayout mainVLayout = new VLayout();
        mainVLayout.setHeight100();
        mainVLayout.addMember(managementWindow);

        addItem(mainVLayout);
    }
}
