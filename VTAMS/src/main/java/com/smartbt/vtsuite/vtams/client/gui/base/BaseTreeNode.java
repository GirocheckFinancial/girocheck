/*
 ** File: BaseTreeNode.java
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

import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.tree.TreeNode;

/**
 * The Base Tree Node
 *
 * @author Ariamnet Lopez
 */
public class BaseTreeNode extends TreeNode {

    private TreeNode parentNode;

    /**
     * Constructor
     *
     */
    public BaseTreeNode() {
        super();
        setIsFolder(true);
    }

    /**
     * Constructor
     *
     * @param parentNode
     */
    public BaseTreeNode(TreeNode parentNode) {
        super();
        setIsFolder(true);
        setParentNode(parentNode);
    }

    public void setId(int id) {
        setAttribute("id", id);
    }

    public int getId() {
        return getAttributeAsInt("id");
    }

    /**
     * Set Node status
     *
     * @param isActive the node status (true or false)
     */
    public void setNodeIsActive(Boolean isActive) {
        setAttribute("active", isActive);
    }

    /**
     * Get Node status
     *
     * @return Boolean the node status (true or false)
     */
    public Boolean getNodeIsActive() {
        return getAttributeAsBoolean("active");
    }

    /**
     * Set Node type
     *
     * @param type the node type. See
     * {@link com.smartbt.vtsuite.vtams.client.classes.EntityType EntityType}
     *
     */
    public void setEntityType(EntityType type) {
        setAttribute("entityType", type);
    }

    /**
     * Get Node type
     *
     * @return EntityType the node type. See
     * {@link com.smartbt.vtsuite.vtams.client.classes.EntityType EntityType}
     */
    public EntityType getEntityType() {
        return (EntityType) getAttributeAsObject("entityType");
    }

    public Record getParentRecord() {
        return getAttributeAsRecord("parentRecord");
    }

    public void setEntityRecord(Record entityRecord) {
        setAttribute("entityRecord", entityRecord);
    }

    public Record getEntityRecord() {
        return getAttributeAsRecord("entityRecord");
    }

    public TreeNode getParentNode() {
        return parentNode;
    }

    private void setParentNode(TreeNode parentNode) {
        setAttribute("parentRecord", parentNode);
        this.parentNode = parentNode;
    }

}
