package com.smartbt.vtsuite.vtams.client.gui.window.filter;

import com.google.gwt.user.client.Window;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseFilterForm;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartgwt.client.widgets.form.fields.FormItem;

/**
 *
 * @author Sreekanth
 */


public class FeeScheduleFilterForm extends BaseFilterForm {
    
    public FeeScheduleFilterForm(EntityType entityType) {
        super();
        //Window.alert("FeeScheduleFilterForm open");
        setFields(new FormItem[]{addButton, updateButton, deleteButton});
    }
    
}
