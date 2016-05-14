/*
 ** File: BoardingStatusDS.java
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
package com.smartbt.vtsuite.vtams.client.gui.component.datasource;

import com.smartbt.vtsuite.vtams.client.classes.Properties;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseDatasource;
import com.smartgwt.client.data.fields.DataSourceBooleanField;
import com.smartgwt.client.data.fields.DataSourceDateField;
import com.smartgwt.client.data.fields.DataSourceTextField;

/**
 * The Boarding Status DataSource
 *
 * @author Ariamnet Lopez
 */
public class BoardingStatusDS extends BaseDatasource {

    /**
     * Constructor
     *
     */
    public BoardingStatusDS() {

        DataSourceTextField nameField = new DataSourceTextField("name");
        DataSourceTextField sicCodeField = new DataSourceTextField("sicCode");
        DataSourceTextField terminalIDField = new DataSourceTextField("terminalID");
        DataSourceTextField merchantNumberField = new DataSourceTextField("merchantNumber");
        DataSourceTextField customerNumberField = new DataSourceTextField("customerNumber");
        DataSourceTextField descriptionField = new DataSourceTextField("description");
        DataSourceDateField activationDateField = new DataSourceDateField("activationDate");
        DataSourceBooleanField statusField = new DataSourceBooleanField("active");
        DataSourceTextField boardedField = new DataSourceTextField("boarded");

        //setJsonRecordXPath("boarding");
        setFetchDataURL(Properties.SEARCH_BOARDING_STATUS_WS);

        setFields(nameField,
                sicCodeField,
                terminalIDField,
                merchantNumberField,
                customerNumberField,
                descriptionField,
                statusField,
                boardedField,
                activationDateField);
    }
}
