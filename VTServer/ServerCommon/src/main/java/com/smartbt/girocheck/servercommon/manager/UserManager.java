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
package com.smartbt.girocheck.servercommon.manager;

import com.smartbt.girocheck.common.VTSuiteMessages;
import com.smartbt.girocheck.servercommon.display.message.BaseResponse;
import com.smartbt.girocheck.servercommon.display.message.ResponseDataList;
import com.smartbt.girocheck.servercommon.display.UserDisplay;
import com.smartbt.girocheck.servercommon.dao.UserDAO;
import com.smartbt.girocheck.servercommon.dao.VTSessionDAO;
import com.smartbt.girocheck.servercommon.display.message.ResponseData;
import com.smartbt.girocheck.servercommon.utils.PasswordUtil;
import com.smartbt.girocheck.servercommon.validators.UserValidator;
import com.smartbt.vtsuite.vtcommon.Constants;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import javax.xml.bind.ValidationException;
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
    
    public BaseResponse deleteUser(int idUser) throws Exception { 
        UserValidator.deleteUser(idUser);
        BaseResponse response = new BaseResponse();
         
        VTSessionDAO.get().deleteSessionByUser(idUser); 
        userDAO.deleteUser(idUser);
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(com.smartbt.girocheck.common.VTSuiteMessages.SUCCESS);
//            }
        return response;
    }
    
    public ResponseData addUser(UserDisplay user) throws ValidationException, NoSuchAlgorithmException, Exception {
        UserValidator.addUser(user);
        ResponseData response = new ResponseData();
        String generatedPassword = PasswordUtil.generatePassword(8);
        UserDisplay userDisplay = userDAO.addUser(user.getUsername(), generatedPassword, user.getFirstName(), user.getLastName(), user.getActive(), user.getEmail(), user.getRole().getId());
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(com.smartbt.girocheck.common.VTSuiteMessages.SUCCESS);
        response.setData(userDisplay);
        return response;
    }
    
    public BaseResponse changePassword(int userId, String password) {
        BaseResponse response = new BaseResponse();        
        if (PasswordUtil.validatePasswordFormat(password)) {            
            
            try {
                userDAO.changePassword(userId, password);
                response.setStatus(Constants.CODE_SUCCESS);
                response.setStatusMessage(VTSuiteMessages.SUCCESS);
            } catch (ValidationException ex) {
                response.setStatus(Constants.INVALID_PASSWORD);
                response.setStatusMessage(ex.getMessage());
            }
            
        } else {            
            response.setStatus(Constants.INVALID_PASSWORD);
            response.setStatusMessage(com.smartbt.girocheck.common.VTSuiteMessages.INVALID_PASSWORD);
        }
        
        return response;
    }
    
    public UserDisplay getUserById(Integer id) {
        return userDAO.getUserById(id);
    }
}
