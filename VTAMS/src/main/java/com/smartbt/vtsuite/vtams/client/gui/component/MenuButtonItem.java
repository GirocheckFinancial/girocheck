/*
 ** File: MenuButtonItem.java
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
package com.smartbt.vtsuite.vtams.client.gui.component;

import com.smartgwt.client.types.SelectionType;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

/**
 * The Menu Button
 *
 * @author Ariamnet Lopez
 */
public class MenuButtonItem extends ToolStripButton {

    /**
     * Constructor
     *
     * @param title the item title
     */
    public MenuButtonItem(String title) {
        super(title);

        setActionType(SelectionType.RADIO);
        setRadioGroup("menuSelection");
    }
}
