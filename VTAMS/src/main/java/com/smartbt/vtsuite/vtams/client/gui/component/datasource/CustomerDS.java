/*
 ** File: CustomerDS.java
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
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.fields.DataSourceBooleanField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.FieldType;

/**
 * The Customer DataSource
 *
 * @author Ariamnet Lopez
 */
public class CustomerDS extends BaseDatasource {

    /**
     * Constructor
     *
     */
    public CustomerDS() {

        DataSourceTextField nameField = new DataSourceTextField("name");
        DataSourceTextField descriptionField = new DataSourceTextField("description");
        

        //setJsonRecordXPath("customer");

        setFetchDataURL(Properties.GET_CUSTOMER_WS);

        setFields(nameField,
                descriptionField);
    }
    
     public CustomerDS(String fetchDataUrl) {
         this();
         setFetchDataURL(fetchDataUrl);
     }
}
