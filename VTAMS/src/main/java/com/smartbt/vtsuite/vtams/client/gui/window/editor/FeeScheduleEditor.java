package com.smartbt.vtsuite.vtams.client.gui.window.editor;

import com.smartbt.vtsuite.vtams.client.gui.base.BaseEditorWindow;
import com.smartbt.vtsuite.vtams.client.gui.component.BaseCheckBoxItem;
import com.smartbt.vtsuite.vtams.client.gui.component.BaseSelectItem;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.FeeMerchantDS;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.FeeSchedulesDS;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.TranasctionMethodDS;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 *
 * @author Sreekanth
 */


public class FeeScheduleEditor extends BaseEditorWindow {
 
    private BaseSelectItem merchantSelect;
    private BaseSelectItem methodSelect; 
    private BaseCheckBoxItem isdefaultCheck;
    private EntityType entityType;
    private static final int COMPONENTS_WIDTH = 175;
    
    public FeeScheduleEditor(EntityType entityType, Record recordEntity) {
        super("FeeSchedule");       
        //Window.alert("Came here FeeScheduleEditor");
        this.entityType = entityType;
        
        merchantSelect = new BaseSelectItem("merchant", "Merchant", new FeeMerchantDS(), false); 
        merchantSelect.setAllowEmptyValue(true);
        //merchantSelect.setValueField("Default");       
        merchantSelect.setDataPath("merchant/id");
        merchantSelect.setDisplayField("legalName");
        merchantSelect.setEmptyDisplayValue("Default");        
        merchantSelect.setWidth(COMPONENTS_WIDTH);
        
        //Window.alert("Came here FeeScheduleEditor 1");
        
        methodSelect = new BaseSelectItem("method", "Method", new TranasctionMethodDS(), true);
        methodSelect.setDataPath("method/id");
        methodSelect.setDisplayField("description");     
        methodSelect.setEmptyDisplayValue("[No methods to show]");
        methodSelect.setWidth(COMPONENTS_WIDTH);
        isdefaultCheck = new BaseCheckBoxItem("isdefault","Default",false);
        
        dataForm.setDataSource(new FeeSchedulesDS(entityType)); 
        dataForm.setFields(merchantSelect, methodSelect, isdefaultCheck);
    }
    
    
    @Override
    public void addRecord(Record record) {
       // Window.alert("Came here addRecord");
        merchantSelect.setDisabled(false);
        methodSelect.setDisabled(false);
        isdefaultCheck.setDisabled(false); 
        

        Criteria criteria = new Criteria();
        criteria.addCriteria("entityType", EntityType.AMS.toString());
 
        merchantSelect.setPickListCriteria(criteria);
        merchantSelect.fetchData(); 
        //Window.alert("got Merchant info"+merchantSelect.getPickListCriteria().getAttributes());
 
        super.addRecord(record);
    }
    
    public Record getRecord() {
        
        Record feeScheduleRecord = new Record();
        feeScheduleRecord.setAttribute("id", dataForm.getValuesAsRecord().getAttributeAsInt("id"));
        feeScheduleRecord.setAttribute("isdefault", isdefaultCheck.getValueAsBoolean());        
        
        
        ListGridRecord merchantRecord = merchantSelect.getSelectedRecord();
   
        Integer idMerchant = merchantRecord.getAttributeAsInt("id");          
    
        
       
        ListGridRecord methodRecord = methodSelect.getSelectedRecord();
   
        Integer idMethod = methodRecord.getAttributeAsInt("id");
        String operation = methodRecord.getAttributeAsString("operation"); 
        String description = methodRecord.getAttributeAsString("description"); 
    
        Record method = new Record();

        method.setAttribute("id", idMethod);
        method.setAttribute("operation", operation);
        method.setAttribute("description", description); 
        
        feeScheduleRecord.setAttribute("merchant", idMerchant);
        
        feeScheduleRecord.setAttribute("method", method);

        return feeScheduleRecord;
    }
    
    
    @Override
    public void updateRecord(Record record) {
        merchantSelect.setDisabled(false);
        methodSelect.setDisabled(false);
        isdefaultCheck.setDisabled(false);      

        Criteria criteria = new Criteria();
 
        criteria.addCriteria("entityType", EntityType.AMS.toString());
 
        merchantSelect.setPickListCriteria(criteria);
        merchantSelect.fetchData();
        
        //merchantSelect.setDataPath(record.getAttribute("id"));
      //  merchantSelect.setDisplayField(record.getAttribute("legalName"));
 
        super.updateRecord(record);
    }
}
