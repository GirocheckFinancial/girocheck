/*
 ** File: BaseStaticTextItem.java
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

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.form.fields.StaticTextItem;

/**
 * The Base Static Text Item
 *
 * @author Ariamnet Lopez
 *
 */
public class BaseStaticTextItem extends StaticTextItem {

    /**
     * Constructor
     *
     * @param name the item name
     * @param title the item title
     */
    public BaseStaticTextItem(String name, String title) {
        super(name, title);

        setShowTitle(true);
        setWrap(false);
        setTitleAlign(Alignment.LEFT);
        setTextAlign(Alignment.LEFT);

        setTextBoxStyle("detail-text");
        setTitleStyle("detail-title");
    }

    /**
     * Constructor
     *
     * @param name the item name
     */
    public BaseStaticTextItem(String name) {
        this(name, "");
    }
}
