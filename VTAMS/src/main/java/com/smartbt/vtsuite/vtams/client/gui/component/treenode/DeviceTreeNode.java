/*
 ** File: DeviceTreeNode.java
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
 * The Device Tree Node
 *
 * @author Ariamnet Lopez
 */
public class DeviceTreeNode extends BaseTreeNode {

    /**
     * Constructor
     *
     */
    public DeviceTreeNode() {
        super();

        setIsFolder(false);
        setEntityType(EntityType.DEVICE);
        setIcon("icons/device.png");
    }
    
    /**
     * Constructor
     *
     * @param parentNode
     */
    public DeviceTreeNode(TreeNode parentNode) {
        super(parentNode);

        setIsFolder(false);
        setEntityType(EntityType.DEVICE);
        setIcon("icons/device.png");
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

    /**
     * Set Node serial number
     *
     * @param serialNumber the node serial number
     */
    public void setNodeSerialNumber(String serialNumber) {
        setAttribute("serialNumber", ((serialNumber == null) || (serialNumber.equals(""))) ? "-" : serialNumber);
    }

    /**
     * Get Node serial number
     *
     * @return String the node serial number
     */
    public String getNodeSerialNumber() {
        return getAttributeAsString("serialNumber");
    }
    
    /**
     * Set Node product code
     *
     * @param productCode the node product code
     */
    public void setNodeProductCode(String productCode) {
        setAttribute("productCode", ((productCode == null) || (productCode.equals(""))) ? "-" : productCode);
    }

    /**
     * Get Node product code
     *
     * @return String the node product code
     */
    public String getNodeProductCode() {
        return getAttributeAsString("productCode");
    }
}
