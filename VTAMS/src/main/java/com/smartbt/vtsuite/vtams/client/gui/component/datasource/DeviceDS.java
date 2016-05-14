/*
 ** File: DeviceDS.java
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
import com.smartgwt.client.data.fields.DataSourceTextField;

/**
 * The Device DataSource
 *
 * @author Ariamnet Lopez
 */
public class DeviceDS extends BaseDatasource {

    /**
     * Constructor
     *
     */
    public DeviceDS() {
        
        DataSourceTextField nameField = new DataSourceTextField("name");
        DataSourceTextField productCodeField = new DataSourceTextField("productCode");
        DataSourceTextField serialNumberField = new DataSourceTextField("serialNumber");
        DataSourceTextField descriptionField = new DataSourceTextField("description");
        DataSourceBooleanField activeField = new DataSourceBooleanField("active");

        //setJsonRecordXPath("device");

        setFetchDataURL(Properties.GET_DEVICES_BY_TERMINAL_WS);        

        setFields(nameField,
                productCodeField,
                serialNumberField,
                descriptionField,
                activeField);
    }
}
