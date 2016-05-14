/*
 ** File: DefaultParamFilterForm.java
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
package com.smartbt.vtsuite.vtams.client.gui.window.filter;


import com.smartbt.vtsuite.vtams.client.classes.Settings;
import com.smartbt.vtsuite.vtams.client.classes.i18n.I18N;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseFilterForm;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseInterface;
import com.smartbt.vtsuite.vtams.client.gui.component.BaseButtonItem;
import com.smartbt.vtsuite.vtams.client.gui.component.BaseSelectItem;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.ApplicationDS;
import com.smartbt.vtsuite.vtams.client.gui.listener.EditorListener;
import com.smartbt.vtsuite.vtams.client.gui.window.editor.ApplicationEditor;
import com.smartbt.vtsuite.vtcommon.Constants;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomUserPrivileges;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;

/**
 * The Default Param Filter Form
 *
 * @author Ariamnet Lopez
 */
public class ApplicationParameterValueFilterForm extends BaseFilterForm implements BaseInterface {

    private ApplicationEditor editorWindow;
    private BaseSelectItem applicationSelect;
    private BaseButtonItem newApplicationButton;

    /**
     * Constructor
     *
     */
    public ApplicationParameterValueFilterForm() {
        super();
        editorWindow = new ApplicationEditor();

        applicationSelect = new BaseSelectItem("idEntity", I18N.GET.LABEL_APPLICATION_TITLE(), new ApplicationDS(), false);
        applicationSelect.setEmptyDisplayValue(I18N.GET.MESSAGE_EMPTY_APPLICATION_SELECT());
        applicationSelect.setDefaultToFirstOption(true);
        applicationSelect.setAutoFetchData(true);

        applicationSelect.addChangedHandler(new ChangedHandler() {
            public void onChanged(ChangedEvent event) {
                Filter();
            }
        });

        newApplicationButton = new BaseButtonItem("newApplicationButton", I18N.GET.BUTTON_NEW_TITLE());
        newApplicationButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                Add();
            }
        });

        editorWindow.addListener(new EditorListener() {
            public void SaveActionExecuted() {
                Save();
            }

            public void CloseActionExecuted() {
                editorWindow.hide();
            }
        });

     //   checkPrivileges();
        setFields(new FormItem[]{applicationSelect, newApplicationButton, addButton, updateButton, deleteButton});
    }

    private void checkPrivileges() {
//        newApplicationButton.setDisabled(!Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_BOARDING_APP_PARAM_APP_MANAGEMENT_NEW_APP));
//        addButton.setDisabled(!Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_BOARDING_APP_PARAM_APP_MANAGEMENT_ADD));
//        updateButton.setDisabled(!Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_BOARDING_APP_PARAM_APP_MANAGEMENT_UPDATE));
//        //deleteButton.setDisabled(!Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_BOARDING_DEFAULTPARAM_));
        newApplicationButton.setDisabled(!Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_BOARDING));
        addButton.setDisabled(!Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_BOARDING));
        updateButton.setDisabled(!Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_BOARDING));
        //deleteButton.setDisabled(!Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_BOARDING_DEFAULTPARAM_));
    }

    /**
     * Get Application select
     *
     * @return BaseSelectItem
     */
    public BaseSelectItem getApplicationSelect() {
        return applicationSelect;
    }

    /**
     * Filter method
     *
     */
    public void Filter() {
        FilterActionExecuted();
//        addButton.setDisabled(!Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_BOARDING_APP_PARAM_APP_MANAGEMENT_ADD));
        addButton.setDisabled(!Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_BOARDING));
    }

    /**
     * Add method
     *
     */
    public void Add() {
        editorWindow.show();
    }

    /**
     * Update method
     *
     * @param record the record to update
     */
    public void Update(Record record) {
        // No supported for Applications
    }

    /**
     * Delete method
     *
     */
    public void Delete() {
        // No supported for Applications
    }

    /**
     * Save method
     *
     */
    public void Save() {
        editorWindow.getDataForm().saveData(new DSCallback() {
            /**
             * Callback to invoke on completion
             *
             * @param response Response sent by the server in response to a
             * DataSource request.
             * @param rawData data
             * @param request Request sent to the server to initiate a
             * DataSource operation.
             */
            public void execute(DSResponse response, Object rawData, DSRequest request) {
                if (response.getStatus() == Constants.CODE_SUCCESS) {
                    editorWindow.hide();
                    applicationSelect.fetchData();
                }
            }
        });
    }
}
