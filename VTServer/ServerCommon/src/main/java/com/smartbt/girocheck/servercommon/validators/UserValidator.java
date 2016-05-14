/*
 *  File UserValidator
 * 
 *  Date Created: January 2014
 * 
 *  Copyright @ @ 2004-2014 Smart Business Technology, Inc.
 *
 *  All rights reserved. No part of this software may be 
 *  reproduced, transmitted, transcribed, stored in a retrieval 
 *  system, or translated into any language or computer language,
 *  in any form or by any means, electronic, mechanical, magnetic, 
 *  optical, chemical, manual or otherwise, without the prior 
 *  written permission of Smart Business Technology, Inc.
 *
 */
package com.smartbt.girocheck.servercommon.validators;

import com.smartbt.girocheck.common.SessionAMSUser;
import com.smartbt.girocheck.common.VTSuiteMessages;
import com.smartbt.girocheck.servercommon.dao.UserDAO;
import com.smartbt.girocheck.servercommon.display.UserDisplay;
import com.smartbt.girocheck.servercommon.validators.utils.UtilValidator;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomUserPrivileges;
import javax.xml.bind.ValidationException;
import org.apache.log4j.Logger;

/**
 *
 * @author Ariel
 */
public class UserValidator {

    private static final Logger log = Logger.getLogger(UserValidator.class);

    /**
     * Search all the Users by a given filter Validator
     *
     * @param search
     * @param pageNumber
     * @param rowsPerPage
     * @throws java.lang.Exception
     */
    public static void searchUsers(String search, int pageNumber, int rowsPerPage) throws Exception {
        if (SessionAMSUser.get() != null) {
            UtilValidator.validatePrivilegesThrowEx(
                    NomUserPrivileges.ALLOW_ADMINISTRATION.getId()
                    , NomUserPrivileges.ALLOW_ADMINISTRATION_USERS.getId());
        }
        UtilValidator.validateSearchFilter(search);
    }

    /**
     * Update a User Validator
     *
     * @param user
     * @throws java.lang.Exception
     */
    public static void updateUser(UserDisplay user) throws Exception {
        if (SessionAMSUser.get() != null) {
            UtilValidator.validatePrivilegesThrowEx(
                    NomUserPrivileges.ALLOW_ADMINISTRATION.getId()
                    , NomUserPrivileges.ALLOW_ADMINISTRATION_USERS.getId()
                    , NomUserPrivileges.ALLOW_ADMINISTRATION_USERS_UPDATE.getId());
        }
        if (UserDAO.get().findById(user.getId()) == null) {
            log.info("----->  updateUser: This User does not exist <-----");
            throw new ValidationException(VTSuiteMessages.USER_DOES_NOT_EXIST);
        }
    }
    
    public static void addUser(UserDisplay user) throws Exception {
        if (SessionAMSUser.get() != null) {
            UtilValidator.validatePrivilegesThrowEx(
                    NomUserPrivileges.ALLOW_ADMINISTRATION.getId(), NomUserPrivileges.ALLOW_ADMINISTRATION_USERS.getId(), NomUserPrivileges.ALLOW_ADMINISTRATION_USERS_UPDATE.getId());
        }
//        if (UserDAO.get().findById(user.getId()) == null) {
//            log.info("----->  updateUser: This User does not exist <-----");
//            throw new ValidationException(VTSuiteMessages.USER_DOES_NOT_EXIST);
//        }
    }
        
    public static void deleteUser(int user) throws Exception {
        if (SessionAMSUser.get() != null) {
            UtilValidator.validatePrivilegesThrowEx(
                    NomUserPrivileges.ALLOW_ADMINISTRATION.getId(), NomUserPrivileges.ALLOW_ADMINISTRATION_USERS.getId(), NomUserPrivileges.ALLOW_ADMINISTRATION_USERS_UPDATE.getId());
        }
        if (UserDAO.get().findById(user) == null) {
            log.info("----->  updateUser: This User does not exist <-----");
            throw new ValidationException(VTSuiteMessages.USER_DOES_NOT_EXIST);
        }
    }
    
}
