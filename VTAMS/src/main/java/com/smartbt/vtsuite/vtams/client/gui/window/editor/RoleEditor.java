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
import com.smartbt.vtsuite.vtams.client.gui.component.BaseTextItem;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.RoleDS;
import com.smartbt.vtsuite.vtams.client.helpers.ValidatorHelper;
import com.smartbt.vtsuite.vtcommon.validator.Constants;
import com.smartbt.vtsuite.vtcommon.validator.RegExp;
import com.smartgwt.client.types.FormErrorOrientation;
import com.smartgwt.client.widgets.form.fields.HiddenItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;

/**
 * The Role Editor Window
 *
 * @author Ariamnet Lopez, Ariel Saavedra
 */
public final class RoleEditor extends BaseEditorWindow {

    private HiddenItem entityTypeHidden;
    private EntityType entityType;

    /**
     * Constructor
     *
     * @param entityType
     */
    public RoleEditor(EntityType entityType) {
        super(I18N.GET.WINDOW_ROLE_TITLE());
        this.entityType = entityType;

        entityTypeHidden = new HiddenItem("entityType");
        init();
        BaseTextItem nameText = new BaseTextItem("name", true);
        TextAreaItem descriptionText = new TextAreaItem("description", I18N.GET.LABEL_DESCRIPTION_TITLE());
        descriptionText.setHeight(50);

        nameText.setLength(Constants.STANDARD_TEXT_MAX_LENGTH);
        descriptionText.setLength(Constants.MEDIUM_TEXT_MAX_LENGTH);
        descriptionText.setRequired(true);
        descriptionText.setErrorOrientation(FormErrorOrientation.RIGHT);
        
        nameText.setKeyPressFilter(RegExp.VALID_TEXT_V_REG_EXP);
        //descriptionText.setKeyPressFilter(RegExp.VALID_TEXT_VI_REG_EXP);

        nameText.setValidators(ValidatorHelper.getTextVValidator());
        nameText.setValidators(ValidatorHelper.getTextVIValidator());

        dataForm.setDataSource(new RoleDS());
        dataForm.setFields(nameText, descriptionText, entityTypeHidden);
    }

    @Override
    public void init() {
        super.init();
        entityTypeHidden.setValue(entityType.toString());
    }
}
