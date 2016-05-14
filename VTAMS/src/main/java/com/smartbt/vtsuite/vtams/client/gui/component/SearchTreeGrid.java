/*
 ** File: SearchTreeGrid.java
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
package com.smartbt.vtsuite.vtams.client.gui.component;

import com.smartbt.vtsuite.vtams.client.classes.Settings;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseTreeGrid;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseTreeNode;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.CustomerDS;
import com.smartbt.vtsuite.vtams.client.gui.component.treenode.AgrupationTreeNode;
import com.smartbt.vtsuite.vtams.client.gui.component.treenode.ClerkTreeNode;
import com.smartbt.vtsuite.vtams.client.gui.component.treenode.CustomerTreeNode;
import com.smartbt.vtsuite.vtams.client.gui.component.treenode.DeviceTreeNode;
import com.smartbt.vtsuite.vtams.client.gui.component.treenode.MerchantTreeNode;
import com.smartbt.vtsuite.vtams.client.gui.component.treenode.TerminalTreeNode;
import com.smartbt.vtsuite.vtams.client.gui.window.MainWindow;
import com.smartbt.vtsuite.vtams.client.utils.Utils;
import com.smartbt.vtsuite.vtcommon.Constants;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.TreeModelType;
import com.smartgwt.client.widgets.tree.Tree;

/**
 * The Search Tree Grid
 *
 * @author Ariamnet Lopez
 */
public class SearchTreeGrid extends BaseTreeGrid {

    /**
     * The Search tree
     *
     */
    private Tree searchTree;

    /**
     * Constructor
     *
     */
    public SearchTreeGrid() {

        setEmptyMessage("");
        setStyleName("tree-list");

        searchTree = new Tree();
        searchTree.setModelType(TreeModelType.PARENT);
        searchTree.setNameProperty("name");
        searchTree.setIdField("idD");
        searchTree.setParentIdField("nodeParent");
        searchTree.setShowRoot(false);

        BaseTreeNode rootNavNode = new BaseTreeNode();
        searchTree.setRoot(rootNavNode);
        setData(searchTree);
    }

    public SearchTreeGrid(boolean dragable) {
        this();
        if (dragable) {
            setAsDragable();
        }
    }

    /**
     * Add nodes to the search tree
     *
     * @param parent the node parent
     * @param records the records
     * @param type the entity type. See
     * {@link com.smartbt.vtsuite.vtams.client.classes.EntityType EntityType}
     */
    public void addNodes(final BaseTreeNode parent, final EntityType type, final Record... records) {
//        if (EntityType.MERCHANT == type && parent.getEntityType() != EntityType.CUSTOMER) {
//            Criteria criteria = new Criteria();
//            criteria.setAttribute("id", 1);
//            new CustomerDS().fetchData(criteria, new DSCallback() {
//
//                @Override
//                public void execute(DSResponse response, Object rawData, DSRequest request) {
//                    if (response.getStatus() == Constants.CODE_SUCCESS) {
//                        BaseTreeNode customerParent = addNode(parent, response.getData()[0], EntityType.CUSTOMER);
//                        for (Record record : records) {
//                            addNode(customerParent, record, type);
//                        }
//                        searchTree.openFolder(customerParent);
//                    }
//                }
//            });
//        } else {
            for (Record record : records) {
                addNode(parent, record, type);
            }
//        }
    }

    private BaseTreeNode addNode(BaseTreeNode parent, Record record, EntityType type) {
        switch (type) {
            case AGRUPATION:
                AgrupationTreeNode agrupationNode = new AgrupationTreeNode(parent);
                if (record.getAttribute("id") != null) {
                    agrupationNode.setId(record.getAttributeAsInt("id"));
                } else {
                    agrupationNode.setCustomStyle("treeNodeUpdated");
                }
                agrupationNode.setName(record.getAttributeAsString("name"));
                if(record.getAttributeAsString("description")!= null){
                    agrupationNode.setNodeDescription(record.getAttributeAsString("description"));
                }
               // agrupationNode.setNodeIsActive(true);
                agrupationNode.setEntityRecord(record);
                searchTree.add(agrupationNode, parent);
                return agrupationNode;
            case MERCHANT:
                MerchantTreeNode merchantNode = new MerchantTreeNode(parent);
                if (record.getAttribute("id") != null) {
                    merchantNode.setId(record.getAttributeAsInt("id"));
                } else {
                    merchantNode.setCustomStyle("treeNodeUpdated");
                }
                merchantNode.setName(record.getAttributeAsString("legalName"));
                 if(record.getAttributeAsString("description")!= null){
                    merchantNode.setNodeDescription(record.getAttributeAsString("description"));
                 }
              //  merchantNode.setNodeIsActive(record.getAttributeAsBoolean("active"));
                merchantNode.setEntityRecord(record);
                searchTree.add(merchantNode, parent);
                return merchantNode;
            case TERMINAL:
                TerminalTreeNode terminalNode = new TerminalTreeNode(parent);
                if (record.getAttribute("id") != null) {
                    terminalNode.setId(record.getAttributeAsInt("id"));
                } else {
                    terminalNode.setCustomStyle("treeNodeUpdated");
                }
                terminalNode.setName(record.getAttributeAsString("serialNumber"));
                 if(record.getAttributeAsString("description")!= null){
                    terminalNode.setNodeDescription(record.getAttributeAsString("description"));
                 }
              //  terminalNode.setNodeIsActive(record.getAttributeAsBoolean("active"));
                terminalNode.setEntityRecord(record);
                searchTree.add(terminalNode, parent);
                return terminalNode;
           
        }
        return null;
    }

    /**
     * Remove nodes from the search tree.
     *
     */
    public void removeNodes() {
        searchTree.removeList(searchTree.getAllNodes());
    }

    /**
     * Get the root node
     *
     * @return BaseTreeNode the root node
     */
    public BaseTreeNode getRootNode() {
        return (BaseTreeNode) searchTree.getRoot();
    }

    public Tree getSearchTree() {
        return searchTree;
    }
}
