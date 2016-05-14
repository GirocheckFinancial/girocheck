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
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.fields.DataSourceBooleanField;
import com.smartgwt.client.data.fields.DataSourceDateField;
import com.smartgwt.client.data.fields.DataSourceFloatField;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.FieldType;

/**
 * The Transaction DataSource
 *
 * @author Ariamnet Lopez
 */
public class TransactionsDS extends BaseDatasource {

    /**
     * Constructor
     *
     */
    public TransactionsDS() {
        
        DataSourceIntegerField typeField = new DataSourceIntegerField("transactionType");
        
        DataSourceDateField createdAtField = new DataSourceDateField("createdAt");
        
        DataSourceTextField operationField = new DataSourceTextField("operation");
        
        DataSourceIntegerField accountSuffixField = new DataSourceIntegerField("accountSuffix");
        
        DataSourceFloatField ammountField = new DataSourceFloatField("ammount");
        DataSourceFloatField feeAmmountField = new DataSourceFloatField("feeAmmount");
        DataSourceFloatField payoutAmmountField = new DataSourceFloatField("payoutAmmount");
        
        DataSourceBooleanField singleField = new DataSourceBooleanField("single");
        
        DataSourceIntegerField resultCodeField = new DataSourceIntegerField("resultCode");
        DataSourceTextField resultMessageField = new DataSourceTextField("resultMessage");
        
        DataSourceTextField merchantField = new DataSourceTextField("merchant");
        DataSourceTextField terminalField = new DataSourceTextField("terminal");
        DataSourceTextField clientFirstNameField = new DataSourceTextField("clientFirstName");
        DataSourceTextField clientLastNameField = new DataSourceTextField("clientLastName");
        DataSourceTextField transactionFinishedField = new DataSourceTextField("transactionFinished");
        
      
//         DataSourceField merchantField = new DataSourceField("merchant", FieldType.ANY);
//        merchantField.setTypeAsDataSource(new MerchantDS());
//        
//         DataSourceField terminalField = new DataSourceField("terminal", FieldType.ANY);
//        terminalField.setTypeAsDataSource(new TerminalDS());
//        
       
//        DataSourceField clientField = new DataSourceField("client", FieldType.ANY);
//        clientField.setTypeAsDataSource(new UserDS(EntityType.CLERK));
        
        setFetchDataURL(Properties.SEARCH_TRANSACTIONS_WS);

        setFields(typeField,
                createdAtField,
                operationField,
                accountSuffixField,
                ammountField,
                feeAmmountField,
                payoutAmmountField,
                singleField,
                resultCodeField,
                resultMessageField,
                merchantField,
                terminalField,
                clientFirstNameField,
                clientLastNameField,
                transactionFinishedField);
    }
}
