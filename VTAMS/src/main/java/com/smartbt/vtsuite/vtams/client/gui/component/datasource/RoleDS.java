/*
 ** File: RoleDS.java
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
package com.smartbt.vtsuite.vtams.client.gui.component.datasource;

import com.smartbt.vtsuite.vtams.client.classes.Properties;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseDatasource;
import com.smartgwt.client.data.fields.DataSourceBooleanField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.DSProtocol;

/**
 * The Role DataSource
 *
 * @author Ariamnet Lopez
 */
public class RoleDS extends BaseDatasource {

    /**
     * Constructor
     *
     */
    public RoleDS() {

        DataSourceTextField name = new DataSourceTextField("name");
//        DataSourceBooleanField activeField = new DataSourceBooleanField("active");
        DataSourceTextField description = new DataSourceTextField("description");

        setFetchDataURL(Properties.GET_ROLES_WS);
        setAddDataURL(Properties.ADD_ROLE_WS);
        setUpdateDataURL(Properties.UPDATE_ROLE_WS);
        setRemoveDataURL(Properties.DELETE_ROLE_WS);

        getAddOperation().setDataProtocol(DSProtocol.POSTPARAMS);
        getUpdateOperation().setDataProtocol(DSProtocol.POSTPARAMS);

        setFields(name, description);
//                setFields(name, activeField, description);
    }
}
