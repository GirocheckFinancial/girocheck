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
package com.smartbt.girocheck.servercommon.manager;

import com.smartbt.girocheck.common.VTSuiteMessages;
import com.smartbt.girocheck.servercommon.display.message.BaseResponse;
import com.smartbt.girocheck.servercommon.display.message.ResponseDataList;
import com.smartbt.girocheck.servercommon.dao.PrivilegesDAO;
import com.smartbt.girocheck.servercommon.validators.PrivilegesValidator;
import com.smartbt.vtsuite.servercommon.utils.validators.SbtServerCommonValidators;
import com.smartbt.vtsuite.vtcommon.Constants;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import org.apache.log4j.Logger;

/**
 *
 * @author Ariel Saavedra
 */
public class PrivilegesManager {

//    private static final Logger log = Logger.getLogger(PrivilegesManager.class);
    SbtServerCommonValidators validators = new SbtServerCommonValidators();
    private PrivilegesDAO privilegesDAO = PrivilegesDAO.get();

    public ResponseDataList searchRolePrivileges(EntityType entityType, boolean entityNotContain, int idRole, int pageNumber, int rowsPerPage) throws Exception {
        PrivilegesValidator.searchRolePrivileges(null, pageNumber, rowsPerPage);
        ResponseDataList response = new ResponseDataList();

        response.setData(privilegesDAO.searchRolePrivileges(entityType, entityNotContain, idRole, pageNumber * rowsPerPage, rowsPerPage));
        int total = privilegesDAO.searchRolePrivileges(entityType, entityNotContain, idRole, -1, -1).size();
        response.setTotalPages((int) Math.ceil((float) total / (float) rowsPerPage));

        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);

        return response;
    }

    public BaseResponse addRolePrivilege(EntityType entityType, int idRole, int idPrivilege) throws Exception {
        PrivilegesValidator.addRolePrivilege(idRole, idPrivilege);
        BaseResponse response = new BaseResponse();

        if (privilegesDAO.existRolePrivilege(entityType, idRole, idPrivilege)) {
            response.setStatus(Constants.CODE_ERROR_GENERAL);
            response.setStatusMessage(VTSuiteMessages.ASSOCIATION_ALREADY_EXIST);
//            log.info("----->  addRolePrivilege: This association already exist <-----");
        } else {
            response.setStatus(Constants.CODE_SUCCESS);
            response.setStatusMessage(VTSuiteMessages.SUCCESS);
            privilegesDAO.addRolePrivilege(entityType, idRole, idPrivilege);
        }
        return response;
    }

    public BaseResponse deleteRolePrivilege( int rolePrivilegeId) throws Exception {
        System.out.println("Entered PrivilegeManager deleteRolePrivilege() with rolePrivilegeId: " + rolePrivilegeId);
        PrivilegesValidator.deleteRolePrivilege(rolePrivilegeId);
        BaseResponse response = new BaseResponse();
        
        privilegesDAO.deleteRolePrivilege(rolePrivilegeId);
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);

        return response;
    }
}
