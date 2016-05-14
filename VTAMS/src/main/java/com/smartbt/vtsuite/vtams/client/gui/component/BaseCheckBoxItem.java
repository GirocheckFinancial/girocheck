
package com.smartbt.vtsuite.vtams.client.gui.component;

import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.form.fields.BooleanItem;

/**
 *
 * @author Alejo
 */


public class BaseCheckBoxItem extends BooleanItem{
    public BaseCheckBoxItem(String name, String title, boolean isRequired) {
        super(name, title);
        
        setVAlign(VerticalAlignment.BOTTOM);
        setStartRow(false);
        setEndRow(false);
        setRequired(isRequired);
    }
}
