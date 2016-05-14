/*
 ** File: MonitoringListGrid.java
 **
 ** Date Created: January 2014
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
import com.smartbt.vtsuite.vtams.client.gui.base.detail.BaseDetailRowHLayout;
import com.smartbt.vtsuite.vtams.client.gui.base.detail.BaseDetailVLayout;
import com.smartbt.vtsuite.vtams.client.gui.component.DateListGridField;
import com.smartbt.vtsuite.vtams.client.gui.component.TextListGridField;
import com.smartbt.vtsuite.vtams.client.gui.component.TimeListGridField;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.MonitoringDS;
import com.smartbt.vtsuite.vtams.client.helpers.DateHelper;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartgwt.client.types.GroupStartOpen;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.SortNormalizer;
import com.smartgwt.client.widgets.grid.events.DataArrivedEvent;
import com.smartgwt.client.widgets.grid.events.DataArrivedHandler;

/**
 * The Monitoring ListGrid
 *
 * @author Ariel Saavedra
 */
public class MonitoringListGrid extends BaseListGrid {

    private TextListGridField clerkField = new TextListGridField("clerk", I18N.GET.LIST_FIELD_CLERK_TITLE(), false);
    private TextListGridField hostField = new TextListGridField("host", I18N.GET.LIST_FIELD_HOST_TITLE(), false);
    private TextListGridField functionField = new TextListGridField("function", I18N.GET.LIST_FIELD_FUNCTION_TITLE(), false);
    private DateListGridField dateField = new DateListGridField("date", I18N.GET.LIST_FIELD_DATE_TITLE(), false);
    private TimeListGridField timeField = new TimeListGridField("time", I18N.GET.LIST_FIELD_TIME_TITLE(), false);
    private TextListGridField terminalField = new TextListGridField("terminal", I18N.GET.LIST_FIELD_TERMINAL_TITLE(), true);

    /**
     * Constructor
     *
     * @param entityType
     */
    public MonitoringListGrid(final EntityType entityType) {
        super();
        clerkField.setDataPath("clerk/username");
        terminalField.setDataPath("terminal/terminalId");

        dateField.setSortNormalizer(new SortNormalizer() {
            public Object normalize(ListGridRecord record, String fieldName) {
                return record.getAttributeAsDate("createdAt");
            }
        });

        dateField.setCellFormatter(new CellFormatter() {
            public String format(Object value, ListGridRecord record, int rowNum, int colNum) {
                return DateHelper.toJapaneseDate(record.getAttributeAsDate("createdAt"));
            }
        });

        timeField.setSortNormalizer(new SortNormalizer() {
            public Object normalize(ListGridRecord record, String fieldName) {
                return DateHelper.toJapaneseTime(record.getAttributeAsDate("createdAt"));
            }
        });

        timeField.setCellFormatter(new CellFormatter() {
            public String format(Object value, ListGridRecord record, int rowNum, int colNum) {
                return DateHelper.toJapaneseTime(record.getAttributeAsDate("createdAt"));
            }
        });

        setGroupStartOpen(GroupStartOpen.ALL);
        addDataArrivedHandler(new DataArrivedHandler() {
            public void onDataArrived(DataArrivedEvent event) {
                switch (entityType) {
                    case CUSTOMER:
                    case MERCHANT:
                        groupBy("terminal");
                        break;
                    case TERMINAL:
                        break;
                }
            }
        });

        setCanSelectText(true);
        setCanExpandRecords(true);

        setEmptyMessage(I18N.GET.MESSAGE_EMPTY_MONITORING_LIST());
        setDataSource(new MonitoringDS());
        setFields(clerkField, hostField, functionField, terminalField, dateField, timeField);
    }

    @Override
    protected Canvas getExpansionComponent(ListGridRecord record) {
        BaseDetailRowHLayout rowMethod = new BaseDetailRowHLayout("Content", record.getAttributeAsString("method"));
        rowMethod.getContentLabel().setWidth("60%");
        rowMethod.setStyleName("monitoringDetailsFirstRow");

        BaseDetailRowHLayout rowUserAgent = new BaseDetailRowHLayout("User-Agent", record.getAttributeAsString("userAgent"));
        rowUserAgent.getContentLabel().setWidth("60%");
        rowUserAgent.setStyleName("monitoringDetailsRow");

        BaseDetailRowHLayout rowContentType = new BaseDetailRowHLayout("Content-Type", record.getAttributeAsString("contentType"));
        rowContentType.getContentLabel().setWidth("60%");
        rowContentType.setStyleName("monitoringDetailsRow");

        BaseDetailRowHLayout rowContent = new BaseDetailRowHLayout("Content", record.getAttributeAsString("content"));
        rowContent.getContentLabel().setCanSelectText(true);
        rowContent.getContentLabel().setWidth("60%");
        rowContent.setStyleName("monitoringDetailsRow");

        BaseDetailVLayout container = new BaseDetailVLayout(rowMethod, rowUserAgent, rowContentType, rowContent);
        container.setStyleName("monitoringDetails");
        container.setWidth100();
        return container;
    }
}
