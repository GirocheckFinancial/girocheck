/*
 ** File: ClerkTreeNode.java
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
import com.smartgwt.client.widgets.tree.TreeNode;

/**
 * The Clerk Tree Node
 *
 * @author Ariel Saavedra
 */
public class ClerkTreeNode extends BaseTreeNode {

    /**
     * Constructor
     *
     */
    public ClerkTreeNode() {
        super();

        setIsFolder(false);
        setEntityType(EntityType.CLERK);
        setIcon("icons/clerk.png");
    }

    /**
     * Constructor
     *
     * @param parentNode
     */
    public ClerkTreeNode(TreeNode parentNode) {
        super(parentNode);

        setIsFolder(false);
        setEntityType(EntityType.CLERK);
        setIcon("icons/clerk.png");
    }
    
    /**
     * Set Node lastName
     *
     * @param lastName
     */
    public void setNodeLastName(String lastName) {
        setAttribute("lastName", ((lastName == null) || (lastName.equals(""))) ? "-" : lastName);
    }

    /**
     * Set Node lastName
     *
     * @param firstName
     */
    public void setNodeFirstName(String firstName) {
        setAttribute("firstName", ((firstName == null) || (firstName.equals(""))) ? "-" : firstName);
    }
}
