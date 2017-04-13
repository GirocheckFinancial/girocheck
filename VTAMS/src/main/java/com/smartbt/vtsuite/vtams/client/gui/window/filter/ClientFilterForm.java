/*
 ** File: UserFilterForm.java
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

import com.smartbt.vtsuite.vtams.client.classes.i18n.I18N;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseFilterForm;
import com.smartbt.vtsuite.vtams.client.gui.component.BaseButtonItem;
import com.smartgwt.client.widgets.form.fields.FormItem;

/**
 * The User Filter Form
 *
 * @author Ariel Saavedra
 */
public class ClientFilterForm extends BaseFilterForm {

    private BaseButtonItem smsReceiveButton;

    /**
     * Constructor
     *
     * @param entityType
     */
    public ClientFilterForm() {
        super();
        smsReceiveButton = new BaseButtonItem("smsReceiveButton", I18N.GET.BUTTON_SMS_OPT_OUT_TITLE());
        smsReceiveButton.setDisabled(true);
        setFields(getFormFields());
    }

    public FormItem[] getFormFields() {
        return new FormItem[]{searchText, filterButton, smsReceiveButton};
    }

    /**
     * @return the smsReceiveButton
     */
    public BaseButtonItem getSmsReceiveButton() {
        return smsReceiveButton;
    }

    /**
     * @param smsReceiveButton the smsReceiveButton to set
     */
    public void setSmsReceiveButton(BaseButtonItem smsReceiveButton) {
        this.smsReceiveButton = smsReceiveButton;
    }
}
