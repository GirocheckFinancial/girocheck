/*
 ** File: BaseDetailLayout.java
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
package com.smartbt.vtsuite.vtams.client.gui.base.detail;

import com.smartbt.vtsuite.vtams.client.gui.base.BaseDatasource;
import com.smartbt.vtsuite.vtams.client.utils.UtilsSmartGWTUtils;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * The Base Detail Layout
 *
 * @author Ariel Saavedra
 */
public abstract class BaseDetailLayout extends VLayout {

    protected BaseDetailVLayout vLayoutLeftTop = new BaseDetailVLayout();
    protected BaseDetailVLayout vLayoutRightTop = new BaseDetailVLayout();
    protected BaseDetailVLayout vLayoutLeftBottom = new BaseDetailVLayout();
    protected BaseDetailVLayout vLayoutRightBottom = new BaseDetailVLayout();
    protected BaseDatasource datasource;
    protected Record record;

    /**
     * Constructor
     *
     * @param record
     */
    public BaseDetailLayout(Record record) {
        this(record, false, null);
    }

    public BaseDetailLayout(Record record, final boolean allDetails, BaseDatasource datasource) {
        super();
        this.vLayoutLeftTop = new BaseDetailVLayout();
        this.vLayoutRightTop = new BaseDetailVLayout();
        this.vLayoutLeftBottom = new BaseDetailVLayout();
        this.vLayoutRightBottom = new BaseDetailVLayout();
        this.datasource = datasource;
        this.record = record;
        setWidth100();
        setMargin(10);
        init(allDetails, datasource);
    }

    private void init(final boolean allDetails, BaseDatasource datasource) {
        if (!allDetails) {
            fillAllFields(allDetails);
        } else {
            // This line is needed to make it run asynchronously
            //  DSRequest dsRequest = new DSRequest();
            // dsRequest.setShowPrompt(false);

            Criteria criteria = new Criteria();
            criteria.addCriteria("id", record.getAttributeAsInt("id"));
            datasource.fetchData(criteria, new DSCallback() {
                public void execute(DSResponse response, Object rawData, DSRequest request) {
                    record = response.getData()[0];
                    fillAllFields(allDetails);
                }
            }, null);
        }
    }

    protected void fillAllFields(boolean allDetails) {
        BaseDetailHLayout hLayoutTop = new BaseDetailHLayout(vLayoutLeftTop, vLayoutRightTop);
        addMember(hLayoutTop);

        addLeftTopFields();
        addRightTopFields();
        setHeight(20);
        if (allDetails) {
            setHeight100();
            addLineSpace();
            BaseDetailHLayout hLayoutBottom = new BaseDetailHLayout(vLayoutLeftBottom, vLayoutRightBottom);
            addMember(hLayoutBottom);

            addLeftBottomFields();
            addRightBottomFields();
        }
    }

    protected abstract void addLeftTopFields();

    protected abstract void addRightTopFields();

    protected abstract void addLeftBottomFields();

    protected abstract void addRightBottomFields();

    private void addLineSpace() {
        addMember(UtilsSmartGWTUtils.getLineSpace());
    }
}
