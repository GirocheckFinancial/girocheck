/*
 ** File: SearchBoardingManagmentWindow.java
 **
 ** Date Created: February 2014
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
package com.smartbt.vtsuite.vtams.client.gui.window.search;

import com.smartbt.vtsuite.vtams.client.classes.Settings;
import com.smartbt.vtsuite.vtams.client.classes.i18n.I18N;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseSearchWindow;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseTreeNode;
import com.smartbt.vtsuite.vtams.client.gui.component.SearchTreeGrid;
import com.smartbt.vtsuite.vtams.client.utils.Utils;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomUserPrivileges;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * The Search Boarding Managment Window
 *
 * @author Ariel Saavedra, Roberto Rodriguez
 */
public abstract class SearchBoardingManagmentWindow extends BaseSearchWindow {
    private BaseTreeNode selectedNode;
    private ButtonItem createAgrupationButton;
    private ButtonItem removeButton;

             

    /**
     * Constructor
     *
     */
    public SearchBoardingManagmentWindow() {
        super();
        
        createAgrupationButton = new ButtonItem("createAgrupationButton", "New Agrupation");
        createAgrupationButton.setStartRow(false);
        createAgrupationButton.setAlign(Alignment.RIGHT);
        createAgrupationButton.setWidth(100);
        createAgrupationButton.setHeight(30);
        createAgrupationButton.setDisabled(!Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_BOARDING_MANAGEMENT_AGRUPATION_ADD));
        createAgrupationButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                clickHandlerAgrupationNode(0, 0);
            }
        });
        
        removeButton = new ButtonItem("removeButton", "Remove");
        removeButton.setEndRow(false);
        removeButton.setAlign(Alignment.RIGHT);
        removeButton.setWidth(100);
        removeButton.setHeight(30);
        removeButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                clickHandlerRemoveButton();
            }
        });
        
        removeButton.setDisabled(!Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_BOARDING_MANAGEMENT_REMOVE));
//        removeButton.setDisabled( true);

        DynamicForm form = new DynamicForm();
        form.setAlign(Alignment.RIGHT);
        form.setColWidths("70%", "30%");
        form.setPadding(6);
        form.setNumCols(2);

//        hLayout.addMember( removeButton);
//        hLayout.addMember( createAgrupationButton);
        form.setFields(removeButton, createAgrupationButton );

        vSearchTreeLayout.addMember(form);
    }

    public abstract void clickHandlerAgrupationNode(final int id, final int idParent);

    public abstract void clickHandlerMerchantNode(final int id, final int idParent);

    public abstract void clickHandlerTerminalNode(final int id, final int idParent);
    
    public abstract void clickHandlerRemoveButton();


    public VLayout getDetailLayout() {
        return vDetailLayout;
    }


    @Override
    protected void selectActionExecuted(BaseTreeNode node) {
        this.selectedNode = node;
          Utils.debug( ":: *****selectActionExecuted() " );
        searchTreeGrid.getSearchTree().openFolder(node);
        if (detailWindow != null) {
            vDetailLayout.removeChild(detailWindow);
        }
        vManagementLayout.removeMembers(vManagementLayout.getMembers());
        
        EntityType entity = (EntityType) node.getAttributeAsObject("entityType");
        Utils.debug( "entityType :: " + entity );
        switch (entity) {
            case AGRUPATION: {
                Utils.debug( "antes del id");
                int id = node.getAttributeAsInt( "id");
                 Utils.debug( "desp del id :: " + id);
                clickHandlerAgrupationNode(id, 0);
                break;
            }
            case MERCHANT: {
                clickHandlerMerchantNode(node.getAttributeAsInt( "id"), node.getParentNode().getAttributeAsInt( "id"));
                break;
            }
            case TERMINAL: {
                clickHandlerTerminalNode(node.getAttributeAsInt( "id"), node.getParentNode().getAttributeAsInt( "id"));
                break;
            }
        }
    }

    public ButtonItem getSaveButton() {
        return createAgrupationButton;
    }

    public SearchTreeGrid getSearchTreeGrid() {
        return searchTreeGrid;
    }

    public ButtonItem getRemoveButton() {
        return removeButton;
    }

    public BaseTreeNode getSelectedNode() {
        return selectedNode;
    }
    
    

}
