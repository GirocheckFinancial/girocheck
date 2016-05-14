/*
 ** File: ParameterValueDS.java
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
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.DSProtocol;
import com.smartgwt.client.types.FieldType;

/**
 * The Parameter Value DataSource
 *
 * @author Ariamnet Lopez, Ariel Saavedra
 */
public class ParameterValueDS extends BaseDatasource {

    /**
     * Constructor
     *
     * @param entityType
     */
    public ParameterValueDS(EntityType entityType) {

        DataSourceTextField valueField = new DataSourceTextField("value");

        DataSourceField parameterField = new DataSourceField("parameter", FieldType.ANY);
        parameterField.setTypeAsDataSource(new ParameterDS(entityType));

        setFetchDataURL(Properties.SEARCH_PARAMETERS_VALUE_WS);
        setAddDataURL(Properties.ADD_PARAMETER_VALUE_WS);
        setUpdateDataURL(Properties.UPDATE_PARAMETER_VALUE_WS);
        setRemoveDataURL(Properties.DELETE_PARAMETER_VALUE_WS);
        
        getAddOperation().setDataProtocol(DSProtocol.POSTPARAMS);
        getUpdateOperation().setDataProtocol(DSProtocol.POSTPARAMS);        
        setFields(valueField,parameterField);
    }
}
