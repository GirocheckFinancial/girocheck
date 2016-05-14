/*
 ** File: ApplicationDS.java
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
import com.smartgwt.client.data.fields.DataSourceTextField;

/**
 * The Application DataSource
 *
 * @author Ariamnet Lopez, Ariel Saavedra
 */
public class ApplicationDS extends BaseDatasource {

    /**
     * Constructor
     *
     */
    public ApplicationDS() {

        DataSourceTextField name = new DataSourceTextField("name");
        DataSourceTextField description = new DataSourceTextField("description");

        setFetchDataURL(Properties.GET_APPLICATIONS_WS);
        setAddDataURL(Properties.ADD_APPLICATION_WS);

        setFields(name, description);
    }
}
