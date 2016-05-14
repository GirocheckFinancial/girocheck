/*
 ** File: BaseEntityDetailWindow.java
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

import com.google.gwt.event.logical.shared.AttachEvent;
import com.smartbt.vtsuite.vtams.client.classes.i18n.I18N;
import com.smartbt.vtsuite.vtams.client.gui.base.detail.BaseDetailLayout;

import com.smartbt.vtsuite.vtams.client.gui.component.BaseTextItem;
import com.smartbt.vtsuite.vtams.client.gui.component.SearchTreeGrid;
import com.smartbt.vtsuite.vtams.client.gui.listener.TreeListener;
import com.smartbt.vtsuite.vtcommon.validator.RegExp;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.events.VisibilityChangedEvent;
import com.smartgwt.client.widgets.events.VisibilityChangedHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.PickerIcon;
import com.smartgwt.client.widgets.form.fields.events.FormItemClickHandler;
import com.smartgwt.client.widgets.form.fields.events.FormItemIconClickEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyPressEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyPressHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.layout.events.SectionHeaderClickEvent;
import com.smartgwt.client.widgets.layout.events.SectionHeaderClickHandler;
import com.smartgwt.client.widgets.tree.events.FolderOpenedEvent;
import com.smartgwt.client.widgets.tree.events.FolderOpenedHandler;
import com.smartgwt.client.widgets.tree.events.NodeClickEvent;
import com.smartgwt.client.widgets.tree.events.NodeClickHandler;
import java.util.ArrayList;

/**
 * The Base Entity Detail Window
 *
 * @author Ariamnet Lopez, Ariel Saavedra
 */
public class BaseEntityDetailWindow extends BaseWindow {

    // Search Section Stack
    protected HLayout hMainLayout = new HLayout();
    protected VLayout vSearchTreeLayout = new VLayout();
    protected SectionStack searchSectionStack = new SectionStack();
    protected SectionStackSection searchSection = new SectionStackSection(I18N.GET.WINDOW_SEARCH_SECTION_TITLE());
    protected DynamicForm searchForm = new DynamicForm();
    protected BaseTextItem searchText = new BaseTextItem("search", false);
    protected SearchTreeGrid searchTreeGrid = new SearchTreeGrid();
    // Detail Section Stack
    protected SectionStack detailSectionStack = new SectionStack();
    protected SectionStackSection detailSection = new SectionStackSection();
    // Data Section Stack
    //protected SectionStack dataSectionStack = new SectionStack();
    protected SectionStackSection managementSection = new SectionStackSection("");
    // Detail Section Components
    protected VLayout vDetailLayout = new VLayout();
    protected BaseDetailLayout detailWindow;
    // Management Section Components
    protected VLayout vManagementLayout = new VLayout();
    protected BaseManagementWindow managementWindow = new BaseManagementWindow();
    private ArrayList<TreeListener> listeners = new ArrayList<TreeListener>();

    /**
     * Add Tree listener
     *
     * @param listener the Tree listener
     */
    public void addListener(TreeListener listener) {
        listeners.add(listener);
    }

    /**
     * Remove Tree Listener
     *
     * @param listener the Tree listener
     */
    public void removeListener(TreeListener listener) {
        listeners.remove(listener);
    }

    /**
     * Search action to execute
     *
     */
    public void SearchActionExecuted() {
        for (TreeListener listener : listeners) {
            listener.SearchActionExecuted();
        }
    }

    /**
     * Select action to execute
     *
     * @param node the selected record
     */
    public void SelectActionExecuted(BaseTreeNode node) {
        for (TreeListener listener : listeners) {
            listener.SelectActionExecuted(node);
        }
    }

    /**
     * Open Folder action to execute
     *
     * @param node
     */
    public void OpenFolderActionExecuted(BaseTreeNode node) {
        for (TreeListener listener : listeners) {
            listener.OpenFolderActionExecuted(node);
        }
    }
    
    /**
     * Constructor
     *
     */
    public BaseEntityDetailWindow() {
        super();
        setHeight100();
        setWidth100();
        setShowHeader(false);
        setShowEdges(false);
        setStyleName("base-entity-window");
        setBodyStyle("base-entity-window");

        // Search Section   
        vSearchTreeLayout.setWidth100();
        vSearchTreeLayout.setHeight100();

//        searchTreeGrid.addRecordClickHandler(new RecordClickHandler() {
//            public void onRecordClick(RecordClickEvent event) {
//                SelectActionExecuted(event.getRecord());
//            }
//        });
        
        searchTreeGrid.addNodeClickHandler(new NodeClickHandler() {

            public void onNodeClick(NodeClickEvent event) {
                SelectActionExecuted((BaseTreeNode) event.getNode());
            }
        });

        searchTreeGrid.addFolderOpenedHandler(new FolderOpenedHandler() {
            public void onFolderOpened(FolderOpenedEvent event) {
                OpenFolderActionExecuted((BaseTreeNode) event.getNode());
            }
        });

        searchForm.setMargin(6);
        searchForm.setAutoFocus(true);
        searchForm.setWidth100();

        final PickerIcon clearPicker = new PickerIcon(PickerIcon.CLEAR, new FormItemClickHandler() {
            public void onFormItemClick(FormItemIconClickEvent event) {
                searchForm.clearValues();
            }
        });

        final PickerIcon searchPicker = new PickerIcon(PickerIcon.SEARCH, new FormItemClickHandler() {
            public void onFormItemClick(FormItemIconClickEvent event) {
                    SearchActionExecuted();
            }
        });
        searchText.setKeyPressFilter(RegExp.VALID_SEARCH_II_REG_EXP);
        searchText.setWidth(235);
        searchText.setShowTitle(false);
        searchText.setIcons(clearPicker, searchPicker);

        searchText.addKeyPressHandler(new KeyPressHandler() {
            public void onKeyPress(KeyPressEvent event) {
                if ((event.getKeyName().equals("Enter"))) {
                    SearchActionExecuted();
                }
            }
        });

        searchForm.setFields(searchText);
        vSearchTreeLayout.addMember(searchForm);
        vSearchTreeLayout.addMember(searchTreeGrid);

        searchSection.addItem(vSearchTreeLayout);
        searchSection.setExpanded(true);

        searchSectionStack.setVisibilityMode(VisibilityMode.MULTIPLE);
        searchSectionStack.setWidth("250");
        searchSectionStack.setHeight100();
        searchSectionStack.addSection(searchSection);

        // Detail Section                
        detailSectionStack.setVisibilityMode(VisibilityMode.MULTIPLE);

        vDetailLayout.setAutoHeight();

        detailSection.addItem(vDetailLayout);
        detailSection.setExpanded(false);
        detailSection.setTitle(I18N.GET.WINDOW_EMPTY_INFO_TITLE());
        detailSectionStack.addSection(detailSection);

        // Management Section
        vManagementLayout.setHeight100();
        
        managementSection.addItem(vManagementLayout);
        managementSection.setExpanded(true);
        managementSection.setCanCollapse(false);
        managementSection.setShowHeader(false);
        detailSectionStack.addSection(managementSection);

        hMainLayout.setMargin(1);
        hMainLayout.setMembersMargin(4);
        hMainLayout.addMember(searchSectionStack);
        hMainLayout.addMember(detailSectionStack);

        detailSectionStack.addSectionHeaderClickHandler(new SectionHeaderClickHandler() {
            public void onSectionHeaderClick(SectionHeaderClickEvent event) {
                boolean expanded = managementSection.getAttributeAsBoolean("expanded");
                managementSection.setExpanded(!expanded);
                managementSection.setAttribute("expanded", !expanded);
            }
        });
        addItem(hMainLayout);
    }

    public VLayout getManagementLayout() {
        return vManagementLayout;
    }

    public SearchTreeGrid getSearchTreeGrid() {
        return searchTreeGrid;
    }

    
}
