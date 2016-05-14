/*
 ** File: WatchdogEntityListGrid.java
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
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.watchdog.WatchdogEntityDS;
import com.smartbt.vtsuite.vtams.client.helpers.DateHelper;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.SortNormalizer;

/**
 * The WatchdogEntity ListGrid
 *
 * @author Ariel Saavedra
 */
public class WatchdogEntityListGrid extends BaseListGrid {

    private TextListGridField nameWatchdogField = new TextListGridField("name",I18N.GET.LIST_FIELD_NAME_TITLE(), false);
    private TextListGridField descriptionWatchdogField = new TextListGridField("description",I18N.GET.LIST_FIELD_DESCRIPTION_TITLE(), false);
    private TextListGridField valueField = new TextListGridField("value",I18N.GET.LIST_FIELD_VALUE_TITLE(), false);
    private TextListGridField createdByField = new TextListGridField("createdBy", I18N.GET.LIST_FIELD_CREATED_BY_TITLE(), false);
    private DateListGridField dateField = new DateListGridField("date", I18N.GET.LIST_FIELD_DATE_TITLE(), false);
    private TimeListGridField timeField = new TimeListGridField("time", I18N.GET.LIST_FIELD_TIME_TITLE(), false);

    /**
     * Constructor
     *
     */
    public WatchdogEntityListGrid() {
        super();
        nameWatchdogField.setDataPath("watchdog/name");
        descriptionWatchdogField.setDataPath("watchdog/description");
        
        createdByField.setCellFormatter(new CellFormatter() {
            public String format(Object value, ListGridRecord record, int rowNum, int colNum) {
                if(record.getAttributeAsRecord("clerkCreator")!=null){
                    return record.getAttributeAsRecord("clerkCreator").getAttributeAsString("username");
                }else if(record.getAttributeAsRecord("userCreator")!=null){
                    return record.getAttributeAsRecord("userCreator").getAttributeAsString("username");
                }else{
                 return "-";   
                }
            }
        });
        
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
        setEmptyMessage(I18N.GET.MESSAGE_EMPTY_WATCHDOG_ENTITY_LIST());
        setDataSource(new WatchdogEntityDS());
        setFields(nameWatchdogField, descriptionWatchdogField, valueField, createdByField, dateField, timeField);
        resizeField(0, 200);
    }
}
