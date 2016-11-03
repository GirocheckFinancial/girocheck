/*
 ** File: UserTab.java
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
package com.smartbt.vtsuite.vtams.client.gui.window.tab;

import com.smartbt.vtsuite.vtams.client.classes.Properties;
import com.smartbt.vtsuite.vtams.client.classes.Settings;
import com.smartbt.vtsuite.vtams.client.classes.i18n.I18N;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseDatasource;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseInterface;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseTab;
import com.smartbt.vtsuite.vtams.client.gui.listener.EditorListener;
import com.smartbt.vtsuite.vtams.client.gui.listener.FilterListenerImp;
import com.smartbt.vtsuite.vtams.client.gui.listener.ListListener;
import com.smartbt.vtsuite.vtams.client.gui.listener.PaginationListener;
import com.smartbt.vtsuite.vtams.client.gui.window.editor.ChangePasswordEditor;
import com.smartbt.vtsuite.vtams.client.gui.window.editor.UserEditor;
import com.smartbt.vtsuite.vtams.client.gui.window.filter.UserFilterForm;
import com.smartbt.vtsuite.vtams.client.gui.window.list.UserListGrid;
import com.smartbt.vtsuite.vtams.client.utils.Utils;
import com.smartbt.vtsuite.vtcommon.Constants;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomUserPrivileges;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.tab.events.TabSelectedEvent;
import com.smartgwt.client.widgets.tab.events.TabSelectedHandler;

/**
 * The User Tab
 *
 * @author Ariamnet Lopez, Ariel Saavedra, Alejo
 */
public class UserTab extends BaseTab implements BaseInterface {

    private UserFilterForm filterForm;
    private UserListGrid listGrid;
    private UserEditor editorWindow;
    private ChangePasswordEditor passwordEditorWindow;
    private final int idEntity;
    private final EntityType entityType;
    private int isChangePassword = 0;

    /**
     * Constructor
     *
     * @param entityType the entity type. See
     * {@link com.smartbt.vtsuite.vtams.client.classes.EntityType EntityType}
     * @param recordEntity
     */
    public UserTab(final EntityType entityType, Record recordEntity) {
        super(I18N.GET.TAB_USERS_TITLE());
        this.idEntity = recordEntity == null ? -1 : recordEntity.getAttributeAsInt("id");
        this.entityType = entityType;

        filterForm = new UserFilterForm(entityType);
        listGrid = new UserListGrid(entityType);
        listGrid.setCanSort(Boolean.FALSE);
        editorWindow = new UserEditor(entityType, recordEntity);
        passwordEditorWindow = new ChangePasswordEditor(entityType, recordEntity);

        addTabSelectedHandler(new TabSelectedHandler() {
            /**
             * Method to execute when the tab is selected.
             *
             * @param event the event
             */
            public void onTabSelected(TabSelectedEvent event) {
                Filter();
            }
        });

        filterForm.addListener(new FilterListenerImp() {
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
                Update(listGrid.getSelectedRecord());
            }
            
            @Override
            public void DeleteActionExecuted() {
                Delete();
            }
            @Override
            public void ChangePasswordActionExecuted() {
                ChangePassWindow(listGrid.getSelectedRecord());
            }
        });

        paginationForm.addListener(new PaginationListener() {
            public void PreviousActionExecuted() {
                Filter();
            }

            public void NextActionExecuted() {
                Filter();
            }
        });

        listGrid.addListener(new ListListener() {
            /**
             * Method to execute when a Select event is fired.
             *
             */
            public void SelectActionExecuted(Record record) {

                        if (Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_ADMINISTRATION_USERS_UPDATE)) {
                            Update(record);
                        }

            }

            /**
             * Method to execute when a Selection Change event is fired.
             *
             */
            public void SelectionChangeActionExecuted(Record record) {

                        filterForm.getUpdateButton().setDisabled(!Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_ADMINISTRATION_USERS_UPDATE)
                                || record == null);
                        filterForm.getDeleteButton().setDisabled(!Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_ADMINISTRATION_USERS_UPDATE)||
                                    record == null);
                        filterForm.getChangePasswordButton().setDisabled(!Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_ADMINISTRATION_USERS_CHANGE_PASSWORD)||
                                    record == null);
//                            filterForm.getUpdateButton().setDisabled(record == null);
//                            filterForm.getDeleteButton().setDisabled(record == null);
            }

            /**
             * Method to execute when a Data Arrive event is fired.
             *
             */
            public void DataArrivedHandlerExecuted() {
                filterForm.getUpdateButton().setDisabled(true);
                filterForm.getDeleteButton().setDisabled(true);
                filterForm.getChangePasswordButton().setDisabled(true);
            }
        });

        editorWindow.addListener(new EditorListener() {
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
                editorWindow.hide();
            }
        });
        
        passwordEditorWindow.addListener(new EditorListener() {
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
                passwordEditorWindow.hide();
            }
        });

        filterLayout.addMember(filterForm);
        filterLayout.addMember(paginationForm);
        listLayout.addMember(listGrid);
    }

    /**
     * Filter method
     *
     */
    public void Filter() {
        Criteria formCriteria = paginationForm.getLastLinkPressed() == null ? filterForm.getValuesAsCriteria() : paginationForm.getCriteria();
        paginationForm.setCriteria(formCriteria);

        formCriteria.addCriteria("idEntity", idEntity);
        formCriteria.addCriteria("entityType", entityType.toString());

        formCriteria.addCriteria("pageNumber", paginationForm.getRequestPageNumber());
        formCriteria.addCriteria("rowsPerPage", paginationForm.getRowsPerPage());

        filterForm.setDisabled(true);

        listGrid.invalidateCache();
        listGrid.fetchData(formCriteria, new DSCallback() {
            public void execute(DSResponse response, Object rawData, DSRequest request) {
                paginationForm.updatePage(response.getAttributeAsInt("totalPages"));
                filterForm.setDisabled(false);
            }
        }, null);
    }

    /**
     * Add method
     *
     */
    public void Add() {
        Record record = new Record();
        record.setAttribute("idEntity", idEntity);
        record.setAttribute("entityType", entityType.toString());
        editorWindow.addRecord(record);
        editorWindow.show();
    }

    /**
     * Update method
     *
     * @param record the record to update
     */
    @Override
    public void Update(Record record) {   

//        Record recordToUpdate = listGrid.getSelectedRecord();
        editorWindow.updateRecord(record);
        editorWindow.show();
    }
    
    public void ChangePassWindow(Record record) {   

        isChangePassword = 1;
        record.setAttribute("idEntity", idEntity);
        record.setAttribute("entityType", entityType.toString());
       // passwordEditorWindow.addRecord(record);
        passwordEditorWindow.show();
    }
    
    

    /**
     * Delete method
     *
     */
    public void Delete() { 
        Record recordToDelete = listGrid.getSelectedRecord();
        
        BaseDatasource ds = new BaseDatasource();

        Criteria criteria = new Criteria();
        criteria.addCriteria( "id", recordToDelete.getAttributeAsInt("id"));
         
        ds.setFetchDataURL( Properties.DELETE_USER_WS );
        ds.fetchData( criteria, new DSCallback() {
          
            public void execute(DSResponse response, Object rawData, DSRequest request) {
                Filter();
            }
        });
      }

    /**
     * Save method
     *
     */
    public void Save() {
        
        if(isChangePassword == 1){
            ChangePassword();
            return;
        }
 

        Record recordToSave = editorWindow.getRecord();
        

//        String actionex = recordToSave.getAttribute("actionEx");
       String id = recordToSave.getAttribute("id");
     
        if (id == null) {
            editorWindow.getDataForm().getDataSource().addData(recordToSave, new DSCallback() {
               
                public void execute(DSResponse response, Object rawData, DSRequest request) {
                    if (response.getStatus() == Constants.CODE_SUCCESS) {
                        Record record = response.getData()[0];
                        SC.say("Generated Password", record.getAttribute("password"));
                        Filter(); 
                       
                       editorWindow.hide();
                    } else {
                        SC.warn(I18N.GET.WINDOW_ERROR_TITLE(), I18N.GET.MESSAGE_ERROR_ACTION());
                    }
                }
            });
        } else { 
            editorWindow.getDataForm().getDataSource().updateData(recordToSave, new DSCallback() {
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
                    } else {
                        SC.warn(I18N.GET.WINDOW_ERROR_TITLE(), I18N.GET.MESSAGE_ERROR_ACTION());
                    }
                }
            });
        }
    }
    
    
    public void ChangePassword() {
        
        Utils.debug("Entered ChangePassword() with a changePassw button");
        
        Record recordTochange = listGrid.getSelectedRecord();
        Record passwordRecord = passwordEditorWindow.getRecord();
        
        if(!passwordRecord.getAttribute("password").equals(passwordRecord.getAttribute("checkpassword"))){          
            ChangePassWindow(listGrid.getSelectedRecord());
            return;
        }
        
        BaseDatasource ds = new BaseDatasource();

        Criteria criteria = new Criteria();
        criteria.addCriteria( "userId", recordTochange.getAttributeAsInt("id"));
        criteria.addCriteria( "password", passwordRecord.getAttribute("password"));
        
        ds.setFetchDataURL( Properties.CHANGE_PASSWORD_WS );
        ds.fetchData( criteria, new DSCallback() {
             
            public void execute(DSResponse response, Object rawData, DSRequest request) {
                Filter();
                passwordEditorWindow.hide();
            }
        });
        Utils.debug("Entered userTab.Delete() with user id: " + recordTochange.getAttributeAsInt("id")+ "Done <*>");
        isChangePassword = 0;
    }

}
