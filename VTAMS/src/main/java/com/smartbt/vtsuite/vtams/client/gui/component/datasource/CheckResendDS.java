package com.smartbt.vtsuite.vtams.client.gui.component.datasource;

import com.smartbt.vtsuite.vtams.client.classes.Properties;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseDatasource;
import com.smartgwt.client.data.fields.DataSourceDateField;
import com.smartgwt.client.data.fields.DataSourceFloatField;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;

/**
 *
 * @author suresh
 */
public class CheckResendDS extends BaseDatasource {

    public CheckResendDS() {

        DataSourceIntegerField checkID = new DataSourceIntegerField("id");

        DataSourceDateField dateInserted = new DataSourceDateField("creationDate");

        DataSourceDateField dateProcessed = new DataSourceDateField("processingDate");

        DataSourceTextField status = new DataSourceTextField("status");
        
        DataSourceFloatField amount = new DataSourceFloatField("amount");

        setFetchDataURL(Properties.SEARCH_CHECK_DETAILS_WS);

        setUpdateDataURL(Properties.RESEND_CHECK_WS);

        setFields(checkID,
                amount,
                dateInserted,
                dateProcessed,
                status);
    }
}
