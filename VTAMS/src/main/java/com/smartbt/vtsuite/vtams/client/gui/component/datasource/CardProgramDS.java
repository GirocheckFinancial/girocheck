/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.vtsuite.vtams.client.gui.component.datasource;

import com.smartbt.vtsuite.vtams.client.classes.Properties;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseDatasource;
import com.smartgwt.client.data.fields.DataSourceTextField;

/**
 *
 * @author Roberto Rodriguez
 */
public class CardProgramDS extends BaseDatasource {

    public CardProgramDS() {

        DataSourceTextField nameField = new DataSourceTextField("name");

        setFetchDataURL(Properties.LIST_CARD_PROGRAM_WS);
        setFields(nameField);
    }
}
