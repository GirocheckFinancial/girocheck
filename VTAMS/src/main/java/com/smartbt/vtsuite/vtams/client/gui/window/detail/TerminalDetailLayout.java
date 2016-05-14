/*
 ** File: TerminalDetailLayout.java
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
import com.smartbt.vtsuite.vtams.client.gui.base.BaseDatasource;
import com.smartbt.vtsuite.vtams.client.gui.base.detail.BaseDetailLayout;
import com.smartbt.vtsuite.vtams.client.gui.base.detail.BaseDetailRowHLayout;
import com.smartgwt.client.data.Record;

/**
 * The Terminal Detail Layout
 *
 * @author Ariel Saavedra
 */
public class TerminalDetailLayout extends BaseDetailLayout {

    public TerminalDetailLayout(Record record) {
        super(record);
    }

    public TerminalDetailLayout(Record record, boolean allDetails, BaseDatasource datasource) {
        super(record, allDetails, datasource);
    }

    @Override
    protected void addLeftTopFields() {
        vLayoutLeftTop.addMembers(
                BaseDetailRowHLayout.getRow(I18N.GET.DETAIL_TERMINAL_ID(), record.getAttributeAsString("terminalId")),
                BaseDetailRowHLayout.getRow(I18N.GET.DETAIL_DESCRIPTION(), record.getAttributeAsString("description")));
    }

    @Override
    protected void addRightTopFields() {
        vLayoutRightTop.addMembers(
                BaseDetailRowHLayout.getRow(I18N.GET.DETAIL_SERIAL_NUMBER(), record.getAttributeAsString("serialNumber")),
                BaseDetailRowHLayout.getRow(I18N.GET.DETAIL_STATUS(), (record.getAttributeAsBoolean("active") ? "Active" : "Inactive")));
    }

    @Override
    protected void addLeftBottomFields() {
        vLayoutLeftBottom.addMember(
                BaseDetailRowHLayout.getRow(I18N.GET.DETAIL_TERMINAL_DESCRIPTION(), record.getAttributeAsString("description")));
        vLayoutLeftBottom.addMember(
                BaseDetailRowHLayout.getRow(I18N.GET.DETAIL_CUSTOMER_NAME(), record.getAttributeAsString("customerName")));
        vLayoutLeftBottom.addMember(
                BaseDetailRowHLayout.getRow(I18N.GET.DETAIL_MERCHANT_NAME(), record.getAttributeAsString("merchantName")));
        vLayoutLeftBottom.addMember(
                BaseDetailRowHLayout.getRow(I18N.GET.DETAIL_APPLICATION(), record.getAttributeAsString("application")));
//        vLayoutLeftBottom.addMember(
//                BaseDetailRowHLayout.getRow(I18N.GET.DETAIL_PRODUCT_TYPE(), record.getAttributeAsString("productType")));
    }

    @Override
    protected void addRightBottomFields() {
        vLayoutRightBottom.addMembers(
                BaseDetailRowHLayout.getRow(I18N.GET.DETAIL_ACTIVATION_DATE(), record.getAttributeAsString("activationDate")),
                BaseDetailRowHLayout.getRow(I18N.GET.DETAIL_DEACTIVATION_DATE(), record.getAttributeAsString("deactivationDate"))
        );
    }
}
