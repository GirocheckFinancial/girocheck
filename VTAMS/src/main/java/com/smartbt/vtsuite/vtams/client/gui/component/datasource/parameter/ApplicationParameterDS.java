/*
 ** File: ParameterDS.java
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
package com.smartbt.vtsuite.vtams.client.gui.component.datasource.parameter;

import com.smartbt.vtsuite.vtams.client.classes.Properties;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseDatasource;
import com.smartgwt.client.data.fields.DataSourceBooleanField;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;

/**
 *  @author Roberto Rodriguez <roberto.rodriguez@smartbt.com>
 */




public class ApplicationParameterDS extends BaseDatasource {

    
    public ApplicationParameterDS() {

        DataSourceIntegerField applicationField = new DataSourceIntegerField("application");
        DataSourceIntegerField applicationLabelField = new DataSourceIntegerField("applicationLabel");
        DataSourceTextField parameterField = new DataSourceTextField("name");
        DataSourceTextField descriptionField = new DataSourceTextField("description");
        DataSourceTextField valueField = new DataSourceTextField("value");
        DataSourceBooleanField readOnlyField = new DataSourceBooleanField("readOnly");
      
        
            setFetchDataURL(Properties.SEARCH_PARAMETERS_WS);
            setAddDataURL(Properties.SAVE_OR_UPDATE_APPLICATION_PARAMETER_WS);
            setUpdateDataURL(Properties.SAVE_OR_UPDATE_APPLICATION_PARAMETER_WS);
            setRemoveDataURL(Properties.DELETE_APPLICATION_PARAMETER_WS);
        
        setFields(applicationLabelField, applicationField, parameterField, descriptionField, valueField, readOnlyField);
    }
}
