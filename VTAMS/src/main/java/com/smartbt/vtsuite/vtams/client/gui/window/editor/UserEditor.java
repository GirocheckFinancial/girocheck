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
import com.smartbt.vtsuite.vtams.client.gui.base.BaseEditorWindow;
import com.smartbt.vtsuite.vtams.client.gui.component.BaseSelectItem;
import com.smartbt.vtsuite.vtams.client.gui.component.BaseTextItem;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.DataSourceBuilder;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.RoleDS;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.UserDS;
import static com.smartbt.vtsuite.vtams.client.utils.Utils.debug;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 * The User Editor Window
 *
 * @author Ariamnet Lopez, Ariel Saavedra
 */
public class UserEditor extends BaseEditorWindow {

    private BaseTextItem usernameText;
    private BaseTextItem lastNameText;
    private BaseTextItem firstNameText; 
    private BaseTextItem emailText;
    private BaseSelectItem activeSelect;
    private BaseSelectItem roleSelect; 
 
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

        activeSelect = new BaseSelectItem("active", "Active", DataSourceBuilder.getUserActiveDS(), false);
 
        activeSelect.setWidth(COMPONENTS_WIDTH);

        emailText = new BaseTextItem("email", false);
        emailText.setWidth(COMPONENTS_WIDTH);
 
        
        roleSelect = new BaseSelectItem("role", I18N.GET.LABEL_ROLE_TITLE(), new RoleDS(), true);
        roleSelect.setDataPath("role/id");
        roleSelect.setEmptyDisplayValue(I18N.GET.MESSAGE_EMPTY_ROLE_SELECT());
        roleSelect.setWidth(COMPONENTS_WIDTH);
 
        dataForm.setDataSource(new UserDS(entityType)); 
        dataForm.setFields(usernameText, lastNameText, firstNameText, activeSelect, emailText, roleSelect);
 
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
        activeSelect.setDisabled(false);
        emailText.setDisabled(false);
 
        activeSelect.fetchData();

        Criteria criteria = new Criteria();
 
        criteria.addCriteria("entityType", EntityType.AMS.toString());
 
        roleSelect.setPickListCriteria(criteria);
        roleSelect.fetchData();  
 
        super.updateRecord(record);
    }

    @Override
    public void addRecord(Record record) {
        usernameText.setDisabled(false);
        firstNameText.setDisabled(false);
        lastNameText.setDisabled(false); 
        activeSelect.setDisabled(false);
        emailText.setDisabled(false);

        Criteria criteria = new Criteria();
        criteria.addCriteria("entityType", EntityType.AMS.toString());
 
        roleSelect.setPickListCriteria(criteria);
        roleSelect.fetchData();  
 
        super.addRecord(record);
    }


    public Record getRecord() {
        Record userRecord = new Record();
          userRecord.setAttribute("id", dataForm.getValuesAsRecord().getAttributeAsInt("id"));
        userRecord.setAttribute("username", usernameText.getValueAsString());
        userRecord.setAttribute("lastName", lastNameText.getValueAsString());
        userRecord.setAttribute("firstName", firstNameText.getValueAsString());  
        userRecord.setAttribute("active", activeSelect.getValue()); 
        userRecord.setAttribute("email", emailText.getValueAsString()); 
        
        ListGridRecord roleRecord = roleSelect.getSelectedRecord();
   
        Integer idRole = roleRecord.getAttributeAsInt("id");
        String name = roleRecord.getAttributeAsString("name");
        String description = roleRecord.getAttributeAsString("description");
    
        Record role = new Record();

        role.setAttribute("id", idRole);
        role.setAttribute("name", name);
        role.setAttribute("description", description);
        userRecord.setAttribute("role", role);

        return userRecord;
    }
}
