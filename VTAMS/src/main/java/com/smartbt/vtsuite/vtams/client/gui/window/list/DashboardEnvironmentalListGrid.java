/*
 ** File: DashboardListGrid.java
 **
 ** Date Created: December 2013
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


import com.smartbt.vtsuite.vtams.client.classes.i18n.I18N;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseListGrid;
import com.smartbt.vtsuite.vtams.client.gui.component.TextListGridField;
import com.smartbt.vtsuite.vtams.client.gui.component.TimeListGridField;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.DashboardEnvironmentalDS;
import com.smartbt.vtsuite.vtams.client.helpers.DateHelper;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.SortNormalizer;

/**
 * The DashboardListGrid ListGrid
 *
 * @author Ariel Saavedra
 */
public class DashboardEnvironmentalListGrid extends BaseListGrid {

    private TextListGridField taskId = new TextListGridField("taskId",I18N.GET.LIST_FIELD_TASK_ID_TITLE(), false);
    private TextListGridField valueField = new TextListGridField("value",I18N.GET.LIST_FIELD_VALUE_TITLE(), false);
    private TimeListGridField timeField = new TimeListGridField("time", I18N.GET.LIST_FIELD_TIME_UPDATED_TITLE(), false);

    /**
     * Constructor
     *
     */
    public DashboardEnvironmentalListGrid() {
        super();
        timeField.setSortNormalizer(new SortNormalizer() {
            public Object normalize(ListGridRecord record, String fieldName) {
                return DateHelper.to24HourTime(record.getAttributeAsDate("createdAt"));
            }
        });

        timeField.setCellFormatter(new CellFormatter() {
            public String format(Object value, ListGridRecord record, int rowNum, int colNum) {
                return DateHelper.to24HourTime(record.getAttributeAsDate("createdAt"));
            }
        });
        setEmptyMessage(I18N.GET.MESSAGE_EMPTY_DASHBOARD_ENTITY_LIST());
        setDataSource(new DashboardEnvironmentalDS());
        setFields(taskId, valueField, timeField);
    }
}
