
package com.smartbt.vtsuite.vtams.client.gui.component.datasource;

import com.smartbt.vtsuite.vtams.client.classes.Properties;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseDatasource;
import com.smartgwt.client.data.fields.DataSourceImageField;

/**
 *
 * @author Alejo
 */


public class AddressImageFormDS extends BaseDatasource{
    
    public AddressImageFormDS(){
        
        DataSourceImageField imageContentFild = new DataSourceImageField("addressImage");
        
        setFetchDataURL(Properties.GET_ADDRESS_IMAGE_FORM_WS);
//        setAddDataURL(Properties.SAVE_OR_UPDATE_CLIENT_WS);
//        setUpdateDataURL(Properties.SAVE_OR_UPDATE_CLIENT_WS);
//        setRemoveDataURL(Properties.DELETE_CLIENT_WS);
        
        setFields(imageContentFild);
        
    }
    
}
