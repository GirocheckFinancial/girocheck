/*
 ** File: MerchantTreeNode.java
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
 * The Merchant Tree Node
 *
 * @author Ariamnet Lopez
 */
public class MerchantTreeNode extends BaseTreeNode {

    /**
     * Constructor
     *
     */
    public MerchantTreeNode() {
        super();

        setEntityType(EntityType.MERCHANT);
        setIcon("icons/merchant.png");
    }

    /**
     * Constructor
     *
     * @param parentNode
     */
    public MerchantTreeNode(TreeNode parentNode) {
        super(parentNode);

        setEntityType(EntityType.MERCHANT);
        setIcon("icons/merchant.png");
    }

    /**
     * Set Node description
     *
     * @param description the node description
     */
    public void setNodeDescription(String description) {
        setAttribute("description", ((description == null) || (description.equals(""))) ? "-" : description);
    }

    /**
     * Get Node description
     *
     * @return String the node description
     */
    public String getNodeDescription() {
        return getAttributeAsString("description");
    }

}
