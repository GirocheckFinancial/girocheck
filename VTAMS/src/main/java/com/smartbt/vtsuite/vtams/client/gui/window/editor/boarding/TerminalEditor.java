/*
 *  File TerminalEditor
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
import com.smartbt.vtsuite.vtams.client.gui.component.BaseTextAreaItem;
import com.smartbt.vtsuite.vtams.client.gui.component.BaseTextItem;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.TerminalDS;
import com.smartbt.vtsuite.vtams.client.utils.Utils;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomUserPrivileges;
import com.smartbt.vtsuite.vtcommon.validator.Constants;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.form.fields.RowSpacerItem;
import com.smartgwt.client.widgets.form.fields.SpacerItem;

/**
 *
 * @author Ariel Saavedra
 */
public class TerminalEditor extends BaseBoardingEditor {

    private BaseTextItem serialNumberText;
    private BaseTextItem terminalUserText;
    private BaseTextItem terminalPasswordText;
    private BaseTextItem idPOSOrderExp;
    
    protected SpacerItem spacerItem1;
    
    private BaseTextAreaItem descriptionTextArea;

    public TerminalEditor(int id, int idParent) {
        super(id, idParent, EntityType.TERMINAL);
        
        Utils.debug("Terminal Editor's constructor");
        dataForm.setNumCols(3);
        dataForm.setColWidths("33%", "33%");
        
        spacerItem1 = new SpacerItem();
        spacerItem1.setColSpan(1);
        acceptButton.setDisabled(true);
        if (Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_BOARDING_MANAGEMENT_TERMINAL_ADD)) {
            acceptButton.setDisabled(false);
        }

        actionForm.setFields( spacerItem, spacerItem1, acceptButton );

        serialNumberText = new BaseTextItem(1, "serialNumber", I18N.GET.DETAIL_SERIAL_NUMBER(), I18N.GET.TOOLTIP_SERIAL_NUMBER(), true);
        
        terminalUserText = new BaseTextItem(2, "terminalUser", "Terminal User", "Enter the user for the terminal.", true);
        idPOSOrderExp = new BaseTextItem(3, "idPOSOrderExp", "ID OrderExpress", "Enter the ID for the OrderExpress terminal.", false);
        terminalPasswordText = new BaseTextItem(4, "terminalPassword", "Terminal Password", "Enter the password for the terminal.", true);
       
        descriptionTextArea = new BaseTextAreaItem(5, "description", I18N.GET.LABEL_DESCRIPTION_TITLE(), null, false);
        descriptionTextArea.setColSpan(3);
        descriptionTextArea.setLength(Constants.HIGH_TEXT_MAX_LENGTH);

        dataForm.setDataSource(new TerminalDS());
        
         //----------- validators lenght---------------------------------------------------------------------------------------------------------
        terminalUserText.setLength( Constants.MEDIUM_TEXT_MAX_LENGTH);
        terminalPasswordText.setLength( Constants.MEDIUM_TEXT_MAX_LENGTH);
        idPOSOrderExp.setLength( Constants.MEDIUM_TEXT_MAX_LENGTH);
        serialNumberText.setLength( Constants.MEDIUM_TEXT_MAX_LENGTH);
         //--------------------------------------------------------------------------------------------------------------------
        
        dataFormSetFields(idEntity,
                serialNumberText, terminalUserText,terminalPasswordText, idPOSOrderExp,
                new RowSpacerItem(),
                descriptionTextArea);
       
    }

    @Override
    public Record getRecord() {
       
        
        Record record = new Record(); 
        record.setAttribute( "validate", dataForm.validate());
        record.setAttribute("id",  idEntity.getAttribute( "id" ) );
        record.setAttribute("idMerchant",  idParent );
        
        record.setAttribute("serialNumber", serialNumberText.getValueAsString());
        record.setAttribute("terminalUser", terminalUserText.getValueAsString());
        record.setAttribute("terminalPassword", terminalPasswordText.getValueAsString());
        record.setAttribute("idPOSOrderExp", idPOSOrderExp.getValueAsString());
        record.setAttribute("description", descriptionTextArea.getValueAsString());

        return record;
    }

    @Override
    public void loadRecord(Record record) {
        dataForm.editRecord(record);
        idEntity.setAttribute("id", record.getAttribute("id"));

        dataForm.rememberValues();
    }

    @Override
    public EntityType getEntityTypeEditor() {
        return EntityType.TERMINAL;
    }
    
        public void createAddChildButton(){
    }
    
}
