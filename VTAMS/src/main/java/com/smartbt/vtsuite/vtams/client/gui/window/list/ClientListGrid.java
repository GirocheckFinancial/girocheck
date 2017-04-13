/*
 ** File: ClientListGrid.java
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
import com.smartbt.vtsuite.vtams.client.gui.component.TextListGridField;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.ClientDS;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import static com.smartbt.vtsuite.vtcommon.enums.EntityType.CUSTOMER;
import com.smartgwt.client.types.GroupStartOpen;
import com.smartgwt.client.widgets.grid.events.DataArrivedEvent;
import com.smartgwt.client.widgets.grid.events.DataArrivedHandler;

/**
 * The Transaction ListGrid
 *
 * @author Ariel Saavedra
 */
public class ClientListGrid extends BaseListGrid {

    private TextListGridField firstNameField = new TextListGridField("firstName", I18N.GET.LIST_FIELD_FIRST_NAME_TITLE(), false);
    private TextListGridField lastField = new TextListGridField("lastName", I18N.GET.LIST_FIELD_LAST_NAME_TITLE(), false);
    private TextListGridField telephoneField = new TextListGridField("telephone", I18N.GET.LIST_FIELD_TELEPHONE_TITLE(), false);
    private TextListGridField addressField = new TextListGridField("address", I18N.GET.LIST_FIELD_ADDRESS_TITLE(), false);
//    private TextListGridField address2Field = new TextListGridField("address2", I18N.GET.LIST_FIELD_ADDRESS1_TITLE(), false);
    private TextListGridField cityField = new TextListGridField("city", I18N.GET.LIST_FIELD_CITY_TITLE(), false);
    private TextListGridField stateField = new TextListGridField("state", I18N.GET.LIST_FIELD_STATE_TITLE(), false);
    private TextListGridField zipField = new TextListGridField("zipcode", I18N.GET.LIST_FIELD_ZIP_TITLE(), false);
    private TextListGridField maskSSField = new TextListGridField("maskSS", I18N.GET.LIST_FIELD_MSSN_TITLE(), false);
    private TextListGridField blackListField = new TextListGridField("blackList", "Black List", false);
    private TextListGridField optOutField = new TextListGridField("optOut", "SMS Opt Out", false);
//    private TextListGridField statusField = new TextListGridField("active", I18N.GET.LIST_FIELD_ACTIVE_TITLE(), false);
//    private TextListGridField merchantField = new TextListGridField("merchant", I18N.GET.LIST_FIELD_MERCHANT_TITLE(), true);

    /**
     * Constructor
     *
     * @param entityType
     */
    public ClientListGrid(final EntityType entityType) {
        super();
//        addressField.setDataPath("addressList/address");
//        address2Field.setDataPath("clientAddressList/address2");
//        cityField.setDataPath("address/city");
//        stateField.setDataPath("address/state/name");
//        zipField.setDataPath("address/zipcode");
//        merchantField.setDataPath("merchant/name");

//        statusField.setCellFormatter(new CellFormatter() {
//            public String format(Object value, ListGridRecord record, int rowNum, int colNum) {
//                return record.getAttributeAsBoolean("active") ? "Active" : "Inactive";
//            }
//        });
        setEmptyMessage(I18N.GET.MESSAGE_EMPTY_CLIENT_LIST());
//        setInitialSort(new SortSpecifier[]{
//            new SortSpecifier("firstName", SortDirection.DESCENDING)
//        });

        setGroupStartOpen(GroupStartOpen.ALL);
        addDataArrivedHandler(new DataArrivedHandler() {
            public void onDataArrived(DataArrivedEvent event) {
                if (entityType != null) {
                    switch (entityType) {
                        case CUSTOMER:
                            groupBy("merchant");
                            break;
                    }
                }
            }
        });

        setDataSource(new ClientDS());
        setFields(firstNameField,
                lastField,
                telephoneField,
                //                companyField,
                addressField,
                //                address2Field,
                cityField,
                stateField,
                zipField,
                maskSSField,
                blackListField,
                optOutField);
    }
}
