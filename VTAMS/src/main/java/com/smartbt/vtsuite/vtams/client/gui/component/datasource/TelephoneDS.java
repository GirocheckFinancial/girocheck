/*
 ** File: TelephoneDS.java
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

import com.smartbt.vtsuite.vtams.client.gui.base.BaseDatasource;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.FieldType;

/**
 * The Device DataSource
 *
 * @author Ariamnet Lopez, Ariel Saavedra
 */
public class TelephoneDS extends BaseDatasource {

   
    public TelephoneDS() {

        DataSourceTextField numberField = new DataSourceTextField("number");

        DataSourceField telephoneTypeField = new DataSourceField("telephoneType", FieldType.ANY);
        telephoneTypeField.setTypeAsDataSource(new TelephoneTypeDS());

        setFields(numberField, telephoneTypeField);
    }
}