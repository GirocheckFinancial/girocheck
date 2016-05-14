/*
 ** File: BaseButtonItem.java
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

import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.form.fields.ButtonItem;

/**
 * The Base Button Item
 *
 * @author Ariamnet Lopez
 */
public class BaseButtonItem extends ButtonItem {

    /**
     * Constructor
     *
     * @param name the item name
     * @param title the item title
     */
    public BaseButtonItem(String name, String title) {
        super(name, title);
        
        setWidth(80);
        setVAlign(VerticalAlignment.BOTTOM);
        setStartRow(false);
        setEndRow(false); 
    }    
}
