package com.smartbt.vtsuite.vtams.client.gui.window.filter;

import com.smartbt.vtsuite.vtams.client.gui.base.BaseFilterForm;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartgwt.client.widgets.form.fields.FormItem;

/**
 *
 * @author Sreekanth
 */


public class FeeManagementFilterForm extends BaseFilterForm {
    
    public FeeManagementFilterForm() {
        setFields(new FormItem[]{searchText, filterButton});
    }
    
}
