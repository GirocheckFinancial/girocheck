/*
 ** File: WatchdogDS.java
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
package com.smartbt.vtsuite.vtams.client.gui.component.datasource.watchdog;

import com.smartbt.vtsuite.vtams.client.classes.Properties;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.*;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseDatasource;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.fields.DataSourceBooleanField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.FieldType;

/**
 * The Watchdog DataSource
 *
 * @author Ariel Saavedra
 */
public class WatchdogDS extends BaseDatasource {

    /**
     * Constructor
     *
     */
    public WatchdogDS() {

        DataSourceTextField nameField = new DataSourceTextField("name");
        DataSourceTextField descriptionField = new DataSourceTextField("description");
        DataSourceBooleanField hasValueField = new DataSourceBooleanField("hasValue");
        DataSourceField dataTypeField = new DataSourceField("dataType", FieldType.ANY);
        dataTypeField.setTypeAsDataSource(new DataTypeDS());

        setFetchDataURL(Properties.GET_WATCHDOGS);
        
        setFields(nameField,
                descriptionField,
                hasValueField,
                dataTypeField);
    }
}
