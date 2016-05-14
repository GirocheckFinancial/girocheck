/*
 ** File: UserDS.java
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

import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartbt.vtsuite.vtams.client.classes.Properties;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseDatasource;
import static com.smartbt.vtsuite.vtcommon.enums.EntityType.CUSTOMER;
import static com.smartbt.vtsuite.vtcommon.enums.EntityType.MERCHANT;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.fields.DataSourceBooleanField;
import com.smartgwt.client.data.fields.DataSourcePasswordField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.FieldType;

/**
 * The User DataSource
 *
 * @author Ariamnet Lopez, Ariel Saavedra
 */
public class UserDS extends BaseDatasource {

    /**
     * Constructor
     *
     * @param entityType
     */
    public UserDS(EntityType entityType) {

        DataSourceTextField usernameField = new DataSourceTextField("username");
        DataSourceTextField lastNameField = new DataSourceTextField("lastName");
        DataSourceTextField firstNameField = new DataSourceTextField("firstName");
        DataSourcePasswordField passwordField = new DataSourcePasswordField("password");
        DataSourceTextField emailField = new DataSourceTextField("email");
        DataSourceBooleanField statusField = new DataSourceBooleanField("active");
//        DataSourceBooleanField activeField = new DataSourceBooleanField("active");

        DataSourceField roleField = new DataSourceField("role", FieldType.ANY);
        roleField.setTypeAsDataSource(new RoleDS());

//        DataSourceField customerField = new DataSourceField("customer", FieldType.ANY);
//        customerField.setTypeAsDataSource(new CustomerDS());
//
//        DataSourceField merchantField = new DataSourceField("merchant", FieldType.ANY);
//        merchantField.setTypeAsDataSource(new MerchantDS());
//
//        DataSourceField terminalField = new DataSourceField("terminal", FieldType.ANY);
//        terminalField.setTypeAsDataSource(new TerminalDS());

//        switch (entityType) {
//            case AMS: {
                setFetchDataURL(Properties.SEARCH_USERS_WS);
                setAddDataURL(Properties.ADD_USER_WS);
                setUpdateDataURL(Properties.UPDATE_USER_WS);
                setRemoveDataURL(Properties.DELETE_USER_WS);

//            }
//            case CLERK:
//            case ENTERPRISE:
//            case CUSTOMER:
//            case MERCHANT:
//            case TERMINAL: {
//                setFetchDataURL(Properties.SEARCH_CLERKS_WS);
//                setAddDataURL(Properties.UPDATE_CLERK_WS);
//                break;
//            }
//        }

        setFields(usernameField,
                lastNameField,
                firstNameField,
                roleField,
                statusField,
//                activeField,
                passwordField,
                emailField);
    }
}
