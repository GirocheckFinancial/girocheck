/*
 ** File: RolePrivilegeDS.java
 **
 ** Date Created: October 2013
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
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.types.DSProtocol;
import com.smartgwt.client.types.FieldType;

/**
 * The RolePrivilege DataSource
 *
 * @author Ariel Saavedra
 */
public class RolePrivilegeDS extends BaseDatasource {

    /**
     * Constructor
     *
     */
    public RolePrivilegeDS() {

        DataSourceField rolField = new DataSourceField("role", FieldType.ANY);
        rolField.setTypeAsDataSource(new RoleDS());

        DataSourceField privilegeField = new DataSourceField("privilege", FieldType.ANY);
        privilegeField.setTypeAsDataSource(new PrivilegeDS());

        setFetchDataURL(Properties.SEARCH_ROLE_PRIVILEGES_WS);
        setAddDataURL(Properties.ADD_ROLE_PRIVILEGE_WS);
        setRemoveDataURL(Properties.DELETE_ROLE_PRIVILEGE_WS);

        getAddOperation().setDataProtocol(DSProtocol.POSTPARAMS);

        setFields(rolField, privilegeField);
    }
}
