/*
 ** File: MonitoringDS.java
 **
 ** Date Created: Janauary 2014
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
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.fields.DataSourceDateField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.FieldType;

/**
 * The Monitoring DataSource
 *
 * @author Ariel Saavedra
 */
public class MonitoringDS extends BaseDatasource {

    /**
     * Constructor
     *
     */
    public MonitoringDS() {

        DataSourceTextField hostField = new DataSourceTextField("host");
        DataSourceTextField functionField = new DataSourceTextField("function");
        DataSourceDateField createdAtField = new DataSourceDateField("createdAt");
        DataSourceTextField methodField = new DataSourceTextField("method");
        DataSourceTextField userAgentField = new DataSourceTextField("userAgent");
        DataSourceTextField contentTypeField = new DataSourceTextField("contentType");
        DataSourceTextField contentField = new DataSourceTextField("content");

        DataSourceField terminalField = new DataSourceField("terminal", FieldType.ANY);
        terminalField.setTypeAsDataSource(new TerminalDS());

        DataSourceField clerkField = new DataSourceField("clerk", FieldType.ANY);
        clerkField.setTypeAsDataSource(new UserDS(EntityType.CLERK));

        setFetchDataURL(Properties.SEARCH_MONITORING);

        setFields(hostField, functionField, createdAtField, terminalField, clerkField, methodField, userAgentField, contentTypeField, contentField);
    }
}
