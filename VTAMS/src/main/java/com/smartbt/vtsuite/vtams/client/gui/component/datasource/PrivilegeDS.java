/*
 ** File: PrivilegeDS.java
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
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.FieldType;

/**
 * The Privilege DataSource
 *
 * @author Ariamnet Lopez, Ariel Saavedra
 */
public class PrivilegeDS extends BaseDatasource {

    /**
     * Constructor
     *
     */
    public PrivilegeDS() {

        DataSourceTextField parameterField = new DataSourceTextField("name");
        DataSourceTextField descriptionField = new DataSourceTextField("description");

//        DataSourceField applicationClerkPrivilegeField = new DataSourceField("applicationClerkPrivilege", FieldType.ANY);
//        applicationClerkPrivilegeField.setMultiple(Boolean.TRUE);
//        applicationClerkPrivilegeField.setTypeAsDataSource(new ApplicationClerkPrivilege());

        setFetchDataURL(Properties.SEARCH_ROLE_PRIVILEGES_WS);
//        setFields(parameterField, descriptionField, applicationClerkPrivilegeField);
        setFields(parameterField, descriptionField);
    }
}

//class ApplicationClerkPrivilege extends BaseDatasource {
//
//    public ApplicationClerkPrivilege() {
//
//        DataSourceField applicationField = new DataSourceField("application", FieldType.ANY);
//        applicationField.setTypeAsDataSource(new ApplicationDS());
//
//        setFields(applicationField);
//    }
//}
