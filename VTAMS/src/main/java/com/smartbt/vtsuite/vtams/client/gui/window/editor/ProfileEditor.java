/*
 ** File: UserEditor.java
 **
 ** Date Created: October 2016
  
 */
package com.smartbt.vtsuite.vtams.client.gui.window.editor;

import com.smartbt.vtsuite.vtams.client.classes.Properties;
import com.smartbt.vtsuite.vtams.client.classes.i18n.I18N;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseDatasource;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseEditorWindow;
import com.smartbt.vtsuite.vtams.client.gui.component.BaseButtonItem;
import com.smartbt.vtsuite.vtams.client.gui.component.BaseTextItem;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.UserDS;
import com.smartbt.vtsuite.vtams.client.gui.listener.EditorListener;
import com.smartbt.vtsuite.vtams.client.utils.Utils;
import static com.smartbt.vtsuite.vtams.client.utils.Utils.debug;
import com.smartbt.vtsuite.vtcommon.Constants;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;

/**
 * The User Editor Window
 *
 * @author Ariamnet Lopez, Ariel Saavedra
 */
public class ProfileEditor extends BaseEditorWindow {

    private BaseTextItem usernameText; 
    private BaseTextItem firstNameText;
    private BaseTextItem lastNameText; 
    private BaseTextItem emailText;  
    private ChangePasswordEditor passwordEditorWindow;  
    
    
  //  private Record recAux;
 
    private EntityType entityType;
    private static final int COMPONENTS_WIDTH = 175;

    /**
     * Constructor
     *
     * @param entityType
     * {@link com.smartbt.vtsuite.vtams.client.classes.EntityType EntityType}
     * @param recordEntity
     */
    public ProfileEditor(EntityType entityType, Record recordEntity) {
        super("Profile");
        
        debug("--UserEditor() 1");
        
        this.entityType = entityType;
        usernameText = new BaseTextItem("username", true);
        usernameText.setWidth(COMPONENTS_WIDTH);
        lastNameText = new BaseTextItem("lastName", true);
        lastNameText.setWidth(COMPONENTS_WIDTH);
        firstNameText = new BaseTextItem("firstName", true);
        firstNameText.setWidth(COMPONENTS_WIDTH);
        

        emailText = new BaseTextItem("email", false);
        emailText.setWidth(COMPONENTS_WIDTH);

        debug("--UserEditor() 2");
        
        dataForm.setDataSource(new UserDS(entityType));
 debug("--UserEditor() 3");
        dataForm.setFields(firstNameText, lastNameText,usernameText,emailText);
 debug("--UserEditor() 4");
 
        
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
        emailText.setDisabled(false);
 
        Criteria criteria = new Criteria();
 
        criteria.addCriteria("entityType", EntityType.AMS.toString());
 
        super.updateRecord(record);
    }

    @Override
    public void addRecord(Record record) {
        usernameText.setDisabled(false);
        firstNameText.setDisabled(false);
        lastNameText.setDisabled(false);
        emailText.setDisabled(false);

        Criteria criteria = new Criteria();
        criteria.addCriteria("entityType", EntityType.AMS.toString());
 
        super.addRecord(record);
    }


    public Record getRecord() {
        Record userRecord = new Record();
        userRecord.setAttribute("id", dataForm.getValuesAsRecord().getAttributeAsInt("id"));
        userRecord.setAttribute("username", usernameText.getValueAsString());
        userRecord.setAttribute("lastName", lastNameText.getValueAsString());
        userRecord.setAttribute("firstName", firstNameText.getValueAsString());
        userRecord.setAttribute("email", emailText.getValueAsString());
     
        return userRecord;
    }
    
    @Override
    public BaseButtonItem createResetButton(){
        BaseButtonItem resetButton = new BaseButtonItem("resetButton", "Change Password");
        resetButton.setWidth(120);
        resetButton.setAlign(Alignment.RIGHT);
        resetButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                debug("--Change Password");
                Reset();
//                dataForm.reset();
//                dataForm.focusInItem(0);
            }
        });
        return resetButton;
    }
    
    public void Reset(){
         debug("--Reset");
        passwordEditorWindow = new ChangePasswordEditor(entityType, null);
       
        passwordEditorWindow.addListener(new EditorListener() {
            /**
             * Method to execute when a Save event is fired.
             *
             */
            public void SaveActionExecuted() {
                ChangePassword();                
            }

             
            public void CloseActionExecuted() {
                passwordEditorWindow.hide();
            }
        });
        
        passwordEditorWindow.show();
    }
    
    public void ChangePassword(){
        Record passwordRecord = passwordEditorWindow.getRecord(); 
        if(!passwordRecord.getAttribute("password").equals(passwordRecord.getAttribute("checkpassword"))){          
            return;
        }
        
        BaseDatasource ds = new BaseDatasource();

        Criteria criteria = new Criteria();
        criteria.addCriteria( "userId", Utils.getUserId());
        criteria.addCriteria( "password", passwordRecord.getAttribute("password"));
        
        ds.setFetchDataURL( Properties.CHANGE_PASSWORD_WS );
        ds.fetchData( criteria, new DSCallback() { 
            public void execute(DSResponse response, Object rawData, DSRequest request) {
               debug("--ChangePassword callback response.getStatus() =" + response.getStatus());
                
               if(response.getStatus() == Constants.CODE_SUCCESS){
                   passwordEditorWindow.hide();
               }
                
            }
        });
    }
}
