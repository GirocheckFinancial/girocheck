/*
 ** File: UserDS.java
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

import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartbt.vtsuite.vtams.client.classes.Properties;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseDatasource;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.fields.DataSourceDateField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.FieldType;

/**
 * The User DataSource
 *
 * @author Ariamnet Lopez, Ariel Saavedra
 */
public class AuditLogDS extends BaseDatasource {

    /**
     * Constructor
     *
     * @param entityType
     */
    public AuditLogDS(EntityType entityType) {

        DataSourceTextField usernameField = new DataSourceTextField("information");
        DataSourceDateField createdAtField = new DataSourceDateField("createdAt");

        DataSourceField userField = new DataSourceField("user", FieldType.ANY);
        userField.setTypeAsDataSource(new UserDS(entityType));

        DataSourceField merchantField = new DataSourceField("merchant", FieldType.ANY);
        merchantField.setTypeAsDataSource(new UserDS(entityType));

        DataSourceField terminalField = new DataSourceField("terminal", FieldType.ANY);
        terminalField.setTypeAsDataSource(new UserDS(entityType));

        setFetchDataURL(Properties.SEARCH_AUDIT_LOGS);

        setFields(usernameField,
                createdAtField,
                userField,
                merchantField,
                terminalField);
    }
}
