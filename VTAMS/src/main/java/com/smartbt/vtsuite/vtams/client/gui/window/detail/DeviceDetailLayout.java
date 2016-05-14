/*
 ** File: DeviceDetailLayout.java
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
import com.smartbt.vtsuite.vtams.client.gui.base.detail.BaseDetailLayout;
import com.smartbt.vtsuite.vtams.client.gui.base.detail.BaseDetailRowHLayout;
import com.smartgwt.client.data.Record;

/**
 * The Device Detail Layout
 *
 * @author  Ariel Saavedra
 */
public class DeviceDetailLayout extends BaseDetailLayout {

    public DeviceDetailLayout(Record record) {
        super(record);
    }
 
    @Override
    protected void addLeftTopFields() {
        vLayoutLeftTop.addMembers(
                BaseDetailRowHLayout.getRow(I18N.GET.DETAIL_NAME(), record.getAttributeAsString("name")),
                BaseDetailRowHLayout.getRow(I18N.GET.DETAIL_SERIAL_NUMBER(), record.getAttributeAsString("serialNumber")),
                BaseDetailRowHLayout.getRow(I18N.GET.DETAIL_STATUS(), (record.getAttributeAsBoolean("active") ? "Active" : "Inactive")));
    }

    @Override
    protected void addRightTopFields() {
        vLayoutRightTop.addMembers(
                BaseDetailRowHLayout.getRow(I18N.GET.DETAIL_PRODUCT_CODE(), record.getAttributeAsString("productCode")),
                BaseDetailRowHLayout.getRow(I18N.GET.DETAIL_DESCRIPTION(), record.getAttributeAsString("description")));
    }

    @Override
    protected void addLeftBottomFields() {
    }

    @Override
    protected void addRightBottomFields() {
    }
}
