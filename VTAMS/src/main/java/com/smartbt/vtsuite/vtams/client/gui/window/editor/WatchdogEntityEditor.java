/*
 ** File: WatchdogEntityEditor.java
 **
 ** Date Created: October 2013
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

import com.smartbt.vtsuite.vtams.client.classes.Properties;
import com.smartbt.vtsuite.vtams.client.classes.i18n.I18N;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseDatasource;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseEditorWindow;
import com.smartbt.vtsuite.vtams.client.gui.component.BaseSelectItem;
import com.smartbt.vtsuite.vtams.client.gui.component.BaseTextAreaItem;
import com.smartbt.vtsuite.vtams.client.gui.component.ClerkPickerCanvasItem;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.watchdog.WatchdogDS;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.watchdog.WatchdogEntityDS;
import com.smartbt.vtsuite.vtams.client.helpers.ValidatorHelper;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomDataType;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomWatchdog;
import com.smartbt.vtsuite.vtcommon.validator.Constants;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.types.FormErrorOrientation;
import com.smartgwt.client.types.MultipleAppearance;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.form.fields.HiddenItem;
import com.smartgwt.client.widgets.form.fields.RowSpacerItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.form.fields.events.KeyPressEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyPressHandler;
import com.smartgwt.client.widgets.form.validator.RequiredIfFunction;
import com.smartgwt.client.widgets.form.validator.Validator;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import java.util.ArrayList;

/**
 * The WatchdogEntityEditor Class
 *
 * @author Ariel Saavedra
 */
public class WatchdogEntityEditor extends BaseEditorWindow {

    private HiddenItem idWatchdogEntity;

    private BaseSelectItem watchdogSelect;
    private BaseTextAreaItem value;
    private ClerkPickerCanvasItem clerkPicker;
    private Record loaded;
    private EntityType entityType;
    private int idEntity;

    public WatchdogEntityEditor(final EntityType entityType, Record recordEntity) {
        super(I18N.GET.TAB_WATCHDOG_TITLE());
        this.entityType = entityType;
        this.idEntity = recordEntity.getAttributeAsInt("id");

        idWatchdogEntity = new HiddenItem();

        value = new BaseTextAreaItem("value", I18N.GET.LABEL_VALUE_WATCHDOG_TITLE(), true);
        value.setWidth(200);
        value.setHeight(20);
        value.setDisabled(true);
        value.addKeyPressHandler(new KeyPressHandler() {
            public void onKeyPress(KeyPressEvent event) {
                if (event.getKeyName().equals("Enter")) {
                    if (watchdogSelect.getSelectedRecord().getAttributeAsInt("id") != NomWatchdog.INFO.getId()) {
                        event.cancel();
                        SaveActionExecuted();
                    }
                }
            }
        });

        ListGrid pickListProperties = new ListGrid();
        pickListProperties.setShowFilterEditor(true);

        watchdogSelect = new BaseSelectItem("watchdog", I18N.GET.LABEL_WATCHDOG_TITLE(), new WatchdogDS(), true);
        if (entityType == EntityType.ENTERPRISE) {
            Record dataTypeRecord = new Record();
            dataTypeRecord.setAttribute("id", NomDataType.STRING.getId());
            Record infoWatchdogRecord = new Record();
            infoWatchdogRecord.setAttribute("id", NomWatchdog.INFO.getId());
            infoWatchdogRecord.setAttribute("name", NomWatchdog.INFO.toString());
            infoWatchdogRecord.setAttribute("dataType", dataTypeRecord);

            watchdogSelect.setOptionDataSource(new BaseDatasource(infoWatchdogRecord));
            watchdogSelect.setDefaultToFirstOption(true);
            watchdogSelect.setCanEdit(false);
            updateValueDataType(infoWatchdogRecord);
        }

        watchdogSelect.setValidateOnExit(true);
        watchdogSelect.setValidateOnChange(true);
        watchdogSelect.setWidth(660);
        watchdogSelect.setPickListWidth(600);
        watchdogSelect.setDataPath("watchdog");
        watchdogSelect.setAutoFetchData(true);
        watchdogSelect.setMultipleAppearance(MultipleAppearance.PICKLIST);
        watchdogSelect.setPickListProperties(pickListProperties);
        ListGridField nameWatchdog = new ListGridField("name");
        nameWatchdog.setWidth(120);
        watchdogSelect.setPickListFields(nameWatchdog, new ListGridField("description"));
        watchdogSelect.addChangedHandler(new ChangedHandler() {

            public void onChanged(ChangedEvent event) {
                value.clearValue();
                updateValueDataType(watchdogSelect.getSelectedRecord());
            }
        });

        getResetButton().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                clerkPicker.getTargetClerkTree().removeNodes();
                updateRecord(loaded);
            }
        });

        clerkPicker = new ClerkPickerCanvasItem(entityType, recordEntity, I18N.GET.LABEL_WATCHDOG_SEND_TO_TITLE(), I18N.GET.MESSAGE_EMPTY_WATCHDOG_CLERKS_LIST());

        dataForm.setErrorOrientation(FormErrorOrientation.RIGHT);
        dataForm.setTitleOrientation(TitleOrientation.TOP);
        dataForm.setDataSource(new WatchdogEntityDS());
        dataForm.setFields(watchdogSelect,
                new RowSpacerItem(),
                value,
                new RowSpacerItem(),
                clerkPicker
        );
    }

    @Override
    public void init() {
        super.init();
        if (entityType != EntityType.ENTERPRISE) {
            updateValueDataType(new Record());
        }
        //clerkPicker.clearAndLoadClerkTree();
    }

    @Override
    public void updateRecord(Record record) {
        loaded = record;
        if (record != null) {
            clerkPicker.getTargetClerkTree().removeNodes();
            Criteria criteria = new Criteria();
            criteria.setAttribute("id", record.getAttributeAsInt("id"));

            WatchdogEntityDS watchdogEntityDS = new WatchdogEntityDS();
            watchdogEntityDS.setFetchDataURL(Properties.GET_WATCHDOG_ENTITY);
            watchdogEntityDS.fetchData(criteria, new DSCallback() {
                public void execute(DSResponse response, Object rawData, DSRequest request) {
                    updateRecordParent(response.getData()[0]);
                }
            }, null);
        } else {
            idWatchdogEntity.setAttribute("id", 0);
            clerkPicker.clearAndLoadClerkTree();
            clerkPicker.getTargetClerkTree().removeNodes();
        }
    }

    private void updateRecordParent(Record record) {
        //Updating
        if (record.getAttributes().length > 1) {
            if (record.getAttributeAsRecord("watchdog") != null) {
                updateValueDataType(record.getAttributeAsRecord("watchdog"));
            }
            loadRecord(record);
            dataForm.validate();
        }
    }

    private void loadRecord(Record record) {
        idWatchdogEntity.setAttribute("id", record.getAttribute("id"));
        value.setValue(record.getAttribute("value"));

        watchdogSelect.setValue(record.getAttributeAsRecord("watchdog").getAttributeAsInt("id"));

        Record[] watchdogEntityClerks = record.getAttributeAsRecordArray("watchdogEntityClerk");
        Record[] clerksRecord = new Record[watchdogEntityClerks.length];

        for (int i = 0; i < watchdogEntityClerks.length; i++) {
            clerksRecord[i] = watchdogEntityClerks[i].getAttributeAsRecord("clerk");
        }
        clerkPicker.getTargetClerkTree().addNodes(clerkPicker.getTargetClerkTree().getRootNode(), EntityType.CLERK, clerksRecord);
        clerkPicker.clearAndLoadClerkTree();

        dataForm.rememberValues();
    }

    private void updateValueDataType(Record watchdog) {

        NomDataType dataType = watchdog.getAttributeAsRecord("dataType") == null ? null
                : NomDataType.getDataTypeById(
                        watchdog.getAttributeAsRecord("dataType").getAttributeAsInt("id"));

        value.setHeight(20);
        value.setWidth(200);
        if (dataType == null) {
            value.setDisabled(Boolean.TRUE);
            value.setValidators(null);
        } else {
            ArrayList<Validator> validators = new ArrayList<Validator>();
            switch (dataType) {
                case DOUBLE:
                    validators.add(ValidatorHelper.getFloatRangeValidator(1, 9, 2));
                    value.setLength(Constants.STANDARD_TEXT_MAX_LENGTH);
                    break;
                case INTEGER:
                    validators.add(ValidatorHelper.getIntegerRangeValidator(1, 9));
                    value.setLength(Constants.STANDARD_TEXT_MAX_LENGTH);
                    break;
                case STRING:
                    validators.add(ValidatorHelper.getTextVIValidator(Constants.STANDARD_TEXT_MIN_LENGTH, Constants.HIGH_TEXT_MAX_LENGTH));
                    value.setLength(Constants.HIGH_TEXT_MAX_LENGTH);
                    value.setWidth(650);
                    value.setHeight(50);
                    break;
            }

            validators.add(ValidatorHelper.getRequiredIfValidator(new RequiredIfFunction() {

                public boolean execute(FormItem formItem, Object value) {
                    return true;
                }
            }));

            value.setValidators(validators.toArray(new Validator[validators.size()]));
            value.setDisabled(Boolean.FALSE);
        }
        dataForm.clearErrors(false);
    }

    public Record getRecord() {
        Record entityLevel = new Record();
        entityLevel.setAttribute("id", idEntity);

        Record watchdog = new Record();
        watchdog.setAttribute("id", watchdogSelect.getValue());

        RecordList sendAlertTo = new RecordList();
        for (Record record : clerkPicker.getTargetRecords()) {
            Record item = new Record();
            item.setAttribute("id", record.getAttribute("id"));
            item.setAttribute("entityType", record.getAttribute("entityType"));
            sendAlertTo.add(item);
        }

        //Loading data
        Record watchdogEntityRecord = new Record();
        watchdogEntityRecord.setAttribute("id", idWatchdogEntity.getAttribute("id"));
        watchdogEntityRecord.setAttribute("value", value.getValueAsString());
        watchdogEntityRecord.setAttribute("watchdog", watchdog);
        watchdogEntityRecord.setAttribute("sendAlertTo", sendAlertTo);

        if (entityType != EntityType.ENTERPRISE) {
            watchdogEntityRecord.setAttribute(entityType.toString().toLowerCase(), entityLevel);
        }
        return watchdogEntityRecord;
    }
}
