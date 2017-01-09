 
package com.smartbt.vtsuite.vtams.client.gui.window;

import com.smartbt.vtsuite.vtams.client.classes.Properties;
import com.smartbt.vtsuite.vtams.client.classes.i18n.I18N;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseDatasource;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseInterface;
import com.smartbt.vtsuite.vtams.client.gui.component.PaginationForm;
import com.smartbt.vtsuite.vtams.client.gui.listener.EditorListener;
import com.smartbt.vtsuite.vtams.client.gui.listener.FilterListenerImp;
import com.smartbt.vtsuite.vtams.client.gui.listener.ListListener;
import com.smartbt.vtsuite.vtams.client.gui.listener.PaginationListener;
import com.smartbt.vtsuite.vtams.client.gui.window.editor.FeeBucketsEditor;
import com.smartbt.vtsuite.vtams.client.gui.window.editor.FeeScheduleEditor;
import com.smartbt.vtsuite.vtams.client.gui.window.filter.FeeBucketsFilterForm;
import com.smartbt.vtsuite.vtams.client.gui.window.filter.FeeManagementFilterForm;
import com.smartbt.vtsuite.vtams.client.gui.window.filter.FeeScheduleFilterForm;
import com.smartbt.vtsuite.vtams.client.gui.window.list.FeeBucketsListGrid;
import com.smartbt.vtsuite.vtams.client.gui.window.list.FeeScheduleListGrid;
import com.smartbt.vtsuite.vtcommon.Constants;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 *
 * @author Sreekanth
 */


public class FeeManagementGridPanel extends VLayout implements BaseInterface {
    
    protected HLayout filterFeeScheduleLayout;
    
    protected HLayout filterFeeBucketLayout;
    
    protected PaginationForm paginationFeeScheduleForm; 
    
    protected PaginationForm paginationFeeBucketForm;
  
    private FeeScheduleEditor editorFeeScheduleWindow;
    private FeeBucketsEditor editorFeeBucketsWindow;
    
    private FeeManagementFilterForm filterForm;
    private FeeScheduleListGrid listFeeScheuleGrid; 
    private FeeScheduleFilterForm feeScheduleFilterForm;
    private FeeBucketsFilterForm feeBucketFilterForm;
    private FeeBucketsListGrid listFeeBucketsGrid;  
    
    private final int idEntity;
    private final EntityType entityType;
    
    private Record feeScheduleRecord;
    
    
  
    
    public FeeManagementGridPanel(final EntityType entityType, Record recordEntity) {
        
        this.idEntity = recordEntity == null ? -1 : recordEntity.getAttributeAsInt("id");
        this.entityType = entityType;
        
        paginationFeeScheduleForm = new PaginationForm();
        paginationFeeScheduleForm.setMargin(10);
        
        paginationFeeBucketForm = new PaginationForm();
        paginationFeeBucketForm.setMargin(10);
        
        filterFeeScheduleLayout = new HLayout();
        filterFeeScheduleLayout.setWidth(300);        
        filterFeeScheduleLayout.setAutoHeight();
        
        filterForm = new FeeManagementFilterForm();
        listFeeScheuleGrid = new FeeScheduleListGrid(entityType);
        feeScheduleFilterForm = new FeeScheduleFilterForm(entityType);
        feeBucketFilterForm = new FeeBucketsFilterForm(entityType);
        
        filterFeeScheduleLayout.addMember(feeScheduleFilterForm);        
        filterFeeScheduleLayout.addMember(paginationFeeScheduleForm);
        
        filterFeeBucketLayout = new HLayout();
        filterFeeBucketLayout.setWidth(300); 
        filterFeeBucketLayout.setAutoHeight();
        
        filterFeeBucketLayout.addMember(feeBucketFilterForm);        
        filterFeeBucketLayout.addMember(paginationFeeBucketForm);
        
        
        listFeeBucketsGrid = new FeeBucketsListGrid(entityType,null);
        listFeeBucketsGrid.setCanSort(Boolean.FALSE);
        listFeeScheuleGrid.setCanSort(Boolean.FALSE);
                                            
        listFeeBucketsGrid.hide();
        filterFeeBucketLayout.hide();
        
        
        editorFeeScheduleWindow = new FeeScheduleEditor(entityType, recordEntity);
        editorFeeBucketsWindow = new FeeBucketsEditor(entityType,recordEntity);
        
        //addMember(filterForm);        
        addMember(listFeeScheuleGrid);        
        addMember(filterFeeScheduleLayout);
        
        addMember(listFeeBucketsGrid);        
        addMember(filterFeeBucketLayout); 
                
       
        
        feeScheduleFilterForm.addListener(new FilterListenerImp() {
            @Override
            public void FilterActionExecuted() {
                Filter();
            }
            
            @Override
            public void AddActionExecuted() {
                Add();
            }

            @Override
            public void UpdateActionExecuted() {
                Update(listFeeScheuleGrid.getSelectedRecord());
            }
            
            @Override
            public void DeleteActionExecuted() {
                Delete();
            }
           
        });
        
        paginationFeeScheduleForm.addListener(new PaginationListener() {
            public void PreviousActionExecuted() {
                Filter();
            }

            public void NextActionExecuted() {
                Filter();
            }
        });
        
        paginationFeeBucketForm.addListener(new PaginationListener() {
            public void PreviousActionExecuted() {
              Filter(feeScheduleRecord);
            }

            public void NextActionExecuted() {
                Filter(feeScheduleRecord);
            }
        });
        
        listFeeScheuleGrid.addListener(new ListListener() {
            /**
             * Method to execute when a Select event is fired.
             *
             */
            public void SelectActionExecuted(Record record) {

                        //if (Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_ADMINISTRATION_USERS_UPDATE)) {
                
                             Update(record);
                            
                       // }

            }

            /**
             * Method to execute when a Selection Change event is fired.
             *
             */
            public void SelectionChangeActionExecuted(Record record) {

                        feeScheduleFilterForm.getUpdateButton().setDisabled(record == null);
                        feeScheduleFilterForm.getDeleteButton().setDisabled(record == null);
                        listFeeBucketsGrid.setTitle(record.getAttribute("merchantName"));
                        listFeeBucketsGrid.show();
                        
                        filterFeeBucketLayout.show();
                        feeScheduleRecord =record; 
                        Filter(feeScheduleRecord);
                        
                        
                       
            }

            /**
             * Method to execute when a Data Arrive event is fired.
             *
             */
            public void DataArrivedHandlerExecuted() {
                feeScheduleFilterForm.getUpdateButton().setDisabled(true);
                feeScheduleFilterForm.getDeleteButton().setDisabled(true);                
            }
        });
        
        editorFeeScheduleWindow.addListener(new EditorListener() {
            /**
             * Method to execute when a Save event is fired.
             *
             */
            public void SaveActionExecuted() {                
                Save();
            }

            /**
             * Method to execute when a Close event is fired.
             *
             */
            public void CloseActionExecuted() {
                editorFeeScheduleWindow.hide();
            }
        });
        
        feeBucketFilterForm.addListener(new FilterListenerImp() {
            @Override
            public void FilterActionExecuted() {
                Filter(feeScheduleRecord);
            }
            
            @Override
            public void AddActionExecuted() {
                AddFeeBuckets();
            }

            @Override
            public void UpdateActionExecuted() {
                UpdateFeeBucket(listFeeBucketsGrid.getSelectedRecord());
            }
            
            @Override
            public void DeleteActionExecuted() {
                DeleteFeeBucket();
            }
           
        });
        
        editorFeeBucketsWindow.addListener(new EditorListener() {
            /**
             * Method to execute when a Save event is fired.
             *
             */
            public void SaveActionExecuted() {                
                SaveFeeBuckets();
            }

            /**
             * Method to execute when a Close event is fired.
             *
             */
            public void CloseActionExecuted() {
                editorFeeBucketsWindow.hide();
            }
        });
        
        listFeeBucketsGrid.addListener(new ListListener() {
            /**
             * Method to execute when a Select event is fired.
             *
             */
            public void SelectActionExecuted(Record record) {

                        //if (Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_ADMINISTRATION_USERS_UPDATE)) {
                
                            UpdateFeeBucket(record);
                            
                       // }

            }

            /**
             * Method to execute when a Selection Change event is fired.
             *
             */
            public void SelectionChangeActionExecuted(Record record) {

                        feeBucketFilterForm.getUpdateButton().setDisabled(record == null);
                        feeBucketFilterForm.getDeleteButton().setDisabled(record == null);                        
                        
                        
                       
            }

            /**
             * Method to execute when a Data Arrive event is fired.
             *
             */
            public void DataArrivedHandlerExecuted() {
                feeBucketFilterForm.getUpdateButton().setDisabled(true);
                feeBucketFilterForm.getDeleteButton().setDisabled(true);                
            }
        });
        
        
        
    }

    @Override
    public void Filter() {
        Criteria formCriteria = paginationFeeScheduleForm.getLastLinkPressed() == null ? paginationFeeScheduleForm.getValuesAsCriteria() : paginationFeeScheduleForm.getCriteria();
        paginationFeeScheduleForm.setCriteria(formCriteria);

        formCriteria.addCriteria("idEntity", idEntity);
        formCriteria.addCriteria("entityType", entityType.toString());

        formCriteria.addCriteria("pageNumber", paginationFeeScheduleForm.getRequestPageNumber());
        formCriteria.addCriteria("rowsPerPage", paginationFeeScheduleForm.getRowsPerPage());
                
        feeScheduleFilterForm.setDisabled(true);

        listFeeScheuleGrid.invalidateCache();
        listFeeScheuleGrid.setData(new RecordList());//ISSUE (The call back is not called if the Criteria is the same)
        listFeeScheuleGrid.fetchData(formCriteria, new DSCallback() {
            public void execute(DSResponse response, Object rawData, DSRequest request) {
                paginationFeeScheduleForm.updatePage(response.getAttributeAsInt("totalPages"));
                feeScheduleFilterForm.setDisabled(false);
            }
        }, null);
    }
    
      
    public void Filter(Record record) {
        //SC.warn("FeeSchedule ID:- "+record.getAttributeAsInt("id")+" merchant:- "+record.getAttribute("merchant")+" default:-"+record.getAttributeAsBoolean("isdefault")+" method:- "+record.getAttribute("method"));
        Criteria formCriteria = paginationFeeBucketForm.getLastLinkPressed() == null ? paginationFeeBucketForm.getValuesAsCriteria() : paginationFeeBucketForm.getCriteria();
        paginationFeeScheduleForm.setCriteria(formCriteria);

        formCriteria.addCriteria("idEntity", idEntity);
        formCriteria.addCriteria("entityType", entityType.toString());

        formCriteria.addCriteria("feeScheduleId", record.getAttributeAsInt("id"));
        formCriteria.addCriteria("pageNumber", paginationFeeBucketForm.getRequestPageNumber());
        formCriteria.addCriteria("rowsPerPage", paginationFeeBucketForm.getRowsPerPage());
                
        feeBucketFilterForm.setDisabled(true);

        listFeeBucketsGrid.invalidateCache();
        
        
        listFeeBucketsGrid.setData(new RecordList());//ISSUE (The call back is not called if the Criteria is the same)
        listFeeBucketsGrid.fetchData(formCriteria, new DSCallback() {
            public void execute(DSResponse response, Object rawData, DSRequest request) {
                paginationFeeBucketForm.updatePage(response.getAttributeAsInt("totalPages"));
                feeBucketFilterForm.setDisabled(false);
            }
        }, null);
    }

    @Override
    public void Add() {
        Record record = new Record();
        record.setAttribute("idEntity", idEntity);
        record.setAttribute("entityType", entityType.toString());
        editorFeeScheduleWindow.addRecord(record);
        editorFeeScheduleWindow.show();         
        // editorFeeBucketsWindow.show();
    }

    @Override
    public void Update(Record record) {
       editorFeeScheduleWindow.updateRecord(record);
       editorFeeScheduleWindow.show();
    }
    
    public void UpdateFeeBucket(Record record) {
       editorFeeBucketsWindow.updateRecord(record);
       editorFeeBucketsWindow.show();
    }

    @Override
    public void Delete() { 
        Record recordToDelete = listFeeScheuleGrid.getSelectedRecord();
        
        BaseDatasource ds = new BaseDatasource();

        Criteria criteria = new Criteria();
        criteria.addCriteria( "id", recordToDelete.getAttributeAsInt("id"));
         
        ds.setFetchDataURL( Properties.DELETE_FEESCHEDULE_WS );
        ds.fetchData( criteria, new DSCallback() {
          
            public void execute(DSResponse response, Object rawData, DSRequest request) {
                Filter();
                Filter(feeScheduleRecord);
            }
        });
      }
    
     public void DeleteFeeBucket() { 
        Record recordToDelete = listFeeBucketsGrid.getSelectedRecord();
        
        BaseDatasource ds = new BaseDatasource();

        Criteria criteria = new Criteria();
        criteria.addCriteria( "id", recordToDelete.getAttributeAsInt("id"));
         
        ds.setFetchDataURL( Properties.DELETE_FEEBUCKETS_WS );
        ds.fetchData( criteria, new DSCallback() {
          
            public void execute(DSResponse response, Object rawData, DSRequest request) {
                Filter(feeScheduleRecord);            
            }
        });
      }

    @Override
    public void Save() {  

       Record recordToSave = editorFeeScheduleWindow.getRecord(); 


       String id = recordToSave.getAttribute("id");
        //SC.warn("came here" +id);
     
        if (id == null) {
            //SC.warn("came here");
            editorFeeScheduleWindow.getDataForm().getDataSource().addData(recordToSave, new DSCallback() {
               
                public void execute(DSResponse response, Object rawData, DSRequest request) {
                    if (response.getStatus() == Constants.CODE_SUCCESS) {                        
                        Filter();                       
                        editorFeeScheduleWindow.hide();
                    } else if (response.getStatus() == Constants.FEE_RECORD_EXISTS) {                        
                        SC.warn("Fee Schedule", "There is another fee schedule record for the the selected merchant with the same method.Please choose different merchant and try again.");
                    }else {                        
                        //SC.warn(I18N.GET.WINDOW_ERROR_TITLE(), I18N.GET.MESSAGE_ERROR_ACTION());
                         SC.warn("Fee Schedule", "There is another fee schedule record for the the selected merchant with the same method.Please choose different merchant and try again.");
                    }
                }
            });
        } else { 
            editorFeeScheduleWindow.getDataForm().getDataSource().updateData(recordToSave, new DSCallback() {
                /**
                 * Callback to invoke on completion
                 *
                 * @param response Response sent by the server in response to a
                 * DataSource request.
                 * @param rawData data
                 * @param request Request sent to the server to initiate a
                 * DataSource operation.
                 */
                public void execute(DSResponse response, Object rawData, DSRequest request) {
                    if (response.getStatus() == Constants.CODE_SUCCESS) {
                        Filter();
                    } else if (response.getStatus() == Constants.FEE_RECORD_EXISTS) {                        
                        SC.warn("Fee Schedule", "There is another fee schedule record for the the selected merchant with the same method.Please choose different merchant and try again.");
                    }else {
                        //SC.warn(I18N.GET.WINDOW_ERROR_TITLE(), I18N.GET.MESSAGE_ERROR_ACTION());
                        SC.warn("Fee Schedule", "There is another fee schedule record for the the selected merchant with the same method.Please choose different merchant and try again.");
                    }
                }
            });
        }
    }
    
    public void AddFeeBuckets() {
        Record record = new Record();
        record.setAttribute("idEntity", idEntity);
        record.setAttribute("entityType", entityType.toString());
        editorFeeBucketsWindow.addRecord(record);
        editorFeeBucketsWindow.show();         
        // editorFeeBucketsWindow.show();
    }
    
    public void SaveFeeBuckets() {  

      final Record recordToSave = editorFeeBucketsWindow.getRecord(); 
      
      if(recordToSave.getAttributeAsFloat("minimum")!=null && recordToSave.getAttributeAsFloat("minimum")<0){
          SC.warn("Value should be positive");
      }else if(recordToSave.getAttributeAsFloat("percentage")!=null && recordToSave.getAttributeAsFloat("percentage")<0){
          SC.warn("Value should be positive");
      }else if(recordToSave.getAttributeAsFloat("percentage")!=null && recordToSave.getAttributeAsFloat("percentage")<0){
          SC.warn("Value should be positive");
      }else if(recordToSave.getAttributeAsFloat("percentage")!=null && recordToSave.getAttributeAsFloat("percentage")>100){
          SC.warn("Percentage Value should not be greater than 100");
      }else{
          if(recordToSave.getAttributeAsFloat("percentage")!=null && recordToSave.getAttributeAsFloat("percentage")>20){            
            SC.confirm("Fee Buckets", "Are you sure about the value entered?", new BooleanCallback() {
                    public void execute(Boolean value) {
                        if (value == Boolean.TRUE) {
                            DoSave(recordToSave);
                        }
                    }
                });            
          }else if(recordToSave.getAttributeAsFloat("fixed")!=null && recordToSave.getAttributeAsFloat("fixed")>100){            
            SC.confirm("Fee Buckets", "Are you sure about the value entered?", new BooleanCallback() {
                    public void execute(Boolean value) {
                        if (value == Boolean.TRUE) {
                            DoSave(recordToSave);
                        }
                    }
                });            
          }else{
              DoSave(recordToSave);
          }
          
      }
}
    
    
public void DoSave(Record recordToSave){
        Record feeSchedule = new Record();
         feeSchedule.setAttribute("id", feeScheduleRecord.getAttributeAsInt("id"));              
             
         recordToSave.setAttribute("feeSchedule", feeSchedule);
       
         String id = recordToSave.getAttribute("id");      
     
        if (id == null) {           
            editorFeeBucketsWindow.getDataForm().getDataSource().addData(recordToSave, new DSCallback() {
               
                public void execute(DSResponse response, Object rawData, DSRequest request) {
                    // SC.warn("came here for executing");
                    if (response.getStatus() == Constants.CODE_SUCCESS) {                        
                        Filter(feeScheduleRecord);                       
                        editorFeeBucketsWindow.hide();
                    } else {
                        SC.warn(I18N.GET.WINDOW_ERROR_TITLE(), I18N.GET.MESSAGE_ERROR_ACTION());
                    }
                }
            });
        } else {            
            editorFeeBucketsWindow.getDataForm().getDataSource().updateData(recordToSave, new DSCallback() {
                /**
                 * Callback to invoke on completion
                 *
                 * @param response Response sent by the server in response to a
                 * DataSource request.
                 * @param rawData data
                 * @param request Request sent to the server to initiate a
                 * DataSource operation.
                 */
                public void execute(DSResponse response, Object rawData, DSRequest request) {
                    
                    if (response.getStatus() == Constants.CODE_SUCCESS) {
                         Filter(feeScheduleRecord);
                    } else {
                        SC.warn(I18N.GET.WINDOW_ERROR_TITLE(), I18N.GET.MESSAGE_ERROR_ACTION());
                    }
                }
            });
        }
      }     
      
   
}
