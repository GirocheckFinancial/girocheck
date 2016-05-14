/*
 ** File: UserManager.java
 **
 ** Date Created: March 2013
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
import com.smartbt.girocheck.servercommon.display.message.ResponseDataList;
import com.smartbt.vtsuite.servercommon.display.common.model.UserDisplay;
import com.smartbt.vtsuite.servercommon.dao.UserDAO;
import com.smartbt.vtsuite.servercommon.validators.UserValidator;
import com.smartbt.vtsuite.vtcommon.Constants;
import org.apache.log4j.Logger;

/**
 *
 * @author Ariel Saavedra
 */
public class UserManager {

    private static final Logger log = Logger.getLogger(UserManager.class);
    private UserDAO userDAO = UserDAO.get();

    /**
     * Search all the Users by a given filter
     *
     * @param search
     * @param pageNumber
     * @param rowsPerPage
     * @return
     * @throws java.lang.Exception
     */
    public ResponseDataList searchUsers(String search, int pageNumber, int rowsPerPage) throws Exception {
        UserValidator.searchUsers(search, pageNumber, rowsPerPage);
        ResponseDataList response = new ResponseDataList();
        response.setData(userDAO.searchUsers(search, pageNumber * rowsPerPage, rowsPerPage));
        int total = userDAO.searchUsers(search, -1, -1).size();
        response.setTotalPages((int) Math.ceil((float) total / (float) rowsPerPage));

        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
        return response;
    }

    /**
     * Update a User
     *
     * @param user
     * @return
     * @throws java.lang.Exception
     */
    public BaseResponse updateUser(UserDisplay user) throws Exception {
        UserValidator.updateUser(user);
        BaseResponse response = new BaseResponse();
        userDAO.updateUser(user);
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
        return response;
    }
}
