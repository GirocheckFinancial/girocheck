/*
 *  File CustomerEditor
 * 
 *  Date Created: February 2014
 * 
 *  Copyright @ @ 2004-2014 Smart Business Technology, Inc.
 *
 *  All rights reserved. No part of this software may be 
 *  reproduced, transmitted, transcribed, stored in a retrieval 
 *  system, or translated into any language or computer language,
 *  in any form or by any means, electronic, mechanical, magnetic, 
 *  optical, chemical, manual or otherwise, without the prior 
 *  written permission of Smart Business Technology, Inc.
 *
 */
package com.smartbt.vtsuite.vtams.client.gui.window.editor.boarding;

import com.smartbt.vtsuite.vtams.client.classes.Settings;
import com.smartbt.vtsuite.vtams.client.classes.i18n.I18N;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseBoardingEditor;
import com.smartbt.vtsuite.vtams.client.gui.component.BaseButtonItem;
import com.smartbt.vtsuite.vtams.client.gui.component.BaseTextAreaItem;
import com.smartbt.vtsuite.vtams.client.gui.component.BaseTextItem;
import com.smartbt.vtsuite.vtams.client.utils.Utils;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomUserPrivileges;
import com.smartbt.vtsuite.vtcommon.validator.Constants;
import com.smartbt.vtsuite.vtcommon.validator.RegExp;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ariel, Roberto
 */
public class AgrupationEditor extends BaseBoardingEditor {
    
    private BaseTextItem nameText;
    private BaseTextAreaItem description;
    
    public AgrupationEditor(int id, int  idParent) {
        super(id, idParent, EntityType.AGRUPATION);  
        
        nameText = new BaseTextItem(1, "name", I18N.GET.LABEL_NAME_TITLE(), I18N.GET.TOOLTIP_NAME_MERCHANT_MSG(), true);
        nameText.setColSpan(2);
        
        description = new BaseTextAreaItem(9, "description", I18N.GET.LABEL_DESCRIPTION_TITLE(), I18N.GET.LABEL_DESCRIPTION_TITLE(), false);
        description.setColSpan(4);
        description.setLength(Constants.HIGH_TEXT_MAX_LENGTH);
        
        acceptButton.setDisabled(true);
        if(Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_BOARDING_MANAGEMENT_AGRUPATION_ADD)){
            acceptButton.setDisabled(false);
        }
        
        actionForm.setFields( spacerItem, reportButton, acceptButton, addChildButton);
        
        dataForm.setFields(nameText, description);
        
        
        nameText.setLength( Constants.STANDARD_TEXT_MAX_LENGTH );
        description.setLength( Constants.HIGH_TEXT_MAX_LENGTH );
        
        nameText.setKeyPressFilter(RegExp.VALID_TEXT_III_REG_EXP);
    }

    @Override
    public Record getRecord() {
        Utils.debug( "Agrupation Editor -> getRecord() :: 1");   
        Record record = new Record();
        boolean validate = dataForm.validate();
        record.setAttribute( "validate", validate);
        Utils.debug( "Agrupation Editor -> getRecord() :: validate = " + validate);
        
        record.setAttribute("id", idEntity.getAttribute("id"));
        record.setAttribute("name", nameText.getValueAsString());
        record.setAttribute("description", description.getValueAsString());
        Utils.debug( "getRecord() :: 2"); 
        return record;
    }
    
     @Override
    public void loadRecord(Record record) {
        dataForm.editRecord(record);
        idEntity.setAttribute("id", record.getAttribute("id"));
        nameText.setValue(record.getAttribute("name"));
        description.setValue(record.getAttribute("description"));
       
        dataForm.rememberValues();
        if(Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_BOARDING_MANAGEMENT_AGRUPATION_ADD_MERCHANT)){
            addChildButton.enable();
        }
    }

    @Override
    public EntityType getEntityTypeEditor() {
        return EntityType.AGRUPATION;
    }
    
    public void createAddChildButton() {
        addChildButton = new BaseButtonItem("addChildButton", "Add Merchant");
        addChildButton.setAlign(Alignment.RIGHT);
        addChildButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {

                addMerchantActionExecuted(0, getId());
            }
        });
        addChildButton.setWidth(80);
        addChildButton.setHeight(30);
        addChildButton.setDisabled(super.getId() == 0);
        addChildButton.setDisabled(!Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_BOARDING_MANAGEMENT_AGRUPATION_ADD_MERCHANT));
    }
    
     
}
