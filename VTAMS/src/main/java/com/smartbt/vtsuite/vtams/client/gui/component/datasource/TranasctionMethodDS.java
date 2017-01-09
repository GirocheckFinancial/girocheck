package com.smartbt.vtsuite.vtams.client.gui.component.datasource;

import com.smartbt.vtsuite.vtams.client.classes.Properties;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseDatasource;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.DSProtocol;

/**
 *
 * @author suresh
 */


public class TranasctionMethodDS extends BaseDatasource {
     public TranasctionMethodDS() {
         
        DataSourceTextField name = new DataSourceTextField("operation");
        DataSourceTextField description = new DataSourceTextField("description");
        
        setFetchDataURL(Properties.SEARCH_TXN_METHOD_WS);
        getAddOperation().setDataProtocol(DSProtocol.POSTPARAMS);
        getUpdateOperation().setDataProtocol(DSProtocol.POSTPARAMS);

        setFields(name, description);
         
     }
    
}
