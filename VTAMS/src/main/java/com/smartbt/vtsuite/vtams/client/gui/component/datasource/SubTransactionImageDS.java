
package com.smartbt.vtsuite.vtams.client.gui.component.datasource;

import com.smartbt.vtsuite.vtams.client.classes.Properties;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseDatasource;
import com.smartgwt.client.data.fields.DataSourceImageField;

/**
 *
 * @author Alejo
 */


public class SubTransactionImageDS extends BaseDatasource{
    
        public SubTransactionImageDS(){
        
        DataSourceImageField imageContentFild = new DataSourceImageField("image");
        
        setFetchDataURL(Properties.GET_TRANSACTION_IMAGE_WS);
        
        setFields(imageContentFild);
        
    }
    
}
