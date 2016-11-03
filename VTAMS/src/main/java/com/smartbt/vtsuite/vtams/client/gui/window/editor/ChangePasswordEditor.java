/*
 ** File: RoleEditor.java
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
package com.smartbt.vtsuite.vtams.client.gui.window.editor;

import com.smartbt.vtsuite.vtams.client.classes.i18n.I18N;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;

import com.smartbt.vtsuite.vtams.client.gui.base.BaseEditorWindow;
import com.smartbt.vtsuite.vtams.client.gui.component.BasePasswordItem;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.UserDS;
import com.smartbt.vtsuite.vtcommon.validator.Constants;
import com.smartbt.vtsuite.vtcommon.validator.RegExp;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.Record;

/**
 * The Role Editor Window
 *
 * @author Alejo
 */
public final class ChangePasswordEditor extends BaseEditorWindow {

    private BasePasswordItem passwordText;
    private BasePasswordItem checkpasswordText;

    public ChangePasswordEditor(EntityType entityType, Record recordEntity) {
        super(I18N.GET.WINDOW_CHANGE_PASSWORD_TITLE());

        passwordText = new BasePasswordItem("password", true);
        checkpasswordText = new BasePasswordItem("RepeatPassword", true);

        passwordText.setKeyPressFilter(RegExp.VALID_PASSW_REG_EXP);
        checkpasswordText.setKeyPressFilter(RegExp.VALID_PASSW_REG_EXP);
        
        
        dataForm.setDataSource(new UserDS(entityType));
        dataForm.setFields(passwordText, checkpasswordText);
    }
    
    @Override
    public void addRecord(Record record) {
        passwordText.setDisabled(false);
        checkpasswordText.setDisabled(false);   

        Criteria criteria = new Criteria();
        criteria.addCriteria("entityType", EntityType.AMS.toString());

       // super.addRecord(record);
    }
    
    public Record getRecord() {

        Record userRecord = new Record();

        userRecord.setAttribute("id", dataForm.getValuesAsRecord().getAttributeAsInt("id"));
        userRecord.setAttribute("password", passwordText.getValueAsString());
        userRecord.setAttribute("checkpassword", checkpasswordText.getValueAsString());
        return userRecord;
    }
    
    public void Reset(){
        passwordText.setValue("");
        checkpasswordText.setValue(""); 
    }
}
