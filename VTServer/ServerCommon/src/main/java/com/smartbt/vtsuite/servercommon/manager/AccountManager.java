/*
 ** File: AccountManager.java
 **
 ** Date Created: Novemeber 2013
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
package com.smartbt.vtsuite.servercommon.manager;

import com.smartbt.vtsuite.common.VTSuiteMessages;
import com.smartbt.girocheck.servercommon.display.message.BaseResponse;
import com.smartbt.vtsuite.servercommon.dao.AccountDAO;
import com.smartbt.vtsuite.servercommon.watchdog.WatchdogWrapper;
import com.smartbt.vtsuite.vtcommon.Constants;
import org.apache.log4j.Logger;

/**
 *
 * @author Ariel Saavedra
 */
public class AccountManager {

    private static final Logger log = Logger.getLogger(AccountManager.class);
    private AccountDAO accountDAO = AccountDAO.get();

   
    /**
     * remove Client From Account
     *
     * @param accountId
     * @return
     * @throws java.lang.Exception
     */
    public BaseResponse removeAccountFromClient(int accountId) throws Exception {
        BaseResponse response = new BaseResponse();
        accountDAO.removeAccountFromClient(accountId);
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
        
        WatchdogWrapper.wrapAccountDeletedMessage();
        return response;
    }
}
