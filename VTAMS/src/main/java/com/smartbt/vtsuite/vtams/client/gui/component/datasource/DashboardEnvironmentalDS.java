/*
 ** File: DashboardDS.java
 **
 ** Date Created: December 2013
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
import com.smartgwt.client.data.fields.DataSourceDateField;
import com.smartgwt.client.data.fields.DataSourceTextField;

/**
 * The Dashboard DataSource
 *
 * @author Ariel Saavedra
 */
public class DashboardEnvironmentalDS extends BaseDatasource {

    /**
     * Constructor
     *
     */
    public DashboardEnvironmentalDS() {

        DataSourceTextField name = new DataSourceTextField("taskId");
        DataSourceTextField value = new DataSourceTextField("value");
        DataSourceDateField createdAtField = new DataSourceDateField("createdAt");

        setFetchDataURL(Properties.GET_DASHBOARD_ENVIROMENTAL);

        setFields(name, value, createdAtField);
    }
}
