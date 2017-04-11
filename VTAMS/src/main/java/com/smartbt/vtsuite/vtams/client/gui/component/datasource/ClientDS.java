/*
 ** File: ClientDS.java
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

/**
 * The Client DataSource
 *
 * @author Ariamnet Lopez
 */
public class ClientDS extends BaseDatasource {

    /**
     * Constructor
     */
    public ClientDS() {

        DataSourceTextField firstNameField = new DataSourceTextField("firstName");
        DataSourceTextField lastNameField = new DataSourceTextField("lastName");

        DataSourceTextField addressField = new DataSourceTextField("address");
        DataSourceTextField stateField = new DataSourceTextField("state");
        DataSourceTextField cityField = new DataSourceTextField("city");
        DataSourceTextField zipcodeField = new DataSourceTextField("zipcode");

        DataSourceTextField telephoneField = new DataSourceTextField("telephone");

        DataSourceTextField emailField = new DataSourceTextField("email");
        DataSourceTextField maskSSField = new DataSourceTextField("maskSS");
        DataSourceBooleanField blackListField = new DataSourceBooleanField("blackList");
        DataSourceBooleanField optOutField = new DataSourceBooleanField("optOut");

        setFetchDataURL(Properties.SEARCH_CLIENTS_WS);
        setAddDataURL(Properties.SAVE_OR_UPDATE_CLIENT_WS);
        setUpdateDataURL(Properties.UPDATE_OPTOUT_CLIENTS_WS);
        setRemoveDataURL(Properties.DELETE_CLIENT_WS);

        setFields(firstNameField,
                lastNameField,
                addressField,
                telephoneField,
                cityField,
                stateField,
                zipcodeField,
                emailField,
                maskSSField,
                blackListField,
                optOutField);
    }
}
