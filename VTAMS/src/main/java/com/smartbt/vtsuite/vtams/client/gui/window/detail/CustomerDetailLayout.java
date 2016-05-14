/*
 ** File: CustomerDetailLayout.java
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
package com.smartbt.vtsuite.vtams.client.gui.window.detail;

import com.smartbt.vtsuite.vtams.client.classes.i18n.I18N;
import com.smartbt.vtsuite.vtams.client.gui.base.detail.BaseDetailRowHLayout;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseDatasource;
import com.smartbt.vtsuite.vtams.client.gui.base.detail.BaseDetailLayout;
import com.smartgwt.client.data.Record;

/**
 * The Customer Detail Layout
 *
 * @author  Ariel Saavedra
 */
public class CustomerDetailLayout extends BaseDetailLayout {

    public CustomerDetailLayout(Record record) {
        super(record);
    }

    public CustomerDetailLayout(Record record, boolean allDetails, BaseDatasource datasource) {
        super(record, allDetails, datasource);
    }

    @Override
    protected void addLeftTopFields() {
        vLayoutLeftTop.addMembers(
                BaseDetailRowHLayout.getRow(I18N.GET.DETAIL_NAME(), record.getAttributeAsString("name")),
                BaseDetailRowHLayout.getRow(I18N.GET.DETAIL_DESCRIPTION(), record.getAttributeAsString("description")));      
    }

    @Override
    protected void addRightTopFields() {
        vLayoutRightTop.addMembers(
                BaseDetailRowHLayout.getRow(I18N.GET.DETAIL_NUMBER(), record.getAttributeAsString("number")),
                BaseDetailRowHLayout.getRow(I18N.GET.DETAIL_STATUS(), (record.getAttributeAsBoolean("active") ? "Active" : "Inactive")));
    }

    @Override
    protected void addLeftBottomFields() {
        vLayoutLeftBottom.addMember(
                BaseDetailRowHLayout.getRow(I18N.GET.DETAIL_CUSTOMER_DESCRIPTION(), record.getAttributeAsString("description")));

        Record[] addressRecords = record.getAttributeAsRecordArray("address");
        for (Record addressRecord : addressRecords) {
            vLayoutLeftBottom.addMember(BaseDetailRowHLayout.getRow(I18N.GET.DETAIL_ADDRESS_LINE1(), addressRecord.getAttributeAsString("address1")));
            vLayoutLeftBottom.addMember(BaseDetailRowHLayout.getRow(I18N.GET.DETAIL_ADDRESS_LINE2(), addressRecord.getAttributeAsString("address2")));
            vLayoutLeftBottom.addMember(BaseDetailRowHLayout.getRow(I18N.GET.DETAIL_CITY(), addressRecord.getAttributeAsString("city")));
            vLayoutLeftBottom.addMember(BaseDetailRowHLayout.getRow(I18N.GET.DETAIL_STATE(), addressRecord.getAttributeAsString("state")));
            vLayoutLeftBottom.addMember(BaseDetailRowHLayout.getRow(I18N.GET.DETAIL_ZIP(), addressRecord.getAttributeAsString("zip")));
            vLayoutLeftBottom.addMember(BaseDetailRowHLayout.getRow(I18N.GET.DETAIL_COUNTRY(), addressRecord.getAttributeAsString("country")));
        }

        Record[] telephonesRecords = record.getAttributeAsRecordArray("telephones");
        for (Record telephonesRecord : telephonesRecords) {
            vLayoutLeftBottom.addMember(BaseDetailRowHLayout.getRow(telephonesRecord.getAttributeAsRecord("telephoneType").getAttributeAsString("name") + ":", 
                    telephonesRecord.getAttributeAsString("number")));
        }
    }

    @Override
    protected void addRightBottomFields() {
        vLayoutRightBottom.addMembers(
                BaseDetailRowHLayout.getRow(I18N.GET.DETAIL_ACTIVATION_DATE(), record.getAttributeAsString("activationDate")),
                BaseDetailRowHLayout.getRow(I18N.GET.DETAIL_DEACTIVATION_DATE(), record.getAttributeAsString("deactivationDate")));
    }
}
