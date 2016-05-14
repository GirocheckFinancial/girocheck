/*
 ** File: DashboardAMSManager.java
 **
 ** Date Created: December 2013
 **
 ** Copyright @ 2004-2013 Smart Business Technology, Inc.
 **
 ** All rights reserved. No part of this software may be 
 ** reproduced, transmitted, transcribed, stored in a retrieval 
 ** system, or translated into any language or computer language, 
 ** in any form or by any means, electronic, mechanical, magnetic, 
 ** optical, chemical, manual or otherwise, without the prior 
 ** written permission of Smart Business Technology, Inc.
 **
 */
package com.smartbt.vtsuite.manager;

import com.smartbt.vtsuite.common.VTSuiteMessages;
import com.smartbt.vtsuite.dao.DashboardAMSDAO;
import com.smartbt.girocheck.servercommon.display.message.ResponseDataList;
import com.smartbt.vtsuite.validators.DashboardValidator;
import com.smartbt.vtsuite.vtcommon.Constants;

/**
 *
 * @author Ariel Saavedra
 */
public class DashboardAMSManager  {

    /**
     * Get list of environmental dashboard
     *
     * @return ResponseDataList
     */
    public ResponseDataList getDashboardEnvironmental() throws Exception {
        DashboardValidator.getDashboardEnvironmental();
        ResponseDataList response = new ResponseDataList();
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
        response.setData(DashboardAMSDAO.get().getDashboardEnvironmental());
        return response;
    }
}
