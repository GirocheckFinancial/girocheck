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
package com.smartbt.vtsuite.vtams.client.gui.component.datasource;

import com.smartbt.vtsuite.vtams.client.classes.Properties;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseDatasource;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;

/**
 * The Transaction DataSource
 *
 * @author Ariamnet Lopez
 */
public class SubTransactionsDS extends BaseDatasource {

    /**
     * Constructor
     *
     */
    public SubTransactionsDS() {
        
        DataSourceIntegerField typeField = new DataSourceIntegerField("subTransactionType");
        DataSourceTextField resultCodeField = new DataSourceTextField("resultCode");
        DataSourceTextField hostCodeField = new DataSourceTextField("hostCode");
        DataSourceTextField resultMessageField = new DataSourceTextField("resultMessage");
        DataSourceTextField merchantField = new DataSourceTextField("merchant");
        DataSourceTextField terminalField = new DataSourceTextField("terminal");
        DataSourceTextField clientFirstNameField = new DataSourceTextField("clientFirstName");
        DataSourceTextField clientLastNameField = new DataSourceTextField("clientLastName");
        
        
        setFetchDataURL(Properties.SEARCH_SUB_TRANSACTIONS_WS);

        setFields(typeField,
                resultCodeField,
                hostCodeField,
                resultMessageField,
                merchantField,
                terminalField,
                clientFirstNameField,
                clientLastNameField);
    }
}
