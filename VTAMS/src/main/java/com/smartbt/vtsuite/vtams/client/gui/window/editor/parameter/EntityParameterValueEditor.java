/*
 ** File: EntityParameterValueEditor.java
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
package com.smartbt.vtsuite.vtams.client.gui.window.editor.parameter;

import com.smartbt.vtsuite.vtams.client.classes.i18n.I18N;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;

import com.smartbt.vtsuite.vtams.client.gui.base.BaseEditorWindow;
import com.smartbt.vtsuite.vtams.client.gui.component.BaseValueItem;
import com.smartbt.vtsuite.vtams.client.gui.component.BaseSelectItem;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.parameter.ParameterDS;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.parameter.ParameterValueDS;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.form.fields.events.ChangeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangeHandler;
import com.smartgwt.client.widgets.form.fields.events.DataArrivedEvent;
import com.smartgwt.client.widgets.form.fields.events.DataArrivedHandler;
import com.smartgwt.client.widgets.form.fields.events.KeyUpEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyUpHandler;

/**
 * The Entity Parameter Value Editor Window
 *
 * @author Ariamnet Lopez, Ariel Saavedra
 */
public class EntityParameterValueEditor extends BaseEditorWindow {

    private BaseValueItem valueSelect;
    private BaseSelectItem parameterSelect;
    private EntityType entityType;

    /**
     * Constructor
     *
     * @param entityType the entity type. See
     * {@link com.smartbt.vtsuite.vtams.client.classes.EntityType EntityType}
     */
    public EntityParameterValueEditor(final EntityType entityType) {
        super(I18N.GET.WINDOW_PARAMETER_TITLE());

        this.entityType = entityType;
        valueSelect = new BaseValueItem("value", true);
        valueSelect.addKeyUpHandler(new KeyUpHandler() {
            public void onKeyUp(KeyUpEvent event) {
                if (event.getKeyName().equals("Enter")) {
                    SaveActionExecuted();
                }
            }
        });

        parameterSelect = new BaseSelectItem("idParameter", I18N.GET.LABEL_PARAMETER_TITLE(), new ParameterDS(entityType), true);
        parameterSelect.showDescription();
        parameterSelect.setDisplayField("parameter");
        parameterSelect.setEmptyDisplayValue(I18N.GET.MESSAGE_EMPTY_PARAMETER_SELECT());
        parameterSelect.addDataArrivedHandler(new DataArrivedHandler() {
            public void onDataArrived(DataArrivedEvent event) {
                if (dataForm.isNewRecord() && parameterSelect.getValue() != null) {
                    parameterSelect.enable();
                } else {
                    parameterSelect.disable();
                }
                if (parameterSelect.getSelectedRecord() != null) {
                    valueSelect.updateValueDataType(parameterSelect.getSelectedRecord().getAttributeAsRecord("dataType"));
                }
            }
        });

        parameterSelect.addChangeHandler(new ChangeHandler() {
            public void onChange(ChangeEvent event) {
                valueSelect.clearValue();
                valueSelect.updateValueDataType(parameterSelect.getSelectedRecord().getAttributeAsRecord("dataType"));
                //valueSelect.changeParameterValueItemType((Integer) event.getValue(), type);
            }
        });

        dataForm.setDataSource(new ParameterValueDS(entityType));
        dataForm.setFields(parameterSelect, valueSelect);
    }

    /**
     * Prepare to update the record by setting to the editor the values of the
     * record passed in.
     *
     * @param record the Parameter Value record
     */
    @Override
    public void updateRecord(Record record) {
        redraw();
        super.updateRecord(record);
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

        parameterSelect.setOptionDataSource(new ParameterDS(entityType));
        Criteria formCriteria = dataForm.getValuesAsCriteria();
        parameterSelect.setPickListCriteria(formCriteria);
        parameterSelect.fetchData();
        parameterSelect.setDefaultToFirstOption(true);
        redraw();
    }

    public BaseSelectItem getParameterSelect() {
        return parameterSelect;
    }

    public BaseValueItem getValueSelect() {
        return valueSelect;
    }
}
