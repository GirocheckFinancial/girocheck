/*
 ** File: BoardingListener.java
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
package com.smartbt.vtsuite.vtams.client.gui.listener;

import com.smartbt.vtsuite.vtams.client.gui.base.BaseTreeNode;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartgwt.client.data.Record;
import java.util.EventListener;

/**
 * The Boarding Event Listener Interface
 *
 * @author Ariel Saavedra
 */
public interface FormBoardingListener extends EventListener {

    /**
     * Confirm Action
     *
     * @param record
     * @param selectNode
     */
//    public void confirmActionExecuted(Record record, boolean selectNode);

    /**
     * add Merchant
     *
     * @param parentNode
     */
    public void addMerchantActionExecuted(int id,int idParent);
    
    /**
     * add Terminal
     *
     * @param parentNode
     */
    public void addTerminalActionExecuted(int id,int idParent);
    
    public void reportActionExecuted(Record record,int idParent, EntityType entityType);
    
    /**
     * add Clerk
     *
     * @param parentNode
     */
    public void acceptActionExecuted(Record record, int idParent,  EntityType entityType);
    

}
