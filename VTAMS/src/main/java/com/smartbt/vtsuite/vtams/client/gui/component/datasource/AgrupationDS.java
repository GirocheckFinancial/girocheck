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
import com.smartgwt.client.data.fields.DataSourceBooleanField;
import com.smartgwt.client.data.fields.DataSourceTextField;

/**
 * The Customer DataSource
 *
 * @author Ariamnet Lopez
 */
public class AgrupationDS extends BaseDatasource {

    /**
     * Constructor
     *
     */
    public AgrupationDS() {

        DataSourceTextField nameField = new DataSourceTextField("name");
        DataSourceTextField descriptionField = new DataSourceTextField("description");
        DataSourceBooleanField hasTransaction = new DataSourceBooleanField("hasTransaction");

        //setJsonRecordXPath("customer");

        setFetchDataURL(Properties.LIST_AGRUPATIONS_WS);
        setAddDataURL( Properties.SAVE_OR_UPDATE_AGRUPATION_WS );
        setRemoveDataURL( Properties.DELETE_AGRUPATION_WS);
        
        setFields(nameField, descriptionField,hasTransaction);
    }
    
     public AgrupationDS(String fetchDataUrl) {
         this();
         setFetchDataURL(fetchDataUrl);
     }
}
