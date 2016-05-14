/*
 ** File: ApplicationParameterValueEditor.java
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
import com.smartbt.vtsuite.vtcommon.enums.EntityType;

import com.smartbt.vtsuite.vtams.client.gui.base.BaseEditorWindow;
import com.smartbt.vtsuite.vtams.client.gui.component.BaseValueItem;
import com.smartbt.vtsuite.vtams.client.gui.component.BaseSelectItem;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.ApplicationDS;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.parameter.ParameterDS;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.parameter.ParameterValueDS;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.form.fields.events.ChangeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangeHandler;
import com.smartgwt.client.widgets.form.fields.events.DataArrivedEvent;
import com.smartgwt.client.widgets.form.fields.events.DataArrivedHandler;
import com.smartgwt.client.widgets.form.fields.events.KeyUpEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyUpHandler;

/**
 * The Application Parameter Value Editor Window
 *
 * @author Ariamnet Lopez, Ariel Saavedra
 */
public class ApplicationParameterValueEditor extends BaseEditorWindow {

    private BaseSelectItem applicationSelect;
    private BaseSelectItem parameterSelect;
    private BaseValueItem valueSelect;
    private static final int COMPONENTS_WIDTH = 175;

    /**
     * Constructor
     *
     */
    public ApplicationParameterValueEditor() {
        super(I18N.GET.WINDOW_DEFAULT_PARAM_TITLE());

        valueSelect = new BaseValueItem("value", true);
        valueSelect.setWidth(COMPONENTS_WIDTH);
        valueSelect.addKeyUpHandler(new KeyUpHandler() {
            public void onKeyUp(KeyUpEvent event) {
                if (event.getKeyName() != null && event.getKeyName().equals("Enter")) {
                    SaveActionExecuted();
                }
            }
        });

        applicationSelect = new BaseSelectItem("idEntity", I18N.GET.LABEL_APPLICATION_TITLE(), new ApplicationDS(), true);
        applicationSelect.setWidth(COMPONENTS_WIDTH);
        applicationSelect.setEmptyDisplayValue(I18N.GET.MESSAGE_EMPTY_APPLICATION_SELECT());

        parameterSelect = new BaseSelectItem("idParameter", I18N.GET.LABEL_PARAMETER_TITLE(), new ParameterDS(EntityType.APPLICATION_PARAMETER_VALUE), true);
        parameterSelect.setWidth(COMPONENTS_WIDTH);
        parameterSelect.setDisplayField("parameter");

        parameterSelect.setEmptyDisplayValue(I18N.GET.MESSAGE_EMPTY_PARAMETER_SELECT());
        parameterSelect.addDataArrivedHandler(new DataArrivedHandler() {
            public void onDataArrived(DataArrivedEvent event) {
                if (dataForm.isNewRecord() && parameterSelect.getValue() != null) {
                    parameterSelect.enable();
                } else {
                    parameterSelect.disable();
                }
                valueSelect.updateValueDataType(parameterSelect.getSelectedRecord().getAttributeAsRecord("dataType"));
            }
        });

        parameterSelect.addChangeHandler(new ChangeHandler() {
            public void onChange(ChangeEvent event) {
                valueSelect.clearValue();
                valueSelect.updateValueDataType(parameterSelect.getSelectedRecord().getAttributeAsRecord("dataType"));
                valueSelect.setValue(parameterSelect.getSelectedRecord().getAttributeAsString("value"));
            }
        });

       parameterSelect.showDescription();

        dataForm.setDataSource(new ParameterValueDS(EntityType.APPLICATION_PARAMETER_VALUE));
        dataForm.setFields(applicationSelect, parameterSelect, valueSelect);
    }

    /**
     * Prepare to update the record by setting to the editor the values of the
     * record passed in.
     *
     * @param record the Default Param record
     */
    @Override
    public void updateRecord(Record record) {
        super.updateRecord(record);
        //valueSelect.setValue(parameterSelect.getSelectedRecord().getAttributeAsString("value"));
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

        parameterSelect.setOptionDataSource(new ParameterDS(EntityType.APPLICATION_PARAMETER_VALUE));
        Criteria formCriteria = dataForm.getValuesAsCriteria();
        parameterSelect.setPickListCriteria(formCriteria);
        parameterSelect.fetchData(new DSCallback() {

            public void execute(DSResponse response, Object rawData, DSRequest request) {
                valueSelect.updateValueDataType(parameterSelect.getSelectedRecord().getAttributeAsRecord("dataType"));
                valueSelect.setValue(parameterSelect.getSelectedRecord().getAttributeAsString("value").toLowerCase());
            }
        });
        parameterSelect.setDefaultToFirstOption(true);
        //redraw();
    }

    public BaseSelectItem getApplicationSelect() {
        return applicationSelect;
    }

    public BaseSelectItem getParameterSelect() {
        return parameterSelect;
    }

    public BaseValueItem getValueSelect() {
        return valueSelect;
    }
}
