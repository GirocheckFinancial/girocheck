/*
 ** File: TransactionFilterForm.java
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

import com.smartbt.vtsuite.vtams.client.classes.Properties;
import com.smartbt.vtsuite.vtams.client.classes.Settings;
import com.smartbt.vtsuite.vtams.client.classes.i18n.I18N;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseFilterForm;
import com.smartbt.vtsuite.vtams.client.gui.component.BaseButtonItem;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.MonitoringDS;
import com.smartbt.vtsuite.vtcommon.Constants;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomUserPrivileges;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.SpacerItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.form.fields.events.KeyPressEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyPressHandler;
import com.smartgwt.client.widgets.form.validator.DateRangeValidator;
import java.util.Date;

/**
 * The Transaction Filter Form
 *
 * @author Ariamnet Lopez
 */
public class MonitoringFilterForm extends BaseFilterForm {

    /**
     * The Start Date field
     *
     */
    public DateItem startRangeDate;
    /**
     * The End Date field
     *
     */
    public DateItem endRangeDate;

    /**
     * The Activate and Deactivation button
     *
     */
    protected BaseButtonItem activateButton;

    /**
     * Constructor
     *
     * @param recordEntity
     */
    public MonitoringFilterForm(final Record recordEntity) {
        super();

        startRangeDate = new DateItem("startRangeDate", I18N.GET.LABEL_START_RANGE_TITLE());
        endRangeDate = new DateItem("endRangeDate", I18N.GET.LABEL_END_RANGE_TITLE());

        startRangeDate.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
        endRangeDate.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
        
        startRangeDate.setUseMask(true);
        endRangeDate.setUseMask(true);

        startRangeDate.setUseTextField(true);
        endRangeDate.setUseTextField(true);

        startRangeDate.setValidateOnChange(true);
        endRangeDate.setValidateOnChange(true);

        final DateRangeValidator maxValidator = new DateRangeValidator();
        Date maxDate = new Date();
        maxDate.setHours(24);
        maxValidator.setMax(maxDate);

        startRangeDate.setValidators(maxValidator);
        endRangeDate.setValidators(maxValidator);

        startRangeDate.setEndDate(new Date());
        endRangeDate.setEndDate(new Date());

        startRangeDate.addChangedHandler(new ChangedHandler() {
            public void onChanged(ChangedEvent event) {
                if (startRangeDate.validate()) {
                    DateRangeValidator minValidator = new DateRangeValidator();
                    minValidator.setMin(startRangeDate.getValueAsDate());
                    endRangeDate.setValidators(maxValidator, minValidator);
                    endRangeDate.validate();
                }
            }
        });

        KeyPressHandler onEnter = new KeyPressHandler() {
            public void onKeyPress(KeyPressEvent event) {
                if ((event.getKeyName().equals("Enter"))) {
                    event.getItem().blurItem();//ISSUE: needed to fire blur in order to take the values successfully
                    FilterActionExecuted();
                    event.getItem().focusInItem();
                }
            }
        };
        startRangeDate.addKeyPressHandler(onEnter);
        endRangeDate.addKeyPressHandler(onEnter);

        activateButton = new BaseButtonItem("activateButton", I18N.GET.BUTTON_TERMINAL_ACTIVATE_MONITORING_TITLE());

        checkPrivileges();
        checkActivationButton(recordEntity);

        setFields(new FormItem[]{startRangeDate, endRangeDate, searchText, filterButton, new SpacerItem(), activateButton});
    }

    private void checkPrivileges() {
//        activateButton.setDisabled(!Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_MONITORING_ACTIVATION));
//        activateButton.setDisabled(!Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_MONITORING_DEACTIVATION));
        activateButton.setDisabled(false);
        activateButton.setDisabled(false);
    }

    public BaseButtonItem getActivateButton() {
        return activateButton;
    }

    public boolean isTerminalBeingMonitored(Record recordEntity) {
        if (((EntityType) recordEntity.getAttributeAsObject("entityType")) == EntityType.TERMINAL) {
            boolean monitored = recordEntity.getAttributeAsRecord("entityRecord").getAttributeAsBoolean("monitored");
            Date startedMonitorAt = recordEntity.getAttributeAsRecord("entityRecord").getAttributeAsDate("startedMonitorAt");

            return monitored && startedMonitorAt != null && startedMonitorAt.getTime() <= startedMonitorAt.getTime() + (24 * 60 * 60 * 1000);
        } else {
            return false;
        }
    }

    public void checkActivationButton(Record recordEntity) {
        if (((EntityType) recordEntity.getAttributeAsObject("entityType")) == EntityType.TERMINAL) {
            if (isTerminalBeingMonitored(recordEntity)) {
                activateButton.setTitle(I18N.GET.BUTTON_TERMINAL_DEACTIVATE_MONITORING_TITLE());
            } else {
                activateButton.setTitle(I18N.GET.BUTTON_TERMINAL_ACTIVATE_MONITORING_TITLE());
            }
            activateButton.setVisible(true);
        } else {
            activateButton.setVisible(false);
        }
    }

   

}
