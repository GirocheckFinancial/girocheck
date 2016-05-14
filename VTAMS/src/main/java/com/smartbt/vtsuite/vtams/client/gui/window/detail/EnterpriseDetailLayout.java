/*
 ** File: EnterpriseDetailLayout.java
 **
 ** Date Created: December 2013
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
package com.smartbt.vtsuite.vtams.client.gui.window.detail;


import com.smartbt.vtsuite.vtams.client.classes.Settings;
import com.smartbt.vtsuite.vtams.client.classes.i18n.I18N;
import com.smartbt.vtsuite.vtams.client.gui.base.detail.BaseDetailRowHLayout;
import com.smartbt.vtsuite.vtams.client.gui.base.detail.BaseDetailLayout;
import com.smartbt.vtsuite.vtams.client.gui.listener.EditorListener;
import com.smartbt.vtsuite.vtams.client.gui.window.editor.WatchdogEntityEditor;
import com.smartbt.vtsuite.vtcommon.Constants;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomUserPrivileges;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.RowSpacerItem;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;

/**
 * The Enterprise Detail Layout
 *
 * @author Ariel Saavedra
 */
public class EnterpriseDetailLayout extends BaseDetailLayout {

    private WatchdogEntityEditor editorWindow;

    public EnterpriseDetailLayout(Record record) {
        super(record);
        editorWindow = new WatchdogEntityEditor(EntityType.ENTERPRISE, record);
        editorWindow.addListener(new EditorListener() {
            /**
             * Method to execute when a Save event is fired.
             *
             */
            public void SaveActionExecuted() {
                Save();
            }

            /**
             * Method to execute when a Close event is fired.
             *
             */
            public void CloseActionExecuted() {
                editorWindow.hide();
            }
        });
    }

    @Override
    protected void addLeftTopFields() {
        vLayoutLeftTop.addMembers(
                BaseDetailRowHLayout.getRow(I18N.GET.DETAIL_NAME(), I18N.GET.GIROCHECK_NAME()),
                BaseDetailRowHLayout.getRow(I18N.GET.DETAIL_DESCRIPTION(), I18N.GET.GIROCHECK_AMS_FULL_NAME()),
                BaseDetailRowHLayout.getRow(I18N.GET.DETAIL_VERSION(), I18N.GET.GIROCHECK_AMS_VERSION()));
    }

    @Override
    protected void addRightTopFields() {
         final ButtonItem watchdogInfo = new ButtonItem();
        watchdogInfo.setAlign(Alignment.LEFT);
        watchdogInfo.setTitle(I18N.GET.BUTTON_WATCHDOG_SEND_INFO());
        watchdogInfo.setWidth(100);

        watchdogInfo.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
            public void onClick(ClickEvent event) {
                editorWindow.updateRecord(null);
                editorWindow.show();
            }
        });

        final DynamicForm contentForm = new DynamicForm();
        contentForm.setTitleOrientation(TitleOrientation.LEFT);
        contentForm.setFields(watchdogInfo);

        vLayoutRightBottom.addMembers(contentForm);
        
//       contentForm.setDisabled(!Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_WATCHDOG_RULES_ADD)); 
       contentForm.setDisabled(false); 
        
        vLayoutRightTop.addMembers(contentForm);
        //BaseDetailRowHLayout.getRow("Version:", "Something 2"));
        
    }

    @Override
    protected void addLeftBottomFields() {
       

    }

    @Override
    protected void addRightBottomFields() {
    }

    /**
     * Save method
     *
     */
    private void Save() {
        Record recordToSave = editorWindow.getRecord();
        editorWindow.hide();
        editorWindow.getDataForm().getDataSource().addData(recordToSave, new DSCallback() {
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
//                    if (entityType == EntityType.ENTERPRISE) {
//                        SC.say(I18N.GET.INFORMATION_WINDOW_TITLE, "The info has been sent.");
//                    }
                } else {
                    SC.warn(I18N.GET.WINDOW_ERROR_TITLE(), I18N.GET.MESSAGE_ERROR_ACTION());
                }
            }
        });
    }
}
