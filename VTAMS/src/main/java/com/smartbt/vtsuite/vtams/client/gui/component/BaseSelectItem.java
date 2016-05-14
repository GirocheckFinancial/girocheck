/*
 ** File: BaseSelectItem.java
 **
 ** Date Created: May 2013
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

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.FormErrorOrientation;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.grid.HoverCustomizer;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 * The Base Select Item
 *
 * @author Ariamnet Lopez
 */
public class BaseSelectItem extends SelectItem {

    /**
     * Constructor
     *
     * @param name the item name
     * @param title the item title
     * @param ds the item datasource
     * @param isRequired Whether a non-empty value is required for this field to
     * pass validation
     */
    public BaseSelectItem(String name, String title, DataSource ds, Boolean isRequired) {
        super(name, title);

        setWrapTitle(false);
        setAddUnknownValues(false);
        setAutoFetchData(false);
        setAllowEmptyValue(false);
        setOptionDataSource(ds);
        setValueField("id");
        setDisplayField("name");
        setHideEmptyPickList(true);
        setRequired(isRequired);
        setErrorOrientation(FormErrorOrientation.RIGHT);

    }

    /**
     * Constructor
     *
     * @param tabIndex
     * @param name the name
     * @param title the title
     * @param tooltip
     * @param ds
     * @param isRequired
     */
    public BaseSelectItem(int tabIndex, String name, String title, String tooltip, DataSource ds, Boolean isRequired) {
        this(name, title, ds, isRequired);

        setTitle(isRequired ? title + "<b> *</b>" : title);

        setRequired(isRequired);
        setTabIndex(tabIndex);
        setTooltip(tooltip);
    }

    public void showDescription() {
        ListGrid pickListProperties = new ListGrid();
        pickListProperties.setCanHover(true);
        pickListProperties.setShowHover(true);
        pickListProperties.setCellHeight(25);
        pickListProperties.setHoverCustomizer(new HoverCustomizer() {
            @Override
            public String hoverHTML(Object value, ListGridRecord record, int rowNum, int colNum) {
                String descStr = record.getAttribute("description");
                if (descStr == null) {
                    descStr = "[no description]";
                }
                return descStr;
            }
        });

        setPickListProperties(pickListProperties);
    }
}
