/*
 ** File: WatchdogEntityDS.java
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
 * The WatchdogEntity DataSource
 *
 * @author Ariel Saavedra
 */
public class WatchdogEntityDS extends BaseDatasource {

    /**
     * Constructor
     *
     */
    public WatchdogEntityDS() {

        DataSourceTextField valueField = new DataSourceTextField("value");
        DataSourceDateField createdAtField = new DataSourceDateField("createdAt");

        DataSourceField watchdogField = new DataSourceField("watchdog", FieldType.ANY);
        watchdogField.setTypeAsDataSource(new WatchdogDS());

        DataSourceField watchdogEntityClerkField = new DataSourceField("watchdogEntityClerk", FieldType.ANY);
        watchdogEntityClerkField.setMultiple(Boolean.TRUE);
        watchdogEntityClerkField.setTypeAsDataSource(new WatchdogEntityClerkDS());

        DataSourceField clerkCreatorField = new DataSourceField("clerkCreator", FieldType.ANY);
        clerkCreatorField.setTypeAsDataSource(new UserDS(EntityType.CLERK));

        DataSourceField userCreatorField = new DataSourceField("userCreator", FieldType.ANY);
        userCreatorField.setTypeAsDataSource(new UserDS(EntityType.AMS));

        setFetchDataURL(Properties.GET_WATCHDOG_ENTITIES_BY_ENTITY);
        setAddDataURL(Properties.SAVE_OR_UPDATE_WATCHDOG_ENTITY);
        setRemoveDataURL(Properties.DELETE_WATCHDOG_ENTITY);

        setFields(valueField,
                createdAtField,
                watchdogField,
                watchdogEntityClerkField,
                clerkCreatorField,
                userCreatorField
        );
    }
}
