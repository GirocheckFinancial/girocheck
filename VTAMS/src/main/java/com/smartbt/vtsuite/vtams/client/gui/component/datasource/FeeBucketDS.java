
package com.smartbt.vtsuite.vtams.client.gui.component.datasource;

import com.smartbt.vtsuite.vtams.client.classes.Properties;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseDatasource;
import com.smartgwt.client.data.fields.DataSourceTextField;

/**
 *
 * @author Sreekanth
 */

public class FeeBucketDS extends BaseDatasource {
    
    public FeeBucketDS() {
        
        DataSourceTextField minimumField = new DataSourceTextField("minimum");
        DataSourceTextField percentageField = new DataSourceTextField("percentage");
        DataSourceTextField fixedField = new DataSourceTextField("fixed");
        
        setFetchDataURL(Properties.SEARCH_FEEBUCKETS_WS);
        setAddDataURL(Properties.ADD_FEEBUCKETS_WS);
        setUpdateDataURL(Properties.UPDATE_FEEBUCKETS_WS);
        setRemoveDataURL(Properties.DELETE_FEEBUCKETS_WS);
        
        setFields(minimumField,
                percentageField,                
                fixedField);
    
    }
}
