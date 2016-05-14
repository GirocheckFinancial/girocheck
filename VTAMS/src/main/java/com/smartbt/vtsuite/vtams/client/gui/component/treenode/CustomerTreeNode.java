/*
 ** File: CustomerTreeNode.java
 **
 ** Date Created: May 2013
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
package com.smartbt.vtsuite.vtams.client.gui.component.treenode;

import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseTreeNode;
import com.smartgwt.client.widgets.tree.TreeNode;

/**
 * The Customer Tree Node
 *
 * @author Ariamnet Lopez
 */
public class CustomerTreeNode extends BaseTreeNode {

    /**
     * Constructor
     *
     */
    public CustomerTreeNode() {
        super();

        setEntityType(EntityType.CUSTOMER);
        setIcon("icons/customer.png");
    }
    
     /**
     * Constructor
     *
     * @param parentNode
     */
    public CustomerTreeNode(TreeNode parentNode) {
        super(parentNode);

        setEntityType(EntityType.CUSTOMER);
        setIcon("icons/customer.png");
    }
    
    /**
     * Set Node description
     *
     * @param description the node description
     */
    public void setNodeDescription(String description) {
        setAttribute("description", description.isEmpty() ? "-" : description);
    }

    /**
     * Get Node description
     *
     * @return String the node description
     */
    public String getNodeDescription() {
        return getAttributeAsString("description");
    }

    /**
     * Set Node number
     *
     * @param number the node number
     */
    public void setNodeNumber(String number) {
        setAttribute("number", number.isEmpty() ? "-" : number);
    }

    /**
     * Get Node number
     *
     * @return String the node number
     */
    public String getNodeNumber() {
        return getAttributeAsString("number");
    }
}
