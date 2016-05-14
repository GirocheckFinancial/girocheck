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


import com.smartbt.vtsuite.vtams.client.classes.Settings;
import com.smartbt.vtsuite.vtams.client.classes.i18n.I18N;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseFilterForm;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomUserPrivileges;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.SpacerItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.form.fields.events.KeyPressEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyPressHandler;
import com.smartgwt.client.widgets.form.validator.DateRangeValidator;
import java.util.Date;

/**
 * The Transaction Filter Form
 *
 * @author Ariamnet Lopez
 */
public class WatchdogAlertFilterForm extends BaseFilterForm {

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
     * Constructor
     *
     */
    public WatchdogAlertFilterForm() {
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

        checkPrivileges();
        setFields(new FormItem[]{startRangeDate, endRangeDate, searchText, filterButton, new SpacerItem(), deleteButton, deleteAllButton});
    }

    private void checkPrivileges() {
//        deleteAllButton.setDisabled(!Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_WATCHDOG_ALERT_DELETE_ALL));
//        deleteButton.setDisabled(!Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_WATCHDOG_ALERT_DELETE));
        deleteAllButton.setDisabled(false);
        deleteButton.setDisabled(false);
    }
}
