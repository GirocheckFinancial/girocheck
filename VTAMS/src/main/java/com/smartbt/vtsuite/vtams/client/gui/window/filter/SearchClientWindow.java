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
package com.smartbt.vtsuite.vtams.client.gui.window.filter;

import com.smartbt.vtsuite.vtams.client.classes.i18n.I18N;
import com.smartbt.vtsuite.vtams.client.gui.component.BaseButtonItem;
import com.smartbt.vtsuite.vtams.client.gui.component.PaginationForm;
import com.smartbt.vtsuite.vtams.client.gui.listener.EditorListener;
import com.smartbt.vtsuite.vtams.client.gui.window.list.ClientListGrid;
import com.smartbt.vtsuite.vtams.client.gui.window.list.SearchClientListGrid;
import com.smartbt.vtsuite.vtams.client.utils.Utils;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.CloseClickEvent;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.events.VisibilityChangedEvent;
import com.smartgwt.client.widgets.events.VisibilityChangedHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItem;
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
public class SearchClientWindow extends Window {

    protected VLayout mainVLayout;
    protected HLayout filterLayout;
    protected VLayout listLayout;
    protected HLayout actionLayout;

    private ClientFilterForm filterForm;
    protected PaginationForm paginationForm;
    private SearchClientListGrid searchClientListGrid;
    protected DynamicForm actionForm;

    /**
     * The confirm button
     */
    protected BaseButtonItem confirmButton;

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
        SC.say("Search OK");
    }

    /**
     * Close action to execute
     *
     */
    public void CloseActionExecuted() {
            SC.say("Search Close");
//        for (EditorListener listener : listeners) {
//            listener.CloseActionExecuted();
//        }
    }

    /**
     * Constructor
     *
     * @param title the window title
     */
    public SearchClientWindow( ) {
        super();
        Utils.debug("SearchClientWindow 1");
        setTitle("Search Client");
        setShowMinimizeButton(false);
        setIsModal(true);
        setShowModalMask(true);
        setAutoCenter(true);
        setAutoSize(true);
Utils.debug("SearchClientWindow 2");
        filterForm = new ClientFilterForm();
Utils.debug("SearchClientWindow 3");
        addCloseClickHandler(new CloseClickHandler() {
            public void onCloseClick(CloseClickEvent event) {
                CloseActionExecuted();
            }
        });
Utils.debug("SearchClientWindow 4");
        addVisibilityChangedHandler(new VisibilityChangedHandler() {
            public void onVisibilityChanged(VisibilityChangedEvent event) {
                if (event.getIsVisible()) {
                    init();
                } else {
                    // dataForm.clearValues();
                }
            }
        });
Utils.debug("SearchClientWindow 5");
        confirmButton = new BaseButtonItem("confirmButton", I18N.GET.BUTTON_CONFIRM_TITLE());
        confirmButton.setWidth(60);
        confirmButton.setAlign(Alignment.RIGHT);
        confirmButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                SaveActionExecuted();
            }
        });
Utils.debug("SearchClientWindow 6");
        closeButton = new BaseButtonItem("closeButton", I18N.GET.BUTTON_CLOSE_TITLE());
        closeButton.setWidth(60);
        closeButton.setAlign(Alignment.LEFT);
        closeButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                CloseActionExecuted();
            }
        });
Utils.debug("SearchClientWindow 7");
        RowSpacerItem rowSpacer = new RowSpacerItem();
        rowSpacer.setHeight(10);

        mainVLayout = new VLayout();

        paginationForm = new PaginationForm();

        filterLayout = new HLayout();
        filterLayout.setAutoHeight();
        filterLayout.addMember(filterForm);
        filterLayout.addMember(paginationForm);
Utils.debug("SearchClientWindow 8");
        listLayout = new VLayout();
        searchClientListGrid = new SearchClientListGrid();
        listLayout.addMember(listLayout);
Utils.debug("SearchClientWindow 9");
        actionForm.setFields(confirmButton, closeButton);
        actionLayout = new HLayout();
        actionLayout.addMember(actionForm);
        actionLayout.setLayoutAlign(Alignment.CENTER);
Utils.debug("SearchClientWindow 10");
        mainVLayout.addMember(filterLayout);
        mainVLayout.addMember(listLayout);
        mainVLayout.addMember(actionLayout);
Utils.debug("SearchClientWindow 11");
        addItem(mainVLayout);
        Utils.debug("SearchClientWindow 12");
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
//        dataForm.editNewRecord(record.toMap());
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
//        dataForm.editRecord(record);
    }

    public void submitForm() {
//        dataForm.submitForm();
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

    public BaseButtonItem getCloseButton() {
        return closeButton;
    }

    public void setCloseButton(BaseButtonItem closeButton) {
        this.closeButton = closeButton;
    }
}
