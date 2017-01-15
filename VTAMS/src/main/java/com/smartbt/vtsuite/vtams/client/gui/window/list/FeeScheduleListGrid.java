 
package com.smartbt.vtsuite.vtams.client.gui.window.list;

import com.smartbt.vtsuite.vtams.client.gui.base.BaseListGrid;
import com.smartbt.vtsuite.vtams.client.gui.component.TextListGridField;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.FeeSchedulesDS;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;

/**
 *
 * @author Sreekanth
 */


public class FeeScheduleListGrid extends BaseListGrid {
    
    private TextListGridField merchantField = new TextListGridField("merchant", "Merchant", false);
    private TextListGridField methodField = new TextListGridField("method", "Method", false);
    private TextListGridField isdefaultField = new TextListGridField("isdefault", "Default", false);
    
    public FeeScheduleListGrid(EntityType entityType) {
        super(); 
        setHeight(200);
        setWidth("1000");
        setEmptyMessage("No Fee Schedules to show");
        methodField.setDataPath("method/description");
        
        merchantField.setDataPath("merchantName");
        
        setDataSource(new FeeSchedulesDS(entityType));
        
        setFields(merchantField, methodField, isdefaultField);
        
    }
}
