/*
 ** File: UserEditor.java
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
package com.smartbt.vtsuite.vtams.client.gui.window.editor;

import com.smartbt.vtsuite.vtams.client.classes.i18n.I18N;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseDatasource;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseEditorWindow;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseTreeNode;
import com.smartbt.vtsuite.vtams.client.gui.component.BasePasswordItem;
import com.smartbt.vtsuite.vtams.client.gui.component.BaseSelectItem;
import com.smartbt.vtsuite.vtams.client.gui.component.BaseTextItem;
import com.smartbt.vtsuite.vtams.client.gui.component.SearchTreeGrid;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.DataSourceBuilder;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.MerchantDS;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.RoleDS;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.TerminalDS;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.UserDS;
import com.smartbt.vtsuite.vtams.client.gui.component.treenode.CustomerTreeNode;
import com.smartbt.vtsuite.vtams.client.utils.Utils;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import static com.smartbt.vtsuite.vtcommon.enums.EntityType.AMS;
import static com.smartbt.vtsuite.vtcommon.enums.EntityType.CUSTOMER;
import static com.smartbt.vtsuite.vtcommon.enums.EntityType.MERCHANT;
import static com.smartbt.vtsuite.vtcommon.enums.EntityType.TERMINAL;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.form.fields.CanvasItem;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.tree.events.FolderOpenedEvent;
import com.smartgwt.client.widgets.tree.events.FolderOpenedHandler;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * The User Editor Window
 *
 * @author Ariamnet Lopez, Ariel Saavedra
 */
public class UserEditor extends BaseEditorWindow {

    private BaseTextItem usernameText;
    private BaseTextItem lastNameText;
    private BaseTextItem firstNameText;
    private BasePasswordItem passwordText;
    private BaseTextItem emailText;
    private BaseSelectItem activeSelect;
    private BaseSelectItem roleSelect;
    private int actionEx;
    private Record recAux;
//    private BaseSelectItem levelSelect;
//    private SearchTreeGrid levelTree;
//    private CanvasItem containerLevelTree;
    private EntityType entityType;
    private static final int COMPONENTS_WIDTH = 175;

    /**
     * Constructor
     *
     * @param entityType
     * {@link com.smartbt.vtsuite.vtams.client.classes.EntityType EntityType}
     * @param recordEntity
     */
    public UserEditor(EntityType entityType, Record recordEntity) {
        
        super(I18N.GET.WINDOW_USER_TITLE());
        this.entityType = entityType;
        usernameText = new BaseTextItem("username", true);
        usernameText.setWidth(COMPONENTS_WIDTH);
        lastNameText = new BaseTextItem("lastName", true);
        lastNameText.setWidth(COMPONENTS_WIDTH);
        firstNameText = new BaseTextItem("firstName", true);
        firstNameText.setWidth(COMPONENTS_WIDTH);
        passwordText = new BasePasswordItem("password", true);
        passwordText.setWidth(COMPONENTS_WIDTH);
        
        activeSelect = new BaseSelectItem( "active", "Active", DataSourceBuilder.getUserActiveDS(), false );
//        activeSelect.setTextAlign( Alignment.LEFT );
        activeSelect.setWidth(COMPONENTS_WIDTH);
        
        emailText = new BaseTextItem("email", false);
        emailText.setWidth(COMPONENTS_WIDTH);
        
        roleSelect = new BaseSelectItem("role", I18N.GET.LABEL_ROLE_TITLE(), new RoleDS(), true);
        roleSelect.setDataPath("role/id");
        roleSelect.setEmptyDisplayValue(I18N.GET.MESSAGE_EMPTY_ROLE_SELECT());
        roleSelect.setWidth(COMPONENTS_WIDTH);
        
        recAux = recordEntity;
        dataForm.setDataSource(new UserDS(entityType));
//        switch (entityType) {
//            case CUSTOMER:
//            case MERCHANT:
//            case TERMINAL:
//                levelSelect = new BaseSelectItem("level", "Level", new BaseDatasource(new Record()), true);
//                levelSelect.setWidth(COMPONENTS_WIDTH);
//                levelSelect.setDefaultToFirstOption(true);
//                levelSelect.setCanEdit(false);
//                levelSelect.setShowDisabled(false);
//
//                levelSelect.addClickHandler(new ClickHandler() {
//
//                    public void onClick(ClickEvent event) {
//                        if (containerLevelTree.isVisible()) {
//                            containerLevelTree.hide();
//                        } else {
//                            containerLevelTree.show();
//                        }
//                    }
//                });
//
//                containerLevelTree = new CanvasItem("levelPicker", null);
//                containerLevelTree.setVisible(false);
//                levelTree = new SearchTreeGrid(true);
//                levelTree.setSelectionType(SelectionStyle.SINGLE);
//                levelTree.setWidth(COMPONENTS_WIDTH);
//                levelTree.setHeight(200);
//
//                Record rootRecord = recordEntity;
//                while (((EntityType)rootRecord.getAttributeAsObject("entityType"))!= EntityType.CUSTOMER) {
//                    rootRecord = rootRecord.getAttributeAsRecord("parentRecord");
//                }
//
//                BaseTreeNode parentNode = new CustomerTreeNode();
//                parentNode.setName(rootRecord.getAttributeAsString("name"));
//                parentNode.setId(rootRecord.getAttributeAsInt("id"));
//                levelTree.getSearchTree().add(parentNode, levelTree.getRootNode());
//
//                levelTree.addRecordClickHandler(new RecordClickHandler() {
//                    public void onRecordClick(RecordClickEvent event) {
//                        selectActionExecuted(event.getRecord());
//                    }
//                });
//
//                levelTree.addFolderOpenedHandler(new FolderOpenedHandler() {
//                    public void onFolderOpened(FolderOpenedEvent event) {
//                        openFolderActionExecuted((BaseTreeNode) event.getNode());
//                    }
//                });
//                containerLevelTree.setCanvas(levelTree);
//
//                dataForm.setFields(usernameText, lastNameText, firstNameText, roleSelect, levelSelect, containerLevelTree);
//                break;
//            case AMS:
                dataForm.setFields(usernameText, lastNameText, firstNameText, passwordText, activeSelect, emailText, roleSelect);
//                break;
//
//        }
    }

    /**
     * Prepare to update the record by setting to the editor the values of the
     * record passed in.
     *
     * @param record the User record
     */
    @Override
    public void updateRecord(Record record) {
        usernameText.setDisabled(false);
        firstNameText.setDisabled(false);
        lastNameText.setDisabled(false);
        passwordText.setDisabled(true);
        activeSelect.setDisabled(false);
        emailText.setDisabled(false);      

        Utils.debug("Entered userEditor.updateRecord() show user active: " + record.getAttributeAsBoolean("active"));
        
//        Criteria criteria1 = new Criteria();
//        if(record.getAttributeAsBoolean("active")){
//            criteria1.addCriteria("id", "1");
//        }else{
//            criteria1.addCriteria("id", "2");
//        }    
//        
//        activeSelect.setPickListCriteria(criteria1);
        activeSelect.fetchData();
        
//        activeSelect.setAttribute("active", record.getAttributeAsBoolean("active") ? 1 : 2);
        
        
        
        Criteria criteria = new Criteria();
//        switch (entityType) {
//            case AMS: {
                criteria.addCriteria("entityType", EntityType.AMS.toString());
//                break;
//            }
//            case CUSTOMER:
//            case MERCHANT:
//            case TERMINAL: {
//                criteria.addCriteria("entityType", EntityType.CLERK.toString());
//
//                Record entityRecord;
//                if (record.getAttributeAsRecord("customer").getAttribute("id") != null) {
//                    entityRecord = record.getAttributeAsRecord("customer");
//                    updateLevel(entityRecord.getAttributeAsInt("id"), EntityType.CUSTOMER.toString(), entityRecord.getAttribute("name"));
//                } else if (record.getAttributeAsRecord("merchant").getAttribute("id") != null) {
//                    entityRecord = record.getAttributeAsRecord("merchant");
//                    updateLevel(entityRecord.getAttributeAsInt("id"), EntityType.MERCHANT.toString(), entityRecord.getAttribute("name"));
//                } else if (record.getAttributeAsRecord("terminal").getAttribute("terminalId") != null) {
//                    entityRecord = record.getAttributeAsRecord("terminal");
//                    updateLevel(entityRecord.getAttributeAsInt("id"), EntityType.TERMINAL.toString(), entityRecord.getAttribute("terminalId"));
//                }
//
//                dataForm.rememberValues();
//                break;
//            }
//        }
        roleSelect.setPickListCriteria(criteria);
        roleSelect.fetchData();
//        Utils.debug("Entered userEditor.updateRecord() show activeSelect.getValue().toString():>>>>> " + activeSelect.getValue());
//        record.setAttribute("actionEx", "upd");
        actionEx = 1;
        
//        if (activeSelect.getValue().equals(1)||activeSelect.getValue().equals("Active")) {
//            record.setAttribute("active", true);
//        } else if(activeSelect.getValue().equals(2) || activeSelect.getValue().equals("Inactive")){
//            record.setAttribute("active", false);
//        }
        
//        if (activeSelect.getValue().equals(1)) {
//            record.setAttribute("active", true);
//        } else {
//            record.setAttribute("active", false);
//        }
        super.updateRecord(record);
    }
    
    @Override
    public void addRecord(Record record) {
        usernameText.setDisabled(false);
        firstNameText.setDisabled(false);
        lastNameText.setDisabled(false);
        passwordText.setDisabled(false);
        activeSelect.setDisabled(false);
        emailText.setDisabled(false);      

        Criteria criteria = new Criteria();
        criteria.addCriteria("entityType", EntityType.AMS.toString());

//        if (activeSelect.getValue().equals(1)||activeSelect.getValue().equals("Active")) {
//            record.setAttribute("active", true);
//        } else if(activeSelect.getValue().equals(2) || activeSelect.getValue().equals("Inactive")){
//            record.setAttribute("active", false);
//        }
        
        roleSelect.setPickListCriteria(criteria);
        roleSelect.fetchData();
//        record.setAttribute("actionEx", "add");
        actionEx = 0;
        
//        if (activeSelect.getValue()) {
//            record.setAttribute("active", true);
//        } else {
//            record.setAttribute("active", false);
//        }
        super.addRecord(record);
    }

//    private void updateLevel(int idEntityType, String entityType, String nameEntity) {
//        ListGridRecord levelRecord = new ListGridRecord();
//        levelRecord.setAttribute("id", idEntityType);
//        levelRecord.setAttribute("name", entityType + " (" + nameEntity + ")");
//        levelRecord.setAttribute("entityType", entityType);
//        levelSelect.setOptionDataSource(new BaseDatasource(levelRecord));
//    }

    /**
     * Method to execute when a Select event is fired.
     *
     * @param record
     */
//    public void selectActionExecuted(final Record record) {
//        updateLevel(record.getAttributeAsInt("id"), record.getAttributeAsString("entityType"), record.getAttribute("name"));
//        containerLevelTree.hide();
//    }

//    /**
//     * Method to execute when an OpenFolder event is fired.
//     *
//     * @param node
//     */
//    public void openFolderActionExecuted(final BaseTreeNode node) {
//        MerchantDS merchantDS = new MerchantDS();
//        TerminalDS terminalDS = new TerminalDS();
//        // If the node doesn't have children yet               
//        if (levelTree.getSearchTree().getChildren(node).length == 0) {
//            Criteria criteria = new Criteria();
//
//            switch (node.getEntityType()) {
//                case CUSTOMER: {
//                    criteria.addCriteria("idCustomer", node.getAttributeAsString("id"));
//
//                    merchantDS.fetchData(criteria, new DSCallback() {
//                        /**
//                         * Callback to invoke on completion
//                         *
//                         * @param response Response sent by the server in
//                         * response to a DataSource request.
//                         * @param rawData data
//                         * @param request Request sent to the server to initiate
//                         * a DataSource operation.
//                         */
//                        public void execute(DSResponse response, Object rawData, DSRequest request) {
//                            Record[] searchRecords = response.getData();
//                            levelTree.addNodes(node, EntityType.MERCHANT, searchRecords);
//                        }
//                    });
//                    break;
//                }
//                case MERCHANT: {
//                    criteria.addCriteria("idMerchant", node.getAttributeAsString("id"));
//
//                    terminalDS.fetchData(criteria, new DSCallback() {
//                        /**
//                         * Callback to invoke on completion
//                         *
//                         * @param response Response sent by the server in
//                         * response to a DataSource request.
//                         * @param rawData data
//                         * @param request Request sent to the server to initiate
//                         * a DataSource operation.
//                         */
//                        public void execute(DSResponse response, Object rawData, DSRequest request) {
//                            Record[] searchRecords = response.getData();
//                            levelTree.addNodes(node, EntityType.TERMINAL, searchRecords);
//                        }
//                    });
//                    break;
//                }
//                default: {
//                    break;
//                }
//            }
//        }
//    }

    public Record getRecord() {

        //Loading data
        Record userRecord = new Record();
//        Utils.debug("Entered userEditor.getRecord() with user id: " + dataForm.getValuesAsRecord().getAttributeAsInt("id"));
        userRecord.setAttribute("id", dataForm.getValuesAsRecord().getAttributeAsInt("id"));
        userRecord.setAttribute("username", usernameText.getValueAsString());
        userRecord.setAttribute("lastName", lastNameText.getValueAsString());
        userRecord.setAttribute("firstName", firstNameText.getValueAsString());
        userRecord.setAttribute("password", passwordText.getValueAsString());
        Utils.debug("Entered userEditor.getRecord() active attribute = : " + activeSelect.getValue());

        userRecord.setAttribute("active", activeSelect.getValue());

//        userRecord.setAttribute("active", activeSelect.getSelectedRecord());
        userRecord.setAttribute("email", emailText.getValueAsString());
        userRecord.setAttribute("role", roleSelect.getSelectedRecord());
        
        String q;
        if(actionEx == 0){
            q = "add";
        }else{
            q = "upd";
        }
        userRecord.setAttribute("actionEx", q);
//        userRecord.setAttribute("id", recAux.getAttribute("idUser"));

//        if (entityType != EntityType.AMS) {
//            Record entityLevel = new Record();
//            entityLevel.setAttribute("id", levelSelect.getSelectedRecord().getAttributeAsInt("id"));
//            userRecord.setAttribute(levelSelect.getSelectedRecord().getAttributeAsString("entityType").toLowerCase(), entityLevel);
//        }
        return userRecord;
    }
}
