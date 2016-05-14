/*
 ** File: WatchdogEntityClerkDS.java
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
package com.smartbt.vtsuite.vtams.client.gui.component.datasource.watchdog;

import com.smartbt.vtsuite.vtams.client.gui.base.BaseDatasource;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.UserDS;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.types.FieldType;

/**
 * The WatchdogEntityClerk DataSource
 *
 * @author Ariel Saavedra
 */
public class WatchdogEntityClerkDS extends BaseDatasource {

    /**
     * Constructor
     *
     */
    public WatchdogEntityClerkDS() {
        DataSourceField clerkField = new DataSourceField("clerk", FieldType.ANY);
        clerkField.setTypeAsDataSource(new UserDS(EntityType.CLERK));
        
        setFields(clerkField);
    }
}
