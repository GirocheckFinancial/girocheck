/*
 ** File: RootTreeNode.java
 **
 ** Date Created: December 2013
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
import com.smartbt.vtsuite.vtcommon.enums.EntityType;

/**
 * The Customer Tree Node
 *
 * @author Ariel Saavedra
 */
public class RootTreeNode extends BaseTreeNode {

    /**
     * Constructor
     *
     */
    public RootTreeNode() {
        super();
        setEntityType(EntityType.ENTERPRISE);
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

}
