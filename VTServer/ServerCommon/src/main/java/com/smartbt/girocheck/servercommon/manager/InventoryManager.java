/*
 * Copyright @ 2004-2014 Smart Business Technology, Inc.
 *
 * All rights reserved. No part of this software may be 
 * reproduced, transmitted, transcribed, stored in a retrieval
 * system, or translated into any language or computer language, 
 * in any form or by any means, electronic, mechanical, magnetic,  
 * optical, chemical, manual or otherwise, without the prior  
 * written permission of Smart Business Technology, Inc.  
 *
 *
 */
package com.smartbt.girocheck.servercommon.manager;
 
import com.smartbt.girocheck.servercommon.dao.InventoryDAO; 
import com.smartbt.girocheck.servercommon.display.InventoryDisplay;
import com.smartbt.girocheck.servercommon.display.MerchantDisplay;
import com.smartbt.girocheck.servercommon.display.message.ResponseData;
import com.smartbt.girocheck.servercommon.display.message.ResponseDataList;
import com.smartbt.vtsuite.common.VTSuiteMessages;
import com.smartbt.vtsuite.vtcommon.Constants;
import java.util.List;
/**
 *
 * @author Roberto Rodriguez :: <roberto.rodriguez@smartbt.com>
 */
public class InventoryManager {
    private InventoryDAO inventoryDAO = InventoryDAO.get();
    
     protected static InventoryManager _this;
 
    public static InventoryManager get() {
        if (_this == null) {
            _this = new InventoryManager();
        }
        return _this;
    }
    
    public ResponseDataList searchInventory(int firstResult, int maxResult) {
        return inventoryDAO.searchInventory(firstResult, maxResult);
    }

    public ResponseData saveOrUpdateInventory(InventoryDisplay inventory) {
        inventoryDAO.saveOrUpdate(inventory);
        
        ResponseData  response = new ResponseData();
        response.setStatus( Constants.CODE_SUCCESS );
        response.setStatusMessage( VTSuiteMessages.SUCCESS );
        
        return response;
    }

}
