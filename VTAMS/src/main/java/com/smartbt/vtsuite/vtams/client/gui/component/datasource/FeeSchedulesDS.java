
package com.smartbt.vtsuite.vtams.client.gui.component.datasource;

import com.smartbt.vtsuite.vtams.client.classes.Properties;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseDatasource;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.FieldType;

/**
 *
 * @author Sreekanth
 */


public class FeeSchedulesDS extends BaseDatasource {
    
    public FeeSchedulesDS(EntityType entityType) {     
        
        DataSourceTextField isdefaultField = new DataSourceTextField("isdefault");
        
        DataSourceTextField merchantField = new DataSourceTextField("merchantName");
        
       // DataSourceField merchantSelect = new DataSourceField("merchant", FieldType.ANY);
       // merchantSelect.setTypeAsDataSource(new FeeMerchantDS());
        
        DataSourceField methodField = new DataSourceField("method", FieldType.ANY);
        methodField.setTypeAsDataSource(new TranasctionMethodDS());
        
        setFetchDataURL(Properties.SEARCH_FEESCHEDULE_WS);
        setAddDataURL(Properties.ADD_FEESCHEDULE_WS);
        setUpdateDataURL(Properties.UPDATE_FEESCHEDULE_WS);
        setRemoveDataURL(Properties.DELETE_FEESCHEDULE_WS);
        
                
        setFields(merchantField,
                methodField,                
                isdefaultField
                //merchantSelect
                );
    
    }
    
}
