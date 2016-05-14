/*
 ** File: AuditLogListGrid.java
 **
 ** Date Created: October 2013
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
import com.smartbt.vtsuite.vtams.client.gui.component.DateListGridField;
import com.smartbt.vtsuite.vtams.client.gui.component.TextListGridField;
import com.smartbt.vtsuite.vtams.client.gui.component.TimeListGridField;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.AuditLogDS;
import com.smartbt.vtsuite.vtams.client.helpers.DateHelper;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.SortNormalizer;

/**
 * The AuditLog ListGrid
 *
 * @author Ariel Saavedra
 */
public class AuditLogListGrid extends BaseListGrid {
    
    private TextListGridField userField = new TextListGridField("user",I18N.GET.LIST_FIELD_USER_TITLE(), false);
    private TextListGridField informationField = new TextListGridField("information",I18N.GET.LIST_FIELD_INFORMATION_TITLE(), false);
    private DateListGridField dateField = new DateListGridField("date", I18N.GET.LIST_FIELD_DATE_TITLE(), false);
    private TimeListGridField timeField = new TimeListGridField("time", I18N.GET.LIST_FIELD_TIME_TITLE(), false);

    /**
     * Constructor
     *
     * @param entityType
     */
    public AuditLogListGrid(EntityType entityType) {
        super();
        userField.setDataPath("user/username");
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
        setEmptyMessage(I18N.GET.MESSAGE_EMPTY_AUDITLOGS_LIST());
        setDataSource(new AuditLogDS(entityType));
        setFields(userField, informationField, dateField, timeField);
        resizeField(0, 200);
    }
}
