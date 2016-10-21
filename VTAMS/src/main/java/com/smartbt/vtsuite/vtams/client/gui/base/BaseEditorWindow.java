/*
 ** File: BaseEditorWindow.java
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
package com.smartbt.vtsuite.vtams.client.gui.base;


import com.smartbt.vtsuite.vtams.client.classes.i18n.I18N;
import com.smartbt.vtsuite.vtams.client.gui.component.BaseButtonItem;
import com.smartbt.vtsuite.vtams.client.gui.listener.EditorListener;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.CloseClickEvent;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.events.VisibilityChangedEvent;
import com.smartgwt.client.widgets.events.VisibilityChangedHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.RowSpacerItem;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import java.util.ArrayList;

/**
 * The Base Editor Window
 *
 * @author Ariamnet Lopez, Ariel Saavedra
 */
public class BaseEditorWindow extends Window {

    /**
     * The main layout
     */
    protected VLayout mainVLayout;
    /**
     * The data form
     */
    protected DynamicForm dataForm;
    /**
     * The action form
     */
    protected DynamicForm actionForm;
    
    protected HLayout actionLayout;
    /**
     * The confirm button
     */
    protected BaseButtonItem confirmButton;
    /**
     * The reset button
     */
    protected BaseButtonItem resetButton;
    /**
     * The close button
     */
    protected BaseButtonItem closeButton;
    private ArrayList<EditorListener> listeners = new ArrayList<EditorListener>();

    /**
     * Add Editor listener
     *
     * @param listener the Editor listener
     */
    public void addListener(EditorListener listener) {
        listeners.add(listener);
    }

    /**
     * Removes Editor listener
     *
     * @param listener the Editor listener
     */
    public void removeListener(EditorListener listener) {
        listeners.remove(listener);
    }

    /**
     * Save action to execute
     *
     */
    public void SaveActionExecuted() {
        if (dataForm.validate()) {
            for (EditorListener listener : listeners) {
                listener.SaveActionExecuted();
            }
        }
    }

    /**
     * Close action to execute
     *
     */
    public void CloseActionExecuted() {
        for (EditorListener listener : listeners) {
            listener.CloseActionExecuted();
        }
    }

    /**
     * Constructor
     *
     * @param title the window title
     */
    public BaseEditorWindow(String title) {
        super();

        setTitle(title);
        setShowMinimizeButton(false);
        setIsModal(true);
        setShowModalMask(true);
        setAutoCenter(true);
        setAutoSize(true);
        
        addCloseClickHandler(new CloseClickHandler() {
            public void onCloseClick(CloseClickEvent event) {
                CloseActionExecuted();
            }
        });

        addVisibilityChangedHandler(new VisibilityChangedHandler() {
            public void onVisibilityChanged(VisibilityChangedEvent event) {
                if (event.getIsVisible()) {
                    init();                    
                } else {
                    dataForm.clearValues();
                }
            }
        });

        confirmButton = new BaseButtonItem("confirmButton", I18N.GET.BUTTON_CONFIRM_TITLE());
        confirmButton.setWidth(60);
        confirmButton.setAlign(Alignment.RIGHT);
        confirmButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                SaveActionExecuted();
            }
        });

        resetButton = createResetButton();

        closeButton = new BaseButtonItem("closeButton", I18N.GET.BUTTON_CLOSE_TITLE());
        closeButton.setWidth(60);
        closeButton.setAlign(Alignment.LEFT);
        closeButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                CloseActionExecuted();
            }
        });

        RowSpacerItem rowSpacer = new RowSpacerItem();
        rowSpacer.setHeight(10);
      
        actionForm = new DynamicForm();
        actionForm.setNumCols(4);
        actionForm.setWidth100();
        actionForm.setAlign(Alignment.RIGHT);
        //rowSpacer,
        actionForm.setFields( confirmButton, resetButton, closeButton);

        dataForm = new DynamicForm();
        dataForm.setHiliteRequiredFields(false);

        mainVLayout = new VLayout();

        mainVLayout.setPadding(10);
        mainVLayout.setWidth100();
        mainVLayout.setHeight100();
        mainVLayout.addMember(dataForm);
        
        actionLayout = new HLayout();
        actionLayout.addMember(actionForm);
        actionLayout.setLayoutAlign(Alignment.CENTER);
        mainVLayout.addMember(actionLayout);

        addItem(mainVLayout);
    }
    
    public BaseButtonItem createResetButton(){
        BaseButtonItem resetButton = new BaseButtonItem("resetButton", I18N.GET.BUTTON_RESET_TITLE());
        resetButton.setWidth(60);
        resetButton.setAlign(Alignment.RIGHT);
        resetButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                Reset();
            }
        });
        return resetButton;
    }
    
    public void Reset(){
        dataForm.reset();
        dataForm.focusInItem(0);
    }

    /**
     * Prepare to add a new record by setting to the editor the default values
     * of the record passed in.
     *
     * @param record the new record
     */
    public void addRecord(Record record) {
//        Iterator keys = record.toMap().keySet().iterator();
//        while (keys.hasNext()) {
//            String k = (String) keys.next();
//            System.out.println("Key: " + k + "  Value: " + record.getAttribute(k));
//            //dataForm.getRecordList().first().setAttribute(k, record.getAttribute(k));
//        }
//        dataForm.editNewRecord(dataForm.getRecordList().first().toMap()); 
        dataForm.editNewRecord(record.toMap());
    }

    public void init() {
        centerInPage();
    }

    /**
     * Prepare to update the record by setting to the editor the values of the
     * record passed in.
     *
     * @param record the record to update
     */
    public void updateRecord(Record record) {
        dataForm.editRecord(record);
    }

    public void submitForm() {
        dataForm.submitForm();
    }

    public DynamicForm getDataForm() {
        return dataForm;
    }

    public void setDataForm(DynamicForm dataForm) {
        this.dataForm = dataForm;
    }

    public DynamicForm getActionForm() {
        return actionForm;
    }

    public void setActionForm(DynamicForm actionForm) {
        this.actionForm = actionForm;
    }

    public BaseButtonItem getConfirmButton() {
        return confirmButton;
    }

    public void setConfirmButton(BaseButtonItem confirmButton) {
        this.confirmButton = confirmButton;
    }

    public BaseButtonItem getResetButton() {
        return resetButton;
    }

    public void setResetButton(BaseButtonItem resetButton) {
        this.resetButton = resetButton;
    }

    public BaseButtonItem getCloseButton() {
        return closeButton;
    }

    public void setCloseButton(BaseButtonItem closeButton) {
        this.closeButton = closeButton;
    }
}
