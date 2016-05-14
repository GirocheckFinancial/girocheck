/*
 ** File: AddressDS.java
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

import com.smartbt.vtsuite.vtams.client.gui.base.BaseDatasource;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.FieldType;

/**
 * The Device DataSource
 *
 * @author Ariel Saavedra
 */
public class AddressDS extends BaseDatasource {

    /**
     * Constructor
     *
     */
    public AddressDS() {

        DataSourceTextField addressField = new DataSourceTextField("address");
        DataSourceTextField cityField = new DataSourceTextField("city");
        DataSourceTextField zipField = new DataSourceTextField("zip");
       // DataSourceTextField stateField = new DataSourceTextField("state");

         DataSourceField countryField = new DataSourceField("country", FieldType.ANY);
         countryField.setTypeAsDataSource(new CountryDS());
         
        DataSourceField stateField = new DataSourceField("state", FieldType.ANY);
        stateField.setTypeAsDataSource(new StateDS());


        setFields(addressField,
                cityField,
                countryField,                
                zipField,
                stateField);
    }
}

