/*
 ** File: TelephoneTypeDS.java
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
import com.smartgwt.client.data.fields.DataSourceTextField;

/**
 *
 * @author Ariel Saavedra
 */
public class TelephoneTypeDS extends BaseDatasource {

   
    public TelephoneTypeDS() {

        DataSourceTextField nameField = new DataSourceTextField("name");
        DataSourceTextField descriptionField = new DataSourceTextField("description");

        setFetchDataURL(Properties.LIST_PHONE_TYPES_WS);
        setFields(nameField, descriptionField);
    }
}
