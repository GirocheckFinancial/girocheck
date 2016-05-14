/*
 ** File: WatchdogAlertDS.java
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
import com.smartbt.vtsuite.vtams.client.gui.base.BaseDatasource;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.UserDS;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.fields.DataSourceDateField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.FieldType;

/**
 * The WatchdogAlert DataSource
 *
 * @author Ariel Saavedra
 */
public class WatchdogAlertDS extends BaseDatasource {

    /**
     * Constructor
     *
     */
    public WatchdogAlertDS() {

        DataSourceTextField valueField = new DataSourceTextField("value");
        DataSourceDateField createdAtField = new DataSourceDateField("createdAt");

        DataSourceField watchdogEntityField = new DataSourceField("watchdog", FieldType.ANY);
        watchdogEntityField.setTypeAsDataSource(new WatchdogDS());

        DataSourceField clerkOriginationField = new DataSourceField("clerkOrigination", FieldType.ANY);
        clerkOriginationField.setTypeAsDataSource(new UserDS(EntityType.CLERK));

        DataSourceField userOriginationField = new DataSourceField("userOrigination", FieldType.ANY);
        userOriginationField.setTypeAsDataSource(new UserDS(EntityType.CLERK));

        setFetchDataURL(Properties.GET_WATCHDOG_ALERTS_BY_ENTITY);
        setRemoveDataURL(Properties.DELETE_WATCHDOG_ALERT);

        setFields(valueField,
                createdAtField,
                watchdogEntityField,
                clerkOriginationField,
                userOriginationField);
    }
}
