package com.smartbt.vtsuite.vtams.client.gui.component.datasource;

import com.smartbt.vtsuite.vtams.client.classes.Properties;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseDatasource;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.types.FieldType;

/**
 *
 * @author Alejo
 */


public class VTSessionDS extends BaseDatasource{
    
        public VTSessionDS() {

//        DataSourceField parametersField = new DataSourceField("parameters", FieldType.ANY);
       
        DataSourceField tokenField = new DataSourceField("token", FieldType.ANY);
        
        setFetchDataURL(Properties.DELETE_SESSION_WS);
        setFields(tokenField);
    }
    
}
