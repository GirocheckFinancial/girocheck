/*
 ** File: IntegerListGridField.java
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
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.widgets.grid.ListGridField;

/**
 * The Integer ListGrid Field
 *
 * @author Ariamnet Lopez
 */
public class IntegerListGridField extends ListGridField {

    /**
     * Constructor
     *
     * @param name the item name
     * @param title the item title
     * @param isHidden Whether this item is hidden or not
     */
    public IntegerListGridField(String name, String title, Boolean isHidden) {
        super(name, title);

        setAlign(Alignment.LEFT);
        setType(ListGridFieldType.INTEGER);
        setHidden(isHidden);
    }
    
    /**
     * Constructor
     *
     * @param name the item name
     * @param isHidden Whether this item is hidden or not
     */
    public IntegerListGridField(String name, Boolean isHidden) {
        this(name, null, isHidden);
    }
}
