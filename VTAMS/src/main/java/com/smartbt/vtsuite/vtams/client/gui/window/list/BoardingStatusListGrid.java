/*
 ** File: BoardingStatusListGrid.java
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

import com.smartbt.vtsuite.vtams.client.classes.i18n.I18N;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseListGrid;
import com.smartbt.vtsuite.vtams.client.gui.component.DateListGridField;
import com.smartbt.vtsuite.vtams.client.gui.component.TextListGridField;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.BoardingStatusDS;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.SortNormalizer;

/**
 * The Boarding ListGrid
 *
 * @author Ariamnet Lopez
 */
public class BoardingStatusListGrid extends BaseListGrid {

    private TextListGridField nameField = new TextListGridField("name", I18N.GET.LIST_FIELD_NAME_TITLE(), false);
    private TextListGridField sicCodeField = new TextListGridField("sicCode", I18N.GET.LIST_FIELD_SICCODE_TITLE(), false);
    private TextListGridField terminalIDField = new TextListGridField("terminalID", I18N.GET.LIST_FIELD_TERMINAL_NUMBER_TITLE(), false);
    private TextListGridField merchantNumberField = new TextListGridField("merchantNumber", I18N.GET.LIST_FIELD_MERCHANT_NUMBER_TITLE(), false);
    private TextListGridField customerNumberField = new TextListGridField("customerNumber", I18N.GET.LIST_FIELD_CUSTOMER_NUMBER_TITLE(), false);
    private TextListGridField descriptionField = new TextListGridField("description", I18N.GET.LIST_FIELD_DESCRIPTION_TITLE(), false);
    private DateListGridField activationDateField = new DateListGridField("activationDate", I18N.GET.LIST_FIELD_ACTIVATION_DATE_TITLE(), false);
    private TextListGridField statusField = new TextListGridField("active", I18N.GET.LIST_FIELD_STATUS_TITLE(), false);
    private TextListGridField boardedField = new TextListGridField("boarded", I18N.GET.LIST_FIELD_BOARDED_TITLE(), false);

    /**
     * c
     * Constructor
     *
     */
    public BoardingStatusListGrid() {
        super();

        statusField.setSortNormalizer(new SortNormalizer() {
            public Object normalize(ListGridRecord record, String fieldName) {
                return record.getAttributeAsDate("createdAt");
            }
        });

        statusField.setCellFormatter(new CellFormatter() {
            public String format(Object value, ListGridRecord record, int rowNum, int colNum) {
                return record.getAttributeAsBoolean("active") ? "Active" : "Inactive";
            }
        });

        setEmptyMessage(I18N.GET.MESSAGE_EMPTY_BOARDING_STATUS_LIST());

        setDataSource(new BoardingStatusDS());
        setFields(nameField,
                sicCodeField,
                terminalIDField,
                merchantNumberField,
                customerNumberField,
                descriptionField,
                activationDateField,
                statusField,
                boardedField);
    }
}
