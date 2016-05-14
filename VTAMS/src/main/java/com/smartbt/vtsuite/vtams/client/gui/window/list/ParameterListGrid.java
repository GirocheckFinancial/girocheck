/*
 ** File: ParameterListGrid.java
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
package com.smartbt.vtsuite.vtams.client.gui.window.list;

import com.smartbt.vtsuite.vtams.client.classes.Constants;

import com.smartbt.vtsuite.vtams.client.classes.i18n.I18N;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseListGrid;
import com.smartbt.vtsuite.vtams.client.gui.component.TextListGridField;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.parameter.ParameterDS;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.parameter.ParameterValueDS;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 * The Parameter Value ListGrid
 *
 * @author Ariamnet Lopez, Ariel Saavedra
 */
public class ParameterListGrid extends BaseListGrid {

    private TextListGridField parameterField = new TextListGridField("parameter", I18N.GET.LIST_FIELD_PARAMETER_TITLE(), false);
    private TextListGridField descriptionField = new TextListGridField("description", I18N.GET.LIST_FIELD_DESCRIPTION_TITLE(), false);
    private TextListGridField valueField = new TextListGridField("value", I18N.GET.LIST_FIELD_VALUE_TITLE(), false);
    private TextListGridField readOnlyField = new TextListGridField("readOnly", I18N.GET.LIST_FIELD_READ_ONLY_TITLE(), false);

    /**
     * Constructor
     *
     * @param entityType
     */
    public ParameterListGrid(EntityType entityType) {
        super();
        setEmptyMessage(I18N.GET.MESSAGE_EMPTY_PARAMETER_LIST());

        valueField.setCellFormatter(new CellFormatter() {
            public String format(Object value, ListGridRecord record, int rowNum, int colNum) {
                String result = record.getAttributeAsString("value");
                if (result.equalsIgnoreCase(Constants.CONSTANT_TRUE)) {
                    result = Constants.CONSTANT_ENABLED;
                } else if (result.equalsIgnoreCase(Constants.CONSTANT_FALSE)) {
                    result = Constants.CONSTANT_DISABLED;
                }

                return result;
            }
        });
        readOnlyField.setCellFormatter(new CellFormatter() {
            public String format(Object value, ListGridRecord record, int rowNum, int colNum) {
                String result = record.getAttributeAsString("readOnly");
                return result == null ? " - " : result.toUpperCase();
            }
        });

//        if (entityType == EntityType.APPLICATION_PARAMETER) {
//            parameterField.setDataPath("parameter");
//            descriptionField.setDataPath("description");
//            valueField.setTitle("Default Value");
//
//            setDataSource(new ParameterDS(entityType));
//            setFields(parameterField, descriptionField, valueField, readOnlyField);
//        } else {
            parameterField.setDataPath("parameter/parameter");
            descriptionField.setDataPath("parameter/description");
            setDataSource(new ParameterValueDS(entityType));
            setFields(parameterField, descriptionField, valueField);
       // }

    }
}
