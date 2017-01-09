package com.smartbt.vtsuite.vtams.client.gui.window.list;

import com.smartbt.vtsuite.vtams.client.gui.base.BaseListGrid;
import com.smartbt.vtsuite.vtams.client.gui.component.TextListGridField;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.FeeBucketDS;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartgwt.client.data.Record;

/**
 *
 * @author Sreekanth
 */


public class FeeBucketsListGrid extends BaseListGrid {
    
    private TextListGridField minimumField = new TextListGridField("minimum", "Minimum", false);
    private TextListGridField percentageField = new TextListGridField("percentage", "Percentage", false);
    private TextListGridField fixedField = new TextListGridField("fixed", "Fixed", false);
    
    private final int idEntity;
    private final EntityType entityType;
    
    public FeeBucketsListGrid(final EntityType entityType, Record recordEntity) {
        super();
        this.idEntity = recordEntity == null ? -1 : recordEntity.getAttributeAsInt("id");
        this.entityType = entityType;
        setHeight(200);
        setWidth("1000");
        setEmptyMessage("No Fee Buckets to show");
        
        setDataSource(new FeeBucketDS());
        
        setFields(minimumField, percentageField, fixedField);
        
    }
    
}
