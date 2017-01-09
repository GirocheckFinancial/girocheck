package com.smartbt.vtsuite.vtams.client.gui.window.editor;

import com.smartbt.vtsuite.vtams.client.gui.base.BaseEditorWindow;
import com.smartbt.vtsuite.vtams.client.gui.component.BaseTextItem;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.FeeBucketDS;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.util.SC;

/**
 *
 * @author Sreekanth
 */


public class FeeBucketsEditor extends BaseEditorWindow {
 
    private BaseTextItem merchant;
    private BaseTextItem minimum;
    private BaseTextItem percentage;
    private BaseTextItem fixed;
    private EntityType entityType;
    private static final int COMPONENTS_WIDTH = 175;
    
    public FeeBucketsEditor(EntityType entityType, Record recordEntity) {
        super("Fee Buckets");       
         this.entityType = entityType;       
        minimum = new BaseTextItem("minimum", true);
        minimum.setWidth(COMPONENTS_WIDTH);
        percentage = new BaseTextItem("percentage", true);
        percentage.setWidth(COMPONENTS_WIDTH);
        fixed = new BaseTextItem("fixed", true);
        fixed.setWidth(COMPONENTS_WIDTH);       
        
        
        dataForm.setDataSource(new FeeBucketDS()); 
        dataForm.setFields(minimum, percentage,fixed);
    }
    
    public Record getRecord() {
        
        // SC.warn("came here getRecord**************" );
        
        Record feeBucketsRecord = new Record();
        feeBucketsRecord.setAttribute("id", dataForm.getValuesAsRecord().getAttributeAsInt("id"));
        String min = minimum.getValueAsString();
        float minimum = Float.parseFloat(min);
        feeBucketsRecord.setAttribute("minimum", minimum);
        
        String percentageStr = percentage.getValueAsString();
        float percentage = Float.parseFloat(percentageStr);
        feeBucketsRecord.setAttribute("percentage", percentage);
        
        String fixedStr = fixed.getValueAsString();
        float fixed = Float.parseFloat(fixedStr);
        feeBucketsRecord.setAttribute("fixed", fixed);   
       
        //SC.warn("got getRecord**************" );
        return feeBucketsRecord;
    }
    
    @Override
    public void addRecord(Record record) {
       // Window.alert("Came here addRecord");
        minimum.setDisabled(false);
        percentage.setDisabled(false);
        fixed.setDisabled(false);
        super.addRecord(record);
    }
    
    @Override
    public void updateRecord(Record record) {
        minimum.setDisabled(false);
        percentage.setDisabled(false);
        fixed.setDisabled(false); 
        super.updateRecord(record);
    }
}
