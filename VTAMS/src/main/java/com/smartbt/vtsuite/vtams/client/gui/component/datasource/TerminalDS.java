/*
 ** File: TerminalDS.java
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
 * The Terminal DataSource
 *
 * @author Ariamnet Lopez
 */
public class TerminalDS extends BaseDatasource {

    /**
     * Constructor
     *
     */
    public TerminalDS() {
        DataSourceTextField idMerchantField = new DataSourceTextField("idMerchant");
        
        DataSourceTextField serialNumberField = new DataSourceTextField("serialNumber");
        DataSourceTextField terminalUserField = new DataSourceTextField("terminalUser");
        DataSourceTextField terminalPasswordField = new DataSourceTextField("terminalPassword");
        DataSourceTextField descriptionField = new DataSourceTextField("description");
        DataSourceTextField idPOSOrderExp = new DataSourceTextField("idPOSOrderExp");
        
        
        DataSourceBooleanField hasTransactionField = new DataSourceBooleanField("hasTransaction");
        
        setFetchDataURL(Properties.GET_TERMINALS_BY_MERCHANT_WS);
        setAddDataURL( Properties.SAVE_OR_UPDATE_TERMINAL_WS);

        setFields(idMerchantField,
                serialNumberField,
                terminalUserField,
                terminalPasswordField,
                descriptionField,
                idPOSOrderExp,
                hasTransactionField);
    }

    public TerminalDS(String fetchDataUrl) {
        this();
        setFetchDataURL(fetchDataUrl);
    }
}
