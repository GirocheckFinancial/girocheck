/*
 ** File: SystemPropertyDS.java
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
import com.smartgwt.client.data.fields.DataSourceTextField;

/**
 * The System Property DataSource
 *
 * @author Ariamnet Lopez
 */
public class SystemPropertyDS extends BaseDatasource {

    /**
     * Constructor
     *
     */
    public SystemPropertyDS() {

        DataSourceTextField name = new DataSourceTextField("propertyName");
        DataSourceTextField value = new DataSourceTextField("propertyValue");

        setFetchDataURL(Properties.GET_SYSTEM_PROPERTY_WS);

        //setJsonRecordXPath("property");

        setFields(name,
                value);
    }
}
