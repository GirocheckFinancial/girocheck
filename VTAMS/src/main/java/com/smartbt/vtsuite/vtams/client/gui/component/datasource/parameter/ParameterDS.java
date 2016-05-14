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
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.DataTypeDS;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.fields.DataSourceBooleanField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.DSProtocol;
import com.smartgwt.client.types.FieldType;

/**
 * The Parameter DataSource
 *
 * @author Ariamnet Lopez, Ariel Saavedra
 */
public class ParameterDS extends BaseDatasource {

    /**
     * Constructor
     *
     * @param entityType
     */
    public ParameterDS(EntityType entityType) {

        DataSourceTextField parameterField = new DataSourceTextField("parameter");
        DataSourceTextField descriptionField = new DataSourceTextField("description");
        DataSourceTextField valueField = new DataSourceTextField("value");
        DataSourceBooleanField readOnlyField = new DataSourceBooleanField("readOnly");
        //DataSourceTextField groupField = new DataSourceTextField("group");
        DataSourceField dataTypeField = new DataSourceField("dataType", FieldType.ANY);
        dataTypeField.setTypeAsDataSource(new DataTypeDS());

        if (entityType != EntityType.APPLICATION_PARAMETER) {
            setFetchDataURL(Properties.GET_NOT_CONTAINED_PARAMETERS_WS);
            setAddDataURL(Properties.ADD_PARAMETER_VALUE_WS);
            setUpdateDataURL(Properties.UPDATE_PARAMETER_VALUE_WS);
        } else {
          //  setFetchDataURL(Properties.SEARCH_APPLICATION_PARAMETERS_WS);
            setAddDataURL(Properties.SAVE_OR_UPDATE_APPLICATION_PARAMETER_WS);
            setUpdateDataURL(Properties.SAVE_OR_UPDATE_APPLICATION_PARAMETER_WS);
            setRemoveDataURL(Properties.DELETE_APPLICATION_PARAMETER_WS);
        }

        //getAddOperation().setDataProtocol(DSProtocol.);
        //getUpdateOperation().setDataProtocol(DSProtocol.POSTPARAMS);

        setFields(parameterField, descriptionField, valueField, dataTypeField, readOnlyField);
    }
}
