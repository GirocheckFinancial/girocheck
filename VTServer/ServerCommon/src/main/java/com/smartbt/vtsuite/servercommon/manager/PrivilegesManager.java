/*
 ** File: PrivilegesManager.java
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
import com.smartbt.vtsuite.servercommon.dao.PrivilegesDAO;
import com.smartbt.vtsuite.servercommon.utils.validators.SbtServerCommonValidators;
import com.smartbt.vtsuite.vtcommon.Constants;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import org.apache.log4j.Logger;

/**
 *
 * @author Ariel Saavedra
 */
public class PrivilegesManager {

    private static final Logger log = Logger.getLogger(PrivilegesManager.class);
    SbtServerCommonValidators validators = new SbtServerCommonValidators();
    private PrivilegesDAO privilegesDAO = PrivilegesDAO.get();

    public ResponseDataList searchRolePrivileges(EntityType entityType, boolean entityNotContain, int idRole, int pageNumber, int rowsPerPage) {
        ResponseDataList response = new ResponseDataList();
//            if (!frontFacade.existObject(UserRole.class, idRole)) {
//                response.setStatus(Constants.CODE_ERROR_GENERAL);
//                response.setStatusMessage(VTSuiteMessages.USER_ROLE_DOES_NOT_EXIST);
//                log.info("----->  getUserRolePrivileges: This UserRole does not exist <-----");
//            } else {
        response.setData(privilegesDAO.searchRolePrivileges(entityType, entityNotContain, idRole, pageNumber * rowsPerPage, rowsPerPage));
        int total = privilegesDAO.searchRolePrivileges(entityType, entityNotContain, idRole, -1, -1).size();
        response.setTotalPages((int) Math.ceil((float) total / (float) rowsPerPage));

        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
//            }
        return response;
    }

    public BaseResponse addRolePrivilege(EntityType entityType, int idRole, int idPrivilege) {
        BaseResponse response = new BaseResponse();
//            if (!frontFacade.existObject(UserRole.class, idRole)) {
//                response.setStatus(Constants.CODE_ERROR_GENERAL);
//                response.setStatusMessage(VTSuiteMessages.USER_ROLE_DOES_NOT_EXIST);
//                log.info("----->  addUserRolePrivilege: This UserRole does not exist <-----");
//            } else if (!frontFacade.existObject(UserPrivilege.class, idPrivilege)) {
//                response.setStatus(Constants.CODE_ERROR_GENERAL);
//                response.setStatusMessage(VTSuiteMessages.USER_PRIVILEGE_DOES_NOT_EXIST);
//                log.info("----->  addUserRolePrivilege: This UserPrivilege does not exist <-----");
//            } else 
        if (privilegesDAO.existRolePrivilege(entityType, idRole, idPrivilege)) {
            response.setStatus(Constants.CODE_ERROR_GENERAL);
            response.setStatusMessage(VTSuiteMessages.ASSOCIATION_ALREADY_EXIST);
            log.info("----->  addRolePrivilege: This association already exist <-----");
        } else {
            response.setStatus(Constants.CODE_SUCCESS);
            response.setStatusMessage(VTSuiteMessages.SUCCESS);
            privilegesDAO.addRolePrivilege(entityType, idRole, idPrivilege);
        }
        return response;
    }

    public BaseResponse deleteRolePrivilege(EntityType entityType, int idRolePrivilege) {
        BaseResponse response = new BaseResponse();
//            if (!frontFacade.existObject(UserRolePrivilege.class, idRole)) {
//                response.setStatus(Constants.CODE_ERROR_GENERAL);
//                response.setStatusMessage(VTSuiteMessages.USER_ROLE_PRIVILEGE_DOES_NOT_EXIST);
//                log.info("----->  deleteUserRolePrivilege: This UserRolePrivilege does not exist <-----");
//            } else {
        privilegesDAO.deleteRolePrivilege(entityType, idRolePrivilege);
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
//            }
        return response;
    }
}
