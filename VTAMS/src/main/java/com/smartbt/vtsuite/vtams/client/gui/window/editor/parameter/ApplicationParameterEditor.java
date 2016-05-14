/*
 ** File: DefaultParamEditor.java
 **
 ** Date Created: May 2013
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
package com.smartbt.vtsuite.vtams.client.gui.window.editor.parameter;


import com.smartbt.vtsuite.vtams.client.classes.i18n.I18N;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseEditorWindow;
import com.smartbt.vtsuite.vtams.client.gui.component.BaseSelectItem;
import com.smartbt.vtsuite.vtams.client.gui.component.BaseTextItem;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.DataSourceBuilder;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.parameter.ApplicationParameterDS;
import com.smartbt.vtsuite.vtams.client.utils.Utils;
import com.smartbt.vtsuite.vtcommon.validator.Constants;
import com.smartbt.vtsuite.vtcommon.validator.RegExp;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.HiddenItem;

/**
 * The Application Parameter Editor Window
 *
 * @author Ariel Saavedra
 */
public class ApplicationParameterEditor extends BaseEditorWindow {
    protected Integer idParameter;
    private BaseSelectItem applicationSelect;
    private BaseTextItem parameterText;
    private BaseTextItem valueText;
    private BaseTextItem descriptionText;
    private CheckboxItem readOnlyCheckBox;
   

    /**
     * Constructor
     *
     */
    public ApplicationParameterEditor() {
        super(I18N.GET.WINDOW_APP_PARAM_TITLE());
        
        this.idParameter = 0;
        
        applicationSelect = new BaseSelectItem(1, "application", "Application","Enter the application for this parameter.",  DataSourceBuilder.getApplicationDS() , true);
        applicationSelect.setWidth( 115 );
        
        parameterText = new BaseTextItem(2, "name", "Parameter Name", "Enter the name for this parameter.", true);
        parameterText.setWidth( 115 );
       
        valueText = new BaseTextItem(3, "value", "Value", "Enter the value for this parameter.",  true);
        valueText.setWidth( 115 );
        
        descriptionText = new BaseTextItem(4, "description", "Description","Enter the description for this parameter.", true);
        descriptionText.setWidth( 115 );

        readOnlyCheckBox = new CheckboxItem( "readOnly", "Read Only" );
        
        // Set fields Validators --------------------------------------------------------------------------------
        parameterText.setLength(Constants.STANDARD_TEXT_MAX_LENGTH);
        descriptionText.setLength(Constants.MEDIUM_TEXT_MAX_LENGTH);
        
        parameterText.setKeyPressFilter(RegExp.VALID_TEXT_REG_EXP);
        descriptionText.setKeyPressFilter(RegExp.VALID_TEXT_V_REG_EXP);

        //dataFo
        dataForm.setDataSource(new ApplicationParameterDS());
        dataForm.setFields( applicationSelect, parameterText, valueText, descriptionText, readOnlyCheckBox);
    }

    /**
     * Prepare to update the record by setting to the editor the values of the
     * record passed in.
     *
     * @param record the Default Param record
     */
    @Override
    public void updateRecord(Record record) {
        int newId = record.getAttributeAsInt( "id" );
        Utils.debug( "updateRecord  -> :: 1"); 
        super.updateRecord(record);
        Utils.debug( "updateRecord  -> :: id = " + newId);
        
        this.idParameter = newId;
        applicationSelect.setValue(record.getAttributeAsInt("application"));
        dataForm.rememberValues();
        dataForm.clearErrors(true);
    }

    /**
     * Prepare to add a new record by setting to the editor the default values
     * of the record passed in.
     *
     * @param record the Default Param record
     */
    @Override
    public void addRecord(Record record) {
        super.addRecord(record);
    }
    
    public Record getRecord() {
        Utils.debug( "ApplicationParameterEditor  -> getRecord() :: 1"); 
        //Loading data
        Record appParameterRecord = new Record(); 
        Utils.debug( "ApplicationParameterEditor  -> getRecord() :: id = " + idParameter); 
        appParameterRecord.setAttribute("id", idParameter);
        appParameterRecord.setAttribute("name", parameterText.getValueAsString());
        appParameterRecord.setAttribute("value", valueText.getValueAsString());
        appParameterRecord.setAttribute("description", descriptionText.getValueAsString());
        appParameterRecord.setAttribute("application", applicationSelect.getSelectedRecord() != null ? applicationSelect.getSelectedRecord().getAttributeAsInt( "id" ) : applicationSelect.getValue() );
        appParameterRecord.setAttribute( "readOnly", readOnlyCheckBox.getValueAsBoolean() );
        return appParameterRecord;
    }

    public void setIdParameter( Integer idParameter ) {
        this.idParameter = idParameter;
    }

    public Integer getIdParameter() {
        return idParameter;
    }
    
    
}
