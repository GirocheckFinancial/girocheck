/*
 ** File: BaseSelectItem.java
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
package com.smartbt.vtsuite.vtams.client.gui.component;

import com.smartbt.vtsuite.vtams.client.helpers.ValidatorHelper;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomDataType;
import com.smartbt.vtsuite.vtcommon.validator.Constants;
import com.smartbt.vtsuite.vtcommon.validator.RegExp;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.FormErrorOrientation;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.validator.RequiredIfFunction;
import java.util.LinkedHashMap;
import com.smartgwt.client.widgets.form.validator.Validator;
import java.util.ArrayList;

/**
 * The Base Parameter Value Item
 *
 * @author Ariamnet Lopez, Ariel Saavedra
 */
public class BaseValueItem extends ComboBoxItem {

    private Boolean isRequired;

    /**
     * Constructor
     *
     * @param name the item name
     * @param isRequired Whether a non-empty value is required for this field to
     * pass validation
     */
    public BaseValueItem(String name, Boolean isRequired) {
        super(name);
        this.isRequired = isRequired;
        setWrapTitle(false);
        setValueMap();
        setRequired(isRequired);
        setErrorOrientation(FormErrorOrientation.RIGHT);
        setSelectOnFocus(false);
        setValidateOnExit(true);
        //setValidateOnChange(true);
    }

    public void updateValueDataType(Record dataType) {
        if (dataType != null) {
            updateValueDataType(NomDataType.getDataTypeById(dataType.getAttributeAsInt("id")));
        } else {
            setValue("");
            setTypeForTextValue();
            setDisabled(Boolean.TRUE);
            setValidators(null);
            validate();
        }
    }

    /**
     * Change the component type to support Boolean or Text values
     *
     * @param dataType
     */
    public void updateValueDataType(NomDataType dataType) {
        //clearValue();
        //setValidators(null);
        ArrayList<Validator> validators = new ArrayList<Validator>();
        switch (dataType) {
            case BOOLEAN:
                // validators.add(ValidatorHelper.getIsBooleanValidator());
                setTypeForBooleanValue();
                break;
            case DOUBLE:
                validators.add(ValidatorHelper.getFloatRangeValidator(1, 9, 2));
                setTypeForTextValue();
                setKeyPressFilter(RegExp.EXP_REG_KEY_FLOAT);
                setLength(Constants.STANDARD_TEXT_MAX_LENGTH);
                break;
            case INTEGER:
                validators.add(ValidatorHelper.getIntegerRangeValidator(1, 9));
                setTypeForTextValue();
                setKeyPressFilter(RegExp.EXP_REG_KEY_INTEGER);
                setLength(Constants.STANDARD_TEXT_MAX_LENGTH);
                break;
            case EMAIL:
                validators.add(ValidatorHelper.getEmailValidator());
                setTypeForTextValue();
                setKeyPressFilter(RegExp.EXP_REG_KEY_EMAIL);
                setLength(Constants.MEDIUM_TEXT_MAX_LENGTH);
                break;
            case STRING:
                validators.add(ValidatorHelper.getTextVIValidator(Constants.STANDARD_TEXT_MIN_LENGTH, Constants.MEDIUM_TEXT_MAX_LENGTH));
                setKeyPressFilter(RegExp.VALID_TEXT_VI_REG_EXP);
                setTypeForTextValue();
                setLength(Constants.MEDIUM_TEXT_MAX_LENGTH);
                break;
            default:
                setTypeForTextValue();
                setKeyPressFilter(RegExp.VALID_TEXT_VI_REG_EXP);
                setLength(Constants.MEDIUM_TEXT_MAX_LENGTH);
                break;
        }
        if (isRequired) {
            validators.add(ValidatorHelper.getRequiredIfValidator(new RequiredIfFunction() {

                public boolean execute(FormItem formItem, Object value) {
                    return true;
                }
            }));
        }

        setValidators(validators.toArray(new Validator[validators.size()]));
        setDisabled(Boolean.FALSE);
        getForm().clearErrors(false);
    }

    /**
     * Change the component type to support Boolean values.
     *
     */
    private void setTypeForBooleanValue() {
        LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
        valueMap.put("true", "Enabled");
        valueMap.put("false", "Disabled");

        setAddUnknownValues(false);
        setValueMap(valueMap);
        showIcon("picker");
        setTextBoxStyle("selectItemText");
    }

    /**
     * Change the component type to support Text values.
     *
     */
    private void setTypeForTextValue() {
        setAddUnknownValues(true);
        setValueMap();
        hideIcon("picker");
        setTextBoxStyle("textItem");
    }

    @Override
    public void setValue(String value) {
        if (value.equalsIgnoreCase(Boolean.TRUE.toString()) || value.equalsIgnoreCase(Boolean.FALSE.toString())) {
            value = value.toLowerCase();
        }
        super.setValue(value);
    }
}
