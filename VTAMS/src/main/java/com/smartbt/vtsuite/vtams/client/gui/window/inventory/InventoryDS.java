/*
 ** File: TransactionsDS.java
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
package com.smartbt.vtsuite.vtams.client.gui.window.inventory;

import com.smartbt.vtsuite.vtams.client.classes.Properties;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseDatasource;
import com.smartgwt.client.data.fields.DataSourceTextField;

/**
 * The Transaction DataSource
 *
 * @author Ariamnet Lopez
 */
public class InventoryDS extends BaseDatasource {

    /**
     * Constructor
     *
     */
    public InventoryDS() {
        
        DataSourceTextField merchantField = new DataSourceTextField("merchant");
        DataSourceTextField inventoryField = new DataSourceTextField("inventory");
        DataSourceTextField thresholdField = new DataSourceTextField("threshold");
         
        setFetchDataURL(Properties.SEARCH_INVENTORY_WS);
        setAddDataURL( Properties.SAVE_OR_UPDATE_INVENTORY_WS );
        
        setFields(merchantField,
                inventoryField,
                thresholdField);
    }
}
