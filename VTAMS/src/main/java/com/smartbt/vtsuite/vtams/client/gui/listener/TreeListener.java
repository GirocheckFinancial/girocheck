/*
 ** File: TreeListener.java
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
package com.smartbt.vtsuite.vtams.client.gui.listener;

import com.smartbt.vtsuite.vtams.client.gui.base.BaseTreeNode;
import com.smartgwt.client.data.Record;
import java.util.EventListener;

/**
 * The Tree Event Listener Interface
 *
 * @author Ariamnet Lopez
 */
public interface TreeListener extends EventListener {

    /**
     * Search event
     *
     */
    public void SearchActionExecuted();

    /**
     * Select event
     *
     * @param node the selected record
     */
    public void SelectActionExecuted(BaseTreeNode node);

    /**
     * Open Folder event
     *
     * @param node the selected node
     */
    public void OpenFolderActionExecuted(BaseTreeNode node);
}
