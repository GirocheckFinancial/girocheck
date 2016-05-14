package com.smartbt.vtsuite.vtams.client.gui.component.datasource;

import com.smartbt.vtsuite.vtams.client.classes.Properties;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseDatasource;
import com.smartbt.vtsuite.vtams.client.utils.Utils;
import com.smartgwt.client.data.fields.DataSourceTextField;

/**
 *
 * @author Alejo
 */


public class ReportDS extends BaseDatasource{
    
    /**
     * Constructor
     */
    public ReportDS() {
        DataSourceTextField urlField = new DataSourceTextField("url");
//        setFetchDataURL(fetchDataUrl);
        setFields(urlField);
    }

    /**
     * @param fetchDataUrl the fetchDataUrl to set
     */
    public void setCustomeFetchDataUrl(String fetchDataUrl) {
        setFetchDataURL(fetchDataUrl);
    }

    
}
