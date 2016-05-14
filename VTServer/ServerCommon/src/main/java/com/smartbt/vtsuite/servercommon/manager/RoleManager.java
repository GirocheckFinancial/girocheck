/*
 ** File: RoleManager.java
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
import com.smartbt.vtsuite.servercommon.dao.RoleDAO;
import com.smartbt.vtsuite.servercommon.utils.validators.SbtServerCommonValidators;
import com.smartbt.vtsuite.servercommon.validators.RoleValidator;
import com.smartbt.vtsuite.vtcommon.Constants;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import javax.xml.bind.ValidationException;
import org.apache.log4j.Logger;

/**
 *
 * @author Ariel Saavedra
 */
public class RoleManager {

    private static final Logger log = Logger.getLogger(RoleManager.class);
    SbtServerCommonValidators validators = new SbtServerCommonValidators();
    private RoleDAO roleDAO = RoleDAO.get();

    public ResponseDataList getRoles(EntityType entityType) {
        ResponseDataList response = new ResponseDataList();
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
        response.setData(roleDAO.getRoles(entityType));
        return response;
    }

    public BaseResponse deleteRole(EntityType entityType, int idRole) {
        BaseResponse response = new BaseResponse();
//            if (!frontFacade.existObject(ClerkRole.class, role.getId())) {
//                response.setStatus(Constants.CODE_ERROR_GENERAL);
//                response.setStatusMessage(VTSuiteMessages.CLERK_ROLE_DOES_NOT_EXIST);
//                log.info("----->  updateClerkRole: This ClerkRole does not exist <-----");
//            } else {
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
        roleDAO.deleteRole(entityType, idRole);
//            }
        return response;
    }

    public BaseResponse updateRole(EntityType entityType, int idRole, String name, String description) {
        BaseResponse response = new BaseResponse();
//            if (!frontFacade.existObject(ClerkRole.class, role.getId())) {
//                response.setStatus(Constants.CODE_ERROR_GENERAL);
//                response.setStatusMessage(VTSuiteMessages.CLERK_ROLE_DOES_NOT_EXIST);
//                log.info("----->  updateClerkRole: This ClerkRole does not exist <-----");
//            } else {
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
        roleDAO.updateRole(entityType, idRole, name, description);
//            }
        return response;
    }

    public BaseResponse addRole(EntityType entityType, String name, String description) throws ValidationException {
        RoleValidator.addRole(entityType, name, description);
        BaseResponse response = new BaseResponse();
        roleDAO.addRole(entityType, name, description);
        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
        return response;
    }
}
