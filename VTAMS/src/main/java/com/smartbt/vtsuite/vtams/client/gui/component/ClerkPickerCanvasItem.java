/*
 *  File ClerkPickerVLayout
 * 
 *  Date Created: February 2013
 * 
 *  Copyright @ @ 2004-2014 Smart Business Technology, Inc.
 *
 *  All rights reserved. No part of this software may be 
 *  reproduced, transmitted, transcribed, stored in a retrieval 
 *  system, or translated into any language or computer language,
 *  in any form or by any means, electronic, mechanical, magnetic, 
 *  optical, chemical, manual or otherwise, without the prior 
 *  written permission of Smart Business Technology, Inc.
 *
 */
package com.smartbt.vtsuite.vtams.client.gui.component;

import com.smartbt.vtsuite.vtams.client.classes.Properties;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseTreeNode;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.CustomerDS;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.MerchantDS;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.TerminalDS;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.UserDS;
import com.smartbt.vtsuite.vtams.client.gui.component.treenode.CustomerTreeNode;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.DragDataAction;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.TransferImgButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.fields.CanvasItem;
import com.smartgwt.client.widgets.form.validator.CustomValidator;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HStack;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.layout.VStack;
import com.smartgwt.client.widgets.tree.events.FolderOpenedEvent;
import com.smartgwt.client.widgets.tree.events.FolderOpenedHandler;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class ClerkPickerCanvasItem extends CanvasItem {

    private final CustomerDS customerDS = new CustomerDS();
    private final MerchantDS merchantDS = new MerchantDS();
    private final TerminalDS terminalDS = new TerminalDS();
    private final UserDS clerkDS;

    private VLayout vlayout;
    private SearchTreeGrid sourceClerkTree;
    private SearchTreeGrid targetClerkTree;
    private EntityType entityType;
    private int idEntity;
    private Record recordEntity;

    public ClerkPickerCanvasItem(EntityType entityType, Record recordEntity, String title, String emptyMsg) {
        super("clerkPicker", title);

        setValidators(new CustomValidator() {

            @Override
            protected boolean condition(Object value) {
                return targetClerkTree.getRecords().length > 0;
            }
        });

        this.customerDS.setFetchDataURL(Properties.GET_CUSTOMERS_WS);

        this.clerkDS = new UserDS(entityType);
        this.clerkDS.setFetchDataURL(Properties.GET_CLERKS_BY_ENTITY_TYPE_WS);

        this.entityType = entityType;
        this.idEntity = recordEntity.getAttributeAsInt("id");
        this.recordEntity = recordEntity;

        vlayout = new VLayout();

        sourceClerkTree = new SearchTreeGrid(true);
        sourceClerkTree.setDragDataAction(DragDataAction.MOVE);
        sourceClerkTree.setSelectionType(SelectionStyle.MULTIPLE);
        sourceClerkTree.setWidth(300);
        sourceClerkTree.setHeight(350);
        sourceClerkTree.setEmptyMessage(emptyMsg);
        
        sourceClerkTree.addFolderOpenedHandler(new FolderOpenedHandler() {
            public void onFolderOpened(FolderOpenedEvent event) {
                openFolderActionExecuted((BaseTreeNode) event.getNode(), sourceClerkTree);
            }
        });

        targetClerkTree = new SearchTreeGrid(true);
        targetClerkTree.setDragDataAction(DragDataAction.MOVE);
        targetClerkTree.setSelectionType(SelectionStyle.MULTIPLE);
        targetClerkTree.setLeft(250);
        targetClerkTree.setWidth(300);
        targetClerkTree.setHeight(350);
        targetClerkTree.setEmptyMessage(emptyMsg);
        targetClerkTree.addFolderOpenedHandler(new FolderOpenedHandler() {
            public void onFolderOpened(FolderOpenedEvent event) {
                openFolderActionExecuted((BaseTreeNode) event.getNode(), targetClerkTree);
            }
        });

        clearAndLoadClerkTree();
        VStack moveControls = new VStack(10);
        moveControls.setWidth(32);
        moveControls.setHeight(74);
        moveControls.setLayoutAlign(Alignment.CENTER);

        TransferImgButton rightArrow = new TransferImgButton(TransferImgButton.RIGHT, new ClickHandler() {
            public void onClick(ClickEvent event) {
                targetClerkTree.transferSelectedData(sourceClerkTree);
            }
        });

        TransferImgButton leftArrow = new TransferImgButton(TransferImgButton.LEFT, new ClickHandler() {
            public void onClick(ClickEvent event) {
                sourceClerkTree.transferSelectedData(targetClerkTree);
            }
        });

        moveControls.addMember(rightArrow);
        moveControls.addMember(leftArrow);

        HStack grids = new HStack(10);
        grids.setHeight(160);
        grids.addMember(sourceClerkTree);
        grids.addMember(moveControls);
        grids.addMember(targetClerkTree);

        grids.draw();

        vlayout.addMember(grids);
        setCanvas(vlayout);
    }

    public ListGridRecord[] getTargetRecords() {
        return targetClerkTree.getRecords();
    }

    public void clearAndLoadClerkTree() {
        sourceClerkTree.removeNodes();
        //targetClerkTree.removeNodes();
        BaseTreeNode parentNode = new CustomerTreeNode();
        Record rootRecord = null;
        switch (entityType) {
            case CUSTOMER:
                rootRecord = recordEntity;
                break;
            case MERCHANT:
                rootRecord = recordEntity.getAttributeAsRecord("parentRecord");
                break;
            case TERMINAL:
                rootRecord = recordEntity.getAttributeAsRecord("parentRecord").getAttributeAsRecord("parentRecord");
                break;
        }
        if (rootRecord != null) {
            parentNode.setName(rootRecord.getAttributeAsString("name"));
            parentNode.setId(rootRecord.getAttributeAsInt("id"));
            sourceClerkTree.getSearchTree().add(parentNode, sourceClerkTree.getRootNode());

            openFolderActionExecuted(parentNode, sourceClerkTree);
            sourceClerkTree.getData().openAll();
        }
    }

    /**
     * Method to execute when an OpenFolder event is fired.
     *
     * @param node
     * @param treeGrid
     */
    public void openFolderActionExecuted(final BaseTreeNode node, SearchTreeGrid treeGrid) {
        // If the node doesn't have children yet               
        if (treeGrid.getSearchTree().getChildren(node).length == 0) {
            switch (node.getEntityType()) {
                case ENTERPRISE: {
                    addCustomers(node, treeGrid);
                    break;
                }
                case CUSTOMER: {
                    addMerchants(node, treeGrid);
                    break;
                }
                case MERCHANT: {
                    addTerminals(node, treeGrid);
                    break;
                }
            }
            addClerks(node, treeGrid);
        }
    }

    private void addCustomers(final BaseTreeNode node, final SearchTreeGrid treeGrid) {
        customerDS.fetchData(null, new DSCallback() {
            public void execute(DSResponse response, Object rawData, DSRequest request) {
                treeGrid.addNodes(node, EntityType.CUSTOMER, response.getData());
            }
        });
    }

    private void addMerchants(final BaseTreeNode node, final SearchTreeGrid treeGrid) {
        Criteria criteria = new Criteria();
        criteria.addCriteria("idCustomer", node.getAttributeAsString("id"));

        merchantDS.fetchData(criteria, new DSCallback() {
            public void execute(DSResponse response, Object rawData, DSRequest request) {
                treeGrid.addNodes(node, EntityType.MERCHANT, response.getData());
            }
        });
    }

    private void addTerminals(final BaseTreeNode node, final SearchTreeGrid treeGrid) {
        Criteria criteria = new Criteria();
        criteria.addCriteria("idMerchant", node.getAttributeAsString("id"));

        terminalDS.fetchData(criteria, new DSCallback() {
            public void execute(DSResponse response, Object rawData, DSRequest request) {
                treeGrid.addNodes(node, EntityType.TERMINAL, response.getData());
            }
        });
    }

    public void addClerks(final BaseTreeNode node, final SearchTreeGrid treeGrid) {
        Criteria criteria = new Criteria();
        criteria.addCriteria("idEntity", node.getAttribute("id"));
        criteria.addCriteria("entityType", node.getEntityType().toString());

        clerkDS.fetchData(criteria, new DSCallback() {
            public void execute(DSResponse response, Object rawData, DSRequest request) {
                Record[] clerksAlreadyLoaded = targetClerkTree.getRecords();

                //if (clerksAlreadyLoaded != null) {
                List<Record> respFilteredRecord = new ArrayList<Record>();
                for (Record data : response.getData()) {
                    boolean addRecord = true;
                    for (Record clerkAlreadyLoaded : clerksAlreadyLoaded) {
                        if (EntityType.valueOf(clerkAlreadyLoaded.getAttribute("entityType")) == EntityType.CLERK
                                && clerkAlreadyLoaded.getAttributeAsInt("id") == data.getAttributeAsInt("id")) {
                            addRecord = false;
                            break;
                        }
                    }
                    if (addRecord) {
                        respFilteredRecord.add(data);
                    }
                }

                Record[] clerksRecord = new Record[respFilteredRecord.size()];
                treeGrid.addNodes(node, EntityType.CLERK, respFilteredRecord.toArray(clerksRecord));
//                } else {
//                    treeGrid.addNodes(node, response.getData(), EntityType.CLERK);
//                }
            }
        });
    }

    public SearchTreeGrid getTargetClerkTree() {
        return targetClerkTree;
    }
}
