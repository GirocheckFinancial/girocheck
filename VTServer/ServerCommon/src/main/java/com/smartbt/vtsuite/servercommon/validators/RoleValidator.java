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
package com.smartbt.vtsuite.servercommon.validators;

import com.smartbt.vtsuite.common.VTSuiteMessages;
import com.smartbt.vtsuite.servercommon.dao.RoleDAO;
import com.smartbt.vtsuite.servercommon.validators.utils.Validator;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
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

    public static void addRole(EntityType entityType, String name, String description) throws ValidationException {
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
    }
}
