/*
 *  File RoleValidator
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
import com.smartbt.girocheck.servercommon.dao.RoleDAO;
import com.smartbt.girocheck.servercommon.validators.utils.UtilValidator;
import com.smartbt.girocheck.servercommon.validators.utils.Validator;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomUserPrivileges;
import com.smartbt.vtsuite.vtcommon.validator.Constants;
import com.smartbt.vtsuite.vtcommon.validator.RegExp;
import javax.xml.bind.ValidationException;
import org.apache.log4j.Logger;

/**
 *
 * @author Ariel
 */
public class RoleValidator {

    private static final Logger log = Logger.getLogger(RoleValidator.class);

    public static void addRole(EntityType entityType, String name, String description) throws Exception {
        if (entityType == null) {
            throw new ValidationException(VTSuiteMessages.VALUE_IS_EMPTY + " (entityType)");
        }
        
        if (!RoleDAO.get().findRolesByNameAndEntity(entityType, name).isEmpty()) {
            throw new ValidationException(VTSuiteMessages.ERROR_ROLE_REPEATED + " '" + name + "'.");
        }
        
        if (name == null || name.isEmpty() || name.length() > Constants.STANDARD_TEXT_MAX_LENGTH) {
            throw new ValidationException(VTSuiteMessages.ERROR_LENGTH + " (name)");
        }
        if (!Validator.matchPattern(name, RegExp.VALID_TEXT_V_REG_EXP)) {
            throw new ValidationException(VTSuiteMessages.ERROR_INVALID_CHARACTERS + " (name)");
        }

        if (description == null || description.isEmpty() || description.length() > Constants.MEDIUM_TEXT_MAX_LENGTH) {
            throw new ValidationException(VTSuiteMessages.ERROR_LENGTH + " (description)");
        }
        if (!Validator.matchPattern(description, RegExp.VALID_TEXT_VI_REG_EXP)) {
            throw new ValidationException(VTSuiteMessages.ERROR_INVALID_CHARACTERS + " (description)");
        }
        
        if (SessionAMSUser.get() != null) {
            UtilValidator.validatePrivilegesThrowEx(
                    NomUserPrivileges.ALLOW_ADMINISTRATION.getId(), NomUserPrivileges.ALLOW_ADMINISTRATION_ROLES.getId(), NomUserPrivileges.ALLOW_ADMINISTRATION_ROLES_NEW.getId());
        }
    }
    
    public static void getRoles(EntityType entityType) throws Exception {
        if (SessionAMSUser.get() != null) {
            UtilValidator.validatePrivilegesThrowEx(
                    NomUserPrivileges.ALLOW_ADMINISTRATION.getId()
                    , NomUserPrivileges.ALLOW_ADMINISTRATION_ROLES.getId());
        }
    }
    
    public static void deleteRole(int idRole) throws Exception {
        if (SessionAMSUser.get() != null) {
            UtilValidator.validatePrivilegesThrowEx(
                    NomUserPrivileges.ALLOW_ADMINISTRATION.getId(), NomUserPrivileges.ALLOW_ADMINISTRATION_ROLES.getId(), NomUserPrivileges.ALLOW_ADMINISTRATION_ROLES_DELETE.getId());
        }
        if (RoleDAO.get().findById(idRole) == null) {
            log.info("----->  updateUser: This Role does not exist <-----");
            throw new ValidationException(VTSuiteMessages.ROLE_DOES_NOT_EXIST);
        }
    }
}
