/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.vtsuite.vtams.client.gui.component.datasource;

import com.smartbt.vtsuite.vtams.client.gui.base.BaseDatasource;
import com.smartgwt.client.data.fields.DataSourceTextField;

/**
 *
 * @author Ariel Saavedra
 */
public class OperationDS extends BaseDatasource {

    public OperationDS() {

        DataSourceTextField nameField = new DataSourceTextField("name");

        setFields(nameField);
    }
}
